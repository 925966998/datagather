package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseProvider;

import java.util.Map;


public class KmxzlxSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.GL_KMXZLX";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
                "gsdm",

                "zth",

                "lxdm",

                "lxmc",

                "KJTXDM",

                "KJYSLBDM"

        };

    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        return null;
    }

    //kmmc
    public String _queryKMXZLX(Map map) {
        StringBuilder builder = new StringBuilder("select * FROM GL_KMXZLX WHERE gsdm <'99999999999999999999' AND lxdm=#{lxdm}");
        return builder.toString();
    }

}
