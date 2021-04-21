package com.ky.newService.mapper;

import com.ky.newService.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @className: VisitSql
 * @description: TODO
 * @author: Lcj
 * @date: 2020-09-28 10:33
 */
public class VisitSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "visiter";
    }

    @Override
    protected String[] getColumns() {
        return new String[]{"supplierManageId", "name", "visitDate", "visitName","content"};
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + this.getTableName() + " where 1=1 and logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "name"))) {
            builder.append(" and name = #{name}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierManageId"))) {
            builder.append(" and supplierManageId = #{supplierManageId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "visitName"))) {
            builder.append(" and visitName = #{visitName}");
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
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierManageId"))) {
            builder.append(" and supplierManageId = #{supplierManageId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "visitName"))) {
            builder.append(" and visitName = #{visitName}");
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
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierManageId"))) {
            builder.append(" and supplierManageId = #{supplierManageId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "visitName"))) {
            builder.append(" and visitName = #{visitName}");
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
