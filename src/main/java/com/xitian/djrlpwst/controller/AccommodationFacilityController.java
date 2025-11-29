package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.domain.entity.AccommodationFacility;
import com.xitian.djrlpwst.domain.query.AccommodationFacilityQuery;
import com.xitian.djrlpwst.service.AccommodationFacilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accommodation-facilities")
@Tag(name = "住宿设施管理", description = "住宿设施管理接口")
public class AccommodationFacilityController extends BaseController<AccommodationFacility> {
    
    @Autowired
    private AccommodationFacilityService accommodationFacilityService;
    
    @PostMapping("/page")
    @Operation(summary = "分页查询住宿设施")
    public ResultBean<PageBean<AccommodationFacility>> page(@RequestBody PageParam<AccommodationFacilityQuery> param) {
        PageBean<AccommodationFacility> page = accommodationFacilityService.getPage(param);
        return ResultBean.success(page);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询住宿设施")
    public ResultBean<AccommodationFacility> getById(@PathVariable Integer id) {
        AccommodationFacility accommodationFacility = accommodationFacilityService.getById(id);
        return ResultBean.success(accommodationFacility);
    }
    
    @PostMapping
    @Operation(summary = "新增住宿设施")
    public ResultBean<Void> add(@RequestBody @Valid AccommodationFacility accommodationFacility) {
        accommodationFacilityService.save(accommodationFacility);
        return ResultBean.success();
    }
    
    @PutMapping
    @Operation(summary = "修改住宿设施")
    public ResultBean<Void> update(@RequestBody @Valid AccommodationFacility accommodationFacility) {
        accommodationFacilityService.updateById(accommodationFacility);
        return ResultBean.success();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除住宿设施")
    public ResultBean<Void> delete(@PathVariable Integer id) {
        accommodationFacilityService.removeById(id);
        return ResultBean.success();
    }
}