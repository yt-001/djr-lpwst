package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.ResultBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guide-routes")
@Tag(name = "向导路线管理", description = "向导路线接口（暂不实现数据逻辑）")
public class GuideRouteController {

    @PostMapping
    @Operation(summary = "新增向导路线")
    public ResultBean<Void> add() {
        return ResultBean.success();
    }

    @PutMapping
    @Operation(summary = "修改向导路线")
    public ResultBean<Void> update() {
        return ResultBean.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除向导路线")
    public ResultBean<Void> delete(@PathVariable Long id) {
        return ResultBean.success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询向导路线")
    public ResultBean<Object> getById(@PathVariable Long id) {
        return ResultBean.success(null);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询向导路线")
    public ResultBean<Object> page() {
        return ResultBean.success(null);
    }
}

