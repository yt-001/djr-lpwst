package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "CommentQuery", description = "评论查询对象")
public class CommentQuery extends BaseQuery {
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "评分")
    private Integer rating;
    
    @Schema(description = "是否审核通过")
    private Integer isApproved;
    
    @Schema(description = "景点ID")
    private Long attractionId;
}