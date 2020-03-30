package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseProvider;

import java.util.Map;

public class PzmlSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.GL_Pzml";
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
        StringBuilder builder = new StringBuilder("select * from GL_Pzml where CHARINDEX('2019',kjqj)=1 ");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("select srrq,fjzs,sr,sh,jzr,CN,kjzg,pzly,zt,pzrq from GL_Pzml where CHARINDEX('2019',kjqj)=1 and idpzh = #{IDPZH}");
        return builder.toString();
    }

}
