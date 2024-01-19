package com.example.storageservice.service;

import com.example.storageservice.dto.*;
import com.example.storageservice.mapper.ItemCreateEditMapper;
import com.example.storageservice.mapper.ItemReadMapper;
import com.example.storageservice.model.Item;
import com.example.storageservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemCreateEditMapper itemCreateEditMapper;
    private final ItemReadMapper itemReadMapper;

    public ItemReadDto create(ItemCreateEditDto itemDto) {
        Item item = itemRepository.save(itemCreateEditMapper.fromDto(itemDto));
        return itemReadMapper.fromEntity(item);
    }

    public List<ItemReadDto> findAll() {
        return itemRepository.findAll().stream()
                .map(itemReadMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<ItemReadDto> findById(Long id) {
        return itemRepository.findById(id)
                .map(itemReadMapper::fromEntity);
    }

    public Optional<ItemReadDto> update(Long id, ItemCreateEditDto itemDto) {
        return itemRepository.findById(id)
                .map(item -> itemCreateEditMapper.updateModel(itemDto, item))
                .map(itemRepository::saveAndFlush)
                .map(itemReadMapper::fromEntity);
    }

    public boolean delete(Long id) {
        return itemRepository.findById(id)
                .map(item -> {
                    itemRepository.delete(item);
                    itemRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public ItemDecreaseAmountResponseDto decreaseAmountForOrderDto(ItemDecreaseAmountRequestDto requestDto) {
        List<Item> itemsFromDb = itemRepository.findByItemCodeIn(requestDto.items().keySet());
        List<ItemDecreaseAmountResponseInfoDto> result = new ArrayList<>();
        itemsFromDb.forEach(itFromDb -> {
                    Integer amountInRequest = requestDto.items().get(itFromDb.getItemCode()).amount();
                    if (itFromDb.getAmount() >= amountInRequest) {
                        itFromDb.setAmount(itFromDb.getAmount() - amountInRequest);
                        result.add(getDto(itFromDb, true));
                    } else {
                        result.add(getDto(itFromDb, false));
                    }
        });
        return new ItemDecreaseAmountResponseDto(result);
    }

    private ItemDecreaseAmountResponseInfoDto getDto(Item item, boolean isDecreased) {
        return new ItemDecreaseAmountResponseInfoDto(
                item.getId(),
                item.getItemCode(),
                isDecreased,
                item.getAmount(),
                item.getPrice()
        );
    }
}
