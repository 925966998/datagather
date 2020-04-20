package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author linan
 * Create By Generator
 */
public class ProductSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "product";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
                "processId",
                "productStatus",
        };
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ps.*,pt.processName as processName FROM product pd LEFT JOIN process ps on pd.processId = ps.id LEFT JOIN process_parent pt ON pt.id=ps.processParentId where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "processId"))) {
            builder.append(" and pd.processId=#{processId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "productStatus"))) {
            builder.append(" and pd.productStatus = #{productStatus}");
        }
        return builder.toString();
    }
}
