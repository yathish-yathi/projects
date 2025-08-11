package com.yathish.Order.orderMapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.yathish.Order.dto.OrderDTO;
import com.yathish.Order.entity.Order;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO orderEntityToDTO (Order order);

    Order orderDTOToEntity (OrderDTO orderDTO);

}
