SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE accommodation_facility_relations;
TRUNCATE TABLE accommodations;
TRUNCATE TABLE accommodation_facilities;
TRUNCATE TABLE accommodation_types;
TRUNCATE TABLE attractions;
TRUNCATE TABLE intangible_culture;
TRUNCATE TABLE restaurants;
TRUNCATE TABLE users;
TRUNCATE TABLE comments;
TRUNCATE TABLE favorites;
TRUNCATE TABLE orders;
TRUNCATE TABLE popular_attractions;

INSERT INTO accommodation_types (name, description) VALUES
('酒店','标准化住宿服务，设施完善'),
('民宿','具有当地特色的居住体验'),
('农家乐','乡村休闲，贴近自然'),
('青年旅舍','经济型多人床位或简易房'),
('客栈公寓','客栈或公寓式住宿');

INSERT INTO accommodation_facilities (name, description, icon) VALUES
('免费WiFi','提供免费无线网络','wifi'),
('WiFi','提供免费无线网络','wifi'),
('泳池','室外或室内游泳池','fire-o'),
('健身房','健身设施','friends-o'),
('早餐','提供早餐服务','smile-comment-o'),
('江景','临江景观房','location-o'),
('餐厅','酒店/民宿内餐饮','hotel-o'),
('庭院','带私家庭院','flower-o'),
('茶室','可品茶休闲','smile-comment-o'),
('投影仪','客房内提供投影','tv-o'),
('厨房','可简单烹饪','smile-comment-o'),
('公共区域','旅舍公共空间','friends-o'),
('酒吧','提供酒水与交流空间','smile-comment-o'),
('温泉','温泉设施','fire-o'),
('会议','会议室/会务','upgrade'),
('洗衣机','可洗衣使用','friends-o'),
('电梯','公寓/酒店配备电梯','upgrade'),
('落地窗','房型带落地窗','location-o');

SET @tid_hotel    = (SELECT id FROM accommodation_types WHERE name='酒店');
SET @tid_homestay = (SELECT id FROM accommodation_types WHERE name='民宿');
SET @tid_farm     = (SELECT id FROM accommodation_types WHERE name='农家乐');
SET @tid_hostel   = (SELECT id FROM accommodation_types WHERE name='青年旅舍');
SET @tid_inn      = (SELECT id FROM accommodation_types WHERE name='客栈公寓');

INSERT INTO accommodations (name, description, type_id, location, latitude, longitude, cover_image, images, price_per_night, capacity, contact_phone) VALUES
('梁平双桂湖度假酒店','临湖景观客房，适合家庭度假', @tid_hotel, '梁平区双桂湖景区', 30.6768, 107.8025, 'https://example.com/img/lgp_hotel_1.jpg', '["https://example.com/img/lgp_hotel_1.jpg"]', 488.00, 3, '023-88880001'),
('梁平明达古镇客栈','古镇风格客栈，安静舒适', @tid_inn, '梁平区明达古镇', 30.6901, 107.7802, 'https://example.com/img/lgp_inn_1.jpg', '["https://example.com/img/lgp_inn_1.jpg"]', 268.00, 2, '023-88880002'),
('竹海里民宿','推窗见竹海，私享庭院', @tid_homestay, '梁平区竹海景区内', 30.7002, 107.8204, 'https://example.com/img/lgp_homestay_1.jpg', '["https://example.com/img/lgp_homestay_1.jpg"]', 328.00, 3, '023-88880003'),
('梁平城市酒店','商务出行优选，交通便利', @tid_hotel, '梁平区人民路', 30.6725, 107.7933, 'https://example.com/img/lgp_hotel_2.jpg', '["https://example.com/img/lgp_hotel_2.jpg"]', 368.00, 2, '023-88880004'),
('稻香农家乐','田园采摘体验，适合亲子', @tid_farm, '梁平区合兴镇', 30.6584, 107.8401, 'https://example.com/img/lgp_farm_1.jpg', '["https://example.com/img/lgp_farm_1.jpg"]', 198.00, 3, '023-88880005'),
('双桂湖青年旅舍','背包客聚集地，社交氛围好', @tid_hostel, '梁平区双桂湖东岸', 30.6798, 107.8077, 'https://example.com/img/lgp_hostel_1.jpg', '["https://example.com/img/lgp_hostel_1.jpg"]', 88.00, 1, '023-88880006'),
('云岭观景公寓','高层江景/湖景房，落地窗', @tid_inn, '梁平区云岭路', 30.6689, 107.7999, 'https://example.com/img/lgp_apartment_1.jpg', '["https://example.com/img/lgp_apartment_1.jpg"]', 320.00, 3, '023-88880007'),
('茶语民宿','茶室文化体验，慢生活', @tid_homestay, '梁平区双桂街道', 30.6751, 107.8122, 'https://example.com/img/lgp_homestay_2.jpg', '["https://example.com/img/lgp_homestay_2.jpg"]', 299.00, 2, '023-88880008'),
('桂湖温泉酒店','室内外温泉池，康养度假', @tid_hotel, '梁平区桂湖路', 30.6710, 107.8158, 'https://example.com/img/lgp_hotel_3.jpg', '["https://example.com/img/lgp_hotel_3.jpg"]', 598.00, 3, '023-88880009'),
('竹影庭院民宿','庭院私密空间，投影观影', @tid_homestay, '梁平区竹影巷', 30.7033, 107.8280, 'https://example.com/img/lgp_homestay_3.jpg', '["https://example.com/img/lgp_homestay_3.jpg"]', 338.00, 3, '023-88880010');

