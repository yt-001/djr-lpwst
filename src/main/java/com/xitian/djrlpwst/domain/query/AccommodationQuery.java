package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "AccommodationQuery", description = "住宿查询对象")
public class AccommodationQuery extends BaseQuery {
    
    @Schema(description = "住宿名称")
    private String name;
    
    @Schema(description = "类型ID")
    private Integer typeId;
    
    @Schema(description = "地理位置")
    private String location;
}