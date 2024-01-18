package com.example.storageservice.dto;

import java.math.BigDecimal;

public record ItemCreateEditDto (String name,
                                 BigDecimal price,
                                 String description,
                                 Integer amount) {
}
