package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.converter.RestaurantConverter;
import com.xitian.djrlpwst.domain.entity.Restaurant;
import com.xitian.djrlpwst.domain.query.RestaurantQuery;
import com.xitian.djrlpwst.domain.vo.RestaurantListVO;
import com.xitian.djrlpwst.domain.vo.RestaurantAdminVO;
import com.xitian.djrlpwst.mapper.RestaurantMapper;
import com.xitian.djrlpwst.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RestaurantServiceImpl extends BaseServiceImpl<Restaurant> implements RestaurantService {
    
    @Autowired
    private RestaurantMapper restaurantMapper;
    
    @Autowired
    private RestaurantConverter restaurantConverter;

    @Override
    public PageBean<RestaurantListVO> getPage(PageParam<RestaurantQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        RestaurantQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Restaurant> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Restaurant> wrapper = Wrappers.lambdaQuery();
        
        // 构建查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getName())) {
                wrapper.like(Restaurant::getName, query.getName());
            }
            
            if (StringUtils.hasText(query.getLocation())) {
                wrapper.like(Restaurant::getLocation, query.getLocation());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 只支持rating和name两个排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "name":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Restaurant::getName);
                    } else {
                        wrapper.orderByAsc(Restaurant::getName);
                    }
                    break;
                case "rating":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Restaurant::getRating);
                    } else {
                        wrapper.orderByAsc(Restaurant::getRating);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        }
        // 如果没有指定排序字段，则不添加排序条件，使用数据库默认顺序
        
        // 执行分页查询
        Page<Restaurant> result = restaurantMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<RestaurantListVO> voList = restaurantConverter.toListVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<RestaurantListVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
    
    @Override
    public PageBean<RestaurantAdminVO> getAdminPage(PageParam<RestaurantQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        RestaurantQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Restaurant> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Restaurant> wrapper = Wrappers.lambdaQuery();
        
        // 构建查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getName())) {
                wrapper.like(Restaurant::getName, query.getName());
            }
            
            if (StringUtils.hasText(query.getLocation())) {
                wrapper.like(Restaurant::getLocation, query.getLocation());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 支持多种排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "name":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Restaurant::getName);
                    } else {
                        wrapper.orderByAsc(Restaurant::getName);
                    }
                    break;
                case "rating":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Restaurant::getRating);
                    } else {
                        wrapper.orderByAsc(Restaurant::getRating);
                    }
                    break;
                case "createTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Restaurant::getCreateTime);
                    } else {
                        wrapper.orderByAsc(Restaurant::getCreateTime);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        }
        // 如果没有指定排序字段，则不添加排序条件，使用数据库默认顺序
        
        // 执行分页查询
        Page<Restaurant> result = restaurantMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<RestaurantAdminVO> voList = restaurantConverter.toAdminVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<RestaurantAdminVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
}