package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface KmxzlxMapper extends BaseMapper {

    @SelectProvider(type = KmxzlxSql.class, method = "_queryGL_KMXZLX")
    List<Map<String, Object>> _queryKMXZLX(Map<String, Object> pageData);


    @Select("select * FROM GL_KMXZLX WHERE gsdm <'99999999999999999999' AND lxdm=#{lxdm};")
    List<Map<String, Object>> _queryKMXZLX(String lxdm1);
}
