package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface FzxlbMapper extends BaseMapper {

    @Select("SELECT lbdm,lbmc,lbfj FROM GL_Fzxlb where kjnd=#{kjnd} AND gsdm=#{gsdm} and  gsdm !='99999999999999999999'  GROUP BY lbdm,lbmc,lbfj")
    List<Map<String, Object>>  _querykmFzxlb(Map pagerParam);

    @Select("SELECT lbdm,lbmc,lbfj FROM GL_Fzxlb where kjnd=#{kjnd} AND gsdm=#{gsdm} AND lbdm=#{lbdm} and  gsdm !='99999999999999999999'  GROUP BY lbdm,lbmc,lbfj")
    List<Map<String, Object>>  _queryFzxlb(Map pagerParam);

}
