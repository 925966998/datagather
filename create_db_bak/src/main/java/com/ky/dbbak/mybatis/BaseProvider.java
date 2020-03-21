package com.ky.dbbak.mybatis;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.UUID;

/**
 * Created by Linan
 */
public abstract class BaseProvider extends PageProvider {

    protected abstract String getTableName();

    protected abstract String[] getColumns();

    public String _get(String id) {
        StringBuilder builder = new StringBuilder("select * from ").append(getTableName()).append(" where Id=#{id}");
        return builder.toString();
    }

    public String _getAll() {
        StringBuilder builder = new StringBuilder("select * from ").append(getTableName());
        return builder.toString();
    }

    public String _deleteForce(String id) {
        StringBuilder builder = new StringBuilder("delete from ").append(getTableName()).append(" where Id=#{id}");
        return builder.toString();
    }

    public String _delete(String id) {
        StringBuilder builder = new StringBuilder("update ").append(getTableName())
                .append(" set LogicalDel=1 where Id=#{id}");
        return builder.toString();
    }

    @SuppressWarnings("rawtypes")
    public String _add(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(StringUtils.uncapitalize(c)) != null) {
                builder1.append(",").append(c);
                builder2.append(",#{" + StringUtils.uncapitalize(c) + "}");
            }
        }
        String s1 = builder1.toString().substring(1, builder1.toString().length());
        String s2 = builder2.toString().substring(1, builder2.toString().length());
        builder.append("insert into ").append(getTableName());
        builder.append(" (");
        builder.append(s1);
        builder.append(")");
        builder.append(" values");
        builder.append("(");
        builder.append(s2);
        builder.append(")");
        return builder.toString();
    }

    public String _update(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(StringUtils.uncapitalize(c)) != null) {
                builder1.append(",").append(c).append("=#{" + StringUtils.uncapitalize(c) + "}");
            }
        }
        builder.append("update ").append(getTableName());
        builder.append(" set ");
        builder.append(builder1.substring(1,builder1.toString().length()));
        builder.append(" where Id=#{id}");
        return builder.toString();
    }

    public String _addEntity(Object bean) {
        try {
            return _add(BeanUtils.describe(bean));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String _updateEntity(Object bean) {
        try {
            return _update(BeanUtils.describe(bean));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
