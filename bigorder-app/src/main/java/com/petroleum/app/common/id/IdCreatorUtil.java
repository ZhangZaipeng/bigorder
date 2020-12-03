package com.petroleum.app.common.id;

import com.petroleum.app.common.OrderTypeEnum;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdCreatorUtil {

  Logger logger = LoggerFactory.getLogger(IdCreatorUtil.class);

  // yyyy-MM-dd HH
  private static final SimpleDateFormat SDF = new SimpleDateFormat("yyMMdd");

  public static String getId(OrderTypeEnum recordTypeEnum) {
    String idName = recordTypeEnum.getTransNo();
    Id.newInstance(1);
    String date = SDF.format(new Date());
    return idName + date + Id.nextId();
  }

}
