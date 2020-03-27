package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.dbbak.utils.DBHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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
    @Value("${spring.target.datasource.url}")
    private String url;
    @Value("${spring.target.datasource.username}")
    private String userName;
    @Value("${spring.target.datasource.password}")
    private String password;

    @Value("${ip}")
    private String ip;
    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/do", method = RequestMethod.GET)
    public void doBak(HttpServletRequest request, HttpServletResponse response) {
        String dbType = request.getParameter("dbType");
        String bakPath = request.getSession().getServletContext().getRealPath("/upload");
        Connection connection = getConnection(dbType);
        String name = databaseName + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()); //文件名
        File file = new File(bakPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String path = file.getPath() + File.separator + name + ".bak";// name文件名
        String str = "backup database " + databaseName + " to disk=? with init";
        File bakFile = null;
        OutputStream out = null;
        try {
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setString(1, path);
            ps.execute();
            response.setContentType("multipart/form-data");
            response.setHeader("Content-disposition", "attachment;filename=" + name + ".bak");
            out = response.getOutputStream();
            bakFile = new File(path);
            FileInputStream inputStream = new FileInputStream(bakFile);
            int b = 0;
            byte[] buffer = new byte[512];
            while (b != -1) {
                b = inputStream.read(buffer);
                //4.写到输出流(out)中
                out.write(buffer, 0, b);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("The doBak exception is {}", e);
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
                if (bakFile != null) {
                    bakFile.delete();
                }
            } catch (Exception e) {
                logger.error("{}", e);
            }
        }
    }

    @RequestMapping(value = "/queryIpAndPort", method = RequestMethod.GET)
    public Object queryIpAndPort() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ip", ip);
        jsonObject.put("port", port);
        return jsonObject;
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
