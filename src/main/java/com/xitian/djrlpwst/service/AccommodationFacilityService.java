package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.AccommodationFacility;
import com.xitian.djrlpwst.domain.query.AccommodationFacilityQuery;

public interface AccommodationFacilityService extends BaseService<AccommodationFacility> {
    
    /**
     * 分页查询住宿设施
     * @param param 分页参数
     * @return 住宿设施分页结果
     */
    PageBean<AccommodationFacility> getPage(PageParam<AccommodationFacilityQuery> param);
}