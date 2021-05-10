package com.ky.hyks.mapper;


import com.ky.hyks.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class CompanySql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "bd_supplier";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"pk_supplier", "code", "legalbody", "name", "shortname", "supprop", "memo", "buslicensenum",
                "taxpayerid", "corpaddress", "enablestate", "supstate", "mnecode", "pk_supplierclass", "pk_suptaxes", "cellNum", "tellPhone", "cellName"};
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select bds.pk_supplier as pk_supplier,bds.code as code,bds.legalbody as legalbody,\n" +
                "bds.name as name ,bds.shortname as shortname ,bds.supprop as supprop ,bds.memo as memo,\n" +
                "bds.buslicensenum as buslicensenum,bds.taxpayerid as taxpayerid,bda.detailinfo as corpaddress,\n" +
                "bds.enablestate as enablestate,bds.supstate as supstate,bds.mnecode as mnecode,bdsc.name as pk_supplierclass,\n" +
                "bdsu.suppliername as pk_suptaxes,s.cell as cellNum,s.phone as tellPhone,s.name as cellName from bd_supplier bds  ");
        builder.append("LEFT JOIN bd_address bda ON bds.CORPADDRESS = bda.pk_address ");
        builder.append("LEFT JOIN bd_supplierclass bdsc on bds.pk_supplierclass=bdsc.pk_supplierclass ");
        builder.append("LEFT JOIN bd_suptaxes bdsu on bds.pk_suptaxes=bdsu.pk_suptaxes ");
        builder.append("LEFT JOIN ( SELECT bdlm.cell AS cell, bdlm.phone AS phone, bdlm.name AS name, bdli.PK_SUPPLIER AS pk_supplier FROM bd_suplinkman bdli LEFT JOIN BD_LINKMAN bdlm ON bdlm.pk_linkman = bdli.pk_linkman) s ON s.pk_supplier = bds.pk_supplier ");
        builder.append("where 1=1");
        if (StringUtils.isNotBlank(MapUtils.getString(map, "name"))) {
            builder.append(" and bds.name like '%"+map.get("name")+"%'");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supprop"))) {
            builder.append(" and bds.supprop = #{supprop}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "enablestate"))) {
            builder.append(" and bds.enablestate = #{enablestate}");
        }
        if (StringUtils.isNotBlank(MapUtils.getString(map, "supstate"))) {
            builder.append(" and bds.supstate = #{supstate}");
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
        builder.append(" order by bds.code ASC");
        return builder.toString();
    }
}
