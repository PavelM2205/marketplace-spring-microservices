package com.example.storageservice.dto;

public record ItemDecreaseAmountRequestInfoDto(Long itemId,
                                               String itemCode,
                                               Integer amount) {
}
