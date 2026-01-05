package com.xitian.djrlpwst.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xitian.djrlpwst.bean.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("restaurant_categories")
@Schema(name = "RestaurantCategory", description = "餐厅分类实体类")
public class RestaurantCategory extends BaseEntity {

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "排序")
    private Integer sortOrder;

    @Schema(description = "是否启用：1-启用，0-禁用")
    private Integer isEnabled;
}

