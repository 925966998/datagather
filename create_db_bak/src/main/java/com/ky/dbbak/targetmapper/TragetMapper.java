package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mapper.*;
import com.ky.dbbak.mybatis.BaseMapper;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface TragetMapper extends BaseMapper {


    @SelectProvider(type = TargetSql.class, method = "_queryDescription")
    List<Map<String, Object>> _queryDescription(Map pagerParam);

    @DeleteProvider(type = TargetSql.class, method = "truncate")
    int truncate(Map pagerParam);

    @SelectProvider(type = DzzbxxSql.class, method = "_queryselect")
    List<Map<String, Object>> _queryDzzbxx(Map pagerParam);

    @InsertProvider(type = YsdwSql.class, method = "_add")
    int _add(Map params);

    @InsertProvider(type = PzfzmxSql.class, method = "_addBatch")
    int _addPzfzmx(Map params);

    @InsertProvider(type = FzncsSql.class, method = "_addBatch")
    int _addFzncs(Map params);

    @InsertProvider(type = FzxxSql.class, method = "_add")
    int _addFzxx(Map params);

    @InsertProvider(type = FzlxSql.class, method = "_add")
    int _addFzlx(Map params);

    @InsertProvider(type = KjkmSql.class, method = "_add")
    int _addKjkm(Map params);

    @SelectProvider(type = FzncsSql.class, method = "_queryPage")
    List<Map<String, Object>> queryPageFZNCS(Map pagerParam);

    @SelectProvider(type = PzfzmxSql.class, method = "_queryPage")
    List<Map<String, Object>> queryPagePZFZMX(Map pagerParam);

    @SelectProvider(type = YsdwSql.class, method = "_queryPage")
    List<Map<String, Object>> queryPageYSDW(Map pagerParam);

    @SelectProvider(type = YsdwSql.class, method = "_queryYsdw")
    List<Map<String, Object>> queryYSDW(Map pagerParam);

    @SelectProvider(type = FzncsSql.class, method = "_queryCount")
    long _queryCountFzncs(Map pagerParam);

    @SelectProvider(type = PzfzmxSql.class, method = "_queryCount")
    long _queryCountPzfzmx(Map pagerParam);

    @SelectProvider(type = YsdwSql.class, method = "_queryCount")
    long _queryCountYsdw(Map pagerParam);

    @SuppressWarnings("rawtypes")
    @InsertProvider(type = YsdwSql.class, method = "_add")
    int _addYsdw(Map params);

    @InsertProvider(type = YsdwSql.class, method = "_update")
    int _updateYsdw(Map params);

}
