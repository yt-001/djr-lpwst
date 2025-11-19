package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.Order;
import com.xitian.djrlpwst.domain.vo.OrderAdminVO;
import com.xitian.djrlpwst.domain.vo.OrderVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {
    
    public OrderVO toVO(Order entity) {
        if (entity == null) {
            return null;
        }
        
        OrderVO vo = new OrderVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setOrderNo(entity.getOrderNo());
        vo.setUserId(entity.getUserId());
        vo.setProductType(entity.getProductType());
        vo.setProductId(entity.getProductId());
        vo.setProductName(entity.getProductName());
        vo.setDescription(entity.getDescription());
        vo.setQuantity(entity.getQuantity());
        vo.setUnitPrice(entity.getUnitPrice());
        vo.setTotalAmount(entity.getTotalAmount());
        vo.setStatus(entity.getStatus());
        vo.setPaymentTime(entity.getPaymentTime());
        vo.setUsedTime(entity.getUsedTime());
        vo.setExpireTime(entity.getExpireTime());
        
        return vo;
    }
    
    public OrderAdminVO toAdminVO(Order entity) {
        if (entity == null) {
            return null;
        }
        
        OrderAdminVO vo = new OrderAdminVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setOrderNo(entity.getOrderNo());
        vo.setUserId(entity.getUserId());
        vo.setProductType(entity.getProductType());
        vo.setProductName(entity.getProductName());
        vo.setDescription(entity.getDescription());
        vo.setQuantity(entity.getQuantity());
        vo.setUnitPrice(entity.getUnitPrice());
        vo.setTotalAmount(entity.getTotalAmount());
        vo.setStatus(entity.getStatus());
        vo.setPaymentTime(entity.getPaymentTime());
        vo.setUsedTime(entity.getUsedTime());
        vo.setExpireTime(entity.getExpireTime());
        
        return vo;
    }
    
    public List<OrderVO> toVOList(List<Order> entityList) {
        if (entityList == null) {
            return null;
        }
        
        List<OrderVO> voList = new ArrayList<>();
        for (Order entity : entityList) {
            voList.add(toVO(entity));
        }
        
        return voList;
    }
    
    public List<OrderAdminVO> toAdminVOList(List<Order> entityList) {
        if (entityList == null) {
            return null;
        }
        
        List<OrderAdminVO> voList = new ArrayList<>();
        for (Order entity : entityList) {
            voList.add(toAdminVO(entity));
        }
        
        return voList;
    }
}