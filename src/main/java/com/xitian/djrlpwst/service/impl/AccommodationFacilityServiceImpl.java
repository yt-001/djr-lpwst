package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.domain.entity.AccommodationFacility;
import com.xitian.djrlpwst.domain.query.AccommodationFacilityQuery;
import com.xitian.djrlpwst.mapper.AccommodationFacilityMapper;
import com.xitian.djrlpwst.service.AccommodationFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AccommodationFacilityServiceImpl extends BaseServiceImpl<AccommodationFacility> implements AccommodationFacilityService {
    
    @Autowired
    private AccommodationFacilityMapper accommodationFacilityMapper;
    
    @Override
    public PageBean<AccommodationFacility> getPage(PageParam<AccommodationFacilityQuery> param) {
        // 创建分页对象
        Page<AccommodationFacility> page = new Page<>(param.getPageNum(), param.getPageSize());
        
        // 创建查询条件
        LambdaQueryWrapper<AccommodationFacility> wrapper = Wrappers.lambdaQuery();
        
        // 构建查询条件
        AccommodationFacilityQuery query = param.getQuery();
        if (query != null) {
            if (StringUtils.hasText(query.getName())) {
                wrapper.like(AccommodationFacility::getName, query.getName());
            }
        }
        
        // 执行分页查询
        Page<AccommodationFacility> result = accommodationFacilityMapper.selectPage(page, wrapper);
        
        // 封装分页结果
        PageBean<AccommodationFacility> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(result.getRecords());
        pageBean.setPageNum((int) result.getCurrent());
        pageBean.setPageSize((int) result.getSize());
        
        return pageBean;
    }
}