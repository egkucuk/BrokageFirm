package com.springboot.brokagefirm.controller;

import com.springboot.brokagefirm.entity.Asset;
import com.springboot.brokagefirm.entity.Order;
import com.springboot.brokagefirm.service.AssetService;
import com.springboot.brokagefirm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/brokagefirm")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AssetService assetService;

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/orders")
    public List<Order> listOrders(@RequestParam String customerId,
                                  @RequestParam LocalDateTime startDate,
                                  @RequestParam LocalDateTime endDate) {
        return orderService.listOrders(customerId, startDate, endDate);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customers/{customerId}/assets")
    public ResponseEntity<List<Asset>> listAssets(@PathVariable String customerId) {
        List<Asset> assets = assetService.listAssets(customerId);
        return ResponseEntity.ok(assets);
    }

    @PostMapping("/{customerId}/deposit")
    public ResponseEntity<String> depositMoney(@PathVariable String customerID,
            @RequestParam BigDecimal amount) {
        assetService.depositMoney(customerID, amount);
        return ResponseEntity.ok("Deposit successful.");
    }

    @PostMapping("/{customerId}/withdraw")
    public ResponseEntity<String> withdrawMoney(@PathVariable String customerId,
            @RequestParam BigDecimal amount, @RequestParam String iban) {
        assetService.withdrawMoney(customerId, amount, iban);
        return ResponseEntity.ok("Withdrawal successful from " + iban);
    }
}