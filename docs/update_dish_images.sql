-- 梁平文旅项目：菜品图片路径标准化更新脚本
-- 目标：根据提供的图片文件，更新菜品表的 image_url 字段
-- 规则：图片路径标准化为 /public/images/，并循环使用提供的图片

SET FOREIGN_KEY_CHECKS = 0;

-- 菜品图片列表 (共19张)
-- /public/images/菜品封面图片生成需求(1).png
-- ...
-- /public/images/菜品封面图片生成需求(19).png

-- 更新 ID 1-30 的菜品图片
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (1).png' WHERE `id` = 1;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (2).png' WHERE `id` = 2;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (3).png' WHERE `id` = 3;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (4).png' WHERE `id` = 4;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (5).png' WHERE `id` = 5;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (6).png' WHERE `id` = 6;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (7).png' WHERE `id` = 7;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (8).png' WHERE `id` = 8;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (9).png' WHERE `id` = 9;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (10).png' WHERE `id` = 10;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (11).png' WHERE `id` = 11;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (12).png' WHERE `id` = 12;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (13).png' WHERE `id` = 13;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (14).png' WHERE `id` = 14;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (15).png' WHERE `id` = 15;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (16).png' WHERE `id` = 16;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (17).png' WHERE `id` = 17;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (18).png' WHERE `id` = 18;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (19).png' WHERE `id` = 19;
-- 循环使用图片 
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (1).png' WHERE `id` = 20;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (2).png' WHERE `id` = 21;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (3).png' WHERE `id` = 22;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (4).png' WHERE `id` = 23;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (5).png' WHERE `id` = 24;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (6).png' WHERE `id` = 25;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (7).png' WHERE `id` = 26;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (8).png' WHERE `id` = 27;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (9).png' WHERE `id` = 28;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (10).png' WHERE `id` = 29;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (11).png' WHERE `id` = 30;
 
-- 更新 ID 100-141 的菜品图片 
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (12).png' WHERE `id` = 100;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (13).png' WHERE `id` = 101;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (14).png' WHERE `id` = 102;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (15).png' WHERE `id` = 103;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (16).png' WHERE `id` = 104;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (17).png' WHERE `id` = 105;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (18).png' WHERE `id` = 106;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (19).png' WHERE `id` = 107;
-- 循环使用图片 
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (1).png' WHERE `id` = 108;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (2).png' WHERE `id` = 109;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (3).png' WHERE `id` = 110;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (4).png' WHERE `id` = 111;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (5).png' WHERE `id` = 112;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (6).png' WHERE `id` = 113;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (7).png' WHERE `id` = 114;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (8).png' WHERE `id` = 115;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (9).png' WHERE `id` = 116;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (10).png' WHERE `id` = 117;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (11).png' WHERE `id` = 118;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (12).png' WHERE `id` = 119;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (13).png' WHERE `id` = 120;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (14).png' WHERE `id` = 121;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (15).png' WHERE `id` = 122;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (16).png' WHERE `id` = 123;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (17).png' WHERE `id` = 124;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (18).png' WHERE `id` = 125;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (19).png' WHERE `id` = 126;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (1).png' WHERE `id` = 127;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (2).png' WHERE `id` = 128;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (3).png' WHERE `id` = 129;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (4).png' WHERE `id` = 130;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (5).png' WHERE `id` = 131;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (6).png' WHERE `id` = 132;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (7).png' WHERE `id` = 133;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (8).png' WHERE `id` = 134;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (9).png' WHERE `id` = 135;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (10).png' WHERE `id` = 136;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (11).png' WHERE `id` = 137;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (12).png' WHERE `id` = 138;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (13).png' WHERE `id` = 139;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (14).png' WHERE `id` = 140;
UPDATE `restaurant_dishes` SET `image_url` = '/public/images/菜品封面图片生成需求 (15).png' WHERE `id` = 141;
 
SET FOREIGN_KEY_CHECKS = 1; 
