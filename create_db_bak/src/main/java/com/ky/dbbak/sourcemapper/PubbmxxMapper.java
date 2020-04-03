package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface PubbmxxMapper extends BaseMapper {

    @SelectProvider(type = PubbmxxSql.class, method = "_queryKjnd")
    List<Map<String, Object>> _queryPubbmxx(Map pagerParam);

    @SelectProvider(type = PubbmxxSql.class, method = "_queryPubbmxx")
    List<Map<String, Object>> _queryyePubbmxx(Map pagerParam);

}
