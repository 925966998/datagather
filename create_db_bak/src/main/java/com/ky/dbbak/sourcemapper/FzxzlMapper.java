package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface FzxzlMapper extends BaseMapper {

    @SelectProvider(type = FzxzlSql.class, method = "_queryKjnd")
    List<Map<String, Object>> _queryFzxzl(Map pagerParam);

    @SelectProvider(type = FzxzlSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_Fzxzl(Map pagerParam);

    @SelectProvider(type = FzxzlSql.class, method = "_queryYeKjnd")
    List<Map<String, Object>> _queryYeFzxzl(Map pagerParam);

}
