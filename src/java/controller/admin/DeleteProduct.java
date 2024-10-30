/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.ProductDAO;
import entity.Products;
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
 * @author admin
 */
@WebServlet(name="DeleteProduct", urlPatterns={"/deleteProduct"})
public class DeleteProduct extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String idProduct = request.getParameter("idProduct");
        
        ProductDAO productDAO = new ProductDAO();
        
        Products product = productDAO.findProductById(ConvertHelper.parseStringToInt(idProduct));
        product.setStatus("SOLD_OUT");
        
        if (productDAO.updateProduct(product)){
            response.sendRedirect("manageProduct");
        }
       
            
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
