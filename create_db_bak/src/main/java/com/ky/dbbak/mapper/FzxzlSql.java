package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseProvider;

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

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM GL_Fzxzl where fzdm=#{fzdm}");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT fzdm,fzmc FROM GL_Fzxzl where fzdm=#{fzdm}");
        return builder.toString();
    }

    public String _queryFzdm(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM GL_Fzxzl where fzdm=#{fzdm}");
        return builder.toString();
    }

}
