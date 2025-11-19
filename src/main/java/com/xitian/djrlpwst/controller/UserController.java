package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.StatusCode;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.converter.UserConverter;
import com.xitian.djrlpwst.domain.dto.UserUpdateDTO;
import com.xitian.djrlpwst.domain.entity.User;
import com.xitian.djrlpwst.domain.query.UserQuery;
import com.xitian.djrlpwst.domain.vo.UserVO;
import com.xitian.djrlpwst.service.UserService;
import com.xitian.djrlpwst.util.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "用户管理", description = "用户管理接口")
public class UserController extends BaseController<User> {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserConverter userConverter;
    
    @PostMapping("/page")
    @Operation(summary = "分页查询用户")
    public ResultBean<PageBean<UserVO>> page(@RequestBody PageParam<UserQuery> param) {
        // TODO: 实现分页查询逻辑
        return ResultBean.success();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询用户")
    public ResultBean<UserVO> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "用户不存在");
        }
        UserVO userVO = userConverter.toVO(user);
        return ResultBean.success(userVO);
    }
    
    @PostMapping
    @Operation(summary = "新增用户")
    public ResultBean<Void> add(@RequestBody User entity) {
        // TODO: 实现新增逻辑
        return ResultBean.success();
    }
    
    @PutMapping
    @Operation(summary = "修改用户")
    public ResultBean<Void> update(@Valid @RequestBody UserUpdateDTO updateDTO) {
        // 获取原始用户信息
        User existingUser = userService.getById(updateDTO.getId());
        if (existingUser == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "用户不存在");
        }
        
        // 只复制非空属性到现有实体
        BeanUtil.copyNonNullProperties(updateDTO, existingUser);
        
        // 更新用户信息
        userService.updateById(existingUser);
        return ResultBean.success();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户")
    public ResultBean<Void> delete(@PathVariable Long id) {
        // TODO: 实现删除逻辑
        return ResultBean.success();
    }
    
    @PostMapping("/list")
    @Operation(summary = "列表查询用户")
    public ResultBean<List<UserVO>> list(@RequestBody UserQuery query) {
        // TODO: 实现列表查询逻辑
        return ResultBean.success();
    }
}