-- 景点收藏模拟数据 (用户ID: 4)
-- 收藏部分景点作为测试数据

INSERT INTO favorites (user_id, attraction_id, create_time, update_time) VALUES
(4, 1, NOW(), NOW()),
(4, 3, NOW(), NOW()),
(4, 7, NOW(), NOW()),
(4, 9, NOW(), NOW()),
(4, 11, NOW(), NOW()),
(4, 15, NOW(), NOW()),
(4, 18, NOW(), NOW()),
(4, 20, NOW(), NOW()),
(4, 22, NOW(), NOW()),
(4, 24, NOW(), NOW());

-- 住宿收藏模拟数据 (用户ID: 4)
-- 收藏部分住宿作为测试数据

INSERT INTO favorites (user_id, accommodation_id, create_time, update_time) VALUES
(4, 1, NOW(), NOW()),
(4, 3, NOW(), NOW()),
(4, 5, NOW(), NOW());

-- 景点评论模拟数据
-- 用户ID为4的用户对部分景点发表评论

INSERT INTO comments (user_id, attraction_id, content, rating, is_approved, create_time, update_time) VALUES
(4, 1, '竹海风景优美，空气清新，非常适合度假放松。', 5, 1, NOW(), NOW()),
(4, 3, '森林公园的自然景观很棒，徒步路线很舒服。', 4, 1, NOW(), NOW()),
(4, 7, '轻轨穿楼的景象很震撼，重庆的特色之一。', 4, 1, NOW(), NOW()),
(4, 9, '天生三桥的自然景观令人叹为观止。', 5, 1, NOW(), NOW()),
(4, 11, '大足石刻历史悠久，文化底蕴深厚。', 5, 1, NOW(), NOW());

-- 其他用户对景点的评论示例
INSERT INTO comments (user_id, attraction_id, content, rating, is_approved, create_time, update_time) VALUES
(1, 1, '环境不错，服务也很好。', 4, 1, NOW(), NOW()),
(2, 3, '东山森林公园是周末放松的好去处。', 5, 1, NOW(), NOW()),
(3, 7, '李子坝轻轨站一定要去看看，很神奇。', 5, 1, NOW(), NOW()),
(1, 9, '武隆的自然景观太美了，值得一游。', 5, 1, NOW(), NOW()),
(2, 11, '大足石刻是中国古代艺术的瑰宝。', 5, 1, NOW(), NOW());

-- 验证插入的数据
-- SELECT * FROM favorites WHERE user_id = 4;
-- SELECT c.*, u.username FROM comments c JOIN users u ON c.user_id = u.id WHERE c.attraction_id IS NOT NULL;