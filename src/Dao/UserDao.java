/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.User;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tuanta
 */
public class UserDao {

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        Connection connection = Connect.getJDBCConection();
        String sql = "select * from user";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phoneNumber"));
                user.setUsername(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setFavourites(rs.getString("favorite"));
                user.setAbout(rs.getString("about"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public User getUserById(int id) {
        

        Connection connection = Connect.getJDBCConection();
        String sql = "select * from user where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phoneNumber"));
                user.setUsername(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setFavourites(rs.getString("favorite"));
                user.setAbout(rs.getString("about"));
                
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User user) {
        Connection connection = Connect.getJDBCConection();
        String sql = " INSERT INTO user( name, phoneNumber, userName, password, role, favorite, about)"
                + " VALUE(?,?,?,?,?,?,?) ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAbout());
            preparedStatement.setString(6, user.getFavourites());
            preparedStatement.setString(7, user.getRole());
            int rs = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateUser(User user){
        Connection connection = Connect.getJDBCConection();
        String sql = "Update user set name = ? , phoneNumber = ? , userName = ? , password = ? , "
                + " role = ? , favorite = ? , about = ? where id = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.setString(6, user.getFavourites());
            preparedStatement.setString(7, user.getAbout());
            preparedStatement.setInt(8, user.getId());
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteUser(int id){
       Connection connection = Connect.getJDBCConection();
       String sql = "delete from user where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
