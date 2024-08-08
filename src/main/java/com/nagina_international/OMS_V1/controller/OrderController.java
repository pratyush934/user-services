package com.nagina_international.OMS_V1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagina_international.OMS_V1.entity.Order;
import com.nagina_international.OMS_V1.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders = orderService.getAllOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Order> createNewOrder(@RequestBody Order order) {
		orderService.saveOrder(order);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
}
