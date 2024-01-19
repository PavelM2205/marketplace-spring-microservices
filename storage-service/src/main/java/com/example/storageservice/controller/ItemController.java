package com.example.storageservice.controller;

import com.example.storageservice.dto.ItemCreateEditDto;
import com.example.storageservice.dto.ItemDecreaseAmountRequestDto;
import com.example.storageservice.dto.ItemDecreaseAmountResponseDto;
import com.example.storageservice.dto.ItemReadDto;
import com.example.storageservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ItemReadDto create(@RequestBody ItemCreateEditDto itemDto) {
        return itemService.create(itemDto);
    }

    @GetMapping
    public List<ItemReadDto> getAll() {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ItemReadDto getById(@PathVariable Long id) {
        return itemService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ItemReadDto update(@PathVariable Long id, @RequestBody ItemCreateEditDto itemDto) {
        return itemService.update(id, itemDto).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return itemService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/store")
    public ItemDecreaseAmountResponseDto decreaseAmountForOrder(@RequestBody ItemDecreaseAmountRequestDto requestDto) {
        return itemService.decreaseAmountForOrderDto(requestDto);
    }
}
