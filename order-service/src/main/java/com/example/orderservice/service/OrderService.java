package com.example.orderservice.service;

import com.example.orderservice.dto.OrderCreateDto;
import com.example.orderservice.dto.OrderReadDto;
import com.example.orderservice.mapper.OrderCreateMapper;
import com.example.orderservice.mapper.OrderReadMapper;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderCreateMapper orderCreateMapper;
    private final OrderReadMapper orderReadMapper;


    public OrderReadDto create(OrderCreateDto orderDto) {
        return orderReadMapper.fromEntity(
                orderRepository.save(orderCreateMapper.fromDto(orderDto)));
    }

    public List<OrderReadDto> findAll() {
        return orderRepository.findAll().stream()
                .map(orderReadMapper::fromEntity)
                .collect(Collectors.toList());
    }
}
