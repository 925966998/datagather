package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author linan
 * Create By Generator
 */
public class StockSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "stock";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
                "productId",
                "productName",
                "storageTime",
                "goodsSpecs",
                "goodsModel",
                "goodsUnit",
                "goodsNum",
                "unitPrice",
                "sumPrice",
                "manager",
                "accountant",
                "curator",
                "operator",
                "supplier",
                "remarks",
        };
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "stockId"))) {
            builder.append(" and stockId=#{stockId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "productPrice"))) {
            builder.append(" and productPrice=#{productPrice}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "productNum"))) {
            builder.append(" and productNum=#{productNum}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "startTime"))) {
            builder.append(" and storageTime >='" + map.get("startTime") + "'");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "endTime"))) {
            builder.append(" and storageTime <='" + map.get("endTime") + "'");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "sort")) && StringUtils.isNotBlank(MapUtils.getString(map, "order")))
            builder.append(" order by ").append(map.get("sort")).append(" ").append(map.get("order"));
        return builder.toString();
    }

}
