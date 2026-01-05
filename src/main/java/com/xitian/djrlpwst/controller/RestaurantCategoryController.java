package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.domain.entity.RestaurantCategory;
import com.xitian.djrlpwst.domain.query.RestaurantCategoryQuery;
import com.xitian.djrlpwst.service.RestaurantCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant-categories")
@Tag(name = "餐厅分类管理", description = "餐厅分类管理接口")
public class RestaurantCategoryController extends BaseController<RestaurantCategory> {

    @Autowired
    private RestaurantCategoryService restaurantCategoryService;

    @PostMapping("/page")
    @Operation(summary = "分页查询餐厅分类")
    public ResultBean<PageBean<RestaurantCategory>> page(@RequestBody PageParam<RestaurantCategoryQuery> param) {
        PageBean<RestaurantCategory> page = restaurantCategoryService.getPage(param);
        return ResultBean.success(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询餐厅分类")
    public ResultBean<RestaurantCategory> getById(@PathVariable Long id) {
        RestaurantCategory category = restaurantCategoryService.getById(id);
        return ResultBean.success(category);
    }

    @PostMapping
    @Operation(summary = "新增餐厅分类")
    public ResultBean<Void> add(@RequestBody RestaurantCategory category) {
        restaurantCategoryService.save(category);
        return ResultBean.success();
    }

    @PutMapping
    @Operation(summary = "修改餐厅分类")
    public ResultBean<Void> update(@RequestBody RestaurantCategory category) {
        restaurantCategoryService.updateById(category);
        return ResultBean.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除餐厅分类")
    public ResultBean<Void> delete(@PathVariable Long id) {
        restaurantCategoryService.removeById(id);
        return ResultBean.success();
    }
}
