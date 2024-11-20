<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tapfoods.model.Menu" %>
<%@ page import="com.tapfoods.model.Restaurant" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link rel="stylesheet" href="css/menu.css">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        
        
        h1 {
            text-align: left;
            color: #333;
            margin-top: 50px;
            font-size: 40px;
            font-weight: 700;
        }
        h2 {
    font-family: 'Lucida Handwriting', cursive;
    font-size: 36px;
    color: #333333; 
    text-align: center;
    margin-bottom: 20px;
    padding: 10px;
    border-radius: 10px;
    text-transform: uppercase;
    letter-spacing: 2px;
}

h2:hover {
    color: black;
    cursor: pointer;
}

        .restaurant {
            background-color: #fff;
            margin: 20px auto;
            padding: 20px;
            max-width: 700px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: left;
            font-family: Arial, sans-serif;
        }
        .restaurant h3 {
            font-size: 28px;
            margin-bottom: 10px;
            color: #333;
            
        }
        .restaurant p {
            font-size: 16px; 
    color: #333; 
    margin: 10px 0; 
        }
        .restaurant p + p {
    border-top: 1px solid #ddd; 
    padding-top: 10px; 
}
        .menu-container {
            display: grid;  
    grid-template-columns: repeat(1, 1fr); 
    gap: 20px; 
    justify-items: center; 
    align-items: center; 
    margin: 20px; 
        }
       .menu-item {
     display: flex;  
    justify-content: space-between;  
    align-items: center; 
    gap: 20px;  
    padding: 20px;
    max-width: 700px;  
    border: 2px solid #ccc; 
    border-radius: 8px;  
    background-color: #fff;  
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);  
    margin: 10px 0; 
}

.menu-item:hover {
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);  
}


        .menu-item img {
            width: 250px; 
    height: 250px;  
    object-fit: cover;  
    border-radius: 8px;    
        }
        .menu-item-content {
            flex: 1;  
    text-align: left;  
        }
        .menu-item h3 {
            font-size: 20px;
            margin-bottom: 10px;
            color: #333;
        }
        .menu-item-content h3 {
    font-size: 24px;
    font-weight: bold;
    margin: 0;
}
        .menu-item p {
            font-size: 14px;
            color: #777;
            line-height: 1.5;
            
        }
        .menu-item .price {
    font-size: 18px;
    font-weight: bold;
    color: #e74c3c;
    margin-top: 10px;
}
        .quantity-input {
            width: 60px;
            padding: 5px;
            margin-right: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }
        .add-to-cart-btn {
             background-color: tomato; 
    color: white;
    padding: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    width: 100%;
    transition: background-color 0.3s ease;
    margin-top: 10px;
        }
        .add-to-cart-btn:hover {
            background-color: darkorange; 
        }
        .no-items {
            text-align: center;
            font-size: 1.5em;
            color: #555;
            width: 100%;
            margin-top: 30px;
        }
        @media (max-width: 768px) {
            .menu-item {
                width: 100%;
            }
        }
       

.restaurant-heading {
    text-align: left;
    background-color: transparent;
    color: tomato;
    padding: 0;
    margin: 20px 0;
    border: none;
    box-shadow: none;
}

.restaurant-heading h3 {
    font-size: 32px;
    font-weight: bold;
    margin: 0;
    letter-spacing: 1px;
    text-transform: uppercase;
    text-align: left;
    padding-left: 450px;
}

.price-label {
    color: lite black;
}

