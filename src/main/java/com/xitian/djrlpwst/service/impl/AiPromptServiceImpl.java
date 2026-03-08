package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.domain.entity.AiPrompt;
import com.xitian.djrlpwst.domain.query.AiPromptQuery;
import com.xitian.djrlpwst.service.AiPromptService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AiPromptServiceImpl extends BaseServiceImpl<AiPrompt> implements AiPromptService {

    @Override
    public List<AiPrompt> listEnabled(Integer limit) {
        LambdaQueryWrapper<AiPrompt> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(AiPrompt::getIsEnabled, 1)
            .orderByAsc(AiPrompt::getSortOrder)
            .orderByAsc(AiPrompt::getId);
        if (limit != null && limit > 0) {
            wrapper.last("LIMIT " + limit);
        }
        return list(wrapper);
    }

    @Override
    public List<AiPrompt> listRandomEnabled(Integer limit) {
        LambdaQueryWrapper<AiPrompt> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(AiPrompt::getIsEnabled, 1);
        if (limit != null && limit > 0) {
            wrapper.last("ORDER BY RAND() LIMIT " + limit);
        } else {
            wrapper.last("ORDER BY RAND()");
        }
        return list(wrapper);
    }

    private static final DateTimeFormatter DATE_TIME_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public PageBean<AiPrompt> getPage(PageParam<AiPromptQuery> param) {
        Page<AiPrompt> page = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<AiPrompt> wrapper = Wrappers.lambdaQuery();
        AiPromptQuery query = param.getQuery();
        if (query != null) {
            if (StringUtils.hasText(query.getKeyword())) {
                wrapper.like(AiPrompt::getContent, query.getKeyword());
            }
            if (query.getIsEnabled() != null) {
                wrapper.eq(AiPrompt::getIsEnabled, query.getIsEnabled());
            }
            LocalDateTime start = parseStart(query.getCreateTimeStart());
            if (start != null) {
                wrapper.ge(AiPrompt::getCreateTime, start);
            }
            LocalDateTime end = parseEnd(query.getCreateTimeEnd());
            if (end != null) {
                wrapper.le(AiPrompt::getCreateTime, end);
            }
        }
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        if (StringUtils.hasText(sortField)) {
            boolean isAsc = sortDirection == Sort.Direction.ASC;
            switch (sortField) {
                case "id":
                    wrapper.orderBy(true, isAsc, AiPrompt::getId);
                    break;
                case "content":
                    wrapper.orderBy(true, isAsc, AiPrompt::getContent);
                    break;
                case "sortOrder":
                    wrapper.orderBy(true, isAsc, AiPrompt::getSortOrder);
                    break;
                case "isEnabled":
                    wrapper.orderBy(true, isAsc, AiPrompt::getIsEnabled);
                    break;
                case "createTime":
                    wrapper.orderBy(true, isAsc, AiPrompt::getCreateTime);
                    break;
                case "updateTime":
                    wrapper.orderBy(true, isAsc, AiPrompt::getUpdateTime);
                    break;
                default:
                    wrapper.orderByDesc(AiPrompt::getUpdateTime);
            }
        } else {
            wrapper.orderByDesc(AiPrompt::getUpdateTime);
        }
        Page<AiPrompt> result = page(page, wrapper);
        return PageBean.of(result.getRecords(), result.getTotal(), param);
    }

    private LocalDateTime parseStart(String value) {
        if (!StringUtils.hasText(value)) return null;
        String v = value.trim();
        if (v.length() == 10) return LocalDateTime.parse(v + " 00:00:00", DATE_TIME_FMT);
        return LocalDateTime.parse(v, DATE_TIME_FMT);
    }

    private LocalDateTime parseEnd(String value) {
        if (!StringUtils.hasText(value)) return null;
        String v = value.trim();
        if (v.length() == 10) return LocalDateTime.parse(v + " 23:59:59", DATE_TIME_FMT);
        return LocalDateTime.parse(v, DATE_TIME_FMT);
    }
}
