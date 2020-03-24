package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseProvider;

import java.util.Map;

public class FzxxSql extends BaseProvider {

    @Override
    protected String getTableName() {
        return "dbo.FZXX";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"XZQHDM", "XZQHMC ", "KJND", "DWMC", "DWDM", "KJDZZBBH", "KJDZZBMC", "FZLX", "FZBM","FZMC",
                "FZQC","FZJC","SJFZBM","FZSM","SFWYSFZ"};
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZXX ");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZXX ");
        return builder.toString();
    }

}
