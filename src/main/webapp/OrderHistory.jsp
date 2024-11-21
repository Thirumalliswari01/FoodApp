<%@page import="com.tapfoods.model.OrderHistory"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order History</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
<style>
    body {
        font-family: 'Poppins', sans-serif;
        background-color: #f8f8f8;
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
    }
    h2 {
        text-align: center;
        margin-bottom: 20px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    td, th {
        padding: 10px;
        border-bottom: 1px solid #ddd;
        text-align: center;
        line-height: 1.5rem;
    }
    input[type="submit"] {
        background-color: #FF5722;
        color: #fff;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        margin-top: 5px;
        cursor: pointer;
        font-size: 1rem;
        font-weight: 600;
    }
    input[type="submit"]:hover {
        background-color: #FF7043;
    }
</style>
</head>
<body>
    <% 
        // Retrieve the order history list from the session
        List<OrderHistory> list = (List<OrderHistory>) request.getAttribute("orderhistorylist");

        // Check if no orders were found
        String message = (String) request.getAttribute("orderHistoryMessage");
        if (message != null) {
            out.println("<h2>" + message + "</h2>");
        }

        if (list != null && !list.isEmpty()) {
    %>
    <h2>Check Your Orders Below</h2>
    <table>
        <tr>
            <th>S.No</th>
            <th>Order Date</th>
            <th>Total Amount</th>
            <th>Status</th>
            <th>Check Items</th>
        </tr>
        <% 
            int increment = 1;
            for (OrderHistory e : list) {
        %>
        <tr>
            <td><%= increment++ %></td>
            <td><%= e.getOrderDate() %></td>
            <td>₹<%= e.gettotalAmount() %></td>
            <td><%= e.getStatus() %></td>
            <td>
                <form action="orderItemList" method="post">

                    <input type="hidden" name="orderid" value="<%= e.getOrderId() %>">
                    <input type="submit" value="Submit">
                </form>
            </td>
        </tr>
        <% 
            }
        } else { 
        %>
        <h2>No Order History Available</h2>
    <% } %>
</body>
</html>
