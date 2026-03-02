-- 梁平文旅项目：景点图片路径标准化更新脚本
-- 目标：根据本地图片文件分析结果，更新景点表的封面图与浏览图路径
-- 规则：识别图片命名规则，精确匹配景点记录，确保路径标准化

SET FOREIGN_KEY_CHECKS = 0;

-- ==========================================
-- 1. 双桂湖国家湿地公园 (ID: 1)
-- ==========================================
START TRANSACTION;
-- 验证景点是否存在
SELECT id, name FROM attractions WHERE id = 1 AND name LIKE '%双桂湖%';

-- 更新封面图与浏览图
UPDATE attractions 
SET cover_image = '/public/images/新对话.png',
    images = '["/public/images/新对话 (1).png", "/public/images/新对话 (2).png"]'
WHERE id = 1;

-- 验证更新结果
SELECT id, cover_image, images FROM attractions WHERE id = 1;
COMMIT;


-- ==========================================
-- 2. 明达古镇 (ID: 2)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 2 AND name LIKE '%明达%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (5).png',
    images = '["/public/images/新对话 (3).png", "/public/images/新对话 (4).png"]'
WHERE id = 2;

SELECT id, cover_image, images FROM attractions WHERE id = 2;
COMMIT;


-- ==========================================
-- 3. 百里竹海景区 (ID: 3)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 3 AND name LIKE '%竹海%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (7).png',
    images = '["/public/images/新对话 (6).png"]'
WHERE id = 3;

SELECT id, cover_image, images FROM attractions WHERE id = 3;
COMMIT;


-- ==========================================
-- 4. 梁平博物馆 (ID: 4)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 4 AND name LIKE '%博物馆%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (8).png',
    images = '["/public/images/新对话 (9).png", "/public/images/新对话 (10).png"]'
WHERE id = 4;

SELECT id, cover_image, images FROM attractions WHERE id = 4;
COMMIT;


-- ==========================================
-- 5. 云岭路观景台 (ID: 5)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 5 AND name LIKE '%云岭%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (11).png',
    images = '["/public/images/新对话 (12).png", "/public/images/新对话 (13).png"]'
WHERE id = 5;

SELECT id, cover_image, images FROM attractions WHERE id = 5;
COMMIT;


-- ==========================================
-- 6. 桂湖温泉中心 (ID: 6)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 6 AND name LIKE '%温泉%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (14).png',
    images = '["/public/images/新对话 (15).png", "/public/images/新对话 (20).png"]'
WHERE id = 6;

SELECT id, cover_image, images FROM attractions WHERE id = 6;
COMMIT;


-- ==========================================
-- 7. 合兴田园综合体 (ID: 7)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 7 AND name LIKE '%合兴%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (16).png',
    images = '["/public/images/新对话 (17).png", "/public/images/新对话 (19).png"]'
WHERE id = 7;

SELECT id, cover_image, images FROM attractions WHERE id = 7;
COMMIT;


-- ==========================================
-- 8. 木版年画展示馆 (ID: 8)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 8 AND name LIKE '%年画%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (18).png',
    images = '["/public/images/新对话 (21).png", "/public/images/新对话 (22).png"]'
WHERE id = 8;

SELECT id, cover_image, images FROM attractions WHERE id = 8;
COMMIT;


-- ==========================================
-- 9. 蟠龙洞景区 (ID: 9)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 9 AND name LIKE '%蟠龙洞%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (24).png',
    images = '["/public/images/新对话 (23).png"]'
WHERE id = 9;

SELECT id, cover_image, images FROM attractions WHERE id = 9;
COMMIT;


-- ==========================================
-- 10. 梁平柚海 (ID: 10)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 10 AND name LIKE '%柚海%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (25).png',
    images = '["/public/images/新对话 (26).png", "/public/images/新对话 (30).png"]'
WHERE id = 10;

SELECT id, cover_image, images FROM attractions WHERE id = 10;
COMMIT;


-- ==========================================
-- 11. 猎神村童话森林 (ID: 11)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 11 AND name LIKE '%童话森林%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (27).png',
    images = '["/public/images/新对话 (28).png", "/public/images/新对话 (29).png"]'
WHERE id = 11;

SELECT id, cover_image, images FROM attractions WHERE id = 11;
COMMIT;


-- ==========================================
-- 12. 龙溪河漂流 (ID: 12)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 12 AND name LIKE '%龙溪河漂流%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (31).png',
    images = '["/public/images/新对话 (32).png", "/public/images/新对话 (33).png", "/public/images/新对话 (34).png"]'
WHERE id = 12;

SELECT id, cover_image, images FROM attractions WHERE id = 12;
COMMIT;


-- ==========================================
-- 13. 牛头山风景区 (ID: 13)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 13 AND name LIKE '%牛头山%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (35).png',
    images = '["/public/images/新对话 (36).png", "/public/images/新对话 (37).png"]'
WHERE id = 13;

SELECT id, cover_image, images FROM attractions WHERE id = 13;
COMMIT;


-- ==========================================
-- 14. 礼让渔儿村 (ID: 14)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 14 AND name LIKE '%渔儿村%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (39).png',
    images = '["/public/images/新对话 (38).png", "/public/images/新对话 (40).png"]'
WHERE id = 14;

SELECT id, cover_image, images FROM attractions WHERE id = 14;
COMMIT;


-- ==========================================
-- 15. 赤牛城遗址 (ID: 15)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 15 AND name LIKE '%赤牛城%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (41).png',
    images = '["/public/images/新对话 (42).png", "/public/images/新对话 (43).png"]'
WHERE id = 15;

SELECT id, cover_image, images FROM attractions WHERE id = 15;
COMMIT;


-- ==========================================
-- 16. 金带万石耕春·稻香世界 (ID: 16)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 16 AND name LIKE '%稻香世界%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (44).png',
    images = '["/public/images/新对话 (45).png", "/public/images/新对话 (46).png"]'
WHERE id = 16;

SELECT id, cover_image, images FROM attractions WHERE id = 16;
COMMIT;


-- ==========================================
-- 17. 竹丰湖水利风景区 (ID: 17)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 17 AND name LIKE '%竹丰湖%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (47).png',
    images = '["/public/images/新对话 (48).png", "/public/images/新对话 (49).png"]'
WHERE id = 17;

SELECT id, cover_image, images FROM attractions WHERE id = 17;
COMMIT;


-- ==========================================
-- 18. 梁平文庙 (ID: 18)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 18 AND name LIKE '%文庙%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (54).png',
    images = '["/public/images/新对话 (53).png", "/public/images/新对话 (55).png"]'
WHERE id = 18;

SELECT id, cover_image, images FROM attractions WHERE id = 18;
COMMIT;


-- ==========================================
-- 19. 寿海景区 (ID: 19)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 19 AND name LIKE '%寿海%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (50).png',
    images = '["/public/images/新对话 (51).png"]'
WHERE id = 19;

SELECT id, cover_image, images FROM attractions WHERE id = 19;
COMMIT;


-- ==========================================
-- 20. 聚葵山风景区 (ID: 20)
-- ==========================================
START TRANSACTION;
SELECT id, name FROM attractions WHERE id = 20 AND name LIKE '%聚葵山%';

UPDATE attractions 
SET cover_image = '/public/images/新对话 (52).png',
    images = '["/public/images/新对话 (58).png", "/public/images/新对话 (60).png"]'
WHERE id = 20;

SELECT id, cover_image, images FROM attractions WHERE id = 20;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
