package com.example.orderservice.mapper;

import com.example.orderservice.dto.ItemInfoDto;
import com.example.orderservice.dto.OrderCreateDto;
import com.example.orderservice.model.ItemInfo;
import com.example.orderservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderCreateMapper {

    @Mapping(target = "created", expression = "java(java.time.LocalDateTime.now())")
    Order fromDto(OrderCreateDto orderDto);

    ItemInfo fromDto(ItemInfoDto itemInfoDto);
}
