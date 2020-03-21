package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseProvider;

import java.util.Map;

public class FzxlbSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.GL_Fzxlb";
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
        StringBuilder builder = new StringBuilder("SELECT lbdm,lbmc,lbfj FROM GL_Fzxlb where kjnd='2019' and gsdm='201901' and lbdm=#{lbdm} GROUP BY lbdm,lbmc,lbfj");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT lbdm,lbmc,lbfj FROM GL_Fzxlb where kjnd='2019' and gsdm='201901' and lbdm=#{lbdm} GROUP BY lbdm,lbmc,lbfj");
        return builder.toString();
    }

}
