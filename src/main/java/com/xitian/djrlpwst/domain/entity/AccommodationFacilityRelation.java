package com.xitian.djrlpwst.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("accommodation_facility_relations")
@Schema(name = "AccommodationFacilityRelation", description = "住宿设施关联实体类")
public class AccommodationFacilityRelation {

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "编号")
    private Long id;

    @NotNull(message = "住宿ID不能为空")
    @TableField("accommodation_id")
    @Schema(description = "住宿ID")
    private Integer accommodationId;

    @NotNull(message = "设施ID不能为空")
    @TableField("facility_id")
    @Schema(description = "设施ID")
    private Integer facilityId;

    @TableField(value = "create_time")
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