SET @wifi      = (SELECT id FROM accommodation_facilities WHERE name IN ('免费WiFi','WiFi') ORDER BY name='免费WiFi' DESC LIMIT 1);
SET @pool      = (SELECT id FROM accommodation_facilities WHERE name='泳池');
SET @gym       = (SELECT id FROM accommodation_facilities WHERE name='健身房');
SET @breakfast = (SELECT id FROM accommodation_facilities WHERE name='早餐');
SET @kitchen   = (SELECT id FROM accommodation_facilities WHERE name='厨房');
SET @projector = (SELECT id FROM accommodation_facilities WHERE name='投影仪');
SET @courtyard = (SELECT id FROM accommodation_facilities WHERE name='庭院');
SET @tearoom   = (SELECT id FROM accommodation_facilities WHERE name='茶室');
SET @common    = (SELECT id FROM accommodation_facilities WHERE name='公共区域');
SET @washer    = (SELECT id FROM accommodation_facilities WHERE name='洗衣机');
SET @elevator  = (SELECT id FROM accommodation_facilities WHERE name='电梯');
SET @viewwin   = (SELECT id FROM accommodation_facilities WHERE name='落地窗');

INSERT IGNORE INTO accommodation_facility_relations (accommodation_id, facility_id)
SELECT a.id, @wifi FROM accommodations a;

INSERT IGNORE INTO accommodation_facility_relations (accommodation_id, facility_id)
SELECT a.id, x.fac_id FROM accommodations a
JOIN (SELECT @pool AS fac_id UNION ALL SELECT @gym UNION ALL SELECT @breakfast) x
WHERE a.type_id = @tid_hotel AND x.fac_id IS NOT NULL;

INSERT IGNORE INTO accommodation_facility_relations (accommodation_id, facility_id)
SELECT a.id, x.fac_id FROM accommodations a
JOIN (SELECT @kitchen AS fac_id UNION ALL SELECT @projector UNION ALL SELECT @courtyard UNION ALL SELECT @tearoom) x
WHERE a.type_id = @tid_homestay AND x.fac_id IS NOT NULL;

INSERT IGNORE INTO accommodation_facility_relations (accommodation_id, facility_id)
SELECT a.id, x.fac_id FROM accommodations a
JOIN (SELECT @courtyard AS fac_id UNION ALL SELECT @kitchen) x
WHERE a.type_id = @tid_farm AND x.fac_id IS NOT NULL;

