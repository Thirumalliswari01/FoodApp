package com.tapfoods.daoimpl;

import com.tapfoods.dao.OrderItemDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {
    private Connection con;

    public OrderItemDAOImpl() {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertOrderItem(OrderItem orderItem) {
        int status = 0;
        String query = "INSERT INTO `orderitem` (`orderId`, `menuId`, `quantity`, `subTotal`) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getSubTotal());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItemList = new ArrayList<>();
        String query = "SELECT * FROM `orderitem`";
        try (Statement stmt = con.createStatement(); 
             ResultSet resultSet = stmt.executeQuery(query)) {
            orderItemList = extractOrderItemFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    @Override
    public OrderItem getOrderItemById(int orderItemId) {
        OrderItem orderItem = null;
        String query = "SELECT * FROM `orderitem` WHERE `orderItemId` = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, orderItemId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                List<OrderItem> orderItemList = extractOrderItemFromResultSet(resultSet);
                if (!orderItemList.isEmpty()) {
                    orderItem = orderItemList.get(0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    @Override
    public List<OrderItem> fetchOrdersItems(int orderId) {
        List<OrderItem> orderItemList = new ArrayList<>();
        String query = "SELECT * FROM `orderitem` WHERE `orderId` = ?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, orderId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                orderItemList = extractOrderItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    private List<OrderItem> extractOrderItemFromResultSet(ResultSet resultSet) {
        List<OrderItem> orderItemList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                orderItemList.add(new OrderItem(
                        resultSet.getInt("orderItemId"),
                        resultSet.getInt("orderId"),
                        resultSet.getInt("menuId"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("subTotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }
}
