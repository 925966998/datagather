package com.czw.czw.dao;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("daoSupportOne")
public class DaoSupportOne implements DAO {

    @Resource(name = "sqlSessionTemplateOne")
    private SqlSessionTemplate sqlSessionTemplateOne;

    /**
     * 保存对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public Object save(String str, Object obj) throws Exception {
        return sqlSessionTemplateOne.insert(str, obj);
    }

    /**
     * 批量更新
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public Object batchSave(String str, List objs) throws Exception {
        return sqlSessionTemplateOne.insert(str, objs);
    }

    /**
     * 修改对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public Object update(String str, Object obj) throws Exception {
        return sqlSessionTemplateOne.update(str, obj);
    }

    /**
     * 批量更新
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public void batchUpdate(String str, List objs) throws Exception {
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplateOne.getSqlSessionFactory();
        //批量执行器
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            if (objs != null) {
                for (int i = 0, size = objs.size(); i < size; i++) {
                    sqlSession.update(str, objs.get(i));
                }
                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 批量更新
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public Object batchDelete(String str, List objs) throws Exception {
        return sqlSessionTemplateOne.delete(str, objs);
    }

    /**
     * 删除对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public Object delete(String str, Object obj) throws Exception {
        return sqlSessionTemplateOne.delete(str, obj);
    }

    /**
     * 查找对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public Object findForObject(String str, Object obj) throws Exception {
        return sqlSessionTemplateOne.selectOne(str, obj);
    }

    /**
     * 查找对象
     *
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public Object findForList(String str, Object obj) throws Exception {
        return sqlSessionTemplateOne.selectList(str, obj);
    }

    public Object findForList(String str) throws Exception {
        return sqlSessionTemplateOne.selectList(str);
    }

    public Object findForMap(String str, Object obj, String key, String value) throws Exception {
        return sqlSessionTemplateOne.selectMap(str, obj, key);
    }


}


