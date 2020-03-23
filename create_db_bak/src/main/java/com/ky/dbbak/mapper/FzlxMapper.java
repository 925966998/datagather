package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface FzlxMapper extends BaseMapper {

    @InsertProvider(type = FzlxSql.class, method = "_add")
    int _addFzlx(Map params);


}
