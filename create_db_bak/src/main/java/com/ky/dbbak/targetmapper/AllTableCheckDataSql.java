package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mybatis.PageProvider;

import java.util.Map;

public class AllTableCheckDataSql extends PageProvider {
    @Override
    protected String[] getColumns() {
        return new String[0];
    }

    @Override
    protected String getDialect() {
        return null;
    }

    @Override
    protected String _query(Map map) {
        return null;
    }


    public String updateFZLXkjdzzbbh(String oldkjdzzbbh, String newkjdzzbbh) {
        return "update dbo.FZLX set KJDZZBBH='" + newkjdzzbbh + "' where KJDZZBBH='" + oldkjdzzbbh + "'";
    }

    public String updateFZNCSkjdzzbbh(String oldkjdzzbbh, String newkjdzzbbh) {
        return "update dbo.FZNCS set KJDZZBBH='" + newkjdzzbbh + "' where KJDZZBBH='" + oldkjdzzbbh + "'";
    }

    public String updateFZXXkjdzzbbh(String oldkjdzzbbh, String newkjdzzbbh) {
        return "update dbo.FZXX set KJDZZBBH='" + newkjdzzbbh + "' where KJDZZBBH='" + oldkjdzzbbh + "'";
    }

    public String updateFZYEkjdzzbbh(String oldkjdzzbbh, String newkjdzzbbh) {
        return "update dbo.FZYE set KJDZZBBH='" + newkjdzzbbh + "' where KJDZZBBH='" + oldkjdzzbbh + "'";
    }

    public String updateJZPZkjdzzbbh(String oldkjdzzbbh, String newkjdzzbbh) {
        return "update dbo.JZPZ set KJDZZBBH='" + newkjdzzbbh + "' where KJDZZBBH='" + oldkjdzzbbh + "'";
    }

    public String updateKJKMkjdzzbbh(String oldkjdzzbbh, String newkjdzzbbh) {
        return "update dbo.KJKM set KJDZZBBH='" + newkjdzzbbh + "' where KJDZZBBH='" + oldkjdzzbbh + "'";
    }

    public String updateKJQJDYkjdzzbbh(String oldkjdzzbbh, String newkjdzzbbh) {
        return "update dbo.KJQJDY set KJDZZBBH='" + newkjdzzbbh + "' where KJDZZBBH='" + oldkjdzzbbh + "'";
    }

    public String updateKMNCSkjdzzbbh(String oldkjdzzbbh, String newkjdzzbbh) {
        return "update dbo.KMNCS set KJDZZBBH='" + newkjdzzbbh + "' where KJDZZBBH='" + oldkjdzzbbh + "'";
    }

    public String updateKMYEkjdzzbbh(String oldkjdzzbbh, String newkjdzzbbh) {
        return "update dbo.KMYE set KJDZZBBH='" + newkjdzzbbh + "' where KJDZZBBH='" + oldkjdzzbbh + "'";
    }

    public String updatePZFZMXkjdzzbbh(String oldkjdzzbbh, String newkjdzzbbh) {
        return "update dbo.PZFZMX set KJDZZBBH='" + newkjdzzbbh + "' where KJDZZBBH='" + oldkjdzzbbh + "'";
    }


}
