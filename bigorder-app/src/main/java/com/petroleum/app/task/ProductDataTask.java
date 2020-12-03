package com.petroleum.app.task;

import com.petroleum.app.common.Conv;
import com.petroleum.app.common.ExecutorUtils;
import com.petroleum.app.common.ProduceThread;
import com.petroleum.app.domain.SystemConfig;
import com.petroleum.app.mapper.SystemConfigMapper;
import com.petroleum.app.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ProductDataTask {

  private static final Logger logger = LoggerFactory.getLogger(ProductDataTask.class);

  @Autowired
  private OrderService orderService;

  @Autowired
  private SystemConfigMapper systemConfigMapper;

  private static boolean isRunning = false;

  @Scheduled(cron = "1/5 * * * * ?")
  public void execute() {
    if (isRunning) {
      return;
    }
    isRunning = true;

    try {
      SystemConfig systemConfig = systemConfigMapper.selectByPrimaryKey("key");
      String value = systemConfig.getRemark1();
      int j = Conv.NI(value);

      logger.info("【ProductDataTask start】 j => {}", j);

      for (int i = 0; i < j; i++) {
        ProduceThread produceThread = new ProduceThread(orderService);
        ExecutorUtils.executeTask(produceThread);
      }

      ExecutorUtils.poolInfo();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      isRunning = false;
    }
  }
}
