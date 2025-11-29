package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.domain.entity.AccommodationType;
import com.xitian.djrlpwst.domain.query.AccommodationTypeQuery;
import com.xitian.djrlpwst.mapper.AccommodationTypeMapper;
import com.xitian.djrlpwst.service.AccommodationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AccommodationTypeServiceImpl extends BaseServiceImpl<AccommodationType> implements AccommodationTypeService {
    
    @Autowired
    private AccommodationTypeMapper accommodationTypeMapper;
    
    @Override
    public PageBean<AccommodationType> getPage(PageParam<AccommodationTypeQuery> param) {
        // 创建分页对象
        Page<AccommodationType> page = new Page<>(param.getPageNum(), param.getPageSize());
        
        // 创建查询条件
        LambdaQueryWrapper<AccommodationType> wrapper = Wrappers.lambdaQuery();
        
        // 构建查询条件
        AccommodationTypeQuery query = param.getQuery();
        if (query != null) {
            if (StringUtils.hasText(query.getName())) {
                wrapper.like(AccommodationType::getName, query.getName());
            }
        }
        
        // 执行分页查询
        Page<AccommodationType> result = accommodationTypeMapper.selectPage(page, wrapper);
        
        // 封装分页结果
        PageBean<AccommodationType> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(result.getRecords());
        pageBean.setPageNum((int) result.getCurrent());
        pageBean.setPageSize((int) result.getSize());
        
        return pageBean;
    }
}