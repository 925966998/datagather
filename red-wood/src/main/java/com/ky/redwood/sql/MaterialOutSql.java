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
                "goodsId",
                "productName",
                "materialId",
                "materialName",
                "amount",
                "useAmount",
                "status",
                "processStatus",
                "userId",
                "parentId",
                "consumablesIs",
                "goodsAmount",
        };
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT m.*,p.flowStatus as flowStatus,g.allName as allName  FROM material_out m ");
        builder.append("LEFT JOIN goods g ON g.id=m.productName  ");
        builder.append("LEFT JOIN (select MAX(flowStatus)AS flowStatus ,materialOutId AS materialOutId from process GROUP BY materialOutId) p ON p.materialOutId=m.id where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "goodsId"))) {
            builder.append(" and m.goodsId=#{goodsId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "materialId"))) {
            builder.append(" and m.materialId=#{materialId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "materialName"))) {
            builder.append(" and m.materialName=#{materialName}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "amount"))) {
            builder.append(" and m.amount=#{amount}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "status"))) {
            builder.append(" and m.status=#{status}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "processStatus"))) {
            builder.append(" and m.processStatus=#{processStatus}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "userId"))) {
            builder.append(" and m.userId=#{userId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "consumablesIs"))) {
            builder.append(" and m.consumablesIs=#{consumablesIs}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "goodsAmount"))) {
            builder.append(" and m.goodsAmount=#{goodsAmount}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "startTime"))) {
            builder.append(" and m.createTime >='" + map.get("startTime") + "'");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "endTime"))) {
            builder.append(" and m.createTime <='" + map.get("endTime") + "'");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "sort")) && StringUtils.isNotBlank(MapUtils.getString(map, "order")))
            builder.append(" order by ").append(map.get("sort")).append(" ").append(map.get("order"));
        return builder.toString();
    }
}
