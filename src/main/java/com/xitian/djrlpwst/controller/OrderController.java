package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.bean.StatusCode;
import com.xitian.djrlpwst.bean.base.BaseController;
import com.xitian.djrlpwst.converter.OrderConverter;
import com.xitian.djrlpwst.domain.dto.OrderCreateDTO;
import com.xitian.djrlpwst.domain.dto.OrderUpdateDTO;
import com.xitian.djrlpwst.domain.entity.Order;
import com.xitian.djrlpwst.domain.query.OrderQuery;
import com.xitian.djrlpwst.domain.vo.OrderVO;
import com.xitian.djrlpwst.domain.vo.OrderAdminVO;
import com.xitian.djrlpwst.service.OrderService;
import com.xitian.djrlpwst.util.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Tag(name = "订单管理", description = "订单管理接口")
public class OrderController extends BaseController<Order> {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderConverter orderConverter;
    
    @PostMapping("/page")
    @Operation(summary = "分页查询订单")
    public ResultBean<PageBean<OrderVO>> page(@RequestBody PageParam<OrderQuery> param) {
        PageBean<OrderVO> page = orderService.getPage(param);
        return ResultBean.success(page);
    }
    
    @PostMapping("/admin/page")
    @Operation(summary = "管理员端分页查询订单详情")
    public ResultBean<PageBean<OrderAdminVO>> adminPage(@RequestBody PageParam<OrderQuery> param) {
        PageBean<OrderAdminVO> page = orderService.getAdminPage(param);
        return ResultBean.success(page);
    }
    
    @PostMapping("/expired-paid")
    @Operation(summary = "分页查询已支付但已过期的订单")
    public ResultBean<PageBean<OrderVO>> expiredPaidOrders(@RequestBody PageParam<OrderQuery> param) {
        PageBean<OrderVO> page = orderService.getExpiredPaidOrders(param);
        return ResultBean.success(page);
    }
    
    @PostMapping("/pending-valid")
    @Operation(summary = "分页查询待使用且未过期的订单")
    public ResultBean<PageBean<OrderVO>> pendingValidOrders(@RequestBody PageParam<OrderQuery> param) {
        PageBean<OrderVO> page = orderService.getPendingValidOrders(param);
        return ResultBean.success(page);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询订单")
    public ResultBean<OrderVO> getById(@PathVariable Long id) {
        Order order = orderService.getById(id);
        if (order == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "订单不存在");
        }
        OrderVO vo = orderConverter.toVO(order);
        return ResultBean.success(vo);
    }
    
    @PostMapping
    @Operation(summary = "新增订单")
    public ResultBean<Void> add(@Valid @RequestBody OrderCreateDTO createDTO) {
        Order entity = new Order();
        entity.setOrderNo(orderService.generateOrderNo());
        entity.setUserId(createDTO.getUserId());
        entity.setProductType(createDTO.getProductType());
        entity.setProductId(createDTO.getProductId());
        entity.setProductName(createDTO.getProductName());
        entity.setDescription(createDTO.getDescription());
        entity.setQuantity(createDTO.getQuantity());
        entity.setUnitPrice(createDTO.getUnitPrice());
        entity.setTotalAmount(createDTO.getTotalAmount());
        entity.setStatus(0); // 默认状态为待支付
        
        orderService.save(entity);
        return ResultBean.success();
    }
    
    @PutMapping
    @Operation(summary = "修改订单")
    public ResultBean<Void> update(@Valid @RequestBody OrderUpdateDTO updateDTO) {
        // 获取原始订单信息
        Order existingOrder = orderService.getById(updateDTO.getId());
        if (existingOrder == null) {
            return ResultBean.fail(StatusCode.DATA_NOT_FOUND, "订单不存在");
        }
        
        // 只复制非空属性到现有实体
        BeanUtil.copyNonNullProperties(updateDTO, existingOrder);
        
        // 更新订单信息
        orderService.updateById(existingOrder);
        return ResultBean.success();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除订单")
    public ResultBean<Void> delete(@PathVariable Long id) {
        orderService.removeById(id);
        return ResultBean.success();
    }
}