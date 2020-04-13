package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class FeeStatisticsSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "process";
    }

    @Override
    protected String[] getColumns() {
        return new String[0];
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder();
        builder.append("select process_parent.processName processName,process_parent.createTime startTime,sum(process.fee+process.add_fee) feeSum from process_parent process_parent ");
        builder.append("left join process process on process.processParentId = process_parent.id where 1=1 ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "startTime"))) {
            builder.append(" and process_parent.createTime >='" + map.get("startTime") + "'");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "endTime"))) {
            builder.append(" and process_parent.createTime <='" + map.get("endTime") + "'");
        }
        builder.append(" GROUP BY process_parent.id");
        return builder.toString();
    }
}
