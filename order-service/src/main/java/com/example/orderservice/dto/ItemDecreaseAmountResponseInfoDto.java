package com.example.orderservice.dto;

import java.math.BigDecimal;

public record ItemDecreaseAmountResponseInfoDto(Long itemId,
                                                String itemCode,
                                                Boolean isDecreased,
                                                Integer rest,
                                                BigDecimal price) {
}
