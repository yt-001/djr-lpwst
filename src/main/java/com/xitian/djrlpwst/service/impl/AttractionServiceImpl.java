package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.converter.AttractionConverter;
import com.xitian.djrlpwst.domain.entity.Attraction;
import com.xitian.djrlpwst.domain.query.AttractionQuery;
import com.xitian.djrlpwst.domain.vo.AttractionListVO;
import com.xitian.djrlpwst.domain.vo.AttractionDetailVO;
import com.xitian.djrlpwst.mapper.AttractionMapper;
import com.xitian.djrlpwst.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AttractionServiceImpl extends BaseServiceImpl<Attraction> implements AttractionService {
    
    @Autowired
    private AttractionMapper attractionMapper;
    
    @Autowired
    private AttractionConverter attractionConverter;

    @Override
    public PageBean<AttractionListVO> getPage(PageParam<AttractionQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        AttractionQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Attraction> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Attraction> wrapper = Wrappers.lambdaQuery();
        
        // 构建查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getName())) {
                wrapper.like(Attraction::getName, query.getName());
            }
            
            if (StringUtils.hasText(query.getLocation())) {
                wrapper.like(Attraction::getLocation, query.getLocation());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 只支持ticketPrice和name两个排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "name":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Attraction::getName);
                    } else {
                        wrapper.orderByAsc(Attraction::getName);
                    }
                    break;
                case "ticketPrice":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Attraction::getTicketPrice);
                    } else {
                        wrapper.orderByAsc(Attraction::getTicketPrice);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        }
        // 如果没有指定排序字段，则不添加排序条件，使用数据库默认顺序
        
        // 执行分页查询
        Page<Attraction> result = attractionMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<AttractionListVO> voList = attractionConverter.toListVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<AttractionListVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
    
    @Override
    public PageBean<AttractionDetailVO> getAdminPage(PageParam<AttractionQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        AttractionQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Attraction> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Attraction> wrapper = Wrappers.lambdaQuery();
        
        // 构建查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getName())) {
                wrapper.like(Attraction::getName, query.getName());
            }
            
            if (StringUtils.hasText(query.getLocation())) {
                wrapper.like(Attraction::getLocation, query.getLocation());
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
                        wrapper.orderByDesc(Attraction::getName);
                    } else {
                        wrapper.orderByAsc(Attraction::getName);
                    }
                    break;
                case "ticketPrice":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Attraction::getTicketPrice);
                    } else {
                        wrapper.orderByAsc(Attraction::getTicketPrice);
                    }
                    break;
                case "createTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Attraction::getCreateTime);
                    } else {
                        wrapper.orderByAsc(Attraction::getCreateTime);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        }
        // 如果没有指定排序字段，则不添加排序条件，使用数据库默认顺序
        
        // 执行分页查询
        Page<Attraction> result = attractionMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<AttractionDetailVO> voList = attractionConverter.toDetailVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<AttractionDetailVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
}