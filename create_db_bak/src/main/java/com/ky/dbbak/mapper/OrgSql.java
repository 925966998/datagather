package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class OrgSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.org";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"id", "orgName","areaName", "areaCode", "orgLevel",
                "pid", "orgCode", "remark", "areaId", "cityId", "provinceId",
                "zt", "hyfl", "ztlx", "kjnd", "zzjgdm", "dwxz", "kfdw", "bbh",
                "bwb", "sfhyysz","kjdzzbbh"
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select org.*,a.areaName as areaName from " + getTableName() + " org left join area a on org.areaCode=a.areaCode where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "orgName"))) {
            builder.append(" and org.orgName=#{orgName}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "likeorgName"))) {
            builder.append(" and org.orgName like '%"+map.get("likeorgName")+"%'");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "areaName"))) {
            builder.append(" and a.areaName=#{areaName}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "areaCode"))) {
            builder.append(" and org.areaCode=#{areaCode}");
        }

        if (StringUtils.isNotEmpty(MapUtils.getString(map, "areaId"))) {
            builder.append(" and org.areaId=#{areaId}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "pid"))) {
            builder.append(" and org.pid=#{pid}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "orgCode"))) {
            builder.append(" and org.orgCode=#{orgCode}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "likeorgCode"))) {
            builder.append(" and org.orgCode like '%"+map.get("likeorgCode")+"%'");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "orgLevel"))) {
            builder.append(" and org.orgLevel=#{orgLevel}");
        }

        builder.append(" order by orgCode desc");
        return builder.toString();
    }

}
