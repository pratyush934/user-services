package com.nagina_international.OMS_V1.service;

import java.util.List;

import com.nagina_international.OMS_V1.entity.Order;

public interface OrderService {

	List<Order> getAllOrders();

	Order saveOrder(Order order);

}
