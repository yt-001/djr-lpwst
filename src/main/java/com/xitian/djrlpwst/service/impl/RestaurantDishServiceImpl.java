package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.domain.entity.DishCategory;
import com.xitian.djrlpwst.domain.entity.Restaurant;
import com.xitian.djrlpwst.domain.entity.RestaurantCategory;
import com.xitian.djrlpwst.domain.entity.RestaurantDish;
import com.xitian.djrlpwst.domain.query.RestaurantDishQuery;
import com.xitian.djrlpwst.domain.vo.RecommendedDishCardVO;
import com.xitian.djrlpwst.mapper.DishCategoryMapper;
import com.xitian.djrlpwst.mapper.RestaurantCategoryMapper;
import com.xitian.djrlpwst.mapper.RestaurantDishMapper;
import com.xitian.djrlpwst.mapper.RestaurantMapper;
import com.xitian.djrlpwst.service.RestaurantDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RestaurantDishServiceImpl extends BaseServiceImpl<RestaurantDish> implements RestaurantDishService {

    @Autowired
    private RestaurantDishMapper restaurantDishMapper;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private DishCategoryMapper dishCategoryMapper;

    @Autowired
    private RestaurantCategoryMapper restaurantCategoryMapper;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<RestaurantDish> listByRestaurantId(Long restaurantId) {
        LambdaQueryWrapper<RestaurantDish> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RestaurantDish::getRestaurantId, restaurantId);
        wrapper.orderByAsc(RestaurantDish::getSortOrder).orderByDesc(RestaurantDish::getId);
        return restaurantDishMapper.selectList(wrapper);
    }

    @Override
    public List<RecommendedDishCardVO> listRecommendedDishCards(Integer limit) {
        int safeLimit = (limit != null && limit > 0) ? Math.min(limit, 100) : 20;

        LambdaQueryWrapper<RestaurantDish> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RestaurantDish::getIsRecommended, 1);
        wrapper.orderByAsc(RestaurantDish::getSortOrder).orderByDesc(RestaurantDish::getId);
        wrapper.last("limit " + safeLimit);

        List<RestaurantDish> dishes = restaurantDishMapper.selectList(wrapper);
        return buildRecommendedDishCards(dishes);
    }

    @Override
    public List<RecommendedDishCardVO> pageRecommendedDishCards(Integer pageNum, Integer pageSize) {
        int safePageNum = (pageNum != null && pageNum > 0) ? pageNum : 1;
        int safePageSize = (pageSize != null && pageSize > 0) ? Math.min(pageSize, 100) : 10;

        LambdaQueryWrapper<RestaurantDish> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RestaurantDish::getIsRecommended, 1);
        wrapper.orderByAsc(RestaurantDish::getSortOrder).orderByDesc(RestaurantDish::getId);

        Page<RestaurantDish> page = new Page<>(safePageNum, safePageSize);
        Page<RestaurantDish> result = restaurantDishMapper.selectPage(page, wrapper);
        List<RestaurantDish> dishes = result == null ? null : result.getRecords();
        return buildRecommendedDishCards(dishes);
    }

    private List<RecommendedDishCardVO> buildRecommendedDishCards(List<RestaurantDish> dishes) {
        if (dishes == null || dishes.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> restaurantIds = dishes.stream()
                .map(RestaurantDish::getRestaurantId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        Map<Long, Restaurant> restaurantMap;
        if (!restaurantIds.isEmpty()) {
            List<Restaurant> restaurants = restaurantMapper.selectBatchIds(restaurantIds);
            restaurantMap = (restaurants == null ? Collections.<Restaurant>emptyList() : restaurants).stream()
                    .filter(Objects::nonNull)
                    .filter(r -> r.getId() != null)
                    .collect(Collectors.toMap(Restaurant::getId, r -> r, (a, b) -> a));
        } else {
            restaurantMap = Collections.emptyMap();
        }

        List<Long> dishCategoryIds = dishes.stream()
                .map(RestaurantDish::getCategoryId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        Map<Long, DishCategory> dishCategoryMap;
        if (!dishCategoryIds.isEmpty()) {
            List<DishCategory> categories = dishCategoryMapper.selectBatchIds(dishCategoryIds);
            dishCategoryMap = (categories == null ? Collections.<DishCategory>emptyList() : categories).stream()
                    .filter(Objects::nonNull)
                    .filter(c -> c.getId() != null)
                    .collect(Collectors.toMap(DishCategory::getId, c -> c, (a, b) -> a));
        } else {
            dishCategoryMap = Collections.emptyMap();
        }

        List<Long> restaurantCategoryIds = restaurantMap.values().stream()
                .map(Restaurant::getCategoryId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        Map<Long, RestaurantCategory> restaurantCategoryMap;
        if (!restaurantCategoryIds.isEmpty()) {
            List<RestaurantCategory> categories = restaurantCategoryMapper.selectBatchIds(restaurantCategoryIds);
            restaurantCategoryMap = (categories == null ? Collections.<RestaurantCategory>emptyList() : categories).stream()
                    .filter(Objects::nonNull)
                    .filter(c -> c.getId() != null)
                    .collect(Collectors.toMap(RestaurantCategory::getId, c -> c, (a, b) -> a));
        } else {
            restaurantCategoryMap = Collections.emptyMap();
        }

        return dishes.stream().map(d -> {
            RecommendedDishCardVO vo = new RecommendedDishCardVO();
            vo.setDishId(d.getId());
            vo.setDishName(d.getName());
            vo.setDishDescription(d.getDescription());
            vo.setDishPrice(d.getPrice());
            vo.setDishImageUrl(d.getImageUrl());
            vo.setDishCategoryId(d.getCategoryId());

            DishCategory dishCategory = d.getCategoryId() == null ? null : dishCategoryMap.get(d.getCategoryId());
            if (dishCategory != null) {
                vo.setDishCategoryName(dishCategory.getName());
            }

            Restaurant restaurant = d.getRestaurantId() == null ? null : restaurantMap.get(d.getRestaurantId());
            if (restaurant != null) {
                vo.setRestaurantId(restaurant.getId());
                vo.setRestaurantName(restaurant.getName());
                vo.setRestaurantCoverImage(restaurant.getCoverImage());
                vo.setRestaurantLocation(restaurant.getLocation());
                vo.setRestaurantOpenHours(restaurant.getOpenHours());
                vo.setRestaurantPriceRange(restaurant.getPriceRange());
                vo.setRestaurantContactPhone(restaurant.getContactPhone());
                vo.setRestaurantRating(restaurant.getRating());

                vo.setRestaurantCategoryId(restaurant.getCategoryId());
                RestaurantCategory rc = restaurant.getCategoryId() == null ? null : restaurantCategoryMap.get(restaurant.getCategoryId());
                if (rc != null) {
                    vo.setRestaurantCategoryName(rc.getName());
                }
            } else {
                vo.setRestaurantId(d.getRestaurantId());
            }

            return vo;
        }).collect(Collectors.toList());
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

        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        if (StringUtils.hasText(sortField)) {
            boolean isAsc = sortDirection == Sort.Direction.ASC;
            switch (sortField) {
                case "id":
                    wrapper.orderBy(true, isAsc, RestaurantDish::getId);
                    break;
                case "name":
                    wrapper.orderBy(true, isAsc, RestaurantDish::getName);
                    break;
                case "categoryId":
                    wrapper.orderBy(true, isAsc, RestaurantDish::getCategoryId);
                    break;
                case "price":
                    wrapper.orderBy(true, isAsc, RestaurantDish::getPrice);
                    break;
                case "isRecommended":
                    wrapper.orderBy(true, isAsc, RestaurantDish::getIsRecommended);
                    break;
                case "sortOrder":
                    wrapper.orderBy(true, isAsc, RestaurantDish::getSortOrder);
                    break;
                case "createTime":
                    wrapper.orderBy(true, isAsc, RestaurantDish::getCreateTime);
                    break;
                case "updateTime":
                    wrapper.orderBy(true, isAsc, RestaurantDish::getUpdateTime);
                    break;
                default:
                    break;
            }
        } else {
            wrapper.orderByAsc(RestaurantDish::getSortOrder).orderByDesc(RestaurantDish::getId);
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
