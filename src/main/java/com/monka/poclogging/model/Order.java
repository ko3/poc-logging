package com.monka.poclogging.model;

import lombok.Data;

@Data
public class Order {
	private String ortderId;
	private String orderDescription;
	private String orderDate;
}
