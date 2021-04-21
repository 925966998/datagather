package com.ky.newService.mapper;

import com.ky.newService.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @className: SupplierSql
 * @description: TODO
 * @author: Lcj
 * @date: 2020-09-28 10:33
 */
public class QualificationSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "qualification";
    }

    @Override
    protected String[] getColumns() {
        return new String[]{"name", "endDay", "supplierManageId", "startTime", "endTime"};
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + this.getTableName() + " where 1=1 and logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "name"))) {
            builder.append(" and name = #{name}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "endDay"))) {
            builder.append(" and endDay = #{endDay}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierManageId"))) {
            builder.append(" and supplierManageId = #{supplierManageId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "startTime"))) {
            builder.append(" and startTime = #{startTime}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "endTime"))) {
            builder.append(" and endTime = #{endTime}");
        }
        builder.append(" order by createTime desc");
        return builder.toString();
    }

    @Override
    public String _queryPage(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + this.getTableName() + " where 1=1 and logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "name"))) {
            builder.append(" and name = #{name}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "endDay"))) {
            builder.append(" and endDay = #{endDay}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierManageId"))) {
            builder.append(" and supplierManageId = #{supplierManageId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "startTime"))) {
            builder.append(" and startTime = #{startTime}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "endTime"))) {
            builder.append(" and endTime = #{endTime}");
        }
        builder.append(" order by createTime desc");
        builder.append(this.pageHelp(MapUtils.getLongValue(map, "page"), MapUtils.getLongValue(map, "rows")));
        return builder.toString();
    }

    @Override
    public String _queryCount(Map map) {
        StringBuilder builder = new StringBuilder("select count(1) from " + this.getTableName() + " where 1=1 and logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "name"))) {
            builder.append(" and name = #{name}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "endDay"))) {
            builder.append(" and endDay = #{endDay}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierManageId"))) {
            builder.append(" and supplierManageId = #{supplierManageId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "startTime"))) {
            builder.append(" and startTime = #{startTime}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "endTime"))) {
            builder.append(" and endTime = #{endTime}");
        }
        builder.append(" order by createTime desc");
        return builder.toString();
    }
    public StringBuilder pageHelp(long currentPage, long pageSize) {
        long count = (currentPage - 1) * pageSize;
        if (count != 0) {
            count = count ;
        }
        StringBuilder builder = new StringBuilder(" limit ");
        builder.append(count);
        builder.append(" ,");
        builder.append(pageSize);
        return builder;
    }
}
