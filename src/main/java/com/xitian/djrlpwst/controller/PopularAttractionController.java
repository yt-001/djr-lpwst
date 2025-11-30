package com.xitian.djrlpwst.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.StatusCode;
import com.xitian.djrlpwst.converter.PopularAttractionConverter;
import com.xitian.djrlpwst.domain.dto.PopularAttractionAddDTO;
import com.xitian.djrlpwst.domain.entity.Attraction;
import com.xitian.djrlpwst.domain.entity.PopularAttraction;
import com.xitian.djrlpwst.domain.query.AttractionQuery;
import com.xitian.djrlpwst.domain.vo.AttractionMapVO;
import com.xitian.djrlpwst.domain.vo.AttractionSimpleVO;
import com.xitian.djrlpwst.service.AttractionService;
import com.xitian.djrlpwst.service.PopularAttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/popular-attractions")
@Tag(name = "热门景点管理", description = "热门景点管理接口")
public class PopularAttractionController {
    
    @Autowired
    private PopularAttractionService popularAttractionService;
    
    @Autowired
    private AttractionService attractionService;
    
    @Autowired
    private PopularAttractionConverter popularAttractionConverter;
    
    @GetMapping
    @Operation(summary = "查询全部热门景点")
    public ResultBean<List<PopularAttraction>> list() {
        List<PopularAttraction> popularAttractions = popularAttractionService.list();
        return ResultBean.success(popularAttractions);
    }
    
    @PostMapping("/attractions/page")
    @Operation(summary = "分页查询景点列表（用于添加热门景点）")
    public ResultBean<PageBean<AttractionSimpleVO>> attractionsPage(@RequestBody PageParam<AttractionQuery> param) {
        // 创建分页对象
        Page<Attraction> page = new Page<>(param.getPageNum(), param.getPageSize());
        
        // 构造查询条件
        LambdaQueryWrapper<Attraction> queryWrapper = new LambdaQueryWrapper<>();
        if (param.getQuery() != null) {
            // 如果传入ID，进行精准查询
            if (param.getQuery().getId() != null) {
                queryWrapper.eq(Attraction::getId, param.getQuery().getId());
            }
            // 如果传入名称，进行模糊查询
            if (param.getQuery().getName() != null && !param.getQuery().getName().isEmpty()) {
                queryWrapper.like(Attraction::getName, param.getQuery().getName());
            }
        }
        
        // 分页查询景点
        Page<Attraction> attractionPage = attractionService.page(page, queryWrapper);
        
        // 转换为简单VO对象
        List<AttractionSimpleVO> simpleVOs = attractionPage.getRecords().stream()
                .map(popularAttractionConverter::toSimpleVO)
                .collect(Collectors.toList());
        
        // 封装分页结果
        PageBean<AttractionSimpleVO> pageBean = new PageBean<>();
        pageBean.setTotal(attractionPage.getTotal());
        pageBean.setList(simpleVOs);
        pageBean.setPageNum((int) attractionPage.getCurrent());
        pageBean.setPageSize((int) attractionPage.getSize());
        
        return ResultBean.success(pageBean);
    }
    
    @GetMapping("/map")
    @Operation(summary = "用户地图查看热门景点接口")
    public ResultBean<List<AttractionMapVO>> map() {
        List<PopularAttraction> popularAttractions = popularAttractionService.list();
        
        List<AttractionMapVO> mapVOs = popularAttractions.stream()
                .map(popularAttractionConverter::toMapVO)
                .collect(Collectors.toList());
        
        return ResultBean.success(mapVOs);
    }
    
    @PostMapping
    @Operation(summary = "添加热门景点")
    public ResultBean<Void> add(@RequestBody PopularAttractionAddDTO addDTO) {
        // 根据景点ID查找原景点信息
        Attraction attraction = attractionService.getById(addDTO.getAttractionId());
        if (attraction == null) {
            return ResultBean.fail(StatusCode.VALIDATION_ERROR, "景点不存在");
        }
        
        // 检查是否已经添加为热门景点
        PopularAttraction existing = popularAttractionService.getOne(
            new LambdaQueryWrapper<PopularAttraction>()
                .eq(PopularAttraction::getAttractionId, addDTO.getAttractionId())
        );
        if (existing != null) {
            return ResultBean.fail(StatusCode.VALIDATION_ERROR, "该景点已经是热门景点");
        }
        
        // 创建热门景点对象
        PopularAttraction popularAttraction = new PopularAttraction();
        popularAttraction.setAttractionId(attraction.getId());
        popularAttraction.setName(attraction.getName());
        popularAttraction.setDescription(attraction.getDescription());
        popularAttraction.setLatitude(attraction.getLatitude());
        popularAttraction.setLongitude(attraction.getLongitude());
        
        // 保存到数据库
        popularAttractionService.save(popularAttraction);
        
        return ResultBean.success();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询热门景点")
    public ResultBean<PopularAttraction> getById(@PathVariable Long id) {
        PopularAttraction popularAttraction = popularAttractionService.getById(id);
        if (popularAttraction == null) {
            return ResultBean.fail(StatusCode.VALIDATION_ERROR, "热门景点不存在");
        }
        return ResultBean.success(popularAttraction);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除热门景点")
    public ResultBean<Void> delete(@PathVariable Long id) {
        if (!popularAttractionService.removeById(id)) {
            return ResultBean.fail(StatusCode.BUSINESS_ERROR, "删除失败");
        }
        return ResultBean.success();
    }
}