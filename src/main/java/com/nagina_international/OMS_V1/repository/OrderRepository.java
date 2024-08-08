package com.nagina_international.OMS_V1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagina_international.OMS_V1.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
