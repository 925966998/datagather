package com.ky.redwood.service;

import com.ky.redwood.entity.CostSharingEntity;
import com.ky.redwood.entity.MaterialOutEntity;
import com.ky.redwood.mapper.FeeStatisticsMapper;
import com.ky.redwood.mybatis.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FeeStatisticsService {
    @Autowired
    FeeStatisticsMapper feeStatisticsMapper;

    public Object querySharing(Map params) {
        List<CostSharingEntity> list = feeStatisticsMapper.querySharing(params);
        long count = feeStatisticsMapper._queryCount(params);
        return new RestResult(count, list);
    }

    public Object queryNoSharing(Map params) {
        List<Map> list = feeStatisticsMapper.queryNoSharing(params);
        long count = feeStatisticsMapper._queryCount(params);
        return new RestResult(count, list);
    }

    public Object queryJgFeeCount(Map params) {
        return feeStatisticsMapper.queryJgFeeCount(params);
    }

    public Object queryAllFeeCount(Map params) {
        return feeStatisticsMapper.queryAllFeeCount(params);
    }


    public Object add(CostSharingEntity entity) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, feeStatisticsMapper._addEntity(entity));
    }

    public Object costSharing(CostSharingEntity costSharingEntity) {
        BigDecimal cost = costSharingEntity.getFzFee().add(costSharingEntity.getJgFee()).add(costSharingEntity.getJwlFee()).add(costSharingEntity.getQtFee()).add(costSharingEntity.getSdFee()).add(costSharingEntity.getXlFee());
        Map params = new HashMap();
        params.put("startTime", costSharingEntity.getStartTime() + " 00:00:00");
        params.put("endTime", costSharingEntity.getEndTime() + " 23:59:59");
        List<Map> maps = feeStatisticsMapper.queryNoSharing(params);
        for (Map map : maps) {
            CostSharingEntity costSharingEntity1 = feeStatisticsMapper.queryByMaterialOutId(map.get("id").toString());
            if (costSharingEntity1 == null) {
                costSharingEntity.setMaterialOutId(map.get("id").toString());
                costSharingEntity.setJgFee(new BigDecimal(map.get("jgFee").toString()));
                String jgFeebfb = division(cost.doubleValue(), new BigDecimal(map.get("jgFee").toString()).doubleValue());
                costSharingEntity.setFtFee(cost.multiply(new BigDecimal(jgFeebfb).setScale(BigDecimal.ROUND_HALF_UP)));
                costSharingEntity.setProductName(map.get("productName").toString());
                costSharingEntity.setProductTime(map.get("productTime").toString());
                feeStatisticsMapper._addEntity(costSharingEntity);
            } else {
                costSharingEntity.setId(costSharingEntity1.getId());
                costSharingEntity.setJgFee(new BigDecimal(map.get("jgFee").toString()));
                String jgFeebfb = division(cost.doubleValue(), new BigDecimal(map.get("jgFee").toString()).doubleValue());
                costSharingEntity.setFtFee(cost.multiply(new BigDecimal(jgFeebfb).setScale(BigDecimal.ROUND_HALF_UP)));
                feeStatisticsMapper._updateEntity(costSharingEntity);
            }
        }
        return null;
    }

    public static String division(Double countnum, Double num) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精知确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format(countnum / (num * 100));
        System.out.println("num1和num2的百分比为道:" + result + "%");
        return result;
    }
}
