package com.xitian.djrlpwst.domain.vo;

import com.xitian.djrlpwst.bean.base.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 非物质文化遗产视图对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IntangibleCultureVO extends BaseVO {
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 非遗类型
     */
    private String type;
    
    /**
     * 传承人
     */
    private String inheritor;
    
    /**
     * 封面图片
     */
    private String coverImage;
    
    /**
     * 图片JSON数组
     */
    private String images;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
