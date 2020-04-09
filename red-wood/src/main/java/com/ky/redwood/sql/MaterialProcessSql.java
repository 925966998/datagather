package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author linan
 * Create By Generator
 */
public class MaterialProcessSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "material_process";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
                "materialOutId",
                "processFlow",
                "productName",
                "productUseAmount",
                "processFee",
                "status",
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
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "materialOutId"))) {
            builder.append(" and materialOutId=#{materialOutId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "processFlow"))) {
            builder.append(" and processFlow=#{processFlow}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "productName"))) {
            builder.append(" and productName=#{productName}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "productUseAmount"))) {
            builder.append(" and productUseAmount=#{productUseAmount}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "processFee"))) {
            builder.append(" and processFee=#{processFee}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "status"))) {
            builder.append(" and status=#{status}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "userId"))) {
            builder.append(" and userId=#{userId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "sort")) && StringUtils.isNotBlank(MapUtils.getString(map, "order")))
            builder.append(" order by ").append(map.get("sort")).append(" ").append(map.get("order"));
        return builder.toString();
    }

}
