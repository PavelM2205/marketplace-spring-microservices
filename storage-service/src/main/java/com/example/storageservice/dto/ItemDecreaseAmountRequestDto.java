package com.example.storageservice.dto;

import java.util.Map;

public record ItemDecreaseAmountRequestDto (Map<String, ItemDecreaseAmountRequestInfoDto> items) {
}
