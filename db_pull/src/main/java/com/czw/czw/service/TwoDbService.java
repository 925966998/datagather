package com.czw.czw.service;

import com.czw.czw.dao.DaoSupportTwo;
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
@Service("twoDbService")
@SuppressWarnings("unchecked")
public class TwoDbService {
    @Resource(name = "daoSupportTwo")
    private DaoSupportTwo dao;

    public List<PageData> selectByDZZBXX(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("TwoMapper.selectByDZZBXX", pd);
    }

    public void insertBatch(List<PageData> list) throws Exception {
        dao.save("TwoMapper.insertBatch", list);
    }
    public void insertFZNCSBatch(List<PageData> list) throws Exception {
        dao.save("TwoMapper.insertFZNCSBatch", list);
    }

    public void insert(PageData pd) throws Exception {
        dao.save("TwoMapper.insert", pd);
    }
}
