package com.ky.dbbak.targetmapper;

import com.ky.dbbak.entity.AreaEntity;
import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface YsdwMapper extends BaseMapper {


    @Select("delete from dbo.YSDW where DWDM=#{dwdm} and DWMC=#{dwmc} and XZQHDM=#{xzqhdm}")
    AreaEntity deleteYsdw(String dwdm, String dwmc, String xzqhdm);
}
