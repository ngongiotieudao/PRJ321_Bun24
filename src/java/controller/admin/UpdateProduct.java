/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.CategoryDAO;
import dal.ProductDAO;
import entity.Categories;
import entity.Products;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import utils.ConvertHelper;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateProduct", urlPatterns = {"/updateProduct"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UpdateProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idProduct = request.getParameter("idProduct");

        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        Products product = productDAO.findProductById(ConvertHelper.parseStringToInt(idProduct));
        List<Categories> categories = categoryDAO.findAll();

        request.setAttribute("product", product);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("update-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");
        String description = request.getParameter("description");
        String categoryId = request.getParameter("categoryId");
        String status = request.getParameter("status");
        String idProduct = request.getParameter("idProduct");

        String imagePath = "";

        Users user = new Users();
        user.setUserId(3);

        Categories categories = new Categories();
        categories.setCategoryId(ConvertHelper.parseStringToInt(categoryId));

        Products product = new Products(ConvertHelper.parseStringToInt(idProduct),
                user, name, description,
                ConvertHelper.parseStringToBigDecimal(price),
                ConvertHelper.parseStringToInt(stock), categories, imagePath,
                status, null);

        ProductDAO productDAO = new ProductDAO();

        if (isImageUploaded(request)) {

            imagePath = saveUploadedImage(request);
            product.setImageUrl(imagePath);

            if (productDAO.updateProduct(product)) {
                response.sendRedirect("updateProduct?idProduct=" + idProduct);
            }

        } else {
            String imageExistOfProduct = productDAO.findProductById(ConvertHelper.parseStringToInt(idProduct)).getImageUrl();
            product.setImageUrl(imageExistOfProduct);

            if (productDAO.updateProduct(product)) {
                response.sendRedirect("updateProduct?idProduct=" + idProduct);
            }

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private String saveUploadedImage(HttpServletRequest request) throws IOException, ServletException {
        String uploadFolder = getServletContext().getRealPath("") + "../../web/img";
        File folder = new File(uploadFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        Part filePart = request.getPart("imageFile");
        String fileName = getSubmittedFileName(filePart);
        String imagePath = "img/" + fileName;

        try (OutputStream out = new FileOutputStream(new File(uploadFolder + File.separator + fileName)); InputStream fileContent = filePart.getInputStream()) {
            byte[] bytes = new byte[1024];
            int read;
            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException fne) {
            System.out.println(fne.getMessage());
        }

        return imagePath;
    }

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private boolean isImageUploaded(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("imageFile");
        String fileName = getSubmittedFileName(filePart);
        return fileName != null && !fileName.isEmpty();
    }
}
