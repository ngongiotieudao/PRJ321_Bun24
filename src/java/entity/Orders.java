/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
/**
 *
 * @author datkh
 */
public class Orders {
    private int orderId;
    private Users users;
    private BigDecimal totalAmount;
    private Timestamp orderDate;
    private String status;

    public Orders() {
    }

    public Orders(int orderId, Users users, BigDecimal totalAmount, Timestamp orderDate, String status) {
        this.orderId = orderId;
        this.users = users;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderId=" + orderId + ", users=" + users + ", totalAmount=" + totalAmount + ", orderDate=" + orderDate + ", status=" + status + '}';
    }
     
    
}
