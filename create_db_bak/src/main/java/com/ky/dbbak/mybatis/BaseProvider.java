package com.ky.dbbak.mybatis;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;
import java.util.List;
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
            if (map.get(c) != null) {
                builder1.append(",").append(c);
                builder2.append(",#{" + c + "}");
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

    //    public String insertAll(Map map) {
//        List<UrlBlackInfo> urlBlack = (List<UrlBlackInfo>) map.get("list");
//        StringBuilder sb = new StringBuilder();
//        sb.append("INSERT INTO tb_url_blacklist ");
//        sb.append("(url, receive_num, url_type, create_time) ");
//        sb.append("VALUES ");
//        MessageFormat mf = new MessageFormat("(#'{'list[{0}].url},#'{'list[{0}].receiveNum},#'{'list[{0}].urlType},#'{'list[{0}].createTime})");
//        for (int i = 0; i < urlBlack.size(); i++) {
//            sb.append(mf.format(new Object[]{i}));
//            if (i < urlBlack.size() - 1) {
//                sb.append(",");
//            }
//        }
//        return sb.toString();
//    }
    @SuppressWarnings("rawtypes")
    public String _addBatch(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        StringBuilder builder3 = new StringBuilder();
        List<Map<String, Object>> maps = (List<Map<String, Object>>) map.get("list");

        builder2.append("(");
        for (String c : getColumns()) {
            if (maps.get(0).get(c) != null) {
                builder2.append("#'{'list[{0}]." + c + "},");
                if (maps.get(0).get(c) != null) {
                    builder1.append(",").append(c);
                }
            }
        }
        builder2.append(")");
        MessageFormat mf = new MessageFormat(builder2.toString());
        for (int i = 0; i < maps.size(); i++) {
            builder3.append(mf.format(new Object[]{i}));
            if (i < maps.size() - 1) {
                builder3.append(",");
            }
        }
        String s1 = builder1.toString().substring(1, builder1.toString().length());
        String s3 = builder3.toString();
        s3 = s3.replace(",)", ")");
        builder.append("insert into ").append(getTableName());
        builder.append(" (");
        builder.append(s1);
        builder.append(")");
        builder.append(" values");
        builder.append(s3);
        System.out.println(builder.toString());
        return builder.toString();
    }

    public String _update(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(getTableName());
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
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

    public String _updateEntity_pk(Object bean, String pk, String pkValue) {
        try {
            return _update_pk(BeanUtils.describe(bean), pk, pkValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String _update_pk(Map map, String pk, String pkValue) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(getTableName());
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
        builder.append(" where ").append(pk).append("='").append(pkValue).append("'");
        return builder.toString();
    }
}
