package com.example.storageservice.dto;

public record ItemDecreaseAmountResponseInfoDto(Long itemId,
                                                String itemCode,
                                                Boolean isDecreased,
                                                Integer rest) {
}
