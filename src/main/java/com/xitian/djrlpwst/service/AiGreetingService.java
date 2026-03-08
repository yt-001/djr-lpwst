package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.domain.entity.AiGreeting;
import com.xitian.djrlpwst.domain.query.AiGreetingQuery;

public interface AiGreetingService extends BaseService<AiGreeting> {
    AiGreeting getRandomEnabled();
    PageBean<AiGreeting> getPage(PageParam<AiGreetingQuery> param);
}
