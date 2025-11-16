package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.Accommodation;
import com.xitian.djrlpwst.domain.query.AccommodationQuery;
import com.xitian.djrlpwst.domain.vo.AccommodationVO;
import com.xitian.djrlpwst.domain.vo.AccommodationAdminVO;

public interface AccommodationService extends BaseService<Accommodation> {
    
    /**
     * 根据查询条件和分页参数分页获取住宿列表
     * @param param 分页参数和查询条件
     * @return 分页结果
     */
    PageBean<AccommodationVO> getPage(PageParam<AccommodationQuery> param);
    
    /**
     * 管理员端分页获取住宿详情列表
     * @param param 分页参数和查询条件
     * @return 分页结果
     */
    PageBean<AccommodationAdminVO> getAdminPage(PageParam<AccommodationQuery> param);
}