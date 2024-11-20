package com.tapfoods.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.daoimpl.RestaurantDAOImpl;
import com.tapfoods.model.Restaurant;

@WebServlet("/restaurantList")  
public class RestaurantListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
        List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();

        req.setAttribute("RestaurantList", restaurantList);
        req.getSession().setAttribute("RestaurantList", restaurantList);
        req.setAttribute("username", req.getParameter("username"));
        req.setAttribute("password", req.getParameter("password"));

        System.out.println("Control in RestaurantListServlet");
        System.out.println("Username: " + req.getParameter("username"));
        System.out.println("Password: " + req.getParameter("password"));

        req.getRequestDispatcher("LoginServlet").include(req, resp);
    }
}
