package com.springboot.brokagefirm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.springboot.brokagefirm.entity.Order;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomerIdAndCreatedTimeBetween(String customerId, LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByCustomerIdAndStatus(String customerId, Order.Status status);
}
