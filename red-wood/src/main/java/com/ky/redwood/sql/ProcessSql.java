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
                "endTime",
                "processingPersonnel"
        };
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder();
        if (map.get("typePage").toString().equals("1")) {
            builder.append("SELECT t.*,pp.processName AS processName FROM ");
            builder.append("process t ");
            builder.append("LEFT JOIN process_parent pp ON pp.id=t.processParentId where 1=1 ");
        } else {
            builder.append("SELECT t.* FROM ");
            builder.append("(SELECT processParentId,max(createTime) as createTime FROM process GROUP BY processParentId) a ");
            builder.append("LEFT JOIN process t  ON t.processParentId=a.processParentId and t.createTime = a.createTime  where 1=1 ");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "processParentId"))) {
            builder.append(" and t.processParentId=#{processParentId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "productName"))) {
            builder.append(" and t.productName=#{productName}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "flowStatus"))) {
            builder.append(" and t.flowStatus=#{flowStatus}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "type"))) {
            builder.append(" and t.type=#{type}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "amount"))) {
            builder.append(" and t.amount=#{amount}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "add_fee"))) {
            builder.append(" and t.add_fee=#{add_fee}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "fee"))) {
            builder.append(" and t.fee=#{fee}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "userId"))) {
            builder.append(" and t.userId=#{userId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "endTime"))) {
            builder.append(" and t.endTime=#{endTime}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "processingPersonnel"))) {
            builder.append(" and t.processingPersonnel=#{processingPersonnel}");
        }
        return builder.toString();
    }

}
