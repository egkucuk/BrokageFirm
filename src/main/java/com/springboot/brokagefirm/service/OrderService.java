package com.springboot.brokagefirm.service;

import com.springboot.brokagefirm.repository.OrderRepository;
import com.springboot.brokagefirm.repository.AssetRepository;
import com.springboot.brokagefirm.entity.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AssetRepository assetRepository;

    public Order createOrder(Order order) {
        order.setCreatedTime(LocalDateTime.now());
        order.setStatus(Order.Status.PENDING);
        return orderRepository.save(order);
    }

    public List<Order> listOrders(String customerId, LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByCustomerIdAndCreatedTimeBetween(customerId,startDate,endDate);
    }

    public void deleteOrder (Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        if(order.getStatus().equals(Order.Status.PENDING)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new RuntimeException("The status is " + order.getStatus() + ". Only PENDING orders can be deleted.");
        }
    }

}
