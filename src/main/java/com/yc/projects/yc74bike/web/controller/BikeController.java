package com.yc.projects.yc74bike.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.projects.yc74bike.bean.Bike;
import com.yc.projects.yc74bike.service.BikeService;
import com.yc.projects.yc74bike.web.model.JsonModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

// @RestController   ->   @Controller  +   @ResponseBody
@Controller
@Api(value = "小辰出行单车信息操作接口", tags = { "单车信息", "控制层" })
public class BikeController {

	private Logger logger = LogManager.getLogger();

	@Autowired
	private BikeService bikeService;

	// 报修
	@PostMapping("/repair")
	public @ResponseBody JsonModel repair(JsonModel jm, Bike bike) {
		try {
			this.bikeService.reportMantinant(bike);
			jm.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}

	@RequestMapping(value = "/findNearAll", method = { RequestMethod.POST })
	@ApiOperation(value = "查找最近的单车", notes = "查找最近的40部单车")
	public @ResponseBody JsonModel findNearAll(@ApiIgnore JsonModel jm, @RequestBody Bike bike) {
		List<Bike> list = bikeService.findNearAll(bike);
		jm.setCode(1);
		jm.setObj(list);
		logger.info(    "位置为:"+ bike.getLongitude()+","+bike.getLatitude()+"附近的车有:" );
		logger.info(   list.toString() );
		return jm;
	}

	/**
	 * 扫码开锁
	 * 
	 * @param jsonModel:返回值部分，
	 * @param bike:
	 *            必须的参数: bid,经度,纬度
	 * @return
	 */
	@RequestMapping(value = "/open", method = { RequestMethod.POST })
	@ApiOperation(value = "用户端开锁操作", notes = "给指定的共享单车开锁，参数以json格式传过来")
	// @RequestBody： 表示将客户端传过来的 post实体中json转为对象. 则请求method必须是 POST.
	public @ResponseBody JsonModel open(@ApiIgnore JsonModel jsonModel, @RequestBody Bike bike) {
		logger.info("请求参数:" + bike);
		try {
			bikeService.open(bike);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
		return jsonModel;
	}
	@RequestMapping(value = "/addBike", method = { RequestMethod.POST })
	@ApiOperation(value = "管理端添加单车", notes = "添加单车，参数以json格式传过来")
	// @RequestBody： 表示将客户端传过来的 post实体中json转为对象. 则请求method必须是 POST.
	public @ResponseBody JsonModel addBike(@ApiIgnore JsonModel jsonModel, @RequestBody Bike bike) {
		logger.info("请求参数:" + bike);
		try {
			bikeService.addNewBike(bike);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
		return jsonModel;
	}
	
	@RequestMapping(value = "/updateBike", method = { RequestMethod.POST })
	@ApiOperation(value = "管理端更新单车", notes = "更新单车，参数以json格式传过来")
	// @RequestBody： 表示将客户端传过来的 post实体中json转为对象. 则请求method必须是 POST.
	public @ResponseBody JsonModel updateBike(@ApiIgnore JsonModel jsonModel, @RequestBody Bike bike) {
		logger.info("请求参数:" + bike);
		try {
			bikeService.updateBike(bike);
			jsonModel.setCode(1);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
		return jsonModel;
	}
	@RequestMapping(value = "/listAllBike", method = { RequestMethod.GET })
	@ApiOperation(value = "list单车", notes = "list单车，参数以json格式传过来")
	public @ResponseBody JsonModel listAllBike(@ApiIgnore JsonModel jsonModel) {
		
		try {
	List<Bike> list=	bikeService.listAllBike();
	logger.info("单车数量:" +list.size() );
			jsonModel.setCode(1);
			jsonModel.setObj(list);
		} catch (Exception e) {
			e.printStackTrace();
			jsonModel.setCode(0);
			jsonModel.setMsg(e.getMessage());
		}
		return jsonModel;
	}
}