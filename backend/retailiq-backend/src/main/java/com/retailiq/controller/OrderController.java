package com.retailiq.controller;

import java.util.List;

import com.retailiq.dto.OrderRequest;

import com.retailiq.dto.OrderResponse;

import com.retailiq.service.OrderService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;



@RestController

@RequestMapping("/api/v1/orders")

public class OrderController {



    private final OrderService orderService;



    public OrderController(OrderService orderService) {

        this.orderService = orderService;

    }



    @PostMapping

    public OrderResponse createOrder(

            @Valid @RequestBody OrderRequest request) {



        return orderService.createOrder(request);

    }
    @GetMapping
    public List<OrderResponse> getAllOrders() {

        return orderService.getAllOrders();

    }




}