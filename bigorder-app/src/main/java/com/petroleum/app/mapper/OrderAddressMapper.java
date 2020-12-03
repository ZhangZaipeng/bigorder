package com.petroleum.app.mapper;

import com.petroleum.app.config.mybatis.DefaultMapper;
import com.petroleum.app.domain.OrderAddress;

public interface OrderAddressMapper extends DefaultMapper {
    int deleteByPrimaryKey(Long addressId);

    int insert(OrderAddress record);

    int insertSelective(OrderAddress record);

    OrderAddress selectByPrimaryKey(Long addressId);

    int updateByPrimaryKeySelective(OrderAddress record);

    int updateByPrimaryKey(OrderAddress record);
}
