package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mapper.YsdwSql;
import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YsdwMapper extends BaseMapper {


    @DeleteProvider(type = YsdwSql.class, method = "deleteYsdw")
    void deleteYsdw(String dwdm, String dwmc, String xzqhdm);
}
