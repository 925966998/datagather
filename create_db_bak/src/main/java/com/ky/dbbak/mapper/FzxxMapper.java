package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FzxxMapper extends BaseMapper {

    @InsertProvider(type = FzxxSql.class, method = "_add")
    int _addFzxx(Map params);


}
