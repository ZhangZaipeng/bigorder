package com.petroleum.app.mapper;

import com.petroleum.app.config.mybatis.DefaultMapper;
import com.petroleum.app.domain.OrderItem;

public interface OrderItemMapper extends DefaultMapper {
    int deleteByPrimaryKey(Long itemId);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}
