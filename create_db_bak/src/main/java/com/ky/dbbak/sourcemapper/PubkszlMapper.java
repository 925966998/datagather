package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface PubkszlMapper extends BaseMapper {

    @SelectProvider(type = PubkszlSql.class, method = "_queryKjnd")
    List<Map<String, Object>> _queryPubkszl(Map pagerParam);

    @SelectProvider(type = PubkszlSql.class, method = "_queryYe")
    List<Map<String, Object>> _queryYePubkszl(Map pagerParam);
}
