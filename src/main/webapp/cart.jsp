<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.tapfoods.model.CartItem" %>
<%@ page import="com.tapfoods.model.Cart" %>
<%@ page import="java.util.Map" %>

<html>
<head>
    <title>Your Cart</title>
    <link rel="stylesheet" href="css/cart.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        .cart-items {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 20px;
        }

        .cart-item {
            display: flex;
            flex-direction: column;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            width: 40%;
            background-color: #ffffff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); 
        }

        .cart-item-content {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .cart-item-image {
            flex: 0 0 auto;
        }

        .cart-item-details {
            flex: 1;
            text-align: left;
        }

        .cart-item img {
            width: 300px;
            height: 250px;
            object-fit: cover;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .update-btn, .remove-btn {
            margin-top: 10px;
            padding: 8px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .update-btn {
            background-color: #28a745;
            color: white;
        }

        .remove-btn {
            background-color: #dc3545;
            color: white;
        }

        .cart-summary {
            font-weight: bold;
            font-size: 1.2em;
            color: #555;
            margin-top: 10px;
            text-align: center;
        }

        .btn {
            padding: 10px 20px;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            margin-top: 15px;
        }

        .add-more-items-btn {
            background-color: #28a745;
        }

        .proceed-to-checkout-btn {
            background-color: #007bff;
            border: none;
            cursor: pointer;
        }

        .cart-empty {
            font-size: 1.2em;
            color: #777;
            text-align: center;
        }
        .shopping-cart-heading {
            text-align: center;
            font-size: 2.5em;
            color: #dc3545;
            font-weight: bold;
            margin-bottom: 20px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
            padding-bottom: 10px;
            font-family: 'Arial', sans-serif;
            letter-spacing: 1px;
        }
        .add-more-items-btn {
            background-color: #FF5722;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            margin-top: 15px;
            transition: background-color 0.3s ease;
        }

        .add-more-items-btn:hover {
            background-color: #B7420E;
        }
        .proceed-to-checkout-btn {
            background-color:  #FF5722;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
            text-align: center;
        }

        .form-center {
            text-align: center;
            margin-top: 1px;
        }
        input[type="number"] {
            width: 100px;
            height: 30px;
            font-size: 16px;
            padding: 10px ;
            border: 1px solid #ccc;
            border-radius: 15px;
            text-align: center;
            margin: 5px 0;
        }
    </style>
</head>
<body>
    <h1 class="shopping-cart-heading">Your Shopping Cart</h1>

    <div class="cart-items">
        <%
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart != null && !cart.getItems().isEmpty()) {
                double totalCartPrice = 0.0;
                for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                    CartItem item = entry.getValue();
                    if (item != null) {
                        double totalPrice = item.getQuantity() * item.getPrice();
                        totalCartPrice += totalPrice;
        %>
            <div class="cart-item">
                <div class="cart-item-content">
                    <div class="cart-item-image">
                        <img src="<%= (item.getImagePath() != null && !item.getImagePath().isEmpty()) ? 
                        (item.getImagePath().startsWith("http") ? item.getImagePath() : request.getContextPath() + item.getImagePath()) 
                        : request.getContextPath() + "/images/default-item.jpg" %>" 
                        alt="<%= item.getName() != null ? item.getName() : "Cart Item Image" %>">
                    </div>

                    <div class="cart-item-details">
                        <p><strong><%= item.getName() != null ? item.getName() : "Unnamed Item" %></strong></p>
                        <p>Price: ₹<%= String.format("%.2f", item.getPrice()) %></p>
                        
                        <form action="cart" method="post">

                            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                            <label>Quantity: 
                                <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1">
                            </label><br>
                            <p>SubTotal: ₹<%= String.format("%.2f", totalPrice) %></p>
                            <input type="submit" name="action" value="update" class="update-btn">
                            <input type="submit" name="action" value="remove" class="remove-btn"><br>
                        
                            <a href="menu?restaurantId=<%= session.getAttribute("restaurantId") %>" class="btn add-more-items-btn">Add More Items</a>
                        </form>
                    </div>
                </div>
            </div>
        <%
                    }
                }
        %>
            <div class="cart-summary">
                <h3>Total Cart Value: ₹<%= String.format("%.2f", totalCartPrice) %></h3>
            </div>
        <%
            } else {
        %>
            <p class="cart-empty">Your cart is empty. Please add items to your cart.</p>
        <%
            }
        %>
    </div>

    <%
        Object restaurantId = session.getAttribute("restaurantId");
        if (restaurantId != null) {
    %>
    <%
        } else {
    %>
        <p class="cart-empty">Unable to load restaurant. Please start a new session.</p>
    <%
        }
    %>

    <%
        if (cart != null && !cart.getItems().isEmpty()) {
    %>
        <form action="checkout.jsp" method="post" class="form-center">
    <input type="submit" value="Proceed to Checkout" class="btn proceed-to-checkout-btn">
</form>

    <%
        }
    %>
</body>
</html>
