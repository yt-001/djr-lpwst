package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.Restaurant;
import com.xitian.djrlpwst.domain.query.RestaurantQuery;
import com.xitian.djrlpwst.domain.vo.RestaurantListVO;

public interface RestaurantService extends BaseService<Restaurant> {
    
    /**
     * 根据查询条件和分页参数分页获取美食列表
     * @param param 分页参数和查询条件
     * @return 分页结果
     */
    PageBean<RestaurantListVO> getPage(PageParam<RestaurantQuery> param);
}