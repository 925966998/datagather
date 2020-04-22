package com.ky.redwood.mapper;

import com.ky.redwood.entity.StockEntity;
import com.ky.redwood.mybatis.BaseMapper;
import com.ky.redwood.sql.StockSql;
import com.ky.redwood.sql.StockSql;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface StockMapper extends BaseMapper {
    
    /**
     * 根据条件查询分页 必要参数： currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10条 其他参数： map里的key为属性名（字段首字母小写） value为查询的条件，默认为等于
     * 要改动sql请修改 *Mapper 类里的 _query() 方法
     */
    @SelectProvider(type = StockSql.class, method = "_queryPage")
    List<StockEntity> _queryPage(Map pagerParam);

    /**
     * 根据条件查询全部 参数： map里的key为属性名（字段首字母小写） value为查询的条件，默认为等于 要改动sql请修改 *Mapper 类里的 _query() 方法
     */
    @SuppressWarnings("rawtypes")
    @SelectProvider(type = StockSql.class, method = "_queryAll")
    List<StockEntity> _queryAll(Map pagerParam);

    /**
     * 根据条件查询总条数 map里的key为属性名（字段首字母小写） value为查询的条件，默认为等于 要改动sql请修改 *Mapper 类里的 _query() 方法
     */
    @SelectProvider(type = StockSql.class, method = "_queryCount")
    long _queryCount(Map params);

    /**
     * 按id查询 参数： id ： 要查询的记录的id
     */
    @SelectProvider(type = StockSql.class, method = "_get")
    StockEntity _get(String id);

    /**
     * 删除（逻辑） 参数： id ： 要删除的记录的id
     */
    @DeleteProvider(type = StockSql.class, method = "_delete")
    int _delete(String id);

    /**
     * 删除（物理） 参数： id ： 要删除的记录的id
     */
    @DeleteProvider(type = StockSql.class, method = "_deleteForce")
    int _deleteForce(String id);

    /**
     * 新增 参数： map里的key为属性名（字段首字母小写） value为要插入的key值
     */
    @SuppressWarnings("rawtypes")
    @InsertProvider(type = StockSql.class, method = "_add")
    int _add(Map params);

    /**
     * 按实体类新增 参数： 实体类对象，必须有id属性
     */
    @InsertProvider(type = StockSql.class, method = "_addEntity")
    int _addEntity(StockEntity bean);

    /**
     * 更新 参数： id ： 要更新的记录的id 其他map里的参数，key为属性名（字段首字母小写） value为要更新成的值
     */
    @InsertProvider(type = StockSql.class, method = "_update")
    int _update(Map params);

    /**
     * 按实体类更新 参数： 实体类对象，必须有id属性
     */
    @InsertProvider(type = StockSql.class, method = "_updateEntity")
    int _updateEntity(StockEntity bean);
    

}
