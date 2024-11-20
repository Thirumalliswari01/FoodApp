package com.tapfoods.dao;

import com.tapfoods.model.OrderHistory;

import java.sql.SQLException;
import java.util.List;

public interface OrderHistoryDAO {
    int addOrderHistory(OrderHistory orderHistory) throws SQLException; 
    List<OrderHistory> getAllOrderHistories(); 
    OrderHistory getOrderHistoryById(int orderHistoryId); 
    List<OrderHistory> fetchOrderOnUserid(int userId); 
}
