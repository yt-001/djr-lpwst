package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.converter.AttractionFavoriteConverter;
import com.xitian.djrlpwst.domain.entity.Attraction;
import com.xitian.djrlpwst.domain.entity.Favorite;
import com.xitian.djrlpwst.domain.entity.Accommodation;
import com.xitian.djrlpwst.domain.entity.Restaurant;
import com.xitian.djrlpwst.domain.vo.AttractionFavoriteVO;
import com.xitian.djrlpwst.domain.vo.AccommodationFavoriteVO;
import com.xitian.djrlpwst.domain.vo.RestaurantFavoriteVO;
import com.xitian.djrlpwst.mapper.AttractionMapper;
import com.xitian.djrlpwst.mapper.FavoriteMapper;
import com.xitian.djrlpwst.mapper.AccommodationMapper;
import com.xitian.djrlpwst.mapper.RestaurantMapper;
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
    private AccommodationMapper accommodationMapper;
    
    @Autowired
    private RestaurantMapper restaurantMapper;
    
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
    public PageBean<AttractionFavoriteVO> getAttractionFavorites(Page<Favorite> param) {
        // 从page对象中获取用户ID（如果已设置）
        Long userId = null;
        if (param.getRecords() != null && !param.getRecords().isEmpty()) {
            userId = param.getRecords().get(0).getUserId();
        }
        
        // 构造查询条件
        LambdaQueryWrapper<Favorite> queryWrapper = Wrappers.<Favorite>lambdaQuery()
                .isNotNull(Favorite::getAttractionId);
                
        // 如果用户ID存在，则添加用户ID过滤条件
        if (userId != null) {
            queryWrapper.eq(Favorite::getUserId, userId);
        }
        
        Page<Favorite> favoritePage = favoriteMapper.selectPage(param, queryWrapper);
        
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
    
    @Override
    public boolean isAccommodationFavorited(Long userId, Long accommodationId) {
        LambdaQueryWrapper<Favorite> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Favorite::getUserId, userId)
               .eq(Favorite::getAccommodationId, accommodationId);
        
        return favoriteMapper.selectCount(wrapper) > 0;
    }
    
    @Override
    public PageBean<AccommodationFavoriteVO> getAccommodationFavorites(Page<Favorite> param) {
        // 从page对象中获取用户ID（如果已设置）
        Long userId = null;
        if (param.getRecords() != null && !param.getRecords().isEmpty()) {
            userId = param.getRecords().get(0).getUserId();
        }
        
        // 构造查询条件
        LambdaQueryWrapper<Favorite> queryWrapper = Wrappers.<Favorite>lambdaQuery()
                .isNotNull(Favorite::getAccommodationId);
                
        // 如果用户ID存在，则添加用户ID过滤条件
        if (userId != null) {
            queryWrapper.eq(Favorite::getUserId, userId);
        }
        
        Page<Favorite> favoritePage = favoriteMapper.selectPage(param, queryWrapper);
        
        // 获取住宿ID列表
        List<Long> accommodationIds = favoritePage.getRecords().stream()
            .map(Favorite::getAccommodationId)
            .collect(Collectors.toList());
        
        // 查询对应的住宿信息
        List<Accommodation> accommodations = accommodationMapper.selectBatchIds(accommodationIds);
        
        // 转换为VO对象
        List<AccommodationFavoriteVO> voList = new java.util.ArrayList<>();
        for (int i = 0; i < favoritePage.getRecords().size(); i++) {
            Favorite favorite = favoritePage.getRecords().get(i);
            Accommodation accommodation = accommodations.stream()
                .filter(a -> a.getId().equals(favorite.getAccommodationId()))
                .findFirst()
                .orElse(null);
            
            if (accommodation != null) {
                AccommodationFavoriteVO vo = new AccommodationFavoriteVO();
                vo.setId(favorite.getId());
                vo.setCreateTime(favorite.getCreateTime());
                vo.setUpdateTime(favorite.getUpdateTime());
                vo.setUserId(favorite.getUserId());
                vo.setAccommodationId(favorite.getAccommodationId());
                vo.setName(accommodation.getName());
                vo.setDescription(accommodation.getDescription());
                vo.setLocation(accommodation.getLocation());
                vo.setCoverImage(accommodation.getCoverImage());
                vo.setPricePerNight(accommodation.getPricePerNight());
                vo.setContactPhone(accommodation.getContactPhone());
                voList.add(vo);
            }
        }
        
        // 封装分页结果
        PageBean<AccommodationFavoriteVO> pageBean = new PageBean<>();
        pageBean.setTotal(favoritePage.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum((int) favoritePage.getCurrent());
        pageBean.setPageSize((int) favoritePage.getSize());
        
        return pageBean;
    }
    
    @Override
    public boolean addAccommodationFavorite(Long userId, Long accommodationId) {
        // 检查是否已收藏
        if (isAccommodationFavorited(userId, accommodationId)) {
            return false; // 已收藏，无需重复收藏
        }
        
        // 创建收藏记录
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setAccommodationId(accommodationId);
        
        // 保存到数据库
        return favoriteMapper.insert(favorite) > 0;
    }
    
    @Override
    public boolean removeAccommodationFavorite(Long userId, Long accommodationId) {
        LambdaQueryWrapper<Favorite> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Favorite::getUserId, userId)
               .eq(Favorite::getAccommodationId, accommodationId);
        
        // 删除收藏记录
        return favoriteMapper.delete(wrapper) > 0;
    }
    
    @Override
    public boolean isRestaurantFavorited(Long userId, Long restaurantId) {
        LambdaQueryWrapper<Favorite> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Favorite::getUserId, userId)
               .eq(Favorite::getRestaurantId, restaurantId);
        
        return favoriteMapper.selectCount(wrapper) > 0;
    }
    
    @Override
    public PageBean<RestaurantFavoriteVO> getRestaurantFavorites(Page<Favorite> param) {
        // 从page对象中获取用户ID（如果已设置）
        Long userId = null;
        if (param.getRecords() != null && !param.getRecords().isEmpty()) {
            userId = param.getRecords().get(0).getUserId();
        }
        
        // 构造查询条件
        LambdaQueryWrapper<Favorite> queryWrapper = Wrappers.<Favorite>lambdaQuery()
                .isNotNull(Favorite::getRestaurantId);
                
        // 如果用户ID存在，则添加用户ID过滤条件
        if (userId != null) {
            queryWrapper.eq(Favorite::getUserId, userId);
        }
        
        Page<Favorite> favoritePage = favoriteMapper.selectPage(param, queryWrapper);
        
        // 获取美食ID列表
        List<Long> restaurantIds = favoritePage.getRecords().stream()
            .map(Favorite::getRestaurantId)
            .collect(Collectors.toList());
        
        // 查询对应的美食信息
        List<Restaurant> restaurants = restaurantMapper.selectBatchIds(restaurantIds);
        
        // 转换为VO对象
        List<RestaurantFavoriteVO> voList = new java.util.ArrayList<>();
        for (int i = 0; i < favoritePage.getRecords().size(); i++) {
            Favorite favorite = favoritePage.getRecords().get(i);
            Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getId().equals(favorite.getRestaurantId()))
                .findFirst()
                .orElse(null);
            
            if (restaurant != null) {
                RestaurantFavoriteVO vo = new RestaurantFavoriteVO();
                vo.setId(favorite.getId());
                vo.setCreateTime(favorite.getCreateTime());
                vo.setUpdateTime(favorite.getUpdateTime());
                vo.setUserId(favorite.getUserId());
                vo.setRestaurantId(favorite.getRestaurantId());
                vo.setName(restaurant.getName());
                vo.setDescription(restaurant.getDescription());
                vo.setLocation(restaurant.getLocation());
                vo.setCoverImage(restaurant.getCoverImage());
                vo.setPriceRange(restaurant.getPriceRange());
                vo.setContactPhone(restaurant.getContactPhone());
                voList.add(vo);
            }
        }
        
        // 封装分页结果
        PageBean<RestaurantFavoriteVO> pageBean = new PageBean<>();
        pageBean.setTotal(favoritePage.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum((int) favoritePage.getCurrent());
        pageBean.setPageSize((int) favoritePage.getSize());
        
        return pageBean;
    }
    
    @Override
    public boolean addRestaurantFavorite(Long userId, Long restaurantId) {
        // 检查是否已收藏
        if (isRestaurantFavorited(userId, restaurantId)) {
            return false; // 已收藏，无需重复收藏
        }
        
        // 创建收藏记录
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setRestaurantId(restaurantId);
        
        // 保存到数据库
        return favoriteMapper.insert(favorite) > 0;
    }
    
    @Override
    public boolean removeRestaurantFavorite(Long userId, Long restaurantId) {
        LambdaQueryWrapper<Favorite> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Favorite::getUserId, userId)
               .eq(Favorite::getRestaurantId, restaurantId);
        
        // 删除收藏记录
        return favoriteMapper.delete(wrapper) > 0;
    }
}