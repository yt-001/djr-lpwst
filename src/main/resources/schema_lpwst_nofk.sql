SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS favorites;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS accommodation_facility_relations;
DROP TABLE IF EXISTS accommodations;
DROP TABLE IF EXISTS accommodation_facilities;
DROP TABLE IF EXISTS accommodation_types;
DROP TABLE IF EXISTS attractions;
DROP TABLE IF EXISTS intangible_culture;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE accommodation_facilities
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    description TEXT NULL,
    icon        VARCHAR(255) NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NULL,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT uk_accommodation_facilities_name UNIQUE (name)
)
COLLATE = utf8mb4_unicode_ci;

CREATE TABLE accommodation_types
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    description TEXT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NULL,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT uk_accommodation_types_name UNIQUE (name)
)
COLLATE = utf8mb4_unicode_ci;

CREATE TABLE accommodations
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(100) NOT NULL COMMENT '住宿名称',
    description     TEXT NULL COMMENT '描述',
    type_id         INT NULL,
    location        VARCHAR(255) NULL COMMENT '地理位置',
    latitude        DECIMAL(10, 8) NULL COMMENT '纬度',
    longitude       DECIMAL(11, 8) NULL COMMENT '经度',
    cover_image     VARCHAR(255) NULL COMMENT '封面图片',
    images          TEXT NULL COMMENT '图片JSON数组',
    price_per_night DECIMAL(10, 2) NULL COMMENT '每晚价格',
    capacity        INT NULL COMMENT '可容纳人数',
    contact_phone   VARCHAR(20) NULL COMMENT '联系电话',
    create_time     DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    update_time     DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
)
COMMENT '住宿表' COLLATE = utf8mb4_unicode_ci;

CREATE TABLE accommodation_facility_relations
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    accommodation_id INT NOT NULL,
    facility_id      INT NOT NULL,
    create_time      DATETIME DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT uk_acc_fac UNIQUE (accommodation_id, facility_id)
)
COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_accommodations_location ON accommodations (location);
CREATE INDEX idx_accommodations_type_id  ON accommodations (type_id);
CREATE INDEX idx_af_rel_acc ON accommodation_facility_relations (accommodation_id);
CREATE INDEX idx_af_rel_fac ON accommodation_facility_relations (facility_id);

CREATE TABLE attractions
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(100) NOT NULL COMMENT '景点名称',
    description   TEXT NULL COMMENT '景点描述',
    location      VARCHAR(255) NULL COMMENT '地理位置',
    latitude      DECIMAL(10, 8) NULL COMMENT '纬度',
    longitude     DECIMAL(11, 8) NULL COMMENT '经度',
    cover_image   VARCHAR(255) NULL COMMENT '封面图片',
    images        TEXT NULL COMMENT '图片JSON数组',
    open_hours    VARCHAR(100) NULL COMMENT '开放时间',
    ticket_price  DECIMAL(10, 2) NULL COMMENT '门票价格',
    contact_phone VARCHAR(20) NULL COMMENT '联系电话',
    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    update_time   DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
)
COMMENT '景点表' COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_attractions_location ON attractions (location);

CREATE TABLE intangible_culture
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL COMMENT '非遗项目名称',
    description TEXT NULL COMMENT '描述',
    type        VARCHAR(50) NULL COMMENT '非遗类型',
    inheritor   VARCHAR(100) NULL COMMENT '传承人',
    cover_image VARCHAR(255) NULL COMMENT '封面图片',
    images      TEXT NULL COMMENT '图片JSON数组',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
)
COMMENT '非物质文化表' COLLATE = utf8mb4_unicode_ci;

CREATE TABLE restaurants
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(100) NOT NULL COMMENT '餐厅/美食名称',
    description   TEXT NULL COMMENT '描述',
    location      VARCHAR(255) NULL COMMENT '地理位置',
    latitude      DECIMAL(10, 8) NULL COMMENT '纬度',
    longitude     DECIMAL(11, 8) NULL COMMENT '经度',
    cover_image   VARCHAR(255) NULL COMMENT '封面图片',
    images        TEXT NULL COMMENT '图片JSON数组',
    open_hours    VARCHAR(100) NULL COMMENT '营业时间',
    price_range   VARCHAR(50) NULL COMMENT '价格区间(如:￥50-100)',
    specialty     VARCHAR(100) NULL COMMENT '招牌菜',
    contact_phone VARCHAR(20) NULL COMMENT '联系电话',
    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    update_time   DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    rating        DECIMAL(2, 1) NULL COMMENT '推荐指数(0-5)'
)
COMMENT '美食表' COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_restaurants_location ON restaurants (location);
CREATE INDEX idx_restaurants_rating   ON restaurants (rating);

