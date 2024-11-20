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

    	User user = (User) req.getSession().getAttribute("loggedInUser");
    	
    	if (user == null) {
    	    System.out.println("User is null in OrderHistoryServlet, redirecting to login page.");
    	    resp.sendRedirect("login.jsp");
    	    return;
    	}



        int userId = user.getUserId();
        

        OrderHistoryDAO orderhistoryobj = new OrderHistoryDAOImpl();
        List<OrderHistory> orderhistorylist = orderhistoryobj.fetchOrderOnUserid(userId);
        
        if (orderhistorylist == null || orderhistorylist.isEmpty()) {
            System.out.println("No order history found for userId: " + userId);
            req.setAttribute("orderHistoryMessage", "No order history found.");
        } else {
            System.out.println("Order history list found for userId: " + userId);
        }
        req.getSession().setAttribute("orderhistorylist", orderhistorylist);

        
        req.getRequestDispatcher("OrderHistory.jsp").forward(req, resp);
    }
}
