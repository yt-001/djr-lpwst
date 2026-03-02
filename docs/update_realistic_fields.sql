-- 梁平文旅项目：基于现有数据的字段内容更新脚本
-- 目标：优化现有模拟数据，使其描述更真实、贴合梁平文旅场景
-- 适用：重庆梁平文旅项目

SET FOREIGN_KEY_CHECKS = 0;

-- 1. 更新景点描述 (Attractions)
UPDATE `attractions` SET `description` = '被誉为“梁平之肺”，是重庆市首个国家级湿地公园。拥有长达10公里的环湖步道，是散步、观鸟和赏落日的绝佳去处。' WHERE `id` = 1;
UPDATE `attractions` SET `description` = '保存完好的传统街巷与古建筑，漫步其中可以感受厚重的历史气息与淳朴的民风。' WHERE `id` = 2;
UPDATE `attractions` SET `description` = '拥有14万亩成片竹海，负氧离子极高，是避暑康养、回归自然的绝佳目的地。' WHERE `id` = 3;
UPDATE `attractions` SET `description` = '全方位展示梁平的历史变迁、非遗文化与民俗风情，是了解“中国寿竹之乡”的窗口。' WHERE `id` = 4;
UPDATE `attractions` SET `description` = '梁平城区的制高点，可远眺万石耕春美景，俯瞰双桂湖全貌。' WHERE `id` = 5;
UPDATE `attractions` SET `description` = '天然矿物温泉，包含多种室内外泡池，集康养、休闲、度假于一体。' WHERE `id` = 6;
UPDATE `attractions` SET `description` = '以农耕文化为主题，提供采摘、垂钓、农事体验等田园休闲项目。' WHERE `id` = 7;
UPDATE `attractions` SET `description` = '国家级非物质文化遗产展示馆，可以近距离观察木版年画的制作工艺。' WHERE `id` = 8;

-- 2. 更新餐厅描述与招牌菜 (Restaurants)
UPDATE `restaurants` SET `description` = '三代祖传秘制卤味，以“干、香、瘦、亮”闻名，梁平非遗美食代表。' WHERE `id` = 1;
UPDATE `restaurants` SET `description` = '主打双桂湖有机生态鱼及油渣系列特色菜，口味纯正，富有农家风情。' WHERE `id` = 2;
UPDATE `restaurants` SET `description` = '环境雅致，主打古镇特色菜肴，腊肉与手工豆干是不容错过的美味。' WHERE `id` = 3;
UPDATE `restaurants` SET `description` = '深山里的鲜味，选用百里竹海新鲜竹荪，配以土鸡慢熬，鲜美无比。' WHERE `id` = 4;
UPDATE `restaurants` SET `description` = '配套康养温泉，提供营养膳食与季节性养生汤品。' WHERE `id` = 5;
UPDATE `restaurants` SET `description` = '静谧的云岭之上，提供手冲咖啡与精致下午茶，适合放空小坐。' WHERE `id` = 6;
UPDATE `restaurants` SET `description` = '地道的田园风味，食材现摘现做，充满了大自然的气息。' WHERE `id` = 7;
UPDATE `restaurants` SET `description` = '汇集梁平各类名优小吃，一站式吃遍梁平味道。' WHERE `id` = 8;

-- 3. 更新住宿描述 (Accommodations)
UPDATE `accommodations` SET `description` = '临双桂湖而建，视野开阔，设施完善，提供高品质的湖畔度假体验。' WHERE `id` = 1;
UPDATE `accommodations` SET `description` = '古色古香的装修风格，环境幽静，体验慢节奏的古镇生活。' WHERE `id` = 2;
UPDATE `accommodations` SET `description` = '位于百里竹海腹地，推窗见绿，听鸟鸣入睡，非常适合康养避暑。' WHERE `id` = 3;
UPDATE `accommodations` SET `description` = '市中心繁华地段，交通便利，是商务出行与城市观光的优选。' WHERE `id` = 4;
UPDATE `accommodations` SET `description` = '纯正的乡野风情，体验自助采摘与农家大锅饭。' WHERE `id` = 5;
UPDATE `accommodations` SET `description` = '专为年轻人设计的社交空间，环境时尚简约，性价比极高。' WHERE `id` = 6;
UPDATE `accommodations` SET `description` = '全屋智能家居，超大落地窗，尽览双桂湖落日美景。' WHERE `id` = 7;
UPDATE `accommodations` SET `description` = '以茶文化为主题，提供沉浸式品茶体验，让身心回归平静。' WHERE `id` = 8;
UPDATE `accommodations` SET `description` = '集温泉泡浴与星级住宿于一体，私享独立泡池，尊享奢华康养。' WHERE `id` = 9;

