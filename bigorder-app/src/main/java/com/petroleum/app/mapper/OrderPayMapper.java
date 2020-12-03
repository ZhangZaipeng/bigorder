package com.petroleum.app.mapper;

import com.petroleum.app.config.mybatis.DefaultMapper;
import com.petroleum.app.domain.OrderPay;

public interface OrderPayMapper extends DefaultMapper {
    int deleteByPrimaryKey(Long payId);

    int insert(OrderPay record);

    int insertSelective(OrderPay record);

    OrderPay selectByPrimaryKey(Long payId);

    int updateByPrimaryKeySelective(OrderPay record);

    int updateByPrimaryKey(OrderPay record);
}
