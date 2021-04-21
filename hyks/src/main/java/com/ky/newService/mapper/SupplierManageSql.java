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
public class SupplierManageSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "supplier_manage";
    }

    @Override
    protected String[] getColumns() {
        return new String[]{"code", "name", "supplierTypeId", "taxNum", "legalPerson", "phone", "telePhone",
                "recordDate", "violationId", "qualificationId", "contact", "userId","supplierMark","state"};
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select sm.*,st.supplierType as supplierTypeName from supplier_manage sm left join supplier_type st on sm.supplierTypeId = st.id ");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("0")) {
            builder.append(" RIGHT JOIN ( SELECT * FROM supplier_user WHERE state = '0' ) su ON su.supplierManageId = sm.id");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("1")) {
            builder.append(" RIGHT JOIN supplier_user su ON su.supplierManageId = sm.id");
        }
        builder.append(" where 1=1 and sm.logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "code"))) {
            builder.append(" and sm.code = #{code}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "name"))) {
            builder.append(" and sm.name like concat('%',#{name},'%')");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierTypeId"))) {
            builder.append(" and sm.supplierTypeId = #{supplierTypeId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "taxNum"))) {
            builder.append(" and sm.taxNum = #{taxNum}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "legalPerson"))) {
            builder.append(" and sm.legalPerson =#{legalPerson}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "phone"))) {
            builder.append(" and sm.phone = #{phone}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "telePhone"))) {
            builder.append(" and sm.telePhone = #{telePhone}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "recordDate"))) {
            builder.append(" and sm.recordDate = #{recordDate}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "violationId"))) {
            builder.append(" and sm.violationId = #{violationId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "qualificationId"))) {
            builder.append(" and sm.qualificationId = #{qualificationId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "contact"))) {
            builder.append(" and sm.contact = #{contact}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userId"))) {
            builder.append(" and sm.userId = #{userId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierMark"))) {
            builder.append(" and sm.supplierMark = #{supplierMark}");
        }
        builder.append(" order by createTime desc");
        return builder.toString();
    }

    @Override
    public String _queryPage(Map map) {
        StringBuilder builder = new StringBuilder("select sm.*,st.supplierType as supplierTypeName from supplier_manage sm left join supplier_type st on sm.supplierTypeId = st.id ");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("0")) {
            builder.append(" RIGHT JOIN ( SELECT * FROM supplier_user WHERE state = '0' ) su ON su.supplierManageId = sm.id");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("1")) {
            builder.append(" RIGHT JOIN supplier_user su ON su.supplierManageId = sm.id");
        }
        builder.append(" where 1=1 and sm.logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "code"))) {
            builder.append(" and sm.code = #{code}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "name"))) {
            builder.append(" and sm.name like concat('%',#{name},'%')");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierTypeId"))) {
            builder.append(" and sm.supplierTypeId = #{supplierTypeId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "taxNum"))) {
            builder.append(" and sm.taxNum = #{taxNum}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "legalPerson"))) {
            builder.append(" and sm.legalPerson =#{legalPerson}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "phone"))) {
            builder.append(" and sm.phone = #{phone}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "telePhone"))) {
            builder.append(" and sm.telePhone = #{telePhone}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "recordDate"))) {
            builder.append(" and sm.recordDate = #{recordDate}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "violationId"))) {
            builder.append(" and sm.violationId = #{violationId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "qualificationId"))) {
            builder.append(" and sm.qualificationId = #{qualificationId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "contact"))) {
            builder.append(" and sm.contact = #{contact}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userId"))) {
            builder.append(" and sm.userId = #{userId}");
        }
        builder.append(" order by createTime desc");
        return builder.toString();
    }

    @Override
    public String _queryCount(Map map) {
        StringBuilder builder = new StringBuilder("select count(1) from " + this.getTableName() + " where 1=1 and logicalDel=0");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "code"))) {
            builder.append(" and code = #{code}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "name"))) {
            builder.append(" and name = #{name}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierTypeId"))) {
            builder.append(" and supplierTypeId = #{supplierTypeId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "taxNum"))) {
            builder.append(" and taxNum = #{taxNum}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "legalPerson"))) {
            builder.append(" and legalPerson = #{legalPerson}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "phone"))) {
            builder.append(" and phone = #{phone}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "telePhone"))) {
            builder.append(" and telePhone = #{telePhone}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "recordDate"))) {
            builder.append(" and recordDate = #{recordDate}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "violationId"))) {
            builder.append(" and violationId = #{violationId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "qualificationId"))) {
            builder.append(" and qualificationId = #{qualificationId}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "contact"))) {
            builder.append(" and contact = #{contact}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "userId"))) {
            builder.append(" and userId = #{userId}");
        }
        builder.append(" order by createTime desc");
        return builder.toString();
    }

    public String _queryContentCount(Map map) {
        StringBuilder builder = new StringBuilder(" SELECT count(1) FROM (SELECT sm.*,st.supplierType AS supplierTypeName FROM ");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("0")) {
            builder.append(" (select * from supplier_manage where state='0')  sm ");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("1")) {
            builder.append("( SELECT * from supplier_user WHERE supplierTypeId='" + map.get("supplierTypeId") + "') su LEFT JOIN (select * from  supplier_manage where state='0')  sm on su.supplierManageId = sm.id ");
        }
        builder.append(" LEFT JOIN supplier_type st ON sm.supplierTypeId = st.id where 1=1 ");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "queryContent"))) {
            builder.append(" and CONCAT(sm.code,sm.name,sm.legalPerson,sm.telePhone,sm.contact,sm.supplierMark) LIKE '%" + map.get("queryContent") + "%'");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierType"))) {
            builder.append(" and sm.supplierTypeId = #{supplierType}");
        }
        builder.append(" order by sm.createTime desc");
        builder.append(") re ");
        return builder.toString();
    }


    public String queryContent(Map map) {
        StringBuilder builder = new StringBuilder("SELECT sm.*,st.supplierType AS supplierTypeName from");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("0")) {
            builder.append("  (select * from supplier_manage where state='0')  sm ");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("1")) {
            builder.append("  ( SELECT * from supplier_user WHERE supplierTypeId='" + map.get("supplierTypeId") + "') su LEFT JOIN (select * from  supplier_manage where state='0') sm on su.supplierManageId = sm.id ");
        }
        builder.append(" LEFT JOIN supplier_type st ON sm.supplierTypeId = st.id  where 1=1 ");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "queryContent"))) {
            builder.append(" and CONCAT(sm.code,sm.name,sm.legalPerson,sm.telePhone,sm.contact,sm.supplierMark) LIKE '%" + map.get("queryContent") + "%'");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierType"))) {
            builder.append(" and sm.supplierTypeId = #{supplierType}");
        }
        builder.append(" order by sm.createTime desc");
        builder.append(this.pageHelp(MapUtils.getLongValue(map, "page"), MapUtils.getLongValue(map, "rows")));
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

    public String _queryPreContentCount(Map map) {
        StringBuilder builder = new StringBuilder(" SELECT count(1) FROM (SELECT sm.*,st.supplierType AS supplierTypeName FROM ");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("2")) {
            builder.append(" (select * from supplier_manage where state='1')  sm ");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("3")) {
            builder.append("( SELECT * from supplier_user WHERE supplierTypeId='" + map.get("supplierTypeId") + "') su LEFT JOIN (select * from  supplier_manage where state='1') sm on su.supplierManageId = sm.id ");
        }
        builder.append(" LEFT JOIN supplier_type st ON sm.supplierTypeId = st.id where 1=1 ");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "queryContent"))) {
            builder.append(" and CONCAT(sm.code,sm.name,sm.legalPerson,sm.telePhone,sm.contact,sm.supplierMark) LIKE '%" + map.get("queryContent") + "%'");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierType"))) {
            builder.append(" and sm.supplierTypeId = #{supplierType}");
        }
        builder.append(" order by sm.createTime desc");
        builder.append(") re ");
        return builder.toString();
    }


    public String queryPreContent(Map map) {
        StringBuilder builder = new StringBuilder("SELECT sm.*,st.supplierType AS supplierTypeName from");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("2")) {
            builder.append("  (select * from supplier_manage where state='1')  sm ");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "flag")) && map.get("flag").equals("3")) {
            builder.append("  ( SELECT * from supplier_user WHERE supplierTypeId='" + map.get("supplierTypeId") + "') su LEFT JOIN (select * from  supplier_manage where state='1') sm on su.supplierManageId = sm.id ");
        }
        builder.append(" LEFT JOIN supplier_type st ON sm.supplierTypeId = st.id  where 1=1 ");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "queryContent"))) {
            builder.append(" and CONCAT(sm.code,sm.name,sm.legalPerson,sm.telePhone,sm.contact,sm.supplierMark) LIKE '%" + map.get("queryContent") + "%'");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supplierType"))) {
            builder.append(" and sm.supplierTypeId = #{supplierType}");
        }
        builder.append(" order by sm.createTime desc");
        builder.append(this.pageHelp(MapUtils.getLongValue(map, "page"), MapUtils.getLongValue(map, "rows")));
        return builder.toString();
    }

}
