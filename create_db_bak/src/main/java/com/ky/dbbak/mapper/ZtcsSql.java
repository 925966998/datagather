package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseProvider;

import java.util.Map;

public class ZtcsSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.GL_Ztcs";
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
        StringBuilder builder = new StringBuilder("select * from GL_Ztcs where CHARINDEX('2019',kjnd)=1 AND ztbh <> '99999999999999999999'");
        return builder.toString();
    }

    public String _queryselect() {
        StringBuilder builder = new StringBuilder("select * from GL_Ztcs where CHARINDEX('2019',kjnd)=1 AND ztbh <> '99999999999999999999'");
        return builder.toString();
    }


}
