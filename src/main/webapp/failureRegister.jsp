<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Failed</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8d7da; 
            color: #721c24; 
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .message-container {
            text-align: center;
            background-color: #ffffff; 
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 24px;
            color: #c82333; 
        }

        p {
            font-size: 16px;
        }

        a {
            text-decoration: none;
            color: #0056b3; 
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="message-container">
        <h1>Registration Failed! ❌</h1>
        <p>We are sorry, but your registration could not be completed. Please try again.</p>
        <p>You can return to the <a href="register.jsp">registration page</a> and fill out the form again.</p>
    </div>
</body>
</html>
