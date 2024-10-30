/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.guest;

import entity.OrderDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author datkh
 */
@WebServlet(name = "ViewCart", urlPatterns = {"/viewCart"})
public class ViewCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        HashMap<Integer, OrderDetails> orderDetailsMap = (HashMap<Integer, OrderDetails>) session.getAttribute("orderDetailsMap");

        if (orderDetailsMap == null) {
            response.sendRedirect("home");
            return;
        }
        
        BigDecimal totalPriceOrder = BigDecimal.ZERO;

        for (Map.Entry<Integer, OrderDetails> entry : orderDetailsMap.entrySet()) {
            OrderDetails orderDetails = entry.getValue();

            BigDecimal price = orderDetails.getProducts().getPrice().multiply(BigDecimal.valueOf(orderDetails.getQuantity()));

            orderDetails.setPrice(price);

            totalPriceOrder = totalPriceOrder.add(price);
        }
        session.setAttribute("totalPriceOrder", totalPriceOrder);
        request.getRequestDispatcher("viewCart.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
