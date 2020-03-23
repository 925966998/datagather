package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class PubkjqjSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.PubKjqj";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"gsdm",
                "ZTH",
                "kjnd",
                "kjqjxh",
                "qsrq",
                "jsrq",
                "jzbz",
                "zzqmcl",
                "qmpzpc",
                "syzt",
                "xgzt",
                "chjzbz",
                "jzzt"
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM PubKjqj");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "kjnd"))) {
            builder.append(" and kjnd=#{kjnd}");
        }
        return builder.toString();
    }

}
