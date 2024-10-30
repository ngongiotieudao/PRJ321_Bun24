/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.guest;

import dal.ProductDAO;
import entity.Products;
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
@WebServlet(name = "ViewProduct", urlPatterns = {"/viewProduct"})
public class ViewProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdParam = request.getParameter("productId");

        if (productIdParam == null || productIdParam.isEmpty()) {
            response.sendRedirect("home");
            return;
        }

        int productId = ConvertHelper.parseStringToInt(productIdParam);
        ProductDAO productDao = new ProductDAO();
        Products product = productDao.findProductById(productId);

        if (product == null) {
            response.sendRedirect("home");
            return;
        }

        request.setAttribute("products", product);
        request.getRequestDispatcher("view-product.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
