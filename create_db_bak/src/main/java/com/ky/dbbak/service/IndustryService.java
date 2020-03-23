package com.ky.dbbak.service;

import com.ky.dbbak.entity.IndustryEntity;
import com.ky.dbbak.entity.TreeNode;
import com.ky.dbbak.mapper.IndustryMapper;
import com.ky.dbbak.mybatis.RestResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IndustryService {
    private static final Logger logger = LoggerFactory.getLogger(IndustryService.class);
    @Autowired
    IndustryMapper industryMapper;

    public Object queryAll(Map params) {
        return new RestResult(RestResult.SUCCESS_CODE, RestResult.SUCCESS_MSG, industryMapper._queryAll(params));
    }

    public Object queryTree() {
        List<IndustryEntity> industryEntities = industryMapper._queryAll(new HashMap());
        List<TreeNode> treeNodes = new ArrayList();
        for (IndustryEntity industryEntity : industryEntities) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(industryEntity.getIndustryCode());
            if (StringUtils.isNotEmpty(industryEntity.getParentID()))
                treeNode.setParentId(industryEntity.getParentID());
            else
                treeNode.setParentId("0");
            treeNode.setText(industryEntity.getIndustryName());
            treeNodes.add(treeNode);
        }
        Map<String, List<TreeNode>> sub = treeNodes.stream().filter(node -> (!node.getParentId().equals("0")) && node.getParentId() != null).collect(Collectors.groupingBy(node -> node.getParentId()));
        treeNodes.forEach(node -> node.setChildren(sub.get(node.getId())));
        List<TreeNode> collect = treeNodes.stream().filter(node -> (node.getParentId().equals("0") || node.getParentId() == null)).collect(Collectors.toList());
        TreeNode treeNode = new TreeNode();
        treeNode.setId("0");
        treeNode.setText("组织机构");
        treeNode.setChildren(collect);
        treeNodes.clear();
        treeNodes.add(treeNode);
        return treeNodes;

    }
}
