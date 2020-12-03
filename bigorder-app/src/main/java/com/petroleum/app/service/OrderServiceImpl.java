package com.petroleum.app.service;

import com.github.javafaker.Faker;
import com.petroleum.app.common.Conv;
import com.petroleum.app.common.id.IdCreatorUtil;
import com.petroleum.app.domain.Order;
import com.petroleum.app.domain.OrderAddress;
import com.petroleum.app.domain.OrderItem;
import com.petroleum.app.domain.OrderPay;
import com.petroleum.app.mapper.OrderAddressMapper;
import com.petroleum.app.mapper.OrderItemMapper;
import com.petroleum.app.mapper.OrderMapper;
import com.petroleum.app.mapper.OrderPayMapper;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.petroleum.app.common.OrderTypeEnum;

/**
 * @Description ：
 * @Tauthor ZhangZaipeng
 * @Tdata 2020/10/27   20:02
 */
@Service
public class OrderServiceImpl implements OrderService {

  private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

  @Autowired
  private OrderMapper orderMapper;

  @Autowired
  private OrderItemMapper orderItemMapper;

  @Autowired
  private OrderAddressMapper orderAddressMapper;

  @Autowired
  private OrderPayMapper orderPayMapper;

  private Faker faker = new Faker(new Locale("zh-CN"));;

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Order produceData() {

    Order order = produceOrder();
    int i = orderMapper.insertSelective(order);
    if (i <= 0) {
      logger.error("produceOrder error");
      throw new RuntimeException("orderMapper.insertSelective error");
    }

    OrderAddress orderAddress = produceOrderAddress(order);
    int i1 = orderAddressMapper.insertSelective(orderAddress);
    if (i1 <= 0) {
      logger.error("produceOrderAddress error");
      throw new RuntimeException("orderAddressMapper.insertSelective error");
    }

    OrderItem orderItem = produceOrderItem(order);
    int i2 = orderItemMapper.insertSelective(orderItem);
    if (i2 <= 0) {
      logger.error("produceOrderItem error");
      throw new RuntimeException("orderItemMapper.insertSelective error");
    }

    OrderPay orderPay = produceOrderPay(order);
    int i3 = orderPayMapper.insertSelective(orderPay);
    if (i3 <= 0) {
      logger.error("produceOrderPay error");
      throw new RuntimeException("orderPayMapper.insertSelective error");
    }

    return order;
  }

  @Override
  public Order produceOrder() {
    String orderId = IdCreatorUtil.getId(OrderTypeEnum.RECHARGE);
    Long userId = faker.number().numberBetween(9000001L, 9900000L);
    Long merchantId = faker.number().numberBetween(700001L, 790000L);
    short order_type = Conv.NShort(faker.number().numberBetween(1, 4));
    BigDecimal order_amt = Conv.NDec(faker.number().numberBetween(50, 1000));
    int order_status = faker.number().numberBetween(0, 3);
    int order_result = faker.number().numberBetween(0, 3);
    short order_is_postage = Conv.NShort(faker.number().numberBetween(0, 1));
    BigDecimal order_postage_amt = Conv.NDec(faker.number().numberBetween(10, 100));
    int score_return = faker.number().numberBetween(50, 500);
    String mark = "备注";

    Order order = new Order();
    order.setOrderId(orderId);
    order.setUserId(userId);
    order.setMerchantId(merchantId);
    order.setOrderType(order_type);
    order.setOrderAmt(order_amt);
    order.setOrderStatus(order_status);
    order.setOrderResult(order_result);
    order.setOrderIsPostage(order_is_postage);
    order.setOrderPostageAmt(order_postage_amt);
    order.setScoreReturn(score_return);
    order.setMark(mark);

    return order;
  }

  @Override
  public OrderAddress produceOrderAddress(Order order) {
    String receipt_name = faker.name().fullName();
    String phone_num = faker.phoneNumber().cellPhone();
    String province = faker.address().city();
    String city = faker.address().city();
    String area = faker.address().city();
    String detailed_address = faker.address().fullAddress();

    OrderAddress orderAddress = new OrderAddress();
    orderAddress.setOrderId(order.getOrderId());
    orderAddress.setUserId(order.getUserId());
    orderAddress.setMerchantId(order.getMerchantId());
    orderAddress.setReceiptName(receipt_name);
    orderAddress.setPhoneNum(phone_num);
    orderAddress.setProvince(province);
    orderAddress.setCity(city);
    orderAddress.setArea(area);
    orderAddress.setDetailedAddress(detailed_address);

    return orderAddress;
  }

  @Override
  public OrderItem produceOrderItem(Order order) {

    String goods_title = faker.music().instrument();
    String cover_img_url = faker.avatar().image();
    String goods_detail = faker.book().title();
    BigDecimal market_price = Conv.NDec(faker.number().numberBetween(50, 1000));
    BigDecimal discount_price = Conv.NDec(faker.number().numberBetween(50, 1000));
    int bonus_point = faker.number().numberBetween(50, 500);

    OrderItem orderItem = new OrderItem();
    orderItem.setOrderId(order.getOrderId());
    orderItem.setUserId(order.getUserId());
    orderItem.setMerchantId(order.getMerchantId());

    orderItem.setGoodsTitle(goods_title);
    orderItem.setCoverImgUrl(cover_img_url);
    orderItem.setGoodsDetail(goods_detail);
    orderItem.setMarketPrice(market_price);
    orderItem.setDiscountPrice(discount_price);
    orderItem.setBonusPoint(bonus_point);

    return orderItem;
  }

  @Override
  public OrderPay produceOrderPay(Order order) {

    BigDecimal pay_score_amt = Conv.NDec(faker.number().numberBetween(50, 1000));
    BigDecimal pay_actual_amt = Conv.NDec(faker.number().numberBetween(50, 1000));
    String pay_opt = Conv.NS(faker.number().numberBetween(0, 5));
    String pay_bus_id = Conv.NS(faker.number().randomNumber());
    String pay_back_msg = Conv.NS(faker.artist().name());
    short pay_finished = Conv.NShort(faker.number().numberBetween(0, 2));

    OrderPay orderPay = new OrderPay();
    orderPay.setOrderId(order.getOrderId());
    orderPay.setUserId(order.getUserId());
    orderPay.setMerchantId(order.getMerchantId());
    orderPay.setPayScoreAmt(pay_score_amt);
    orderPay.setPayActualAmt(pay_actual_amt);
    orderPay.setPayOpt(pay_opt);
    orderPay.setPayBusId(pay_bus_id);
    orderPay.setPayBackMsg(pay_back_msg);
    orderPay.setPayFinished(pay_finished);
    orderPay.setPayTime(new Date());

    return orderPay;
  }

  @Override
  public Order selectOrderById(String orderId) {
    return orderMapper.selectByPrimaryKey(orderId);
  }

}
