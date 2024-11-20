package com.tapfoods.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OrderTable {
    private int orderId;
    private int restaurantId;
    private int userId;
    private Timestamp orderTime;
    private double totalAmount;
    private String status;
    private String paymentMode;

    public OrderTable() {
        super();
        
        this.orderTime = Timestamp.valueOf(LocalDateTime.now());
    }

    public OrderTable(int orderId, int restaurantId, int userId, Timestamp orderTime, double totalAmount, String status, String paymentMode) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.userId = userId;
        
        this.orderTime = (orderTime != null) ? orderTime : Timestamp.valueOf(LocalDateTime.now());
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMode = paymentMode;
    }

    public OrderTable(int restaurantId, int userId, Timestamp orderTime, double totalAmount, String status, String paymentMode) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        // Set orderTime to the provided value or current time if null
        this.orderTime = (orderTime != null) ? orderTime : Timestamp.valueOf(LocalDateTime.now());
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMode = paymentMode;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        // If provided value is null, set current time
        if (orderTime == null) {
            this.orderTime = Timestamp.valueOf(LocalDateTime.now());
        } else {
            this.orderTime = orderTime;
        }
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public String toString() {
        return "OrderTable [orderId=" + orderId + ", restaurantId=" + restaurantId + ", userId=" + userId +
                ", orderTime=" + orderTime + ", totalAmount=" + totalAmount + ", status=" + status +
                ", paymentMode=" + paymentMode + "]";
    }
}
