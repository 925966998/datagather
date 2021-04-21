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
public class SupplierUserSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "supplier_user";
    }

    @Override
    protected String[] getColumns() {
        return new String[]{"supplierManageId", "userId", "state","supplierTypeId"};
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select su.*,st.supplierType as supplierTypeName,sm.code as code,sm.name as name, sm.taxNum as taxNum,sm.telePhone as telePhone,sm.legalPerson as legalPerson,sm.contact as contact,sm.state as supplierState from supplier_user su ");
        builder.append("left join supplier_manage sm on su.supplierManageId=sm.id ");
        builder.append("left join supplier_type st on su.supplierTypeId=st.id ");
        builder.append("left join sys_user s on su.userId = s.id  where 1=1 and su.logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierManageId"))) {
            builder.append(" and sm.name like concat('%',#{supplierManageId},'%') ");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userId"))) {
            builder.append(" and su.userId = #{userId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "state"))) {
            builder.append(" and su.state = #{state}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierTypeId"))) {
            builder.append(" and su.supplierTypeId = #{supplierTypeId}");
        }
        builder.append(" order by createTime desc");
        return builder.toString();
    }

    @Override
    public String _queryPage(Map map) {
        StringBuilder builder = new StringBuilder("select su.*,st.supplierType as supplierTypeName,sm.code as code,sm.name as name, sm.taxNum as taxNum,sm.telePhone as telePhone,sm.legalPerson as legalPerson,sm.contact as contact,sm.state as supplierState  from supplier_user su ");
        builder.append("left join supplier_manage sm on su.supplierManageId=sm.id ");
        builder.append("left join supplier_type st on su.supplierTypeId=st.id ");
        builder.append("left join sys_user s on su.userId = s.id  where 1=1 and su.logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierManageId"))) {
            builder.append(" and sm.name like concat('%',#{supplierManageId},'%') ");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userId"))) {
            builder.append(" and su.userId = #{userId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "state"))) {
            builder.append(" and su.state = #{state}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierTypeId"))) {
            builder.append(" and su.supplierTypeId = #{supplierTypeId}");
        }
        builder.append(" order by createTime desc");
        builder.append(this.pageHelp(MapUtils.getLongValue(map, "page"), MapUtils.getLongValue(map, "rows")));
        return builder.toString();
    }

    @Override
    public String _queryCount(Map map) {
        StringBuilder builder = new StringBuilder("select count(1) from ( select su.*,st.supplierType as supplierTypeName,sm.code as code,sm.name as name, sm.taxNum as taxNum,sm.telePhone as telePhone,sm.legalPerson as legalPerson,sm.contact as contact,sm.state as supplierState  from supplier_user su ");
        builder.append("left join supplier_manage sm on su.supplierManageId=sm.id ");
        builder.append("left join supplier_type st on su.supplierTypeId=st.id ");
        builder.append("left join sys_user s on su.userId = s.id  where 1=1 and su.logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierManageId"))) {
            builder.append(" and sm.name like concat('%',#{supplierManageId},'%') ");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userId"))) {
            builder.append(" and su.userId = #{userId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "state"))) {
            builder.append(" and su.state = #{state}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierTypeId"))) {
            builder.append(" and su.supplierTypeId = #{supplierTypeId}");
        }
        builder.append(" ) re");
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
