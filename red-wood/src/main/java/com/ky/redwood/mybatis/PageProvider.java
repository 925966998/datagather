package com.ky.redwood.mybatis;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by Linan
 */
public abstract class PageProvider {
    protected final static String DIALECT_MYSQL = "mysql";
    protected final static String DIALECT_ORACLE = "oracle";
    protected final static String DIALECT_SQL = "sqlserver";

    protected abstract String getDialect();

    protected abstract String _query(Map map);

    public String _queryAll(Map param) {
        return _query(param);
    }


    public String _queryCount(Map map) {
        StringBuilder builder = new StringBuilder("select count(1) from (");
        builder.append(_query(map));
        builder.append(") t");
        return builder.toString();
    }

    public String _queryPage(Map param) {
        long pageSize = MapUtils.getLongValue(param, "pageSize");
        pageSize = pageSize <= 0 ? 10 : pageSize;
        long currentPage = MapUtils.getLongValue(param, "currentPage");
        currentPage = currentPage <= 0 ? 1 : currentPage;
        StringBuilder builder = new StringBuilder();
        if (getDialect().toLowerCase().equals(DIALECT_MYSQL)) {
            builder.append(_query(param));
            builder.append(" limit ").append((currentPage - 1) * pageSize).append(",").append(pageSize);
        } else if (getDialect().toLowerCase().equals(DIALECT_ORACLE)) {
            builder.append(" SELECT * FROM (SELECT m.*,rownum AS rn FROM( ").append(_query(param))
                    .append(" ) m ) r where r.rn between ")
                    .append((currentPage - 1L) * pageSize + 1L).append(" and ").append(currentPage * pageSize).append(" ");
        } else if (getDialect().toLowerCase().equals(DIALECT_SQL)) {
            builder.append("select * from ( ").
                    append(_query(param)).
                    append(" ) tt order by tt.id OFFSET ").
                    append((currentPage - 1L) * pageSize).append(" ROWS FETCH next ").
                    append(pageSize).append(" rows only");
        }
        return builder.toString();
    }


    public String buildPageSql(StringBuilder sqlbuilder, Map param) {
        long pageSize = MapUtils.getLongValue(param, "pageSize");
        pageSize = pageSize <= 0 ? 10 : pageSize;
        long currentPage = MapUtils.getLongValue(param, "currentPage");
        currentPage = currentPage <= 0 ? 1 : currentPage;
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(MapUtils.getString(param, "sort")) && StringUtils.isNotBlank(MapUtils.getString(param, "order")))
            sqlbuilder.append(" order by ").append(param.get("sort")).append(" ").append(param.get("order"));
        if (getDialect().toLowerCase().equals(DIALECT_MYSQL)) {
            sqlbuilder.append(" limit ").append((currentPage - 1) * pageSize).append(",").append(pageSize);
            return sqlbuilder.toString();
        } else if (getDialect().toLowerCase().equals(DIALECT_ORACLE)) {
            builder.append(" SELECT * FROM (SELECT m.*,rownum AS rn FROM( ").append(sqlbuilder)
                    .append(" ) m ) r where r.rn between ")
                    .append((currentPage - 1L) * pageSize + 1L).append(" and ").append(currentPage * pageSize).append(" ");
        } else if (getDialect().toLowerCase().equals(DIALECT_SQL)) {
            builder.append("select * from ( ").
                    append(sqlbuilder).
                    append(" ) tt order by tt.id OFFSET ").
                    append((currentPage - 1L) * pageSize).append(" ROWS FETCH next ").
                    append(pageSize).append(" rows only");
        }
        return builder.toString();
    }
}
