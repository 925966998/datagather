package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.entity.ZtcsEntity;
import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZtcsMapper extends BaseMapper {

    @SelectProvider(type = ZtcsSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryZtcs();

    @SelectProvider(type = ZtcsSql.class, method = "_queryKjnd")
    List<Map<String, Object>> _queryZtcszh(Map pagerParam);
    @SelectProvider(type = ZtcsSql.class, method = "queryZtcs")
    List<Map<String, Object>> _queryDwZtcs(Map<String, Object> pageData);
    @Select("SELECT * FROM GL_Ztcs WHERE hsdwdm = #{hsdwdm} AND kjnd ='2019' AND ztbh <> '99999999999999999999' ")
    List<ZtcsEntity> queryByZTH(String hsdwdm);
}
