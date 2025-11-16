package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.converter.AccommodationConverter;
import com.xitian.djrlpwst.domain.entity.Accommodation;
import com.xitian.djrlpwst.domain.query.AccommodationQuery;
import com.xitian.djrlpwst.domain.vo.AccommodationVO;
import com.xitian.djrlpwst.domain.vo.AccommodationAdminVO;
import com.xitian.djrlpwst.mapper.AccommodationMapper;
import com.xitian.djrlpwst.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AccommodationServiceImpl extends BaseServiceImpl<Accommodation> implements AccommodationService {
    
    @Autowired
    private AccommodationMapper accommodationMapper;
    
    @Autowired
    private AccommodationConverter accommodationConverter;

    @Override
    public PageBean<AccommodationVO> getPage(PageParam<AccommodationQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        AccommodationQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Accommodation> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Accommodation> wrapper = Wrappers.lambdaQuery();
        
        // 构建查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getName())) {
                wrapper.like(Accommodation::getName, query.getName());
            }
            
            if (StringUtils.hasText(query.getType())) {
                wrapper.like(Accommodation::getType, query.getType());
            }
            
            if (StringUtils.hasText(query.getLocation())) {
                wrapper.like(Accommodation::getLocation, query.getLocation());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 只支持name和pricePerNight两个排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "name":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Accommodation::getName);
                    } else {
                        wrapper.orderByAsc(Accommodation::getName);
                    }
                    break;
                case "pricePerNight":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Accommodation::getPricePerNight);
                    } else {
                        wrapper.orderByAsc(Accommodation::getPricePerNight);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        }
        // 如果没有指定排序字段，则不添加排序条件，使用数据库默认顺序
        
        // 执行分页查询
        Page<Accommodation> result = accommodationMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<AccommodationVO> voList = accommodationConverter.toVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<AccommodationVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
    
    @Override
    public PageBean<AccommodationAdminVO> getAdminPage(PageParam<AccommodationQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        AccommodationQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Accommodation> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Accommodation> wrapper = Wrappers.lambdaQuery();
        
        // 构建查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getName())) {
                wrapper.like(Accommodation::getName, query.getName());
            }
            
            if (StringUtils.hasText(query.getType())) {
                wrapper.like(Accommodation::getType, query.getType());
            }
            
            if (StringUtils.hasText(query.getLocation())) {
                wrapper.like(Accommodation::getLocation, query.getLocation());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 管理员端也只支持name和pricePerNight两个排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "name":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Accommodation::getName);
                    } else {
                        wrapper.orderByAsc(Accommodation::getName);
                    }
                    break;
                case "pricePerNight":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Accommodation::getPricePerNight);
                    } else {
                        wrapper.orderByAsc(Accommodation::getPricePerNight);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        }
        // 如果没有指定排序字段，则不添加排序条件，使用数据库默认顺序
        
        // 执行分页查询
        Page<Accommodation> result = accommodationMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<AccommodationAdminVO> voList = accommodationConverter.toAdminVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<AccommodationAdminVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
}