package com.example.orderservice.dto;

public record ItemInfoDto(Long itemId,
                          String itemCode,
                          Integer count) {
}
