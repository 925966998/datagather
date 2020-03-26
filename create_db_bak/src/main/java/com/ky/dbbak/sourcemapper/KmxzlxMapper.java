package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface KmxzlxMapper extends BaseMapper {

    @Select("select * from GL_KMXZLX  where gsdm< '99999999999999999999' AND lxdm=#{lxdm} ")
    List<Map<String, Object>> _queryGL_KMXZLX(String lxdm);

    @Select("select * from PUBBMXX  where kjnd = '2019' AND syzt='1' ")
    List<Map<String, Object>> _queryPUBBMXX();

    @Select("select * from GL_XMZL  where kjnd = '2019' AND syzt='1' ")
    List<Map<String, Object>> _queryGL_XMZL();

    @Select("select * from PUBKSZL  where kjnd = '2019' AND syzt='1' ")
    List<Map<String, Object>> _queryPUBKSZL();

    @Select("select * from GL_Fzxzl  where kjnd = '2019' AND syzt='1' ")
    List<Map<String, Object>> _queryGL_Fzxzl();


    @Select("select * from GL_KMXX  where kjnd = '2019' AND syzt='1' ")
    List<Map<String, Object>> _queryKjkmxx();

    @Select("select * from GL_KMXX  where kmdm = #{kmdm} ")
    List<Map<String, Object>> _queryKjtxxx(String kmdm);

    @Select("select * from GL_Yeb where kjnd = '2019'")
    List<Map<String, Object>> _queryGL_Yeb();
}
