package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.Accommodation;
import com.xitian.djrlpwst.domain.query.AccommodationQuery;
import com.xitian.djrlpwst.domain.vo.AccommodationVO;
import com.xitian.djrlpwst.domain.vo.AccommodationSimpleVO;
import com.xitian.djrlpwst.domain.vo.AccommodationDetailVO;
import com.xitian.djrlpwst.domain.vo.AccommodationAdminVO;

public interface  AccommodationService extends BaseService<Accommodation> {
    
    /**
     * 分页查询住宿信息（前端用户使用）
     * @param param 分页参数和查询条件
     * @return 住宿信息分页结果
     */
    PageBean<AccommodationSimpleVO> getPage(PageParam<AccommodationQuery> param);
    
    /**
     * 根据ID查询住宿详情
     * @param id 住宿ID
     * @return 住宿详情
     */
    AccommodationDetailVO getById(Long id);
    
    /**
     * 根据ID查询住宿实体
     * @param id 住宿ID
     * @return 住宿实体
     */
    Accommodation getEntityById(Long id);
    
    /**
     * 分页查询住宿信息（管理端使用）
     * @param param 分页参数和查询条件
     * @return 住宿信息分页结果
     */
    PageBean<AccommodationAdminVO> getAdminPage(PageParam<AccommodationQuery> param);
}