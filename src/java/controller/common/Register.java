/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import dal.UserDAO;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String u = request.getParameter("username");
        String p = request.getParameter("password");
        String re = request.getParameter("repass");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        
        if (!p.equals(re)) {
            request.setAttribute("err", "Password not matching! try again!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        
        UserDAO userDAO = new UserDAO();
        if (userDAO.isUsernameExists(u)) {
            request.setAttribute("err", "Username exist!, please input another");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }      
        
        if (userDAO.isEmailExists(email)) {
            request.setAttribute("err", "Email existed");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }     
        
        Users newUser = new Users();
        newUser.setUsername(u);
        newUser.setPassword(p);
        newUser.setFullName(fullname);
        newUser.setEmail(email);
        newUser.setPhoneNumber(phone);
        newUser.setAddress(address);
        newUser.setRole("USER");
        
        userDAO.registerUser(newUser);
        
        response.sendRedirect("login");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
