package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface PubkjqjMapper extends BaseMapper {

    @SelectProvider(type = PubkjqjSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryPubKjqj(Map<String, Object> pageData);

}
