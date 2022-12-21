package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.utilities.ConnectionUtil;

public class UserDao {

    public User getUserByUsername(String username) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from users where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            rs.next();
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"));
        } catch (SQLException e) {
            System.out.println("getUserByUserName: " + e.getMessage());
            return new User();
        }
    }

    public User createUser(UsernamePasswordAuthentication registerRequest) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "insert into users values (default, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, registerRequest.getUsername());
            ps.setString(2, registerRequest.getPassword());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            rs.next();
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"));
        } catch (SQLException e) {
            System.out.println("createUser: " + e.getMessage());
            return null;
        }
    }
}
