package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class YebSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.GL_Yeb";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from GL_Yeb");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("select * from GL_Yeb where CHARINDEX('2019',kjnd)=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "kmdm"))) {
            builder.append(" and kmdm=#{kmdm}");
        }
        return builder.toString();
    }

    public String _queryKjnd(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "kjnd"))) {
            builder.append(" and kjnd=#{kjnd}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "kmdm"))) {
            builder.append(" and kmdm=#{kmdm}");
        }
        return builder.toString();
    }

}
