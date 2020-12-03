package com.petroleum.app.service;

import com.petroleum.app.domain.Order;
import com.petroleum.app.domain.OrderAddress;
import com.petroleum.app.domain.OrderItem;
import com.petroleum.app.domain.OrderPay;

/**
 * @Description ：
 * @Tauthor ZhangZaipeng
 * @Tdata 2020/10/27   20:02
 */
public interface OrderService {
  Order produceData();

  Order produceOrder();

  OrderAddress produceOrderAddress(Order order);

  OrderItem produceOrderItem(Order order);

  OrderPay produceOrderPay(Order order);

  Order selectOrderById(String orderId);
}