INSERT IGNORE INTO accommodation_facility_relations (accommodation_id, facility_id)
SELECT a.id, x.fac_id FROM accommodations a
JOIN (SELECT @common AS fac_id UNION ALL SELECT @washer) x
WHERE a.type_id = @tid_hostel AND x.fac_id IS NOT NULL;

INSERT IGNORE INTO accommodation_facility_relations (accommodation_id, facility_id)
SELECT a.id, x.fac_id FROM accommodations a
JOIN (SELECT @washer AS fac_id UNION ALL SELECT @elevator UNION ALL SELECT @viewwin) x
WHERE a.type_id = @tid_inn AND x.fac_id IS NOT NULL;

INSERT INTO attractions (name, description, location, latitude, longitude, cover_image, images, open_hours, ticket_price, contact_phone) VALUES
('双桂湖国家湿地公园','湖泊湿地景观，步道环湖适合散步', '梁平区双桂湖', 30.6768, 107.8025, 'https://example.com/img/att_sgh.jpg', '["https://example.com/img/att_sgh.jpg"]', '8:00-18:00', 0.00, '023-88881001'),
('明达古镇','传统街巷与古建筑保存完好', '梁平区明达古镇', 30.6901, 107.7802, 'https://example.com/img/att_md.jpg', '["https://example.com/img/att_md.jpg"]', '全天', 0.00, '023-88881002'),
('竹海景区','大片竹林，负氧离子丰富', '梁平区竹海', 30.7002, 107.8204, 'https://example.com/img/att_zh.jpg', '["https://example.com/img/att_zh.jpg"]', '8:30-17:30', 30.00, '023-88881003'),
('梁平博物馆','了解本地历史人文', '梁平区博物馆路', 30.6712, 107.7958, 'https://example.com/img/att_bwg.jpg', '["https://example.com/img/att_bwg.jpg"]', '9:00-17:00', 0.00, '023-88881004'),
('云岭观景台','俯瞰城区与湖景', '梁平区云岭路观景台', 30.6689, 107.7999, 'https://example.com/img/att_yl.jpg', '["https://example.com/img/att_yl.jpg"]', '全天', 0.00, '023-88881005'),
('桂湖温泉中心','温泉泡浴与康养', '梁平区桂湖路', 30.6710, 107.8158, 'https://example.com/img/att_wq.jpg', '["https://example.com/img/att_wq.jpg"]', '10:00-22:00', 98.00, '023-88881006'),
('合兴田园综合体','农耕体验与采摘', '梁平区合兴镇', 30.6584, 107.8401, 'https://example.com/img/att_hx.jpg', '["https://example.com/img/att_hx.jpg"]', '9:00-18:00', 20.00, '023-88881007'),
('梁平木版年画馆','非遗展陈与互动体验', '梁平区文化街', 30.6755, 107.8066, 'https://example.com/img/att_nh.jpg', '["https://example.com/img/att_nh.jpg"]', '9:00-17:30', 15.00, '023-88881008');

-- 热门景点（示例数据）
INSERT INTO popular_attractions (attraction_id, name, description, latitude, longitude) VALUES
(1, '双桂湖国家湿地公园', '湖泊湿地景观，步道环湖适合散步', 30.6768, 107.8025),
(2, '明达古镇', '传统街巷与古建筑保存完好', 30.6901, 107.7802),
(3, '竹海景区', '大片竹林，负氧离子丰富', 30.7002, 107.8204),
(5, '云岭观景台', '俯瞰城区与湖景', 30.6689, 107.7999);

