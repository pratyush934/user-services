package com.nagina_international.OMS_V1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RestController
    @RequestMapping("/api")
    public static class TestController {

        @GetMapping("/test")
        public String getTest() {
            return "Test";
        }

        @GetMapping("/test2")
        public String getTest2() {
            return "Test2";
        }
    }
}
