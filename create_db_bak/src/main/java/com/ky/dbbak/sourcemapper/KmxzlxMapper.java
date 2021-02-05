package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface KmxzlxMapper extends BaseMapper {

    @Select("select * from GL_KMXZLX  where gsdm <> '99999999999999999999' AND lxdm=#{lxdm}  and gsdm = #{gsdm} and zth=#{zth}")
    List<Map<String, Object>> _queryGL_KMXZLX(Map pagerParam);

    @Select("select * from GL_KMXZLX  where gsdm <> '99999999999999999999' AND lxdm=#{lxdm}  ")
    List<Map<String, Object>> _queryKMXZLX(String lxdm);

    @Select("select * from PUBBMXX  where kjnd = '2020' ")
    List<Map<String, Object>> _queryPUBBMXX();

    @Select("select * from GL_XMZL  where kjnd = '2020'  ")
    List<Map<String, Object>> _queryGL_XMZL();

    @Select("select * from PUBKSZL  where kjnd = '2020' ")
    List<Map<String, Object>> _queryPUBKSZL();

    @Select("select * from GL_Fzxzl  where CHARINDEX('2020',KJND)=1  AND lbdm = #{lbdm}")
    List<Map<String, Object>> _queryGL_Fzxzl(Map pagerParam);


    @Select("select * from GL_KMXX  where kjnd = '2020' ")
    List<Map<String, Object>> _queryKjkmxx();

    @Select("select * from GL_KMXX  where kmdm = #{kmdm} ")
    List<Map<String, Object>> _queryKjtxxx(String kmdm);

    @Select("select * from GL_Yeb where kjnd = '2020' and yeblx='B'")
    List<Map<String, Object>> _queryGL_Yeb();


    @Select("select * from PUBBMXX where kjnd = '2020' and  bmdm = #{bmdm} ")
    List<Map<String, Object>> _queryPUBBMXXq(String bmdm);

    @Select("select * from PUBKSZL where kjnd = '2020' and  dwdm = #{dwdm} ")
    List<Map<String, Object>> _queryPUBKSZLq(String dwdm);

    @Select("select * from GL_XMZL where kjnd = '2020' and  XMDM = #{XMDM} ")
    List<Map<String, Object>> _queryXMZLq(String XMDM);

    @Select("select * from GL_Fzxzl where kjnd = '2020' and  fzdm = #{fzdm} ")
    List<Map<String, Object>> _queryFzxzlq(String fzdm);

    @Select("SELECT COUNT(*) as num FROM GL_Pznr WHERE kjqj = #{kjqj} AND kmdm =#{kmdm}  ")
    long _queryPznrCount(Map pagerParam);
    /*
    @Select("select * from PUBBMXX where CHARINDEX('2020',kjnd)=1 and syzt = 1")
    List<Map<String, Object>> _queryPUBBMXXq();

    @Select("select * from GL_XMZL where CHARINDEX('2020',KJND)=1 and SYZT = 1")
    List<Map<String, Object>> _queryXMZLq();
    */


    @Select("select * from GL_Fzxlb  where CHARINDEX('2020',KJND)=1  AND lbdm = #{lbdm}")
    List<Map<String, Object>> _queryfzhsx(String lbdm);

    @Select("select * from GL_Yeb where kjnd = #{kjnd} and gsdm=#{gsdm} and ZTH = #{ZTH}")
    List<Map<String, Object>> _queryYebList(Map pagerParam);

    @Select("SELECT COUNT(*) as num FROM GL_Pznr WHERE kjqj = #{kjqj} AND kmdm =#{kmdm} and gsdm =#{gsdm} and ZTH= #{ZTH} ")
    long _queryCount(Map pagerParam);
}
