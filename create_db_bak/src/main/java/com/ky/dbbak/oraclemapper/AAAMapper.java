package com.ky.dbbak.oraclemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AAAMapper extends BaseMapper {

    @Select("select * from a_ksqtxx")
    List<Map<String, Object>>  qq();



}
