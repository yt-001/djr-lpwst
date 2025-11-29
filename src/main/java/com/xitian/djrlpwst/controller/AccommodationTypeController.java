package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.domain.entity.AccommodationType;
import com.xitian.djrlpwst.domain.query.AccommodationTypeQuery;
import com.xitian.djrlpwst.service.AccommodationTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accommodation-types")
@Tag(name = "住宿类型管理", description = "住宿类型管理接口")
public class AccommodationTypeController extends BaseController<AccommodationType> {
    
    @Autowired
    private AccommodationTypeService accommodationTypeService;
    
    @PostMapping("/page")
    @Operation(summary = "分页查询住宿类型")
    public ResultBean<PageBean<AccommodationType>> page(@RequestBody PageParam<AccommodationTypeQuery> param) {
        PageBean<AccommodationType> page = accommodationTypeService.getPage(param);
        return ResultBean.success(page);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询住宿类型")
    public ResultBean<AccommodationType> getById(@PathVariable Integer id) {
        AccommodationType accommodationType = accommodationTypeService.getById(id);
        return ResultBean.success(accommodationType);
    }
    
    @PostMapping
    @Operation(summary = "新增住宿类型")
    public ResultBean<Void> add(@RequestBody @Valid AccommodationType accommodationType) {
        accommodationTypeService.save(accommodationType);
        return ResultBean.success();
    }
    
    @PutMapping
    @Operation(summary = "修改住宿类型")
    public ResultBean<Void> update(@RequestBody @Valid AccommodationType accommodationType) {
        accommodationTypeService.updateById(accommodationType);
        return ResultBean.success();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除住宿类型")
    public ResultBean<Void> delete(@PathVariable Integer id) {
        accommodationTypeService.removeById(id);
        return ResultBean.success();
    }
}