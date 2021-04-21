package com.ky.newService.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.newService.entity.*;
import com.ky.newService.excle.ExcelHead;
import com.ky.newService.excle.ExcelStyle;
import com.ky.newService.excle.ExcelUtils;
import com.ky.newService.excle.ExportExcel;
import com.ky.newService.mapper.*;
import com.ky.newService.mybatis.RestResult;
import com.ky.newService.service.SupplierManageService;
import com.ky.newService.utils.DateUtil;
import com.ky.newService.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/ky-supplier/supplierManage")
public class SupplierManageController {


    private static final Logger logger = LoggerFactory.getLogger(SupplierManageController.class);

    @Autowired
    SupplierManageService supplierManageService;
    @Autowired
    SupplierUserMapper supplierUserMapper;
    @Autowired
    SupplierManageMapper supplierManageMapper;
    @Autowired
    SupplierTypeMapper supplierTypeMapper;
    @Autowired
    QualificationMapper qualificationMapper;
    @Autowired
    SupplierChangeMapper supplierChangeMapper;
    @Autowired
    ViolationMapper violationMapper;

    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierManageController queryByParams method params are {}", params);
        return supplierManageService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierManageController queryByParams method params are {}", params);
        return supplierManageService.get(params);
    }

    /**
     * 新增OR更新数据
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public Object saveOrUpdate(@RequestBody String body, HttpServletRequest request) {
        logger.info("The SupplierManageController saveOrUpdate method params are {}", body);
        SupplierManageEntity supplierManageEntity = JSONObject.parseObject(body, SupplierManageEntity.class);
        if (supplierManageEntity.getId() != null && supplierManageEntity.getId().length() > 0) {
            return supplierManageService.update(supplierManageEntity);
        } else {
            SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
            List<SupplierManageEntity> supplierManageEntities = supplierManageMapper.queryByCode(supplierManageEntity.getTaxNum());
            if (supplierManageEntities.size() > 0 && supplierManageEntities != null) {
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "改客商已存在，请重新录入");
            } else {
                String supplierManageId = UUID.randomUUID().toString();
                supplierManageEntity.setId(supplierManageId);
                supplierManageEntity.setUserId(user.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(new Date());
                supplierManageEntity.setRecordDate(date);
                supplierManageEntity.setCode(DateUtil.getSdYMdHmsS());
                supplierManageEntity.setState(0);
                SupplierUserEntity supplierUserEntity = new SupplierUserEntity();
                supplierUserEntity.setId(UUID.randomUUID().toString());
                supplierUserEntity.setSupplierManageId(supplierManageId);
                supplierUserEntity.setUserId(user.getId());
                supplierUserEntity.setState(0);
                supplierUserEntity.setSupplierTypeId(supplierManageEntity.getSupplierTypeId());
                supplierUserMapper._addEntity(supplierUserEntity);
                return supplierManageService.add(supplierManageEntity);
            }
        }
    }


    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierManageController delete method params is {}", params);
        return supplierManageService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @RequestMapping(value = "deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierManageController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        System.out.println(id);
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                supplierManageService._deleteForce(split[i]);
                supplierUserMapper._deleteBySupplierId(split[i]);
                qualificationMapper.deleteBySupplierId(split[i]);
                violationMapper.deleteBySupplierId(split[i]);
            }
        } else {
            supplierManageService._deleteForce(params.get("id").toString());
            supplierUserMapper._deleteBySupplierId(params.get("id").toString());
            qualificationMapper.deleteBySupplierId(params.get("id").toString());
            violationMapper.deleteBySupplierId(params.get("id").toString());
        }
        return new RestResult();
    }

    @RequestMapping(value = "getSessionRoleCode", method = RequestMethod.GET)
    public Object getSessionRoleCode(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonController getSessionRoleCode method params are {}", params);
        Object roleCode = request.getSession().getAttribute("roleCode");
        return roleCode;
    }
    /**
     * 根据条件分页查询
     */
    @RequestMapping(value = "/queryPage", method = RequestMethod.GET)
    public Object queryPage(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        logger.info("The SupplierManageController queryPage method params are {}", params);
        System.out.println(user.getId());
        if (user.getRoleId().trim().equals("a599f1da-f57c-4afc-a600-b58e15836aa0")) {
            params.put("flag", "0");
            return supplierManageService.queryPage(params);
        } else {
            params.put("flag", "1");
            params.put("userId", user.getId());
            return supplierManageService.queryPage(params);
        }
    }


    @RequestMapping(value = "/queryContent", method = RequestMethod.GET)
    public Object queryContent(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        logger.info("The SupplierManageController queryPage method params are {}", params);
        System.out.println(user.getId());
        if (user.getRoleId().trim().equals("a599f1da-f57c-4afc-a600-b58e15836aa0")) {
            params.put("flag", "0");
            return supplierManageService.queryContent(params);
        } else {
            params.put("flag", "1");
            params.put("userId", user.getId());
            params.put("supplierTypeId",user.getCompanyId());
            return supplierManageService.queryContent(params);
        }
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
        List<ExcelHead> headList = supplierManageMapper._queryColumnAndComment();
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        List<SupplierManageEntity> supplierManageEntities = new ArrayList<>();
        try {
            file.transferTo(uploadFile);
            InputStream inputStream = new FileInputStream(uploadFile);
            List<SupplierManageEntity> supplierManageEntities1 = ExcelUtils.readExcelToEntity(SupplierManageEntity.class, inputStream, uploadFile.getName(), headList);
            String phoneRegex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
            int i = 1;
            for (SupplierManageEntity supplierManageEntity : supplierManageEntities1) {
                if (supplierManageEntity.getName() != null ||  supplierManageEntity.getTaxNum() != null) {
                    if (StringUtils.isEmpty(supplierManageEntity.getName()) ||StringUtils.isEmpty(supplierManageEntity.getTaxNum())) {
                        return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "第" + i + "行，" + supplierManageEntity.getName() + "客商名称/客商编码/客商税号 均不能为空");
                    }
                    i++;
                    boolean phoneMatches = supplierManageEntity.getTelePhone().matches(phoneRegex);
                    if (supplierManageEntity.getTelePhone() == null || supplierManageEntity.getTelePhone() == "" || phoneMatches == false) {
                        return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "该表中第" + i + "行手机号有误，请重新录入");
                    }
                    List<SupplierManageEntity> supplierManageEntities2 = supplierManageMapper.queryByCode(supplierManageEntity.getTaxNum());
                    if (supplierManageEntities2.size() > 0 && supplierManageEntities2 != null) {
                        if (supplierManageEntity.getTaxNum().equals(supplierManageEntities2.get(0).getTaxNum())) {
                            return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "第" + i + "行，" + supplierManageEntity.getName() + "该客商已存在");
                        }
                    }
                    //本次录入检查唯一
                    if (supplierManageEntities != null && supplierManageEntities.size() > 0) {
                        for (int j = 0; j < supplierManageEntities.size(); j++) {
                            SupplierManageEntity supplierManageEntity1 = supplierManageEntities.get(j);
                            if ((supplierManageEntity1.getTaxNum()).equals(supplierManageEntity.getTaxNum())) {
                                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "第" + i + "行，" + supplierManageEntity.getName() + "已经录过，请重新录入");
                            }
                        }
                    }
                    Map map = new HashMap();
                    map.put("supplierType", supplierManageEntity.getSupplierTypeId().replaceAll(" ", ""));
                    List<SupplierTypeEntity> supplierTypeEntities = supplierTypeMapper._queryAll(map);
                    if (supplierTypeEntities.size() > 0 && supplierTypeEntities != null) {
                        supplierManageEntity.setSupplierTypeId(supplierTypeEntities.get(0).getId());
                    } else {
                        return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "第" + i + "行，" + supplierManageEntity.getName() + "客商类型不正确");
                    }
                    String supplierManageId = UUID.randomUUID().toString();
                    supplierManageEntity.setId(supplierManageId);
                    supplierManageEntity.setName(supplierManageEntity.getName().replaceAll(" ", ""));
                    supplierManageEntity.setCode(DateUtil.getSdYMdHmsS());
                    supplierManageEntity.setContact(supplierManageEntity.getContact());
                    supplierManageEntity.setLegalPerson(supplierManageEntity.getLegalPerson());
                    supplierManageEntity.setTelePhone(supplierManageEntity.getTelePhone());
                    supplierManageEntity.setPhone(supplierManageEntity.getPhone());
                    supplierManageEntity.setTaxNum(supplierManageEntity.getTaxNum());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(new Date());
                    supplierManageEntity.setRecordDate(date);
                    supplierManageEntity.setSupplierMark(supplierManageEntity.getSupplierMark());
                    supplierManageEntity.setUserId(user.getId());
                    supplierManageEntities.add(supplierManageEntity);
                }
            }
            logger.info("execute success {}", supplierManageEntities1.size());
        } catch (Exception e) {
            logger.error("{}", e);
            supplierManageEntities.clear();
        } finally {
            uploadFile.delete();
        }
        if (supplierManageEntities != null && supplierManageEntities.size() > 0) {
            for (SupplierManageEntity supplierManageEntity : supplierManageEntities
            ) {
                supplierManageEntity.setState(0);
                supplierManageMapper._addEntity(supplierManageEntity);
                SupplierUserEntity supplierUserEntity = new SupplierUserEntity();
                supplierUserEntity.setId(UUID.randomUUID().toString());
                supplierUserEntity.setSupplierManageId(supplierManageEntity.getId());
                supplierUserEntity.setUserId(user.getId());
                supplierUserEntity.setState(0);
                supplierUserEntity.setSupplierTypeId(supplierManageEntity.getSupplierTypeId());
                supplierUserMapper._addEntity(supplierUserEntity);
            }
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, "上传成功");
    }

    /**
     * 变更记录
     */

    @RequestMapping(value = "/queryChange", method = RequestMethod.GET)
    public Object queryChange(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        logger.info("The SupplierManageController queryChange method params are {}", params);
        return supplierManageService.queryChange(params);
    }

    @RequestMapping(value = "/changeSupplier", method = RequestMethod.GET)
    public Object changeSupplier(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierManageController queryByParams method params are {}", params);
        SupplierManageEntity supplierManageEntity = supplierManageMapper._get(params.get("id").toString());
        SupplierChangeEntity supplierChangeEntity = new SupplierChangeEntity();
        supplierChangeEntity.setId(UUID.randomUUID().toString());
        supplierChangeEntity.setSupplierManageId(supplierManageEntity.getId());
        supplierChangeEntity.setCode(supplierManageEntity.getCode());
        supplierChangeEntity.setName(supplierManageEntity.getName());
        supplierChangeEntity.setTaxNum(supplierManageEntity.getTaxNum());
        supplierChangeEntity.setLegalPerson(supplierManageEntity.getLegalPerson());
        supplierChangeEntity.setPhone(supplierManageEntity.getPhone());
        supplierChangeEntity.setTelePhone(supplierManageEntity.getTelePhone());
        supplierChangeEntity.setRecordDate(supplierManageEntity.getRecordDate());
        supplierChangeEntity.setContact(supplierManageEntity.getContact());
        supplierChangeEntity.setSupplierTypeId(supplierManageEntity.getSupplierTypeId());
        supplierChangeEntity.setSupplierMark(supplierManageEntity.getSupplierMark());
        supplierChangeMapper._addEntity(supplierChangeEntity);
        return new RestResult();
    }


    /**
     * 预备客商
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(@RequestBody String body, HttpServletRequest request) {
        logger.info("The SupplierManageController save method params are {}", body);
        SupplierManageEntity supplierManageEntity = JSONObject.parseObject(body, SupplierManageEntity.class);
        if (supplierManageEntity.getId() != null && supplierManageEntity.getId().length() > 0) {
            return supplierManageService.update(supplierManageEntity);
        } else {
            SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
            List<SupplierManageEntity> supplierManageEntities = supplierManageMapper.queryByCode(supplierManageEntity.getTaxNum());
            if (supplierManageEntities.size() > 0 && supplierManageEntities != null) {
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "改客商已存在，请重新录入");
            } else {
                String supplierManageId = UUID.randomUUID().toString();
                supplierManageEntity.setId(supplierManageId);
                supplierManageEntity.setUserId(user.getId());
                supplierManageEntity.setState(1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(new Date());
                supplierManageEntity.setRecordDate(date);
                supplierManageEntity.setCode(DateUtil.getSdYMdHmsS());
                SupplierUserEntity supplierUserEntity = new SupplierUserEntity();
                supplierUserEntity.setId(UUID.randomUUID().toString());
                supplierUserEntity.setSupplierManageId(supplierManageId);
                supplierUserEntity.setUserId(user.getId());
                supplierUserEntity.setState(0);
                supplierUserEntity.setSupplierTypeId(supplierManageEntity.getSupplierTypeId());
                supplierUserMapper._addEntity(supplierUserEntity);
                return supplierManageService.add(supplierManageEntity);
            }
        }
    }

    @RequestMapping(value = "/queryPreContent", method = RequestMethod.GET)
    public Object queryPreContent(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        logger.info("The SupplierManageController queryPage method params are {}", params);
        System.out.println(user.getId());
        if (user.getRoleId().trim().equals("a599f1da-f57c-4afc-a600-b58e15836aa0")) {
            params.put("flag", "2");
            return supplierManageService.queryPreContent(params);
        } else {
            params.put("flag", "3");
            params.put("supplierTypeId",user.getCompanyId());
            params.put("userId", user.getId());
            return supplierManageService.queryPreContent(params);
        }
    }

    @RequestMapping(value = "/PreImport", method = RequestMethod.POST)
    @Transactional
    public RestResult PreImport(@RequestParam MultipartFile file, HttpServletRequest request) {
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
        List<ExcelHead> headList = supplierManageMapper._queryColumnAndComment();
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        List<SupplierManageEntity> supplierManageEntities = new ArrayList<>();
        try {
            file.transferTo(uploadFile);
            InputStream inputStream = new FileInputStream(uploadFile);
            List<SupplierManageEntity> supplierManageEntities1 = ExcelUtils.readExcelToEntity(SupplierManageEntity.class, inputStream, uploadFile.getName(), headList);
            String phoneRegex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
            int i = 1;
            for (SupplierManageEntity supplierManageEntity : supplierManageEntities1) {
                if (supplierManageEntity.getName() != null || supplierManageEntity.getCode() != null || supplierManageEntity.getTaxNum() != null) {
                    if (StringUtils.isEmpty(supplierManageEntity.getName()) || StringUtils.isEmpty(supplierManageEntity.getTaxNum())) {
                        return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "第" + i + "行，" + supplierManageEntity.getName() + "客商名称/客商编码/客商税号 均不能为空");
                    }
                    i++;
                    boolean phoneMatches = supplierManageEntity.getTelePhone().matches(phoneRegex);
                    if (supplierManageEntity.getTelePhone() == null || supplierManageEntity.getTelePhone() == "" || phoneMatches == false) {
                        return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "该表中第" + i + "行手机号有误，请重新录入");
                    }
                    List<SupplierManageEntity> supplierManageEntities2 = supplierManageMapper.queryByCode(supplierManageEntity.getTaxNum());
                    if (supplierManageEntities2.size() > 0 && supplierManageEntities2 != null) {
                        if (supplierManageEntity.getTaxNum().equals(supplierManageEntities2.get(0).getTaxNum()) ) {
                            return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "第" + i + "行，" + supplierManageEntity.getName() + "该客商已存在");
                        }
                    }
                    //本次录入检查唯一
                    if (supplierManageEntities != null && supplierManageEntities.size() > 0) {
                        for (int j = 0; j < supplierManageEntities.size(); j++) {
                            SupplierManageEntity supplierManageEntity1 = supplierManageEntities.get(j);
                            if ((supplierManageEntity1.getTaxNum()).equals(supplierManageEntity.getTaxNum())) {
                                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "第" + i + "行，" + supplierManageEntity.getName() + "已经录过，请重新录入");
                            }
                        }
                    }
                    Map map = new HashMap();
                    map.put("supplierType", supplierManageEntity.getSupplierTypeId().replaceAll(" ", ""));
                    List<SupplierTypeEntity> supplierTypeEntities = supplierTypeMapper._queryAll(map);
                    if (supplierTypeEntities.size() > 0 && supplierTypeEntities != null) {
                        supplierManageEntity.setSupplierTypeId(supplierTypeEntities.get(0).getId());
                    } else {
                        return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "第" + i + "行，" + supplierManageEntity.getName() + "客商类型不正确");
                    }
                    String supplierManageId = UUID.randomUUID().toString();
                    supplierManageEntity.setId(supplierManageId);
                    supplierManageEntity.setName(supplierManageEntity.getName().replaceAll(" ", ""));
                    supplierManageEntity.setCode(DateUtil.getSdYMdHmsS());
                    supplierManageEntity.setContact(supplierManageEntity.getContact());
                    supplierManageEntity.setLegalPerson(supplierManageEntity.getLegalPerson());
                    supplierManageEntity.setTelePhone(supplierManageEntity.getTelePhone());
                    supplierManageEntity.setPhone(supplierManageEntity.getPhone());
                    supplierManageEntity.setTaxNum(supplierManageEntity.getTaxNum());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdf.format(new Date());
                    supplierManageEntity.setRecordDate(date);
                    supplierManageEntity.setSupplierMark(supplierManageEntity.getSupplierMark());
                    supplierManageEntity.setUserId(user.getId());
                    supplierManageEntities.add(supplierManageEntity);
                }
            }
            logger.info("execute success {}", supplierManageEntities1.size());
        } catch (Exception e) {
            logger.error("{}", e);
            supplierManageEntities.clear();
        } finally {
            uploadFile.delete();
        }
        if (supplierManageEntities != null && supplierManageEntities.size() > 0) {
            for (SupplierManageEntity supplierManageEntity : supplierManageEntities
            ) {
                supplierManageEntity.setState(1);
                supplierManageMapper._addEntity(supplierManageEntity);
                SupplierUserEntity supplierUserEntity = new SupplierUserEntity();
                supplierUserEntity.setId(UUID.randomUUID().toString());
                supplierUserEntity.setSupplierManageId(supplierManageEntity.getId());
                supplierUserEntity.setUserId(user.getId());
                supplierUserEntity.setState(0);
                supplierUserEntity.setSupplierTypeId(supplierManageEntity.getSupplierTypeId());
                supplierUserMapper._addEntity(supplierUserEntity);
            }
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, "上传成功");
    }


    @RequestMapping(value = "/updateById", method = RequestMethod.GET)
    public Object updateById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The SupplierManageController queryByParams method params are {}", params);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        SupplierManageEntity supplierManageEntity = supplierManageMapper._get(params.get("id").toString());
        supplierManageEntity.setState(0);
        SupplierUserEntity supplierUserEntity = new SupplierUserEntity();
        supplierUserEntity.setId(UUID.randomUUID().toString());
        supplierUserEntity.setSupplierManageId(supplierManageEntity.getId());
        supplierUserEntity.setUserId(user.getId());
        supplierUserEntity.setState(0);
        supplierUserEntity.setSupplierTypeId(supplierManageEntity.getSupplierTypeId());
        supplierUserMapper._addEntity(supplierUserEntity);
        return supplierManageMapper._updateEntity(supplierManageEntity);
    }


    @RequestMapping(value = "/export", method = RequestMethod.GET)
    protected void export(HttpServletRequest request, HttpServletResponse response) {
        Map params = HttpUtils.getParams(request);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        if (user.getRoleId().trim().equals("a599f1da-f57c-4afc-a600-b58e15836aa0")) {
            params.put("flag", "0");
        } else {
            params.put("flag", "1");
            params.put("userId", user.getId());
        }
        Map map = this.fieldExport(params);
        String[] header = (String[]) map.get("header");
        List<String[]> data = (List<String[]>) map.get("data");
        ExcelStyle style = (ExcelStyle) map.get("style");
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String((style.getXlsName() + ".xlsx").getBytes(), "iso-8859-1"));
            OutputStream out = response.getOutputStream();
            ExportExcel.export(header, data, style, out);
        } catch (Exception e) {
            logger.error("exportExcel error:{}", e);
        }
    }


    public Map fieldExport(Map params) {
        Map resultMap = new HashMap();
        ExcelStyle style = new ExcelStyle();
        List<String[]> data = new ArrayList();
        List<SupplierManageEntity> supplierManageEntities = supplierManageMapper.queryContent(params);
        SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
        String tStamp = dfs.format(new Date());
        style.setColumnWidth(25);
        style.setSheetName("导出");
        style.setXlsName("客商信息表_" + tStamp);
        for (SupplierManageEntity entity : supplierManageEntities) {
            if (entity!=null){
                Map map = new HashMap();
                map.put("supplierManageId", entity.getId());
                List<ViolationEntity> violationEntities = violationMapper._queryBySupplierId(map);
                if (violationEntities.size() > 0 && violationEntities != null) {
                    for (int i = 0; i < violationEntities.size(); i++) {
                        if (violationEntities.get(i).getResult() == 0) {
                            data.add(new String[]{
                                    entity.getCode(),
                                    entity.getName(),
                                    entity.getTaxNum(),
                                    entity.getLegalPerson(),
                                    entity.getTelePhone(),
                                    violationEntities.get(i).getName(),
                                    violationEntities.get(i).getStartTime(),
                                    "已处理"
                            });
                        } else {
                            data.add(new String[]{
                                    entity.getCode(),
                                    entity.getName(),
                                    entity.getTaxNum(),
                                    entity.getLegalPerson(),
                                    entity.getTelePhone(),
                                    violationEntities.get(i).getName(),
                                    violationEntities.get(i).getStartTime(),
                                    "未处理"
                            });
                        }
                    }
                } else {
                    data.add(new String[]{
                            entity.getCode(),
                            entity.getName(),
                            entity.getTaxNum(),
                            entity.getLegalPerson(),
                            entity.getTelePhone(),
                    });
                }
            }
        }
        resultMap.put("header",
                new String[]{"编码", "姓名", "税号", "法人", "手机号", "违规内容", "违规时间", "处理结果"});
        resultMap.put("data", data);
        resultMap.put("style", style);
        return resultMap;
    }
}
