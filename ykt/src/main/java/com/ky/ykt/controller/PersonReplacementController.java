package com.ky.ykt.controller;

import com.alibaba.fastjson.JSONObject;
import com.ky.ykt.entity.*;
import com.ky.ykt.excle.ExcelHead;
import com.ky.ykt.excle.ExcelUtils;
import com.ky.ykt.logUtil.Log;
import com.ky.ykt.mapper.*;
import com.ky.ykt.mybatis.RestResult;
import com.ky.ykt.service.PersonReplacementService;
import com.ky.ykt.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @class: monitor
 * @classDesc: 功能描述（）
 * @author: yaoWieJie
 * @createTime: 2020-03-10 8:38
 * @version: v1.0
 */
@RestController
@RequestMapping("/ky-ykt/personPeplacement")
public class PersonReplacementController {

    private static final Logger logger = LoggerFactory.getLogger(PersonReplacementController.class);
    
    @Autowired
    PersonReplacementService personReplacementService;
    @Autowired
    PersonReplacementMapper personReplacementMapper;
    @Autowired
    ProjectDetailMapper projectDetailMapper;
    @Autowired
    PersonMapper personMapper;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    AreasMapper areasMapper;
    @Autowired
    PersonUploadMapper personUploadMapper;
    @Autowired
    ProjectReplacementMapper projectReplacementMapper;
    /**
     * 根据条件查询数据（不分页）
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryByParams", method = RequestMethod.GET)
    public Object queryByParams(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonReplacementController queryByParams method params are {}", params);
        return personReplacementService.queryAll(params);
    }

    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryById", method = RequestMethod.GET)
    public Object queryById(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonReplacementController queryById method params are {}", params);
        return personReplacementService.queryReplacementById(params);

    }
    /**
     * 根据Id查询数据
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/queryBypersonId", method = RequestMethod.GET)
    public Object queryBypersonId(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonReplacementController queryById method params are {}", params);
        return personReplacementService.queryReplacementBypersonId(params);

    }
    /**
     * 新增OR更新数据
     */
    @Log(description = "补发管理新增,修改操作", module = "补发管理")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object saveOrUpdate(@RequestBody String body, HttpServletRequest request) {
        logger.info("The PersonReplacementController saveOrUpdate method params are {}", body);
        PersonReplacementEntity personReplacementEntity = JSONObject.parseObject(body, PersonReplacementEntity.class);
        return personReplacementService.update(personReplacementEntity);
    }

    /**
     * 逻辑删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "补发管理逻辑删除操作", module = "补发管理")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonReplacementController delete method params is {}", params);
        return personReplacementService.delete(params.get("id").toString());
    }

    /**
     * 物理删除
     */
    @Log(description = "补发管理物理删除操作", module = "补发管理")
    @RequestMapping(value = "/deleteForce", method = RequestMethod.GET)
    public Object deleteForce(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonReplacementController deleteForce method params is {}", params);
        String id = params.get("id").toString();
        if (id.contains(",")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                personReplacementService._deleteForce(split[i]);
            }
        } else {
            personReplacementService._deleteForce(params.get("id").toString());
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
        logger.info("The PersonReplacementController queryPage method params are {}", params);
        return personReplacementService.queryPage(params);
    }

    /**
     * 删除
     */
    @SuppressWarnings("rawtypes")
    @Log(description = "补发管理删除操作", module = "补发管理")
    @RequestMapping(value = "/deleteOne", method = RequestMethod.GET)
    public Object deleteOne(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonReplacementController delete method params is {}", params);
        return personReplacementService.delete(params.get("id").toString());
    }

    /**
     * 删除多个
     */
    @Log(description = "行政区域管理删除操作", module = "行政区域管理")
    @RequestMapping(value = "deleteMoney", method = RequestMethod.GET)
    public Object deleteMoney(HttpServletRequest request) {
        Map params = HttpUtils.getParams(request);
        logger.info("The PersonReplacementController deleteForce method params is {}", params);
        if (params.get("ids") != null) {
            String idsStr = params.get("ids").toString();
            String[] ids = idsStr.split(",");
            for (String id : ids
            ) {
                personReplacementService.delete(id);
            }
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG);
    }

    /**
     * 补发
     */
    @Log(description = "补发管理新增,修改操作", module = "补发管理")
    @RequestMapping(value = "/personPeplacementById", method = RequestMethod.POST, produces = "application/json;UTF-8")
    public Object personPeplacementById(PersonReplacementEntity personReplacementEntity,HttpServletRequest request) {
        logger.info("The PersonReplacementController saveOrUpdate method params are {}", personReplacementEntity);
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        personReplacementEntity.setId(UUID.randomUUID().toString());
        personReplacementEntity.setUserId(user.getId());
        personReplacementEntity.setDepartmentId(user.getDepartmentId());
        return personReplacementService.add(personReplacementEntity);
    }

