package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "AttractionQuery", description = "景点查询对象")
public class AttractionQuery extends BaseQuery {
    
    @Schema(description = "景点名称")
    private String name;
    
    @Schema(description = "地理位置")
    private String location;
}