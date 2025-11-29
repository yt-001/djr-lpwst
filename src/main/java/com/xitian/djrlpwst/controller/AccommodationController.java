package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.StatusCode;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.converter.AccommodationConverter;
import com.xitian.djrlpwst.domain.dto.AccommodationUpdateDTO;
import com.xitian.djrlpwst.domain.entity.Accommodation;
import com.xitian.djrlpwst.domain.query.AccommodationQuery;
import com.xitian.djrlpwst.domain.vo.AccommodationDetailVO;
import com.xitian.djrlpwst.domain.vo.AccommodationVO;
import com.xitian.djrlpwst.domain.vo.AccommodationSimpleVO;
import com.xitian.djrlpwst.domain.vo.AccommodationAdminVO;
import com.xitian.djrlpwst.service.AccommodationService;
import com.xitian.djrlpwst.util.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accommodations")
@Tag(name = "住宿管理", description = "住宿管理接口")
public class AccommodationController extends BaseController<Accommodation> {
    
    @Autowired
    private AccommodationService accommodationService;
    
    @Autowired
    private AccommodationConverter accommodationConverter;
    
    @PostMapping("/page")
    @Operation(summary = "分页查询住宿")
    public ResultBean<PageBean<AccommodationSimpleVO>> page(@RequestBody PageParam<AccommodationQuery> param) {
        PageBean<AccommodationSimpleVO> page = accommodationService.getPage(param);
        return ResultBean.success(page);
    }
    
    @PostMapping("/admin/page")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "管理员端分页查询住宿详情", description = "仅管理员可访问")
    public ResultBean<PageBean<AccommodationAdminVO>> adminPage(@RequestBody PageParam<AccommodationQuery> param) {
        PageBean<AccommodationAdminVO> page = accommodationService.getAdminPage(param);
        return ResultBean.success(page);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询住宿")
    public ResultBean<AccommodationDetailVO> getById(@PathVariable Long id) {
        AccommodationDetailVO accommodation = accommodationService.getById(id);
        return ResultBean.success(accommodation);
    }
    
    @PostMapping
    @Operation(summary = "新增住宿")
    public ResultBean<Void> add(@RequestBody Accommodation entity) {
        accommodationService.save(entity);
        return ResultBean.success();
    }
    
    @PutMapping
    @Operation(summary = "修改住宿")
    public ResultBean<Void> update(@Valid @RequestBody AccommodationUpdateDTO updateDTO) {
        // 获取原始住宿信息
        Accommodation existingAccommodation = accommodationService.getEntityById(updateDTO.getId());
        if (existingAccommodation == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "住宿不存在");
        }
        
        // 只复制非空属性到现有实体
        BeanUtil.copyNonNullProperties(updateDTO, existingAccommodation);
        
        // 更新住宿信息
        accommodationService.updateById(existingAccommodation);
        return ResultBean.success();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除住宿")
    public ResultBean<Void> delete(@PathVariable Long id) {
        accommodationService.removeById(id);
        return ResultBean.success();
    }
    
    @PostMapping("/list")
    @Operation(summary = "列表查询住宿")
    public ResultBean<Void> list(@RequestBody AccommodationQuery query) {
        // TODO: 实现列表查询逻辑
        return ResultBean.success();
    }
}