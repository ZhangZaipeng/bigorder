package com.petroleum.app.mapper;

import com.petroleum.app.config.mybatis.DefaultMapper;
import com.petroleum.app.domain.Order;

public interface OrderMapper extends DefaultMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
