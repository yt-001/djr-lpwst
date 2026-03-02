-- 梁平文旅项目：美食餐厅图片路径标准化更新脚本
-- 目标：根据本地图片文件分析结果，更新餐厅表的封面图与浏览图路径
-- 规则：识别图片命名规则，精准匹配餐厅记录，确保路径标准化

SET FOREIGN_KEY_CHECKS = 0;

-- ==========================================
-- 1. 张鸭子 (ID: 1) - 三代祖传秘制卤味
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (22).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (22).png"]'
WHERE `id` = 1;

-- ==========================================
-- 2. 稻香里私房菜 (ID: 2)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (23).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (23).png"]'
WHERE `id` = 2;

-- ==========================================
-- 3. 明达古镇客栈餐厅 (ID: 3)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (27).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (27).png"]'
WHERE `id` = 3;

-- ==========================================
-- 4. 百里竹海竹荪鸡 (ID: 4)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (26).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (26).png"]'
WHERE `id` = 4;

-- ==========================================
-- 5. 桂湖温泉餐厅 (ID: 5)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (24).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (24).png"]'
WHERE `id` = 5;

-- ==========================================
-- 6. 云岭下午茶 (ID: 6) - 映射为双桂湖畔下午茶
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (34).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (34).png"]'
WHERE `id` = 6;

-- ==========================================
-- 7. 合兴农家院 (ID: 7)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (29).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (29).png"]'
WHERE `id` = 7;

-- ==========================================
-- 8. 梁平老街名优小吃 (ID: 8)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (25).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (25).png"]'
WHERE `id` = 8;

-- ==========================================
-- 9. 柚乡园私房菜 (ID: 9)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (28).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (28).png"]'
WHERE `id` = 9;

-- ==========================================
-- 10. 古镇老茶馆 (ID: 10)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (36).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (36).png"]'
WHERE `id` = 10;

-- ==========================================
-- 11. 梁山老面馆 (ID: 11)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (35).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (35).png"]'
WHERE `id` = 11;

-- ==========================================
-- 12. 竹丰湖鱼鲜馆 (ID: 12)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (42).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (42).png"]'
WHERE `id` = 12;

-- ==========================================
-- 13. 蟠龙老腊肉店 (ID: 13)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (30).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (30).png"]'
WHERE `id` = 13;

-- ==========================================
-- 14. 都梁私房小院 (ID: 14) - 映射为竹尖私房菜
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (31).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (31).png"]'
WHERE `id` = 14;

-- ==========================================
-- 15. 礼让豆干老作坊 (ID: 15)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (39).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (39).png"]'
WHERE `id` = 15;

-- ==========================================
-- 16. 百里竹海人家 (ID: 16)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (37).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (37).png"]'
WHERE `id` = 16;

-- ==========================================
-- 17. 蟠龙洞山珍馆 (ID: 17)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (30).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (30).png"]'
WHERE `id` = 17;

-- ==========================================
-- 18. 万石耕春稻香餐厅 (ID: 18)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (32).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (32).png"]'
WHERE `id` = 18;

-- ==========================================
-- 19. 双桂湖畔下午茶 (ID: 19)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (34).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (34).png"]'
WHERE `id` = 19;

-- ==========================================
-- 20. 袁驿豆干直营店 (ID: 20)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (40).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (40).png"]'
WHERE `id` = 20;

-- ==========================================
-- 21. 都梁印象大酒楼 (ID: 21)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (33).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (33).png"]'
WHERE `id` = 21;

-- ==========================================
-- 22. 龙溪河鲜坊 (ID: 22)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (41).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (41).png"]'
WHERE `id` = 22;

-- ==========================================
-- 23. 梁山杂酱面馆 (ID: 23)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (35).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (35).png"]'
WHERE `id` = 23;

-- ==========================================
-- 24. 金带禅意素食 (ID: 24)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (43).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (43).png"]'
WHERE `id` = 24;

-- ==========================================
-- 25. 温泉养生汤锅 (ID: 25)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (38).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (38).png"]'
WHERE `id` = 25;

-- ==========================================
-- 26. 柚海庄园餐厅 (ID: 26)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (28).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (28).png"]'
WHERE `id` = 26;

-- ==========================================
-- 27. 云岭观景露台吧 (ID: 27)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (44).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (44).png"]'
WHERE `id` = 27;

-- ==========================================
-- 28. 竹丰湖渔家傲 (ID: 28)
-- ==========================================
UPDATE `restaurants` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (42).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (42).png"]'
WHERE `id` = 28;

SET FOREIGN_KEY_CHECKS = 1;
