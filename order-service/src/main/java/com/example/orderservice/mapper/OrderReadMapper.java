package com.example.orderservice.mapper;

import com.example.orderservice.dto.ItemInfoDto;
import com.example.orderservice.dto.OrderReadDto;
import com.example.orderservice.model.ItemInfo;
import com.example.orderservice.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderReadMapper {

    OrderReadDto fromEntity(Order order);

    ItemInfoDto fromEntity(ItemInfo itemInfo);
}
