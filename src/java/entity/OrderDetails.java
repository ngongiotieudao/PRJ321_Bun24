/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;

/**
 *
 * @author datkh
 */
public class OrderDetails {
    private int orderDetailsId;
    private int orderId;
    private Products products;
    private int quantity;
    private BigDecimal price;

    public OrderDetails() {
    }

    public OrderDetails(int orderDetailsId, int orderId, Products products, int quantity, BigDecimal price) {
        this.orderDetailsId = orderDetailsId;
        this.orderId = orderId;
        this.products = products;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(int orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderDetailsId=" + orderDetailsId + ", orderId=" + orderId + ", products=" + products + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
    
}
