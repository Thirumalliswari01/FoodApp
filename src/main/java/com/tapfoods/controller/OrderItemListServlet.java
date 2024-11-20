package com.tapfoods.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tapfoods.dao.OrderItemDAO;
import com.tapfoods.daoimpl.OrderItemDAOImpl;
import com.tapfoods.model.OrderItem;

@WebServlet("/orderItemList")
public class OrderItemListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

        	int orderId = Integer.parseInt(req.getParameter("orderid")); 
            System.out.println("Order ID received: " + orderId); 
            

            OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
            List<OrderItem> orderItemList = orderItemDAO.fetchOrdersItems(orderId);
            System.out.println("Fetched Order Items: " + orderItemList);


            if (orderItemList != null && !orderItemList.isEmpty()) {
                req.setAttribute("orderitemlist", orderItemList);
            } else {
                req.setAttribute("message", "No items found for this order.");
            }


            req.getRequestDispatcher("OrderhistoryTime.jsp").include(req, resp);

        } catch (NumberFormatException e) {
            System.err.println("Invalid order ID format: " + e.getMessage());
            resp.sendRedirect("errorPage.jsp"); 
        } catch (Exception e) {
            System.err.println("Error retrieving order items: " + e.getMessage());
            resp.sendRedirect("errorPage.jsp");
        }
    }
}
