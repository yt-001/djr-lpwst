package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.converter.AttractionFavoriteConverter;
import com.xitian.djrlpwst.domain.entity.Attraction;
import com.xitian.djrlpwst.domain.entity.Favorite;
import com.xitian.djrlpwst.domain.query.FavoriteQuery;
import com.xitian.djrlpwst.domain.vo.AttractionFavoriteVO;
import com.xitian.djrlpwst.mapper.AttractionMapper;
import com.xitian.djrlpwst.mapper.FavoriteMapper;
import com.xitian.djrlpwst.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl extends BaseServiceImpl<Favorite> implements FavoriteService {
    
    @Autowired
    private FavoriteMapper favoriteMapper;
    
    @Autowired
    private AttractionMapper attractionMapper;
    
    @Autowired
    private AttractionFavoriteConverter attractionFavoriteConverter;
    
    @Override
    public boolean isAttractionFavorited(Long userId, Long attractionId) {
        LambdaQueryWrapper<Favorite> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Favorite::getUserId, userId)
               .eq(Favorite::getAttractionId, attractionId);
        
        return favoriteMapper.selectCount(wrapper) > 0;
    }
    
    @Override
    public PageBean<AttractionFavoriteVO> getAttractionFavorites(Page<Favorite> param, FavoriteQuery query) {
        // 构建查询条件
        LambdaQueryWrapper<Favorite> wrapper = Wrappers.lambdaQuery();
        wrapper.isNotNull(Favorite::getAttractionId); // 只查询景点收藏
        
        // 添加用户ID查询条件
        if (query != null && query.getUserId() != null) {
            wrapper.eq(Favorite::getUserId, query.getUserId());
        }
        
        // 查询收藏记录
        Page<Favorite> favoritePage = favoriteMapper.selectPage(param, wrapper);
        
        // 获取景点ID列表
        List<Long> attractionIds = favoritePage.getRecords().stream()
            .map(Favorite::getAttractionId)
            .collect(Collectors.toList());
        
        // 查询对应的景点信息
        List<Attraction> attractions = attractionMapper.selectBatchIds(attractionIds);
        
        // 转换为VO对象
        List<AttractionFavoriteVO> voList = new java.util.ArrayList<>();
        for (int i = 0; i < favoritePage.getRecords().size(); i++) {
            Favorite favorite = favoritePage.getRecords().get(i);
            Attraction attraction = attractions.stream()
                .filter(a -> a.getId().equals(favorite.getAttractionId()))
                .findFirst()
                .orElse(null);
            
            if (attraction != null) {
                AttractionFavoriteVO vo = attractionFavoriteConverter.toVO(favorite, attraction);
                voList.add(vo);
            }
        }
        
        // 封装分页结果
        PageBean<AttractionFavoriteVO> pageBean = new PageBean<>();
        pageBean.setTotal(favoritePage.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum((int) favoritePage.getCurrent());
        pageBean.setPageSize((int) favoritePage.getSize());
        
        return pageBean;
    }
    
    @Override
    public boolean addAttractionFavorite(Long userId, Long attractionId) {
        // 检查是否已收藏
        if (isAttractionFavorited(userId, attractionId)) {
            return false; // 已收藏，无需重复收藏
        }
        
        // 创建收藏记录
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setAttractionId(attractionId);
        
        // 保存到数据库
        return favoriteMapper.insert(favorite) > 0;
    }
    
    @Override
    public boolean removeAttractionFavorite(Long userId, Long attractionId) {
        LambdaQueryWrapper<Favorite> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Favorite::getUserId, userId)
               .eq(Favorite::getAttractionId, attractionId);
        
        // 删除收藏记录
        return favoriteMapper.delete(wrapper) > 0;
    }
}