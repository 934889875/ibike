package com.yc.projects.yc74bike.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.projects.yc74bike.service.LogService;
import com.yc.projects.yc74bike.web.model.JsonModel;

@Controller
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/log/savelog")   //只支持  POST请求
    @ResponseBody   //回送给客户端的是一个json数据
    public JsonModel ready( JsonModel jsonModel,@RequestBody String log) {
        logService.save(log);
        jsonModel.setCode(1);
        return jsonModel;
    }
    @PostMapping("/log/addPayLog")   //只支持  POST请求
    @ResponseBody   //回送给客户端的是一个json数据
    public JsonModel addPayLog( JsonModel jsonModel,@RequestBody String log) {
        logService.savePayLog(log);
        jsonModel.setCode(1);
        return jsonModel;
    }
    

    @PostMapping("/log/addRepairLog")
    public  @ResponseBody JsonModel addRepairLog(JsonModel jsonModel,@RequestBody String log){
    	logService.saveRepairLog(log);
    	jsonModel.setCode(1);
    	return jsonModel;
    }
    @PostMapping("/log/addDriverLog")
    public  @ResponseBody JsonModel addDriverrLog(JsonModel jsonModel,@RequestBody String log){
    	logService.saveDriverLog(log);
    	jsonModel.setCode(1);
    	return jsonModel;
    }
}