package com.xitian.djrlpwst.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.StatusCode;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.domain.dto.AttractionFavoriteDTO;
import com.xitian.djrlpwst.domain.entity.Favorite;
import com.xitian.djrlpwst.domain.query.FavoriteQuery;
import com.xitian.djrlpwst.domain.vo.AttractionFavoriteVO;
import com.xitian.djrlpwst.domain.vo.FavoriteVO;
import com.xitian.djrlpwst.service.FavoriteService;
import com.xitian.djrlpwst.util.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@Tag(name = "收藏管理", description = "收藏管理接口")
public class FavoriteController extends BaseController<Favorite> {
    
    @Autowired
    private FavoriteService favoriteService;
    
    @PostMapping("/page")
    @Operation(summary = "分页查询收藏")
    public ResultBean<PageBean<FavoriteVO>> page(@RequestBody PageParam<FavoriteQuery> param) {
        // TODO: 实现分页查询逻辑
        return ResultBean.success();
    }
    
    @PostMapping("/attractions/page")
    @Operation(summary = "分页查询景点收藏")
    public ResultBean<PageBean<AttractionFavoriteVO>> attractionFavoritesPage(@RequestBody PageParam<FavoriteQuery> param) {
        // 创建分页对象
        Page<Favorite> page = new Page<>(param.getPageNum(), param.getPageSize());
        
        // 获取分页数据
        PageBean<AttractionFavoriteVO> pageBean = favoriteService.getAttractionFavorites(page);
        
        return ResultBean.success(pageBean);
    }
    
    @PostMapping("/attractions/check")
    @Operation(summary = "检查景点是否已收藏")
    public ResultBean<Boolean> checkAttractionFavorite(@RequestBody AttractionFavoriteDTO dto) {
        boolean isFavorited = favoriteService.isAttractionFavorited(dto.getUserId(), dto.getAttractionId());
        return ResultBean.success(isFavorited);
    }
    
    @PostMapping("/attractions/add")
    @Operation(summary = "收藏景点")
    public ResultBean<Void> addAttractionFavorite(@RequestBody AttractionFavoriteDTO dto) {
        boolean result = favoriteService.addAttractionFavorite(dto.getUserId(), dto.getAttractionId());
        if (result) {
            return ResultBean.success();
        } else {
            return ResultBean.fail(StatusCode.BUSINESS_ERROR, "收藏失败，可能已收藏过该景点");
        }
    }
    
    @PostMapping("/attractions/remove")
    @Operation(summary = "取消景点收藏")
    public ResultBean<Void> removeAttractionFavorite(@RequestBody AttractionFavoriteDTO dto) {
        boolean result = favoriteService.removeAttractionFavorite(dto.getUserId(), dto.getAttractionId());
        if (result) {
            return ResultBean.success();
        } else {
            return ResultBean.fail(StatusCode.BUSINESS_ERROR, "取消收藏失败，可能未收藏过该景点");
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询收藏")
    public ResultBean<FavoriteVO> getById(@PathVariable Long id) {
        // TODO: 实现根据ID查询逻辑
        return ResultBean.success();
    }
    
    @PostMapping
    @Operation(summary = "新增收藏")
    public ResultBean<Void> add(@RequestBody Favorite entity) {
        // TODO: 实现新增逻辑
        return ResultBean.success();
    }
    
    @PutMapping
    @Operation(summary = "修改收藏")
    public ResultBean<Void> update(@RequestBody Favorite entity) {
        // TODO: 实现修改逻辑
        return ResultBean.success();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除收藏")
    public ResultBean<Void> delete(@PathVariable Long id) {
        // TODO: 实现删除逻辑
        return ResultBean.success();
    }
    
    @PostMapping("/list")
    @Operation(summary = "列表查询收藏")
    public ResultBean<List<FavoriteVO>> list(@RequestBody FavoriteQuery query) {
        // TODO: 实现列表查询逻辑
        return ResultBean.success();
    }
}