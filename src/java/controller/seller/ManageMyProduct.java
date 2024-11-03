package controller.seller;

import dal.ProductDAO;
import dal.UserDAO;
import entity.Products;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import utils.ConvertHelper;

@WebServlet(name = "manageMyProduct", urlPatterns = {"/manageMyProduct"})
public class ManageMyProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userid = request.getParameter("userId");
        ProductDAO productDAO = new ProductDAO();
        List<Products> products = productDAO.findProductByUserId(ConvertHelper.parseStringToInt(userid));
        request.setAttribute("products", products);
        request.getRequestDispatcher("manage-my-product.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
