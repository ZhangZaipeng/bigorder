-- 订单
CREATE TABLE `tb_order` (
  `order_id` bigint(11) NOT NULL COMMENT '订单编号',
  `user_id` bigint(11) NOT NULL COMMENT '用户编号',
  `merchant_id` varchar(25) NOT NULL COMMENT '商户编号',

  `order_type` smallint(1) NOT NULL COMMENT '订单类型 1-积分商品 2-拍币商品',
  `order_amt` decimal(10,2) DEFAULT NULL COMMENT '商品金额',
  `order_status` int(5) DEFAULT '0' COMMENT '状态；0=未完成，1=已完成，2=取消，3=退款',
  `order_result` int(5) DEFAULT NULL COMMENT '结果；0=待发货，1=已发货，2=已收货，3=售后/核销',
  `order_is_postage` smallint(1) NOT NULL COMMENT '是否包邮',
  `order_postage_amt` decimal(10,2) DEFAULT NULL COMMENT '包邮金额',

  `score_return` int(11) DEFAULT NULL COMMENT '返还积分',

  `mark` varchar(225) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',

  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_merchant_id` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- 订单 snap 商品 item
CREATE TABLE `tb_order_item` (
  `item_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增长',
  `order_id` bigint(11) NOT NULL COMMENT '订单编号',
  `user_id` bigint(11) NOT NULL COMMENT '用户编号',
  `merchant_id` bigint(11) DEFAULT '0' COMMENT '商户ID',

  `goods_title` varchar(255) DEFAULT NULL COMMENT '标题',
  `cover_img_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `goods_detail` longtext COMMENT '商品详情',
  `market_price` decimal(10,2) DEFAULT NULL COMMENT '市场价格',
  `discount_price` decimal(10,2) DEFAULT NULL COMMENT '优惠价格',
  `bonus_point` int(10) DEFAULT '0' COMMENT '购买商品赠送的积分',

  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`item_id`) ,
  KEY `idx_order_id` (`order_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_merchant_id` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单商品表';


-- 订单 配送地址
CREATE TABLE `tb_order_address` (
  `address_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增长',
  `order_id` bigint(11) NOT NULL COMMENT '订单编号',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `merchant_id` varchar(25) NOT NULL COMMENT '商户编号',

  `receipt_name` varchar(50) DEFAULT NULL COMMENT '收件人姓名',
  `phone_num` varchar(50) DEFAULT NULL COMMENT '电话号码',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `area` varchar(50) DEFAULT NULL COMMENT '区',
  `detailed_address` varchar(100) DEFAULT NULL COMMENT '详情地址',

  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`address_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_merchant_id` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单 配送地址';


-- 订单 支付信息
CREATE TABLE `tb_order_pay` (
  `pay_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增长',
  `order_id` bigint(11) NOT NULL COMMENT '订单编号',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `merchant_id` varchar(25) NOT NULL COMMENT '商户编号',

  `pay_score_amt` decimal(10,2) DEFAULT NULL COMMENT '支付积分',
  `pay_actual_amt` decimal(10,2) DEFAULT NULL COMMENT '实际支付金额',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `pay_opt` varchar(10) DEFAULT NULL COMMENT '支付方式 1-微信 2-支付宝 3-积分',
  `pay_bus_id` varchar(50) DEFAULT NULL COMMENT '支付宝/微信交易流水信息',
  `pay_back_msg` varchar(1000) DEFAULT NULL COMMENT '支付回调信息',
  `pay_finished` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否支付 0-否 1-是 2-超时取消',

  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`pay_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_merchant_id` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='订单支付表';

