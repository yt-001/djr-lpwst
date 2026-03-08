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

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ai_greetings")
@Schema(name = "AiGreeting", description = "AI问候语实体类")
public class AiGreeting extends BaseEntity {

    @Schema(description = "问候语内容")
    private String content;

    @TableField("is_enabled")
    @Schema(description = "是否启用：1-启用，0-禁用")
    private Integer isEnabled;

    @TableField("sort_order")
    @Schema(description = "排序")
    private Integer sortOrder;
}
