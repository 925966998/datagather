package com.ky.dbbak.controller;

import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.utils.DBHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/ky-datagather/bak")
public class DoBakController {
    private static final Logger logger = LoggerFactory.getLogger(DoBakController.class);
    private static final String SQLSERVER = "SQLSERVER";
    private static final String ORACLE = "ORACLE";
    private static final String MYSQL = "MYSQL";
    //db message
    @Value("${target.databaseName}")
    private String databaseName;
    @Value("${spring.target.datasource.jdbc-url}")
    private String url;
    @Value("${spring.target.datasource.username}")
    private String userName;
    @Value("${spring.target.datasource.password}")
    private String password;

    @RequestMapping(value = "/do", method = RequestMethod.GET)
    public Object doBak(HttpServletRequest request) {
        String dbType = request.getParameter("dbType");
        String bakPath = request.getParameter("bakPath");
        Connection connection = getConnection(dbType);
        String name = databaseName + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()); //文件名
        File file = new File(bakPath);
        String path = file.getPath() + File.separator + name + ".bak";// name文件名
        String str = "backup database " + databaseName + " to disk=? with init";
        try {
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, path);
            ps.execute();
            return new RestResult();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("The doBak exception is {}", e);
        }
        return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG);
    }


    private Connection getConnection(String dbType) {
        Connection connection = null;
        if (StringUtils.isNotEmpty(dbType)) {
            if (dbType.toUpperCase().equals(SQLSERVER))
                connection = DBHelper.initSQLServer(url, userName, password, true);
            if (dbType.toUpperCase().equals(ORACLE))
                connection = DBHelper.initOracle(url, userName, password, true);
            if (dbType.toUpperCase().equals(MYSQL))
                connection = DBHelper.initMysql(url, userName, password, true);
        } else {
            connection = DBHelper.initSQLServer(url, userName, password, true);
        }
        return connection;
    }
}
