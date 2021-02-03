package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class HsdwSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.hsdw";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
                "dwdm","dwmc"
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from GL_Ztcs where CHARINDEX('2020',kjnd)=1 AND ztbh <> '99999999999999999999'");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "dwdm"))) {
            builder.append(" and dwdm = #{dwdm}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "dwmc"))) {
            builder.append(" and dwmc = #{dwmc}");
        }
        return builder.toString();
    }
}
