package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.RestaurantDish;
import org.springframework.stereotype.Component;

@Component
public class RestaurantDishConverter {

    public RestaurantDish toEntity(RestaurantDish entity) {
        return entity;
    }
}

