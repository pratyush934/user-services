package com.nagina_international.OMS_V1.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * This class will be the product class that we will need for our order service.
 * The class will use a ProductServiceClient to fetch details from the Product 
 * microservice that we need here. The fields that will be needed are product 
 * name and asin for a product whenever a user searches using sku.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
	private String genericName;
	private String productASIN;
	private String productSKU;
	private Double unitPrice;
}
