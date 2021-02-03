package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface PznrMapper extends BaseMapper {

    @SelectProvider(type = PznrSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryPznr(Map pagerParam);

    @Select("select * from GL_Pznr where IDPZH = #{idpzh}")
    List<Map<String, Object>> _queryPznrIdPZH(String idpzh);

    @SelectProvider(type = PznrSql.class, method = "_queryAll")
    List<Map<String, Object>> _queryAll(Map pagerParam);

    @SelectProvider(type = PznrSql.class, method = "_queryByPznr")
    List<Map<String, Object>> _queryByPznr(Map pagerParam);

    @Select("select KJTXDM from GL_Pznr where CHARINDEX('2020',kjqj)=1 and kmdm = #{kmdm}")
    List<Map<String, Object>> _queryByPznr1(Map<String, Object> pznrMap);

    @SelectProvider(type = PznrSql.class, method = "_querySmallJe")
    List<Map<String, Object>> _querySmallJe(Map<Object, Object> dmap);

    @Select("SELECT COUNT(*) as num FROM GL_Pznr WHERE kjqj = #{kjqj} AND kmdm =#{kmdm} ")
    long _queryByPznrCount(Map pagerParam);

    @SelectProvider(type = PznrSql.class, method = "_queryselectPzmx")
    List<Map<String, Object>> _queryPznr_G(Map pagerParam);
    @Select("SELECT COUNT(*) as num FROM GL_Pznr WHERE kjqj = #{kjqj} AND kmdm =#{kmdm} and gsdm = #{gsdm} and ZTH = #{ZTH} ")
    long _queryByGPznrCount(Map pagerParam);
}
