package com.xitian.djrlpwst.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.StatusCode;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.domain.entity.IntangibleCulture;
import com.xitian.djrlpwst.domain.query.IntangibleCultureQuery;
import com.xitian.djrlpwst.domain.vo.IntangibleCultureVO;
import com.xitian.djrlpwst.service.IntangibleCultureService;
import com.xitian.djrlpwst.util.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 非物质文化遗产控制器
 */
@RestController
@RequestMapping("/intangible-cultures")
@Tag(name = "非物质文化遗产管理", description = "非物质文化遗产管理接口")
public class IntangibleCultureController extends BaseController<IntangibleCulture> {
    
    @Autowired
    private IntangibleCultureService intangibleCultureService;
    
    @PostMapping("/page")
    @Operation(summary = "分页查询非物质文化遗产")
    public ResultBean<PageBean<IntangibleCultureVO>> page(@RequestBody PageParam<IntangibleCultureQuery> param) {
        // 创建分页对象
        Page<IntangibleCulture> page = new Page<>(param.getPageNum(), param.getPageSize());
        
        // 获取分页数据
        PageBean<IntangibleCultureVO> pageBean = intangibleCultureService.getIntangibleCultures(page, param.getQuery());
        
        return ResultBean.success(pageBean);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询非物质文化遗产")
    public ResultBean<IntangibleCultureVO> getById(@PathVariable Long id) {
        IntangibleCultureVO culture = intangibleCultureService.getIntangibleCultureById(id);
        if (culture == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "非物质文化遗产不存在");
        }
        return ResultBean.success(culture);
    }
    
    @PostMapping
    @Operation(summary = "新增非物质文化遗产")
    public ResultBean<Void> add(@RequestBody IntangibleCulture entity) {
        intangibleCultureService.save(entity);
        return ResultBean.success();
    }
    
    @PutMapping
    @Operation(summary = "修改非物质文化遗产")
    public ResultBean<Void> update(@RequestBody IntangibleCulture entity) {
        if (entity.getId() == null) {
            return ResultBean.fail(StatusCode.VALIDATION_ERROR, "ID不能为空");
        }
        IntangibleCulture existing = intangibleCultureService.getById(entity.getId());
        if (existing == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "非物质文化遗产不存在");
        }
        BeanUtil.copyNonNullProperties(entity, existing);
        intangibleCultureService.updateById(existing);
        return ResultBean.success();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除非物质文化遗产")
    public ResultBean<Void> delete(@PathVariable Long id) {
        boolean ok = intangibleCultureService.removeById(id);
        if (!ok) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "非物质文化遗产不存在");
        }
        return ResultBean.success();
    }
    
    @PostMapping("/list")
    @Operation(summary = "列表查询非物质文化遗产")
    public ResultBean<List<IntangibleCultureVO>> list(@RequestBody IntangibleCultureQuery query) {
        // 创建分页对象，获取所有数据
        Page<IntangibleCulture> page = new Page<>(1, 1000);
        PageBean<IntangibleCultureVO> pageBean = intangibleCultureService.getIntangibleCultures(page, query);
        return ResultBean.success(pageBean.getList());
    }
}
