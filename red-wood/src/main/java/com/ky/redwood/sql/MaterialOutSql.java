package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author linan
 * Create By Generator
 */
public class MaterialOutSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "material_out";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
            "processParentId",
                    "processName",
                    "materialId",
                    "materialName",
                    "amount",
                    "status",
                    "processStatus",
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
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "materialId"))) {
            builder.append(" and materialId=#{materialId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "amount"))) {
            builder.append(" and amount=#{amount}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "status"))) {
            builder.append(" and status=#{status}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "processStatus"))) {
            builder.append(" and processStatus=#{processStatus}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "userId"))) {
            builder.append(" and userId=#{userId}");
        }
        return builder.toString();
    }

}
