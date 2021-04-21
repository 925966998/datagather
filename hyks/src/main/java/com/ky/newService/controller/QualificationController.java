package com.ky.newService.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.newService.entity.QualificationEntity;
import com.ky.newService.entity.SupplierUserEntity;
import com.ky.newService.entity.SysUserEntity;
import com.ky.newService.mapper.QualificationMapper;
import com.ky.newService.mapper.SupplierUserMapper;
import com.ky.newService.mybatis.RestResult;
import com.ky.newService.service.QualificationService;
import com.ky.newService.utils.HttpUtils;
import com.ky.newService.utils.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.ky.newService.utils.DateUtil.getDaySub;

/**
 * @className: qualificationController
 * @description: TODO
 * @author: Lcj
 * @date: 2020-09-28 10:19
 */

@RestController
@RequestMapping("/ky-supplier/qualification")
public class QualificationController {
    private static final Logger logger = LoggerFactory.getLogger(QualificationController.class);
    @Autowired
    QualificationService qualificationService;
    @Autowired
    QualificationMapper qualificationMapper;
    @Autowired
    SupplierUserMapper supplierUserMapper;

    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The QualificationController queryByParams method params are {}", params);
        return qualificationService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The QualificationController queryByParams method params are {}", params);
        return qualificationService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The QualificationController saveOrUpdate method params are {}", body);
        QualificationEntity qualificationEntity = JSONObject.parseObject(body, QualificationEntity.class);
        if (qualificationEntity.getId() != null && qualificationEntity.getId().length() > 0) {
            return qualificationService.update(qualificationEntity);
        } else {
            qualificationEntity.setId(UUID.randomUUID().toString());
            return qualificationService.add(qualificationEntity);
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Object save(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        System.out.println(params);
        if (params.get("id").toString() == "" || params.get("id").toString() == null) {
            QualificationEntity qualificationEntity1 = new QualificationEntity();
            qualificationEntity1.setId(UUID.randomUUID().toString());
            qualificationEntity1.setName(params.get("name").toString());
            qualificationEntity1.setEndTime(params.get("endTime").toString());
            qualificationEntity1.setStartTime(params.get("startTime").toString());
            qualificationEntity1.setSupplierManageId(params.get("supplierManageId").toString());
            qualificationEntity1.setEndDay(Integer.valueOf(params.get("endDay").toString()));
            return qualificationService.add(qualificationEntity1);
        } else {
            QualificationEntity qualificationEntity = qualificationMapper._get(params.get("id").toString());
            qualificationEntity.setName(params.get("name").toString());
            qualificationEntity.setEndTime(params.get("endTime").toString());
            qualificationEntity.setStartTime(params.get("startTime").toString());
            qualificationEntity.setEndDay(Integer.valueOf(params.get("endDay").toString()));
            return qualificationService.update(qualificationEntity);
        }
    }


    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The QualificationController delete method params is {}", params);
        return qualificationService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The QualificationController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        System.out.println(id);
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                qualificationService._deleteForce(split[i]);
            }
        } else {
            qualificationService._deleteForce(params.get("id").toString());
        }
        return new RestResult();
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The QualificationController queryPage method params are {}", params);
        return qualificationService.queryPage(params);
    }


    @RequestMapping(value = "/queryDate", method = RequestMethod.GET)
    public Object queryDate(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The QualificationController queryDate method params are {}", params);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        List resultList = new ArrayList();
        if (user.getRoleId().trim().equals("a599f1da-f57c-4afc-a600-b58e15836aa0")) {
            List<QualificationEntity> qualificationEntities = qualificationMapper._queryAll(params);
            for (QualificationEntity q : qualificationEntities) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(new Date());
                long day = getDaySub(date, q.getEndTime());
                if (day < q.getEndDay()) {
                    resultList.add(q);
                }
            }
        } else {
            params.put("userId", user.getId());
            List<SupplierUserEntity> supplierUserEntities = supplierUserMapper._queryAll(params);
            for (SupplierUserEntity s : supplierUserEntities) {
                params.put("supplierManageId", s.getSupplierManageId());
                List<QualificationEntity> qualificationEntities = qualificationMapper._queryAll(params);
                for (QualificationEntity q : qualificationEntities) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(new Date());
                    long day = getDaySub(date, q.getEndTime());
                    if (day < q.getEndDay()) {
                        resultList.add(q);
                    }
                }
            }
        }
        return resultList;
    }


    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/readFile", method = RequestMethod.GET)
    public Object readFile(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        params.get("uploadFormId");
        List<QualificationEntity> qualificationEntities = qualificationMapper._queryAll(params);
        String a = System.getProperty("user.dir");
        System.out.println(a);
        String filePath = PathUtil.getClasspath() + "upload/";
        String path = filePath + params.get("uploadFormId").toString() + "\\/";
        //String path = "D://file" + "\\/" + params.get("uploadFormId").toString() + "\\/";
        List<Map<String, String>> result = new ArrayList<>();
        File config = new File(path);
        File temp[];
        temp = config.listFiles();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].isDirectory()) {//判断是否目录，如果是目录则递归回调继续处理改目录下的文件temp[i].getPath()
                temp[i].getPath();
            } else {
                System.out.println(temp[i].getPath());
                System.out.println(temp[i].getName());
                Map<String, String> map = new HashMap<>();
                map.put("name", temp[i].getName());
                map.put("supplierManageId", qualificationEntities.get(0).getSupplierManageId());
                map.put("filePath", temp[i].getPath());
                result.add(map);
            }
        }
        return result;
    }

    @RequestMapping(value = "/querySupplierId", method = RequestMethod.GET)
    public String querySupplierId(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        QualificationEntity qualificationEntity =qualificationMapper._get(params.get("uploadFormId").toString());
        return qualificationEntity.getSupplierManageId();
    }

}