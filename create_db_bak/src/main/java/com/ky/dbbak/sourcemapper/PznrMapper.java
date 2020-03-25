package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface PznrMapper extends BaseMapper {

    @SelectProvider(type = PznrSql.class, method = "_queryByPznr")
    List<Map<String, Object>> _queryPznr(Map pagerParam);

    @Select("select * from GL_Pznr where IDPZH = #{idpzh}")
    List<Map<String, Object>> _queryPznrIdPZH(String idpzh);

    @SelectProvider(type = PznrSql.class, method = "_queryAll")
    List<Map<String, Object>> _queryAll(Map pagerParam);

    @SelectProvider(type = PznrSql.class, method = "_queryByPznr")
    List<Map<String, Object>> _queryByPznr(Map pagerParam);
}