-- 4. 更新非遗文化描述 (Intangible Culture)
UPDATE `intangible_culture` SET `description` = '起源于清代，构图饱满，色彩对比强烈，被誉为“中国民间艺术的瑰宝”。' WHERE `id` = 1;
UPDATE `intangible_culture` SET `description` = '选用优质寿竹，经过几十道手工工序编织而成，轻薄如绢，工艺精湛。' WHERE `id` = 2;
UPDATE `intangible_culture` SET `description` = '梁平柚作为“中国三大名柚”之一，其种植技艺包含独特的嫁接与管护传统。' WHERE `id` = 3;
UPDATE `intangible_culture` SET `description` = '唱腔高亢，表演细腻，是巴渝民间戏剧艺术的活化石。' WHERE `id` = 4;
UPDATE `intangible_culture` SET `description` = '传承百年的传统酒曲酿造工艺，酒香醇厚，回味悠长。' WHERE `id` = 5;

-- 5. 扩充住宿类型 (Accommodation Types)
INSERT IGNORE INTO `accommodation_types` (`id`, `name`, `description`) VALUES 
(6, '精品酒店', '独特的设计风格，个性化的定制服务，追求高品质生活。'),
(7, '帐篷营地', '亲近自然，星空露营，体验不一样的野趣生活。');

-- 6. 扩充住宿数据 (Accommodations)
INSERT IGNORE INTO `accommodations` (`id`, `name`, `description`, `type_id`, `location`, `latitude`, `longitude`, `cover_image`, `images`, `price_per_night`, `capacity`, `contact_phone`) VALUES 
(11, '梁平宾馆', '梁平老牌星级宾馆，地处闹市区，交通便捷，配套设施齐全。', 1, '梁平区人民西路', 30.6720, 107.7940, '/public/images/acc_lpbg.jpg', '["/public/images/acc_lpbg_1.jpg"]', 288.00, 2, '023-53221234'),
(12, '猎神三合院民宿', '典型的巴渝三合院建筑，青砖黛瓦，古朴典雅，是体验当地民俗的绝佳去处。', 2, '梁平区竹山镇猎神村', 30.7025, 107.8235, '/public/images/acc_lsshy.jpg', '["/public/images/acc_lsshy_1.jpg"]', 688.00, 4, '023-53582222'),
(13, '桂香园大酒店', '位于双桂街道，环境优雅，内部装修豪华，提供餐饮、住宿、会议一体化服务。', 1, '梁平区双桂街道', 30.6760, 107.8105, '/public/images/acc_gxy.jpg', '["/public/images/acc_gxy_1.jpg"]', 398.00, 2, '023-53229999'),
(14, '柚乡度假村', '以梁平柚为主题的生态度假村，环境优美，适合亲子出游和单位团建。', 3, '梁平区合兴镇', 30.6590, 107.8410, '/public/images/acc_yxdjc.jpg', '["/public/images/acc_yxdjc_1.jpg"]', 198.00, 3, '023-53668888'),
(15, '竹丰湖星空营地', '坐落在竹丰湖畔，提供高端帐篷住宿，夜晚可以仰望繁星，听涛入眠。', 7, '梁平区竹山镇竹丰湖', 30.7050, 107.8280, '/public/images/acc_zfhyy.jpg', '["/public/images/acc_zfhyy_1.jpg"]', 458.00, 2, '023-53589999'),
(16, '龙溪河畔民宿', '紧邻龙溪河，河水清澈，环境宁静，提供垂钓和乡村特色美食。', 2, '梁平区屏锦镇', 30.5520, 107.7210, '/public/images/acc_lxhp.jpg', '["/public/images/acc_lxhp_1.jpg"]', 238.00, 2, '023-53445555'),
(17, '蟠龙古镇客栈', '位于古镇核心区，保留了古老的木质结构，让你瞬间穿越回旧时光。', 5, '梁平区蟠龙镇', 30.6410, 107.8820, '/public/images/acc_plgz.jpg', '["/public/images/acc_plgz_1.jpg"]', 168.00, 2, '023-53991111'),
(18, '万石耕春精品民宿', '位于万石耕春景区内，四周稻田环绕，推窗即可呼吸稻香，体验归隐田园。', 6, '梁平区安胜镇', 30.7130, 107.8550, '/public/images/acc_wsgc_ms.jpg', '["/public/images/acc_wsgc_ms_1.jpg"]', 528.00, 2, '023-53661111'),
(19, '双桂禅意精品酒店', '紧邻双桂堂，装修融入禅文化元素，安静素雅，是静心修养的佳地。', 6, '梁平区金带街道', 30.6545, 107.7515, '/public/images/acc_sgcy.jpg', '["/public/images/acc_sgcy_1.jpg"]', 428.00, 2, '023-53371111'),
(20, '滑石寨云端民宿', '位于滑石古寨顶端，视野极佳，清晨可观云海，夜晚可看繁星。', 2, '梁平区金带街道滑石村', 30.6325, 107.7348, '/public/images/acc_hszyd.jpg', '["/public/images/acc_hszyd_1.jpg"]', 388.00, 2, '023-53331111');

