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
            // Retrieve the order ID from the request parameter
            int orderId = Integer.parseInt(req.getParameter("orderid")); 
            System.out.println("Order ID received: " + orderId); 

            // Instantiate the DAO to fetch order items
            OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
            List<OrderItem> orderItemList = orderItemDAO.fetchOrdersItems(orderId);
            System.out.println("Fetched Order Items: " + orderItemList);

            // Check if order items are found and set them as request attributes
            if (orderItemList != null && !orderItemList.isEmpty()) {
                req.setAttribute("orderitemlist", orderItemList);
            } else {
                req.setAttribute("message", "No items found for this order.");
            }

            // Forward the request to the JSP page (use forward instead of include for redirection)
            req.getRequestDispatcher("/OrderhistoryTime.jsp").forward(req, resp); // Forward instead of include

        } catch (NumberFormatException e) {
            // Handle invalid order ID format
            System.err.println("Invalid order ID format: " + e.getMessage());
            req.setAttribute("errorMessage", "Invalid order ID format."); // Set error message for the JSP
            req.getRequestDispatcher("/errorPage.jsp").forward(req, resp); // Forward to error page

        } catch (Exception e) {
            // Handle general errors
            System.err.println("Error retrieving order items: " + e.getMessage());
            req.setAttribute("errorMessage", "Error retrieving order items."); // Set error message for the JSP
            req.getRequestDispatcher("/errorPage.jsp").forward(req, resp); // Forward to error page
        }
    }
}
