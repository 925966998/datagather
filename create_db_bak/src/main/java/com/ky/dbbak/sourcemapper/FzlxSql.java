package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseProvider;

import java.util.Map;

public class FzlxSql extends BaseProvider {

    @Override
    protected String getTableName() {
        return "dbo.FZLX";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"XZQHDM", "XZQHMC ", "KJND", "DWMC", "DWDM", "KJDZZBBH", "KJDZZBMC", "FZLXBM", "FZLXMC","FZLXJG"
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZLX ");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZLX ");
        return builder.toString();
    }

}
