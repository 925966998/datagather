package com.ky.redwood.sql;

import com.ky.redwood.mybatis.BaseProvider;
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
        return new String[]{"userName", "password", "status", "fullName", "phone", "idCardNo"};
    }

    @Override
    protected String getDialect() {
        return "mysql";
    }

    @Override
    protected String _query(Map map) {
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
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
        /*if (map.containsKey("startTime") || map.containsKey("endTime")) {
            String startTime = format.format(new Date());
            String endTime = format.format(new Date());
            if (StringUtils.isBlank(MapUtils.getString(map, "startTime")))
                startTime = format.format(new Date());
            else
                startTime = map.get("startTime").toString();
            if (StringUtils.isBlank(MapUtils.getString(map, "endTime")))
                endTime = format.format(new Date());
            else
                endTime = map.get("endTime").toString();
            builder.append(" and paymentDate between " + startTime + " and " + endTime + "");
        }*/
        if (StringUtils.isNotBlank(MapUtils.getString(map, "sort")) && StringUtils.isNotBlank(MapUtils.getString(map, "order")))
            builder.append(" order by ").append(map.get("sort")).append(" ").append(map.get("order"));
        return builder.toString();
    }

}
