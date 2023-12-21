package com.example.storageservice.dto;

public record ItemReadDto (Long id,
                           String name,
                           double price,
                           int amount) {
}
