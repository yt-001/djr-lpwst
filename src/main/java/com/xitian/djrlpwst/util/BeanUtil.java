package com.xitian.djrlpwst.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * Bean工具类
 * 提供Bean操作相关的通用方法
 */
public class BeanUtil {

    /**
     * 复制非空属性到目标对象
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyNonNullProperties(Object source, Object target) {
        BeanWrapper sourceWrapper = new BeanWrapperImpl(source);
        
        Set<String> emptyNames = new HashSet<>();
        PropertyDescriptor[] propertyDescriptors = sourceWrapper.getPropertyDescriptors();
        
        for (PropertyDescriptor pd : propertyDescriptors) {
            Object srcValue = sourceWrapper.getPropertyValue(pd.getName());
            if (srcValue == null || 
                (srcValue instanceof String && !StringUtils.hasText((String) srcValue))) {
                emptyNames.add(pd.getName());
            }
        }
        
        // 复制非空属性
        BeanUtils.copyProperties(source, target, emptyNames.toArray(new String[0]));
    }
}