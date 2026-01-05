package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.domain.entity.DishCategory;
import com.xitian.djrlpwst.domain.query.DishCategoryQuery;
import com.xitian.djrlpwst.service.DishCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dish-categories")
@Tag(name = "菜品分类管理", description = "菜品分类管理接口")
public class DishCategoryController extends BaseController<DishCategory> {

    @Autowired
    private DishCategoryService dishCategoryService;

    @PostMapping("/page")
    @Operation(summary = "分页查询菜品分类")
    public ResultBean<PageBean<DishCategory>> page(@RequestBody PageParam<DishCategoryQuery> param) {
        PageBean<DishCategory> page = dishCategoryService.getPage(param);
        return ResultBean.success(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询菜品分类")
    public ResultBean<DishCategory> getById(@PathVariable Long id) {
        DishCategory category = dishCategoryService.getById(id);
        return ResultBean.success(category);
    }

    @PostMapping
    @Operation(summary = "新增菜品分类")
    public ResultBean<Void> add(@RequestBody DishCategory category) {
        dishCategoryService.save(category);
        return ResultBean.success();
    }

    @PutMapping
    @Operation(summary = "修改菜品分类")
    public ResultBean<Void> update(@RequestBody DishCategory category) {
        dishCategoryService.updateById(category);
        return ResultBean.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除菜品分类")
    public ResultBean<Void> delete(@PathVariable Long id) {
        dishCategoryService.removeById(id);
        return ResultBean.success();
    }
}
