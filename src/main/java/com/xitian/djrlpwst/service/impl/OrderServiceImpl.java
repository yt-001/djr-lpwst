package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xitian.djrlpwst.bean.PageBean;
import com.xitian.djrlpwst.bean.PageParam;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.converter.OrderConverter;
import com.xitian.djrlpwst.domain.entity.Order;
import com.xitian.djrlpwst.domain.query.OrderQuery;
import com.xitian.djrlpwst.domain.vo.OrderAdminVO;
import com.xitian.djrlpwst.domain.vo.OrderVO;
import com.xitian.djrlpwst.mapper.OrderMapper;
import com.xitian.djrlpwst.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private OrderConverter orderConverter;
    
    // 订单编号生成器
    private final AtomicLong orderNoGenerator = new AtomicLong(System.currentTimeMillis());
    
    @Override
    public String generateOrderNo() {
        // 生成订单编号：当前日期时间 + 自增序列
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String sequence = String.format("%06d", orderNoGenerator.getAndIncrement() % 1000000);
        return "ORD" + dateTime + sequence;
    }

    @Override
    public PageBean<OrderVO> getPage(PageParam<OrderQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        OrderQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Order> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery();
        
        // 构建查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getOrderNo())) {
                wrapper.like(Order::getOrderNo, query.getOrderNo());
            }
            
            if (query.getUserId() != null) {
                wrapper.eq(Order::getUserId, query.getUserId());
            }
            
            if (query.getProductType() != null) {
                wrapper.eq(Order::getProductType, query.getProductType());
            }
            
            // 处理多个状态查询
            if (query.getStatusList() != null && !query.getStatusList().isEmpty()) {
                wrapper.in(Order::getStatus, query.getStatusList());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 支持多种排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "orderNo":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getOrderNo);
                    } else {
                        wrapper.orderByAsc(Order::getOrderNo);
                    }
                    break;
                case "createTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getCreateTime);
                    } else {
                        wrapper.orderByAsc(Order::getCreateTime);
                    }
                    break;
                case "totalAmount":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getTotalAmount);
                    } else {
                        wrapper.orderByAsc(Order::getTotalAmount);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        } else {
            // 默认按创建时间倒序排列（最新的订单在前）
            wrapper.orderByDesc(Order::getCreateTime);
        }
        
        // 执行分页查询
        Page<Order> result = orderMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<OrderVO> voList = orderConverter.toVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<OrderVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }

    @Override
    public PageBean<OrderAdminVO> getAdminPage(PageParam<OrderQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        OrderQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Order> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery();
        
        // 构建查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getOrderNo())) {
                wrapper.like(Order::getOrderNo, query.getOrderNo());
            }
            
            if (query.getUserId() != null) {
                wrapper.eq(Order::getUserId, query.getUserId());
            }
            
            if (query.getProductType() != null) {
                wrapper.eq(Order::getProductType, query.getProductType());
            }
            
            // 处理多个状态查询
            if (query.getStatusList() != null && !query.getStatusList().isEmpty()) {
                wrapper.in(Order::getStatus, query.getStatusList());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 支持多种排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "orderNo":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getOrderNo);
                    } else {
                        wrapper.orderByAsc(Order::getOrderNo);
                    }
                    break;
                case "createTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getCreateTime);
                    } else {
                        wrapper.orderByAsc(Order::getCreateTime);
                    }
                    break;
                case "totalAmount":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getTotalAmount);
                    } else {
                        wrapper.orderByAsc(Order::getTotalAmount);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        } else {
            // 默认按创建时间倒序排列（最新的订单在前）
            wrapper.orderByDesc(Order::getCreateTime);
        }
        
        // 执行分页查询
        Page<Order> result = orderMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<OrderAdminVO> voList = orderConverter.toAdminVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<OrderAdminVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
    
    @Override
    public PageBean<OrderVO> getExpiredPaidOrders(PageParam<OrderQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        OrderQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Order> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery();
        
        // 设置查询条件：状态为1(已支付)且过期时间早于当前时间
        wrapper.eq(Order::getStatus, 1);
        wrapper.lt(Order::getExpireTime, LocalDateTime.now());
        
        // 构建其他查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getOrderNo())) {
                wrapper.like(Order::getOrderNo, query.getOrderNo());
            }
            
            if (query.getUserId() != null) {
                wrapper.eq(Order::getUserId, query.getUserId());
            }
            
            if (query.getProductType() != null) {
                wrapper.eq(Order::getProductType, query.getProductType());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 支持多种排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "orderNo":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getOrderNo);
                    } else {
                        wrapper.orderByAsc(Order::getOrderNo);
                    }
                    break;
                case "createTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getCreateTime);
                    } else {
                        wrapper.orderByAsc(Order::getCreateTime);
                    }
                    break;
                case "totalAmount":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getTotalAmount);
                    } else {
                        wrapper.orderByAsc(Order::getTotalAmount);
                    }
                    break;
                case "expireTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getExpireTime);
                    } else {
                        wrapper.orderByAsc(Order::getExpireTime);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        } else {
            // 默认按过期时间倒序排列
            wrapper.orderByDesc(Order::getExpireTime);
        }
        
        // 执行分页查询
        Page<Order> result = orderMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<OrderVO> voList = orderConverter.toVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<OrderVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
    
    @Override
    public PageBean<OrderVO> getPendingValidOrders(PageParam<OrderQuery> param) {
        // 获取分页参数
        int pageNum = param.getPageNum();
        int pageSize = param.getPageSize();
        OrderQuery query = param.getQuery();
        
        // 创建分页对象和查询条件构造器
        Page<Order> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Order> wrapper = Wrappers.lambdaQuery();
        
        // 设置查询条件：状态为1(已支付)且过期时间晚于当前时间
        wrapper.eq(Order::getStatus, 1);
        wrapper.gt(Order::getExpireTime, LocalDateTime.now());
        
        // 构建其他查询条件
        if (query != null) {
            if (StringUtils.hasText(query.getOrderNo())) {
                wrapper.like(Order::getOrderNo, query.getOrderNo());
            }
            
            if (query.getUserId() != null) {
                wrapper.eq(Order::getUserId, query.getUserId());
            }
            
            if (query.getProductType() != null) {
                wrapper.eq(Order::getProductType, query.getProductType());
            }
        }
        
        // 根据前端传递的排序字段和排序方向进行排序
        String sortField = param.getSortField();
        Sort.Direction sortDirection = param.getSortDirection();
        
        // 支持多种排序字段
        if (StringUtils.hasText(sortField)) {
            switch (sortField) {
                case "orderNo":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getOrderNo);
                    } else {
                        wrapper.orderByAsc(Order::getOrderNo);
                    }
                    break;
                case "createTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getCreateTime);
                    } else {
                        wrapper.orderByAsc(Order::getCreateTime);
                    }
                    break;
                case "totalAmount":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getTotalAmount);
                    } else {
                        wrapper.orderByAsc(Order::getTotalAmount);
                    }
                    break;
                case "expireTime":
                    if (sortDirection == Sort.Direction.DESC) {
                        wrapper.orderByDesc(Order::getExpireTime);
                    } else {
                        wrapper.orderByAsc(Order::getExpireTime);
                    }
                    break;
                default:
                    // 不支持的排序字段，不添加排序条件，使用数据库默认顺序
                    break;
            }
        } else {
            // 默认按过期时间升序排列
            wrapper.orderByAsc(Order::getExpireTime);
        }
        
        // 执行分页查询
        Page<Order> result = orderMapper.selectPage(page, wrapper);
        
        // 转换结果为VO对象
        List<OrderVO> voList = orderConverter.toVOList(result.getRecords());
        
        // 封装分页结果
        PageBean<OrderVO> pageBean = new PageBean<>();
        pageBean.setTotal(result.getTotal());
        pageBean.setList(voList);
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        
        return pageBean;
    }
    
    @Override
    public boolean updateOrderStatus(Long orderId, Integer newStatus, LocalDateTime usedTime) {
        // 获取原始订单信息
        Order existingOrder = this.getById(orderId);
        if (existingOrder == null) {
            return false;
        }
        
        // 检查订单状态是否发生变化
        Integer oldStatus = existingOrder.getStatus();
        
        // 如果状态发生了变化，需要更新相应的时间字段
        if (newStatus != null && !newStatus.equals(oldStatus)) {
            // 根据新状态设置相应的时间字段
            switch (newStatus) {
                case 1: // 已支付
                    // 如果之前不是已支付状态，则更新支付时间
                    if (oldStatus != 1) {
                        existingOrder.setPaymentTime(LocalDateTime.now());
                        // 设置过期时间为180天后
                        existingOrder.setExpireTime(LocalDateTime.now().plusDays(180));
                    }
                    break;
                case 2: // 已使用
                    // 更新使用时间
                    existingOrder.setUsedTime(LocalDateTime.now());
                    break;
                case 3: // 已取消
                case 4: // 已退款
                    // 清除支付时间和过期时间
                    existingOrder.setPaymentTime(null);
                    existingOrder.setExpireTime(null);
                    break;
                default:
                    // 对于其他状态（如待支付0），清除支付相关时间
                    if (oldStatus == 1 || oldStatus == 2) {
                        existingOrder.setPaymentTime(null);
                        existingOrder.setUsedTime(null);
                        existingOrder.setExpireTime(null);
                    }
                    break;
            }
        } else if (newStatus == null && usedTime != null) {
            // 如果没有更新状态，但是更新了使用时间，也要确保状态是已使用
            if (oldStatus == null || oldStatus != 2) {
                existingOrder.setStatus(2); // 设置为已使用状态
                existingOrder.setUsedTime(usedTime);
            }
        }
        
        // 更新状态
        if (newStatus != null) {
            existingOrder.setStatus(newStatus);
        }
        
        // 更新使用时间
        if (usedTime != null) {
            existingOrder.setUsedTime(usedTime);
        }
        
        // 更新订单信息
        return this.updateById(existingOrder);
    }
}