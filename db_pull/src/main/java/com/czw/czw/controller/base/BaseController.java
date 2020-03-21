package com.czw.czw.controller.base;


import com.czw.czw.model.PageData;
import com.czw.czw.model.system.User;
import com.czw.czw.model.system.UserInfo;
import com.czw.czw.util.Const;
import com.czw.czw.util.Logger;
import com.czw.czw.util.UuidUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {

    protected Logger logger = Logger.getLogger(this.getClass());

    private static final long serialVersionUID = 6357869213649815390L;


    /**
     * 得到ModelAndView
     */
    public ModelAndView getModelAndView() {
        return new ModelAndView();
    }

    /**
     * 得到request对象
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();

        return request;
    }

    /**
     * 得到request对象
     */
    public HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletWebRequest) RequestContextHolder
                .getRequestAttributes()).getResponse();
        return response;
    }

    /**
     * 得到32位的uuid
     *
     * @return
     */
    public String get32UUID() {

        return UuidUtil.get32UUID();
    }

    /**
     * 得到PageData
     */
    public PageData getPageData() {
        PageData pData = new PageData(this.getRequest());
        return pData;
    }


    public static void logBefore(Logger logger, String interfaceName) {
		/*logger.info("");
		logger.info("start");
		logger.info(interfaceName);*/
    }

    public static void logAfter(Logger logger) {
		/*logger.info("end");
		logger.info("");*/
    }


    /**
     * @author liuhaowen
     * @Description 管理员获取用户
     * @data 2016年3月29日 下午4:50:48
     */
    public User getAdmin() {
        Subject currentUser = SecurityUtils.getSubject(); // shiro管理的session
        Session session = currentUser.getSession();
        User user = (User) session.getAttribute(Const.SESSION_USER);

        return user;
    }

    /**
     * @author liuhaowen
     * @Description 用户
     * @data 2016年3月29日 下午4:51:05
     */
    public UserInfo getUser() {

        Subject currentUser = SecurityUtils.getSubject(); // shiro管理的session
        Session session = currentUser.getSession();
        UserInfo user = (UserInfo) session.getAttribute(Const.SESSION_AUSER);
        return user;
    }

    /**
     * 获取用户从session
     *
     * @param
     * @throws Exception
     */

    public PageData findUser() throws Exception {
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        PageData data = (PageData) session.getAttribute("SHOPUSERSESSION");
        return data;
    }

    /**
     * 保存用户到session
     *
     * @param pd
     * @throws Exception
     */
    public void saveUser(PageData pd) throws Exception {
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("SHOPUSERSESSION", pd);
    }

    /**
     * 清除用户到session
     *
     * @throws Exception
     */
    public void clear() throws Exception {
        HttpServletRequest request = this.getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("SHOPUSERSESSION");
    }


    /**
     * 获取连接全路径
     *
     * @return
     */
    public String getURL() {
        HttpServletRequest request = this.getRequest();
        StringBuffer url = request.getRequestURL();
        if (request.getQueryString() != null) {
            url.append("?");
            url.append(request.getQueryString());
        }
        return url.toString();
    }

    /**
     * 返回错误页面
     *
     * @return
     */
    public ModelAndView returnErrorPage(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e.getMessage());
        mv.setViewName("error");
        return mv;
    }

    /**
     * 日志
     * OPERATION_TYPE 操作类型  1增2改3删除4审核5其他
     * OBJECT_ID  对象id
     * OPERATION_OBJECT  对象说明
     *
     * @throws Exception
     */
    public void logs(int OPERATION_TYPE, int OBJECT_ID, int OBJECT_TYPE, String OPERATION_OBJECT) throws Exception {
        PageData pd = new PageData();
        pd.put("operation_type", OPERATION_TYPE);
        pd.put("object_id", OBJECT_ID);
        pd.put("object_type", OBJECT_TYPE);
        pd.put("operation_object", OPERATION_OBJECT);
        pd.put("check_user", getAdmin().getUSERNAME());
        //pd.put("check_user", findUser().get("ACCOUNT_NAME"));
    }
}
