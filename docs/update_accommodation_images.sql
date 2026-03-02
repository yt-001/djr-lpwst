-- 梁平文旅项目：住宿图片路径精准更新脚本 (精简版 - 22条数据)
-- 目标：根据用户删除数据后的最新住宿列表（ID 1-22），精准匹配封面图片
-- 规则：图片路径标准化为 /public/images/，且 images 字段包含封面图

SET FOREIGN_KEY_CHECKS = 0;

-- ==========================================
-- 1. 梁平双桂湖度假酒店 (ID: 1)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片.png',
    `images` = '["/public/images/生成住宿模拟封面图片.png"]'
WHERE `id` = 1;

-- ==========================================
-- 2. 梁平明达古镇客栈 (ID: 2)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/新对话 (5).png',
    `images` = '["/public/images/新对话 (5).png"]'
WHERE `id` = 2;

-- ==========================================
-- 3. 竹海里民宿 (ID: 3)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (2).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (2).png"]'
WHERE `id` = 3;

-- ==========================================
-- 4. 梁平城市酒店 (ID: 4)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (1).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (1).png"]'
WHERE `id` = 4;

-- ==========================================
-- 5. 稻香农家乐 (ID: 5)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (4).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (4).png"]'
WHERE `id` = 5;

-- ==========================================
-- 6. 双桂湖青年旅舍 (ID: 6)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (3).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (3).png"]'
WHERE `id` = 6;

-- ==========================================
-- 7. 云岭观景公寓 (ID: 7)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (5).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (5).png"]'
WHERE `id` = 7;

-- ==========================================
-- 8. 茶语民宿 (ID: 8)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (6).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (6).png"]'
WHERE `id` = 8;

-- ==========================================
-- 9. 桂湖温泉酒店 (ID: 9)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (11).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (11).png"]'
WHERE `id` = 9;

-- ==========================================
-- 10. 竹影庭院民宿 (ID: 10)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (13).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (13).png"]'
WHERE `id` = 10;

-- ==========================================
-- 11. 梁平宾馆 (ID: 11)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (7).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (7).png"]'
WHERE `id` = 11;

-- ==========================================
-- 12. 猎神三合院民宿 (ID: 12)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (9).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (9).png"]'
WHERE `id` = 12;

-- ==========================================
-- 13. 桂香园大酒店 (ID: 13)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (10).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (10).png"]'
WHERE `id` = 13;

-- ==========================================
-- 14. 柚乡度假村 (ID: 14)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (12).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (12).png"]'
WHERE `id` = 14;

-- ==========================================
-- 15. 竹丰湖星空营地 (ID: 15)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (14).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (14).png"]'
WHERE `id` = 15;

-- ==========================================
-- 16. 龙溪河畔民宿 (ID: 16)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (15).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (15).png"]'
WHERE `id` = 16;

-- ==========================================
-- 17. 蟠龙古镇客栈 (ID: 17)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (8).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (8).png"]'
WHERE `id` = 17;

-- ==========================================
-- 18. 万石耕春精品民宿 (ID: 18)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (16).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (16).png"]'
WHERE `id` = 18;

-- ==========================================
-- 19. 双桂禅意精品酒店 (ID: 19)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (19).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (19).png"]'
WHERE `id` = 19;

-- ==========================================
-- 20. 滑石寨云端民宿 (ID: 20)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (18).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (18).png"]'
WHERE `id` = 20;

-- ==========================================
-- 21. 都梁商务宾馆 (ID: 21)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (20).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (20).png"]'
WHERE `id` = 21;

-- ==========================================
-- 22. 屏锦印象酒店 (ID: 22)
-- ==========================================
UPDATE `accommodations` SET 
    `cover_image` = '/public/images/生成住宿模拟封面图片 (21).png',
    `images` = '["/public/images/生成住宿模拟封面图片 (21).png"]'
WHERE `id` = 22;

SET FOREIGN_KEY_CHECKS = 1;
