package com.xitian.djrlpwst.domain.vo;

import com.xitian.djrlpwst.bean.base.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AttractionCommentVO", description = "景点评论视图对象")
public class AttractionCommentVO extends BaseVO {
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "用户头像")
    private String userAvatar;
    
    @Schema(description = "景点ID")
    private Long attractionId;
    
    @Schema(description = "景点名称")
    private String attractionName;
    
    @Schema(description = "评论内容")
    private String content;
    
    @Schema(description = "评分(1-5星)")
    private Integer rating;
    
    @Schema(description = "是否审核通过(0-否,1-是)")
    private Integer isApproved;
}