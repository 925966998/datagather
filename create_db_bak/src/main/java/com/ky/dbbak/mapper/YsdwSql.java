package com.ky.dbbak.mapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class YsdwSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.YSDW";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"XZQHDM", "XZQHMC", "KJND", "DWMC", "DWDM", "ZZJGDM", "SJDM", "DMJC", "SFMJ", "XZJB", "DWXZ", "YSGLFS", "DWLB", "ZGKSDM",
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from YSDW where 1=1 ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "XZQHDM"))) {
            builder.append(" and XZQHDM=#{XZQHDM}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "DWDM"))) {
            builder.append(" and DWDM=#{DWDM}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "DWMC"))) {
            builder.append(" and DWMC=#{DWMC}");
        }
        return builder.toString();
    }

    public String _queryYsdw(Map map) {
        StringBuilder builder = new StringBuilder("select * from YSDW where 1=1 ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "XZQHDM"))) {
            builder.append(" and XZQHDM=#{XZQHDM}");
        }
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("select * from YSDW");
        return builder.toString();
    }

    public String deleteYsdw(String dwdm, String dwmc, String xzqhdm) {
        StringBuilder builder = new StringBuilder("delete from dbo.YSDW where DWDM='" + dwdm + "' and DWMC='" + dwmc + "' and XZQHDM='" + xzqhdm + "'");
        return builder.toString();
    }

}
