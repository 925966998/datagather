package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class FzlxSql extends BaseProvider {

    @Override
    protected String getTableName() {
        return "dbo.FZLX";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"XZQHDM", "XZQHMC", "KJND", "DWMC", "DWDM", "KJDZZBBH", "KJDZZBMC", "FZLXBM", "FZLXMC","FZLXJG"
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }


    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZLX ");
        return builder.toString();
    }


    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "XZQHDM"))) {
            builder.append(" and XZQHDM=#{XZQHDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "XZQHMC"))) {
            builder.append(" and XZQHMC=#{XZQHMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJND"))) {
            builder.append(" and KJND=#{KJND}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "DWMC"))) {
            builder.append(" and DWMC=#{DWMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "DWDM"))) {
            builder.append(" and DWDM=#{DWDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJDZZBBH"))) {
            builder.append(" and KJDZZBBH=#{KJDZZBBH}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJDZZBMC"))) {
            builder.append(" and KJDZZBMC=#{KJDZZBMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "FZLXBM"))) {
            builder.append(" and FZLXBM=#{FZLXBM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "FZLXMC"))) {
            builder.append(" and FZLXMC=#{FZLXMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "FZLXJG"))) {
            builder.append(" and FZLXJG=#{FZLXJG}");
        }


        return builder.toString();
    }
}
