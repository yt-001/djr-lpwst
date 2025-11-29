package com.xitian.djrlpwst.domain.query;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "AccommodationFacilityQuery", description = "住宿设施查询对象")
public class AccommodationFacilityQuery extends BaseQuery {
    
    @Schema(description = "设施名称")
    private String name;
}