/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.OrderDAO;
import entity.OrderDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import utils.ConvertHelper;

/**
 *
 * @author datkh
 */
@WebServlet(name = "ViewOrderDetails", urlPatterns = {"/viewOrderDetails"})
public class ViewOrderDetails extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        OrderDAO orderDAO = new OrderDAO();
        List<OrderDetails> orderDetails =  orderDAO.findAllOrderDetailsByOrderId(ConvertHelper.parseStringToInt(orderId));
        
        request.setAttribute("orderDetails", orderDetails);
        
        request.getRequestDispatcher("order-details.jsp").forward(request, response);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
