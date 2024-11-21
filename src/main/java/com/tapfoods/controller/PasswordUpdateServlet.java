package com.tapfoods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tapfoods.dao.UserDAO;
import com.tapfoods.daoimpl.UserDAOImpl;
import com.tapfoods.model.User;

@WebServlet("/updatepassword")

public class PasswordUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Control reached the password update servlet");

        // Retrieve parameters from the form
        String newPassword = req.getParameter("newpassword");
        String confirmPassword = req.getParameter("confirmpassword");

        // Retrieve the logged-in user from the session
        User user = (User) req.getSession().getAttribute("loggedInUser");

        // Check if the user is logged in
        if (user == null) {
            resp.sendRedirect("login.jsp");  // Redirect to login page if the user is not logged in
            return;
        }

        // Check if the new password and confirmation password match
        if (newPassword.equals(confirmPassword)) {
            try {
                // Instantiate DAO and call update method
                UserDAO userDAO = new UserDAOImpl();
                boolean isUpdated = userDAO.updatePassword(user.getUserId(), newPassword);

                if (isUpdated) {
                    // Redirect to success page if password update is successful
                    resp.sendRedirect("changedpasswordsuccess.html");
                } else {
                    // If update failed, redirect to error page
                    req.setAttribute("error", "Password update failed. Please try again.");
                    req.getRequestDispatcher("ChangePassword.jsp").forward(req, resp);  // Use .jsp for displaying error messages
                }
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("error", "An error occurred while updating your password. Please try again later.");
                req.getRequestDispatcher("ChangePassword.jsp").forward(req, resp);  // Forward to the same page with error message
            }
        } else {
            // If passwords do not match, display an error message
            req.setAttribute("error", "Password and confirm password do not match.");
            req.getRequestDispatcher("ChangePassword.jsp").forward(req, resp);  // Forward to ChangePassword.jsp with error message
        }
    }
}
