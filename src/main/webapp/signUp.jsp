<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Here</title>
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary-color: #FF8C00;
            --accent-color: #FF6347;
            --text-color: #FFFFFF;
            --heading-color: #FF4500;
            --input-text-color: #FFFF00;
            --link-color: #FF1493;
        }

        body {
            font-family: Arial, sans-serif;
            color: var(--text-color);
            font-weight: bold;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #000;
        }

        .background-container {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-image: url('images/login.jpg');
            background-size: cover;
            background-position: center;
            filter: blur(8px);
            z-index: -1;
        }

        .signup-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            position: relative;
            width: 400px;
            height: auto;
        }

        h1 {
            color: var(--heading-color);
            font-weight: bold;
            margin-bottom: 20px;
            text-align: center;
        }

        table {
            width: 100%;
        }

        td {
            padding: 8px;
            text-align: left;
        }

        .emoji {
            position: absolute;
            left: 10px;
            top: 50%;
            transform: translateY(-50%);
            font-family: "Segoe UI Emoji", "Apple Color Emoji", sans-serif;
            font-size: 20px;
            color: var(--primary-color);
            pointer-events: none;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="tel"] {
            padding: 10px 10px 10px 35px;
            border: 2px solid var(--primary-color);
            border-radius: 5px;
            width: 250px;
            font-size: 16px;
            background-color: #ffffff;
            color: var(--input-text-color);
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="password"]:focus,
        input[type="tel"]:focus {
            border-color: var(--accent-color);
            outline: none;
            color: #FF6347;
        }

        input[type="submit"] {
            background-color: var(--primary-color);
            color: #ffffff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #e07b00;
            transform: scale(1.05);
        }

        .signup-link {
            margin-top: 15px;
            font-size: 14px;
        }

        .signup-link a {
            color: var(--link-color);
            text-decoration: none;
            font-weight: bold;
        }

        .signup-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="background-container"></div>
    <div class="signup-container">
        <h1>Register Here 🎉</h1>
        <form action="signUp" method="post">
            <table>
                <tr>
                    <td><span class="emoji"></span>Username</td>
                    <td><input type="text" name="username" required></td>
                </tr>
                <tr>
                    <td><span class="emoji"></span>Email</td>
                    <td><input type="email" name="email" required></td>
                </tr>
                <tr>
                    <td><span class="emoji"></span>Phone Number</td>
                    <td><input type="tel" name="phonenumber" required></td>
                </tr>
                <tr>
                    <td><span class="emoji"></span>Password</td>
                    <td><input type="password" name="password" required></td>
                </tr>
                <tr>
                    <td><span class="emoji"></span>Confirm Password</td>
                    <td><input type="password" name="cpassword" required></td>
                </tr>
                <tr>
                    <td><span class="emoji"></span>Address</td>
                    <td><input type="text" name="address" required></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="text-align: right;"><input type="submit" value="Sign Up ✅"></td>
                </tr>
            </table>
        </form>
        <div class="signup-link">
            Already have an account? <a href="login.jsp">Login</a>
        </div>
    </div>
</body>
</html>
