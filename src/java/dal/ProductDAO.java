/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static constant.Constant.RECORD_PER_PAGE;
import entity.Categories;
import entity.Products;
import entity.Users;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

/**
 *
 * @author admin
 */
public class ProductDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private List<Products> listProducts;

    public ProductDAO() {
        listProducts = new ArrayList();
    }

    public List<Products> findAll() {
        String sql = "select * from Products p\n"
                + "                JOIN Users u on p.userId = u.userId\n"
                + "               JOIN Categories c \n"
                + "                 ON p.categoryId = c.categoryId";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Products product = extractProductFromResultSet(rs);
                listProducts.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listProducts;

    }

    public Products findProductById(int id) {
        String sql = "select * from Products p\n"
                + "JOIN Users u on p.userId = u.userId\n"
                + "JOIN Categories c \n"
                + "ON p.categoryId = c.categoryId\n"
                + "Where p.productId = ?";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Products product = extractProductFromResultSet(rs);
                return product;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int TotalRecord(boolean auctionMode) {
        String statusMode = auctionMode ? "AUCTION" : "AVAILABLE";
        String sql = "select count(p.productId) from Products p \n"
                + "where p.status = ?";

        int totalRecord = 0;

        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setString(1, statusMode);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalRecord = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return totalRecord;
    }

    public List<Products> findProductByPage(int page, boolean auctionMode) {

        String statusMode = auctionMode ? "AUCTION" : "AVAILABLE";

        String sql = "SELECT p.productId, p.[name], p.[description], p.price, p.stock,\n"
                + "p.[status], p.imageUrl, p.createdAt, u.userId, u.fullName,\n"
                + "c.categoryId, c.categoryName FROM Products p\n"
                + "JOIN Users u ON p.userId = u.userId\n"
                + "JOIN Categories c ON p.categoryId = c.categoryId\n"
                + "WHERE p.[status] = ?\n"
                + "ORDER BY p.productId\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection connection = new DBContext().connection) {

            ps = connection.prepareStatement(sql);

            ps.setString(1, statusMode);
            ps.setInt(2, (page - 1) * RECORD_PER_PAGE);
            ps.setInt(3, RECORD_PER_PAGE);

            rs = ps.executeQuery();

            while (rs.next()) {
                Products product = extractProductFromResultSet(rs);
                listProducts.add(product);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listProducts;

    }

    public boolean AddProduct(Products product) {
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([userId]\n"
                + "           ,[name]\n"
                + "           ,[description]\n"
                + "           ,[price]\n"
                + "           ,[stock]\n"
                + "           ,[categoryId]\n"
                + "           ,[imageUrl]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?);";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, product.getUsers().getUserId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setBigDecimal(4, product.getPrice());
            ps.setInt(5, product.getStock());
            ps.setInt(6, product.getCategory().getCategoryId());
            ps.setString(7, product.getImageUrl());
            ps.setString(8, product.getStatus());

            int rowAffected = ps.executeUpdate();

            return rowAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateProduct(Products product) {
        String sql = "UPDATE [dbo].[Products]\n"
                + "SET [userId] = ?,\n"
                + "    [name] = ?,\n"
                + "    [description] = ?,\n"
                + "    [price] = ?,\n"
                + "    [stock] = ?,\n"
                + "    [categoryId] = ?,\n"
                + "    [imageUrl] = ?,\n"
                + "    [status] = ?\n"
                + "WHERE [productId] = ?;"; // Điều kiện WHERE để xác định sản phẩm cần cập nhật

        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);

            ps.setInt(1, product.getUsers().getUserId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setBigDecimal(4, product.getPrice());
            ps.setInt(5, product.getStock());
            ps.setInt(6, product.getCategory().getCategoryId());
            ps.setString(7, product.getImageUrl());
            ps.setString(8, product.getStatus());
            ps.setInt(9, product.getProductId());
            int rowAffected = ps.executeUpdate();

            return rowAffected > 0; // Trả về true nếu có hàng được cập nhật
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int totalRecordByCategory(boolean auctionMode, int categoryId) {
        String statusMode = auctionMode ? "AUCTION" : "AVAILABLE";

        String sql = "select count(p.productId) from Products p\n"
                + "where p.[status] = ?  and p.categoryId = ?";
        int totalRecords = 0;

        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);

            ps.setString(1, statusMode);
            ps.setInt(2, categoryId);

            rs = ps.executeQuery();

            if (rs.next()) {
                totalRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return totalRecords;
    }

    public List<Products> findProductByPageAndCategory(int page, int category, boolean auctionMode) {
        String statusMode = auctionMode ? "AUCTION" : "AVAILABLE";

        String sql = "SELECT p.productId, p.[name], p.[description], p.price, p.stock,\n"
                + "p.[status], p.imageUrl, p.createdAt, u.userId, u.fullName,\n"
                + "c.categoryId, c.categoryName FROM Products p\n"
                + "JOIN Users u ON p.userId = u.userId\n"
                + "JOIN Categories c ON p.categoryId = c.categoryId\n"
                + "WHERE p.[status] = ? and p.categoryId = ?\n"
                + "ORDER BY p.productId\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT ? ROWS ONLY";

        try (Connection connection = new DBContext().connection) {

            ps = connection.prepareStatement(sql);

            ps.setString(1, statusMode);
            ps.setInt(2, category);
            ps.setInt(3, (page - 1) * RECORD_PER_PAGE);
            ps.setInt(4, RECORD_PER_PAGE);

            rs = ps.executeQuery();

            while (rs.next()) {
                Products product = extractProductFromResultSet(rs);
                listProducts.add(product);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listProducts;
    }

    public int totalRecordBySearch(boolean auctionMode, String inputSearch) {
        String statusMode = auctionMode ? "AUCTION" : "AVAILABLE";

        String sql = "select count(p.productId) from Products p\n"
                + "where p.[status] = ? and p.[name] like ?";
        int totalRecords = 0;

        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);

            ps.setString(1, statusMode);
            ps.setString(2, "%" + inputSearch + "%");

            rs = ps.executeQuery();

            if (rs.next()) {
                totalRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return totalRecords;
    }

    public List<Products> searchByName(int page, String inputSearch, boolean auctionMode) {
        String statusMode = auctionMode ? "AUCTION" : "AVAILABLE";

        String sql = "SELECT p.productId, p.[name], p.[description], p.price, p.stock,\n"
                + "p.[status], p.imageUrl, p.createdAt, u.userId, u.fullName,\n"
                + "c.categoryId, c.categoryName FROM Products p\n"
                + "JOIN Users u ON p.userId = u.userId\n"
                + "JOIN Categories c ON p.categoryId = c.categoryId\n"
                + "WHERE p.[status] = ? and p.[name] like ?\n"
                + "ORDER BY p.productId\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT ? ROWS ONLY";

        try (Connection connection = new DBContext().connection) {

            ps = connection.prepareStatement(sql);

            ps.setString(1, statusMode);
            ps.setString(2, "%" + inputSearch + "%");
            ps.setInt(3, (page - 1) * RECORD_PER_PAGE);
            ps.setInt(4, RECORD_PER_PAGE);

            rs = ps.executeQuery();

            while (rs.next()) {
                Products product = extractProductFromResultSet(rs);
                listProducts.add(product);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listProducts;
    }

    private Products extractProductFromResultSet(ResultSet rs) throws SQLException {
        // Extracting product fields
        int productId = rs.getInt("productId");
        String name = rs.getString("name");
        String description = rs.getString("description");
        BigDecimal price = rs.getBigDecimal("price");
        int stock = rs.getInt("stock");
        String status = rs.getString("status");
        String imageUrl = rs.getString("imageUrl");
        Timestamp createdAt = rs.getTimestamp("createdAt");

        // Extracting related user data
        Users user = extractUserFromResultSet(rs);

        // Extracting related category data
        Categories category = extractCategoryFromResultSet(rs);

        // Returning a populated Products object
        return new Products(productId, user, name, description, price, stock, category, imageUrl, status, createdAt);
    }

    private Users extractUserFromResultSet(ResultSet rs) throws SQLException {
        int userId = rs.getInt("userId");
        String fullName = rs.getString("fullName");

        Users user = new Users();
        user.setUserId(userId);
        user.setFullName(fullName);

        return user;
    }

    private Categories extractCategoryFromResultSet(ResultSet rs) throws SQLException {
        int categoryId = rs.getInt("categoryId");
        String categoryName = rs.getString("categoryName");

        Categories category = new Categories();
        category.setCategoryId(categoryId);
        category.setCategoryName(categoryName);

        return category;
    }

    public List<Products> findProductByUserId(int userId) {
        List<Products> userProducts = new ArrayList<>();
        String sql = "SELECT p.productId, p.[name], p.[description], p.price, p.stock,\n"
                + "p.[status], p.imageUrl, p.createdAt, u.userId, u.fullName,\n"
                + "c.categoryId, c.categoryName FROM Products p\n"
                + "JOIN Users u ON p.userId = u.userId\n"
                + "JOIN Categories c ON p.categoryId = c.categoryId\n"
                + "WHERE u.userId = ?";

        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Products product = extractProductFromResultSet(rs);
                userProducts.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userProducts;
    }

    public static void main(String[] args) {
    ProductDAO productDAO = new ProductDAO();

    // Test findProductByUserId
    int testUserId = 1; // Replace with an existing user ID to test
    System.out.println("Testing findProductByUserId with user ID: " + testUserId);

    List<Products> products = productDAO.findProductByUserId(testUserId);
    if (products.isEmpty()) {
        System.out.println("No products found for user ID: " + testUserId);
    } else {
        System.out.println("Products for user ID " + testUserId + ":");
        for (Products product : products) {
            System.out.println(product);
        }
    }
}

}
