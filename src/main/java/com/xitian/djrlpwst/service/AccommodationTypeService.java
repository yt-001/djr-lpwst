package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.AccommodationType;
import com.xitian.djrlpwst.domain.query.AccommodationTypeQuery;

public interface AccommodationTypeService extends BaseService<AccommodationType> {
    
    /**
     * 分页查询住宿类型
     * @param param 分页参数
     * @return 住宿类型分页结果
     */
    PageBean<AccommodationType> getPage(PageParam<AccommodationTypeQuery> param);
}