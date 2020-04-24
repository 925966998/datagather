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
                "stockId",
                "goodsPrice",
                "customName",
                "sellDate",
                "invoiceId",
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
                "remarks",
                "productName",
                "processParentId",
                "saleOrNo",
        };
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT s.*,pp.processName as processParentName FROM sale s left join process_parent pp on pp.id=s.processParentId where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "productId"))) {
            builder.append(" and s.productId=#{productId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "goodsPrice"))) {
            builder.append(" and s.goodsPrice=#{goodsPrice}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "customName"))) {
            builder.append(" and s.customName=#{customName}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "invoiceId"))) {
            builder.append(" and s.invoiceId=#{invoiceId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "goodsSpecs"))) {
            builder.append(" and s.goodsSpecs=#{goodsSpecs}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "goodsModel"))) {
            builder.append(" and s.goodsModel=#{goodsModel}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "goodsUnit"))) {
            builder.append(" and s.goodsUnit=#{goodsUnit}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "goodsNum"))) {
            builder.append(" and s.goodsNum=#{goodsNum}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "unitPrice"))) {
            builder.append(" and s.unitPrice=#{unitPrice}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "sumPrice"))) {
            builder.append(" and s.sumPrice=#{sumPrice}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "manager"))) {
            builder.append(" and s.manager=#{manager}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "accountant"))) {
            builder.append(" and s.accountant=#{accountant}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "curator"))) {
            builder.append(" and s.curator=#{curator}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "operator"))) {
            builder.append(" and s.operator=#{operator}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "processParentId"))) {
            builder.append(" and s.processParentId=#{processParentId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "saleOrNo"))) {
            builder.append(" and s.saleOrNo=#{saleOrNo}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "startTime"))) {
            builder.append(" and s.sellDate >='" + map.get("startTime") + "'");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "endTime"))) {
            builder.append(" and s.sellDate <='" + map.get("endTime") + "'");
        }

        if (StringUtils.isNotBlank(MapUtils.getString(map, "sort")) && StringUtils.isNotBlank(MapUtils.getString(map, "order")))
            builder.append(" order by ").append(map.get("sort")).append(" ").append(map.get("order"));
        return builder.toString();
    }

}
