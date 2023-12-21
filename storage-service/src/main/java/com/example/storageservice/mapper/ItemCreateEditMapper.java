package com.example.storageservice.mapper;

import com.example.storageservice.dto.ItemCreateEditDto;
import com.example.storageservice.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemCreateEditMapper {

    Item fromDto(ItemCreateEditDto itemDto);

    Item updateModel(ItemCreateEditDto itemDto, @MappingTarget Item item);
}
