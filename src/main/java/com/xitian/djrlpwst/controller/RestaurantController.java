package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.domain.entity.Restaurant;
import com.xitian.djrlpwst.domain.query.RestaurantQuery;
import com.xitian.djrlpwst.domain.vo.RestaurantListVO;
import com.xitian.djrlpwst.domain.vo.RestaurantVO;
import com.xitian.djrlpwst.domain.vo.RestaurantAdminVO;
import com.xitian.djrlpwst.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@Tag(name = "美食管理", description = "美食管理接口")
public class RestaurantController extends BaseController<Restaurant> {
    
    @Autowired
    private RestaurantService restaurantService;
    
    @Autowired
    private com.xitian.djrlpwst.converter.RestaurantConverter restaurantConverter;
    
    @PostMapping("/page")
    @Operation(summary = "分页查询美食")
    public ResultBean<PageBean<RestaurantListVO>> page(@RequestBody PageParam<RestaurantQuery> param) {
        PageBean<RestaurantListVO> page = restaurantService.getPage(param);
        return ResultBean.success(page);
    }
    
    @PostMapping("/admin/page")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "管理员端分页查询美食详情", description = "仅管理员可访问")
    public ResultBean<PageBean<RestaurantAdminVO>> adminPage(@RequestBody PageParam<RestaurantQuery> param) {
        PageBean<RestaurantAdminVO> page = restaurantService.getAdminPage(param);
        return ResultBean.success(page);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询美食")
    public ResultBean<RestaurantVO> getById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getById(id);
        RestaurantVO vo = restaurantConverter.toVO(restaurant);
        return ResultBean.success(vo);
    }
    
    @PostMapping
    @Operation(summary = "新增美食")
    public ResultBean<Void> add(@RequestBody Restaurant entity) {
        restaurantService.save(entity);
        return ResultBean.success();
    }
    
    @PutMapping
    @Operation(summary = "修改美食")
    public ResultBean<Void> update(@RequestBody Restaurant entity) {
        restaurantService.updateById(entity);
        return ResultBean.success();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除美食")
    public ResultBean<Void> delete(@PathVariable Long id) {
        restaurantService.removeById(id);
        return ResultBean.success();
    }
    
    @PostMapping("/list")
    @Operation(summary = "列表查询美食")
    public ResultBean<List<RestaurantVO>> list(@RequestBody RestaurantQuery query) {
        // TODO: 实现列表查询逻辑
        return ResultBean.success();
    }
}