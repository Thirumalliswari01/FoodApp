package com.tapfoods.dao;

import com.tapfoods.model.OrderTable;

import java.sql.SQLException;
import java.util.List;

public interface OrderTableDAO {
    int insertOrder(OrderTable order) throws SQLException;
    List<OrderTable> getAllOrders();
    OrderTable getOrderById(int orderId);
    int maxOrderId(); 
	boolean isPaymentModeAlreadyUsed(int userId, int restaurantId, String paymentMode) throws SQLException;

	
}
