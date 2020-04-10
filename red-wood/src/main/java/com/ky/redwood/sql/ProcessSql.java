package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author linan
 * Create By Generator
 */
public class ProcessSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "process";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
            "processParentId",
                    "productName",
                    "flowStatus",
                    "type",
                    "amount",
                    "add_fee",
                    "fee",
                    "userId",
        };
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "processParentId"))) {
            builder.append(" and processParentId=#{processParentId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "productName"))) {
            builder.append(" and productName=#{productName}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "flowStatus"))) {
            builder.append(" and flowStatus=#{flowStatus}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "type"))) {
            builder.append(" and type=#{type}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "amount"))) {
            builder.append(" and amount=#{amount}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "add_fee"))) {
            builder.append(" and add_fee=#{add_fee}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "fee"))) {
            builder.append(" and fee=#{fee}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "userId"))) {
            builder.append(" and userId=#{userId}");
        }
        return builder.toString();
    }

}
