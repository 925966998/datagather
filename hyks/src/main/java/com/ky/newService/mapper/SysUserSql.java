package com.ky.newService.mapper;


import com.ky.newService.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class SysUserSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "sys_user";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"userName", "fullName","password", "status","phone", "roleId", "companyId","userNote"};
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select u.*,r.roleName as roleName,st.supplierType as supplierType from sys_user u left join role r on u.roleId=r.id  left join supplier_type st on u.companyId=st.id  where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userName"))) {
            builder.append(" and userName like concat('%',#{userName},'%')");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "status"))) {
            builder.append(" and status = #{status}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "fullName"))) {
            builder.append(" and fullName like concat('%',#{fullName},'%')");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
            builder.append(" and idCardNo = #{idCardNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "companyId"))) {
            builder.append(" and companyId = #{companyId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userNote"))) {
            builder.append(" and userNote = #{userNote}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "roleId"))) {
            builder.append(" and roleId = #{roleId}");
        }
        builder.append(" order by updateTime desc");
        return builder.toString();
    }

    @Override
    public String _queryPage(Map map) {
        StringBuilder builder = new StringBuilder("select u.*,r.roleName as roleName, st.supplierType as supplierType from sys_user u left join role r on u.roleId=r.id left join supplier_type st on u.companyId=st.id where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userName"))) {
            builder.append(" and userName like concat('%',#{userName},'%')");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "status"))) {
            builder.append(" and status = #{status}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "fullName"))) {
            builder.append(" and fullName like concat('%',#{fullName},'%')");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "idCardNo"))) {
            builder.append(" and idCardNo = #{idCardNo}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "companyId"))) {
            builder.append(" and companyId = #{companyId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userNote"))) {
            builder.append(" and userNote = #{userNote}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "roleId"))) {
            builder.append(" and roleId = #{roleId}");
        }
        builder.append(" order by updateTime desc");
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
