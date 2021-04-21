package com.ky.newService.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.newService.entity.*;
import com.ky.newService.mapper.SupplierUserMapper;
import com.ky.newService.mapper.ViolationMapper;
import com.ky.newService.mybatis.RestResult;
import com.ky.newService.service.ViolationService;
import com.ky.newService.utils.HttpUtils;
import com.ky.newService.utils.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * @className: ViolationContrller
 * @description: TODO
 * @author: Lcj
 * @date: 2020-09-28 10:21
 */
@RestController
@RequestMapping("/ky-supplier/violation")
public class ViolationContrller {


    private static final Logger logger = LoggerFactory.getLogger(ViolationContrller.class);

    @Autowired
    ViolationService violationService;
    @Autowired
    ViolationMapper violationMapper;
    @Autowired
    SupplierUserMapper supplierUserMapper;

    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ViolationContrller queryByParams method params are {}", params);
        return violationService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ViolationContrller queryByParams method params are {}", params);
        return violationService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The ViolationContrller saveOrUpdate method params are {}", body);
        ViolationEntity violationEntity = JSONObject.parseObject(body, ViolationEntity.class);
        if (violationEntity.getId() != null && violationEntity.getId().length() > 0) {
            return violationService.update(violationEntity);
        } else {
            violationEntity.setId(UUID.randomUUID().toString());
            return violationService.add(violationEntity);
        }
    }


    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Object save(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        System.out.println(params);
        if (params.get("id").toString() == "" || params.get("id").toString() == null) {
            ViolationEntity violationEntity = new ViolationEntity();
            violationEntity.setId(UUID.randomUUID().toString());
            violationEntity.setName(params.get("name").toString());
            violationEntity.setStartTime(params.get("startTime").toString());
            violationEntity.setSupplierManageId(params.get("supplierManageId").toString());
            if (params.get("dealTime").toString()==null||params.get("dealTime").toString().equals("")){
                violationEntity.setDealTime(null);
            }else {
                violationEntity.setDealTime(params.get("dealTime").toString());
            }
            violationEntity.setOpinion(params.get("opinion").toString());
            violationEntity.setResult(Integer.valueOf(params.get("result").toString()));
            return violationService.add(violationEntity);
        } else {
            ViolationEntity violationEntity = violationMapper._get(params.get("id").toString());
            violationEntity.setName(params.get("name").toString());
            violationEntity.setStartTime(params.get("startTime").toString());
            if (params.get("dealTime").toString()==null||params.get("dealTime").toString().equals("")){
                violationEntity.setDealTime(null);
            }else {
                violationEntity.setDealTime(params.get("dealTime").toString());
            }
            violationEntity.setOpinion(params.get("opinion").toString());
            violationEntity.setResult(Integer.valueOf(params.get("result").toString()));
            return violationService.update(violationEntity);
        }
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ViolationContrller delete method params is {}", params);
        return violationService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ViolationContrller deleteForce method params is {}", params);
        String id = params.get("id").toString();
        System.out.println(id);
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                violationService._deleteForce(split[i]);
            }
        } else {
            violationService._deleteForce(params.get("id").toString());
        }
        return new RestResult();
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The ViolationContrller queryPage method params are {}", params);
        return violationService.queryPage(params);
    }

//    @RequestMapping(value = "/queryDate", method = RequestMethod.GET)
//    public Object queryDate(HttpServletRequest request) {
//        Map params = HttpUtils.getParams(request);
//        logger.info("The QualificationController queryDate method params are {}", params);
//        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
//        List resultList = new ArrayList();
//        if (user.getId().trim().equals("223b6557-3969-4b1d-9b81-296786a546de")) {
//            List<ViolationEntity> violationEntities = violationMapper._queryAll(params);
//            for (ViolationEntity v : violationEntities) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String date = sdf.format(new Date());
//                long day = getDaySub(date, v.getEndTime());
//                System.out.println(day);
//                if (day < 7) {
//                    resultList.add(v);
//                }
//            }
//        } else {
//            params.put("userId", user.getId());
//            List<SupplierUserEntity> supplierUserEntities = supplierUserMapper._queryAll(params);
//            for (SupplierUserEntity s : supplierUserEntities) {
//                params.put("supplierManageId", s.getSupplierManageId());
//                List<ViolationEntity> violationEntities = violationMapper._queryAll(params);
//                for (ViolationEntity v : violationEntities) {
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    String date = sdf.format(new Date());
//                    long day = getDaySub(date, v.getEndTime());
//                    System.out.println(day);
//                    if (day < 7) {
//                        resultList.add(v);
//                    }
//                }
//            }
//        }
//        return resultList;
//    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/readFile", method = RequestMethod.GET)
    public Object readFile(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        params.get("uploadFormId");
        List<ViolationEntity> violationEntities = violationMapper._queryAll(params);
        String a = System.getProperty("user.dir");
        System.out.println(a);
        String filePath = PathUtil.getClasspath() + "upload/";
        String path = filePath + params.get("uploadFormId").toString() + "\\/";
//        String path = "D://file" + "\\/" + params.get("uploadFormId").toString() + "\\/";
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
                map.put("supplierManageId", violationEntities.get(0).getSupplierManageId());
                map.put("filePath", temp[i].getPath());
                result.add(map);
            }
        }
        return result;
    }

    @RequestMapping(value = "/querySupplierId", method = RequestMethod.GET)
    public String querySupplierId(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        ViolationEntity violationEntity =violationMapper._get(params.get("uploadFormId").toString());
        return violationEntity.getSupplierManageId();
    }

    @RequestMapping(value = "/fileViolation", method = RequestMethod.GET)
    public Object fileViolation(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        String filePath = PathUtil.getClasspath() + "upload/";
        String path = filePath + params.get("uploadFormId").toString() + "\\/";
        File config = new File(path);
        File temp[];
        temp = config.listFiles();
        if (temp !=null){
            return "success";
        }else {
            return "false";
        }
    }
}
