<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(135deg, #f6f8fd 0%, #f0f3fa 100%);
            padding: 20px;
        }

        .container {
            display: flex;
            max-width: 1200px;
            width: 95%;
            background-color: white;
            border-radius: 25px;
            overflow: hidden;
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }

        .container:hover {
            transform: translateY(-5px);
        }

        .left-side {
            flex: 1.2;
            position: relative;
            overflow: hidden;
        }

        .left-side img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transform: scale(1.1);
            transition: transform 0.5s ease;
        }

        .left-side:hover img {
            transform: scale(1);
        }

        .overlay {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: linear-gradient(135deg, rgba(0, 0, 0, 0.7) 0%, rgba(0, 0, 0, 0.5) 100%);
            padding: 60px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            color: white;
            text-align: center;
        }

        .overlay h1 {
            font-size: 3rem;
            font-weight: 700;
            margin-bottom: 1.5rem;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }

        .overlay p {
            font-size: 1.2rem;
            line-height: 1.6;
            opacity: 0.9;
        }

        .right-side {
            flex: 1;
            padding: 40px;
            background: white;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        .login-form {
            max-width: 400px;
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .title {
            color: #1a1a1a;
            margin-bottom: 1.5rem;
            font-size: 2.2rem;
            font-weight: 700;
            text-align: center;
        }

        .form-group {
            margin-bottom: 1.5rem;
            position: relative;
            width: 100%;
        }

        input {
            width: 100%;
            padding: 15px;
            border: 2px solid #e1e1e1;
            border-radius: 12px;
            font-size: 1rem;
            transition: all 0.3s ease;
            background: #f8f9fa;
        }

        input:focus {
            outline: none;
            border-color: #ff4b4b;
            background: white;
            box-shadow: 0 0 0 4px rgba(255, 75, 75, 0.1);
        }

        button {
            width: 100%;
            padding: 15px;
            background: linear-gradient(135deg, #ff4b4b 0%, #ff6b6b 100%);
            color: white;
            border: none;
            border-radius: 12px;
            font-size: 1.1rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 4px 15px rgba(255, 75, 75, 0.2);
        }

        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(255, 75, 75, 0.3);
        }

        .signin-link {
            text-align: center;
            margin-top: 1.5rem;
            color: #6c757d;
            font-size: 0.95rem;
        }

        .signin-link a {
            color: #ff4b4b;
            text-decoration: none;
            font-weight: 600;
            transition: color 0.3s ease;
        }

        .signin-link a:hover {
            color: #ff6b6b;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="left-side">
            <img src="https://i.pinimg.com/736x/2c/00/d7/2c00d7c98f6382ba603d56c5969d73ec.jpg" alt="Delicious Burger">
            <div class="overlay">
                <h1>Welcome Back!</h1>
                <p>Indulge your taste buds at Foodie Delights, where every flavor tells a delicious story.</p>
            </div>
        </div>
        <div class="right-side">
            <div class="login-form">
                <h2 class="title">Login In To Your Account</h2>
                <form action="login" method="post">
                    <div class="form-group">
                        <table>
                            <tr>
                                <td style="text-align: right; padding-right: 40px;">Email</td>
                                <td>
                                    <input type="email" name="email" placeholder="Email" required>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="form-group">
                        <table>
                            <tr>
                                <td style="text-align: right; padding-right: 10px;">Password</td>
                                <td>
                                    <input type="password" name="password" placeholder="password" required>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <button type="submit">Login</button>
                </form>
                <div class="signin-link">
                    <p>Don't have an account? <a href="signUp.jsp">Sign Up</a></p>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
