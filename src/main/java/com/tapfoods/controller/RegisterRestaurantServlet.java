package com.tapfoods.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tapfoods.dao.RestaurantDAO; // Update if needed
import com.tapfoods.daoimpl.RestaurantDAOImpl; // Update to match your implementation
import com.tapfoods.model.Restaurant; // Update to match your implementation

@WebServlet("/restaurantregister")
public class RegisterRestaurantServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String restaurantIdStr = req.getParameter("restaurantid");
        int restaurantId = Integer.parseInt(restaurantIdStr);
        String restaurantName = req.getParameter("restaurantname");
        String cuisineType = req.getParameter("cuisinetype");
        String deliveryTimeStr = req.getParameter("deliverytime");
        int deliveryTime = Integer.parseInt(deliveryTimeStr);
        String isActiveStr = req.getParameter("isActive");
        boolean isActive = isActiveStr.equals("Yes");
        String ratingStr = req.getParameter("rating");
        int rating = Integer.parseInt(ratingStr);
        String imagePath = req.getParameter("imagepath");
        String address = req.getParameter("address"); 
        int adminUserId = Integer.parseInt(req.getParameter("adminUserId")); 
        PrintWriter pw = resp.getWriter();

        Restaurant restaurant = new Restaurant(restaurantId, restaurantName, cuisineType, deliveryTime, address, adminUserId, rating, isActive, imagePath);
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl(); 
        int result = restaurantDAO.addRestaurant(restaurant); 

        if (result != 0) {
            pw.println("Registration of Restaurant is successful");
        } else {
            pw.println("Registration Failure");
        }
    }
}
