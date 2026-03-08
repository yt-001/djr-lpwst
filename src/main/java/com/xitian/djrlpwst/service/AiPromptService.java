package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.AiPrompt;
import com.xitian.djrlpwst.domain.query.AiPromptQuery;

import java.util.List;

public interface AiPromptService extends BaseService<AiPrompt> {
    List<AiPrompt> listEnabled(Integer limit);
    List<AiPrompt> listRandomEnabled(Integer limit);
    PageBean<AiPrompt> getPage(PageParam<AiPromptQuery> param);
}
