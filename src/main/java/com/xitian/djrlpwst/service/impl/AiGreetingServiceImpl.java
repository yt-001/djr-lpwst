package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.domain.entity.AiGreeting;
import com.xitian.djrlpwst.domain.query.AiGreetingQuery;
import com.xitian.djrlpwst.mapper.AiGreetingMapper;
import com.xitian.djrlpwst.service.AiGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AiGreetingServiceImpl extends BaseServiceImpl<AiGreeting> implements AiGreetingService {

    @Autowired
    private AiGreetingMapper aiGreetingMapper;

    @Override
    public AiGreeting getRandomEnabled() {
        return aiGreetingMapper.selectRandomEnabled();
    }

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public PageBean<AiGreeting> getPage(PageParam<AiGreetingQuery> param) {
        Page<AiGreeting> page = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<AiGreeting> wrapper = Wrappers.lambdaQuery();
        AiGreetingQuery query = param.getQuery();
        if (query != null) {
            if (StringUtils.hasText(query.getKeyword())) {
                wrapper.like(AiGreeting::getContent, query.getKeyword());
            }
            if (query.getIsEnabled() != null) {
                wrapper.eq(AiGreeting::getIsEnabled, query.getIsEnabled());
            }
            LocalDateTime start = parseStart(query.getCreateTimeStart());
            if (start != null) {
                wrapper.ge(AiGreeting::getCreateTime, start);
            }
            LocalDateTime end = parseEnd(query.getCreateTimeEnd());
            if (end != null) {
                wrapper.le(AiGreeting::getCreateTime, end);
            }
        }
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        if (StringUtils.hasText(sortField)) {
            boolean isAsc = sortDirection == Sort.Direction.ASC;
            switch (sortField) {
                case "id":
                    wrapper.orderBy(true, isAsc, AiGreeting::getId);
                    break;
                case "content":
                    wrapper.orderBy(true, isAsc, AiGreeting::getContent);
                    break;
                case "sortOrder":
                    wrapper.orderBy(true, isAsc, AiGreeting::getSortOrder);
                    break;
                case "isEnabled":
                    wrapper.orderBy(true, isAsc, AiGreeting::getIsEnabled);
                    break;
                case "createTime":
                    wrapper.orderBy(true, isAsc, AiGreeting::getCreateTime);
                    break;
                case "updateTime":
                    wrapper.orderBy(true, isAsc, AiGreeting::getUpdateTime);
                    break;
                default:
                    wrapper.orderByDesc(AiGreeting::getUpdateTime);
            }
        } else {
            wrapper.orderByDesc(AiGreeting::getUpdateTime);
        }
        Page<AiGreeting> result = page(page, wrapper);
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
