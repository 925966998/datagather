package com.ky.dbbak.sourcemapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class PubbmxxSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return "dbo.PUBBMXX";
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
        StringBuilder builder = new StringBuilder("SELECT * FROM PUBBMXX where bmdm=#{bmdm}");
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT bmdm,bmmc FROM PUBBMXX where bmdm=#{bmdm}");
        return builder.toString();
    }

    public String _queryKjnd(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "kjnd"))) {
            builder.append(" and kjnd=#{kjnd}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "gsdm"))) {
            builder.append(" and gsdm=#{gsdm}");
        }
        return builder.toString();
    }

    public String _queryPubbmxx(Map map) {
        StringBuilder builder = new StringBuilder("select * from " + getTableName() + " where 1=1");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "bmdm"))) {
            builder.append(" and bmdm=#{bmdm}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "kjnd"))) {
            builder.append(" and kjnd=#{kjnd}");
        }if (StringUtils.isNotEmpty(MapUtils.getString(map, "gsdm"))) {
            builder.append(" and gsdm=#{gsdm}");
        }
        return builder.toString();
    }
}
