package com.ky.centerservice.mapper;

import com.ky.centerservice.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class PushMainSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "push_main";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"provinceCode", "cityCode", "countyCode", "captialType", "pushAmount", "pushDate", "pushResult","batchNumber"};
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + this.getTableName() + " where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "provinceCode"))) {
            builder.append(" and provinceCode = #{provinceCode}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "cityCode"))) {
            builder.append(" and cityCode = #{cityCode}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "countyCode"))) {
            builder.append(" and countyCode = #{countyCode}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "captialType"))) {
            builder.append(" and captialType = #{captialType}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "pushAmount"))) {
            builder.append(" and pushAmount = #{pushAmount}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "pushDate"))) {
            builder.append(" and pushDate = #{pushDate}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "pushResult"))) {
            builder.append(" and pushResult = #{pushResult}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "batchNumber"))) {
            builder.append(" and batchNumber = #{batchNumber}");
        }
        builder.append(" order by createTime desc");
        return builder.toString();
    }

    @Override
    public String _queryCount(Map map) {
        StringBuilder builder = new StringBuilder("select count(1) from " + this.getTableName() + " where 1=1 and logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "provinceCode"))) {
            builder.append(" and provinceCode = #{provinceCode}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "cityCode"))) {
            builder.append(" and cityCode = #{cityCode}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "countyCode"))) {
            builder.append(" and countyCode = #{countyCode}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "captialType"))) {
            builder.append(" and captialType = #{captialType}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "pushAmount"))) {
            builder.append(" and pushAmount = #{pushAmount}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "pushDate"))) {
            builder.append(" and pushDate = #{pushDate}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "pushResult"))) {
            builder.append(" and pushResult = #{pushResult}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "batchNumber"))) {
            builder.append(" and batchNumber = #{batchNumber}");
        }
        builder.append(" order by createTime desc");
        return builder.toString();
    }

    @Override
    public String _queryPage(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + this.getTableName() + " where 1=1 and logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "provinceCode"))) {
            builder.append(" and provinceCode = #{provinceCode}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "cityCode"))) {
            builder.append(" and cityCode = #{cityCode}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "countyCode"))) {
            builder.append(" and countyCode = #{countyCode}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "captialType"))) {
            builder.append(" and captialType = #{captialType}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "pushAmount"))) {
            builder.append(" and pushAmount = #{pushAmount}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "pushDate"))) {
            builder.append(" and pushDate = #{pushDate}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "pushResult"))) {
            builder.append(" and pushResult = #{pushResult}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "batchNumber"))) {
            builder.append(" and batchNumber = #{batchNumber}");
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