-- 7. 扩充景点数据 (Attractions)
INSERT IGNORE INTO `attractions` (`id`, `name`, `description`, `location`, `latitude`, `longitude`, `cover_image`, `images`, `open_hours`, `ticket_price`, `contact_phone`) VALUES 
(9, '蟠龙洞景区', '国家3A级旅游景区，典型的喀斯特溶洞，洞内石钟乳、石笋形态各异，冬暖夏凉，被誉为“地下艺术宫殿”。', '梁平区蟠龙镇', 30.6420, 107.8830, '/public/images/att_pld.jpg', '["/public/images/att_pld_1.jpg"]', '09:00-17:00', 35.00, '023-53992222'),
(10, '梁平柚海', '中国名柚之乡的核心区，拥有万亩优质梁平柚基地，每年秋季金果挂满枝头，是采摘体验和乡村摄影的绝佳地。', '梁平区合兴街道', 30.6595, 107.8420, '/public/images/att_yh.jpg', '["/public/images/att_yh_1.jpg"]', '全天', 0.00, '023-53663333'),
(11, '猎神村童话森林', '位于百里竹海深处，将自然景观与童话元素结合，拥有树屋、滑道等互动设施，非常适合亲子游玩。', '梁平区竹山镇猎神村', 30.7030, 107.8240, '/public/images/att_thsl.jpg', '["/public/images/att_thsl_1.jpg"]', '08:30-18:00', 20.00, '023-53584444');

-- 8. 扩充非遗文化数据 (Intangible Culture)
INSERT IGNORE INTO `intangible_culture` (`id`, `name`, `description`, `type`, `inheritor`, `cover_image`, `images`) VALUES 
(6, '梁平草席', '历史悠久，选用本地优质灯芯草，经过手工编织而成，具有凉爽吸汗、折叠不损的特点。', '传统技艺', '周老师', '/public/images/cult_cx.jpg', '["/public/images/cult_cx_1.jpg"]'),
(7, '礼让豆干制作技艺', '梁平礼让镇的特色美食，以“干、薄、细”著称，口感筋道，是梁平著名的地理标志产品。', '传统技艺', '张师傅', '/public/images/cult_lrdg.jpg', '["/public/images/cult_lrdg_1.jpg"]');