INSERT INTO intangible_culture (name, description, type, inheritor, cover_image, images) VALUES
('梁平木版年画','色彩艳丽、造型夸张的地方传统美术','传统美术','徐家辉','https://example.com/img/nh_lp.jpg','["https://example.com/img/nh_lp.jpg"]'),
('竹编技艺','以竹为材的编织工艺','传统技艺','李师傅','https://example.com/img/nh_zb.jpg','["https://example.com/img/nh_zb.jpg"]'),
('柚子种植技艺','当地特色作物栽培技艺','传统技艺','王师傅','https://example.com/img/nh_yz.jpg','["https://example.com/img/nh_yz.jpg"]'),
('川剧折子戏','川剧表演艺术','传统戏剧','沈老师','https://example.com/img/nh_cj.jpg','["https://example.com/img/nh_cj.jpg"]'),
('酿造技艺','地方酒酿工艺','传统技艺','刘师傅','https://example.com/img/nh_nz.jpg','["https://example.com/img/nh_nz.jpg"]');

INSERT INTO restaurants (name, description, location, latitude, longitude, cover_image, images, open_hours, price_range, specialty, contact_phone, rating) VALUES
('梁平张鸭子总店','地方名小吃，卤味香浓','梁平区人民路',30.6725,107.7933,'https://example.com/img/res_zyz.jpg','["https://example.com/img/res_zyz.jpg"]','10:00-22:00','￥30-60','张鸭子', '023-88882001',4.6),
('双桂湖渔家','湖鲜料理与家常菜','梁平区双桂湖景区',30.6768,107.8025,'https://example.com/img/res_yj.jpg','["https://example.com/img/res_yj.jpg"]','11:00-21:00','￥60-120','湖鲜', '023-88882002',4.4),
('明达古镇客栈餐厅','古镇风味菜','梁平区明达古镇',30.6901,107.7802,'https://example.com/img/res_md.jpg','["https://example.com/img/res_md.jpg"]','11:00-22:00','￥40-80','古镇合菜', '023-88882003',4.3),
('竹海私房菜','竹荪与时令菜','梁平区竹海',30.7002,107.8204,'https://example.com/img/res_zh.jpg','["https://example.com/img/res_zh.jpg"]','11:00-21:30','￥80-150','竹荪', '023-88882004',4.5),
('桂湖温泉餐厅','康养轻食与汤品','梁平区桂湖路',30.6710,107.8158,'https://example.com/img/res_wq.jpg','["https://example.com/img/res_wq.jpg"]','10:30-21:30','￥50-100','温泉套餐', '023-88882005',4.2),
('云岭观景咖啡','轻食咖啡甜点','梁平区云岭路',30.6689,107.7999,'https://example.com/img/res_yl.jpg','["https://example.com/img/res_yl.jpg"]','09:00-22:00','￥30-80','手冲咖啡', '023-88882006',4.7),
('合兴农家院','农家菜与土鸡','梁平区合兴镇',30.6584,107.8401,'https://example.com/img/res_hx.jpg','["https://example.com/img/res_hx.jpg"]','10:30-21:00','￥40-90','土鸡', '023-88882007',4.3),
('文化街小吃集市','地方特色小吃','梁平区文化街',30.6755,107.8066,'https://example.com/img/res_wc.jpg','["https://example.com/img/res_wc.jpg"]','16:00-23:00','￥20-50','小吃拼盘', '023-88882008',4.1);

INSERT INTO users (username, password_hash, email, phone, avatar_url, status, role) VALUES
('lp_user01','hash01','lp01@example.com','13800000001',NULL,1,1),
('lp_user02','hash02','lp02@example.com','13800000002',NULL,1,1),
('lp_user03','hash03','lp03@example.com','13800000003',NULL,1,1),
('lp_user04','hash04','lp04@example.com','13800000004',NULL,1,1),
('lp_user05','hash05','lp05@example.com','13800000005',NULL,1,1),
('lp_user06','hash06','lp06@example.com','13800000006',NULL,1,1),
('lp_user07','hash07','lp07@example.com','13800000007',NULL,1,1),
('lp_user08','hash08','lp08@example.com','13800000008',NULL,1,1),
('lp_user09','hash09','lp09@example.com','13800000009',NULL,1,1),
('lp_user10','hash10','lp10@example.com','13800000010',NULL,1,1);

