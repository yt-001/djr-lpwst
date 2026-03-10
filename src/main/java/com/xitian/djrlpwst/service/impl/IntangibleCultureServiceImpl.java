package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.converter.IntangibleCultureConverter;
import com.xitian.djrlpwst.domain.entity.IntangibleCulture;
import com.xitian.djrlpwst.domain.query.IntangibleCultureQuery;
import com.xitian.djrlpwst.domain.vo.IntangibleCultureVO;
import com.xitian.djrlpwst.mapper.IntangibleCultureMapper;
import com.xitian.djrlpwst.service.IntangibleCultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 非物质文化遗产服务实现类
 */
@Service
public class IntangibleCultureServiceImpl extends BaseServiceImpl<IntangibleCulture> implements IntangibleCultureService {
    
    @Autowired
    private IntangibleCultureMapper intangibleCultureMapper;
    
    @Autowired
    private IntangibleCultureConverter intangibleCultureConverter;
    
    @Override
    public PageBean<IntangibleCultureVO> getIntangibleCultures(Page<IntangibleCulture> page, IntangibleCultureQuery query) {
        // 构建查询条件
        LambdaQueryWrapper<IntangibleCulture> wrapper = Wrappers.lambdaQuery();
        
        // 添加查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getName())) {
                wrapper.eq(IntangibleCulture::getName, query.getName());
            }
            
            if (StringUtils.hasText(query.getType())) {
                wrapper.eq(IntangibleCulture::getType, query.getType());
            }
            
            if (StringUtils.hasText(query.getInheritor())) {
                wrapper.eq(IntangibleCulture::getInheritor, query.getInheritor());
            }
            
            if (StringUtils.hasText(query.getKeyword())) {
                wrapper.like(IntangibleCulture::getName, query.getKeyword())
                      .or().like(IntangibleCulture::getDescription, query.getKeyword())
                      .or().like(IntangibleCulture::getType, query.getKeyword())
                      .or().like(IntangibleCulture::getInheritor, query.getKeyword());
            }
        }
        
        // 按创建时间倒序排列
        wrapper.orderByDesc(IntangibleCulture::getCreateTime);
        
        // 执行分页查询
        Page<IntangibleCulture> culturePage = intangibleCultureMapper.selectPage(page, wrapper);
        
        // 转换为VO对象
        List<IntangibleCultureVO> voList = intangibleCultureConverter.toVOList(culturePage.getRecords());
        
        // 封装分页结果
        PageBean<IntangibleCultureVO> pageBean = new PageBean<>();
        pageBean.setTotal(culturePage.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum((int) culturePage.getCurrent());
        pageBean.setPageSize((int) culturePage.getSize());
        
        return pageBean;
    }
    
    @Override
    public IntangibleCultureVO getIntangibleCultureById(Long id) {
        // 查询实体
        IntangibleCulture culture = intangibleCultureMapper.selectById(id);
        if (culture == null) {
            return null;
        }
        
        // 转换为VO对象
        return intangibleCultureConverter.toVO(culture);
    }
}