-- 9. 扩充餐厅数据 (Restaurants)
INSERT IGNORE INTO `restaurants` (`id`, `category_id`, `name`, `description`, `location`, `latitude`, `longitude`, `cover_image`, `images`, `open_hours`, `price_range`, `contact_phone`, `rating`) VALUES 
(9, 2, '柚乡园私房菜', '将梁平柚融入传统川菜，创新推出柚皮扣肉、柚香排骨等特色菜，清新爽口不油腻。', '梁平区合兴街道', 30.6600, 107.8430, '/public/images/res_yxy.jpg', '["/public/images/res_yxy_1.jpg"]', '10:00-21:00', '￥60-100', '023-53664444', 4.8),
(10, 3, '古镇老茶馆', '保留了清代建筑风格，提供地道的盖碗茶和当地特色点心，是感受慢生活的绝佳场所。', '梁平区明达古镇', 30.6925, 107.7810, '/public/images/res_gzlcg.jpg', '["/public/images/res_gzlcg_1.jpg"]', '08:00-18:00', '￥10-30', '023-53881111', 4.6);

-- 10. 扩充菜品数据 (Restaurant Dishes)
INSERT IGNORE INTO `restaurant_dishes` (`id`, `restaurant_id`, `category_id`, `name`, `description`, `price`, `image_url`, `is_recommended`, `sort_order`) VALUES 
(28, 9, 1, '特色柚皮扣肉', '选用肥瘦相间的五花肉，配以秘制处理的柚子皮，软糯清香。', 58.00, '/public/images/dish_ypkr.jpg', 1, 1),
(29, 9, 2, '柚香排骨', '排骨炸至酥脆，裹上酸甜适中的柚子酱汁。', 48.00, '/public/images/dish_yxpg.jpg', 1, 2),
(30, 10, 4, '古镇手工糍粑', '现打糍粑，软糯Q弹，配上黄豆粉和红糖浆。', 15.00, '/public/images/dish_sgcb.jpg', 1, 1);

-- 11. 扩充评论数据 (Comments)
INSERT IGNORE INTO `comments` (`user_id`, `attraction_id`, `restaurant_id`, `culture_id`, `accommodation_id`, `content`, `rating`, `is_approved`) VALUES 
(1, 9, NULL, NULL, NULL, '蟠龙洞里面的钟乳石真的太漂亮了，大自然的鬼斧神工，夏天进去真的很凉快！', 5, 1),
(2, 10, NULL, NULL, NULL, '秋天的柚海真的太壮观了，到处都是柚子的清香，摘了一筐带回家，水分很足。', 5, 1),
(3, NULL, 9, NULL, NULL, '第一次吃到柚子皮做的扣肉，竟然一点都不腻，还有淡淡的清香，强烈推荐！', 5, 1),
(4, NULL, NULL, 6, NULL, '买了一张梁平草席给家里的老人，做工非常精细，确实比市面上的好很多。', 5, 1),
(5, NULL, NULL, NULL, 12, '三合院民宿很有味道，老板娘很热情，还带我们去体验了竹编，非常开心的旅程。', 5, 1),
(6, 1, NULL, NULL, NULL, '双桂湖的落日真的绝了，每天傍晚来跑跑步，感觉生活都慢下来了。', 5, 1),
(7, 2, NULL, NULL, NULL, '百里竹海非常适合避暑，空气质量没得说，就是建议多穿件薄外套，山上有点凉。', 4, 1),
(8, NULL, 1, NULL, NULL, '张鸭子确实是梁平的名片，每次路过都要买两只带给亲戚朋友。', 5, 1),
(9, NULL, 4, NULL, NULL, '竹荪汤非常鲜，服务员说都是当天现挖的，难怪口感这么脆嫩。', 5, 1),
(10, NULL, NULL, NULL, 15, '在竹丰湖边露营是一种不一样的体验，晚上看星星非常清楚，设施也很干净。', 4, 1);

