package com.example.orderservice.dto;

import java.math.BigDecimal;

public record OrderFailCreateItemDto (Long itemId,
                                      String itemCode,
                                      Integer requestCount,
                                      Integer restCount,
                                      BigDecimal price) {
}
