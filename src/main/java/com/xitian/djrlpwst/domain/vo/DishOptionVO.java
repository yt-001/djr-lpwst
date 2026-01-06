package com.xitian.djrlpwst.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "DishOptionVO", description = "菜品下拉选项VO")
public class DishOptionVO {

    @Schema(description = "菜品ID")
    private Long id;

    @Schema(description = "菜品名称")
    private String name;
}

