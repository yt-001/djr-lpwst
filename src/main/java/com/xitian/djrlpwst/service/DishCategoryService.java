package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.DishCategory;
import com.xitian.djrlpwst.domain.query.DishCategoryQuery;

public interface DishCategoryService extends BaseService<DishCategory> {

    PageBean<DishCategory> getPage(PageParam<DishCategoryQuery> param);
}
