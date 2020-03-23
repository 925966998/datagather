package com.ky.dbbak.controller;

import com.ky.dbbak.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ky-datagather/area")
public class AreaController {

    @Autowired
    AreaService areaService;

    @RequestMapping(value = "/queryByAreaLevel/{level}", method = RequestMethod.GET)
    public Object queryByAreaLevel(@PathVariable String level) {
        return areaService.queryByAreaLevel(level);
    }

    @RequestMapping(value = "/queryByPid/{pid}", method = RequestMethod.GET)
    public Object queryByPid(@PathVariable String pid) {
        return areaService.queryByPid(pid);
    }

    @RequestMapping(value = "/queryById/{id}", method = RequestMethod.GET)
    public Object queryById(@PathVariable String id) {
        return areaService.queryById(id);
    }

    @RequestMapping(value = "/queryTree", method = RequestMethod.GET)
    public Object queryTree() {
        return areaService.queryTree();
    }

}
