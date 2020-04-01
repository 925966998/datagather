package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.Map;

@Mapper
public interface AllTableCheckDataMapper extends BaseMapper {

    @Select("select top 1* FROM dbo.FZLX where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkFZLXHasData(String kjdzzbbh);

    @UpdateProvider(type = AllTableCheckDataSql.class, method = "updateFZLXkjdzzbbh")
    int updateFZLXkjdzzbbh(Map map);

    @Select("select top 1* FROM dbo.FZNCS where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkFZNCSHasData(String kjdzzbbh);

    @UpdateProvider(type = AllTableCheckDataSql.class, method = "updateFZNCSkjdzzbbh")
    int updateFZNCSkjdzzbbh(Map map);

    @Select("select top 1* FROM dbo.FZXX where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkFZXXHasData(String kjdzzbbh);

    @UpdateProvider(type = AllTableCheckDataSql.class, method = "updateFZXXkjdzzbbh")
    int updateFZXXkjdzzbbh(Map map);

    @Select("select top 1* FROM dbo.FZYE where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkFZYEHasData(String kjdzzbbh);

    @UpdateProvider(type = AllTableCheckDataSql.class, method = "updateFZYEkjdzzbbh")
    int updateFZYEkjdzzbbh(Map map);

    @Select("select top 1* FROM dbo.JZPZ where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkJZPZHasData(String kjdzzbbh);

    @UpdateProvider(type = AllTableCheckDataSql.class, method = "updateJZPZkjdzzbbh")
    int updateJZPZkjdzzbbh(Map map);

    @Select("select top 1* FROM dbo.KJKM where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkKJKMHasData(String kjdzzbbh);

    @UpdateProvider(type = AllTableCheckDataSql.class, method = "updateKJKMkjdzzbbh")
    int updateKJKMkjdzzbbh(Map map);

    @Select("select top 1* FROM dbo.KJQJDY where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkKJQJDYHasData(String kjdzzbbh);

    @UpdateProvider(type = AllTableCheckDataSql.class, method = "updateKJQJDYkjdzzbbh")
    int updateKJQJDYkjdzzbbh(Map map);

    @Select("select top 1* FROM dbo.KMNCS where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkKMNCSHasData(String kjdzzbbh);

    @UpdateProvider(type = AllTableCheckDataSql.class, method = "updateKMNCSkjdzzbbh")
    int updateKMNCSkjdzzbbh(Map map);

    @Select("select top 1* FROM dbo.KMYE where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkKMYEHasData(String kjdzzbbh);

    @UpdateProvider(type = AllTableCheckDataSql.class, method = "updateKMYEkjdzzbbh")
    int updateKMYEkjdzzbbh(Map map);

    @Select("select top 1* FROM dbo.PZFZMX where KJDZZBBH = #{kjdzzbbh}")
    java.util.Map checkPZFZMXHasData(String kjdzzbbh);

    @UpdateProvider(type = AllTableCheckDataSql.class, method = "updatePZFZMXkjdzzbbh")
    int updatePZFZMXkjdzzbbh(Map map);


}
