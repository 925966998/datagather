package com.ky.hyks.mapper;


import com.ky.hyks.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class OrderInfoSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "KY_HYKS_orderInfo";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"code","matterType","pk_order",
        "matterSpec","matterName","marbasClassCode","marbasClassName","nastNum","pk_group","dbillDate","state"};
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from KY_HYKS_orderInfo  where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "code"))) {
            builder.append(" and code = #{code}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "matterType"))) {
            builder.append(" and matterType like '%"+map.get("matterType")+"%'");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "state"))) {
            builder.append(" and state = #{state}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "matterName"))) {
            builder.append(" and matterName = #{matterName}");
        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "companyId"))) {
//            builder.append(" and u.companyId = #{companyId}");
//        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "userNote"))) {
//            builder.append(" and u.userNote = #{userNote}");
//        }
//        if (StringUtils.isNotBlank(MapUtils.getString(map, "roleId"))) {
//            builder.append(" and u.roleId = #{roleId}");
//        }
        builder.append(" order by createTime desc");
        return builder.toString();
    }

    @Override
    public String _queryPage(Map map) {
        StringBuilder builder = new StringBuilder("select * from KY_HYKS_orderInfo  where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "code"))) {
            builder.append(" and code = #{code}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "matterType"))) {
            builder.append(" and matterType like '%"+map.get("matterType")+"%'");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "state"))) {
            builder.append(" and state = #{state}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "matterName"))) {
            builder.append(" and matterName = #{matterName}");
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
        builder.append(" order by updateTime desc");
//        builder.append(this.pageHelp(MapUtils.getLongValue(map, "page"), MapUtils.getLongValue(map, "rows")));
        return builder.toString();
    }


}
