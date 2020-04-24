package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author linan
 * Create By Generator
 */
public class GoodsSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "goods";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
                "goodsSpec",
                "goodsUnit",
                "allName",
                "texture",
        };
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT s.* FROM goods s ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "goodsSpec"))) {
            builder.append(" and s.goodsSpec=#{goodsSpec}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "goodsUnit"))) {
            builder.append(" and s.goodsUnit=#{goodsUnit}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "allName"))) {
            builder.append(" and s.allName like concat('%',#{allName},'%')" );
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "texture"))) {
            builder.append(" and s.texture=#{texture}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "sort")) && StringUtils.isNotBlank(MapUtils.getString(map, "order")))
            builder.append(" order by ").append(map.get("sort")).append(" ").append(map.get("order"));
        return builder.toString();
    }

}
