package com.ky.dbbak.service;

import com.ky.dbbak.mybatis.RestResult;
import com.ky.dbbak.sourcemapper.SourceMapper;
import com.ky.dbbak.targetmapper.DzzbxxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FzxlbService
 * @Description: TODO
 * @Author czw
 * @Date 2020/3/26
 **/
@Service
public class FzxlbService {


    @Autowired
    SourceMapper sourceMapper;

    public Map<String, Object> _queryGL_Fzxlb1(Map params) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> maps = sourceMapper._queryGL_Fzxlb1(params);
        if (maps != null && maps.size() > 0) {
            for (Map<String, Object> resultMap : maps
            ) {
                map.put(resultMap.get("lbdm").toString(), resultMap);
            }
        }
        return map;
    }
}
