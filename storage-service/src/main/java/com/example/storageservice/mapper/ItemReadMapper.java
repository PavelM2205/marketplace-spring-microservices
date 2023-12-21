package com.example.storageservice.mapper;

import com.example.storageservice.dto.ItemReadDto;
import com.example.storageservice.model.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemReadMapper {

    ItemReadDto fromEntity(Item item);
}
