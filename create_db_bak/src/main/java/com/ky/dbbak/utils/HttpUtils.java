package com.ky.dbbak.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    //获取参数
    public static Map getParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        //获取httpRequest Method
        String requestMethod = request.getMethod();
        if (requestMethod.toUpperCase().equals("GET")) {
            Enumeration<String> en = request.getParameterNames();
            while (en.hasMoreElements()) {
                String key = en.nextElement();
                String value = request.getParameter(key);
                params.put(key, value);
            }
        } else {
            StringBuffer jb = new StringBuffer();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null)
                    jb.append(line);
            } catch (Exception e) {
                logger.error("read request data error");
            }
            if (jb.toString().length() > 0) {
                JSONObject json = JSON.parseObject(jb.toString());
                for (Map.Entry<String, Object> entry : json.entrySet()) {
                    params.put(entry.getKey(), entry.getValue().toString());
                }
            }
        }
        return params;
    }

    /**无效
     * 必须进行Mapper-Sql拼接
     * @param ids
     * @return
     */
    public static String dealInParams(List<String> ids) {
        StringBuilder builder = new StringBuilder();
        for (String id : ids) {
            if (ids.indexOf(id) > 0)
                builder.append(",");
            builder.append("'").append(id).append("'");
        }
        return builder.toString();
    }
}
