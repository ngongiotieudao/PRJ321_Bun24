/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Users;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author datkh
 */
public class UserDAO {

    private PreparedStatement ps;
    private ResultSet rs;

    public Users login(String u, String p) {

        String sql = "select *\n"
                + "from users u\n"
                + "where u.username = ? and u.[password] = ? ";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);

            ps.setString(1, u);
            ps.setString(2, p);
            rs = ps.executeQuery();

            if (rs.next()) {
                Users user = new Users();
                user.setUserId(rs.getInt("userId"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setFullName(rs.getString("fullName"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("createdAt"));

                return user;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void registerUser(Users newUser) {
        String sql = "INSERT INTO users ([username], [password], [email], [fullName], [address], [phoneNumber], [role]) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, newUser.getEmail());
            ps.setString(4, newUser.getFullName());
            ps.setString(5, newUser.getAddress());
            ps.setString(6, newUser.getPhoneNumber());
            ps.setString(7, newUser.getRole());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isUsernameExists(String username) {
        String sql = "SELECT username FROM users WHERE username = ?";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            return rs.next(); // Returns true if a user with the username exists
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean isEmailExists(String email) {
        String sql = "SELECT email FROM users WHERE email = ?";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            return rs.next(); // Returns true if a user with the username exists
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
