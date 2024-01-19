package com.example.orderservice.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderReadDto(Long id,
                           List<ItemInfoDto> items,
                           LocalDateTime created,
                           boolean isPaid) {
}