    //补交上传
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @Transactional
    public RestResult importExcel(@RequestParam MultipartFile file, HttpServletRequest request) {
        logger.info("The file is {}", file);
        //String projectId = request.getParameter("projectId");
        if (file == null || file.getName().equals("") || file.getSize() <= 0) {
            return new RestResult(40000, RestResult.ERROR_MSG, "文件不合法,请检查文件是否为Excel文件");
        }
        String fileName = file.getOriginalFilename();
        try {
            ExcelUtils.checkFile(fileName);
        } catch (Exception e) {
            return new RestResult(40000, RestResult.ERROR_MSG, "文件不合法,请检查文件是否为Excel文件");
        }
        //String filePath = request.getSession().getServletContext().getRealPath("upload/");
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String path = filePath + fileName;
        File uploadFile = new File(path);
        List<ExcelHead> headList = personMapper._queryColumnAndComment();
        SysUserEntity user = (SysUserEntity) request.getSession().getAttribute("user");
        try {
            file.transferTo(uploadFile);
            InputStream inputStream = new FileInputStream(uploadFile);
            List<PersonEntity> personEntities = ExcelUtils.readExcelToEntity(PersonEntity.class, inputStream, uploadFile.getName(), headList);
            //计算发放剩余金额
            //校验录入的表的字段是否合格
            //EXCAL表身份证号校验
            String idCardNoRegex = "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)|(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)";
            //EXCAL手机号校验
            String phoneRegex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
            int i = 1;
            String projectDetailId = UUID.randomUUID().toString();
            PersonReplacementEntity personReplacementEntity = new PersonReplacementEntity();
            ProjectReplacementEntity projectReplacementEntity = new ProjectReplacementEntity();
            for (PersonEntity personEntity : personEntities) {
                if (personEntity.getName() != null || personEntity.getBankCardNo() != null || personEntity.getAddress() != null
                        || personEntity.getCounty() != null || personEntity.getIdCardNo() != null || personEntity.getPhone() != null
                        || personEntity.getGrantAmount() != null) {

                    if (StringUtils.isEmpty(personEntity.getName()) || StringUtils.isEmpty(personEntity.getBankCardNo()) || StringUtils.isEmpty(personEntity.getGrantAmount())
                            || StringUtils.isEmpty(personEntity.getIdCardNo()) || StringUtils.isEmpty(personEntity.getPhone())) {
                        return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "姓名/银行卡号/手机号/身份证号/发放金额均不能为空");
                    }

                    i++;
                    if (personEntity.getGrantAmount() == null || personEntity.getGrantAmount() == "") {
                        return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "该表中第" + i + "行发放金额有误，请重新录入");
                    }
                    AreasEntity areasEntity = areasMapper._queryCname(personEntity.getCounty());
                    if (areasEntity == null) {
                        return new RestResult(40000, RestResult.ERROR_MSG, "该表中第" + i + "行所属区域有误，请重新录入");
                    }
                    boolean idCardMatches = personEntity.getIdCardNo().matches(idCardNoRegex);
                    if (personEntity.getIdCardNo() == null || personEntity.getIdCardNo() == "" || idCardMatches == false) {
                        return new RestResult(40000, RestResult.ERROR_MSG, "该表中第" + i + "行身份证号有误，请重新录入");
                    }
                    boolean phoneMatches = personEntity.getPhone().matches(phoneRegex);
                    if (personEntity.getIdCardNo() == null || personEntity.getIdCardNo() == "" || phoneMatches == false) {
                        return new RestResult(40000, RestResult.ERROR_MSG, "该表中第" + i + "行手机号有误，请重新录入");
                    }
                    //根据个人信息查询personId
                    Map hashMap = new HashMap();
                    hashMap.put("name",personEntity.getName());
                    hashMap.put("idCardNo",personEntity.getIdCardNo());
                    hashMap.put("userId",personEntity.getUserId());
                    hashMap.put("departmentId",personEntity.getDepartmentId());
                    List<PersonEntity> personEntityList = personMapper._queryAll(hashMap);
                    if (personEntityList.size()<=0){
                        return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "所选项目没有此人员，请重新选择");
                    }
                    //插入补发人员表
                    personReplacementEntity.setId(UUID.randomUUID().toString());
                    personReplacementEntity.setProjectId(projectDetailId);
                    personReplacementEntity.setUserId(user.getId());
                    //新增状态是未提交 3
                    personReplacementEntity.setStatus("3");
                    personReplacementEntity.setDepartmentId(user.getDepartmentId());
                    personReplacementEntity.setReplacementAmount(personEntity.getGrantAmount());
                    personReplacementEntity.setPersonId(personEntityList.get(0).getId());
                    personReplacementMapper._addEntity(personReplacementEntity);
                    /*
                    //插入补发项目表
                    projectReplacementEntity.setProjectDetailId(projectDetailId);
                    projectReplacementEntity.setProjectId(personEntityList.get(0).getProjectId());
                    projectReplacementMapper._addEntity(projectReplacementEntity);
                    */
                }

            }
            logger.info("execute success {}", personEntities.size());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        } finally {
            uploadFile.delete();
        }
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, "上传成功");
    }


    @Log(description = "补发录入提交操作", module = "人员管理")
    @RequestMapping(value = "/doSubmit/{projectId}", method = RequestMethod.POST)
    @Transactional
    public Object doSubmit(HttpSession session, @PathVariable String projectId) {
        SysUserEntity user = (SysUserEntity) session.getAttribute("user");
        HashMap params = new HashMap();
        params.put("userId", user.getId());
        params.put("status", "3");//未提交
        List<PersonReplacementEntity> personReplacementEntities = personReplacementMapper._queryAll(params);
        //通过personId查询该人的上一次发放的项目Id
        PersonUploadEntity personUploadEntity = personUploadMapper.querypersonId(personReplacementEntities.get(0).getPersonId());
        //通过项目Id查询发放剩余金额
        ProjectDetailEntity projectDeEntity = projectDetailMapper.queryId(personUploadEntity.getProjectId());
        //计算总共的补发金额
        BigDecimal totalAmount = new BigDecimal("0");
        for (PersonReplacementEntity personReplacementEntity:personReplacementEntities) {
            //获得补发金额
            BigDecimal bigDecimal = new BigDecimal(personReplacementEntity.getReplacementAmount());
            totalAmount = totalAmount.add(bigDecimal);
        }
        //判断上一轮发放的剩余金额
        if(projectDeEntity.getSurplusAmount().compareTo(totalAmount) != 1){
            return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "项目发放剩余金额不足，请重新选择");
        }
        String projectDetailId = "";
        if (personReplacementEntities.size() > 0) {
            for (PersonReplacementEntity personReplacementEntity : personReplacementEntities) {
                projectDetailId = personReplacementEntity.getProjectId();
                personReplacementEntity.setStatus("4");//已提交
                personReplacementMapper._updateEntity(personReplacementEntity);

                //修改人员档案中的发放金额
                //查询该人员的档案
                PersonUploadEntity personUploadEntity1 = personUploadMapper.querypersonId(personReplacementEntity.getPersonId());
                BigDecimal grantAmount = new BigDecimal(personUploadEntity1.getGrantAmount());
                BigDecimal grantAmount1 = new BigDecimal(personReplacementEntity.getReplacementAmount());
                personUploadEntity1.setGrantAmount(grantAmount.add(grantAmount1).toString());
                //把新生成的projectId再次赋值给该人员
                personUploadEntity1.setProjectId(personReplacementEntity.getProjectId());
                personUploadMapper._updateEntity(personUploadEntity1);
            }
            //通过projectDetailId查询projectDeatil记录
            //ProjectDetailEntity projectDetailEntity = projectDetailMapper._get(projectDetailId);
            ProjectDetailEntity projectDetailEntity = new ProjectDetailEntity();
            ProjectEntity projectEntity = projectMapper._get(projectId);
            //上一轮发放的剩余金额为本次发放的金额
            projectDetailEntity.setTotalAmount(projectDeEntity.getSurplusAmount());
            projectDetailEntity.setPaymentAmount(totalAmount);
            if(projectDeEntity.getSurplusAmount().compareTo(totalAmount) != 1){
                return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "发放剩余金额不足");
            }
            //发放剩余金额
            projectDetailEntity.setSurplusAmount(projectDeEntity.getSurplusAmount().subtract(totalAmount));
            projectDetailEntity.setProjectId(projectId);
            projectDetailEntity.setProjectName(projectEntity.getProjectName());
            projectDetailEntity.setStartTime(new Date());
            //操作人
            projectDetailEntity.setOperUser(user.getId());
            //操作单位
            projectDetailEntity.setOperDepartment(user.getDepartmentId());
            projectDetailEntity.setPaymentDepartment(projectEntity.getPaymentDepartment());
            projectDetailEntity.setState(0);
            projectDetailEntity.setId(projectDetailId);
            projectDetailMapper._addEntity(projectDetailEntity);

            //插入projectReplacement记录
            ProjectReplacementEntity projectReplacementEntity = new ProjectReplacementEntity();
            projectReplacementEntity.setId(UUID.randomUUID().toString());
            projectReplacementEntity.setProjectId(projectDeEntity.getId());
            projectReplacementEntity.setProjectDetailId(projectDetailId);
            projectReplacementMapper._addEntity(projectReplacementEntity);
        } else {
            return new RestResult(RestResult.ERROR_CODE, RestResult.ERROR_MSG, "数据不能为空，请重新录入！！");
        }
        return new RestResult();
    }

}