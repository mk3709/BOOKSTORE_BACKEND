package com.bridgelabz.bookstore.order.service;



import com.bridgelabz.bookstore.order.dto.OrderDTO;
import com.bridgelabz.bookstore.order.entity.OrderData;

import java.util.List;

public interface IOrderService {
    OrderData placeOrder(int userId, OrderDTO orderDto);
    String cancelOrder(int orderId, int userId);
    List<OrderData> userOrders(int userId);
}