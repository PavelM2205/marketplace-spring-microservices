package com.example.orderservice.service;

import com.example.orderservice.dto.*;
import com.example.orderservice.mapper.OrderReadMapper;
import com.example.orderservice.model.ItemInfo;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderReadMapper orderReadMapper;
    private final WebClient.Builder webClientBuilder;

    @Value("${storage.service.uri}")
    private String storageServiceUri;

    @Transactional
    public OrderCreateResponseDto create(OrderCreateDto orderDto) {
        Map<String, ItemDecreaseAmountRequestInfoDto> map = getMapForRequest(orderDto);

        ItemDecreaseAmountResponseDto response = webClientBuilder.build()
                .put()
                .uri(storageServiceUri + "/items/store")
                .bodyValue(new ItemDecreaseAmountRequestDto(map))
                .retrieve()
                .bodyToMono(ItemDecreaseAmountResponseDto.class)
                .block();

        List<ItemInfo> successList = response.items().stream()
                .filter(ItemDecreaseAmountResponseInfoDto::isDecreased)
                .map(dto -> getItemInfo(dto, map.get(dto.itemCode()).amount()))
                .toList();

        List<OrderFailCreateItemDto> failList = response.items().stream()
                .filter(dto -> !dto.isDecreased())
                .map(dto -> getOrderFailCreateItemDto(dto, map.get(dto.itemCode()).amount()))
                .toList();

        Order order = Order.builder()
                .orderCode(UUID.randomUUID().toString())
                .items(successList)
                .created(LocalDateTime.now())
                .isPaid(false)
                .build();
        orderRepository.save(order);

        return new OrderCreateResponseDto(
                orderReadMapper.fromEntity(order),
                failList
        );
    }

    private Map<String, ItemDecreaseAmountRequestInfoDto> getMapForRequest(OrderCreateDto orderDto) {
        return orderDto.items().stream()
                .map(it ->
                        new ItemDecreaseAmountRequestInfoDto(
                                it.itemId(),
                                it.itemCode(),
                                it.count()
                        ))
                .collect(Collectors.toMap(
                        ItemDecreaseAmountRequestInfoDto::itemCode,
                        it -> it));
    }

    private ItemInfo getItemInfo(ItemDecreaseAmountResponseInfoDto dto, Integer count) {
        return ItemInfo.builder()
                .itemId(dto.itemId())
                .itemCode(dto.itemCode())
                .count(count)
                .price(dto.price())
                .build();
    }

    private OrderFailCreateItemDto getOrderFailCreateItemDto(ItemDecreaseAmountResponseInfoDto dto, Integer requestCount) {
        return new OrderFailCreateItemDto(
                dto.itemId(),
                dto.itemCode(),
                requestCount,
                dto.rest(),
                dto.price()
        );
    }

    @Transactional(readOnly = true)
    public List<OrderReadDto> findAll() {
        return orderRepository.findAll().stream()
                .map(orderReadMapper::fromEntity)
                .collect(Collectors.toList());
    }
}
