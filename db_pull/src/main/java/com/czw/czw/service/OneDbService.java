package com.czw.czw.service;

import com.czw.czw.dao.DaoSupportOne;
import com.czw.czw.model.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName OneDb
 * @Description: TODO
 * @Author czw
 * @Date 2020/3/19
 **/
@Service("oneDbService")
@SuppressWarnings("unchecked")
public class OneDbService {
    @Resource(name = "daoSupportOne")
    private DaoSupportOne dao;

    public List<PageData> selectBypznr(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("OneMapper.selectBypznr", pd);
    }

    public List<PageData> selectByKmxx(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("OneMapper.selectByKmxx", pd);
    }

    public List<PageData> selectByFzxlb(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("OneMapper.selectByFzxlb", pd);
    }

    public List<PageData> selectByPUBBMXX(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("OneMapper.selectByPUBBMXX", pd);
    }

    public List<PageData> selectByPUBKSZL(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("OneMapper.selectByPUBKSZL", pd);
    }

    public List<PageData> selectByGL_Xmzl(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("OneMapper.selectByGL_Xmzl", pd);
    }

    public List<PageData> selectByGL_Fzxzl(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("OneMapper.selectByGL_Fzxzl", pd);
    }

    public List<PageData> selectByGL_Yeb(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("OneMapper.selectByGL_Yeb", pd);
    }
    public List<PageData> selectByGL_KMXX(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("OneMapper.selectByGL_KMXX", pd);
    }
}
