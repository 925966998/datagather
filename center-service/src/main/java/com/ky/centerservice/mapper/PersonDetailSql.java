package com.ky.centerservice.mapper;

import com.ky.centerservice.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class PersonDetailSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "person_detail";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"name", "idCardNo", "socialInsuranceNo", "bankService", "bankOpen", "bankNo", "belongArea"};
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + this.getTableName() + " where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "name"))) {
            builder.append(" and name = #{name}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
            builder.append(" and idCardNo = #{idCardNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "socialInsuranceNo"))) {
            builder.append(" and socialInsuranceNo = #{socialInsuranceNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "bankService"))) {
            builder.append(" and bankService = #{bankService}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "bankOpen"))) {
            builder.append(" and bankOpen = #{bankOpen}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "bankNo"))) {
            builder.append(" and bankNo = #{bankNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "belongArea"))) {
            builder.append(" and belongArea = #{belongArea}");
        }
        builder.append(" order by createTime desc");
        return builder.toString();
    }

    @Override
    public String _queryCount(Map map) {
        StringBuilder builder = new StringBuilder("select count(1) from " + this.getTableName() + " where 1=1 and logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "name"))) {
            builder.append(" and name = #{name}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
            builder.append(" and idCardNo = #{idCardNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "socialInsuranceNo"))) {
            builder.append(" and socialInsuranceNo = #{socialInsuranceNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "bankService"))) {
            builder.append(" and bankService = #{bankService}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "bankOpen"))) {
            builder.append(" and bankOpen = #{bankOpen}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "bankNo"))) {
            builder.append(" and bankNo = #{bankNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "belongArea"))) {
            builder.append(" and belongArea = #{belongArea}");
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
        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
            builder.append(" and idCardNo = #{idCardNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "socialInsuranceNo"))) {
            builder.append(" and socialInsuranceNo = #{socialInsuranceNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "bankService"))) {
            builder.append(" and bankService = #{bankService}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "bankOpen"))) {
            builder.append(" and bankOpen = #{bankOpen}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "bankNo"))) {
            builder.append(" and bankNo = #{bankNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "belongArea"))) {
            builder.append(" and belongArea = #{belongArea}");
        }
        builder.append(" order by createTime desc");
        builder.append(this.pageHelp(MapUtils.getLongValue(map, "page"), MapUtils.getLongValue(map, "rows")));
        return builder.toString();
    }

    public StringBuilder pageHelp(long currentPage, long pageSize) {
        long count = (currentPage - 1) * pageSize;
        if (count != 0) {
            count = count;
        }
        StringBuilder builder = new StringBuilder(" limit ");
        builder.append(count);
        builder.append(" ,");
        builder.append(pageSize);
        return builder;
    }
}