.navbar {
           display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: white;
    padding: 10px 20px; /* Increased padding for a larger bar */
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    height: 80px; 
        }

        .navbar img {
            height: 100px;
            width: auto;
        }

        .navbar ul {
            list-style: none;
            margin: 0;
            padding: 0;
            display: flex;
            gap: 20px;
        }

        .navbar ul li {
            display: inline;
        }

        .navbar ul li a {
            text-decoration: none;
            color: black;;
            font-size: 16px;
            font-weight: bold;
            padding: 10px 15px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .navbar ul li a:hover {
            background-color: black;
        }
        .search-bar {
    display: flex;
    align-items: right-end;
    gap: 10px; 
 
}

.search-label {
    font-size: 16px;
    color: #333;
    display: flex;
    align-items: center;
    margin-left: 10px; 
}

.search-bar input[type="text"] {
    padding: 10px 30px;
    border: 1px solid #c02d55;
    border-radius: 20px;
    font-size: 14px;
    outline: none;
    width: 200px;
    transition: box-shadow 0.3s ease;
    float: right;
}

.search-bar input[type="text"]:focus {
    box-shadow: 0 0 5px #de3163;
}

.search-bar input[type="text"]::placeholder {
    color: #aaa;
    font-style: italic;
}

h4 {
    font-size: 32px;
    font-weight: normal;
    font-family: var(--text-color);
    margin: 0;
    letter-spacing: 1px;
    text-align: left;
    padding-left: 400px;
}

h5 {
    color: #2f2f2f;
}

        
    </style>
</head>
<body>
	 <div class="navbar">
    <a href="home.jsp">
        <img src="<%= request.getContextPath() %>/images/logo3.png" alt="Logo">
    </a>
    
    <ul>
    <form action="search.jsp" method="get" class="search-bar">
    <span class="search-label">&#x1F50D; Search</span> <!-- Magnifying Glass symbol -->
    <input type="text" name="query" placeholder="Search for dishes, restaurants..." required>
</form>
       <li><a href="home.jsp">&#x2302; Home</a></li> <!-- House symbol -->
    <li><a href="signUp.jsp">&#x270E; SignUp</a></li> <!-- Pencil symbol -->
    <li><a href="login.jsp">&#x1F511; Login</a></li> <!-- Key symbol -->
    <li><a href="cart.jsp">&#x1F6D2; Cart</a></li> <!-- Shopping Cart symbol -->
    </ul>
    
</div>



  <div class="restaurant-list">
    <%

    String restaurantIdParam = request.getParameter("restaurantId");
    
    if (restaurantIdParam != null) {
        int restaurantId = Integer.parseInt(restaurantIdParam);
        

        List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantList");

        if (restaurantList != null && !restaurantList.isEmpty()) {

        	Restaurant selectedRestaurant = null;
            for (Restaurant restaurant : restaurantList) {
                if (restaurant.getRestaurantId() == restaurantId) {
                    selectedRestaurant = restaurant;
                    break;
                }
            }

            if (selectedRestaurant != null) {
    %>
    		<div class ="restaurant-heading">
                <h3><%= selectedRestaurant.getRestaurantName() %></h3>
             </div>
            <div class="restaurant">
                <p>Cuisine : <%= selectedRestaurant.getCuisineType() %></p> 
                <p>Address:<%= selectedRestaurant.getAddress()%>
                <p>Delivery Time: <%= selectedRestaurant.getDeliveryTime() %> mins</p>
                <p>Rating: <%= selectedRestaurant.getRating() %> ⭐   ₹400 for two</p>
            </div>
    <%
            } else {
    %>
                <p>Restaurant not found.</p>
    <%
            }
        }
    } else {
    %>
        <p>No restaurant selected.</p>
    <%
    }
    %>



        <h2>✦ Menu ✦</h2>
       <h4>Top-Rated Dishes </h4>
        
        <div class="menu-container">
            <%

            List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
                
                if (menuList == null) {
                    menuList = (List<Menu>) session.getAttribute("menuList");
                }

                if (menuList != null && !menuList.isEmpty()) {

                	for (Menu menuItem : menuList) {
            %>
            <div class="menu-item">
                <%-- Image fallback logic --%>
                <img src="<%= (menuItem.getImagePath() != null && !menuItem.getImagePath().isEmpty()) ? 
                    (menuItem.getImagePath().startsWith("http") ? menuItem.getImagePath() : request.getContextPath() + menuItem.getImagePath()) 
                    : request.getContextPath() + "/images/default.jpg" %>" 
                    alt="<%= menuItem.getItemName() != null ? menuItem.getItemName() : "Menu Item Image" %>">

                <div class="menu-item-content">
                    <h3><%= menuItem.getItemName() %></h3>
                    <p><%= menuItem.getDescription() %></p>
                   <p class="price">
    <span class="price-label">Price:</span> ₹<%= menuItem.getPrice() %>
</p>

                    
                    <!-- Add to Cart Form -->
                    <form action="cart" method="post">
                        <input type="hidden" name="itemId" value="<%= menuItem.getMenuId() %>">
                        <input type="hidden" name="restaurantId" value="<%= menuItem.getRestaurantId() %>">
                        
                        <label>Quantity: </label>
                        <input type="number" name="quantity" value="1" min="1" class="quantity-input">
                        
                        <input type="hidden" name="action" value="add">
                        <input type="submit" value="Add to Cart" class="add-to-cart-btn">
                    </form>
                </div>
            </div>
            
            
            <h5>---------------------------------------------------------------------------------------------------------------------------------------------------</h5>
            <%
                    }
                } else {
            %>
            <p class="no-items">No menu items available.</p>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
