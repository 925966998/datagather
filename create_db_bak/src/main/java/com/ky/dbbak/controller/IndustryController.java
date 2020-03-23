package com.ky.dbbak.controller;

import com.ky.dbbak.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/ky-datagather/industry")
public class IndustryController {

    @Autowired
    IndustryService industryService;
    /**
     * 根据条件查询数据（不分页）
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public Object queryAll() {
        return industryService.queryAll(new HashMap<>());
    }

    @RequestMapping(value = "/queryTree", method = RequestMethod.GET)
    public Object queryTree() {
        return industryService.queryTree();
    }
}
