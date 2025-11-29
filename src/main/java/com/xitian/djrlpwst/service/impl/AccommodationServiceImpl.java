package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.converter.AccommodationConverter;
import com.xitian.djrlpwst.domain.entity.*;
import com.xitian.djrlpwst.domain.query.AccommodationQuery;
import com.xitian.djrlpwst.domain.vo.*;
import com.xitian.djrlpwst.mapper.*;
import com.xitian.djrlpwst.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl extends BaseServiceImpl<Accommodation> implements AccommodationService {
    
    @Autowired
    private AccommodationMapper accommodationMapper;
    
    @Autowired
    private AccommodationTypeMapper accommodationTypeMapper;
    
    @Autowired
    private AccommodationFacilityMapper accommodationFacilityMapper;
    
    @Autowired
    private AccommodationFacilityRelationMapper accommodationFacilityRelationMapper;
    
    @Autowired
    private AccommodationConverter accommodationConverter;

    @Override
    public PageBean<AccommodationSimpleVO> getPage(PageParam<AccommodationQuery> param) {
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
            
            if (query.getTypeId() != null) {
                wrapper.eq(Accommodation::getTypeId, query.getTypeId());
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
        
        // 获取住宿类型和设施信息
        List<AccommodationType> types = new ArrayList<>();
        List<List<AccommodationFacility>> facilitiesList = new ArrayList<>();
        
        for (Accommodation accommodation : result.getRecords()) {
            // 获取类型信息
            AccommodationType type = null;
            if (accommodation.getTypeId() != null) {
                type = accommodationTypeMapper.selectById(accommodation.getTypeId());
            }
            types.add(type);
            
            // 获取设施信息
            List<AccommodationFacility> facilities = new ArrayList<>();
            if (accommodation.getId() != null) {
                // 先获取关联关系
                LambdaQueryWrapper<AccommodationFacilityRelation> relationWrapper = Wrappers.lambdaQuery();
                relationWrapper.eq(AccommodationFacilityRelation::getAccommodationId, accommodation.getId());
                List<AccommodationFacilityRelation> relations = accommodationFacilityRelationMapper.selectList(relationWrapper);
                
                // 根据关联关系获取设施详情
                if (!relations.isEmpty()) {
                    List<Integer> facilityIds = relations.stream()
                            .map(AccommodationFacilityRelation::getFacilityId)
                            .collect(Collectors.toList());
                    
                    LambdaQueryWrapper<AccommodationFacility> facilityWrapper = Wrappers.lambdaQuery();
                    facilityWrapper.in(AccommodationFacility::getId, facilityIds);
                    facilities = accommodationFacilityMapper.selectList(facilityWrapper);
                }
            }
            facilitiesList.add(facilities);
        }
        
        // 转换结果为简化VO对象
        List<AccommodationSimpleVO> voList = accommodationConverter.toSimpleVOList(result.getRecords(), types, facilitiesList);
        
        // 封装分页结果
        PageBean<AccommodationSimpleVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
    
    @Override
    public AccommodationDetailVO getById(Long id) {
        // 查询住宿信息
        Accommodation accommodation = accommodationMapper.selectById(id);
        if (accommodation == null) {
            return null;
        }
        
        // 获取类型信息
        AccommodationType type = null;
        if (accommodation.getTypeId() != null) {
            type = accommodationTypeMapper.selectById(accommodation.getTypeId());
        }
        
        // 获取设施信息
        List<AccommodationFacility> facilities = new ArrayList<>();
        if (accommodation.getId() != null) {
            // 先获取关联关系
            LambdaQueryWrapper<AccommodationFacilityRelation> relationWrapper = Wrappers.lambdaQuery();
            relationWrapper.eq(AccommodationFacilityRelation::getAccommodationId, accommodation.getId());
            List<AccommodationFacilityRelation> relations = accommodationFacilityRelationMapper.selectList(relationWrapper);
            
            // 根据关联关系获取设施详情
            if (!relations.isEmpty()) {
                List<Integer> facilityIds = relations.stream()
                        .map(AccommodationFacilityRelation::getFacilityId)
                        .collect(Collectors.toList());
                
                LambdaQueryWrapper<AccommodationFacility> facilityWrapper = Wrappers.lambdaQuery();
                facilityWrapper.in(AccommodationFacility::getId, facilityIds);
                facilities = accommodationFacilityMapper.selectList(facilityWrapper);
            }
        }
        
        // 转换为详情VO对象
        return accommodationConverter.toDetailVO(accommodation, type, facilities);
    }
    
    @Override
    public Accommodation getEntityById(Long id) {
        return accommodationMapper.selectById(id);
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
            
            if (query.getTypeId() != null) {
                wrapper.eq(Accommodation::getTypeId, query.getTypeId());
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
        
        // 获取住宿类型信息
        List<AccommodationType> types = new ArrayList<>();
        for (Accommodation accommodation : result.getRecords()) {
            AccommodationType type = null;
            if (accommodation.getTypeId() != null) {
                type = accommodationTypeMapper.selectById(accommodation.getTypeId());
            }
            types.add(type);
        }
        
        // 转换结果为VO对象
        List<AccommodationAdminVO> voList = accommodationConverter.toAdminVOList(result.getRecords(), types);
        
        // 封装分页结果
        PageBean<AccommodationAdminVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
}