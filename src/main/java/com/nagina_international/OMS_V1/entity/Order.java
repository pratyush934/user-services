package com.nagina_international.OMS_V1.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* This is the main module where product information will be entered and stored
 * in the tb_order database table. The module has a id, name, urls, description \
 * and asin numbers assigned to it. Each asin number will have variations that 
 * are captured by SKU numbers.
 * asin and SKU are maintained product wise in seprate tables 
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*
	 * The product name, asin, sku will come from the ProductServiceClient
	 * and a Product DTO. The ProductServiceClient will use a REST template to
	 * call the Product Service which will be a different microservice.
	 */
	private String name;
	private String asin;
	private String sku;
	private Double unitPrice;
	private int quantity;
	private String status;
	private Date orderDate;
}
