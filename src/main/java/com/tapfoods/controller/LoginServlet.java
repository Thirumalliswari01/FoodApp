package com.tapfoods.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.UserDAO;
import com.tapfoods.daoimpl.UserDAOImpl;
import com.tapfoods.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUser(email);


        if (user != null && password.equals(user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedInUser", user); 
            
            System.out.println("User added to session: " + user);
            resp.sendRedirect("home"); 
        } else { 

        	req.setAttribute("errorMessage", "Invalid email or password");
            req.getRequestDispatcher("login.jsp").forward(req, resp); // Redirect back to login page
        }
    }
}
