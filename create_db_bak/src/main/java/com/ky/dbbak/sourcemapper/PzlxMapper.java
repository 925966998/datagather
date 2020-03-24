package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface PzlxMapper extends BaseMapper {

    @SelectProvider(type = PzlxSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryPzlx(Map pagerParam);

}
