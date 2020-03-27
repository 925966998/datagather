package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class KMNCSSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "KMNCS";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
                "KJYF",

                "KJKMBM",
"XZQHDM",

"XZQHMC",

"KJND",

"DWMC",

"DWDM",

"KJDZZBBH",

"KJDZZBMC",


"KJTX",



"KJKMMC",

"KMQC",

"KJKMJC",

"SFZDJKM",

"SJKMBM",

"SFXJHXJDJW",

"BZMC",

"YEFX",

"BBQCYE",

"QCSL",

"WBQCYE",
};


    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "XZQHDM"))) {
        builder.append(" and XZQHDM=#{XZQHDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "XZQHMC"))) {
        builder.append(" and XZQHMC=#{XZQHMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJND"))) {
        builder.append(" and KJND=#{KJND}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "DWMC"))) {
        builder.append(" and DWMC=#{DWMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "DWDM"))) {
        builder.append(" and DWDM=#{DWDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJDZZBBH"))) {
        builder.append(" and KJDZZBBH=#{KJDZZBBH}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJDZZBMC"))) {
        builder.append(" and KJDZZBMC=#{KJDZZBMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJYF"))) {
        builder.append(" and KJYF=#{KJYF}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJTX"))) {
        builder.append(" and KJTX=#{KJTX}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJKMBM"))) {
        builder.append(" and KJKMBM=#{KJKMBM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJKMMC"))) {
        builder.append(" and KJKMMC=#{KJKMMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KMQC"))) {
        builder.append(" and KMQC=#{KMQC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJKMJC"))) {
        builder.append(" and KJKMJC=#{KJKMJC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "SFZDJKM"))) {
        builder.append(" and SFZDJKM=#{SFZDJKM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "SJKMBM"))) {
        builder.append(" and SJKMBM=#{SJKMBM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "SFXJHXJDJW"))) {
        builder.append(" and SFXJHXJDJW=#{SFXJHXJDJW}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "BZMC"))) {
        builder.append(" and BZMC=#{BZMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "YEFX"))) {
        builder.append(" and YEFX=#{YEFX}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "BBQCYE"))) {
        builder.append(" and BBQCYE=#{BBQCYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "QCSL"))) {
        builder.append(" and QCSL=#{QCSL}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "WBQCYE"))) {
        builder.append(" and WBQCYE=#{WBQCYE}");
        }
        return builder.toString();
    }

}
