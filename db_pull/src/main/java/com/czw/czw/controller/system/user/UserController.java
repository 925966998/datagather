package com.czw.czw.controller.system.user;

import com.czw.czw.controller.base.BaseController;
import com.czw.czw.model.Page;
import com.czw.czw.model.PageData;
import com.czw.czw.model.system.Role;
import com.czw.czw.service.system.menu.MenuService;
import com.czw.czw.service.system.role.RoleService;
import com.czw.czw.service.system.user.SystemUserService;
import com.czw.czw.util.AppUtil;
import com.czw.czw.util.Const;
import com.czw.czw.util.Jurisdiction;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 类名称：UserController
 * 创建人：
 * 创建时间：2014年6月28日
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    String menuUrl = "user/listUsers"; //菜单地址(权限用)
    @Resource(name = "systemUserService")
    private SystemUserService userService;
    @Resource(name = "roleService")
    private RoleService roleService;
    @Resource(name = "menuService")
    private MenuService menuService;


    /**
     * 保存用户
     */
    @RequestMapping(value = "/saveU")
    public ModelAndView saveU(PrintWriter out) throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();

        pd.put("USER_ID", this.get32UUID());    //ID
        pd.put("RIGHTS", "");                    //权限
        pd.put("LAST_LOGIN", "");                //最后登录时间
        pd.put("IP", "");                        //IP
        pd.put("STATUS", "0");                    //状态
        pd.put("SKIN", "default");                //默认皮肤

        pd.put("PASSWORD", new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString());
        if (null == userService.findByUId(pd)) {
            if (Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
                userService.saveU(pd);
            } //判断新增权限
            mv.addObject("msg", "success");
        } else {
            mv.addObject("msg", "failed");
        }
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 判断用户名是否存在
     */
    @RequestMapping(value = "/hasU")
    @ResponseBody
    public Object hasU() {
        Map<String, String> map = new HashMap<String, String>();
        String errInfo = "success";
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            if (userService.findByUId(pd) != null) {
                errInfo = "error";
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        map.put("result", errInfo);                //返回结果
        return AppUtil.returnObject(new PageData(), map);
    }

    /**
     * 判断邮箱是否存在
     */
    @RequestMapping(value = "/hasE")
    @ResponseBody
    public Object hasE() {
        Map<String, String> map = new HashMap<String, String>();
        String errInfo = "success";
        PageData pd = new PageData();
        try {
            pd = this.getPageData();

            if (userService.findByUE(pd) != null) {
                errInfo = "error";
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        map.put("result", errInfo);                //返回结果
        return AppUtil.returnObject(new PageData(), map);
    }

    /**
     * 判断编码是否存在
     */
    @RequestMapping(value = "/hasN")
    @ResponseBody
    public Object hasN() {
        Map<String, String> map = new HashMap<String, String>();
        String errInfo = "success";
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            if (userService.findByUN(pd) != null) {
                errInfo = "error";
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        map.put("result", errInfo);                //返回结果
        return AppUtil.returnObject(new PageData(), map);
    }

    /**
     * 修改用户
     */
    @RequestMapping(value = "/editU")
    public ModelAndView editU() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        if (pd.getString("PASSWORD") != null && !"".equals(pd.getString("PASSWORD"))) {
            pd.put("PASSWORD", new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString());
        }
        if (Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
            userService.editU(pd);
        }
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 去修改用户页面
     */
    @RequestMapping(value = "/goEditU")
    public ModelAndView goEditU() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();

        //顶部修改个人资料
        String fx = pd.getString("fx");

        //System.out.println(fx);

        if ("head".equals(fx)) {
            mv.addObject("fx", "head");
        } else {
            mv.addObject("fx", "user");
        }

        List<Role> roleList = roleService.listAllERRoles();            //列出所有二级角色
        pd = userService.findByUiId(pd);                            //根据ID读取
        mv.setViewName("system/user/user_edit");
        mv.addObject("msg", "editU");
        mv.addObject("pd", pd);
        mv.addObject("roleList", roleList);

        return mv;
    }

    /**
     * 去新增用户页面
     */
    @RequestMapping(value = "/goAddU")
    public ModelAndView goAddU() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        List<Role> roleList;

        roleList = roleService.listAllERRoles();            //列出所有二级角色

        mv.setViewName("system/user/user_edit");
        mv.addObject("msg", "saveU");
        mv.addObject("pd", pd);
        mv.addObject("roleList", roleList);

        return mv;
    }

    /**
     * 显示用户列表(用户组)
     */
    @RequestMapping(value = "/listUsers")
    public ModelAndView listUsers(Page page) throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();

        String USERNAME = pd.getString("USERNAME");

        if (null != USERNAME && !"".equals(USERNAME)) {
            USERNAME = USERNAME.trim();
            pd.put("USERNAME", USERNAME);
        }

        String lastLoginStart = pd.getString("lastLoginStart");
        String lastLoginEnd = pd.getString("lastLoginEnd");

        if (lastLoginStart != null && !"".equals(lastLoginStart)) {
            lastLoginStart = lastLoginStart + " 00:00:00";
            pd.put("lastLoginStart", lastLoginStart);
        }
        if (lastLoginEnd != null && !"".equals(lastLoginEnd)) {
            lastLoginEnd = lastLoginEnd + " 00:00:00";
            pd.put("lastLoginEnd", lastLoginEnd);
        }

        page.setPd(pd);
        List<PageData> userList = userService.listPdPageUser(page);            //列出用户列表
        List<Role> roleList = roleService.listAllERRoles();                        //列出所有二级角色

        mv.setViewName("system/user/user_list");
        mv.addObject("userList", userList);
        mv.addObject("roleList", roleList);
        mv.addObject("pd", pd);
        mv.addObject(Const.SESSION_QX, this.getHC());    //按钮权限
        return mv;
    }


    /**
     * 显示用户列表(tab方式)
     */
    @RequestMapping(value = "/listtabUsers")
    public ModelAndView listtabUsers(Page page) throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        List<PageData> userList = userService.listAllUser(pd);            //列出用户列表
        mv.setViewName("system/user/user_tb_list");
        mv.addObject("userList", userList);
        mv.addObject("pd", pd);
        mv.addObject(Const.SESSION_QX, this.getHC());    //按钮权限
        return mv;
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/deleteU")
    public void deleteU(PrintWriter out) {
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            if (Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
                userService.deleteU(pd);
            }
            out.write("success");
            out.close();
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }

    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "/deleteAllU")
    @ResponseBody
    public Object deleteAllU() {
        PageData pd = new PageData();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pd = this.getPageData();
            List<PageData> pdList = new ArrayList<PageData>();
            String USER_IDS = pd.getString("USER_IDS");

            if (null != USER_IDS && !"".equals(USER_IDS)) {
                String ArrayUSER_IDS[] = USER_IDS.split(",");
                if (Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
                    userService.deleteAllU(ArrayUSER_IDS);
                }
                pd.put("msg", "ok");
            } else {
                pd.put("msg", "no");
            }

            pdList.add(pd);
            map.put("list", pdList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        } finally {
            logAfter(logger);
        }
        return AppUtil.returnObject(pd, map);
    }
    //===================================================================================================


    /*
     * 导出用户信息到EXCEL
     * @return
     */
    @RequestMapping(value = "/excel")
    @ResponseBody
    public ModelAndView exportExcel(HttpServletResponse response) {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            if (Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
                //检索条件===
                String USERNAME = pd.getString("USERNAME");
                if (null != USERNAME && !"".equals(USERNAME)) {
                    USERNAME = USERNAME.trim();
                    pd.put("USERNAME", USERNAME);
                }
                String lastLoginStart = pd.getString("lastLoginStart");
                String lastLoginEnd = pd.getString("lastLoginEnd");
                if (lastLoginStart != null && !"".equals(lastLoginStart)) {
                    lastLoginStart = lastLoginStart + " 00:00:00";
                    pd.put("lastLoginStart", lastLoginStart);
                }
                if (lastLoginEnd != null && !"".equals(lastLoginEnd)) {
                    lastLoginEnd = lastLoginEnd + " 00:00:00";
                    pd.put("lastLoginEnd", lastLoginEnd);
                }
                //检索条件===

                Map<String, Object> dataMap = new HashMap<String, Object>();
                List<String> titles = new ArrayList<String>();

                titles.add("用户名");        //1
                titles.add("编号");        //2
                titles.add("姓名");            //3
                titles.add("职位");            //4
                titles.add("手机");            //5
                titles.add("邮箱");            //6
                titles.add("最近登录");        //7
                titles.add("上次登录IP");    //8

                dataMap.put("titles", titles);

                List<PageData> userList = userService.listAllUser(pd);
//				List<PageData> varList = new ArrayList<PageData>();
//				for(int i=0;i<userList.size();i++){
//					PageData vpd = new PageData();
//					vpd.put("var1", userList.get(i).getString("USERNAME"));		//1
//					vpd.put("var2", userList.get(i).getString("NUMBER"));		//2
//					vpd.put("var3", userList.get(i).getString("NAME"));			//3
//					vpd.put("var4", userList.get(i).getString("ROLE_NAME"));	//4
//					vpd.put("var5", userList.get(i).getString("PHONE"));		//5
//					vpd.put("var6", userList.get(i).getString("EMAIL"));		//6
//					vpd.put("var7", userList.get(i).getString("LAST_LOGIN"));	//7
//					vpd.put("var8", userList.get(i).getString("IP"));			//8
//					varList.add(vpd);
//				}
//				dataMap.put("varList", varList);
                try {
                    // 1.创建工作簿
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    // 1.1创建合并单元格对象
                    CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, 3);// 起始行,结束行,起始列,结束列
                    // 2.创建工作表
                    HSSFSheet sheet = workbook.createSheet("用户列表");
                    // 2.1加载合并单元格对象
                    sheet.addMergedRegion(callRangeAddress);
                    // 1.2头标题样式
                    HSSFCellStyle headStyle = createCellStyle(workbook, (short) 16);
                    // 1.3列标题样式
                    HSSFCellStyle colStyle = createCellStyle(workbook, (short) 13);
                    // 设置默认列宽
                    sheet.setDefaultColumnWidth(25);
                    // 3.创建行
                    // 3.1创建头标题行;并且设置头标题
                    HSSFRow row = sheet.createRow(0);
                    HSSFCell cell = row.createCell(0);

                    // 加载单元格样式
                    cell.setCellValue("用户列表");

                    // 3.2创建列标题;并且设置列标题
                    HSSFRow row2 = sheet.createRow(1);
                    for (int i = 0; i < titles.size(); i++) {
                        HSSFCell cell2 = row2.createCell(i);
                        // 加载单元格样式
                        cell2.setCellValue(titles.get(i));
                    }
                    HSSFCellStyle cellStyle = workbook.createCellStyle();
                    CreationHelper creationHelper = workbook.getCreationHelper();
                    cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm"));

                    HSSFCellStyle style = workbook.createCellStyle();
                    // 4.操作单元格;将用户列表写入excel
                    for (int i = 0; i < userList.size(); i++) {
                        HSSFRow row3 = sheet.createRow(i + 2);
                        HSSFCell cell1 = row3.createCell(0);
                        cell1.setCellStyle(style);
                        cell1.setCellValue(userList.get(i).getString("USERNAME"));
                        HSSFCell cell2 = row3.createCell(1);
                        cell2.setCellStyle(style);
                        cell2.setCellValue(userList.get(i).getString("NUMBER"));
                        HSSFCell cell3 = row3.createCell(2);
                        cell3.setCellStyle(style);
                        cell3.setCellValue(userList.get(i).getString("NAME"));
                        HSSFCell cell4 = row3.createCell(3);
                        cell4.setCellStyle(style);
                        cell4.setCellValue(userList.get(i).getString("ROLE_NAME"));
                        HSSFCell cell5 = row3.createCell(4);
                        cell5.setCellStyle(style);
                        cell5.setCellValue(userList.get(i).getString("PHONE"));
                        HSSFCell cell6 = row3.createCell(5);
                        cell6.setCellStyle(style);
                        cell6.setCellValue(userList.get(i).getString("EMAIL"));
                        HSSFCell cell7 = row3.createCell(6);
                        cell7.setCellStyle(style);
                        cell7.setCellValue(userList.get(i).getString("LAST_LOGIN"));
                        HSSFCell cell8 = row3.createCell(7);
                        cell8.setCellStyle(style);
                        cell8.setCellValue(userList.get(i).getString("IP"));
                    }

                    // 这里设置的文件格式是application/x-excel
                    response.setContentType("application/x-excel");

                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + new String(("用户" + ".xls").getBytes(), "ISO-8859-1"));

                    ServletOutputStream outputStream = response.getOutputStream();

                    workbook.write(outputStream);
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    // 5.输出
                    workbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//				ObjectExcelView erv = new ObjectExcelView();					//执行excel操作
//				mv = new ModelAndView(erv,dataMap);
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }
    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontsize) {
        // TODO Auto-generated method stub
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        // style.setVerticalAlignment(HorizontalAlignment.CENTER);//垂直居中
        // 创建字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(fontsize);
        // 加载字体
        style.setFont(font);
        return style;
    }
    /**
     * 打开上传EXCEL页面
     */
    @RequestMapping(value = "/goUploadExcel")
    public ModelAndView goUploadExcel() throws Exception {
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("system/user/uploadexcel");
        return mv;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }


    /* ===============================权限================================== */
    public Map<String, String> getHC() {
        //Map<String, String> map=new HashMap<String,String>();
        //map.put("add","1");
        //map.put("del", "1");
        //map.put("edit", "1");
        //map.put("cha", "1");
        //return map;
        Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
        Session session = currentUser.getSession();
        return (Map<String, String>) session.getAttribute(Const.SESSION_QX);
    }
    /* ===============================权限================================== */
}
