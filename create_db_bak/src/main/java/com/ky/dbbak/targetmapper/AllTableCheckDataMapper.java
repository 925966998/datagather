package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface AllTableCheckDataMapper extends BaseMapper {
    @Select("select top 1* FROM dbo.FZLX where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkFZLXHasData(String kjdzzbbh);

    @Select("select top 1* FROM dbo.FZNCS where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkFZNCSHasData(String kjdzzbbh);

    @Select("select top 1* FROM dbo.FZXX where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkFZXXHasData(String kjdzzbbh);

    @Select("select top 1* FROM dbo.FZYE where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkFZYEHasData(String kjdzzbbh);

    @Select("select top 1* FROM dbo.JZPZ where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkJZPZHasData(String kjdzzbbh);

    @Select("select top 1* FROM dbo.KJKM where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkKJKMHasData(String kjdzzbbh);

    @Select("select top 1* FROM dbo.KJQJDY where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkKJQJDYHasData(String kjdzzbbh);

    @Select("select top 1* FROM dbo.KMNCS where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkKMNCSHasData(String kjdzzbbh);

    @Select("select top 1* FROM dbo.KMYE where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkKMYEHasData(String kjdzzbbh);

    @Select("select top 1* FROM dbo.PZFZMX where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkPZFZMXHasData(String kjdzzbbh);

}
