package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface KmxxMapper extends BaseMapper {

    @SelectProvider(type = KmxxSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_KMXX(Map pagerParam);
}
