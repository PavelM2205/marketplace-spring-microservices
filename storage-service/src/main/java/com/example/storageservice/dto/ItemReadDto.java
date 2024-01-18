package com.example.storageservice.dto;

import java.math.BigDecimal;

public record ItemReadDto (Long id,
                           String itemCode,
                           String name,
                           BigDecimal price,
                           Integer amount,
                           String description) {
}
