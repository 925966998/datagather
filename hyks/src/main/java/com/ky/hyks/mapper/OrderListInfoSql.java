package com.ky.hyks.mapper;


import com.ky.hyks.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class OrderListInfoSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "KY_HYKS_order_list_info";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"orderInfoId", "orderListId"};
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select li.*,l.listName as orderListName,i.matterName as orderInfoName,i.nastNum as totalAmount,i.matterType as unit,i.matterSpec as specs from KY_HYKS_order_list_info  li ");
        builder.append(" left join KY_HYKS_orderList l on li.orderListId= l.id");
        builder.append(" left join KY_HYKS_orderInfo i on li.orderInfoId= i.id");
        builder.append(" where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "orderInfoId"))) {
            builder.append(" and li.orderInfoId = #{orderInfoId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "orderListId"))) {
            builder.append(" and li.orderListId = #{orderListId}");
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
        builder.append(" order by li.createTime desc");
        return builder.toString();
    }

    @Override
    public String _queryPage(Map map) {
        StringBuilder builder = new StringBuilder("select li.*,l.listName as orderListName,i.matterName as orderInfoName,i.nastNum as totalAmount,i.matterType as unit,i.matterSpec as specs from KY_HYKS_order_list_info  li ");
        builder.append(" left join KY_HYKS_orderList l on li.orderListId= l.id");
        builder.append(" left join KY_HYKS_orderInfo i on li.orderInfoId= i.id");
        builder.append(" where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "orderInfoId"))) {
            builder.append(" and li.orderInfoId = #{orderInfoId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "orderListId"))) {
            builder.append(" and li.orderListId = #{orderListId}");
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
        builder.append(" order by li.createTime desc");
//        builder.append(this.pageHelp(MapUtils.getLongValue(map, "page"), MapUtils.getLongValue(map, "rows")));
        return builder.toString();
    }


}
