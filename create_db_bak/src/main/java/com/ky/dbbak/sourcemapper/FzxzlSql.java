package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class FzxzlSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.GL_Fzxzl";
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

    public String _queryFzdm(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM GL_Fzxzl where fzdm=#{fzdm}");
        return builder.toString();
    }
    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM GL_Fzxzl");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT fzdm,fzmc FROM GL_Fzxzl where fzdm=#{fzdm}");
        return builder.toString();
    }

    public String _queryKjnd(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "lbdm"))) {
            builder.append(" and lbdm=#{lbdm}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "kjnd"))) {
            builder.append(" and kjnd=#{kjnd}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "gsdm"))) {
            builder.append(" and gsdm=#{gsdm}");
        }
        return builder.toString();
    }

    public String _queryYeKjnd(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "fzdm"))) {
            builder.append(" and fzdm=#{fzdm}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "kjnd"))) {
            builder.append(" and kjnd=#{kjnd}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "gsdm"))) {
            builder.append(" and gsdm=#{gsdm}");
        }
        return builder.toString();
    }
}
