package com.xitian.djrlpwst.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xitian.djrlpwst.domain.entity.AiGreeting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AiGreetingMapper extends BaseMapper<AiGreeting> {

    @Select("SELECT * FROM ai_greetings WHERE is_enabled = 1 ORDER BY RAND() LIMIT 1")
    AiGreeting selectRandomEnabled();
}
