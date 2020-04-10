package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author linan
 * Create By Generator
 */
public class ProcessParentSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "process_parent";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
            "processName",
                    "type",
        };
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "processName"))) {
            builder.append(" and processName=#{processName}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "type"))) {
            builder.append(" and type=#{type}");
        }
        return builder.toString();
    }

}
