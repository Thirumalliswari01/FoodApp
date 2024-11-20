package com.tapfoods.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.daoimpl.MenuDAOImpl;
import com.tapfoods.model.Menu;
@WebServlet("/menuregister")
public class RegisterMenuItems extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Setting up the response content type
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        try {
            // Retrieving form data from the request
            String restaurantId1 = req.getParameter("restaurantid");
            String itemName = req.getParameter("Itemname");
            String description = req.getParameter("description");
            String price1 = req.getParameter("price");
            String isActive1 = req.getParameter("isActive");
            String imagePath = req.getParameter("imagepath");

            // Validate inputs
            if (restaurantId1 == null || itemName == null || description == null || price1 == null || isActive1 == null || imagePath == null) {
                pw.println("All fields must be provided.");
                return;
            }

            int restaurantId = Integer.parseInt(restaurantId1);  // Convert restaurantId to integer
            int price = Integer.parseInt(price1);  // Convert price to integer

            boolean isActive = isActive1.equals("Yes");

            Menu menu = new Menu(restaurantId, itemName, description, price, isActive, imagePath);

            MenuDAO menuDAO = new MenuDAOImpl();
            int res = menuDAO.addMenu(menu);

            // Response message
            if (res != 0) {
                pw.println("Successfully added menu item to the database.");
            } else {
                pw.println("Cannot add menu item to the database.");
            }
        } catch (NumberFormatException e) {
            pw.println("Invalid input: Please ensure numeric fields (restaurantId and price) are entered correctly.");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("An error occurred while processing your request. Please try again later.");
        } finally {
            pw.close();
        }
    }
}