-- 12. 继续扩充景点数据 (Attractions) - 翻倍计划
INSERT IGNORE INTO `attractions` (`id`, `name`, `description`, `location`, `latitude`, `longitude`, `cover_image`, `images`, `open_hours`, `ticket_price`, `contact_phone`) VALUES 
(12, '龙溪河漂流', '位于屏锦镇，河水清澈见底，两岸绿树成荫。全长约5公里，落差适中，是夏季消暑、亲水互动的绝佳选择。', '梁平区屏锦镇', 30.5510, 107.7200, '/public/images/att_lxhpl.jpg', '["/public/images/att_lxhpl_1.jpg"]', '09:00-16:30', 88.00, '023-53441111'),
(13, '牛头山风景区', '梁平著名的自然风景区，山上怪石嶙峋，古木参天。登顶可俯瞰周边数个乡镇的田园风光，气势磅礴。', '梁平区蟠龙镇', 30.6450, 107.8900, '/public/images/att_nts.jpg', '["/public/images/att_nts_1.jpg"]', '08:00-18:00', 0.00, '023-53993333'),
(14, '礼让渔儿村', '以渔文化为特色的美丽乡村，拥有大型生态养殖基地。可以体验垂钓、品尝百鱼宴，感受水乡风情。', '梁平区礼让镇', 30.6210, 107.6820, '/public/images/att_yec.jpg', '["/public/images/att_yec_1.jpg"]', '全天', 0.00, '023-53771111'),
(15, '赤牛城遗址', '南宋时期的抗蒙名城，具有极高的历史研究价值。遗址依山而建，地势险要，古城墙和炮台至今清晰可见。', '梁平区双桂街道', 30.6850, 107.7850, '/public/images/att_cncyz.jpg', '["/public/images/att_cncyz_1.jpg"]', '全天', 0.00, '023-53226666'),
(16, '金带万石耕春·稻香世界', '现代农业示范区，集中展示了梁平深厚的稻作文化。秋季万亩稻浪随风起伏，如同一幅巨大的金黄色油画。', '梁平区金带街道', 30.6550, 107.7450, '/public/images/att_dxsj.jpg', '["/public/images/att_dxsj_1.jpg"]', '全天', 0.00, '023-53372222'),
(17, '竹丰湖水利风景区', '位于百里竹海的核心，湖水碧绿如翠玉镶嵌在群山之中。湖边竹林掩映，环境幽静，是划船、写生的佳地。', '梁平区竹山镇', 30.7060, 107.8290, '/public/images/att_zfh.jpg', '["/public/images/att_zfh_1.jpg"]', '08:30-17:30', 0.00, '023-53585555'),
(18, '梁平文庙', '始建于明代，建筑精美，规制宏大。是当地祭孔和开展传统文化教育的重要场所，散发着浓郁的儒家文化气息。', '梁平区文化街', 30.6750, 107.8050, '/public/images/att_wm.jpg', '["/public/images/att_wm_1.jpg"]', '09:00-17:00', 0.00, '023-53227777'),
(19, '寿海景区', '以长寿文化为主题，拥有巨大的“寿”字石刻和长寿步道。森林覆盖率极高，空气中负氧离子含量丰富。', '梁平区竹山镇', 30.7080, 107.8350, '/public/images/att_sh.jpg', '["/public/images/att_sh_1.jpg"]', '08:30-17:30', 25.00, '023-53583333'),
(20, '聚葵山风景区', '山上种有大量葵树，每逢花开时节，漫山遍野一片金黄。山上还有古老的寨门和庙宇，历史感厚重。', '梁平区聚葵镇', 30.5850, 107.7520, '/public/images/att_jks.jpg', '["/public/images/att_jks_1.jpg"]', '全天', 0.00, '023-53442222');

