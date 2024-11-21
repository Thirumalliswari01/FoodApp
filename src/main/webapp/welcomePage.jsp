<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Account</title>
    <style>
        :root {
            --primary-color: #32CD32;
            --secondary-color: #ADD8E6;
            --accent-color: #4B4B4B;
            --bg-color: #F0F4F4;
            --text-color: #FFFFFF;
            --p-text-color: #77DD77;           --link-border-color: #000000;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-image: url('images/background10.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            color: var(--text-color);
            display: flex;
            justify-content: center;
            align-items: flex-start;
            height: 100vh;
            margin: 0;
            position: relative;
        }

        .container {
            width: 400px;
            height: auto;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background-color: transparent;
            position: absolute;
            top: 10%;
            transform: translateY(0);
        }

        h2 {
            font-size: 24px;
            margin-bottom: 20px;
            padding-bottom: 10px;
            background: linear-gradient(90deg, #FF69B4, #00BFFF);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            border-bottom: 2px solid var(--primary-color);
            display: inline-block;
            color: #FFFFFF;
        }

        p {
            margin: 10px 0;
            font-size: 16px;
            text-align: center;
            color: var(--p-text-color);
        }

        a {
            font-size: 18px;
            margin: 0 15px;
            padding: 10px 25px;
            text-decoration: none;
            background-color: #FFFFFF;
            color: #FF6347;
            border: 2px solid #FF6347;
            transition: background-color 0.3s, color 0.3s, transform 0.3s;
            box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }

        a:hover {
            background-color: #FF6347;
            color: #FFFFFF;
            transform: translateY(-3px);
        }

        div {
            display: flex;
            margin-top: 20px;
        }

        @media screen and (max-width: 600px) {
            .container {
                width: 90%;
                padding: 30px;
            }

            a {
                padding: 5px 10px;
            }

            h2 {
                font-size: 22px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome to Foodie's Fantasy! <span>&#x1F37D;</span></h2>
        <p>Order your favorite food online. Fast, easy, and delicious! 🍕</p>
        <div>
            <a href="signUp.jsp" aria-label="Sign Up">Sign Up</a>
            <a href="login.jsp" aria-label="Login">Login</a>
        </div>
    </div>
</body>
</html>
