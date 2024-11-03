/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.OrderDAO;
import dal.UserDAO;
import entity.Orders;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ConvertHelper;

/**
 *
 * @author datkh
 */
@WebServlet(name = "UpdateRole", urlPatterns = {"/updateRole"})
public class UpdateRole extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userid = request.getParameter("userId");
        String role = request.getParameter("role");

        UserDAO userDAO = new UserDAO();
        Users users = userDAO.findUserById(ConvertHelper.parseStringToInt(userid));
        users.setRole(role);

        if (userDAO.updateRole(ConvertHelper.parseStringToInt(userid), role)) {
            response.sendRedirect("manageRole");
        }
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
