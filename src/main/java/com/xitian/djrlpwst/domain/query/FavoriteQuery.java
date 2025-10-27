package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "FavoriteQuery", description = "收藏查询对象")
public class FavoriteQuery extends BaseQuery {
    
    @Schema(description = "用户ID")
    private Long userId;
}