-- 管理员与测试用户
INSERT INTO users (username, password_hash, email, phone, avatar_url, status, role) VALUES
('admin_lpwst','admin_hash','admin@example.com','13900000000',NULL,1,0),
('tester_lpwst','tester_hash','tester@example.com','13900000001',NULL,1,1);

INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time) VALUES
(1, 1, NULL, NULL, NULL, '双桂湖风景很好，环湖步道很舒适', 5, 1, NOW()),
(2, NULL, 1, NULL, NULL, '张鸭子味道很正，外酥里嫩', 5, 1, NOW()),
(3, NULL, NULL, 1, NULL, '年画很有特色，值得参观', 5, 1, NOW()),
(4, NULL, NULL, NULL, 1, '度假酒店服务好，早餐丰富', 4, 1, NOW()),
(5, 3, NULL, NULL, NULL, '竹海空气清新，拍照出片', 5, 1, NOW()),
(6, NULL, 6, NULL, NULL, '观景咖啡店视野好，甜点不错', 4, 1, NOW()),
(7, NULL, NULL, NULL, 3, '民宿庭院安静，适合休闲', 5, 1, NOW()),
(8, 2, NULL, NULL, NULL, '古镇人不多，逛起来舒服', 4, 1, NOW()),
(9, NULL, 4, NULL, NULL, '竹荪汤鲜美，推荐', 5, 1, NOW()),
(10, NULL, NULL, NULL, 9, '温泉酒店池子多，放松身心', 4, 1, NOW());

DROP PROCEDURE IF EXISTS sp_seed_comments_liangping;
DELIMITER $$
CREATE PROCEDURE sp_seed_comments_liangping(
  IN cnt_attract INT,
  IN cnt_rest INT,
  IN cnt_culture INT,
  IN cnt_accommodation INT
)
BEGIN
  DECLARE n INT;

  SET n = 1;
  WHILE n <= cnt_attract DO
    INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time)
    VALUES (
      ((n-1) % 10) + 1,
      ((n-1) % 8) + 1,
      NULL,
      NULL,
      NULL,
      CONCAT('梁平景点体验评价 ', n),
      ((n-1) % 5) + 1,
      1,
      DATE_SUB(NOW(), INTERVAL ((n-1) % 30) DAY)
    );
    SET n = n + 1;
  END WHILE;

  SET n = 1;
  WHILE n <= cnt_rest DO
    INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time)
    VALUES (
      ((n-1) % 10) + 1,
      NULL,
      ((n-1) % 8) + 1,
      NULL,
      NULL,
      CONCAT('梁平美食体验评价 ', n),
      ((n-1) % 5) + 1,
      1,
      DATE_SUB(NOW(), INTERVAL ((n-1) % 30) DAY)
    );
    SET n = n + 1;
  END WHILE;

  SET n = 1;
  WHILE n <= cnt_culture DO
    INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time)
    VALUES (
      ((n-1) % 10) + 1,
      NULL,
      NULL,
      ((n-1) % 5) + 1,
      NULL,
      CONCAT('梁平非遗体验评价 ', n),
      ((n-1) % 5) + 1,
      1,
      DATE_SUB(NOW(), INTERVAL ((n-1) % 30) DAY)
    );
    SET n = n + 1;
  END WHILE;

  SET n = 1;
  WHILE n <= cnt_accommodation DO
    INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time)
    VALUES (
      ((n-1) % 10) + 1,
      NULL,
      NULL,
      NULL,
      ((n-1) % 10) + 1,
      CONCAT('梁平住宿体验评价 ', n),
      ((n-1) % 5) + 1,
      1,
      DATE_SUB(NOW(), INTERVAL ((n-1) % 30) DAY)
    );
    SET n = n + 1;
  END WHILE;
END $$
DELIMITER ;

CALL sp_seed_comments_liangping(600, 600, 400, 400);

