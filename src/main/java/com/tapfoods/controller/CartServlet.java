package com.tapfoods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.daoimpl.MenuDAOImpl;
import com.tapfoods.model.Cart;
import com.tapfoods.model.CartItem;
import com.tapfoods.model.Menu;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String actionType = request.getParameter("action");

        if ("add".equals(actionType)) {
            addItemToCart(request, cart);
        } else if ("update".equals(actionType)) {
            updateCartItem(request, cart);
        } else if ("remove".equals(actionType)) {
            removeCartItem(request, cart);
        }

        session.setAttribute("cart", cart);

        if (cart.getItems().isEmpty()) {
            response.sendRedirect("cart.jsp?message=Your cart is empty");
        } else {
            response.sendRedirect("cart.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null || cart.getItems().isEmpty()) {
            request.setAttribute("message", "Your cart is empty.");
        }

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    private void addItemToCart(HttpServletRequest request, Cart cart) {
        try {
            String itemIdStr = request.getParameter("itemId");
            String quantityStr = request.getParameter("quantity");

            if (itemIdStr == null || quantityStr == null) {
                return;
            }

            int itemId = Integer.parseInt(itemIdStr);
            int quantity = Integer.parseInt(quantityStr);

            if (quantity <= 0) {
                return;
            }

            MenuDAO menuDAO = new MenuDAOImpl();
            Menu menuItem = menuDAO.getMenuItemById(itemId);

            if (menuItem != null) {
                double subtotal = quantity * menuItem.getPrice();

                CartItem cartItem = new CartItem(
                        menuItem.getMenuId(),
                        menuItem.getRestaurantId(),
                        menuItem.getItemName(),
                        quantity,
                        menuItem.getPrice(),
                        subtotal,
                        menuItem.getImagePath()
                );

                cart.addItem(cartItem);

                HttpSession session = request.getSession();
                session.setAttribute("restaurantId", menuItem.getRestaurantId());
            } else {
                System.out.println("Error: Menu item not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCartItem(HttpServletRequest request, Cart cart) {
        try {
            String itemIdStr = request.getParameter("itemId");
            String quantityStr = request.getParameter("quantity");

            if (itemIdStr == null || quantityStr == null) {
                return;
            }

            int itemId = Integer.parseInt(itemIdStr);
            int quantity = Integer.parseInt(quantityStr);

            if (quantity <= 0) {
                return;
            }

            cart.updateItem(itemId, quantity);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format while updating item. " + e.getMessage());
        }
    }

    private void removeCartItem(HttpServletRequest request, Cart cart) {
        try {
            String itemIdStr = request.getParameter("itemId");

            if (itemIdStr != null) {
                int itemId = Integer.parseInt(itemIdStr);
                cart.removeItem(itemId);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format while removing item. " + e.getMessage());
        }
    }
}
