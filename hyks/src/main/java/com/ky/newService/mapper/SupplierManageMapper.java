package com.ky.newService.mapper;

import com.ky.newService.entity.SupplierManageEntity;
import com.ky.newService.excle.ExcelHead;
import com.ky.newService.mybatis.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SupplierManageMapper extends BaseMapper {
    /**
     * 根据条件查询分页 必要参数： currentPage : 当前第几页，默认1 pageSize : 每页多少条，默认10条 其他参数： map里的key为属性名（字段首字母小写） value为查询的条件，默认为等于
     * 要改动sql请修改 *Mapper 类里的 _query() 方法
     */
    @SelectProvider(type = SupplierManageSql.class, method = "_queryPage")
    List<SupplierManageEntity> _queryPage(Map pagerParam);

    /**
     * 新增 参数： map里的key为属性名（字段首字母小写） value为要插入的key值
     */
    @SuppressWarnings("rawtypes")
    @InsertProvider(type = SupplierManageSql.class, method = "_add")
    int _add(Map params);


    /**
     * 更新 参数： id ： 要更新的记录的id 其他map里的参数，key为属性名（字段首字母小写） value为要更新成的值
     */
    @InsertProvider(type = SupplierManageSql.class, method = "_update")
    int _update(Map params);

    /**
     * 根据条件查询全部 参数： map里的key为属性名（字段首字母小写） value为查询的条件，默认为等于 要改动sql请修改 *Mapper 类里的 _query() 方法
     */
    @SuppressWarnings("rawtypes")
    @SelectProvider(type = SupplierManageSql.class, method = "_queryAll")
    List<SupplierManageEntity> _queryAll(Map pagerParam);

    /**
     * 根据条件查询总条数 map里的key为属性名（字段首字母小写） value为查询的条件，默认为等于 要改动sql请修改 *Mapper 类里的 _query() 方法
     */
    @SelectProvider(type = SupplierManageSql.class, method = "_queryCount")
    long _queryCount(Map params);

    /**
     * 按id查询 参数： id ： 要查询的记录的id
     */
    @SelectProvider(type = SupplierManageSql.class, method = "_get")
    SupplierManageEntity _get(String id);

    /**
     * 删除（逻辑） 参数： id ： 要删除的记录的id
     */
    @DeleteProvider(type = SupplierManageSql.class, method = "_delete")
    int _delete(String id);

    /**
     * 删除（物理） 参数： id ： 要删除的记录的id
     */
    @DeleteProvider(type = SupplierManageSql.class, method = "_deleteForce")
    int _deleteForce(String id);


    /**
     * 按实体类新增 参数： 实体类对象，必须有id属性
     */
    @InsertProvider(type = SupplierManageSql.class, method = "_addEntity")
    int _addEntity(SupplierManageEntity bean);


    /**
     * 按实体类更新 参数： 实体类对象，必须有id属性
     */
    @InsertProvider(type = SupplierManageSql.class, method = "_updateEntity")
    int _updateEntity(SupplierManageEntity bean);


    @SuppressWarnings("rawtypes")
    @SelectProvider(type = SupplierManageSql.class, method = "queryContent")
    List<SupplierManageEntity> queryContent(Map params);
    @SelectProvider(type = SupplierManageSql.class, method = "_queryContentCount")
    long _queryContentCount(Map params);

    @SuppressWarnings("rawtypes")
    @SelectProvider(type = SupplierManageSql.class, method = "queryPreContent")
    List<SupplierManageEntity> queryPreContent(Map params);
    @SelectProvider(type = SupplierManageSql.class, method = "_queryPreContentCount")
    long _queryPreContentCount(Map params);

    @Select("SELECT COLUMN_NAME entityName,column_comment excelName FROM INFORMATION_SCHEMA.Columns WHERE table_name='supplier_manage' AND table_schema='ky-supplier'")
    List<ExcelHead> _queryColumnAndComment();

    @Select("select * from supplier_manage where taxNum=#{taxNum}")
    List<SupplierManageEntity> queryByCode(@Param("taxNum")String taxNum);

}
