package com.yc.projects.yc74bike.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.projects.yc74bike.bean.PayModel;
import com.yc.projects.yc74bike.service.BikeService;
import com.yc.projects.yc74bike.service.PayService;
import com.yc.projects.yc74bike.service.UserService;
import com.yc.projects.yc74bike.web.model.JsonModel;

import io.swagger.annotations.Api;

@Controller
@Api(value = "结算操作接口", tags = { "账单" })
public class PayController {
	@Autowired
	private UserService userService;
	@Autowired
	private BikeService bikeService;
	@Autowired
	private PayService payService;
	
	@PostMapping("/payMoney")
	public @ResponseBody JsonModel payMoney(JsonModel jm,PayModel pm) {
		try {
			int paymoney= payService.pay(  pm );     
			jm.setCode(1);
			jm.setMsg(paymoney+"");
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(  e.getMessage());
		}
		return jm;
	}
}