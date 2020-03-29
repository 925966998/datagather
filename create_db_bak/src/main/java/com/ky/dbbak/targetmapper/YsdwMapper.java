package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YsdwMapper extends BaseMapper {


    @Delete("delete from dbo.YSDW where DWDM=#{dwdm} and DWMC=#{dwmc} and XZQHDM=#{xzqhdm}")
    void deleteYsdw(String dwdm, String dwmc, String xzqhdm);
}
