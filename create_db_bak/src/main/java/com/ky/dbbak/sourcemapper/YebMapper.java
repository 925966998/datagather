package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface YebMapper extends BaseMapper {

    @SelectProvider(type = YebSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryGL_Yeb(Map pagerParam);
    @SelectProvider(type = YebSql.class, method = "_queryKjnd")
    List<Map<String, Object>> _queryYebKjnd(Map<String, Object> pageData);
    @Select("select * from GL_Yeb where kjnd='2020' and yeblx='B'")
    List<Map<String, Object>> _queryAllYeb(Map<String, Object> pageData);
    @Select("select ncj,ncd from GL_Yeb where kmdm = #{kmdm}")
    List<Map<String, Object>> _queryByKmdm(Map<String, Object> pd);
}
