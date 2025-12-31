package com.xitian.djrlpwst.converter;

import com.xitian.djrlpwst.domain.entity.GuideRoute;
import com.xitian.djrlpwst.domain.vo.GuideRouteCardVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GuideRouteConverter {

    public GuideRouteCardVO toCardVO(GuideRoute route, Integer pointCount) {
        if (route == null) {
            return null;
        }
        GuideRouteCardVO vo = new GuideRouteCardVO();
        vo.setId(route.getId());
        vo.setCreateTime(route.getCreateTime());
        vo.setUpdateTime(route.getUpdateTime());
        vo.setName(route.getName());
        vo.setDescription(route.getDescription());
        vo.setCoverImage(route.getCoverImage());
        vo.setPointCount(pointCount);
        vo.setStatus(route.getStatus());
        vo.setEditStatus(route.getEditStatus());
        return vo;
    }

    public List<GuideRouteCardVO> toCardVOList(List<GuideRoute> routes, List<Integer> pointCounts) {
        if (routes == null) {
            return null;
        }
        List<GuideRouteCardVO> list = new ArrayList<>();
        for (int i = 0; i < routes.size(); i++) {
            GuideRoute route = routes.get(i);
            Integer count = pointCounts != null && i < pointCounts.size() ? pointCounts.get(i) : null;
            list.add(toCardVO(route, count));
        }
        return list;
    }
}

