package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FzyeMapper extends BaseMapper {

    @InsertProvider(type = FzyeSql.class, method = "_addBatch")
    int _addFzye(Map params);

}
