package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class PznrSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.GL_Pznr";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{};
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select\n" +
                "        *\n" +
                "        from GL_Pznr where CHARINDEX('2019',kjqj)=1 ");
        return builder.toString();
    }
    //KJTXDM
    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("select * from GL_Pznr where CHARINDEX('2019',kjqj)=1 ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "kmdm"))) {
            builder.append(" and kmdm=#{kmdm}");
        }
        return builder.toString();
    }

    @Override
    public String _queryAll(Map map) {
        StringBuilder builder = new StringBuilder("select DISTINCT glp.*,glk.kmmc as kmmc from GL_Pznr glp left join GL_KMXX glk on glp.kmdm = glk.kmdm  where CHARINDEX('2019',kjqj)=1 ");
        return builder.toString();
    }

    public String _queryByPznr(Map map) {
        StringBuilder builder = new StringBuilder("select DISTINCT kmdm from GL_Pznr where CHARINDEX('2019',kjqj)=1 and IDPZH = #{IDPZH} and jdbz = #{jdbz} and KJTXDM = #{KJTXDM}");
        return builder.toString();
    }

    public String _querySmallJe(Map map) {
        StringBuilder builder = new StringBuilder("select DISTINCT kmdm from GL_Pznr where CHARINDEX('2019',kjqj)=1 and IDPZH = #{IDPZH} and jdbz = #{jdbz} and je<0");
        return builder.toString();
    }
}
