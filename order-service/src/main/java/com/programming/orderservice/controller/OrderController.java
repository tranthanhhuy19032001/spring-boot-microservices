package com.programming.orderservice.controller;

import com.programming.orderservice.dto.OrderRequest;
import org.springframework.http.ResponseEntity;
import com.programming.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            return ResponseEntity.ok(orderService.placeOrder(orderRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Product is not in stock, please try again later");
        }
    }
}