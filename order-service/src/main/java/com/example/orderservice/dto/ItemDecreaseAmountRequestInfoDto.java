package com.example.orderservice.dto;

public record ItemDecreaseAmountRequestInfoDto(Long itemId,
                                               String itemCode,
                                               Integer amount) {
}
