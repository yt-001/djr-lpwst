-- 更新梁平非遗文化表数据，共23条真实模拟数据
-- 涵盖国家级、省级、市级、区级非遗项目
-- 数据贴合真实场景，包含真实传承人、项目描述及类型

-- 先清空旧的非遗数据（如果需要保持ID连续，也可以用 DELETE）
TRUNCATE TABLE `intangible_culture`;

INSERT INTO `intangible_culture` (`id`, `name`, `description`, `type`, `inheritor`, `cover_image`, `images`) VALUES
(1, '梁平木版年画', '梁平木版年画起源于明代嘉靖年间，是重庆市梁平区境内的一种民间美术。其艺术特点是构图饱满、色彩鲜艳、造型夸张。2006年被列入第一批国家级非物质文化遗产名录。', '民间美术 (国家级)', '莫志录', '/public/images/culture_nianhua.jpg', '["/public/images/culture_nianhua_1.jpg", "/public/images/culture_nianhua_2.jpg"]'),
(2, '梁平竹帘', '梁平竹帘画是中国传统工艺美术品，以竹丝为经纬，手工编织而成，并由书画家在帘上作画。其工艺精湛，薄如蝉翼，曾作为国礼赠送给外国友人。2008年列入国家级非遗。', '传统手工艺 (国家级)', '牟秉衡', '/public/images/culture_zhulian.jpg', '["/public/images/culture_zhulian_1.jpg", "/public/images/culture_zhulian_2.jpg"]'),
(3, '梁平癞子锣鼓', '梁平癞子锣鼓是流行于梁平一带的民间器乐，因其节奏变化丰富、打击力度强劲而得名。其演奏形式多样，既能表现喜庆欢快，也能模拟自然音响。2006年列入国家级非遗。', '传统音乐 (国家级)', '陆泽建', '/public/images/culture_luogu.jpg', '["/public/images/culture_luogu_1.jpg"]'),
(4, '梁山灯戏', '梁山灯戏是流行于梁平一带的传统戏剧，以灯戏为基础，融合了民间小调和川剧元素，表演幽默诙谐，贴近生活。2006年列入国家级非遗。', '传统戏剧 (国家级)', '万圣才', '/public/images/culture_dengxi.jpg', '["/public/images/culture_dengxi_1.jpg"]'),
(5, '梁平抬儿调', '梁平抬儿调是梁平山歌的一种，起源于抬运重物时的号子，后演变为一种独具特色的民间演唱形式，旋律高亢嘹亮。2008年列入国家级非遗。', '传统音乐 (国家级)', '贺江华', '/public/images/culture_taierdiao.jpg', '["/public/images/culture_taierdiao_1.jpg"]'),
(6, '梁平二黄', '梁平二黄是流行于梁平的传统戏曲，其唱腔深受秦腔和汉调影响，具有浑厚、质朴的艺术风格，是研究川渝戏曲演变的重要素材。2011年列入国家级非遗。', '传统戏剧 (国家级)', '陈中和', '/public/images/culture_erhuang.jpg', '["/public/images/culture_erhuang_1.jpg"]'),
(7, '梁平草把龙', '梁平草把龙是流行于梁平的一种民间舞蹈，用稻草扎制成龙，舞动时动作舒展大方，寓意五谷丰登、祈求平安。2014年列入重庆省级非遗。', '传统舞蹈 (省级)', '严守全', '/public/images/culture_caobalong.jpg', '["/public/images/culture_caobalong_1.jpg"]'),
(8, '梁平礼让草席', '梁平礼让草席有着上千年的历史，选用优质龙须草经多道工序手工编织，具有席面平整、质地柔软、清香怡人的特点。', '传统手工艺 (省级)', '王文德', '/public/images/culture_caoxi.jpg', '["/public/images/culture_caoxi_1.jpg"]'),
(9, '梁平柚制作技艺', '梁平柚是“中国三大名柚”之一。其制作技艺涵盖了育苗、嫁接、修剪及采后加工等一整套传统流程，体现了当地劳动人民的智慧。', '传统技艺 (省级)', '张文茂', '/public/images/culture_you.jpg', '["/public/images/culture_you_1.jpg"]'),
(10, '梁平甜茶制作技艺', '梁平甜茶选用当地特有的野生甜茶树叶，采用传统炒制和自然晾晒工艺，口感清爽回甘，具有清热解毒之功效。', '传统技艺 (市级)', '李大清', '/public/images/culture_tiancha.jpg', '["/public/images/culture_tiancha_1.jpg"]'),
(11, '梁平土陶制作技艺', '梁平土陶选用当地优质陶土，经手工拉坯、雕刻、施釉、龙窑烧制而成，产品古朴大方，具有极强的实用性和艺术价值。', '传统手工艺 (市级)', '周长明', '/public/images/culture_tutao.jpg', '["/public/images/culture_tutao_1.jpg"]'),
(12, '梁平剪纸', '梁平剪纸以当地风俗民情为题材，线条细腻流畅，构图巧妙。常用于窗花、婚庆、节庆等场合。', '民间美术 (市级)', '赵美英', '/public/images/culture_jianzhi.jpg', '["/public/images/culture_jianzhi_1.jpg"]'),
(13, '梁平刺绣', '梁平刺绣吸取了苏绣和蜀绣的精华，形成了色彩亮丽、针法活泼、极富地方特色的艺术风格。', '民间美术 (市级)', '刘素珍', '/public/images/culture_cixiu.jpg', '["/public/images/culture_cixiu_1.jpg"]'),
(14, '梁平竹雕', '梁平竹雕以当地盛产的慈竹、楠竹为原材料，采用浮雕、透雕、圆雕等技法，刻画人物、花鸟、山水，栩栩如生。', '传统手工艺 (区级)', '孙开才', '/public/images/culture_zhudiao.jpg', '["/public/images/culture_zhudiao_1.jpg"]'),
(15, '梁平蓝染技艺', '梁平蓝染采用纯天然植物靛青作为染料，通过夹缬、扎染等传统技法，在纯棉布料上呈现出蓝底白花的古朴美感。', '传统手工艺 (区级)', '吴顺芳', '/public/images/culture_lanran.jpg', '["/public/images/culture_lanran_1.jpg"]'),
(16, '梁平糖画', '梁平糖画艺人以糖液为墨，以石板为纸，挥洒自如，瞬间勾勒出飞禽走兽、英雄人物，既可观赏又可食用。', '传统手工艺 (区级)', '郑平安', '/public/images/culture_tanghua.jpg', '["/public/images/culture_tanghua_1.jpg"]'),
(17, '梁平面塑', '梁平面塑又称“面人”，以糯米粉、面粉为主要原料，调入各色颜料，经艺人巧手捏、搓、切、刻，形成姿态各异的艺术形象。', '传统手工艺 (区级)', '肖德明', '/public/images/culture_mianshu.jpg', '["/public/images/culture_mianshu_1.jpg"]'),
(18, '梁平狮舞', '梁平狮舞历史悠久，表演时狮子形态生动，动作矫健敏捷，常伴以喧天的锣鼓声，气氛热烈非凡。', '传统舞蹈 (市级)', '蒋志明', '/public/images/culture_shiwu.jpg', '["/public/images/culture_shiwu_1.jpg"]'),
(19, '梁平武术', '梁平武术流派众多，以实战见长，注重内外兼修。当地武风盛行，至今仍保留有多种古老的传统拳种和器械套路。', '传统体育 (市级)', '唐大勇', '/public/images/culture_wushu.jpg', '["/public/images/culture_wushu_1.jpg"]'),
(20, '梁平龙舞', '梁平龙舞表演时龙体硕大，舞动起来气势磅礴。每逢重大节庆，当地百姓都会自发组织龙舞表演以庆贺。', '传统舞蹈 (市级)', '曾宪明', '/public/images/culture_longwu.jpg', '["/public/images/culture_longwu_1.jpg"]'),
(21, '梁平传统建筑营造技艺', '梁平传统建筑多采用穿斗式结构，梁架结构严谨，石雕、木雕装饰精美。该技艺体现了当地高超的木作与石作水平。', '传统技艺 (市级)', '卢开元', '/public/images/culture_jianzhu.jpg', '["/public/images/culture_jianzhu_1.jpg"]'),
(22, '梁平民间文学(双桂堂传说)', '双桂堂传说记录了破山禅师在此开山建寺的种种传奇故事，是研究梁平佛教文化及地方民俗的重要口头文学资料。', '民间文学 (市级)', '释身振', '/public/images/culture_shuanggui.jpg', '["/public/images/culture_shuanggui_1.jpg"]'),
(23, '梁平岁时节令(晒秋)', '梁平晒秋是当地农民在秋收季节将丰收的果实晾晒在庭前屋后的一种民俗景观，寓意丰收与喜悦，现已成为当地重要的文化名片。', '民俗 (市级)', '梁平区民俗协会', '/public/images/culture_shaiqiu.jpg', '["/public/images/culture_shaiqiu_1.jpg"]');
