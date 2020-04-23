package com.ky.redwood.mapper;

import com.ky.redwood.entity.CostSharingEntity;
import com.ky.redwood.entity.MaterialEntity;
import com.ky.redwood.mybatis.BaseMapper;
import com.ky.redwood.sql.FeeStatisticsSql;
import com.ky.redwood.sql.MaterialSql;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface FeeStatisticsMapper extends BaseMapper {

    @SelectProvider(type = FeeStatisticsSql.class, method = "_queryPage")
    List<CostSharingEntity> _queryPage(Map pagerParam);

    @SelectProvider(type = FeeStatisticsSql.class, method = "_queryCount")
    long _queryCount(Map params);

    @SelectProvider(type = FeeStatisticsSql.class, method = "queryAllFeeCount")
    Map queryAllFeeCount(Map params);

    @SelectProvider(type = FeeStatisticsSql.class, method = "queryJgFeeCount")
    Map queryJgFeeCount(Map params);


    @InsertProvider(type = FeeStatisticsSql.class, method = "_addEntity")
    int _addEntity(CostSharingEntity bean);

    @UpdateProvider(type = FeeStatisticsSql.class, method = "_updateEntity")
    int _updateEntity(CostSharingEntity bean);

    @SelectProvider(type = FeeStatisticsSql.class, method = "querySharing")
    List<CostSharingEntity> querySharing(Map params);

    @SelectProvider(type = FeeStatisticsSql.class, method = "queryNoSharing")
    List<Map> queryNoSharing(Map params);

    @Select("select * from cost_sharing where materialOutId=#{id}")
    CostSharingEntity queryByMaterialOutId(String id);
}
