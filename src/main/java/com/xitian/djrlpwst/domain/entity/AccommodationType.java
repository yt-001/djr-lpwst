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
@TableName("accommodation_types")
@Schema(name = "AccommodationType", description = "住宿类型实体类")
public class AccommodationType extends BaseEntity {
    
    @NotBlank(message = "类型名称不能为空")
    @Schema(description = "类型名称")
    private String name;
    
    @Schema(description = "类型描述")
    private String description;
}