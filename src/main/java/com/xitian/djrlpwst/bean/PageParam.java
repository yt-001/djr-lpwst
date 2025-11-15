package com.xitian.djrlpwst.bean;

import com.xitian.djrlpwst.bean.base.BaseQuery;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PageParam<T extends BaseQuery> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /*---------- 分页必备 ----------*/
    @Min(value = 1, message = "页码必须大于0")
    private int pageNum  = 1;   // 第几页（从1开始，前端友好）
    
    @Min(value = 1, message = "每页条数必须大于0")
    @Max(value = 200, message = "每页条数不能超过200")
    private int pageSize = 10;  // 每页条数

    /*---------- 排序 ----------*/
    private String sortField;   // 排序字段
    private Sort.Direction sortDirection = Sort.Direction.DESC;

    /*---------- 业务条件 ----------*/
    @NotNull(message = "查询条件不能为空")
    private T query;            // 真正的查询条件，泛型隔离

    // 添加最大限制检查
    public void setPageSize(int pageSize) {
        this.pageSize = Math.min(pageSize, 201);  // 上限200条
    }
}