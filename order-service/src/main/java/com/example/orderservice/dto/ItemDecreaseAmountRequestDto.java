package com.example.orderservice.dto;

import java.util.Map;

public record ItemDecreaseAmountRequestDto (Map<String, ItemDecreaseAmountRequestInfoDto> items) {
}
