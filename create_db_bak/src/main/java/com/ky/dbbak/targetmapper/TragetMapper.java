package com.ky.dbbak.targetmapper;

import com.ky.dbbak.mapper.FzncsSql;
import com.ky.dbbak.mapper.PzfzmxSql;
import com.ky.dbbak.mapper.YsdwSql;
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

    @InsertProvider(type = PzfzmxSql.class, method = "_add")
    int _addPzfzmx(Map params);

    @InsertProvider(type = FzncsSql.class, method = "_add")
    int _addFzncs(Map params);

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


    @SelectProvider(type = JZPZSql.class, method = "_queryPage")
    List<Map<String, Object>> queryPageJZPZ(Map pagerParam);

    @SelectProvider(type = JZPZSql.class, method = "_queryCount")
    long _queryCountJZPZ(Map pagerParam);


    @SelectProvider(type = KjqjdySql.class, method = "_queryPage")
    List<Map<String, Object>> queryPageKjqjdy(Map pagerParam);

    @SelectProvider(type = KjqjdySql.class, method = "_queryCount")
    long _queryCountKjqjdy(Map pagerParam);

    @SelectProvider(type = KMNCSSql.class, method = "_queryPage")
    List<Map<String, Object>> queryPageKMNCS(Map pagerParam);

    @SelectProvider(type = KMNCSSql.class, method = "_queryCount")
    long _queryCountKMNCS(Map pagerParam);


    @SelectProvider(type = KMYESql.class, method = "_queryPage")
    List<Map<String, Object>> queryPageKMYE(Map pagerParam);

    @SelectProvider(type = KMYESql.class, method = "_queryCount")
    long _queryCountKMYE(Map pagerParam);

    @SelectProvider(type = FzlxSql.class, method = "_queryPage")
    List<Map<String, Object>> queryPageFzlx(Map pagerParam);

    @SelectProvider(type = FzlxSql.class, method = "_queryCount")
    long _queryCountFzlx(Map pagerParam);

    @SelectProvider(type = FzxxSql.class, method = "_queryPage")
    List<Map<String, Object>> queryPageFzxx(Map pagerParam);

    @SelectProvider(type = FzxxSql.class, method = "_queryCount")
    long _queryCountFzxx(Map pagerParam);


    @SelectProvider(type = KJKMSql.class, method = "_queryPage")
    List<Map<String, Object>> queryPageKJKM(Map pagerParam);

    @SelectProvider(type = KJKMSql.class, method = "_queryCount")
    long _queryCountKJKM(Map pagerParam);


    @SelectProvider(type = FZYESql.class, method = "_queryPage")
    List<Map<String, Object>> queryPageFZYE(Map pagerParam);

    @SelectProvider(type = FZYESql.class, method = "_queryCount")
    long _queryCountFZYE(Map pagerParam);

    @SelectProvider(type = DzzbxxSql.class, method = "_queryPage")
    List<Map<String, Object>> queryPageDZZBXX(Map pagerParam);

    @SelectProvider(type = DzzbxxSql.class, method = "_queryCount")
    long _queryCountDZZBXX(Map pagerParam);


}
