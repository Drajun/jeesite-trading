/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.customs.web;

import java.util.Date;
import java.util.List;

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
import com.jeesite.modules.logistics.customs.entity.CustomsC;
import com.jeesite.modules.logistics.customs.service.CustomsCService;
import com.jeesite.modules.purandsell.sales.entity.ContractC;
import com.jeesite.modules.purandsell.sales.service.ContractCService;
import com.jeesite.modules.sys.entity.Employee;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.service.EmployeeService;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 报关委托Controller
 * @author longlou.d@foxmail.com
 * @version 2019-04-09
 */
@Controller
@RequestMapping(value = "${adminPath}/customs/customsC")
public class CustomsCController extends BaseController {

	@Autowired
	private CustomsCService customsCService;
	@Autowired
	private ContractCService contractCService;
	@Autowired
	private EmployeeService employeeService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CustomsC get(String id, boolean isNewRecord) {
		return customsCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("customs:customsC:view")
	@RequestMapping(value = {"list", ""})
	public String list(CustomsC customsC, Model model) {
		model.addAttribute("customsC", customsC);
		return "logistics/customs/customsCList";
	}
	
	/**
	 * 审批列表
	 */
	@RequiresPermissions("customs:customsC:view")
	@RequestMapping(value = {"checkList", ""})
	public String checkList(CustomsC customsC, Model model) {
		model.addAttribute("customsC", customsC);
		return "logistics/customsCheck/customsCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("customs:customsC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CustomsC> listData(CustomsC customsC, HttpServletRequest request, HttpServletResponse response) {
		customsC.setPage(new Page<>(request, response));
		Page<CustomsC> page = customsCService.findPage(customsC);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("customs:customsC:view")
	@RequestMapping(value = "form")
	public String form(CustomsC customsC, Model model) {
		//合同列表
		ContractC contract = new ContractC();
		List<ContractC> contractList = contractCService.findList(contract);
		model.addAttribute("contractList", contractList);
		
		//员工
		Employee employee = new Employee();
		List<Employee> employeeList = employeeService.findList(employee);
		model.addAttribute("employeeList", employeeList);

		model.addAttribute("customsC", customsC);
		return "logistics/customs/customsCForm";
	}
	
	/**
	 * 查看审批表单
	 */
	@RequiresPermissions("customs:customsC:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(CustomsC customsC, Model model) {
		//合同列表
		ContractC contract = new ContractC();
		List<ContractC> contractList = contractCService.findList(contract);
		model.addAttribute("contractList", contractList);
		
		model.addAttribute("customsC", customsC);
		return "logistics/customsCheck/customsCForm";
	}

	/**
	 * 保存报关
	 */
	@RequiresPermissions("customs:customsC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CustomsC customsC) {
		if(customsC.getStatu().equals("1")||customsC.getStatu().equals("0")){
			customsCService.save(customsC);
			return renderResult(Global.TRUE, text("保存报关成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择草稿或待审！"));	
		}
	}
	
	/**
	 * 保存审批
	 */
	@RequiresPermissions("customs:customsC:edit")
	@PostMapping(value = "checkSave")
	@ResponseBody
	public String checkSave(@Validated CustomsC customsC) {
		if(customsC.getStatu().equals("3")||customsC.getStatu().equals("2")){
			User user = UserUtils.getUser();
			customsC.setCheckBy(user.getUserName());
			customsC.setCheckTime(new Date());
			customsCService.save(customsC);
			return renderResult(Global.TRUE, text("保存审批成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择通过或驳回！"));	
		}
	}
	
	/**
	 * 停用报关
	 */
	@RequiresPermissions("customs:customsC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(CustomsC customsC) {
		customsC.setStatus(CustomsC.STATUS_DISABLE);
		customsCService.updateStatus(customsC);
		return renderResult(Global.TRUE, text("停用报关成功"));
	}
	
	/**
	 * 启用报关
	 */
	@RequiresPermissions("customs:customsC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(CustomsC customsC) {
		customsC.setStatus(CustomsC.STATUS_NORMAL);
		customsCService.updateStatus(customsC);
		return renderResult(Global.TRUE, text("启用报关成功"));
	}
	
	/**
	 * 删除报关
	 */
	@RequiresPermissions("customs:customsC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CustomsC customsC) {
		customsCService.delete(customsC);
		return renderResult(Global.TRUE, text("删除报关成功！"));
	}
	
}