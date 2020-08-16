package com.monka.poclogging.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.monka.poclogging.model.Order;

@RestController
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getOrders(){
		List<Order> orders = new ArrayList<>();
		LOGGER.info("Inside /orders; returning list of orders");
		return new ResponseEntity<List<Order>>(new ArrayList<>(), HttpStatus.OK);
	}
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable String orderId){
		Order order = new Order();
		order.setOrtderId(orderId);
		order.setOrderDescription("Sample order");
		order.setOrderDate(new Date().toString());
		LOGGER.info("Inside /orders/{orderId}; returning the order");
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
}
