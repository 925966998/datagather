package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class KmxxSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.GL_KMXX";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{

        };
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder(" select\n" +
                "        *\n" +
                "        from GL_KMXX where kmdm=#{kmdm}");
        return builder.toString();
    }

    //kmmc
    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder(" select * from GL_KMXX  where  CHARINDEX('2019',kjnd)=1 AND kmdm=#{kmdm}");
        return builder.toString();
    }

    //kmmc
    public String _queryGL_KMXX1(Map map) {
        StringBuilder builder = new StringBuilder("select kmmc from GL_KMXX  where  CHARINDEX('2019',kjnd)=1 ");
        if (MapUtils.getObject(map, "kmdms") != null) {
            builder.append(" and kmdm in (");
            if (map.get("kmdms") instanceof List) {
                List<String> menuIdList = (List) map.get("kmdms");
                for (String id : menuIdList) {
                    if (menuIdList.indexOf(id) > 0)
                        builder.append(",");
                    builder.append("'").append(id).append("'");
                }
            } else {
                builder.append(map.get("kmdms"));
            }
            builder.append(")");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "kjnd"))) {
            builder.append(" and kjnd=#{kjnd}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "gsdm"))) {
            builder.append(" and gsdm=#{gsdm}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZTH"))) {
            builder.append(" and ZTH=#{ZTH}");
        }
        builder.append(" ORDER BY kmdm");
        return builder.toString();
    }


    public String _queryKjnd(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "kjnd"))) {
            builder.append(" and kjnd=#{kjnd}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "gsdm"))) {
            builder.append(" and gsdm=#{gsdm}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZTH"))) {
            builder.append(" and ZTH=#{ZTH}");
        }
        return builder.toString();
    }

    public String _queryKmxz(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "kmdm"))) {
            builder.append(" and kmdm=#{kmdm}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "kjnd"))) {
            builder.append(" and kjnd=#{kjnd}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "gsdm"))) {
            builder.append(" and gsdm=#{gsdm}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "ZTH"))) {
            builder.append(" and ZTH=#{ZTH}");
        }
        return builder.toString();
    }

}
