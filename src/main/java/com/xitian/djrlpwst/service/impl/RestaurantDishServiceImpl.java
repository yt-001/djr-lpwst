package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.domain.entity.RestaurantDish;
import com.xitian.djrlpwst.domain.query.RestaurantDishQuery;
import com.xitian.djrlpwst.mapper.RestaurantDishMapper;
import com.xitian.djrlpwst.service.RestaurantDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RestaurantDishServiceImpl extends BaseServiceImpl<RestaurantDish> implements RestaurantDishService {

    @Autowired
    private RestaurantDishMapper restaurantDishMapper;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<RestaurantDish> listByRestaurantId(Long restaurantId) {
        LambdaQueryWrapper<RestaurantDish> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RestaurantDish::getRestaurantId, restaurantId);
        return restaurantDishMapper.selectList(wrapper);
    }

    @Override
    public PageBean<RestaurantDish> pageByRestaurantId(Long restaurantId, PageParam<RestaurantDishQuery> param) {
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        RestaurantDishQuery query = param.getQuery();

        Page<RestaurantDish> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<RestaurantDish> wrapper = Wrappers.lambdaQuery();

        wrapper.eq(RestaurantDish::getRestaurantId, restaurantId);

        if (query != null) {
            if (query.getCategoryId() != null) {
                wrapper.eq(RestaurantDish::getCategoryId, query.getCategoryId());
            }

            if (StringUtils.hasText(query.getName())) {
                wrapper.like(RestaurantDish::getName, query.getName());
            }

            if (query.getIsRecommended() != null) {
                wrapper.eq(RestaurantDish::getIsRecommended, query.getIsRecommended());
            }

            if (StringUtils.hasText(query.getCreateTimeStart())) {
                LocalDateTime start = parseStart(query.getCreateTimeStart());
                if (start != null) {
                    wrapper.ge(RestaurantDish::getCreateTime, start);
                }
            }

            if (StringUtils.hasText(query.getCreateTimeEnd())) {
                LocalDateTime end = parseEnd(query.getCreateTimeEnd());
                if (end != null) {
                    wrapper.le(RestaurantDish::getCreateTime, end);
                }
            }
        }

        Page<RestaurantDish> result = restaurantDishMapper.selectPage(page, wrapper);

        PageBean<RestaurantDish> pageBean = new PageBean<>();
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

    @Override
    public Long addDish(RestaurantDish dish) {
        restaurantDishMapper.insert(dish);
        return dish.getId();
    }

    @Override
    public boolean updateDish(RestaurantDish dish) {
        return restaurantDishMapper.updateById(dish) > 0;
    }

    @Override
    public boolean deleteDish(Long dishId) {
        return restaurantDishMapper.deleteById(dishId) > 0;
    }
}
