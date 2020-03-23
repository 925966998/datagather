package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface KjkmMapper extends BaseMapper {

    @InsertProvider(type = KjkmSql.class, method = "_add")
    int _addKjkm(Map params);

    @SelectProvider(type = Kmxx1Sql.class, method = "_queryselect")
    List<Map<String, Object>> _query(Map pagerParam);

    @Select("select * FROM GL_KMXZLX WHERE gsdm <'99999999999999999999';")
    List<Map<String, Object>> _queryKmxx();

}
