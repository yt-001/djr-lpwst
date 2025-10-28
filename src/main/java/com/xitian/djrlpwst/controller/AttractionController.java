package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.converter.AttractionConverter;
import com.xitian.djrlpwst.domain.entity.Attraction;
import com.xitian.djrlpwst.domain.query.AttractionQuery;
import com.xitian.djrlpwst.domain.vo.AttractionListVO;
import com.xitian.djrlpwst.domain.vo.AttractionVO;
import com.xitian.djrlpwst.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attractions")
@Tag(name = "景点管理", description = "景点管理接口")
public class AttractionController extends BaseController<Attraction> {
    
    @Autowired
    private AttractionService attractionService;
    
    @Autowired
    private AttractionConverter attractionConverter;
    
    @PostMapping("/page")
    @Operation(summary = "分页查询景点")
    public ResultBean<PageBean<AttractionListVO>> page(@RequestBody PageParam<AttractionQuery> param) {
        PageBean<AttractionListVO> page = attractionService.getPage(param);
        return ResultBean.success(page);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询景点")
    public ResultBean<AttractionVO> getById(@PathVariable Long id) {
        Attraction attraction = attractionService.getById(id);
        AttractionVO vo = attractionConverter.toVO(attraction);
        return ResultBean.success(vo);
    }
    
    @PostMapping
    @Operation(summary = "新增景点")
    public ResultBean<Void> add(@RequestBody Attraction entity) {
        attractionService.save(entity);
        return ResultBean.success();
    }
    
    @PutMapping
    @Operation(summary = "修改景点")
    public ResultBean<Void> update(@RequestBody Attraction entity) {
        attractionService.updateById(entity);
        return ResultBean.success();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除景点")
    public ResultBean<Void> delete(@PathVariable Long id) {
        attractionService.removeById(id);
        return ResultBean.success();
    }
}