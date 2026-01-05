package com.xitian.djrlpwst.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xitian.djrlpwst.bean.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("restaurant_dishes")
@Schema(name = "RestaurantDish", description = "美食菜品实体类")
public class RestaurantDish extends BaseEntity {

    @TableField("restaurant_id")
    @Schema(description = "所属美食ID")
    private Long restaurantId;

    @TableField("category_id")
    @Schema(description = "菜品分类ID")
    private Long categoryId;

    @Schema(description = "菜品名称")
    private String name;

    @Schema(description = "菜品描述")
    private String description;

    @Schema(description = "菜品价格")
    private BigDecimal price;

    @TableField("image_url")
    @Schema(description = "菜品图片")
    private String imageUrl;

    @TableField("is_recommended")
    @Schema(description = "是否推荐菜：1-是，0-否")
    private Integer isRecommended;

    @TableField("sort_order")
    @Schema(description = "展示顺序（同一店铺内）")
    private Integer sortOrder;
}

