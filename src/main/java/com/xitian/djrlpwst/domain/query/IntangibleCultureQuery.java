package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 非物质文化遗产查询对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IntangibleCultureQuery extends BaseQuery {
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 非遗类型
     */
    private String type;
    
    /**
     * 传承人
     */
    private String inheritor;
    
    /**
     * 关键词（用于模糊搜索）
     */
    private String keyword;
}
