package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class JZPZSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "JZPZ";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{
                "KJYF",
                "JZLXBH",
                "JZPZHH",
"XZQHDM",

"XZQHMC",

"KJND",

"DWMC",

"DWDM",

"KJDZZBBH",

"KJDZZBMC",

"JZPZRQ",


"JZLXMC",

"JZPZZL",

"JZPZBH",


"FLXH",

"JZPZZY",

"KJTX",

"KJKMBM",

"KJKMMC",

"KMQC",

"JFFSE",

"DFFSE",

"DFKMBM",

"DFKMMC",

"BZ",

"JFWBFSE",

"DFWBFSE",

"HL",

"SL",

"DJ",

"JSFS",

"FJS",

"ZDRY",

"FHRY",

"JZRY",

"CNRY",

"CWZG",

"YPZH",

"JZBZ",

"ZFBZ",

"SFJZ",

"SFWYSZ",

"ZFDJBH",

"GNKMDM",

"GNKMMC",

"JJKMDM",

"JJKMMC",

"ZJXZDM",

"ZJXZMC",

"ZBLYDM",

"ZBLYMC",

"ZCLXDM",

"ZCLXMC",

"YSGLLXDM",

"YSGLLXMC",

"ZFFSDM",

"ZFFSMC",

"YSXMDM",

"YSXMMC",

"XMFLDM",

"XMFLMC",

"ZBWHMC",

"JSFSDM",

"JSFSMC",
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
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JZPZRQ"))) {
        builder.append(" and JZPZRQ=#{JZPZRQ}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JZLXBH"))) {
        builder.append(" and JZLXBH=#{JZLXBH}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JZLXMC"))) {
        builder.append(" and JZLXMC=#{JZLXMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JZPZZL"))) {
        builder.append(" and JZPZZL=#{JZPZZL}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JZPZBH"))) {
        builder.append(" and JZPZBH=#{JZPZBH}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JZPZHH"))) {
        builder.append(" and JZPZHH=#{JZPZHH}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "FLXH"))) {
        builder.append(" and FLXH=#{FLXH}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JZPZZY"))) {
        builder.append(" and JZPZZY=#{JZPZZY}");
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
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JFFSE"))) {
        builder.append(" and JFFSE=#{JFFSE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "DFFSE"))) {
        builder.append(" and DFFSE=#{DFFSE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "DFKMBM"))) {
        builder.append(" and DFKMBM=#{DFKMBM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "DFKMMC"))) {
        builder.append(" and DFKMMC=#{DFKMMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "BZ"))) {
        builder.append(" and BZ=#{BZ}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JFWBFSE"))) {
        builder.append(" and JFWBFSE=#{JFWBFSE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "DFWBFSE"))) {
        builder.append(" and DFWBFSE=#{DFWBFSE}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "HL"))) {
        builder.append(" and HL=#{HL}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "SL"))) {
        builder.append(" and SL=#{SL}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "DJ"))) {
        builder.append(" and DJ=#{DJ}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JSFS"))) {
        builder.append(" and JSFS=#{JSFS}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "FJS"))) {
        builder.append(" and FJS=#{FJS}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZDRY"))) {
        builder.append(" and ZDRY=#{ZDRY}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "FHRY"))) {
        builder.append(" and FHRY=#{FHRY}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JZRY"))) {
        builder.append(" and JZRY=#{JZRY}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "CNRY"))) {
        builder.append(" and CNRY=#{CNRY}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "CWZG"))) {
        builder.append(" and CWZG=#{CWZG}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "YPZH"))) {
        builder.append(" and YPZH=#{YPZH}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JZBZ"))) {
        builder.append(" and JZBZ=#{JZBZ}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZFBZ"))) {
        builder.append(" and ZFBZ=#{ZFBZ}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "SFJZ"))) {
        builder.append(" and SFJZ=#{SFJZ}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "SFWYSZ"))) {
        builder.append(" and SFWYSZ=#{SFWYSZ}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZFDJBH"))) {
        builder.append(" and ZFDJBH=#{ZFDJBH}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "GNKMDM"))) {
        builder.append(" and GNKMDM=#{GNKMDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "GNKMMC"))) {
        builder.append(" and GNKMMC=#{GNKMMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JJKMDM"))) {
        builder.append(" and JJKMDM=#{JJKMDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JJKMMC"))) {
        builder.append(" and JJKMMC=#{JJKMMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZJXZDM"))) {
        builder.append(" and ZJXZDM=#{ZJXZDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZJXZMC"))) {
        builder.append(" and ZJXZMC=#{ZJXZMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZBLYDM"))) {
        builder.append(" and ZBLYDM=#{ZBLYDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZBLYMC"))) {
        builder.append(" and ZBLYMC=#{ZBLYMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZCLXDM"))) {
        builder.append(" and ZCLXDM=#{ZCLXDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZCLXMC"))) {
        builder.append(" and ZCLXMC=#{ZCLXMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "YSGLLXDM"))) {
        builder.append(" and YSGLLXDM=#{YSGLLXDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "YSGLLXMC"))) {
        builder.append(" and YSGLLXMC=#{YSGLLXMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZFFSDM"))) {
        builder.append(" and ZFFSDM=#{ZFFSDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZFFSMC"))) {
        builder.append(" and ZFFSMC=#{ZFFSMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "YSXMDM"))) {
        builder.append(" and YSXMDM=#{YSXMDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "YSXMMC"))) {
        builder.append(" and YSXMMC=#{YSXMMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "XMFLDM"))) {
        builder.append(" and XMFLDM=#{XMFLDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "XMFLMC"))) {
        builder.append(" and XMFLMC=#{XMFLMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZBWHMC"))) {
        builder.append(" and ZBWHMC=#{ZBWHMC}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JSFSDM"))) {
        builder.append(" and JSFSDM=#{JSFSDM}");
        }
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "JSFSMC"))) {
        builder.append(" and JSFSMC=#{JSFSMC}");
        }
        return builder.toString();
    }

}
