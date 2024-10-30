<%-- 
    Document   : register
    Created on : Oct 26, 2024, 11:26:42 AM
    Author     : datkh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Register</title>
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

       .register-container {
           background: white;
           padding: 40px;
           border-radius: 10px;
           box-shadow: 0 15px 35px rgba(0,0,0,0.2);
           width: 400px;
       }

       .register-container h1 {
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

       .register-btn {
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

       .register-btn:hover {
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
       }

       .additional-links a:hover {
           text-decoration: underline;
       }
   </style>
</head>
<body>
   <div class="register-container">
       <h1>Create Account</h1>
       
       <form action="register" method="post">
           <h4 class="error-message">${requestScope.er}</h4>
           <h4 class="error-message">${requestScope.err}</h4>
           
           <div class="form-group">
               <label for="username">Username</label>
               <input type="text" id="username" name="username" placeholder="Enter your username" required>
           </div>
           
           <div class="form-group">
               <label for="password">Password</label>
               <input type="password" id="password" name="password" placeholder="Enter your password" required>
           </div>
           
           <div class="form-group">
               <label for="repass">Re-Enter Password:</label>
               <input type="password" id="repass" name="repass" placeholder="Re-enter your password" required>
           </div>
           
           <div class="form-group">
               <label for="fullname">Full Name</label>
               <input type="text" id="fullname" name="fullname" placeholder="Enter your full name" required>
           </div>
           
           <div class="form-group">
               <label for="email">Email</label>
               <input type="email" id="email" name="email" placeholder="Enter your email" required>
           </div>
           
           <div class="form-group">
               <label for="phone">Phone</label>
               <input type="tel" id="phone" name="phone" placeholder="Enter your phone number" required>
           </div>
           
           <div class="form-group">
               <label for="address">Address</label>
               <input type="text" id="address" name="address" placeholder="Enter your address" required>
           </div>
           
           <button type="submit" class="register-btn">REGISTER</button>
       </form>
       
       <div class="additional-links">
           <a href="login">Already have an account? Login here</a>
       </div>
   </div>
</body>
</html>