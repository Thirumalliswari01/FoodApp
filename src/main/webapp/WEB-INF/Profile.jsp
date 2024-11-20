<%@page import="com.tapfoods.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <style type="text/css">
        body {
            font-family: "Poppins", sans-serif;
            margin: 0px 80px;
        }
        .profile-container {
            border: 3px solid black;
            margin: 0px auto;
            margin-top: 100px;
            background-color: rgb(216, 26, 42);
            color: white;
            border-radius: 15px;
            padding: 50px 100px;
            text-align: center;
        }
        h2 {
            margin-bottom: 40px;
        }
        table td, th {
            line-height: 3rem;
        }
        td {
            padding-left: 2rem;
        }
        input[type="text"],
        input[type="email"],
        input[type="tel"] {
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 1rem;
            width: 80%; /* Adjust the width to improve layout */
            background-color: white; /* Lighter background for readability */
            color: black; /* Change text color to black */
            outline: none; /* Remove outline on focus */
        }
        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="tel"]:focus {
            border-color: rgb(0, 123, 255); /* Change border color on focus */
        }
    </style>
</head>
<body>
    <% 
        User userProfile = (User) session.getAttribute("loggedInUser"); // Retrieve user from session
        if (userProfile != null) {
    %>
    <div class="profile-container">
        <h2>User Profile Details</h2>
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" value="<%= userProfile.getUsername() %>" readonly></td>
            </tr>
            <tr>
                <td>Email Address:</td>
                <td><input type="email" value="<%= userProfile.getEmail() %>" readonly></td>
            </tr>
            <tr>
                <td>Contact Number:</td>
                <td><input type="tel" value="<%= userProfile.getPhonenumber() %>" readonly></td>
            </tr>
            <tr>
                <td>Residential Address:</td>
                <td><input type="text" value="<%= userProfile.getAddress() %>" readonly></td>
            </tr>
        </table>
    </div>
    <% 
        } else { 
    %>
    <div class="profile-container">
        <h2>No user information available.</h2>
    </div>
    <% 
        }
    %>
</body>
</html>