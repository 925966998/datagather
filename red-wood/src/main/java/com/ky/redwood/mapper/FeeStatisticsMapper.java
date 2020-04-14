package com.ky.redwood.mapper;

import com.ky.redwood.mybatis.BaseMapper;
import com.ky.redwood.sql.FeeStatisticsSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface FeeStatisticsMapper extends BaseMapper {

    @SelectProvider(type = FeeStatisticsSql.class, method = "_queryPage")
    List<Map> queryFeeStatistics(Map pagerParam);

    @SelectProvider(type = FeeStatisticsSql.class, method = "_queryCount")
    long _queryCount(Map params);

}
