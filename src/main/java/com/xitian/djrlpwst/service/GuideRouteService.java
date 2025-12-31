package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.bean.base.service.BaseService;
import com.xitian.djrlpwst.domain.entity.GuideRoute;
import com.xitian.djrlpwst.domain.dto.GuideRouteWorkflowUpdateDTO;
import com.xitian.djrlpwst.domain.vo.GuideRouteCardVO;
import com.xitian.djrlpwst.domain.vo.GuideRouteDetailVO;

import java.util.List;

public interface GuideRouteService extends BaseService<GuideRoute> {

    List<GuideRouteCardVO> getHomeCardList();

    GuideRouteDetailVO getRouteDetail(Long routeId);

    void updateRouteWorkflow(Long routeId, GuideRouteWorkflowUpdateDTO dto);
}
