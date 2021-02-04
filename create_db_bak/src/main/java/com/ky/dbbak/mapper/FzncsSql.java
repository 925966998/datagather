package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class FzncsSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.FZNCS";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"KJKMBM", "FZLXMC", "FZBM", "XZQHDM", "XZQHMC", "KJND", "DWMC", "DWDM", "KJDZZBBH", "KJDZZBMC", "KJYF", "KJKMMC", "KJKMJC", "KJTX", "SFZDJKM", "SJKMBM", "FZMC", "BZMC", "YEFX", "BBQCYE", "QCSL", "WBQCYE"
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZNCS where 1=1 ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJDZZBBH"))) {
            builder.append(" and KJDZZBBH=#{KJDZZBBH}");
        }
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZNCS where 1=1 ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "XZQHDM"))) {
            builder.append(" and XZQHDM=#{XZQHDM}");
        }
        return builder.toString();
    }


    public String queryFzncs(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZNCS where 1=1");
        if (org.apache.commons.lang.StringUtils.isNotEmpty(MapUtils.getString(map, "KJDZZBBH"))) {
            builder.append(" and KJDZZBBH=#{KJDZZBBH}");
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(MapUtils.getString(map, "KJKMBM"))) {
            builder.append(" and KJKMBM=#{KJKMBM}");
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(MapUtils.getString(map, "FZLX"))) {
            builder.append(" and FZLX=#{FZLX}");
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(MapUtils.getString(map, "FZBM"))) {
            builder.append(" and FZBM=#{FZBM}");
        }
        return builder.toString();
    }

    public String _updateFzncs(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(getTableName());
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
        builder.append(" where KJDZZBBH=#{KJDZZBBH}");
        builder.append(" and KJKMBM=#{KJKMBM}");
        builder.append(" and FZLX=#{FZLX}");
        builder.append(" and FZBM=#{FZBM}");
        return builder.toString();
    }

}
