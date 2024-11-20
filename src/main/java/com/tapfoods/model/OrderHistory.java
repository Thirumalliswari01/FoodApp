package com.tapfoods.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderHistory {

    private int orderHistoryId;
    private int orderId;
    private int userId;
    private LocalDateTime orderDate;
    private double totalAmount;  
    private String status;

    public OrderHistory() {
        super();
    }

    public OrderHistory(int orderHistoryId, int orderId, int userId, LocalDateTime orderDate, double totalAmount ,
                        String status) {
        super();
        this.orderHistoryId = orderHistoryId;
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount  = totalAmount ;
        this.status = status;
    }

    public OrderHistory(int orderId, int userId, LocalDateTime orderDate, double totalAmount , String status) {
        super();
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount  = totalAmount ;
        this.status = status;
    }

    public int getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(int orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double gettotalAmount () {
        return totalAmount ;
    }

    public void settotalAmount (double totalAmount ) {
        this.totalAmount  = totalAmount ;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderHistory [orderHistoryId=" + orderHistoryId + ", orderId=" + orderId + ", userId=" + userId
                + ", orderDate=" + orderDate + ", totalAmount =" + totalAmount  + ", status=" + status + "]";
    }

	
	
}
