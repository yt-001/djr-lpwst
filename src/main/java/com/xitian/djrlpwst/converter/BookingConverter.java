package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.Booking;
import com.xitian.djrlpwst.domain.vo.BookingVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingConverter {
    
    public BookingVO toVO(Booking entity) {
        if (entity == null) {
            return null;
        }
        
        BookingVO vo = new BookingVO();
        vo.setId(entity.getId());
        vo.setCreateTime(entity.getCreateTime());
        vo.setUpdateTime(entity.getUpdateTime());
        vo.setUserId(entity.getUserId());
        vo.setAccommodationId(entity.getAccommodationId());
        vo.setAttractionId(entity.getAttractionId());
        vo.setCheckInDate(entity.getCheckInDate());
        vo.setCheckOutDate(entity.getCheckOutDate());
        vo.setGuestCount(entity.getGuestCount());
        vo.setTotalPrice(entity.getTotalPrice());
        vo.setStatus(entity.getStatus());
        vo.setSpecialRequests(entity.getSpecialRequests());
        
        return vo;
    }
    
    public List<BookingVO> toVOList(List<Booking> entityList) {
        if (entityList == null) {
            return null;
        }
        
        List<BookingVO> voList = new ArrayList<>();
        for (Booking entity : entityList) {
            voList.add(toVO(entity));
        }
        
        return voList;
    }
}