package com.xitian.djrlpwst.service.impl;

import com.xitian.djrlpwst.domain.dto.AiKnowledgeCreateDTO;
import com.xitian.djrlpwst.domain.dto.AiKnowledgeUpdateDTO;
import com.xitian.djrlpwst.domain.vo.AiKnowledgeVO;
import com.xitian.djrlpwst.service.AiKnowledgeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class AiKnowledgeServiceImpl implements AiKnowledgeService {

    private final ConcurrentHashMap<Long, AiKnowledgeVO> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public AiKnowledgeVO create(AiKnowledgeCreateDTO dto) {
        Long id = idGenerator.getAndIncrement();
        LocalDateTime now = LocalDateTime.now();
        AiKnowledgeVO vo = new AiKnowledgeVO(id, dto.getTitle(), dto.getContent(), now, now);
        store.put(id, vo);
        return vo;
    }

    @Override
    public AiKnowledgeVO update(Long id, AiKnowledgeUpdateDTO dto) {
        AiKnowledgeVO existing = store.get(id);
        if (existing == null) {
            return null;
        }
        if (StringUtils.hasText(dto.getTitle())) {
            existing.setTitle(dto.getTitle());
        }
        if (StringUtils.hasText(dto.getContent())) {
            existing.setContent(dto.getContent());
        }
        existing.setUpdatedAt(LocalDateTime.now());
        store.put(id, existing);
        return existing;
    }

    @Override
    public boolean delete(Long id) {
        return store.remove(id) != null;
    }

    @Override
    public AiKnowledgeVO getById(Long id) {
        return store.get(id);
    }

    @Override
    public List<AiKnowledgeVO> list(String keyword) {
        return store.values().stream()
            .filter(item -> {
                if (!StringUtils.hasText(keyword)) {
                    return true;
                }
                String k = keyword.trim();
                return (item.getTitle() != null && item.getTitle().contains(k))
                    || (item.getContent() != null && item.getContent().contains(k));
            })
            .sorted(Comparator.comparing(AiKnowledgeVO::getId))
            .collect(Collectors.toList());
    }

    @Override
    public String buildContext() {
        List<AiKnowledgeVO> items = list(null);
        if (items.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (AiKnowledgeVO item : items) {
            sb.append("标题：").append(item.getTitle()).append("\n");
            sb.append("内容：").append(item.getContent()).append("\n");
        }
        return sb.toString();
    }
}
