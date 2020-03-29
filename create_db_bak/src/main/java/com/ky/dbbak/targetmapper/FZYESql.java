package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class FZYESql extends BaseProvider {

    @Override
    protected String getTableName() {
        return "dbo.FZYE";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"KJYF","KJKMBM","FZLX","XZQHDM", "XZQHMC", "KJND", "DWMC", "DWDM", "KJDZZBBH", "KJDZZBMC",  "KJTX",  "KJKMMC", "KJKMQC", "NCJFYE", "NCDFYE",
                "NCYEFX", "QCJFYE", "QCDFYE", "QCYEFX", "JFFSE", "JFLJFSE", "DFFSE", "DFLJFSE", "QMJFYE", "QMDFYE", "QMYEFX", "QCWBJFYE", "QCWBDFYE", "JFWBFSE",
                "DFWBFSE", "QMWBJFYE", "QMWBDFYE", "KJKMJB", "SFZDJKM", "BZMC", "SJKMBM", "FLS",  "FZBM", "FZMC", "FZJB", "SJFZBM", "BZDM"
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZYE where 1=1 ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJDZZBBH"))) {
            builder.append(" and KJDZZBBH=#{KJDZZBBH}");
        }
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZYE ");
        return builder.toString();
    }

}
