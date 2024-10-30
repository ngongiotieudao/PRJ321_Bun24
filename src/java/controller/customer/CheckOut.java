/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.OrderDAO;
import dal.ProductDAO;
import entity.OrderDetails;
import entity.Orders;
import entity.Products;
import entity.Users;
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
 * @author admin
 */
@WebServlet(name = "CheckOut", urlPatterns = {"/checkOut"})
public class CheckOut extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckOut</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOut at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        HttpSession session = request.getSession();
        BigDecimal totalAmountOrder = (BigDecimal) session.getAttribute("totalPriceOrder");
        HashMap<Integer, OrderDetails> orderDetailsMap = (HashMap<Integer, OrderDetails>) session.getAttribute("orderDetailsMap");

        Users user = (Users) session.getAttribute("account");

        String message;

        if (orderDetailsMap == null || orderDetailsMap.isEmpty()) {
            message = "Check out failed! No items in the cart found!";
        } else {
            Orders order = new Orders(0, user, totalAmountOrder, null, "PENDING");
            OrderDAO orderDAO = new OrderDAO();
            ProductDAO productDAO = new ProductDAO();
            if (orderDAO.insertOrderAndOrderDetails(order, orderDetailsMap)) {
                message = "Checkout Successfully!";
                
                for (OrderDetails orderDetails : orderDetailsMap.values()) {
                    Products product = orderDetails.getProducts();
                    int newStock = product.getStock() - orderDetails.getQuantity();

                    if (newStock >= 0) {
                        product.setStock(newStock);
                        if (!productDAO.updateProduct(product)) {
                            message = "Checkout Completed with Stock Update Errors!";
                            break;
                        }
                    }
                }
                session.removeAttribute("orderDetailsMap");
                session.removeAttribute("totalPriceOrder");
            } else {
                message ="Checkout Failed";
            }
        }
        request.setAttribute("msg", message);
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
        processRequest(request, response);
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
