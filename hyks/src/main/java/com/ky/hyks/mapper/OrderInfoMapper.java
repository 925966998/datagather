package com.ky.hyks.mapper;


import com.ky.hyks.entity.CompanyEntity;
import com.ky.hyks.entity.OrderInfoEntity;
import com.ky.hyks.mybatis.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderInfoMapper extends BaseMapper {

    /**
     * 根据条件查询分页 必要参数： currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10条 其他参数： map里的key为属性名（字段首字母小写） value为查询的条件，默认为等于
     * 要改动sql请修改 *Mapper 类里的 _query() 方法
     */
    @SelectProvider(type = OrderInfoSql.class, method = "_queryPage")
    List<OrderInfoEntity> _queryPage(Map pagerParam);

    /**
     * 根据条件查询全部 参数： map里的key为属性名（字段首字母小写） value为查询的条件，默认为等于 要改动sql请修改 *Mapper 类里的 _query() 方法
     */
    @SuppressWarnings("rawtypes")
    @SelectProvider(type = OrderInfoSql.class, method = "_queryAll")
    List<OrderInfoEntity> _queryAll(Map pagerParam);

    /**
     * 根据条件查询总条数 map里的key为属性名（字段首字母小写） value为查询的条件，默认为等于 要改动sql请修改 *Mapper 类里的 _query() 方法
     */
    @SelectProvider(type = OrderInfoSql.class, method = "_queryCount")
    long _queryCount(Map params);

    /**
     * 按id查询 参数： id ： 要查询的记录的id
     */
    @SelectProvider(type = OrderInfoSql.class, method = "_get")
    OrderInfoEntity _get(String id);

    /**
     * 删除（逻辑） 参数： id ： 要删除的记录的id
     */
    @DeleteProvider(type = OrderInfoSql.class, method = "_delete")
    int _delete(String id);

    /**
     * 删除（物理） 参数： id ： 要删除的记录的id
     */
    @DeleteProvider(type = OrderInfoSql.class, method = "_deleteForce")
    int _deleteForce(String id);

    /**
     * 新增 参数： map里的key为属性名（字段首字母小写） value为要插入的key值
     */
    @SuppressWarnings("rawtypes")
    @InsertProvider(type = OrderInfoSql.class, method = "_add")
    int _add(Map params);

    /**
     * 按实体类新增 参数： 实体类对象，必须有id属性
     */
    @InsertProvider(type = OrderInfoSql.class, method = "_addEntity")
    int _addEntity(OrderInfoEntity bean);

    /**
     * 更新 参数： id ： 要更新的记录的id 其他map里的参数，key为属性名（字段首字母小写） value为要更新成的值
     */
    @InsertProvider(type = OrderInfoSql.class, method = "_update")
    int _update(Map params);

    /**
     * 按实体类更新 参数： 实体类对象，必须有id属性
     */
    @InsertProvider(type = OrderInfoSql.class, method = "_updateEntity")
    int _updateEntity(OrderInfoEntity bean);

    @Select(" SELECT c.*,d.code as MARBASCLASSCODE,d.name as MARBASCLASSNAME\n" +
            "FROM (SELECT a.*,b.code,b.name as MATTERNAME,b.materialspec,b.pk_marbasclass \n" +
            "FROM ( SELECT pk_praybill_b, nastnum, dbilldate, pk_material, pk_group FROM po_praybill_b WHERE pk_praybill_b = #{pk_praybill_b} ) a\n" +
            "LEFT JOIN bd_material b ON a.pk_material = b.pk_material ) c \n" +
            "LEFT JOIN bd_marbasclass d ON d.pk_marbasclass = c.pk_marbasclass")
    OrderInfoEntity queryBypk(Map map);
}
