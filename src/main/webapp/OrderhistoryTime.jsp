<%@page import="com.tapfoods.dao.MenuDAO"%>
<%@page import="com.tapfoods.daoimpl.MenuDAOImpl"%>
<%@page import="com.tapfoods.model.Menu"%>
<%@page import="com.tapfoods.model.OrderItem"%>
<%@page import="java.util.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Items</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <style type="text/css">
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f8f8f8;
            max-width: 1200px;
            margin: 0 auto;
        }
        img {
            width: 150px;  
            height: auto;  
            border-radius: 5px;
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
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<h2>Your Order Items</h2>

<% 
    List<OrderItem> orderItemList = (List<OrderItem>) request.getAttribute("orderitemlist");
    int serialNumber = 1;
    double totalAmount = 0; 
%>
<table>
    <tr>
        <th>S.No</th>
        <th>Item Image</th>
        <th>Item Name</th>
        <th>Quantity</th>
        <th>Sub Total</th>
    </tr>
    
    <% 
    if (orderItemList != null && !orderItemList.isEmpty()) { 
        for (OrderItem orderItem : orderItemList) { 
            MenuDAO menuDAO = new MenuDAOImpl(); 
            Menu menuItem = menuDAO.getMenuItemById(orderItem.getMenuId());
            String imagePath = (menuItem != null && menuItem.getImagePath() != null && !menuItem.getImagePath().isEmpty()) 
                               ? menuItem.getImagePath() 
                               : "https://via.placeholder.com/150";
    %>
    
    <tr>
        <td><%= serialNumber++ %></td>
        <td>
            <img alt="<%= menuItem != null ? menuItem.getItemName() : "Item" %>" src="<%= imagePath %>">
        </td>
        <td><%= menuItem != null ? menuItem.getItemName() : "Unavailable" %></td>
        <td><%= orderItem.getQuantity() %></td>
        <td><%= String.format("%.2f", orderItem.getSubTotal()) %></td>
    </tr>
    <% 
            totalAmount += orderItem.getSubTotal(); 
        } 
    } else { 
    %>
        <tr>
            <td colspan="5">No items found in the order.</td>
        </tr>
    <% } %>
    
    <tr>
        <td colspan="4"><strong>Total</strong></td>
        <td><strong><%= String.format("%.2f", totalAmount) %></strong></td>
    </tr>
</table>

</body>
</html>
