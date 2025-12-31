package com.xitian.djrlpwst.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xitian.djrlpwst.bean.base.service.BaseServiceImpl;
import com.xitian.djrlpwst.converter.GuideRouteConverter;
import com.xitian.djrlpwst.domain.dto.GuideRouteWorkflowUpdateDTO;
import com.xitian.djrlpwst.domain.entity.GuideRoute;
import com.xitian.djrlpwst.domain.entity.GuideRoutePoint;
import com.xitian.djrlpwst.domain.entity.GuideRouteEdge;
import com.xitian.djrlpwst.domain.vo.GuideRouteCardVO;
import com.xitian.djrlpwst.domain.vo.GuideRouteDetailVO;
import com.xitian.djrlpwst.mapper.GuideRouteMapper;
import com.xitian.djrlpwst.mapper.GuideRoutePointMapper;
import com.xitian.djrlpwst.mapper.GuideRouteEdgeMapper;
import com.xitian.djrlpwst.service.GuideRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuideRouteServiceImpl extends BaseServiceImpl<GuideRoute> implements GuideRouteService {

    @Autowired
    private GuideRouteMapper guideRouteMapper;

    @Autowired
    private GuideRoutePointMapper guideRoutePointMapper;

    @Autowired
    private GuideRouteEdgeMapper guideRouteEdgeMapper;

    @Autowired
    private GuideRouteConverter guideRouteConverter;

    @Override
    public List<GuideRouteCardVO> getHomeCardList() {
        LambdaQueryWrapper<GuideRoute> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(GuideRoute::getStatus, (byte) 1)
                .eq(GuideRoute::getEditStatus, (byte) 1)
                .orderByDesc(GuideRoute::getCreateTime);
        List<GuideRoute> routes = guideRouteMapper.selectList(wrapper);
        if (routes == null || routes.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> pointCounts = new ArrayList<>(routes.size());
        for (GuideRoute route : routes) {
            long count = guideRoutePointMapper.selectCount(
                    Wrappers.<GuideRoutePoint>lambdaQuery()
                            .eq(GuideRoutePoint::getRouteId, route.getId().intValue())
            );
            pointCounts.add((int) count);
        }
        return guideRouteConverter.toCardVOList(routes, pointCounts);
    }

    @Override
    public GuideRouteDetailVO getRouteDetail(Long routeId) {
        if (routeId == null) {
            return null;
        }
        GuideRoute route = guideRouteMapper.selectById(routeId);
        if (route == null) {
            return null;
        }
        List<GuideRoutePoint> points = guideRoutePointMapper.selectList(
                Wrappers.<GuideRoutePoint>lambdaQuery()
                        .eq(GuideRoutePoint::getRouteId, route.getId().intValue())
                        .orderByAsc(GuideRoutePoint::getStepOrder)
        );
        List<GuideRouteEdge> edges = guideRouteEdgeMapper.selectList(
                Wrappers.<GuideRouteEdge>lambdaQuery()
                        .eq(GuideRouteEdge::getRouteId, route.getId().intValue())
        );
        List<GuideRouteDetailVO.Point> pointVOs = new ArrayList<>();
        for (GuideRoutePoint point : points) {
            GuideRouteDetailVO.Point vo = new GuideRouteDetailVO.Point(
                    point.getId(),
                    point.getRouteId(),
                    point.getStepOrder(),
                    point.getName(),
                    point.getAddress(),
                    point.getLatitude(),
                    point.getLongitude(),
                    point.getCanvasX(),
                    point.getCanvasY(),
                    point.getStayMinutes(),
                    point.getRemark()
            );
            pointVOs.add(vo);
        }
        List<GuideRouteDetailVO.Edge> edgeVOs = new ArrayList<>();
        for (GuideRouteEdge edge : edges) {
            GuideRouteDetailVO.Edge vo = new GuideRouteDetailVO.Edge(
                    edge.getId(),
                    edge.getRouteId(),
                    edge.getSourcePointId(),
                    edge.getTargetPointId(),
                    edge.getLabel()
            );
            edgeVOs.add(vo);
        }
        GuideRouteDetailVO detail = GuideRouteDetailVO.builder()
                .id(route.getId())
                .createTime(route.getCreateTime())
                .updateTime(route.getUpdateTime())
                .name(route.getName())
                .description(route.getDescription())
                .coverImage(route.getCoverImage())
                .totalDistance(route.getTotalDistance())
                .totalDuration(route.getTotalDuration())
                .status(route.getStatus())
                .editStatus(route.getEditStatus())
                .points(pointVOs)
                .edges(edgeVOs)
                .build();
        return detail;
    }

    @Override
    public void updateRouteWorkflow(Long routeId, GuideRouteWorkflowUpdateDTO dto) {
        if (routeId == null || dto == null) {
            return;
        }
        GuideRoute route = guideRouteMapper.selectById(routeId);
        if (route == null) {
            return;
        }
        if (dto.getPoints() != null) {
            for (GuideRouteWorkflowUpdateDTO.PointLayout pointLayout : dto.getPoints()) {
                if (pointLayout == null || pointLayout.getId() == null) {
                    continue;
                }
                GuideRoutePoint point = new GuideRoutePoint();
                point.setId(pointLayout.getId());
                point.setCanvasX(pointLayout.getCanvasX());
                point.setCanvasY(pointLayout.getCanvasY());
                guideRoutePointMapper.updateById(point);
            }
        }
        if (dto.getEdges() != null) {
            guideRouteEdgeMapper.delete(
                    Wrappers.<GuideRouteEdge>lambdaQuery()
                            .eq(GuideRouteEdge::getRouteId, route.getId().intValue())
            );
            for (GuideRouteWorkflowUpdateDTO.Edge edgeDto : dto.getEdges()) {
                if (edgeDto == null
                        || edgeDto.getSourcePointId() == null
                        || edgeDto.getTargetPointId() == null) {
                    continue;
                }
                GuideRouteEdge edge = GuideRouteEdge.builder()
                        .routeId(route.getId().intValue())
                        .sourcePointId(edgeDto.getSourcePointId())
                        .targetPointId(edgeDto.getTargetPointId())
                        .label(edgeDto.getLabel())
                        .build();
                guideRouteEdgeMapper.insert(edge);
            }
        }
    }
}
