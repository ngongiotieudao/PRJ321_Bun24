/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.OrderDetails;
import entity.Orders;
import entity.Products;
import entity.Users;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
/**
 *
 * @author admin
 */
public class OrderDAO {
    
    private PreparedStatement ps;
    private ResultSet rs;
    private List<Orders> listOrder;
    private List<OrderDetails> orderDetails;
    
    public OrderDAO(){
        this.listOrder = new ArrayList<>();
        this.orderDetails = new ArrayList<>();
    
    }
    
    public boolean insertOrderAndOrderDetails(Orders order, HashMap<Integer, OrderDetails> mapOrderDetails) {
        String sql = "INSERT INTO [dbo].[Orders] \n"
                +"           ([userId], [totalAmount], [status])\n"
                +"      VALUES \n"
                +"          ( ?, ?, ?)";
        
        String sqlOrderDetails = "INSERT INTO [dbo].[OrderDetails] \n"
                +"              ([orderId], [productId], [quantity], [price]) \n"
                +"      VALUES \n"
                +"          ( ?, ?, ?, ?)";
        
        try( Connection connection = new DBContext().connection) {
            
            ps = connection.prepareStatement(sql,ps.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, order.getUsers().getUserId());
            ps.setBigDecimal(2, order.getTotalAmount());
            ps.setString(3, order.getStatus());
            
            int rowAffected = ps.executeUpdate();
            
            try(ResultSet generatedKeys= ps.getGeneratedKeys()) {
                if(generatedKeys.next()) {
                    int idOrder = generatedKeys.getInt(1);
                    
                    ps = connection.prepareStatement(sqlOrderDetails);
                    
                    for(Map.Entry<Integer, OrderDetails> entry : mapOrderDetails.entrySet()) {
                        OrderDetails orderDetail = entry.getValue();
                        
                         ps.setInt(1, idOrder);
                         ps.setInt(2, orderDetail.getProducts().getProductId());
                         ps.setInt(3, orderDetail.getQuantity());
                         ps.setBigDecimal(4, orderDetail.getPrice());
                         
                         int rowOrderDetailsAffected = ps.executeUpdate();
                         
                         if (rowOrderDetailsAffected < 0 ) {
                             return false;
                         }
                    }
                }
            }
            
            return rowAffected > 0;
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            
        }
        
        return false;
    }
    
    public boolean updateOrder(Orders order) {
        String sql = "UPDATE [dbo].[Orders]\n"
                +"    SET [userId] = ?,\n"
                +"        [totalAmount] = ?,\n"
                +"        [status] = ?\n"
                +"    WHERE orderId = ?";
        try( Connection connection = new DBContext().connection) {
            
            ps = connection.prepareStatement(sql);
            
            ps.setInt(1, order.getUsers().getUserId());
            ps.setBigDecimal(2, order.getTotalAmount());
            ps.setString(3, order.getStatus());
            ps.setInt(4, order.getOrderId());
            
            int rowAffected = ps.executeUpdate();
            
 
            return rowAffected > 0;
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            
        }
        
        return false;
    }
    
    public Orders findOrderById(int id) {
        String sql = "SELECT * FROM Orders o\n"
                + "WHERE o.orderId= ?";
        try(Connection connection = new DBContext().connection) {
            
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if(rs.next()) {
                int orderId = rs.getInt("orderId");
                int userId = rs.getInt("userId");
                BigDecimal totalAmount = rs.getBigDecimal("totalAmount");
                String status = rs.getString("status");
                Timestamp orderDate= rs.getTimestamp("orderDate");
                
                Users user = new Users();
                user.setUserId(userId);
                
                Orders order = new Orders(orderId, user, totalAmount, orderDate, status);
                
                return order;
                
            }
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public List<Orders> findAll() {
        String sql = "SELECT * FROM Orders o\n"
                +" join Users u\n"
                +" on o.userId = u.userId";
        
        try(Connection connection = new DBContext().connection) {
            
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                int userId = rs.getInt("userId");
                BigDecimal totalAmount = rs.getBigDecimal("totalAmount");
                String status = rs.getString("status");
                Timestamp orderDate= rs.getTimestamp("orderDate");
                String fullName = rs.getString("fullName");
                String phoneNumber = rs.getString("phoneNumber");
                
                Users user = new Users();
                user.setUserId(userId);
                user.setFullName(fullName);
                user.setPhoneNumber(phoneNumber);
                
                
                Orders order = new Orders(orderId, user, totalAmount, orderDate, status);
                
                listOrder.add(order);
            }
            
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return listOrder;
    }
    

    
    public List<OrderDetails> findAllOrderDetailsByOrderId(int id) {
        
        
        String sql = "SELECT * FROM OrderDetails od\n"
                + "join Products p\n"
                + "on od.productId = p.productId \n"
                + "WHERE od.orderId = ?";
        
        try (Connection connection = new DBContext().connection) {
            
             ps = connection.prepareStatement(sql);
             ps.setInt(1, id);
             
             rs = ps.executeQuery();
             
             while(rs.next()) {
                int orderDetailsId = rs.getInt("orderDetailId");
                int orderId = rs.getInt("orderId");
                int productId = rs.getInt("productId");
                String imageProduct = rs.getString("imageUrl");
                String nameProduct = rs.getString("name");
                int quantity = rs.getInt("quantity");
                BigDecimal price = rs.getBigDecimal("price");
                
                
                Products product = new Products();
                product.setProductId(productId);
                product.setImageUrl(imageProduct);
                product.setName(nameProduct);
                
                OrderDetails orderDetail =  new OrderDetails(orderDetailsId, orderId, product, quantity, price);              
                orderDetails.add(orderDetail);
             }
            
             
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return orderDetails;
    }
}
