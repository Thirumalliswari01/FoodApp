package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {

   
    private static final String ADD_RESTAURANT = "INSERT INTO restaurant (restaurantName, deliveryTime, cuisineType, address, rating, isActive, adminId, imgPath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM restaurant";
    private static final String SELECT_ON_ID = "SELECT * FROM restaurant WHERE restaurantId = ?";
    private static final String UPDATE_ON_ID = "UPDATE restaurant SET restaurantName = ?, deliveryTime = ?, cuisineType = ?, address = ?, rating = ?, isActive = ?, adminId = ?, imgPath = ? WHERE restaurantId = ?";
    private static final String DELETE_ON_ID = "DELETE FROM restaurant WHERE restaurantId = ?";

    @Override
    public int addRestaurant(Restaurant r) {
        int status = 0;

        
        try (Connection con = DBUtils.myConnect(); 
             PreparedStatement pstmt = con.prepareStatement(ADD_RESTAURANT)) {
            
            pstmt.setString(1, r.getRestaurantName());
            pstmt.setInt(2, r.getDeliveryTime());
            pstmt.setString(3, r.getCuisineType());
            pstmt.setString(4, r.getAddress());
            pstmt.setFloat(5, r.getRating());
            pstmt.setBoolean(6, r.isActive());
            pstmt.setInt(7, r.getAdminUserId());  
            pstmt.setString(8, r.getImagePath() != null && !r.getImagePath().isEmpty() ? r.getImagePath() : "default/image/path.jpg");

            status = pstmt.executeUpdate();  
            
        } catch (Exception e) {
            System.err.println("Error adding restaurant: " + e.getMessage());
        }
        return status;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurantList = new ArrayList<>();

      
        try (Connection con = DBUtils.myConnect(); 
             Statement stmt = con.createStatement(); 
             ResultSet resultSet = stmt.executeQuery(SELECT_ALL)) {

            restaurantList = extractRestaurantFromResultSet(resultSet);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    @Override
    public Restaurant getRestaurant(int restaurantId) {
        Restaurant res = null;



        try (Connection con = DBUtils.myConnect(); 
             PreparedStatement pstmt = con.prepareStatement(SELECT_ON_ID)) {
            
            pstmt.setInt(1, restaurantId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    res = new Restaurant(
                        resultSet.getInt("restaurantId"),
                        resultSet.getString("restaurantName"),
                        resultSet.getString("cuisineType"),
                        resultSet.getInt("deliveryTime"),
                        resultSet.getString("address"),
                        resultSet.getInt("adminId"),
                        resultSet.getFloat("rating"),
                        resultSet.getBoolean("isActive"),
                        resultSet.getString("imgPath"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;  // Return null if no restaurant is found
    }

    @Override
    public int updateRestaurant(Restaurant r) {
        int status = 0;



        try (Connection con = DBUtils.myConnect(); 
             PreparedStatement pstmt = con.prepareStatement(UPDATE_ON_ID)) {
            
            pstmt.setString(1, r.getRestaurantName());
            pstmt.setInt(2, r.getDeliveryTime());
            pstmt.setString(3, r.getCuisineType());
            pstmt.setString(4, r.getAddress());
            pstmt.setFloat(5, r.getRating());
            pstmt.setBoolean(6, r.isActive());
            pstmt.setInt(7, r.getAdminUserId());
            pstmt.setString(8, r.getImagePath());
            pstmt.setInt(9, r.getRestaurantId());  


            status = pstmt.executeUpdate();  
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteRestaurant(int restaurantId) {
        int status = 0;

        
        try (Connection con = DBUtils.myConnect(); 
             PreparedStatement pstmt = con.prepareStatement(DELETE_ON_ID)) {
            
            pstmt.setInt(1, restaurantId);
            status = pstmt.executeUpdate();  
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    
    private List<Restaurant> extractRestaurantFromResultSet(ResultSet resultSet) {
        List<Restaurant> restaurantList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                restaurantList.add(new Restaurant(
                    resultSet.getInt("restaurantId"),
                    resultSet.getString("restaurantName"),
                    resultSet.getString("cuisineType"),
                    resultSet.getInt("deliveryTime"),
                    resultSet.getString("address"),
                    resultSet.getInt("adminId"),
                    resultSet.getFloat("rating"),
                    resultSet.getBoolean("isActive"),
                    resultSet.getString("imgPath")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return restaurantList;  
    }
}