package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class AreaSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.area";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"areaId", "areaName", "areaCode", "pid", "areaLevel"};
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "areaId"))) {
            builder.append(" and areaId=#{areaId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "areaName"))) {
            builder.append(" and areaName=#{areaName}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "areaCode"))) {
            builder.append(" and areaCode=#{areaCode}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "pid"))) {
            builder.append(" and pid=#{pid}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "areaLevel"))) {
            builder.append(" and areaLevel=#{areaLevel}");
        }

        builder.append(" order by areaCode");
        return builder.toString();
    }

}
