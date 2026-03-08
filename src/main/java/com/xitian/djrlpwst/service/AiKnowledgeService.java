package com.xitian.djrlpwst.service;

import com.xitian.djrlpwst.domain.dto.AiKnowledgeCreateDTO;
import com.xitian.djrlpwst.domain.dto.AiKnowledgeUpdateDTO;
import com.xitian.djrlpwst.domain.vo.AiKnowledgeVO;

import java.util.List;

public interface AiKnowledgeService {
    AiKnowledgeVO create(AiKnowledgeCreateDTO dto);
    AiKnowledgeVO update(Long id, AiKnowledgeUpdateDTO dto);
    boolean delete(Long id);
    AiKnowledgeVO getById(Long id);
    List<AiKnowledgeVO> list(String keyword);
    String buildContext();
}
