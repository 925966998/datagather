package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseProvider;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

public class FzxxSql extends BaseProvider {

    @Override
    protected String getTableName() {
        return "dbo.FZXX";
    }

    // 涉及到插入和更新的字段，不在该定义中的字段不会被操作
    @Override
    protected String[] getColumns() {
        return new String[]{"FZLX", "FZBM", "XZQHDM", "XZQHMC", "KJND", "DWMC", "DWDM", "KJDZZBBH", "KJDZZBMC", "FZMC",
                "FZQC", "FZJC", "SJFZBM", "FZSM", "SFWYSFZ"};
    }

    @Override
    protected String getDialect() {
        return "sqlserver";
    }

    @Override
    protected String _query(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZXX where 1=1 ");
        if (StringUtils.isNotEmpty(MapUtils.getString(map, "KJDZZBBH"))) {
            builder.append(" and KJDZZBBH=#{KJDZZBBH}");
        }
        return builder.toString();
    }

    public String _queryselect(Map map) {
        StringBuilder builder = new StringBuilder("SELECT * FROM FZXX ");
        return builder.toString();
    }

    public String _queryGL_FZXX1(Map map) {
        StringBuilder builder = new StringBuilder("select fzmc from GL_FZXZL  where  CHARINDEX('2020',kjnd)=1");
        if (MapUtils.getObject(map, "fzdms") != null) {
            builder.append(" and fzdm in (");
            if (map.get("fzdms") instanceof List) {
                List<String> menuIdList = (List) map.get("fzdms");
                for (String id : menuIdList) {
                    if (menuIdList.indexOf(id) > 0)
                        builder.append(",");
                    builder.append("'").append(id).append("'");
                }
            } else {
                builder.append(map.get("fzdms"));
            }
            builder.append(")");
        }
        builder.append(" AND gsdm <> '99999999999999999999' ORDER BY fzdm");
        return builder.toString();
    }

}
