<%-- 
    Document   : login
    Created on : Oct 26, 2024, 10:34:18 AM
    Author     : datkh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: linear-gradient(120deg, #3498db, #8e44ad);
        }

        .login-container {
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.2);
            width: 400px;
        }

        .login-container h1 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
            font-size: 2em;
        }

        .error-message {
            color: #e74c3c;
            text-align: center;
            margin-bottom: 20px;
            font-size: 0.9em;
        }

        .form-group {
            margin-bottom: 20px;
            position: relative;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #7f8c8d;
            font-size: 0.9em;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #bdc3c7;
            border-radius: 5px;
            font-size: 1em;
            transition: all 0.3s ease;
        }

        .form-group input:focus {
            border-color: #3498db;
            outline: none;
            box-shadow: 0 0 5px rgba(52,152,219,0.3);
        }

        .login-btn {
            width: 100%;
            padding: 12px;
            background: #3498db;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 1em;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .login-btn:hover {
            background: #2980b9;
        }

        .additional-links {
            margin-top: 20px;
            text-align: center;
        }

        .additional-links a {
            color: #3498db;
            text-decoration: none;
            font-size: 0.9em;
            margin: 0 10px;
        }

        .additional-links a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h1>Welcome Back</h1>
        
        <h4 class="error-message">${requestScope.error}</h4>
        
        <form action="login" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" required/>
            </div>
            
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required/>
            </div>
            
            <button type="submit" class="login-btn">LOGIN</button>
        </form>
        
        <div class="additional-links">
            <a href="register">Register</a>
        </div>
    </div>
</body>
</html>