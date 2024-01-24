package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderCreateDto;
import com.example.orderservice.dto.OrderCreateResponseDto;
import com.example.orderservice.dto.OrderReadDto;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderCreateResponseDto create(@RequestBody OrderCreateDto orderDto) {
        return orderService.create(orderDto);
    }

    @GetMapping
    public List<OrderReadDto> getAll() {
        return orderService.findAll();
    }
}
