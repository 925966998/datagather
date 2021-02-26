package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class XmzlSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.GL_XMZL";
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
        StringBuilder builder = new StringBuilder("select * from GL_XMZL");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT XMDM,XMMC FROM GL_Xmzl where XMDM=#{XMDM}");
        return builder.toString();
    }

    public String _queryKjnd(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJND"))) {
            builder.append(" and kjnd=#{KJND}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "GSDM"))) {
            builder.append(" and gsdm=#{GSDM}");
        }
        return builder.toString();
    }

    public String _queryyeXm(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "XMDM"))) {
            builder.append(" and xmdm=#{XMDM}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJND"))) {
            builder.append(" and kjnd=#{KJND}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "GSDM"))) {
            builder.append(" and gsdm=#{GSDM}");
        }
        return builder.toString();
    }
}
