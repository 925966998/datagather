package com.ky.redwood.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.redwood.entity.*;
import com.ky.redwood.excle.ExcelHead;
import com.ky.redwood.excle.ExcelUtils;
import com.ky.redwood.logUtil.Log;
import com.ky.redwood.mapper.GoodsMapper;
import com.ky.redwood.mybatis.RestResult;
import com.ky.redwood.service.GoodsService;
import com.ky.redwood.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ky-redwood/goods")
public class GoodsController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    GoodsService goodsService;
    @Autowired
    GoodsMapper goodsMapper;


    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The GoodsController queryByParams method params are {}", params);
        return goodsService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The GoodsController queryByParams method params are {}", params);
        return goodsService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @Log(description = "销售单新增,修改操作", module = "销售单管理")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, consumes = "application/json")
    public Object saveOrUpdate(@RequestBody String body) {
        logger.info("The GoodsController saveOrUpdate method params are {}", body);
        GoodsEntity goodsEntity = JSONObject.parseObject(body, GoodsEntity.class);
        if (StringUtils.isNotEmpty(goodsEntity.getId())) {
            return goodsService.update(goodsEntity);
        } else {
            goodsEntity.setId(UUID.randomUUID().toString());
            return goodsService.add(goodsEntity);
        }
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "销售单管理逻辑删除操作", module = "销售单管理")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The GoodsController delete method params is {}", params);
        return goodsService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @Log(description = "销售单管理物理删除操作", module = "销售单管理")
    @RequestMapping(value = "/deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The GoodsController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                goodsService._deleteForce(split[i]);
            }
        } else {
            goodsService._deleteForce(params.get("id").toString());
        }
        return new RestResult();
    }

    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        params.put("currentPage", params.get("page"));
        params.put("pageSize", params.get("rows"));
        logger.info("The GoodsController queryPage method params are {}", params);
        return goodsService.queryPage(params);
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @Transactional
    public RestResult importExcel(@RequestParam MultipartFile file, HttpServletRequest request) {
        logger.info("The file is {}", file);
        if (file == null || file.getName().equals("") || file.getSize() <= 0) {
            return new RestResult(40000, RestResult.ERROR_MSG, "文件不合法,请检查文件是否为Excel文件");
        }
        String fileName = file.getOriginalFilename();
        try {
            ExcelUtils.checkFile(fileName);
        } catch (Exception e) {
            return new RestResult(40000, RestResult.ERROR_MSG, "文件不合法,请检查文件是否为Excel文件");
        }
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String path = filePath + fileName;
        File uploadFile = new File(path);
        List<ExcelHead> headList = goodsMapper._queryColumnAndComment();
        try {
            file.transferTo(uploadFile);
            InputStream inputStream = new FileInputStream(uploadFile);
            List<GoodsEntity> goodsEntities = ExcelUtils.readExcelToEntity(GoodsEntity.class, inputStream, uploadFile.getName(), headList);
            System.out.println(goodsEntities);
            for (GoodsEntity goodsEntity : goodsEntities) {
                if (goodsEntity.getAllName() != null || goodsEntity.getTexture() != null || goodsEntity.getGoodsUnit() != null
                        || goodsEntity.getGoodsSpec() != null) {
                    goodsEntity.setId(UUID.randomUUID().toString());
                    goodsMapper._addEntity(goodsEntity);
                }
            }
            logger.info("execute success {}", goodsEntities.size());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        } finally {
            uploadFile.delete();
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, "上传成功");
    }
}
