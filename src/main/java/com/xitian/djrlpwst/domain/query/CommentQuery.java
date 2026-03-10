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

    @Schema(description = "餐厅ID")
    private Long restaurantId;

    @Schema(description = "住宿ID")
    private Long accommodationId;

    @Schema(description = "非遗ID")
    private Long cultureId;

    @Schema(description = "创建时间开始，格式：yyyy-MM-dd")
    private String createTimeStart;

    @Schema(description = "创建时间结束，格式：yyyy-MM-dd")
    private String createTimeEnd;
}
