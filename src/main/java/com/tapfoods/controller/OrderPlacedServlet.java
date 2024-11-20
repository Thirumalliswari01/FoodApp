package com.tapfoods.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.OrderHistoryDAO;
import com.tapfoods.dao.OrderItemDAO;
import com.tapfoods.dao.OrderTableDAO;
import com.tapfoods.daoimpl.OrderHistoryDAOImpl;
import com.tapfoods.daoimpl.OrderItemDAOImpl;
import com.tapfoods.daoimpl.OrderTableDAOImpl;
import com.tapfoods.model.Cart;
import com.tapfoods.model.CartItem;
import com.tapfoods.model.OrderHistory;
import com.tapfoods.model.OrderItem;
import com.tapfoods.model.OrderTable;
import com.tapfoods.model.User;

@WebServlet("/orderPlaced")
public class OrderPlacedServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            resp.sendRedirect("login.html");  
            return;
        }

        if (cart == null || cart.getItems().isEmpty()) {
            resp.sendRedirect("cart.jsp?message=Your cart is empty or has expired.");
            return;
        }

        int userId = user.getUserId();
        String paymentMode = req.getParameter("paymentMode");
        float totalAmount = 0;

        try {
            totalAmount = Float.parseFloat(req.getParameter("totalAmount"));
        } catch (NumberFormatException e) {
            resp.sendRedirect("errorPage.html?error=Invalid totalAmount format");
            return;
        }

        String restaurantIdStr = req.getParameter("restaurantId");
        int restaurantId = 0;

        if (restaurantIdStr != null && !restaurantIdStr.isEmpty()) {
            try {
                restaurantId = Integer.parseInt(restaurantIdStr);
            } catch (NumberFormatException e) {
                resp.sendRedirect("errorPage.html?error=Invalid+restaurantId+format");
                return;
            }
        } else {
            resp.sendRedirect("errorPage.html?error=Missing+restaurantId");
            return;
        }

        OrderTable order = new OrderTable();
        order.setUserId(userId);
        order.setPaymentMode(paymentMode);
        order.setRestaurantId(restaurantId);
        order.setTotalAmount(totalAmount);

        OrderTableDAO orderDAO = new OrderTableDAOImpl();
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
        OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();

        try {
            int orderId = orderDAO.insertOrder(order);

            if (orderId > 0) {
                Map<Integer, CartItem> itemsMap = cart.getItems();
                for (CartItem cartItem : itemsMap.values()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(orderId);
                    orderItem.setMenuId(cartItem.getItemId());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setSubTotal(cartItem.getSubtotal());

                    if (orderItemDAO.insertOrderItem(orderItem) <= 0) {
                        throw new Exception("Failed to insert order item with Menu ID: " + cartItem.getItemId());
                    }
                }

                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setOrderId(orderId);
                orderHistory.setUserId(userId);
                orderHistory.settotalAmount(totalAmount);

                try {
                    int result = orderHistoryDAO.addOrderHistory(orderHistory);
                    if (result <= 0) {
                        throw new SQLException("Failed to insert order history for Order ID: " + orderId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendRedirect("errorPage.html?error=" + e.getMessage());
                    return;
                }

                resp.sendRedirect("orderSuccess.jsp?orderId=" + orderId + "&totalAmount=" + totalAmount);
            } else {
                resp.sendRedirect("errorPage.html?error=Failed to place order.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("errorPage.html?error=" + e.getMessage());
        }
    }
}
