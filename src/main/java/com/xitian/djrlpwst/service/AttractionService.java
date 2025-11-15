package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.Attraction;
import com.xitian.djrlpwst.domain.query.AttractionQuery;
import com.xitian.djrlpwst.domain.vo.AttractionListVO;
import com.xitian.djrlpwst.domain.vo.AttractionDetailVO;

public interface AttractionService extends BaseService<Attraction> {

    /**
     * 根据查询条件和分页参数分页获取景点列表
     * @param param 分页参数和查询条件
     * @return 分页结果
     */
    PageBean<AttractionListVO> getPage(PageParam<AttractionQuery> param);
    
    /**
     * 管理员端分页获取景点详情列表
     * @param param 分页参数和查询条件
     * @return 分页结果
     */
    PageBean<AttractionDetailVO> getAdminPage(PageParam<AttractionQuery> param);
}