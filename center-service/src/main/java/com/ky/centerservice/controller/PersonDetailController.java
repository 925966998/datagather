package com.ky.centerservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.centerservice.entity.PersonDetailEntity;
import com.ky.centerservice.entity.SysUserEntity;
import com.ky.centerservice.logUtil.Log;
import com.ky.centerservice.mapper.PersonDetailMapper;
import com.ky.centerservice.mybatis.PagerResult;
import com.ky.centerservice.mybatis.RestResult;
import com.ky.centerservice.service.PersonDetailService;
import com.ky.centerservice.utils.HttpUtil;
import com.ky.centerservice.utils.HttpUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @className: PersonDetailController
 * @description: TODO
 * @author: Lcj
 * @date: 2021-01-22 09:56
 */

@RestController
@RequestMapping("/ky-ykt/personDetail")
public class PersonDetailController {

    private static final Logger logger = LoggerFactory.getLogger(PersonDetailController.class);

    @Autowired
    PersonDetailService personDetailService;
    @Autowired
    PersonDetailMapper personDetailMapper;

    /**
     * 查询全部数据不分页
     */
    @RequestMapping(value = "queryByParams", method = RequestMethod.GET, produces = "application/json;UTF-8")
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonDetailController queryByParams method params are {}", params);
        return personDetailService.queryAll(params);
    }

    @Log(description = "成本管理回显", module = "成本管理")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET, produces = "application/json;UTF-8")
    public Object queryById(String id) {
        logger.info("The PersonDetailController queryById method params are {}", id);
        PersonDetailEntity personDetailEntity = personDetailMapper._get(id);
        return personDetailEntity;
    }


    @Log(description = "人员档案新增，修改操作", module = "人员档案")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object saveOrUpdate(@RequestBody String body, HttpServletRequest request) {
        logger.info("The PersonUploadController saveOrUpdate method params are {}", body);
        PersonDetailEntity personDetailEntity = JSONObject.parseObject(body, PersonDetailEntity.class);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        System.out.println(personDetailEntity);
        if (personDetailEntity.getId() != null && personDetailEntity.getId().length() > 0) {
            return personDetailService.update(personDetailEntity);
        } else {
            personDetailEntity.setId(UUID.randomUUID().toString());
            return personDetailService.add(personDetailEntity);
        }
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonDetailController queryPage method params are {}", params);
        RestResult restResult = personDetailService.queryPage(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }

    public JSONObject toJson(PagerResult data) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("total", data.getTotalItemsCount());
        jsonObj.put("rows", data.getItems());
        return jsonObj;
    }

    /**
     * 删除多个
     */
    @Log(description = "角色管理删除操作", module = "角色管理")
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteMoney(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonDetailController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                personDetailService._deleteForce(split[i]);
            }
        } else {
            personDetailService._deleteForce(params.get("id").toString());
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG);
    }


    @RequestMapping(value = "/dataUp", method = RequestMethod.GET)
    public String checkAllInfo(HttpServletRequest request) {
        try {
            Map params = HttpUtils.getParams(request);
            RestResult restResult = (RestResult) personDetailService.queryAll(params);
            SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
            List<PersonDetailEntity> data = (List<PersonDetailEntity>) restResult.getData();
            String dataCheckAll = "";
            String s1 = HttpUtil.sendPost1("http://127.0.0.1:8080/ky-ykt/personDetail/notifyCheckAll", dataCheckAll);
            return s1;
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }


    @RequestMapping(value = "/notifyCheckAll", method = RequestMethod.POST)
    @ResponseBody
    public void notifyCheckAll(HttpServletRequest request) {
        logger.info("11111");
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            String result = new String(outSteam.toByteArray(), "GBK");
            // 关闭流
            outSteam.close();
            inStream.close();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/notifyCheckOne", method = RequestMethod.POST)
    @ResponseBody
    public void notifyCheckOne(HttpServletRequest request) {
        logger.info("进入单个校验回调");
        try {
            DataInputStream in = new DataInputStream(request.getInputStream());
            byte[] dataOrigin = new byte[request.getContentLength()];
            // 根据长度，将消息实体的内容读入字节数组dataOrigin中
            in.readFully(dataOrigin);
            in.close();
            String xml = new String(dataOrigin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String sign(JSONObject jsondata, String key, String charset) throws UnsupportedEncodingException {
        String bodyStr = jsondata.getString("body");
        String text = bodyStr + key; //待签名的字符串
        System.out.println(DigestUtils.md5Hex(text.getBytes(charset)));
        return DigestUtils.md5Hex(text.getBytes(charset));
    }

    public static void main(String[] args) throws IOException {
        JSONObject jsondata = new JSONObject();
        jsondata.put("body", "111");
        String a = "1231321321321";
        System.out.println(DigestUtils.md5Hex(a));
        //9dd760d28bd01870acc35b4ec2bbf32f
        String bodyStr = DigestUtils.md5Hex(a) + jsondata.getString("body");
        String charset = "UTF-8";
        final BASE64Encoder encoder = new BASE64Encoder();
        final BASE64Decoder decoder = new BASE64Decoder();
        byte[] textByte = bodyStr.getBytes(charset);
        String encodedText = encoder.encode(textByte).replaceAll("\r\n", "");
        System.out.println(encodedText);
        System.out.println(new String(decoder.decodeBuffer(encodedText), "UTF-8"));
    }
}
