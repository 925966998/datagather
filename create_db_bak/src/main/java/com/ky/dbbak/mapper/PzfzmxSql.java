package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseProvider;

import java.util.Map;

public class PzfzmxSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.PZFZMX";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
                "XZQHDM", "XZQHMC", "KJND", "DWMC", "DWDM", "KJDZZBBH", "KJDZZBMC", "KJYF", "PZLXBH", "JZPZZL", "JZPZBH", "JZPZHH", "FLXH", "JZPZZY", "KJTX", "KJKMBM", "KJKMMC", "FZLX", "FZBM", "FZMC", "FZQC", "YJFZBM", "EJFZBM", "SJFZBM", "SIJFZBM", "WJFZBM", "JFFSE", "DFFSE", "BZ", "WBJFFSE", "WBDFFSE", "HL", "SL", "DJ"
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select\n" +
                "        *\n" +
                "        from PZFZMX ");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("select\n" +
                "        *\n" +
                "        from PZFZMX ");
        return builder.toString();
    }

}
