package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @ClassName TargetSql
 * @Description: TODO
 * @Author czw
 * @Date 2020/3/23
 **/
public class TargetSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return null;
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"strName", "strDes"
        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        return null;
    }

    public String truncate(Map map) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "tableName")) && StringUtils.isNotEmpty(MapUtils.getString(map, "KJDZZBBH"))) {
            builder = new StringBuilder("delete from " + map.get("tableName") + " where 1=1 and KJDZZBBH = '" + map.get("KJDZZBBH")+"'");
        }
        return builder.toString();
    }
    public String truncateYsdw(Map map) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "tableName")) && StringUtils.isNotEmpty(MapUtils.getString(map, "XZQHDM"))) {
            builder = new StringBuilder("delete from " + map.get("tableName") + " where 1=1 and XZQHDM = " + map.get("XZQHDM") +" and DWMC='"+map.get("DWMC")+"' and DWDM='"+map.get("DWDM")+"'");
        }
        return builder.toString();
    }

    public String _queryDescription(Map map) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "tableName"))) {
            builder = new StringBuilder("SELECT " +
                    "    strName=a.name, " +
                    "    strDes=isnull(g.[value],'') " +
                    " FROM   syscolumns   a " +
                    "    left join   systypes   b   on   a.xusertype=b.xusertype" +
                    "    inner join   sysobjects   d   on   a.id=d.id     and   d.xtype='U'   and     d.name<>'dtproperties'" +
                    "    left join   syscomments   e   on   a.cdefault=e.id" +
                    "    left join   sys.extended_properties   g   on   a.id=g.major_id   and   a.colid=g.minor_id" +
                    "    left join   sys.extended_properties   f   on   d.id=f.major_id   and   f.minor_id=0" +
                    " where d.name='" + map.get("tableName") + "' " +
                    " order by a.id,a.colorder ");
        }

        return builder.toString();
    }

}
