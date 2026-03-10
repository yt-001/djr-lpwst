package com.xitian.djrlpwst.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xitian.djrlpwst.bean.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 非物质文化遗产实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("intangible_culture")
public class IntangibleCulture extends BaseEntity {
    
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
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
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
