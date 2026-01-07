package com.xitian.djrlpwst.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(name = "RecommendedDishCardVO", description = "推荐菜系卡片VO")
public class RecommendedDishCardVO {

    @Schema(description = "菜品ID")
    private Long dishId;

    @Schema(description = "菜品名称")
    private String dishName;

    @Schema(description = "菜品描述")
    private String dishDescription;

    @Schema(description = "菜品价格")
    private BigDecimal dishPrice;

    @Schema(description = "菜品图片URL（后端返回完整路径）")
    private String dishImageUrl;

    @Schema(description = "菜品分类ID")
    private Long dishCategoryId;

    @Schema(description = "菜品分类名称")
    private String dishCategoryName;

    @Schema(description = "商家ID")
    private Long restaurantId;

    @Schema(description = "商家名称")
    private String restaurantName;

    @Schema(description = "商家封面图")
    private String restaurantCoverImage;

    @Schema(description = "商家位置")
    private String restaurantLocation;

    @Schema(description = "商家营业时间")
    private String restaurantOpenHours;

    @Schema(description = "商家价格范围")
    private String restaurantPriceRange;

    @Schema(description = "商家电话")
    private String restaurantContactPhone;

    @Schema(description = "商家评分")
    private BigDecimal restaurantRating;

    @Schema(description = "商家分类ID")
    private Long restaurantCategoryId;

    @Schema(description = "商家分类名称")
    private String restaurantCategoryName;
}
