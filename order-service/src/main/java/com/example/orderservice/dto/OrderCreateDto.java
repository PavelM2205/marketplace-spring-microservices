package com.example.orderservice.dto;

import java.util.List;

public record OrderCreateDto(List<ItemInfoDto> items,
                             Long customerId) {
}
