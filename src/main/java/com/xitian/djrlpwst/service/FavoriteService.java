package com.xitian.djrlpwst.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.Favorite;
import com.xitian.djrlpwst.domain.query.FavoriteQuery;
import com.xitian.djrlpwst.domain.vo.AttractionFavoriteVO;

import java.util.List;

public interface FavoriteService extends BaseService<Favorite> {
    
    /**
     * 检查用户是否已收藏指定景点
     * @param userId 用户ID
     * @param attractionId 景点ID
     * @return 是否已收藏
     */
    boolean isAttractionFavorited(Long userId, Long attractionId);
    
    /**
     * 获取用户收藏的景点列表（分页）
     * @param param 查询参数
     * @return 分页数据
     */
    PageBean<AttractionFavoriteVO> getAttractionFavorites(Page<Favorite> param);
    
    /**
     * 添加景点收藏
     * @param userId 用户ID
     * @param attractionId 景点ID
     * @return 是否收藏成功
     */
    boolean addAttractionFavorite(Long userId, Long attractionId);
    
    /**
     * 取消景点收藏
     * @param userId 用户ID
     * @param attractionId 景点ID
     * @return 是否取消成功
     */
    boolean removeAttractionFavorite(Long userId, Long attractionId);
}