package com.tapfoods.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.daoimpl.RestaurantDAOImpl;
import com.tapfoods.model.Restaurant;

@WebServlet("/home") // Ensure the URL pattern is "/home"
public class Home extends HttpServlet {
    private RestaurantDAO restaurantDAO;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        restaurantDAO = new RestaurantDAOImpl();
        List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants(); // Get restaurant list

        // Set the restaurant list in the session for use in home.jsp
        HttpSession session = request.getSession();
        session.setAttribute("restaurantList", restaurantList);

        String restaurantId = request.getParameter("restaurantId");

        // Set restaurantId in session if available
        if (restaurantId != null) {
            session.setAttribute("restaurantId", Integer.parseInt(restaurantId));
        }

        // Redirect the request to home.jsp
        response.sendRedirect("home.jsp");
    }
}
