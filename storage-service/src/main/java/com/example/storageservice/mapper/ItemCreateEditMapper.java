package com.example.storageservice.mapper;

import com.example.storageservice.dto.ItemCreateEditDto;
import com.example.storageservice.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemCreateEditMapper {

    @Mapping(target = "itemCode", expression = "java(java.util.UUID.randomUUID().toString())")
    Item fromDto(ItemCreateEditDto itemDto);

    Item updateModel(ItemCreateEditDto itemDto, @MappingTarget Item item);
}
