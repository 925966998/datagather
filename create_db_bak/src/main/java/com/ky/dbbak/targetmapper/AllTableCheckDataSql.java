package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.BaseProvider;
import com.ky.dbbak.mybatis.PageProvider;

import java.util.Map;

public class AllTableCheckDataSql extends BaseProvider {
    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected String[] getColumns() {
        return new String[]{"XZQHDM",
                "XZQHMC",
                "KJND",
                "DWMC",
                "DWDM",
                "KJDZZBBH",
                "KJDZZBMC"

        };
    }

    @Override
    protected String getDialect() {
        return null;
    }

    @Override
    protected String _query(Map map) {
        return null;
    }


    public String updateFZLXkjdzzbbh(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(" dbo.FZLX ");
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
        builder.append(" where KJDZZBBH=#{oldKjdzzbbh}");
        return builder.toString();
    }

    public String updateFZNCSkjdzzbbh(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(" dbo.FZNCS ");
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
        builder.append(" where KJDZZBBH=#{oldKjdzzbbh}");
        return builder.toString();
    }

    public String updateFZXXkjdzzbbh(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(" dbo.FZXX ");
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
        builder.append(" where KJDZZBBH=#{oldKjdzzbbh}");
        return builder.toString();
    }

    public String updateFZYEkjdzzbbh(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(" dbo.FZYE ");
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
        builder.append(" where KJDZZBBH=#{oldKjdzzbbh}");
        return builder.toString();
    }

    public String updateJZPZkjdzzbbh(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(" dbo.JZPZ ");
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
        builder.append(" where KJDZZBBH=#{oldKjdzzbbh}");
        return builder.toString();
    }

    public String updateKJKMkjdzzbbh(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(" dbo.KJKM ");
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
        builder.append(" where KJDZZBBH=#{oldKjdzzbbh}");
        return builder.toString();
    }

    public String updateKJQJDYkjdzzbbh(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(" dbo.KJQJDY ");
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
        builder.append(" where KJDZZBBH=#{oldKjdzzbbh}");
        return builder.toString();
    }

    public String updateKMNCSkjdzzbbh(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(" dbo.KMNCS ");
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
        builder.append(" where KJDZZBBH=#{oldKjdzzbbh}");
        return builder.toString();
    }

    public String updateKMYEkjdzzbbh(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(" dbo.KMYE ");
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
        builder.append(" where KJDZZBBH=#{oldKjdzzbbh}");
        return builder.toString();
    }

    public String updatePZFZMXkjdzzbbh(Map map) {
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        for (String c : getColumns()) {
            if (map.get(c) != null) {
                builder1.append(",").append(c).append("=#{" + c + "}");
            }
        }
        builder.append("update ").append(" dbo.PZFZMX ");
        builder.append(" set ");
        builder.append(builder1.substring(1, builder1.toString().length()));
        builder.append(" where KJDZZBBH=#{oldKjdzzbbh}");
        return builder.toString();
    }


}