-- 13. 继续扩充住宿数据 (Accommodations) - 翻倍计划
INSERT IGNORE INTO `accommodations` (`id`, `name`, `description`, `type_id`, `location`, `latitude`, `longitude`, `cover_image`, `images`, `price_per_night`, `capacity`, `contact_phone`) VALUES 
(21, '都梁商务宾馆', '位于梁平老城区，靠近步行街，生活便利，房间价格实惠，是背包客的首选。', 4, '梁平区梁山街道', 30.6710, 107.8010, '/public/images/acc_dlsw.jpg', '["/public/images/acc_dlsw_1.jpg"]', 128.00, 2, '023-53221122'),
(22, '屏锦印象酒店', '屏锦镇规模最大的综合性酒店，提供标准间、豪华套房及大型餐饮会议设施。', 1, '梁平区屏锦镇', 30.5530, 107.7220, '/public/images/acc_pjyx.jpg', '["/public/images/acc_pjyx_1.jpg"]', 258.00, 2, '023-53443344'),
(23, '竹海观云民宿', '建在百里竹海的山脊线上，可以俯瞰整个竹海云雾缭绕的景观，氛围极佳。', 2, '梁平区竹山镇', 30.7040, 107.8250, '/public/images/acc_zhgy.jpg', '["/public/images/acc_zhgy_1.jpg"]', 488.00, 2, '023-53584455'),
(24, '礼让鱼家大院', '结合当地渔文化设计的庭院民宿，可以在院子里自助烧烤、钓鱼，非常有生活气息。', 3, '梁平区礼让镇', 30.6220, 107.6830, '/public/images/acc_lyyj.jpg', '["/public/images/acc_lyyj_1.jpg"]', 188.00, 3, '023-53772233'),
(25, '新盛大酒店', '位于新盛镇中心，设施整洁，服务热情，价格亲民，适合乡镇出差人员入住。', 1, '梁平区新盛镇', 30.7520, 107.8120, '/public/images/acc_xsjd.jpg', '["/public/images/acc_xsjd_1.jpg"]', 158.00, 2, '023-53884455'),
(26, '明达时光里客栈', '由古镇旧民居修缮而成，保留了大量的木质元素和天井设计，非常有格调。', 5, '梁平区明达镇', 30.6930, 107.7820, '/public/images/acc_mdsg.jpg', '["/public/images/acc_mdsg_1.jpg"]', 218.00, 2, '023-53885566'),
(27, '蟠龙溶洞度假酒店', '紧邻蟠龙洞景区，装修风格融入了溶洞元素，冬暖夏凉，居住体验非常独特。', 1, '梁平区蟠龙镇', 30.6430, 107.8840, '/public/images/acc_pldd.jpg', '["/public/images/acc_pldd_1.jpg"]', 328.00, 2, '023-53994455'),
(28, '安胜稻田营地', '直接搭建在稻田边的装配式民宿，夜晚伴着蛙声入睡，清晨在稻香中醒来。', 7, '梁平区安胜镇', 30.7140, 107.8560, '/public/images/acc_asdt.jpg', '["/public/images/acc_asdt_1.jpg"]', 398.00, 2, '023-53662233'),
(29, '聚葵山庄', '山林式避暑山庄，提供大排档、棋牌娱乐和独栋小木屋，是当地人聚会的常去地。', 3, '梁平区聚葵镇', 30.5860, 107.7530, '/public/images/acc_jksz.jpg', '["/public/images/acc_jksz_1.jpg"]', 168.00, 4, '023-53446677'),
(30, '梁平云端轻奢公寓', '高层电梯公寓，视野极佳，内部配备极米投影仪和全套厨具，适合长租和家庭游。', 5, '梁平区双桂街道', 30.6780, 107.8080, '/public/images/acc_ydqs.jpg', '["/public/images/acc_ydqs_1.jpg"]', 298.00, 3, '023-53225566'),
(31, '金带禅语民宿', '位于双桂堂后山，环境极度安静，装修以素色为主，提供素食早餐和禅修体验。', 6, '梁平区金带街道', 30.6560, 107.7530, '/public/images/acc_jdcy.jpg', '["/public/images/acc_jdcy_1.jpg"]', 458.00, 2, '023-53373344'),
(32, '龙门镇悦来宾馆', '龙门镇街上的老字号宾馆，虽然设施略显陈旧但胜在干净整洁，老板娘非常实在。', 4, '梁平区龙门镇', 30.6120, 107.9210, '/public/images/acc_lmyl.jpg', '["/public/images/acc_lmyl_1.jpg"]', 98.00, 2, '023-53995566'),
(33, '百里竹海观星台民宿', '位于竹海最高点附近，拥有超大的全景玻璃露台，是观赏星空和云海的绝佳位置。', 2, '梁平区竹山镇', 30.7090, 107.8360, '/public/images/acc_zhgx.jpg', '["/public/images/acc_zhgx_1.jpg"]', 558.00, 2, '023-53586677'),
(34, '袁驿豆香人家', '袁驿镇上的特色民宿，住在这里可以亲手参与豆干的制作过程，非常有意思。', 3, '梁平区袁驿镇', 30.5830, 107.6550, '/public/images/acc_yydx.jpg', '["/public/images/acc_yydx_1.jpg"]', 148.00, 2, '023-53883344'),
(35, '梁平瑞丰大酒店', '靠近梁平南站（高铁站），现代商务风格，提供接送站服务，出行非常方便。', 1, '梁平区双桂街道', 30.6650, 107.8150, '/public/images/acc_rfdjd.jpg', '["/public/images/acc_rfdjd_1.jpg"]', 388.00, 2, '023-53228899');

