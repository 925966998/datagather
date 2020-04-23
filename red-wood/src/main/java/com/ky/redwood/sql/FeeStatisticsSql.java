package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class FeeStatisticsSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "cost_sharing";
    }

    @Override
    protected String[] getColumns() {
        return new String[]{
                "materialOutId",
                "jgFee",
                "jwlFee",
                "sdFee",
                "fzFee",
                "xlFee",
                "qtFee",
                "ftFee",
                "productTime"
        };
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder();
        builder.append("select cost_sharing.*,material_out.productName productName from cost_sharing  left join material_out on cost_sharing.materialOutId=material_out.id where 1=1 ");
        return builder.toString();
    }

    public String querySharing(Map map) {
        StringBuilder builder = new StringBuilder();
        builder.append("select cost_sharing.*,material_out.productName productName from cost_sharing  left join material_out on cost_sharing.materialOutId=material_out.id where 1=1 ");
        //builder.append(" and cost_sharing.materialOutId in (select id from material_out where createTime >='" + map.get("startTime") + "' and createTime <='" + map.get("endTime") + "' )");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "startTime"))) {
            builder.append(" and cost_sharing.productTime >='" + map.get("startTime") + "'");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "endTime"))) {
            builder.append(" and cost_sharing.productTime <='" + map.get("endTime") + "'");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "sort")) && StringUtils.isNotBlank(MapUtils.getString(map, "order")))
            builder.append(" order by ").append(map.get("sort")).append(" ").append(map.get("order"));
        if (StringUtils.isNotBlank(MapUtils.getString(map, "currentPage")) && StringUtils.isNotBlank(MapUtils.getString(map, "pageSize")))
            builder.append(" limit ").append((MapUtils.getInteger(map, "currentPage") - 1) * MapUtils.getInteger(map, "pageSize")).append(",").append(MapUtils.getInteger(map, "pageSize"));
        return builder.toString();
    }

    public String queryNoSharing(Map map) {
        StringBuilder builder = new StringBuilder();
        builder.append("select material_out.id id,material_out.productName productName,material_out.createTime productTime,sum(process.fee+process.add_fee) jgFee from material_out material_out ");
        builder.append("INNER join process process on process.materialOutId = material_out.id where 1=1 ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "startTime"))) {
            builder.append(" and material_out.createTime >='" + map.get("startTime") + "'");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "endTime"))) {
            builder.append(" and material_out.createTime <='" + map.get("endTime") + "'");
        }
        builder.append(" GROUP BY material_out.id");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "sort")) && StringUtils.isNotBlank(MapUtils.getString(map, "order")))
            builder.append(" order by ").append(map.get("sort")).append(" ").append(map.get("order"));
        if (StringUtils.isNotBlank(MapUtils.getString(map, "currentPage")) && StringUtils.isNotBlank(MapUtils.getString(map, "pageSize")))
            builder.append(" limit ").append((MapUtils.getInteger(map, "currentPage") - 1) * MapUtils.getInteger(map, "pageSize")).append(",").append(MapUtils.getInteger(map, "pageSize"));
        return builder.toString();
    }

    public String queryAllFeeCount(Map map) {
        StringBuilder builder = new StringBuilder();
        builder.append("select sum(jgFee) jgFee,sum(jwlFee) jwlFee,sum(sdFee) sdFee,sum(fzFee) fzFee,sum(xlFee) xlFee,sum(qtFee) qtFee,sum(ftFee) ftFee from cost_sharing where 1=1 ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "startTime"))) {
            builder.append(" and productTime >='" + map.get("startTime") + "'");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "endTime"))) {
            builder.append(" and productTime <='" + map.get("endTime") + "'");
        }
        return builder.toString();
    }


    public String queryJgFeeCount(Map map) {
        StringBuilder builder = new StringBuilder();
        builder.append("select sum(process.fee+process.add_fee) jgFee from material_out material_out ");
        builder.append("INNER join process process on process.materialOutId = material_out.id where 1=1 ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "startTime"))) {
            builder.append(" and material_out.createTime >='" + map.get("startTime") + "'");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "endTime"))) {
            builder.append(" and material_out.createTime <='" + map.get("endTime") + "'");
        }
        return builder.toString();
    }
}
