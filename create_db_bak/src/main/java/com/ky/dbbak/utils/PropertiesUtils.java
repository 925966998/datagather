package com.ky.dbbak.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    /**
     * @param fileName 配置文件名称
     *                 优点：可以在非Web应用中读取配置资源信息，可以读取任意的资源文件信息
     *                 缺点：只能加载类classes下面的资源文件
     * @return Map<String, Object> 以Map键值对方式返回配置文件内容
     * @Title: getProfileByClassLoader
     * @Description: 采用ClassLoader(类加载器)方式进行读取配置信息
     */
    public static Map getProfileByClassLoader(String fileName) {
        // 通过ClassLoader获取到文件输入流对象
        InputStream in = Profile.class.getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        Properties props = new Properties();
        Map profileMap = new HashMap();
        try {
            props.load(reader);
            for (Object key : props.keySet()) {
                profileMap.put(key.toString(), props.getProperty(key.toString()));
            }
        } catch (IOException e) {
            logger.error("The PropertiesUtils getProfileByClassLoader exception is {}", e);
        }
        return profileMap;
    }

    /**
     * 传递键值对的Map，更新properties文件
     *
     * @param fileName    文件名(放在resource源包目录下)，需要后缀
     * @param keyValueMap 键值对Map
     */
    public static void updateProperties(String fileName, Map<String, String> keyValueMap) throws Exception {
        String filePath = PropertiesUtils.class.getClassLoader().getResource(fileName).getPath();
        System.out.println("propertiesPath:" + filePath);
        Properties props = new Properties();
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            // 从输入流中读取属性列表（键和元素对）
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            props.load(br);
            br.close();
            // 写入属性文件
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
            // 清空旧的文件
            // props.clear();
            for (String key : keyValueMap.keySet()) {
                props.setProperty(key, keyValueMap.get(key));
            }
            props.store(bw, "改变数据");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("The PropertiesUtils updateProperties exception is {}", e);
        } finally {
            try {
                br.close();
                bw.close();
            } catch (IOException e) {
                logger.error("The PropertiesUtils updateProperties exception is {}", e);
            }
        }
    }
}