CREATE TABLE users
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(50) NOT NULL COMMENT '用户名',
    password_hash VARCHAR(255) NOT NULL COMMENT '密码',
    email         VARCHAR(100) NOT NULL COMMENT '邮箱',
    phone         VARCHAR(20) NULL COMMENT '手机号',
    avatar_url    VARCHAR(255) NULL COMMENT '头像URL',
    status        TINYINT(1) DEFAULT 1 NOT NULL COMMENT '状态(0-禁用,1-启用)',
    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    update_time   DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    role          TINYINT(1) DEFAULT 1 NOT NULL COMMENT '角色(0-管理员,1-普通用户)',
    CONSTRAINT email UNIQUE (email),
    CONSTRAINT username UNIQUE (username)
)
COMMENT '用户表' COLLATE = utf8mb4_unicode_ci;

CREATE TABLE comments
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    user_id          INT NOT NULL COMMENT '用户ID',
    attraction_id    INT NULL COMMENT '景点ID',
    restaurant_id    INT NULL COMMENT '美食ID',
    culture_id       INT NULL COMMENT '非遗ID',
    accommodation_id INT NULL COMMENT '住宿ID',
    content          TEXT NOT NULL COMMENT '评论内容',
    rating           INT NULL COMMENT '评分(1-5星)',
    is_approved      TINYINT(1) DEFAULT 0 NULL COMMENT '是否审核通过(0-否,1-是)',
    create_time      DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    update_time      DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    CHECK ((`rating` >= 1) AND (`rating` <= 5))
)
COMMENT '评论表' COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_comments_user_id  ON comments (user_id);
CREATE INDEX idx_comments_rating    ON comments (rating);
CREATE INDEX idx_comments_attract   ON comments (attraction_id);
CREATE INDEX idx_comments_rest      ON comments (restaurant_id);
CREATE INDEX idx_comments_culture   ON comments (culture_id);
CREATE INDEX idx_comments_acc       ON comments (accommodation_id);

CREATE TABLE favorites
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    user_id          INT NOT NULL COMMENT '用户ID',
    attraction_id    INT NULL COMMENT '景点ID',
    restaurant_id    INT NULL COMMENT '美食ID',
    culture_id       INT NULL COMMENT '非遗ID',
    accommodation_id INT NULL COMMENT '住宿ID',
    create_time      DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    update_time      DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
)
COMMENT '收藏表' COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_favorites_user_id ON favorites (user_id);
CREATE INDEX idx_favorites_attract ON favorites (attraction_id);
CREATE INDEX idx_favorites_rest    ON favorites (restaurant_id);
CREATE INDEX idx_favorites_culture ON favorites (culture_id);
CREATE INDEX idx_favorites_acc     ON favorites (accommodation_id);

CREATE TABLE orders
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    order_no     VARCHAR(32) NOT NULL COMMENT '订单编号',
    user_id      INT NOT NULL COMMENT '用户ID',
    product_type TINYINT(1) NOT NULL COMMENT '产品类型(1-景点门票,2-美食消费券,3-住宿消费券)',
    product_id   INT NOT NULL COMMENT '产品ID(根据product_type关联不同表)',
    product_name VARCHAR(100) NOT NULL COMMENT '产品名称',
    description  VARCHAR(500) NULL COMMENT '消费描述(如:住宿套餐类型/美食具体菜品等)',
    quantity     INT DEFAULT 1 NOT NULL COMMENT '数量',
    unit_price   DECIMAL(10, 2) NOT NULL COMMENT '单价',
    total_amount DECIMAL(10, 2) NOT NULL COMMENT '总金额',
    status       TINYINT(1) DEFAULT 0 NOT NULL COMMENT '订单状态(0-待支付,1-已支付,2-已使用,3-已取消,4-已退款)',
    payment_time DATETIME NULL COMMENT '支付时间',
    used_time    DATETIME NULL COMMENT '使用时间',
    expire_time  DATETIME NULL COMMENT '过期时间',
    create_time  DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    update_time  DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT idx_orders_order_no UNIQUE (order_no)
)
COMMENT '订单表' COLLATE = utf8mb4_unicode_ci;

CREATE INDEX idx_orders_create_time ON orders (create_time);
CREATE INDEX idx_orders_product     ON orders (product_type, product_id);
CREATE INDEX idx_orders_status      ON orders (status);
CREATE INDEX idx_orders_user_id     ON orders (user_id);

