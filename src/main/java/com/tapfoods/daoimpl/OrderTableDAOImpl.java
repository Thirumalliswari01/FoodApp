package com.tapfoods.daoimpl;

import com.tapfoods.dao.OrderTableDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.OrderTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderTableDAOImpl implements OrderTableDAO {

    private Connection con;
    private static final String ADD_ORDER = "INSERT INTO `ordertable` (`userId`, `paymentMode`, `restaurantId`, `totalAmount`, `status`) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDERS = "SELECT * FROM `ordertable`";
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM `ordertable` WHERE `orderId` = ?";
    private static final String UPDATE_ORDER_STATUS = "UPDATE `ordertable` SET `status` = ? WHERE `orderId` = ?";
    private static final String MAX_ORDER_ID = "SELECT MAX(`orderId`) FROM `ordertable`";

    public OrderTableDAOImpl() {
        try {
            con = DBUtils.myConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String CHECK_PAYMENT_MODE_EXISTS = "SELECT COUNT(*) FROM `ordertable` WHERE `paymentMode` = ? AND `userId` = ? AND `restaurantId` = ?";

    public int insertOrder(OrderTable order) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getUserId());
            stmt.setString(2, order.getPaymentMode());
            stmt.setInt(3, order.getRestaurantId());
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, order.getStatus());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating order failed, no ID obtained.");
                    }
                }
            }
            return affectedRows;
        }
    }

    @Override
    public List<OrderTable> getAllOrders() {
        List<OrderTable> orderList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(SELECT_ALL_ORDERS)) {
            orderList = extractOrderTableFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public OrderTable getOrderById(int orderId) {
        OrderTable order = null;
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_ORDER_BY_ID)) {
            pstmt.setInt(1, orderId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                List<OrderTable> orderList = extractOrderTableFromResultSet(resultSet);
                if (!orderList.isEmpty()) {
                    order = orderList.get(0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public int maxOrderId() {
        int maxOrderId = 0;
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(MAX_ORDER_ID)) {
            if (rs.next()) {
                maxOrderId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxOrderId;
    }

    private List<OrderTable> extractOrderTableFromResultSet(ResultSet resultSet) throws SQLException {
        List<OrderTable> orderList = new ArrayList<>();
        while (resultSet.next()) {
            OrderTable order = new OrderTable();
            order.setOrderId(resultSet.getInt("orderId"));
            order.setUserId(resultSet.getInt("userId"));
            order.setPaymentMode(resultSet.getString("paymentMode"));
            order.setRestaurantId(resultSet.getInt("restaurantId"));
            order.setTotalAmount(resultSet.getDouble("totalAmount"));
            order.setStatus(resultSet.getString("status"));
            orderList.add(order);
        }
        return orderList;
    }

    public boolean isPaymentModeAlreadyUsed(int userId, int restaurantId, String paymentMode) throws SQLException {
        String sql = "SELECT COUNT(*) FROM `ordertable` WHERE `userId` = ? AND `restaurantId` = ? AND `paymentMode` = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, restaurantId);
            stmt.setString(3, paymentMode);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
