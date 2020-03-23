package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface KjqjdyMapper extends BaseMapper {

    @InsertProvider(type = KjqjdySql.class, method = "_add")
    int _addKjqjdy(Map<String, Object> dataPull);

}
