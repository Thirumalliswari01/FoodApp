<!-- error.jsp -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 50px;
        }
        .error-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            display: inline-block;
        }
        h1 {
            color: #ff0000;
        }
        p {
            color: #333;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Oops! Something went wrong.</h1>
        <p>${errorMessage}</p> 
        <p>We encountered an issue while processing your request. Please try again later or contact support if the problem persists.</p>
        <a href="index.jsp">Return to Home</a>
    </div>
</body>
</html>
