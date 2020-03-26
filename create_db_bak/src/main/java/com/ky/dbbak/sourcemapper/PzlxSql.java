package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseProvider;

import java.util.Map;

public class PzlxSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.GL_Pzlx";
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
        StringBuilder builder = new StringBuilder("SELECT * FROM GL_Pzlx WHERE pzlxdm = #{PZLXDM} AND gsdm <> '99999999999999999999';");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT pzlxmc FROM GL_Pzlx WHERE pzlxdm = #{PZLXDM} AND gsdm <> '99999999999999999999';");
        return builder.toString();
    }

}
