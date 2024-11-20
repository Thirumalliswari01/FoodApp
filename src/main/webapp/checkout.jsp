<%@ page import="com.tapfoods.model.Cart" %>
<%@ page import="com.tapfoods.model.CartItem" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <style type="text/css">
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
        .checkout-container {
            max-width: 900px;
            margin: 20px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1{
            color: #333;
            text-align: left;
        }
        h2 {
            text-align: left;
            font-size: 28px;
            margin-bottom: 20px;
            color: #333;
        }
        .cart-summary {
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 2px solid #f0f0f0;
        }
        .cart-item {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid #f0f0f0;
        }
        .cart-item:last-child {
            border-bottom: none;
        }
        .cart-item p {
            margin: 5px 0;
            color: #555;
        }
        .total-price {
            text-align: right;
            font-size: 1.5rem;
            font-weight: 600;
            color: #333;
        }
        .checkout-form {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            margin: 0 auto;
            width: 80%;
            max-width: 600px;
        }
        .checkout-form label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 500;
        }
        .checkout-form input, .checkout-form select {
            width: 100%;
            max-width: 400px;
            padding: 12px;
            margin-bottom: 15px;
            border-radius: 8px;
            border: 1px solid #ddd;
            font-size: 1rem;
            box-sizing: border-box;
        }
        .checkout-form input[type="submit"] {
            background-color:  #FF5722;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 1.1rem;
            font-weight: 600;
            transition: background-color 0.3s ease;
        }
        .checkout-form input[type="submit"]:hover {
            background-color: #218838;
        }
        .cart-empty {
            text-align: center;
            padding: 40px;
        }
        .cart-empty h1 {
            font-size: 2rem;
            color: #ff5722;
        }
        .cart-empty p {
            font-size: 1.2rem;
            color: #555;
        }
        .cart-summary h2, .checkout-form h2 {
            color: #ff5722;
            text-transform: uppercase;
            font-weight: 600;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="checkout-container">
        <h1>Check your order details below</h1>
        <% 
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart != null && cart.getItems() != null && !cart.getItems().isEmpty()) {
                double totalAmount = 0.0;
                Integer restaurantId = (Integer) session.getAttribute("restaurantId");
        %>
            <div class="cart-summary">
                <table style="width: 100%; border-collapse: collapse; margin-bottom: 20px;">
                    <thead>
                        <tr style="background-color: #f2f2f2; text-align: left;">
                            <th style="padding: 10px;">Item Image</th>
                            <th style="padding: 10px;">Item Name</th>
                            <th style="padding: 10px;"> ₹ Price </th>
                            <th style="padding: 10px;">Quantity</th>
                            <th style="padding: 10px;">₹ SubTotal</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                                CartItem cartItem = entry.getValue();
                                double itemTotal = cartItem.getPrice() * cartItem.getQuantity();
                                totalAmount += itemTotal;
                        %>
                        <tr>
                            <td style="padding: 10px;">
                                <img src="<%= (cartItem.getImagePath() != null && !cartItem.getImagePath().isEmpty()) ? 
                                                (cartItem.getImagePath().startsWith("http") ? cartItem.getImagePath() : request.getContextPath() + cartItem.getImagePath()) 
                                                : request.getContextPath() + "/images/default-item.jpg" %>" 
                                    alt="<%= cartItem.getName() != null ? cartItem.getName() : "Cart Item Image" %>" 
                                    style="width: 150px; height: 150px; object-fit: cover;">
                            </td>
                            <td style="padding: 10px;"><%= cartItem.getName() %></td>
                            <td style="padding: 10px;">₹<%= String.format("%.2f", cartItem.getPrice()) %></td>
                            <td style="padding: 10px;"><%= cartItem.getQuantity() %></td>
                            <td style="padding: 10px;">₹<%= String.format("%.2f", itemTotal) %></td>
                        </tr>
                        <% 
                            } 
                        %>
                    </tbody>
                </table>
                <div class="total-price" style="font-weight: bold; font-size: 1.2em; margin-top: 10px;">
                    <h3>Item SubTotal:</h3><p> ₹<%= String.format("%.2f", totalAmount) %></p>
                </div>
            </div>
            <h2>Enter Your Details</h2>
            <div class="checkout-form">
                <form action="orderPlaced" method="post">
                    <input type="hidden" name="userId" value="<%= session.getAttribute("userId") != null ? session.getAttribute("userId").toString() : "" %>">
                    <input type="hidden" name="restaurantId" value="<%= session.getAttribute("restaurantId") != null ? session.getAttribute("restaurantId") : "" %>">
                    <input type="text" id="name" name="name" required placeholder="Enter your full name">
                    <input type="text" id="address" name="address" required placeholder="Enter your delivery address">
                    <input type="tel" id="phone" name="phone" pattern="[0-9]{10}" required placeholder="Enter your phone number">
                    <select id="paymentMode" name="paymentMode" required>
                        <option value="Cash on Delivery">Cash on Delivery</option>
                        <option value="Credit/Debit Card">Credit/Debit Card</option>
                        <option value="UPI">UPI</option>
                    </select>                    
                    <input type="text" name="totalAmount" value="<%= String.format("%.2f", totalAmount) %>" required readonly />
                    <input type="submit" value="Confirm Order">
                </form>
            </div>
        <% 
            } else { 
        %>
            <div class="cart-empty">
                <h1>Your Cart is Empty</h1>
                <p>Please add items to your cart before proceeding to checkout.</p>
            </div>
        <% 
            } 
        %>
    </div>
</body>
</html>
