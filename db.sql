CREATE TABLE `yrzy_users` (
  `id`          BIGINT(20)    NOT NULL AUTO_INCREMENT,
  `nick_name`   VARCHAR(64)   NOT NULL COMMENT '用户昵称',
  `real_name`   VARCHAR(64)   NOT NULL COMMENT '用户真实姓名',
  `password`    VARCHAR(128)  NOT NULL COMMENT '用户密码',
  `phone`       VARCHAR(32)   NOT NULL COMMENT '手机',
  `type`        SMALLINT      NOT NULL COMMENT '用户类型 0->管理员 1->买家 2->卖家',
  `status`      SMALLINT      NOT NULL COMMENT '用户状态 0->未激活 1->正常 -1->冻结',
  `created_at`  DATETIME      NOT NULL COMMENT '创建时间',
  `updated_at`  DATETIME      NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
);


CREATE TABLE `yrzy_shops` (
  `id`          BIGINT(20)    NOT NULL AUTO_INCREMENT,
  `user_id`     BIGINT(20)    NOT NULL COMMENT '商家id',
  `user_name`   VARCHAR(128)  NOT NULL COMMENT '商家名称',
  `shop_name`   VARCHAR(128)  NOT NULL COMMENT '店铺名称',
  `status`      SMALLINT      NOT NULL COMMENT '店铺状态 0->初始 1->正常 -1->冻结 -2->申请失败',
  `phone`       VARCHAR(32)   NULL     COMMENT '店铺移动电话',
  `telephone`   VARCHAR(32)   NULL     COMMENT '店铺固定电话',
  `iamge_url`   VARCHAR(128)  NULL     COMMENT '店铺主图',
  `address`     VARCHAR(128)  NOT NULL COMMENT '店铺地址',
  `created_at`  DATETIME      NOT NULL COMMENT '创建时间',
  `updated_at`  DATETIME      NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
);
CREATE INDEX idx_yrzy_shops_user_id ON yrzy_shops(`user_id`);



CREATE TABLE `yrzy_businesses` (
  `id`          BIGINT(20)    NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(64)   NOT NULL COMMENT '生意名称',
  `shop_id`     BIGINT(20)    NOT NULL COMMENT '生意所属的店铺id',
  `shop_name`   VARCHAR(64)   NOT NULL COMMENT '生意所属的店铺名称',
  `user_id`     BIGINT(20)    NOT NULL COMMENT '商家id',
  `user_name`   BIGINT(20)    NOT NULL COMMENT '商家名称',
  `discount_info` VARCHAR(1024) NOT NULL COMMENT '生意优惠内容',
  `status`      SMALLINT      NOT NULL COMMENT '生意的状态 0->未上架 1->上架 -1->下架 -2->删除',
  `main_iamge`  VARCHAR(128)  NOT NULL COMMENT '描述图片',
  `created_at`  DATETIME      NOT NULL ,
  `updated_at`  DATETIME      NOT NULL ,
  PRIMARY KEY (`id`)
);
CREATE INDEX yrzy_business_user_id ON yrzy_businesses(`user_id`);




CREATE TABLE `yrzy_orders` (
  `id`          BIGINT(20)    NOT NULL AUTO_INCREMENT,
  `shop_id`     BIGINT(20)    NOT NULL COMMENT '店铺id',
  `shop_name`   VARCHAR(128)  NOT NULL COMMENT '店铺名称',
  `seller_id`   BIGINT(20)    NOT NULL COMMENT '商家id',
  `seller_name` VARCHAR(64)   NOT NULL COMMENT '商家名称',
  `buyer_id`    BIGINT(20)    NOT NULL COMMENT '买家id',
  `buyer_name`  VARCHAR(64)   NOT NULL COMMENT '买家名称',
  `business_id` BIGINT(20)    NOT NULL COMMENT '生意id',
  `business_name` VARCHAR(128)  NOT NULL COMMENT '生意名称',
  `created_at`  DATETIME      NOT NULL,
  `updated_at`  DATETIME      NOT NULL ,
  PRIMARY KEY (`id`)
);