package com.xitian.djrlpwst.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.IntangibleCulture;
import com.xitian.djrlpwst.domain.query.IntangibleCultureQuery;
import com.xitian.djrlpwst.domain.vo.IntangibleCultureVO;

/**
 * 非物质文化遗产服务接口
 */
public interface IntangibleCultureService extends BaseService<IntangibleCulture> {
    
    /**
     * 分页查询非物质文化遗产
     * @param page 分页参数
     * @param query 查询条件
     * @return 分页数据
     */
    PageBean<IntangibleCultureVO> getIntangibleCultures(Page<IntangibleCulture> page, IntangibleCultureQuery query);
    
    /**
     * 根据ID查询非物质文化遗产
     * @param id 主键ID
     * @return 非物质文化遗产详情
     */
    IntangibleCultureVO getIntangibleCultureById(Long id);
}
