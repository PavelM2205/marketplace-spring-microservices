package com.example.orderservice.dto;

import java.util.List;

public record OrderCreateResponseDto (OrderReadDto orderReadDto,
                                      List<OrderFailCreateItemDto> failsItems) {
}
