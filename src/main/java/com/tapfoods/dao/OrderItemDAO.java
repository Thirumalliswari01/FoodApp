package com.tapfoods.dao;

import com.tapfoods.model.OrderItem;
import java.util.List;

public interface OrderItemDAO {
    int insertOrderItem(OrderItem orderItem); 
    List<OrderItem> getAllOrderItems(); 
    OrderItem getOrderItemById(int orderItemId); 
    List<OrderItem> fetchOrdersItems(int orderId); 
}
