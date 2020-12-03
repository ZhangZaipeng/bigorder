package com.petroleum.app.mapper;

import com.petroleum.app.config.mybatis.DefaultMapper;
import com.petroleum.app.domain.SystemConfig;

public interface SystemConfigMapper extends DefaultMapper {
    SystemConfig selectByPrimaryKey(String key);
}
