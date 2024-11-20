package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tapfoods.dao.OrderHistoryDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {

    private Connection con;
    List<OrderHistory> orderHistoryList = new ArrayList<OrderHistory>();

    private static final String ADD_ORDER_HISTORY = "INSERT INTO `orderhistory` (`orderId`, `userId`, `orderDate`, `totalAmount`, `status`) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDER_HISTORY = "SELECT * FROM `orderhistory`";
    private static final String SELECT_ORDER_HISTORY_BY_ID = "SELECT * FROM `orderhistory` WHERE `orderHistoryId` = ?";
    private static final String SELECT_ORDER_HISTORY_BY_USER_ID = "SELECT * FROM `orderhistory` WHERE `userId` = ?";

    public OrderHistoryDAOImpl() {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrderHistory(OrderHistory orderHistory) {
        int status1 = 0;
        try (PreparedStatement pstmt = con.prepareStatement(ADD_ORDER_HISTORY)) {
            String status = orderHistory.getStatus();
            if (status == null || status.trim().isEmpty()) {
                status = "Pending";
            }

            pstmt.setInt(1, orderHistory.getOrderId());
            pstmt.setInt(2, orderHistory.getUserId());

            LocalDateTime orderDate = orderHistory.getOrderDate() != null 
                ? orderHistory.getOrderDate() 
                : LocalDateTime.now();

            pstmt.setTimestamp(3, Timestamp.valueOf(orderDate));
            pstmt.setDouble(4, orderHistory.gettotalAmount());
            pstmt.setString(5, status);

            status1 = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status1;
    }

    @Override
    public List<OrderHistory> getAllOrderHistories() {
        try (Statement stmt = con.createStatement(); 
             ResultSet resultSet = stmt.executeQuery(SELECT_ALL_ORDER_HISTORY)) {
            orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    @Override
    public OrderHistory getOrderHistoryById(int orderHistoryId) {
        OrderHistory orderHistory = null;
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_ORDER_HISTORY_BY_ID)) {
            pstmt.setInt(1, orderHistoryId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                List<OrderHistory> orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
                if (!orderHistoryList.isEmpty()) {
                    orderHistory = orderHistoryList.get(0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistory;
    }

    @Override
    public List<OrderHistory> fetchOrderOnUserid(int userId) {
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_ORDER_HISTORY_BY_USER_ID)) {
            pstmt.setInt(1, userId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                orderHistoryList = extractOrderHistoryFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }

    private List<OrderHistory> extractOrderHistoryFromResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            orderHistoryList.add(new OrderHistory(
                    resultSet.getInt("orderHistoryId"),
                    resultSet.getInt("orderId"),
                    resultSet.getInt("userId"),
                    resultSet.getTimestamp("orderDate").toLocalDateTime(),
                    resultSet.getDouble("totalAmount"),
                    resultSet.getString("status")
            ));
        }
        return orderHistoryList;
    }
}
