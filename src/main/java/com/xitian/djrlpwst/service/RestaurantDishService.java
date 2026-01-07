package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.RestaurantDish;
import com.xitian.djrlpwst.domain.query.RestaurantDishQuery;
import com.xitian.djrlpwst.domain.vo.RecommendedDishCardVO;

import java.util.List;

public interface RestaurantDishService extends BaseService<RestaurantDish> {

    List<RestaurantDish> listByRestaurantId(Long restaurantId);

    PageBean<RestaurantDish> pageByRestaurantId(Long restaurantId, PageParam<RestaurantDishQuery> param);

    Long addDish(RestaurantDish dish);

    boolean updateDish(RestaurantDish dish);

    boolean deleteDish(Long dishId);

    List<RecommendedDishCardVO> listRecommendedDishCards(Integer limit);

    List<RecommendedDishCardVO> pageRecommendedDishCards(Integer pageNum, Integer pageSize);
}
