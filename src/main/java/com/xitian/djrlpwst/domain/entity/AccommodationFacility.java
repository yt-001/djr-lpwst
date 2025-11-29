package com.xitian.djrlpwst.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xitian.djrlpwst.bean.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("accommodation_facilities")
@Schema(name = "AccommodationFacility", description = "住宿设施实体类")
public class AccommodationFacility extends BaseEntity {
    
    @NotBlank(message = "设施名称不能为空")
    @Schema(description = "设施名称")
    private String name;
    
    @Schema(description = "设施描述")
    private String description;
    
    @Schema(description = "设施图标")
    private String icon;
}