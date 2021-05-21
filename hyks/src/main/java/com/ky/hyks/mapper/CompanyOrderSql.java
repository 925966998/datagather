package com.ky.hyks.mapper;


import com.ky.hyks.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class CompanyOrderSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "KY_HYKS_company_order";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"companyId","orderId","amount","state","priceNum" };
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select co.*,s.NAME as companyName,o.name as orderName from (SELECT\n" +
                "\tco.*,\n" +
                "\tc.TALKNUM \n" +
                "FROM\n" +
                "\tKY_HYKS_COMPANY_ORDER co\n" +
                "\tLEFT JOIN (\n" +
                "\tSELECT\n" +
                "\t\to.*,\n" +
                "\t\tr.TALKNUM \n" +
                "\tFROM\n" +
                "\t\tKY_HYKS_ORDERINFO o\n" +
                "\tLEFT JOIN ( SELECT ol.* , l.TALKNUM FROM KY_HYKS_ORDER_LIST_INFO ol LEFT JOIN KY_HYKS_ORDERLIST l ON l.ID = ol.ORDERLISTID ) r ON o.ID = r.ORDERINFOID \n" +
                "\t) c ON c.ID = co.ORDERID ) co ");
        builder.append("left join bd_supplier s on co.companyId=s.pk_supplier ");
        builder.append("left join KY_HYKS_orderInfo o on co.orderId=o.id ");
        builder.append("where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "companyId"))) {
            builder.append(" and co.companyId = #{companyId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "orderId"))) {
            builder.append(" and co.orderId = #{orderId}");
        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "fullName"))) {
//            builder.append(" and u.fullName like concat('%',#{fullName},'%')");
//        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
//            builder.append(" and u.idCardNo = #{idCardNo}");
//        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "companyId"))) {
//            builder.append(" and u.companyId = #{companyId}");
//        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "userNote"))) {
//            builder.append(" and u.userNote = #{userNote}");
//        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "roleId"))) {
//            builder.append(" and u.roleId = #{roleId}");
//        }
        builder.append(" order by co.createTime desc");
        return builder.toString();
    }

    @Override
    public String _queryPage(Map map) {
        StringBuilder builder = new StringBuilder("select co.*,s.NAME as companyName,o.name as orderName from (SELECT\n" +
                "\tco.*,\n" +
                "\tc.TALKNUM \n" +
                "FROM\n" +
                "\tKY_HYKS_COMPANY_ORDER co\n" +
                "\tLEFT JOIN (\n" +
                "\tSELECT\n" +
                "\t\to.*,\n" +
                "\t\tr.TALKNUM \n" +
                "\tFROM\n" +
                "\t\tKY_HYKS_ORDERINFO o\n" +
                "\tLEFT JOIN ( SELECT ol.* , l.TALKNUM FROM KY_HYKS_ORDER_LIST_INFO ol LEFT JOIN KY_HYKS_ORDERLIST l ON l.ID = ol.ORDERLISTID ) r ON o.ID = r.ORDERINFOID \n" +
                "\t) c ON c.ID = co.ORDERID ) co ");
        builder.append("left join bd_supplier s on co.companyId=s.pk_supplier ");
        builder.append("left join KY_HYKS_orderInfo o on co.orderId=o.id ");
        builder.append("where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "companyId"))) {
            builder.append(" and co.companyId = #{companyId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "orderId"))) {
            builder.append(" and co.orderId = #{orderId}");
        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "status"))) {
//            builder.append(" and u.status = #{status}");
//        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "fullName"))) {
//            builder.append(" and u.fullName like concat('%',#{fullName},'%')");
//        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
//            builder.append(" and u.idCardNo = #{idCardNo}");
//        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "companyId"))) {
//            builder.append(" and u.companyId = #{companyId}");
//        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "userNote"))) {
//            builder.append(" and u.userNote = #{userNote}");
//        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "roleId"))) {
//            builder.append(" and u.roleId = #{roleId}");
//        }
        builder.append(" order by co.updateTime desc");
        return builder.toString();
    }



}