-- 基于角色（管理员/测试用户）扩充评论/收藏/订单数据
DROP PROCEDURE IF EXISTS sp_seed_role_data_liangping;
DELIMITER $$
/**
 * sp_seed_role_data_liangping
 * 目标：为管理员(admin_lpwst)与测试用户(tester_lpwst)批量生成模拟数据
 * 内容：评论、收藏、订单，按不同实体与类型分布生成
 * 参数：per_role_comments 每个角色评论条数；per_role_favorites 每个角色收藏条数；per_role_orders 每个角色订单条数
 */
CREATE PROCEDURE sp_seed_role_data_liangping(
  IN per_role_comments INT,
  IN per_role_favorites INT,
  IN per_role_orders INT
)
BEGIN
  DECLARE admin_id INT;
  DECLARE tester_id INT;
  DECLARE a_cnt INT; DECLARE r_cnt INT; DECLARE c_cnt INT; DECLARE acc_cnt INT;
  DECLARE n INT; DECLARE off INT;
  DECLARE pid INT; DECLARE pname VARCHAR(100);
  DECLARE s TINYINT;
  DECLARE pt DATETIME;
  DECLARE ut DATETIME;
  DECLARE et DATETIME;

  SELECT id INTO admin_id FROM users WHERE username='admin_lpwst' LIMIT 1;
  SELECT id INTO tester_id FROM users WHERE username='tester_lpwst' LIMIT 1;

  SELECT COUNT(*) INTO a_cnt FROM attractions;
  SELECT COUNT(*) INTO r_cnt FROM restaurants;
  SELECT COUNT(*) INTO c_cnt FROM intangible_culture;
  SELECT COUNT(*) INTO acc_cnt FROM accommodations;

  -- 为指定用户生成评论（轮转四类：景点、美食、非遗、住宿）
  SET n = 1;
  WHILE n <= per_role_comments DO
    -- 管理员
    IF (n % 4) = 1 THEN
      SET off = (n-1) % a_cnt;
      INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time)
      SELECT admin_id, t.id, NULL, NULL, NULL, CONCAT('管理员点评景点 #', t.name), ((n-1)%5)+1, 1, DATE_SUB(NOW(), INTERVAL ((n-1)%30) DAY)
      FROM (SELECT id, name FROM attractions ORDER BY id LIMIT off,1) t;
    ELSEIF (n % 4) = 2 THEN
      SET off = (n-1) % r_cnt;
      INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time)
      SELECT admin_id, NULL, t.id, NULL, NULL, CONCAT('管理员点评美食 #', t.name), ((n-1)%5)+1, 1, DATE_SUB(NOW(), INTERVAL ((n-1)%30) DAY)
      FROM (SELECT id, name FROM restaurants ORDER BY id LIMIT off,1) t;
    ELSEIF (n % 4) = 3 THEN
      SET off = (n-1) % c_cnt;
      INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time)
      SELECT admin_id, NULL, NULL, t.id, NULL, CONCAT('管理员点评非遗 #', t.name), ((n-1)%5)+1, 1, DATE_SUB(NOW(), INTERVAL ((n-1)%30) DAY)
      FROM (SELECT id, name FROM intangible_culture ORDER BY id LIMIT off,1) t;
    ELSE
      SET off = (n-1) % acc_cnt;
      INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time)
      SELECT admin_id, NULL, NULL, NULL, t.id, CONCAT('管理员点评住宿 #', t.name), ((n-1)%5)+1, 1, DATE_SUB(NOW(), INTERVAL ((n-1)%30) DAY)
      FROM (SELECT id, name FROM accommodations ORDER BY id LIMIT off,1) t;
    END IF;

    -- 测试用户
    IF (n % 4) = 1 THEN
      SET off = (n-1) % a_cnt;
      INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time)
      SELECT tester_id, t.id, NULL, NULL, NULL, CONCAT('测试用户点评景点 #', t.name), ((n-1)%5)+1, 1, DATE_SUB(NOW(), INTERVAL ((n-1)%30) DAY)
      FROM (SELECT id, name FROM attractions ORDER BY id LIMIT off,1) t;
    ELSEIF (n % 4) = 2 THEN
      SET off = (n-1) % r_cnt;
      INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time)
      SELECT tester_id, NULL, t.id, NULL, NULL, CONCAT('测试用户点评美食 #', t.name), ((n-1)%5)+1, 1, DATE_SUB(NOW(), INTERVAL ((n-1)%30) DAY)
      FROM (SELECT id, name FROM restaurants ORDER BY id LIMIT off,1) t;
    ELSEIF (n % 4) = 3 THEN
      SET off = (n-1) % c_cnt;
      INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time)
      SELECT tester_id, NULL, NULL, t.id, NULL, CONCAT('测试用户点评非遗 #', t.name), ((n-1)%5)+1, 1, DATE_SUB(NOW(), INTERVAL ((n-1)%30) DAY)
      FROM (SELECT id, name FROM intangible_culture ORDER BY id LIMIT off,1) t;
    ELSE
      SET off = (n-1) % acc_cnt;
      INSERT INTO comments (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, content, rating, is_approved, create_time)
      SELECT tester_id, NULL, NULL, NULL, t.id, CONCAT('测试用户点评住宿 #', t.name), ((n-1)%5)+1, 1, DATE_SUB(NOW(), INTERVAL ((n-1)%30) DAY)
      FROM (SELECT id, name FROM accommodations ORDER BY id LIMIT off,1) t;
    END IF;

    SET n = n + 1;
  END WHILE;

  -- 收藏（两角色按类别轮转）
  SET n = 1;
  WHILE n <= per_role_favorites DO
    SET off = (n-1) % a_cnt;
    INSERT IGNORE INTO favorites (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, create_time)
    SELECT admin_id, t.id, NULL, NULL, NULL, NOW() FROM (SELECT id FROM attractions ORDER BY id LIMIT off,1) t;
    INSERT IGNORE INTO favorites (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, create_time)
    SELECT tester_id, t.id, NULL, NULL, NULL, NOW() FROM (SELECT id FROM attractions ORDER BY id LIMIT off,1) t;

    SET off = (n-1) % r_cnt;
    INSERT IGNORE INTO favorites (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, create_time)
    SELECT admin_id, NULL, t.id, NULL, NULL, NOW() FROM (SELECT id FROM restaurants ORDER BY id LIMIT off,1) t;
    INSERT IGNORE INTO favorites (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, create_time)
    SELECT tester_id, NULL, t.id, NULL, NULL, NOW() FROM (SELECT id FROM restaurants ORDER BY id LIMIT off,1) t;

    SET off = (n-1) % c_cnt;
    INSERT IGNORE INTO favorites (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, create_time)
    SELECT admin_id, NULL, NULL, t.id, NULL, NOW() FROM (SELECT id FROM intangible_culture ORDER BY id LIMIT off,1) t;
    INSERT IGNORE INTO favorites (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, create_time)
    SELECT tester_id, NULL, NULL, t.id, NULL, NOW() FROM (SELECT id FROM intangible_culture ORDER BY id LIMIT off,1) t;

    SET off = (n-1) % acc_cnt;
    INSERT IGNORE INTO favorites (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, create_time)
    SELECT admin_id, NULL, NULL, NULL, t.id, NOW() FROM (SELECT id FROM accommodations ORDER BY id LIMIT off,1) t;
    INSERT IGNORE INTO favorites (user_id, attraction_id, restaurant_id, culture_id, accommodation_id, create_time)
    SELECT tester_id, NULL, NULL, NULL, t.id, NOW() FROM (SELECT id FROM accommodations ORDER BY id LIMIT off,1) t;

    SET n = n + 1;
  END WHILE;

  SET n = 1;
  WHILE n <= per_role_orders DO
    SET s = (n-1) % 5;
    SET pt = NULL; SET ut = NULL; SET et = NULL;
    IF s = 0 THEN
      SET et = DATE_ADD(NOW(), INTERVAL 7 DAY);
    ELSEIF s = 1 THEN
      SET pt = DATE_SUB(NOW(), INTERVAL (n % 10) DAY);
      SET et = DATE_ADD(pt, INTERVAL 30 DAY);
    ELSEIF s = 2 THEN
      SET pt = DATE_SUB(NOW(), INTERVAL (n % 10) DAY);
      SET ut = DATE_SUB(NOW(), INTERVAL (n % 5) DAY);
      SET et = DATE_ADD(ut, INTERVAL 1 DAY);
    ELSEIF s = 3 THEN
      SET et = NOW();
    ELSE
      SET pt = DATE_SUB(NOW(), INTERVAL (n % 10) DAY);
      SET et = NOW();
    END IF;

    IF (n % 3) = 1 THEN
      SET off = (n-1) % a_cnt;
      SELECT id, name INTO pid, pname FROM attractions ORDER BY id LIMIT off,1;
      INSERT INTO orders (order_no, user_id, product_type, product_id, product_name, description, quantity, unit_price, total_amount, status, payment_time, used_time, expire_time, create_time)
      VALUES (CONCAT('LPORDA', DATE_FORMAT(NOW(),'%Y%m%d'), LPAD(n,5,'0')), admin_id, 1, pid, pname, '门票', 2, 20.00, 40.00, s, pt, ut, et, NOW());
      INSERT INTO orders (order_no, user_id, product_type, product_id, product_name, description, quantity, unit_price, total_amount, status, payment_time, used_time, expire_time, create_time)
      VALUES (CONCAT('LPORDT', DATE_FORMAT(NOW(),'%Y%m%d'), LPAD(n,5,'0')), tester_id, 1, pid, pname, '门票', 2, 20.00, 40.00, s, pt, ut, et, NOW());
    ELSEIF (n % 3) = 2 THEN
      SET off = (n-1) % r_cnt;
      SELECT id, name INTO pid, pname FROM restaurants ORDER BY id LIMIT off,1;
      INSERT INTO orders (order_no, user_id, product_type, product_id, product_name, description, quantity, unit_price, total_amount, status, payment_time, used_time, expire_time, create_time)
      VALUES (CONCAT('LPORDA', DATE_FORMAT(NOW(),'%Y%m%d'), LPAD(n,5,'0')), admin_id, 2, pid, pname, '代金券100元', 1, 95.00, 95.00, s, pt, ut, et, NOW());
      INSERT INTO orders (order_no, user_id, product_type, product_id, product_name, description, quantity, unit_price, total_amount, status, payment_time, used_time, expire_time, create_time)
      VALUES (CONCAT('LPORDT', DATE_FORMAT(NOW(),'%Y%m%d'), LPAD(n,5,'0')), tester_id, 2, pid, pname, '代金券100元', 1, 95.00, 95.00, s, pt, ut, et, NOW());
    ELSE
      SET off = (n-1) % acc_cnt;
      SELECT id, name INTO pid, pname FROM accommodations ORDER BY id LIMIT off,1;
      INSERT INTO orders (order_no, user_id, product_type, product_id, product_name, description, quantity, unit_price, total_amount, status, payment_time, used_time, expire_time, create_time)
      VALUES (CONCAT('LPORDA', DATE_FORMAT(NOW(),'%Y%m%d'), LPAD(n,5,'0')), admin_id, 3, pid, pname, '住宿1晚', 1, 288.00, 288.00, s, pt, ut, et, NOW());
      INSERT INTO orders (order_no, user_id, product_type, product_id, product_name, description, quantity, unit_price, total_amount, status, payment_time, used_time, expire_time, create_time)
      VALUES (CONCAT('LPORDT', DATE_FORMAT(NOW(),'%Y%m%d'), LPAD(n,5,'0')), tester_id, 3, pid, pname, '住宿1晚', 1, 288.00, 288.00, s, pt, ut, et, NOW());
    END IF;
    SET n = n + 1;
  END WHILE;
END $$
DELIMITER ;

CALL sp_seed_role_data_liangping(800, 120, 80);

SET FOREIGN_KEY_CHECKS = 1;
