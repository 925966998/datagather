package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author linan
 * Create By Generator
 */
public class MaterialSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "material";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
            "materialName",
                    "materialSpec",
                    "measdoc",
                    "materialType",
                    "amount",
                    "price",
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
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "materialName"))) {
            builder.append(" and materialName=#{materialName}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "materialSpec"))) {
            builder.append(" and materialSpec=#{materialSpec}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "measdoc"))) {
            builder.append(" and measdoc=#{measdoc}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "materialType"))) {
            builder.append(" and materialType=#{materialType}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "amount"))) {
            builder.append(" and amount=#{amount}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "price"))) {
            builder.append(" and price=#{price}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "userId"))) {
            builder.append(" and userId=#{userId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "sort")) && StringUtils.isNotBlank(MapUtils.getString(map, "order")))
            builder.append(" order by ").append(map.get("sort")).append(" ").append(map.get("order"));
        return builder.toString();
    }

}
