package com.example.orderservice.dto;

import java.math.BigDecimal;

public record ItemInfoDto(Long itemId,
                          String itemCode,
                          Integer count,
                          BigDecimal price) {
}
