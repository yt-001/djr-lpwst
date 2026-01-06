package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.domain.entity.RestaurantCategory;
import com.xitian.djrlpwst.domain.query.RestaurantCategoryQuery;
import com.xitian.djrlpwst.mapper.RestaurantCategoryMapper;
import com.xitian.djrlpwst.service.RestaurantCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RestaurantCategoryServiceImpl extends BaseServiceImpl<RestaurantCategory> implements RestaurantCategoryService {

    @Autowired
    private RestaurantCategoryMapper restaurantCategoryMapper;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public PageBean<RestaurantCategory> getPage(PageParam<RestaurantCategoryQuery> param) {
        Page<RestaurantCategory> page = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<RestaurantCategory> wrapper = Wrappers.lambdaQuery();

        RestaurantCategoryQuery query = param.getQuery();
        if (query != null) {
            if (StringUtils.hasText(query.getName())) {
                wrapper.like(RestaurantCategory::getName, query.getName());
            }

            if (StringUtils.hasText(query.getKeyword())) {
                wrapper.like(RestaurantCategory::getName, query.getKeyword());
            }

            if (query.getIsEnabled() != null) {
                wrapper.eq(RestaurantCategory::getIsEnabled, query.getIsEnabled());
            }

            LocalDateTime start = parseStart(query.getCreateTimeStart());
            if (start != null) {
                wrapper.ge(RestaurantCategory::getCreateTime, start);
            }

            LocalDateTime end = parseEnd(query.getCreateTimeEnd());
            if (end != null) {
                wrapper.le(RestaurantCategory::getCreateTime, end);
            }
        }

        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        if (StringUtils.hasText(sortField)) {
            boolean isAsc = sortDirection == Sort.Direction.ASC;
            switch (sortField) {
                case "id":
                    wrapper.orderBy(true, isAsc, RestaurantCategory::getId);
                    break;
                case "name":
                    wrapper.orderBy(true, isAsc, RestaurantCategory::getName);
                    break;
                case "sortOrder":
                    wrapper.orderBy(true, isAsc, RestaurantCategory::getSortOrder);
                    break;
                case "isEnabled":
                    wrapper.orderBy(true, isAsc, RestaurantCategory::getIsEnabled);
                    break;
                case "createTime":
                    wrapper.orderBy(true, isAsc, RestaurantCategory::getCreateTime);
                    break;
                case "updateTime":
                    wrapper.orderBy(true, isAsc, RestaurantCategory::getUpdateTime);
                    break;
                default:
                    break;
            }
        } else {
            wrapper.orderByAsc(RestaurantCategory::getSortOrder).orderByDesc(RestaurantCategory::getId);
        }

        Page<RestaurantCategory> result = restaurantCategoryMapper.selectPage(page, wrapper);

        PageBean<RestaurantCategory> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(result.getRecords());
        pageBean.setPageNum((int) result.getCurrent());
        pageBean.setPageSize((int) result.getSize());

        return pageBean;
    }

    private LocalDateTime parseStart(String input) {
        if (!StringUtils.hasText(input)) {
            return null;
        }
        String value = input.trim();
        try {
            if (value.length() > 10) {
                return LocalDateTime.parse(value, DATE_TIME_FMT);
            }
            return LocalDate.parse(value, DATE_FMT).atStartOfDay();
        } catch (Exception ignored) {
            return null;
        }
    }

    private LocalDateTime parseEnd(String input) {
        if (!StringUtils.hasText(input)) {
            return null;
        }
        String value = input.trim();
        try {
            if (value.length() > 10) {
                return LocalDateTime.parse(value, DATE_TIME_FMT);
            }
            LocalDate endDate = LocalDate.parse(value, DATE_FMT);
            return endDate.plusDays(1).atStartOfDay().minusNanos(1);
        } catch (Exception ignored) {
            return null;
        }
    }
}
