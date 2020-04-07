package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.entity.HsdwEntity;
import com.ky.dbbak.entity.ZtcsEntity;
import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface HsdwMapper extends BaseMapper {

    @SelectProvider(type = HsdwSql.class, method = "_queryselect")
    List<HsdwEntity> _queryHsdw(Map params);
}
