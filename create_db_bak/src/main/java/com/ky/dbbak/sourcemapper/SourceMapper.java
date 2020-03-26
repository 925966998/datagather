package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mapper.*;
import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface SourceMapper extends BaseMapper {

    @SelectProvider(type = PznrSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryPznr(Map pagerParam);

    @Select("select * from dbo.GL_Yeb where kjnd='2019'")
    List<Map<String, Object>> _queryGL_Yeb(Map pagerParam);

    @SelectProvider(type = KmxxSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_KMXX(Map pagerParam);

    @SelectProvider(type = KmxxSql.class, method = "_queryGL_KMXX1")
    List<String> _queryGL_KMXX1(Map pagerParam);

    @SelectProvider(type = PubbmxxSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryPubbmxx(Map pagerParam);

    @SelectProvider(type = XmzlSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_Xmzl(Map pagerParam);

    @SelectProvider(type = PubkszlSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryPUBKSZL(Map pagerParam);

    @SelectProvider(type = FzxlbSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_Fzxlb(Map pagerParam);

    @SelectProvider(type = FzxlbSql.class, method = "_queryselect1")
    List<Map<String, Object>> _queryGL_Fzxlb1(Map pagerParam);

    @SelectProvider(type = FzxzlSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_Fzxzl(Map pagerParam);


    @SelectProvider(type = Kmxx1Sql.class, method = "_queryselect")
    List<Map<String, Object>> _query(Map pagerParam);

    @Select("select * FROM GL_KMXZLX WHERE gsdm <'99999999999999999999';")
    List<Map<String, Object>> _queryKmxx();

}
