/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.guest;

import static constant.Constant.RECORD_PER_PAGE;
import dal.CategoryDAO;
import dal.ProductDAO;
import entity.Categories;
import entity.OrderDetails;
import entity.PageControl;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import utils.ConvertHelper;
import java.util.HashMap;

/**
 *
 * @author admin
 */
@WebServlet(name = "Home", urlPatterns = {"/home"})
public class Home extends HttpServlet {

    private HashMap<Integer, OrderDetails> orderDetailsMap;

    @Override
    public void init() throws ServletException {
        if (orderDetailsMap == null) {
            orderDetailsMap = new HashMap<>();
            
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageControl pageControl = new PageControl();
        CategoryDAO categoryDAO = new CategoryDAO();
        HttpSession session = request.getSession(); 
        session.setAttribute("orderDetailsMap", orderDetailsMap);
        
        
        List<Products> listProduct = pagination(request, pageControl);
        List<Categories> listCategories = categoryDAO.findAll();
        
        request.setAttribute("listCategories", listCategories);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("pageControl", pageControl);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<Products> pagination(HttpServletRequest request, PageControl pageControl) {
        //get page
        String pageRaw = request.getParameter("page");
        ProductDAO productDAO = new ProductDAO();

        //valid page
        int page;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (NumberFormatException e) {
            page = 1;
        }
        int totalRecord = 0;
        List<Products> listProduct = null;

        String action = request.getParameter("action") == null
                ? "defaultFindAll"
                : request.getParameter("action");

        switch (action) {
            case "searchByName":
                String inputName = request.getParameter("inputName");

                totalRecord = productDAO.totalRecordBySearch(false, inputName);

                listProduct = productDAO.searchByName(page, inputName, false);

                pageControl.setUrlPattern("home?action=searchByName&inputName=" + inputName + "&");

                break;
            case "category":
                String categoryId = request.getParameter("categoryId");
                totalRecord = productDAO.totalRecordByCategory(false, ConvertHelper.parseStringToInt(categoryId));
                listProduct = productDAO.findProductByPageAndCategory(page, ConvertHelper.parseStringToInt(categoryId), false);
                pageControl.setUrlPattern("home?action=category&categoryId=" + categoryId + "&");
                break;
            default:
                totalRecord = productDAO.TotalRecord(false);

                listProduct = productDAO.findProductByPage(page, false);
                pageControl.setUrlPattern("home?");

        }
        int totalPage = (totalRecord % RECORD_PER_PAGE) == 0
                ? (totalRecord / RECORD_PER_PAGE)
                : (totalRecord / RECORD_PER_PAGE) + 1;

        pageControl.setPage(page);
        pageControl.setTotalPage(totalPage);
        pageControl.setTotalRecord(totalRecord);

        return listProduct;
    }
}
