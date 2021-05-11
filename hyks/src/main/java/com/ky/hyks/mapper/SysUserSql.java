package com.ky.hyks.mapper;


import com.ky.hyks.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class SysUserSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "KY_HYKS_sys_user";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"userName", "fullName", "password", "status", "phone", "roleId", "companyId", "userNote"};
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select u.*,r.roleName as roleName from KY_HYKS_sys_user u left join KY_HYKS_role r on u.roleId=r.id   where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userName"))) {
            builder.append(" and u.userName like concat('%',#{userName},'%')");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "status"))) {
            builder.append(" and u.status = #{status}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "fullName"))) {
            builder.append(" and u.fullName like concat('%',#{fullName},'%')");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
            builder.append(" and u.idCardNo = #{idCardNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "companyId"))) {
            builder.append(" and u.companyId = #{companyId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userNote"))) {
            builder.append(" and u.userNote = #{userNote}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "roleId"))) {
            builder.append(" and u.roleId = #{roleId}");
        }
        builder.append(" order by u.updateTime desc");
        return builder.toString();
    }

    @Override
    public String _queryPage(Map map) {
        StringBuilder builder = new StringBuilder("select u.*,r.roleName as roleName from KY_HYKS_sys_user u left join KY_HYKS_role r on u.roleId=r.id   where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userName"))) {
            builder.append(" and u.userName like concat('%',#{userName},'%')");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "status"))) {
            builder.append(" and u.status = #{status}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "fullName"))) {
            builder.append(" and u.fullName like concat('%',#{fullName},'%')");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
            builder.append(" and u.idCardNo = #{idCardNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "companyId"))) {
            builder.append(" and u.companyId = #{companyId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userNote"))) {
            builder.append(" and u.userNote = #{userNote}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "roleId"))) {
            builder.append(" and u.roleId = #{roleId}");
        }
        builder.append(" order by u.updateTime desc");
//        builder.append(this.pageHelp(MapUtils.getLongValue(map, "page"), MapUtils.getLongValue(map, "rows")));
        return builder.toString();
    }

//    public StringBuilder pageHelp(long currentPage, long pageSize) {
//        long count = (currentPage - 1) * pageSize;
//        if (count != 0) {
//            count = count;
//        }
//        StringBuilder builder = new StringBuilder(" rownum between ");
//        builder.append(count);
//        builder.append(" and");
//        builder.append(pageSize);
//        return builder;
//    }

}
