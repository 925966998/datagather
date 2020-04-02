package com.ky.dbbak.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.dbbak.entity.OrgEntity;
import com.ky.dbbak.service.OrgService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ky-datagather/org")
public class OrgController {
    private static final Logger logger = LoggerFactory.getLogger(OrgController.class);

    @Autowired
    OrgService orgService;

    @RequestMapping(value = "/queryTree", method = RequestMethod.GET)
    public Object queryTree(String orgName,String orgCode) {
        Map map = new HashMap();
        map.put("orgName", orgName);
        map.put("orgCode", orgCode);
        return orgService.queryTree(map);
    }


    /**
     * 新增OR更新数据
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The OrgController saveOrUpdate method params are {}", body);
        OrgEntity orgEntity = JSONObject.parseObject(body, OrgEntity.class);
        if (StringUtils.isNotEmpty(orgEntity.getId())) {
            return orgService.update(orgEntity);
        } else {
            orgEntity.setId(UUID.randomUUID().toString());
            return orgService.add(orgEntity);
        }
    }

    @RequestMapping(value = "/queryById/{id}", method = RequestMethod.GET)
    public Object queryById(@PathVariable String id) {
        logger.info("The OrgController queryById method id are {}", id);
        return orgService.queryById(id);
    }

    /**
     * 物理删除
     */
    @RequestMapping(value = "/deleteForce/{id}", method = RequestMethod.GET)
    public Object deleteForce(@PathVariable String id) {
        logger.info("The OrgController deleteForce method id is {}", id);
        return orgService._deleteForce(id);
    }

    /*List<KMYEEntity> kmyeEntities = new ArrayList<KMYEEntity>();
    List<Map<String, Object>> resultListNew = dbyService.kjkmResult(resultList, pageDataGL_Ztcs.get(0));
        if (resultListNew != null && resultListNew.size() > 0) {
        for (Map map1 : resultListNew) {
            try {
                KMYEEntity kmyeEntity = new KMYEEntity();
                BeanUtils.populate(kmyeEntity, map1);
                kmyeEntity.setKey(map1.get("KJDZZBBH").toString() + map1.get("KJYF").toString() + map1.get("KJKMBM").toString());
                kmyeEntities.add(kmyeEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map<String, List<KMYEEntity>> collect = kmyeEntities.stream().collect(Collectors.groupingBy(KMYEEntity::getKey));
        for (String key : collect.keySet()) {
            List<KMYEEntity> kmyeEntities1 = collect.get(key);
            KMYEEntity kmyeEntity = kmyeEntities1.get(0);
            BigDecimal NCDFYE = kmyeEntities1.stream()
                    .map(KMYEEntity::getNCDFYE)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            kmyeEntity.setNCDFYE(NCDFYE);
            kmyeMapper._addEntity(kmyeEntity);
        }*/
}
