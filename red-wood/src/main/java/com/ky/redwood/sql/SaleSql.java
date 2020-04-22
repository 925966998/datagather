package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author linan
 * Create By Generator
 */
public class SaleSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "sale";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
                "productId",
                "goodsPrice",
                "customName",
                "sellDate",
        };
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "productId"))) {
            builder.append(" and productId=#{productId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "goodsPrice"))) {
            builder.append(" and goodsPrice=#{goodsPrice}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "customName"))) {
            builder.append(" and customName=#{customName}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "startTime"))) {
            builder.append(" and sellDate >='" + map.get("startTime") + "'");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "endTime"))) {
            builder.append(" and sellDate <='" + map.get("endTime") + "'");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "sort")) && StringUtils.isNotBlank(MapUtils.getString(map, "order")))
            builder.append(" order by ").append(map.get("sort")).append(" ").append(map.get("order"));
        return builder.toString();
    }

}
