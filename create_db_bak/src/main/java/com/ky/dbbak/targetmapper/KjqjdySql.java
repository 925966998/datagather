package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class KjqjdySql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "KJQJDY";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
                "XZQHDM",

                "XZQHMC",

                "KJND",

                "DWMC",

                "DWDM",

                "KJDZZBBH",

                "KJDZZBMC",

                "KJYF",

                "KSRQ",

                "JZRQ",
        };

    }

    @Override
    protected String getDialect() {
        return "sqlserver";
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
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJYF"))) {
            builder.append(" and KJYF=#{KJYF}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KSRQ"))) {
            builder.append(" and KSRQ=#{KSRQ}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JZRQ"))) {
            builder.append(" and JZRQ=#{JZRQ}");
        }
        return builder.toString();
    }


}
