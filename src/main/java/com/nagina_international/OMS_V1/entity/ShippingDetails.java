package com.nagina_international.OMS_V1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * This is the table where the order shipping details are stored. The shipping
 * details can be clubbed together for a single customer with multiple orders. 
 * This logic will have to built into the shipping module.
 */

@Entity
@Table(name = "tb_shipping")
public class ShippingDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//This will be the order foreign key
	private Long orderId;
	private String name;
	private String address;
	private String email;
	private String phone;	
}
