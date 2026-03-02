-- 梁平文旅项目：用户模拟数据高仿真更新脚本
-- 目标：替换死板的用户信息，关联真实的头像路径
-- 适用：重庆梁平文旅项目

SET FOREIGN_KEY_CHECKS = 0;

-- 1. 更新用户表 (users)
-- 密码 hash 值保持不变或设为通用值（假设原有逻辑使用加密存储，这里仅更新展示信息）

-- 用户 1: 资深背包客
UPDATE `users` SET 
    `username` = '梁平老兵', 
    `password_hash` = '$2a$10$hpWhBmnpX71WImDuv5K4VefqHhWztTmk5Oa5IFVOICN2D9nIwVsuG',
    `email` = 'laobing@lp.com', 
    `phone` = '13853221001', 
    `avatar_url` = '/public/images/u=1146390148,437789889&fm=253&fmt=auto&app=138&f=JPEG.heic' 
WHERE `id` = 1;

-- 用户 2: 摄影爱好者
UPDATE `users` SET 
    `username` = '光影行者', 
    `password_hash` = '$2a$10$hpWhBmnpX71WImDuv5K4VefqHhWztTmk5Oa5IFVOICN2D9nIwVsuG',
    `email` = 'camera@lp.com', 
    `phone` = '13953582002', 
    `avatar_url` = '/public/images/3_f9b12ee0.png' 
WHERE `id` = 2;

-- 用户 3: 美食达人
UPDATE `users` SET 
    `username` = '梁平张鸭子死忠粉', 
    `password_hash` = '$2a$10$hpWhBmnpX71WImDuv5K4VefqHhWztTmk5Oa5IFVOICN2D9nIwVsuG',
    `email` = 'foodie@lp.com', 
    `phone` = '13653373003', 
    `avatar_url` = '/public/images/64228b5021b9eed96012d1c658cbf8d3.jpg' 
WHERE `id` = 3;

-- 用户 4: 亲子游宝妈
UPDATE `users` SET 
    `username` = '悠悠妈咪', 
    `password_hash` = '$2a$10$hpWhBmnpX71WImDuv5K4VefqHhWztTmk5Oa5IFVOICN2D9nIwVsuG',
    `email` = 'yoyo@lp.com', 
    `phone` = '13553224004', 
    `avatar_url` = '/public/images/b3acf4242f1f84640861acd388e9d0ae.jpg' 
WHERE `id` = 4;

-- 用户 5: 传统文化爱好者
UPDATE `users` SET 
    `username` = '非遗传承守望者', 
    `password_hash` = '$2a$10$hpWhBmnpX71WImDuv5K4VefqHhWztTmk5Oa5IFVOICN2D9nIwVsuG',
    `email` = 'feiyi@lp.com', 
    `phone` = '13753665005', 
    `avatar_url` = '/public/images/favicon.png' 
WHERE `id` = 5;

-- 用户 6: 户外运动达人
UPDATE `users` SET 
    `username` = '竹海风行者', 
    `password_hash` = '$2a$10$hpWhBmnpX71WImDuv5K4VefqHhWztTmk5Oa5IFVOICN2D9nIwVsuG',
    `email` = 'hiker@lp.com', 
    `phone` = '13353586006', 
    `avatar_url` = '/public/images/QQ图片20230622122803.jpg' 
WHERE `id` = 6;

-- 用户 7: 退休教师
UPDATE `users` SET 
    `username` = '云卷云舒', 
    `password_hash` = '$2a$10$hpWhBmnpX71WImDuv5K4VefqHhWztTmk5Oa5IFVOICN2D9nIwVsuG',
    `email` = 'teacher@lp.com', 
    `phone` = '13153447007', 
    `avatar_url` = '/public/images/QQ图片20230622122821.jpg' 
WHERE `id` = 7;

-- 用户 8: 文艺青年
UPDATE `users` SET 
    `username` = '双桂湖畔的诗人', 
    `password_hash` = '$2a$10$hpWhBmnpX71WImDuv5K4VefqHhWztTmk5Oa5IFVOICN2D9nIwVsuG',
    `email` = 'poet@lp.com', 
    `phone` = '15853228008', 
    `avatar_url` = '/public/images/QQ图片20230622122837.jpg' 
WHERE `id` = 8;

-- 用户 9: 自驾游车友
UPDATE `users` SET 
    `username` = '一路向西', 
    `password_hash` = '$2a$10$hpWhBmnpX71WImDuv5K4VefqHhWztTmk5Oa5IFVOICN2D9nIwVsuG',
    `email` = 'driver@lp.com', 
    `phone` = '15953999009', 
    `avatar_url` = '/public/images/QQ图片20230629090458.png' 
WHERE `id` = 9;

-- 用户 10: 梁平本地向导
UPDATE `users` SET 
    `username` = '柚乡土著', 
    `password_hash` = '$2a$10$hpWhBmnpX71WImDuv5K4VefqHhWztTmk5Oa5IFVOICN2D9nIwVsuG',
    `email` = 'native@lp.com', 
    `phone` = '18853220010', 
    `avatar_url` = '/public/images/Snipaste_2025-12-15_14-54-00.png' 
WHERE `id` = 10;

SET FOREIGN_KEY_CHECKS = 1;
