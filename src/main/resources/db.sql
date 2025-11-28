create table accommodations
(
    id              int auto_increment comment '住宿ID'
        primary key,
    name            varchar(100)                       not null comment '住宿名称',
    description     text                               null comment '描述',
    type            varchar(20)                        null comment '类型(农家乐/民宿/酒店)',
    location        varchar(255)                       null comment '地理位置',
    latitude        decimal(10, 8)                     null comment '纬度',
    longitude       decimal(11, 8)                     null comment '经度',
    cover_image     varchar(255)                       null comment '封面图片',
    images          text                               null comment '图片JSON数组',
    price_per_night decimal(10, 2)                     null comment '每晚价格',
    capacity        int                                null comment '可容纳人数',
    facilities      text                               null comment '设施JSON数组',
    contact_phone   varchar(20)                        null comment '联系电话',
    create_time     datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time     datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '住宿表' collate = utf8mb4_unicode_ci;

create index idx_accommodations_location
    on accommodations (location);

create table attractions
(
    id            int auto_increment comment '景点ID'
        primary key,
    name          varchar(100)                       not null comment '景点名称',
    description   text                               null comment '景点描述',
    location      varchar(255)                       null comment '地理位置',
    latitude      decimal(10, 8)                     null comment '纬度',
    longitude     decimal(11, 8)                     null comment '经度',
    cover_image   varchar(255)                       null comment '封面图片',
    images        text                               null comment '图片JSON数组',
    open_hours    varchar(100)                       null comment '开放时间',
    ticket_price  decimal(10, 2)                     null comment '门票价格',
    contact_phone varchar(20)                        null comment '联系电话',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '景点表' collate = utf8mb4_unicode_ci;

create index idx_attractions_location
    on attractions (location);

create table intangible_culture
(
    id          int auto_increment comment '非遗ID'
        primary key,
    name        varchar(100)                       not null comment '非遗项目名称',
    description text                               null comment '描述',
    type        varchar(50)                        null comment '非遗类型',
    inheritor   varchar(100)                       null comment '传承人',
    cover_image varchar(255)                       null comment '封面图片',
    images      text                               null comment '图片JSON数组',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '非物质文化表' collate = utf8mb4_unicode_ci;

create table restaurants
(
    id            int auto_increment comment '美食ID'
        primary key,
    name          varchar(100)                       not null comment '餐厅/美食名称',
    description   text                               null comment '描述',
    location      varchar(255)                       null comment '地理位置',
    latitude      decimal(10, 8)                     null comment '纬度',
    longitude     decimal(11, 8)                     null comment '经度',
    cover_image   varchar(255)                       null comment '封面图片',
    images        text                               null comment '图片JSON数组',
    open_hours    varchar(100)                       null comment '营业时间',
    price_range   varchar(50)                        null comment '价格区间(如:￥50-100)',
    specialty     varchar(100)                       null comment '招牌菜',
    contact_phone varchar(20)                        null comment '联系电话',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    rating        decimal(2, 1)                      null comment '推荐指数(0-5)'
)
    comment '美食表' collate = utf8mb4_unicode_ci;

create index idx_restaurants_location
    on restaurants (location);

create index idx_restaurants_rating
    on restaurants (rating);

create table users
(
    id            int auto_increment comment '用户ID'
        primary key,
    username      varchar(50)                          not null comment '用户名',
    password_hash varchar(255)                         not null comment '密码',
    email         varchar(100)                         not null comment '邮箱',
    phone         varchar(20)                          null comment '手机号',
    avatar_url    varchar(255)                         null comment '头像URL',
    status        tinyint(1) default 1                 not null comment '状态(0-禁用,1-启用)',
    create_time   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    role          tinyint(1) default 1                 not null comment '角色(0-管理员,1-普通用户)',
    constraint email
        unique (email),
    constraint username
        unique (username)
)
    comment '用户表' collate = utf8mb4_unicode_ci;

create table comments
(
    id               int auto_increment comment '评论ID'
        primary key,
    user_id          int                                  not null comment '用户ID',
    attraction_id    int                                  null comment '景点ID',
    restaurant_id    int                                  null comment '美食ID',
    culture_id       int                                  null comment '非遗ID',
    accommodation_id int                                  null comment '住宿ID',
    content          text                                 not null comment '评论内容',
    rating           int                                  null comment '评分(1-5星)',
    is_approved      tinyint(1) default 0                 null comment '是否审核通过(0-否,1-是)',
    create_time      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint comments_ibfk_1
        foreign key (user_id) references users (id),
    constraint comments_ibfk_2
        foreign key (attraction_id) references attractions (id),
    constraint comments_ibfk_3
        foreign key (restaurant_id) references restaurants (id),
    constraint comments_ibfk_4
        foreign key (culture_id) references intangible_culture (id),
    constraint comments_ibfk_5
        foreign key (accommodation_id) references accommodations (id),
    check ((`rating` >= 1) and (`rating` <= 5))
)
    comment '评论表' collate = utf8mb4_unicode_ci;

create index accommodation_id
    on comments (accommodation_id);

create index attraction_id
    on comments (attraction_id);

create index culture_id
    on comments (culture_id);

create index idx_comments_rating
    on comments (rating);

create index idx_comments_user_id
    on comments (user_id);

create index restaurant_id
    on comments (restaurant_id);

create table favorites
(
    id               int auto_increment comment '收藏ID'
        primary key,
    user_id          int                                not null comment '用户ID',
    attraction_id    int                                null comment '景点ID',
    restaurant_id    int                                null comment '美食ID',
    culture_id       int                                null comment '非遗ID',
    accommodation_id int                                null comment '住宿ID',
    create_time      datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint favorites_ibfk_1
        foreign key (user_id) references users (id),
    constraint favorites_ibfk_2
        foreign key (attraction_id) references attractions (id),
    constraint favorites_ibfk_3
        foreign key (restaurant_id) references restaurants (id),
    constraint favorites_ibfk_4
        foreign key (culture_id) references intangible_culture (id),
    constraint favorites_ibfk_5
        foreign key (accommodation_id) references accommodations (id)
)
    comment '收藏表' collate = utf8mb4_unicode_ci;

create index accommodation_id
    on favorites (accommodation_id);

create index attraction_id
    on favorites (attraction_id);

create index culture_id
    on favorites (culture_id);

create index idx_favorites_user_id
    on favorites (user_id);

create index restaurant_id
    on favorites (restaurant_id);

create table orders
(
    id           int auto_increment comment '订单ID'
        primary key,
    order_no     varchar(32)                          not null comment '订单编号',
    user_id      int                                  not null comment '用户ID',
    product_type tinyint(1)                           not null comment '产品类型(1-景点门票,2-美食消费券,3-住宿消费券)',
    product_id   int                                  not null comment '产品ID(根据product_type关联不同表)',
    product_name varchar(100)                         not null comment '产品名称',
    description  varchar(500)                         null comment '消费描述(如:住宿套餐类型/美食具体菜品等)',
    quantity     int        default 1                 not null comment '数量',
    unit_price   decimal(10, 2)                       not null comment '单价',
    total_amount decimal(10, 2)                       not null comment '总金额',
    status       tinyint(1) default 0                 not null comment '订单状态(0-待支付,1-已支付,2-已使用,3-已取消,4-已退款)',
    payment_time datetime                             null comment '支付时间',
    used_time    datetime                             null comment '使用时间',
    expire_time  datetime                             null comment '过期时间',
    create_time  datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    update_time  datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint idx_orders_order_no
        unique (order_no),
    constraint orders_ibfk_1
        foreign key (user_id) references users (id)
)
    comment '订单表' collate = utf8mb4_unicode_ci;

create index idx_orders_create_time
    on orders (create_time);

create index idx_orders_product
    on orders (product_type, product_id);

create index idx_orders_status
    on orders (status);

create index idx_orders_user_id
    on orders (user_id);

create index idx_users_email
    on users (email);

create index idx_users_role
    on users (role);

create index idx_users_username
    on users (username);

