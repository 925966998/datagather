package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface XmzlMapper extends BaseMapper {

    @SelectProvider(type = XmzlSql.class, method = "_queryKjnd")
    List<Map<String, Object>> _queryXmzl(Map pagerParam);

    @SelectProvider(type = XmzlSql.class, method = "_queryyeXm")
    List<Map<String, Object>> _queryYeXmzl(Map pagerParam);
}
