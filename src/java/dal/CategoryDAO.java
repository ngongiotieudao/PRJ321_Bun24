/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Categories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private List<Categories> listCategory;

    public CategoryDAO() {
        listCategory = new ArrayList<>();
    }
    

    public List<Categories> findAll() {
        String sql = "select * from Categories";
        try (Connection connection = new DBContext().connection) {
            
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int categoryId = rs.getInt("categoryId");
                String categoryName = rs.getString("categoryName");              
                Categories category = new Categories(categoryId, categoryName, null);
                listCategory.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listCategory;
    }
    
}
