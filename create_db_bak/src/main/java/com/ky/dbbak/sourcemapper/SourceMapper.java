package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mapper.*;
import com.ky.dbbak.mybatis.BaseMapper;
import com.ky.dbbak.targetmapper.DzzbxxSql;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface SourceMapper extends BaseMapper {

    @SelectProvider(type = PznrSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryPznr(Map pagerParam);

    @SelectProvider(type = YebSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_Yeb(Map pagerParam);

    @SelectProvider(type = KmxxSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_KMXX(Map pagerParam);

    @SelectProvider(type = PubbmxxSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryPubbmxx(Map pagerParam);

    @SelectProvider(type = XmzlSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_Xmzl(Map pagerParam);

    @SelectProvider(type = PubkszlSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryPUBKSZL(Map pagerParam);

    @SelectProvider(type = FzxlbSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_Fzxlb(Map pagerParam);

    @SelectProvider(type = FzxzlSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_Fzxzl(Map pagerParam);

}
