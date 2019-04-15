/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.financial.cost.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.basic.statistics.entity.Data;
import com.jeesite.modules.basic.statistics.service.DataService;
import com.jeesite.modules.financial.cost.entity.Cost;
import com.jeesite.modules.financial.cost.service.CostService;

/**
 * 成本记录Controller
 * @author longlou.d@foxmail.com
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/cost/cost")
public class CostController extends BaseController {

	@Autowired
	private CostService costService;
	@Autowired
	private DataService dataService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Cost get(String id, boolean isNewRecord) {
		return costService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("cost:cost:view")
	@RequestMapping(value = {"list", ""})
	public String list(Cost cost, Model model) {
		model.addAttribute("cost", cost);
		return "financial/cost/costList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("cost:cost:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Cost> listData(Cost cost, HttpServletRequest request, HttpServletResponse response) {
		cost.setPage(new Page<>(request, response));
		Page<Cost> page = costService.findPage(cost);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("cost:cost:view")
	@RequestMapping(value = "form")
	public String form(Cost cost, Model model) {
		model.addAttribute("cost", cost);
		return "financial/cost/costForm";
	}

	/**
	 * 保存成本
	 */
	@RequiresPermissions("cost:cost:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Cost cost) {
		costService.save(cost);
		return renderResult(Global.TRUE, text("保存成本成功！"));
	}
	
	/**
	 * 停用成本
	 */
	@RequiresPermissions("cost:cost:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Cost cost) {
		cost.setStatus(Cost.STATUS_DISABLE);
		costService.updateStatus(cost);
		return renderResult(Global.TRUE, text("停用成本成功"));
	}
	
	/**
	 * 启用成本
	 */
	@RequiresPermissions("cost:cost:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Cost cost) {
		cost.setStatus(Cost.STATUS_NORMAL);
		costService.updateStatus(cost);
		return renderResult(Global.TRUE, text("启用成本成功"));
	}
	
	/**
	 * 删除成本
	 */
	@RequiresPermissions("cost:cost:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Cost cost) {
		costService.delete(cost);
		return renderResult(Global.TRUE, text("删除成本成功！"));
	}
	
	/**
	 * 统计指定月份付款额（生成成本）
	 */
	@RequiresPermissions("cost:cost:view")
	@RequestMapping(value="statisticPayAmount")
	@ResponseBody
	public Map<String, String> statisticPayAmount(Date date){
		//GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1])-1, 1);
	    //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    //String dateString = formatter.format(calendar.getTime());
	    Future<Data> data = dataService.statisticsPayByMonth(date);
	    Map<String, String> map = new HashMap<String, String>();
	    try {
			map.put("data", String.valueOf(data.get().getData()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("message", "统计出错");
		}
		return map;
	}
}