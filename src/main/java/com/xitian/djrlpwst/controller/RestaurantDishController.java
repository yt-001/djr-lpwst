package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.domain.entity.RestaurantDish;
import com.xitian.djrlpwst.domain.query.RestaurantDishQuery;
import com.xitian.djrlpwst.domain.vo.DishOptionVO;
import com.xitian.djrlpwst.domain.vo.RecommendedDishCardVO;
import com.xitian.djrlpwst.service.RestaurantDishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
@Tag(name = "美食菜品管理", description = "美食菜品管理接口")
public class RestaurantDishController {

    @Autowired
    private RestaurantDishService restaurantDishService;

    @Autowired
    private com.xitian.djrlpwst.converter.RestaurantDishConverter restaurantDishConverter;

    @GetMapping("/{id}/dishes")
    @Operation(summary = "查询美食菜品列表")
    public ResultBean<List<RestaurantDish>> dishes(@PathVariable Long id) {
        List<RestaurantDish> list = restaurantDishService.listByRestaurantId(id);
        return ResultBean.success(list);
    }

    @GetMapping("/{id}/dishes/options")
    @Operation(summary = "查询美食菜品下拉选项(仅ID和名称)", description = "仅管理员可访问")
    public ResultBean<List<DishOptionVO>> dishOptions(@PathVariable Long id) {
        List<RestaurantDish> list = restaurantDishService.listByRestaurantId(id);
        List<DishOptionVO> options = (list == null ? Collections.<RestaurantDish>emptyList() : list).stream()
                .map(d -> {
                    DishOptionVO vo = new DishOptionVO();
                    vo.setId(d.getId());
                    vo.setName(d.getName());
                    return vo;
                })
                .collect(Collectors.toList());
        return ResultBean.success(options);
    }

    @PostMapping("/{id}/dishes/page")
    @Operation(summary = "分页查询美食菜品列表")
    public ResultBean<PageBean<RestaurantDish>> dishesPage(@PathVariable Long id, @RequestBody PageParam<RestaurantDishQuery> param) {
        PageBean<RestaurantDish> page = restaurantDishService.pageByRestaurantId(id, param);
        return ResultBean.success(page);
    }

    @GetMapping("/recommended-dishes/cards")
    @Operation(summary = "推荐菜品卡片列表")
    public ResultBean<List<RecommendedDishCardVO>> recommendedDishCards(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        List<RecommendedDishCardVO> list;
        if (pageNum != null || pageSize != null) {
            list = restaurantDishService.pageRecommendedDishCards(pageNum, pageSize);
        } else {
            list = restaurantDishService.listRecommendedDishCards(limit);
        }
        return ResultBean.success(list);
    }

    @PostMapping("/{id}/dishes")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "新增美食菜品", description = "仅管理员可访问")
    public ResultBean<Void> addDish(@PathVariable Long id, @RequestBody RestaurantDish dish) {
        dish.setRestaurantId(id);
        restaurantDishService.addDish(dish);
        return ResultBean.success();
    }

    @PutMapping("/{id}/dishes/{dishId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "修改美食菜品", description = "仅管理员可访问")
    public ResultBean<Void> updateDish(@PathVariable Long id, @PathVariable Long dishId, @RequestBody RestaurantDish dish) {
        dish.setId(dishId);
        dish.setRestaurantId(id);
        restaurantDishService.updateDish(dish);
        return ResultBean.success();
    }

    @DeleteMapping("/{id}/dishes/{dishId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "删除美食菜品", description = "仅管理员可访问")
    public ResultBean<Void> deleteDish(@PathVariable Long id, @PathVariable Long dishId) {
        restaurantDishService.deleteDish(dishId);
        return ResultBean.success();
    }
}
