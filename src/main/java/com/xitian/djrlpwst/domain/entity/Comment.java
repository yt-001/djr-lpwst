package com.xitian.djrlpwst.domain.entity;

import com.xitian.djrlpwst.bean.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("comments")
@Schema(name = "Comment", description = "评论实体类")
public class Comment extends BaseEntity {
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "景点ID")
    private Long attractionId;
    
    @Schema(description = "美食ID")
    private Long restaurantId;
    
    @Schema(description = "非遗ID")
    private Long cultureId;
    
    @Schema(description = "住宿ID")
    private Long accommodationId;
    
    @Schema(description = "评论内容")
    private String content;
    
    @Schema(description = "评分(1-5星)")
    private Integer rating;
    
    @Schema(description = "是否审核通过(0-否,1-是)")
    private Integer isApproved;
}