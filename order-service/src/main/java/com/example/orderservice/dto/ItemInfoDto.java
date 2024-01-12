package com.example.orderservice.dto;

import java.math.BigDecimal;

public record ItemInfoDto(Long itemId,
                          int count,
                          BigDecimal price) {
}
