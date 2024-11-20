package com.tapfoods.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signUp")
public class RegisterServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/tapfoods";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Thiru@07";
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        String sql = "INSERT INTO user(username, email, phoneNumber, password, address) VALUES (?, ?, ?, ?, ?)";

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phonenumber = req.getParameter("phonenumber");
        String password = req.getParameter("password");
        String cpassword = req.getParameter("cpassword");
        String address = req.getParameter("address");

        if (password.equals(cpassword)) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, email);
                pstmt.setString(3, phonenumber);
                pstmt.setString(4, password);
                pstmt.setString(5, address);

                int result = pstmt.executeUpdate();
                
                if (result > 0) {
                    resp.sendRedirect("successRegister.jsp");
                } else {
                    resp.sendRedirect("failureRegister.jsp");
                }
                
                pstmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "An error occurred while processing your registration. Please try again later.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);  
            }
        } else {
            resp.sendRedirect("invalidpwd.jsp");
        }
    }
}
