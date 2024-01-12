package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderCreateDto;
import com.example.orderservice.dto.OrderReadDto;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderReadDto create(@RequestBody OrderCreateDto orderDto) {
        return orderService.create(orderDto);
    }

    @GetMapping
    public List<OrderReadDto> getAll() {
        return orderService.findAll();
    }
}
