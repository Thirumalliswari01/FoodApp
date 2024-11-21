package com.tapfoods.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tapfoods.dao.OrderHistoryDAO;
import com.tapfoods.daoimpl.OrderHistoryDAOImpl;
import com.tapfoods.model.OrderHistory;
import com.tapfoods.model.User;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Get the logged-in user from session
        User user = (User) req.getSession().getAttribute("loggedInUser");
        
        if (user == null) {
            System.out.println("User is null in OrderHistoryServlet, redirecting to login page.");
            resp.sendRedirect("login.jsp");  // Redirect to login if no user found
            return;
        }

        int userId = user.getUserId();

        try {
            // Fetch order history for the user
            OrderHistoryDAO orderhistoryobj = new OrderHistoryDAOImpl();
            List<OrderHistory> orderhistorylist = orderhistoryobj.fetchOrderOnUserid(userId);
            
            if (orderhistorylist == null || orderhistorylist.isEmpty()) {
                System.out.println("No order history found for userId: " + userId);
                req.setAttribute("orderHistoryMessage", "No order history found.");
            } else {
                System.out.println("Order history list found for userId: " + userId);
                req.setAttribute("orderhistorylist", orderhistorylist);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("orderHistoryMessage", "An error occurred while fetching order history.");
        }

        // Forward to OrderHistory.jsp to display the results
        req.getRequestDispatcher("OrderHistory.jsp").forward(req, resp);
    }
}
