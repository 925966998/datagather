package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class KMYESql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "KMYE";
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

"NCJFYE",

"NCDFYE",

"NCYEFX",

"QCJFYE",

"QCDFYE",

"QCYEFX",

"WBNCJFYE",

"WBNCDFYE",

"WBQCJFYE",

"WBQCDFYE",

"JFFSE",

"JFLJFSE",

"WBJFFSE",

"WBJFLJFSE",

"DFFSE",

"DFLJFSE",

"WBDFFSE",

"WBDFLJFSE",

"QMJFYE",

"QMDFYE",

"QMYEFX",

"WBQMJFYE",

"WBQMDFYE",

"FLS",

"KJKMJB",

"SFZDJKM",

"SJKMBM",

"SFXJHXJDJW",

"BZMC",

"BZDM",
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
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "NCJFYE"))) {
        builder.append(" and NCJFYE=#{NCJFYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "NCDFYE"))) {
        builder.append(" and NCDFYE=#{NCDFYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "NCYEFX"))) {
        builder.append(" and NCYEFX=#{NCYEFX}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "QCJFYE"))) {
        builder.append(" and QCJFYE=#{QCJFYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "QCDFYE"))) {
        builder.append(" and QCDFYE=#{QCDFYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "QCYEFX"))) {
        builder.append(" and QCYEFX=#{QCYEFX}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "WBNCJFYE"))) {
        builder.append(" and WBNCJFYE=#{WBNCJFYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "WBNCDFYE"))) {
        builder.append(" and WBNCDFYE=#{WBNCDFYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "WBQCJFYE"))) {
        builder.append(" and WBQCJFYE=#{WBQCJFYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "WBQCDFYE"))) {
        builder.append(" and WBQCDFYE=#{WBQCDFYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JFFSE"))) {
        builder.append(" and JFFSE=#{JFFSE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JFLJFSE"))) {
        builder.append(" and JFLJFSE=#{JFLJFSE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "WBJFFSE"))) {
        builder.append(" and WBJFFSE=#{WBJFFSE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "WBJFLJFSE"))) {
        builder.append(" and WBJFLJFSE=#{WBJFLJFSE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "DFFSE"))) {
        builder.append(" and DFFSE=#{DFFSE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "DFLJFSE"))) {
        builder.append(" and DFLJFSE=#{DFLJFSE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "WBDFFSE"))) {
        builder.append(" and WBDFFSE=#{WBDFFSE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "WBDFLJFSE"))) {
        builder.append(" and WBDFLJFSE=#{WBDFLJFSE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "QMJFYE"))) {
        builder.append(" and QMJFYE=#{QMJFYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "QMDFYE"))) {
        builder.append(" and QMDFYE=#{QMDFYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "QMYEFX"))) {
        builder.append(" and QMYEFX=#{QMYEFX}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "WBQMJFYE"))) {
        builder.append(" and WBQMJFYE=#{WBQMJFYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "WBQMDFYE"))) {
        builder.append(" and WBQMDFYE=#{WBQMDFYE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "FLS"))) {
        builder.append(" and FLS=#{FLS}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJKMJB"))) {
        builder.append(" and KJKMJB=#{KJKMJB}");
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
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "BZDM"))) {
        builder.append(" and BZDM=#{BZDM}");
        }
        return builder.toString();
    }

}
