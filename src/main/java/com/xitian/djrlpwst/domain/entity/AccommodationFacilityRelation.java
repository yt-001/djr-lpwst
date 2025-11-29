package com.xitian.djrlpwst.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xitian.djrlpwst.bean.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("accommodation_facility_relations")
@Schema(name = "AccommodationFacilityRelation", description = "住宿设施关联实体类")
public class AccommodationFacilityRelation extends BaseEntity {
    
    @NotNull(message = "住宿ID不能为空")
    @TableField("accommodation_id")
    @Schema(description = "住宿ID")
    private Integer accommodationId;
    
    @NotNull(message = "设施ID不能为空")
    @TableField("facility_id")
    @Schema(description = "设施ID")
    private Integer facilityId;
}