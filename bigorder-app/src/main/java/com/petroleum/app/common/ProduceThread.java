package com.petroleum.app.common;

import com.petroleum.app.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description ：
 * @Tauthor ZhangZaipeng
 * @Tdata 2020/10/27   21:54
 */
public class ProduceThread implements Runnable {

  private final Logger logger = LoggerFactory.getLogger(ProduceThread.class);

  private OrderService orderService;

  public ProduceThread(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public void run() {
    orderService.produceData();
  }
}
