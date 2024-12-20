<%@page import="com.tapfoods.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile Information</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<style type="text/css">
    body {
        font-family: "Poppins", sans-serif;
        margin: 0;
        padding: 0;
    }
    .profile {
        width: 40%;
        margin: 50px auto;
        padding: 30px;
        background-color: #FFDAB9; /* Light orange color */
        border-radius: 20px;
        box-shadow: 0 12px 30px rgba(0, 0, 0, 0.1);
        color: black;
        text-align: center;
    }
    h2 {
        font-size: 2rem;
        font-weight: 600;
        margin-bottom: 30px;
    }
    table {
        width: 100%;
        margin-top: 20px;
        text-align: left;
    }
    table td {
        padding: 10px;
        font-size: 1.1rem;
        color: #333;
    }
    td {
        padding-left: 20px;
    }
    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 12px;
        margin: 10px 0;
        border-radius: 8px;
        border: 1px solid #ddd;
        background-color: #ffffff;
        font-size: 1rem;
        color: #333;
        transition: background-color 0.3s, border-color 0.3s;
    }
    input[type="text"]:focus, input[type="password"]:focus {
        outline: none;
        background-color: #fdf1f3;
        border-color: #f2a1d2;
    }
    input[type="text"]:hover, input[type="password"]:hover {
        background-color: #fff9fb;
        border-color: #f2a1d2;
    }
    button {
        padding: 12px 25px;
        background-color: #f2a1d2;
        color: white;
        border: none;
        border-radius: 6px;
        font-size: 1rem;
        cursor: pointer;
        transition: background-color 0.3s, transform 0.3s;
        margin-top: 20px;
    }
    button:hover {
        background-color: #d88b8f;
        transform: translateY(-3px);
    }
    button:active {
        transform: translateY(1px);
    }
</style>


</head>
<body>
    <% User user = (User) session.getAttribute("loggedInUser"); %>
    <div class="profile">
        <h2>Profile Information</h2>
        <form method="post">
            <table>
                <tr>
                    <td>UserName:</td>
                    <td><input type="text" value="<%=user.getUsername() %>" readonly></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" value="<%=user.getEmail()%>" readonly></td>
                </tr>
                <tr>
                    <td>Phone Number:</td>
                    <td><input type="text" value="<%=user.getPhonenumber()%>" readonly></td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td><input type="text" value="<%=user.getAddress()%>" readonly></td>
                </tr>
            </table>
            <button type="submit">Update Profile</button>
        </form>
    </div>
</body>
</html>
