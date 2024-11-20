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
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Control reached the password update servlet");


        String newPassword = req.getParameter("newpassword");
        String confirmPassword = req.getParameter("confirmpassword");


        User user = (User) req.getSession().getAttribute("loggedInUser");


        if (user == null) {
            resp.sendRedirect("login.jsp");  
            return;
        }


        if (newPassword.equals(confirmPassword)) {
            try {

            	UserDAO userDAO = new UserDAOImpl();
                boolean isUpdated = userDAO.updatePassword(user.getUserId(), newPassword); 


                if (isUpdated) {
                    resp.sendRedirect("changedpasswordsuccess.html");
                } else {
                    resp.sendRedirect("errorpage.html"); 
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendRedirect("errorpage.html");  
            }
        } else {

        	req.setAttribute("error", "Password and confirm password do not match");
            req.getRequestDispatcher("ChangePassword.html").forward(req, resp);  
        }
    }
}
