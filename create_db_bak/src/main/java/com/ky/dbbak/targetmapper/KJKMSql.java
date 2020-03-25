package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class KJKMSql extends BaseProvider {

    @Override
    protected String getTableName() {
        return "dbo.KJKM";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"XZQHDM", "XZQHMC", "KJND", "DWMC", "DWDM", "KJDZZBBH", "KJDZZBMC", "KJTX", "KJKMBM","KJKMMC","KMQC","KJKMJC","FZHSBZ","FZHSX","KMLBBH","KMLBMC","JLDWDM","YEFX","SJKMBM","SFZDJKM","SFXJHXJDJW"
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM KJKM where 1=1 ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "XZQHDM"))) {
            builder.append(" and XZQHDM=#{XZQHDM}");
        }
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM KJKM ");
        return builder.toString();
    }

}
