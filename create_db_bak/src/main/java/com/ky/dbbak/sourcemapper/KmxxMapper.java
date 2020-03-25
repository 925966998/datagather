package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface KmxxMapper extends BaseMapper {

    @SelectProvider(type = KmxxSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_KMXX(Map pagerParam);
    @Select("select kmmc,yefx from GL_KMXX  where cHARINDEX('2019',kjnd)=1 AND kmdm=#{kmdm} ")
    List<Map<String, Object>> _queryKmdm(String kmdm);
    @Select("select kmmc,yefx from GL_KMXX  where cHARINDEX('2019',kjnd)=1 AND kmdm=#{kmdm} ")
    List<Map<String, Object>> _querykmxx(Map pagerParam);
}