-- 14. 扩充更多美食餐厅 (Restaurants) - 翻倍计划
INSERT IGNORE INTO `restaurants` (`id`, `category_id`, `name`, `description`, `location`, `latitude`, `longitude`, `cover_image`, `images`, `open_hours`, `price_range`, `contact_phone`, `rating`) VALUES 
(11, 1, '梁山老面馆', '专注于梁平传统手工面条，杂酱面和牛肉面是店内双绝，面条筋道，臊子香浓。', '梁平区梁山街道', 30.6730, 107.7950, '/public/images/res_lslm.jpg', '["/public/images/res_lslm_1.jpg"]', '06:00-20:00', '￥10-25', '023-53224455', 4.7),
(12, 5, '竹丰湖鱼鲜馆', '紧邻竹丰湖，主打各种做法的湖鱼，鱼肉鲜嫩，配上农家自制咸菜，味道一绝。', '梁平区竹山镇', 30.7070, 107.8300, '/public/images/res_zfhf.jpg', '["/public/images/res_zfhf_1.jpg"]', '10:00-21:00', '￥70-120', '023-53587788', 4.6),
(13, 3, '蟠龙老腊肉店', '选用蟠龙镇高山上喂养的土猪肉，经过传统烟熏工艺制作，肥而不腻，瘦而不柴。', '梁平区蟠龙镇', 30.6440, 107.8850, '/public/images/res_pllr.jpg', '["/public/images/res_pllr_1.jpg"]', '09:00-19:00', '￥40-90', '023-53996677', 4.8),
(14, 2, '都梁私房小院', '闹中取静的私房菜馆，菜品精致且富有创意，是商务宴请和家庭聚会的优选。', '梁平区桂香路', 30.6770, 107.8020, '/public/images/res_dlxf.jpg', '["/public/images/res_dlxf_1.jpg"]', '11:00-21:30', '￥100-200', '023-53226677', 4.9);

-- 15. 扩充更多非遗文化 (Intangible Culture)
INSERT IGNORE INTO `intangible_culture` (`id`, `name`, `description`, `type`, `inheritor`, `cover_image`, `images`) VALUES 
(8, '梁平抬儿调', '流传于梁平山区的民间音乐，节奏欢快，唱词通俗易懂，反映了山民的劳动生活。', '传统音乐', '李老师', '/public/images/cult_tet.jpg', '["/public/images/cult_tet_1.jpg"]'),
(9, '梁平土陶制作技艺', '使用当地优质陶土，经过拉坯、雕刻、上釉、烧制等多道工序，造型古朴，极具民族特色。', '传统技艺', '王师傅', '/public/images/cult_tt.jpg', '["/public/images/cult_tt_1.jpg"]');

SET FOREIGN_KEY_CHECKS = 1;
