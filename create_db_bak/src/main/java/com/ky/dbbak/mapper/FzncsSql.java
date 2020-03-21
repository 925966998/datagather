package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseProvider;

import java.util.Map;

public class FzncsSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.FZNCS";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"XZQHDM", "XZQHMC ", "KJND", "DWMC", "DWDM", "KJDZZBBH", "KJDZZBMC", "KJYF", "KJKMBM", "KJKMMC", "KJKMJC", "KJTX", "SFZDJKM", "SJKMBM", "FZLX", "FZBM", "FZMC", "BZMC", "YEFX", "BBQCYE", "QCSL", "WBQCYE"
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZNCS ");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZNCS ");
        return builder.toString();
    }

}
