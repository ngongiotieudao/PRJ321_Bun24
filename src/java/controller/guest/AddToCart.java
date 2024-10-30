/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.guest;

import dal.ProductDAO;
import entity.OrderDetails;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import utils.ConvertHelper;

/**
 *
 * @author datkh
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/addToCart"})
public class AddToCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productId");

        ProductDAO productDAO = new ProductDAO();
        HttpSession session = request.getSession();
        HashMap<Integer, OrderDetails> orderDetailsMap = (HashMap<Integer, OrderDetails>) session.getAttribute("orderDetailsMap");

        Products product = productDAO.findProductById(ConvertHelper.parseStringToInt(productId));
        if (product != null) {
            if (orderDetailsMap.containsKey(product.getProductId())) {
                
                OrderDetails existingOrderDetails = orderDetailsMap.get(product.getProductId());
                existingOrderDetails.setQuantity(existingOrderDetails.getQuantity() + 1);
            }else{
                
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setProducts(product);
                orderDetails.setQuantity(1);
                orderDetailsMap.put(product.getProductId(),orderDetails);
            
            }
        }
        response.sendRedirect("viewCart");

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
