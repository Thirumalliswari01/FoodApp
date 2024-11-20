package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.UserDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.User;

public class UserDAOImpl implements UserDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet resultSet;
    private ArrayList<User> userList = new ArrayList<User>();
    private User user = null;

    private static final String ADD_USER = "INSERT INTO `user`(`username`, `email`, `phonenumber`, `password`, `address`) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM `user`";
    private static final String SELECT_ON_EMAIL = "SELECT * FROM `user` WHERE `email`=?";
    private static final String UPDATE_ON_EMAIL = "UPDATE `user` SET `username`=?, `phonenumber`=?, `password`=?, `address`=? WHERE `email`=?";
    private static final String DELETE_ON_EMAIL = "DELETE FROM `user` WHERE `email`=?";
    private static final String UPDATE_PASSWORD = "UPDATE `user` SET `password`=? WHERE `userId`=?";

    public UserDAOImpl() { // constructor
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addUser(User u) {
        int status = 0;
        try {
            pstmt = con.prepareStatement(ADD_USER);
            pstmt.setString(1, u.getUsername());
            pstmt.setString(2, u.getEmail());
            pstmt.setString(3, u.getPhonenumber());
            pstmt.setString(4, u.getPassword());
            pstmt.setString(5, u.getAddress());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt);
        }
        return status;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(SELECT_ALL);
            userList = extractUserFromResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(stmt, resultSet);
        }
        return userList;
    }

    @Override
    public User getUser(String email) {
        try {
            pstmt = con.prepareStatement(SELECT_ON_EMAIL);
            pstmt.setString(1, email);
            resultSet = pstmt.executeQuery();
            userList = extractUserFromResultSet(resultSet);
            if (!userList.isEmpty()) {
                user = userList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt, resultSet);
        }
        return user;
    }

    @Override
    public int updateUser(User u) {
        int status = 0;
        try {
            pstmt = con.prepareStatement(UPDATE_ON_EMAIL);
            pstmt.setString(1, u.getUsername());
            pstmt.setString(2, u.getPhonenumber());
            pstmt.setString(3, u.getPassword());
            pstmt.setString(4, u.getAddress());
            pstmt.setString(5, u.getEmail());
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt);
        }
        return status;
    }

    @Override
    public int deleteUser(String email) {
        int status = 0;
        try {
            pstmt = con.prepareStatement(DELETE_ON_EMAIL);
            pstmt.setString(1, email);
            status = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt);
        }
        return status;
    }

    
    @Override
    public boolean updatePassword(int userId, String newPassword) {
        boolean isUpdated = false;
        try {
            pstmt = con.prepareStatement(UPDATE_PASSWORD);
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, userId);
            int rowsUpdated = pstmt.executeUpdate();
            isUpdated = (rowsUpdated > 0); 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt);
        }
        return isUpdated;
    }


    private ArrayList<User> extractUserFromResultSet(ResultSet resultSet) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("userId"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("password"),
                        resultSet.getString("address")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    private void closeResources(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void closeResources(Statement stmt, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
