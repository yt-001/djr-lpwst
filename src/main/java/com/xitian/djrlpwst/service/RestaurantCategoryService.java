package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.RestaurantCategory;
import com.xitian.djrlpwst.domain.query.RestaurantCategoryQuery;

public interface RestaurantCategoryService extends BaseService<RestaurantCategory> {

    PageBean<RestaurantCategory> getPage(PageParam<RestaurantCategoryQuery> param);
}
