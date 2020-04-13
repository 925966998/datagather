package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author linan
 * Create By Generator
 */
public class ProcessFlowSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "process_flow";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
            "id",
                    "processFlowName",
        };
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "processFlowName"))) {
            builder.append(" and processFlowName like concat('%',#{processFlowName},'%')");
        }
        return builder.toString();
    }

}
