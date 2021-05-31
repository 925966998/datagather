package com.ky.hyks.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ky.hyks.entity.CompanyEntity;
import com.ky.hyks.entity.CompanyOrderEntity;
import com.ky.hyks.entity.OrderInfoEntity;
import com.ky.hyks.entity.SysUserEntity;
import com.ky.hyks.logUtil.Log;
import com.ky.hyks.mapper.CompanyOrderMapper;
import com.ky.hyks.mapper.OrderInfoMapper;
import com.ky.hyks.mybatis.PagerResult;
import com.ky.hyks.mybatis.RestResult;
import com.ky.hyks.service.CompanyService;
import com.ky.hyks.service.OrderInfoService;
import com.ky.hyks.utils.HttpUtil;
import com.ky.hyks.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@RequestMapping("/ky-supplier/orderInfo")
public class OrderInfoController {

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoController.class);

    @Autowired
    OrderInfoService orderInfoService;
    @Autowired
    CompanyOrderMapper companyOrderMapper;
    @Autowired
    OrderInfoMapper orderInfoMapper;
    /**
     * 查询全部数据不分页
     */
    @RequestMapping(value = "queryByParams", method = RequestMethod.GET, produces = "application/json;UTF-8")
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderInfoController queryByParams method params are {}", params);
        return orderInfoService.queryAll(params);
    }

    @RequestMapping(value = "queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonController queryByParams method params are {}", params);
        return orderInfoService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "角色管理新增，修改操作", module = "角色管理")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object saveOrUpdate(@RequestBody String body) {
//        logger.info("The OrderInfoController saveOrUpdate method params are {}", body);
//        JSONArray jsonArray = JSONArray.parseArray(body );
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            System.out.println(jsonObject.get("parentVO"));
//            System.out.println(jsonObject.get("childrenVO"));
//        }
//        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, "success");
        OrderInfoEntity orderInfoEntity = JSONObject.parseObject(body, OrderInfoEntity.class);
        if (StringUtils.isNotEmpty(orderInfoEntity.getId())) {
            return orderInfoService.update(orderInfoEntity);
        } else {
            orderInfoEntity.setId(UUID.randomUUID().toString());
            orderInfoEntity.setState(0);
            orderInfoService.add(orderInfoEntity);
            return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, "success");
        }
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderInfoController queryPage method params are {}", params);
        params.put("state", 0);
        RestResult restResult = orderInfoService.queryPage(params);
        PagerResult data = (PagerResult) restResult.getData();
        return this.toJson(data);
    }


    public JSONObject toJson(PagerResult data) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("total", data.getTotalItemsCount());
        jsonObj.put("rows", data.getItems());
        return jsonObj;
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public OrderInfoEntity select(String id) {
        logger.info("The OrderInfoController queryPage method params are {}", id);
        RestResult restResult = orderInfoService._get(id);
        OrderInfoEntity data = (OrderInfoEntity) restResult.getData();
        return data;
    }

    /**
     * 删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "角色管理删除操作", module = "角色管理")
    @RequestMapping(value = "/deleteOne", method = RequestMethod.GET)
    public Object deleteOne(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderInfoController delete method params is {}", params);
        return orderInfoService.delete(params.get("id").toString());
    }

    /**
     * 删除多个
     */
    @Log(description = "角色管理删除操作", module = "角色管理")
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteMoney(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The OrderInfoController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                orderInfoService._deleteForce(split[i]);
            }
        } else {
            orderInfoService._deleteForce(params.get("id").toString());
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG);
    }


    @Log(description = "成本管理新增/删除操作", module = "成本管理")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object save(String orderInfoEntities, HttpServletRequest request) {
        logger.info("The OrderInfoController saveOrUpdate method params are {}", orderInfoEntities);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        List<OrderInfoEntity> orderInfoEntityList = JSON.parseArray(orderInfoEntities, OrderInfoEntity.class);
        for (OrderInfoEntity orderInfoEntity : orderInfoEntityList) {
            if (StringUtils.isNotEmpty(orderInfoEntity.getId())) {
                return orderInfoService.update(orderInfoEntity);
            } else {
                orderInfoEntity.setId(UUID.randomUUID().toString());
                return orderInfoService.add(orderInfoEntity);
            }
        }
        return new RestResult();
    }


    @RequestMapping(value = "sendNC", method = RequestMethod.GET)
    public String checkAllInfo(HttpServletRequest request) {
        try {
            Map params = HttpUtils.getParams(request);
            RestResult restResult = (RestResult) orderInfoService.queryAll(params);
            List<OrderInfoEntity> orderInfoEntities = (List<OrderInfoEntity>) orderInfoService.queryAll(params);
            String a = "22222";
            String s1 = HttpUtil.sendPost1("http://127.0.0.1:8080/ky-ykt/personDetail/notifyCheckAll", a);
            return s1;
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    @Log(description = "角色管理新增，修改操作", module = "角色管理")
    @RequestMapping(value = "saveNC", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object saveNC(@RequestBody String body) {
        logger.info("The OrderInfoController saveOrUpdate method params are {}", body);
        JSONArray jsonArray = JSONArray.parseArray(body);
        JSONObject parentVO = ((JSONObject) jsonArray.get(0)).getJSONObject("parentVO");
        JSONArray children = ((JSONObject) jsonArray.get(1)).getJSONArray("childrenVO");
        logger.info("parentVO are {}", parentVO);
        logger.info("children are {}", children);
        for (int i = 0; i < children.size(); i++) {
            //需求Id
            System.out.println(((JSONObject) children.get(i)).get("pk_praybill_b"));
            //表头id
            System.out.println(((JSONObject) children.get(i)).get("pk_praybill"));
            OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
            orderInfoEntity.setId(((JSONObject) children.get(i)).get("pk_praybill_b").toString());
            orderInfoEntity.setPk_order(((JSONObject) children.get(i)).get("pk_praybill").toString());
            Map map = new HashMap();
            map.put("pk_praybill_b",((JSONObject) children.get(i)).get("pk_praybill_b").toString());
            OrderInfoEntity orderInfoEntity1 = orderInfoMapper.queryBypk(map);
            orderInfoEntity.setCode(orderInfoEntity1.getCode());
            orderInfoEntity.setMatterSpec(orderInfoEntity1.getMatterSpec());
            orderInfoEntity.setMatterName(orderInfoEntity1.getMatterName());
            orderInfoEntity.setMarbasClassCode(orderInfoEntity1.getMarbasClassCode());
            orderInfoEntity.setMarbasClassName(orderInfoEntity1.getMarbasClassName());
            orderInfoEntity.setNastNum(orderInfoEntity1.getNastNum());
            orderInfoEntity.setDbillDate(orderInfoEntity1.getDbillDate());
            orderInfoEntity.setPk_group(orderInfoEntity1.getPk_group());
            orderInfoEntity.setState(0);
            orderInfoService.add(orderInfoEntity);
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, "success");
    }
}
