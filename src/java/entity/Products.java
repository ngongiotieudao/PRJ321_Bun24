/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 *
 * @author admin
 */
public class Products {
    private int productId;
    private Users users;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private Categories category;
    private String imageUrl;
    private String status;
    private Timestamp createdAt;

    public Products() {
    }

    public Products(int productId, Users users, String name, String description, BigDecimal price, int stock, Categories category, String imageUrl, String status, Timestamp createdAt) {
        this.productId = productId;
        this.users = users;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.imageUrl = imageUrl;
        this.status = status;
        this.createdAt = createdAt;
    }

    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Products{" + "productId=" + productId + ", users=" + users + ", name=" + name + ", description=" + description + ", price=" + price + ", stock=" + stock + ", category=" + category + ", imageUrl=" + imageUrl + ", status=" + status + ", createdAt=" + createdAt + '}';
    }
    
    
    
    
}
