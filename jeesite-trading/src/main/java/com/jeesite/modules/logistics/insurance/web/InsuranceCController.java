/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.insurance.web;

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
import com.jeesite.modules.logistics.insurance.entity.InsuranceC;
import com.jeesite.modules.logistics.insurance.service.InsuranceCService;
import com.jeesite.modules.purandsell.sales.entity.ContractC;
import com.jeesite.modules.purandsell.sales.service.ContractCService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 保险管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/insurance/insuranceC")
public class InsuranceCController extends BaseController {

	@Autowired
	private InsuranceCService insuranceCService;
	@Autowired
	private ContractCService contractCService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public InsuranceC get(String id, boolean isNewRecord) {
		return insuranceCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("insurance:insuranceC:view")
	@RequestMapping(value = {"list", ""})
	public String list(InsuranceC insuranceC, Model model) {
		model.addAttribute("insuranceC", insuranceC);
		return "logistics/insurance/insuranceCList";
	}
	
	/**
	 * 查询审批列表
	 */
	@RequiresPermissions("insurance:insuranceC:view")
	@RequestMapping(value = {"checkList", ""})
	public String checkList(InsuranceC insuranceC, Model model) {	
		model.addAttribute("insuranceC", insuranceC);
		return "logistics/insuranceCheck/insuranceCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("insurance:insuranceC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<InsuranceC> listData(InsuranceC insuranceC, HttpServletRequest request, HttpServletResponse response) {
		insuranceC.setPage(new Page<>(request, response));
		Page<InsuranceC> page = insuranceCService.findPage(insuranceC);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("insurance:insuranceC:view")
	@RequestMapping(value = "form")
	public String form(InsuranceC insuranceC, Model model) {
		//合同列表
		ContractC contract = new ContractC();
		List<ContractC> contractList = contractCService.findList(contract);
		contractList.removeIf(list->list.getStatu().equals("0")||list.getStatu().equals("1")||list.getStatu().equals("3")||list.getStatu().equals("E")||list.getStatu().equals("F")||list.getStatu().equals("G"));
		model.addAttribute("contractList", contractList);
		
		model.addAttribute("insuranceC", insuranceC);
		return "logistics/insurance/insuranceCForm";
	}
	
	/**
	 * 查看审批表单
	 */
	@RequiresPermissions("insurance:insuranceC:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(InsuranceC insuranceC, Model model) {
		//合同列表
		ContractC contract = new ContractC();
		List<ContractC> contractList = contractCService.findList(contract);
		model.addAttribute("contractList", contractList);
		
		model.addAttribute("insuranceC", insuranceC);
		return "logistics/insuranceCheck/insuranceCForm";
	}

	/**
	 * 保存保险
	 */
	@RequiresPermissions("insurance:insuranceC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated InsuranceC insuranceC) {
		if(insuranceC.getStatu().equals("1")||insuranceC.getStatu().equals("0")){
			insuranceC.setCheckBy("");
			insuranceC.setCheckRemarks("");
			insuranceC.setCheckTime(new Date(0));
			insuranceCService.save(insuranceC);
			return renderResult(Global.TRUE, text("保存保险成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择草稿或待审！"));	
		}
	}
	
	/**
	 * 保存审批
	 */
	@RequiresPermissions("insurance:insuranceC:edit")
	@PostMapping(value = "checkSave")
	@ResponseBody
	public String checkSave(@Validated InsuranceC insuranceC) {
		if(insuranceC.getStatu().equals("3")||insuranceC.getStatu().equals("2")){
			User user = UserUtils.getUser();
			insuranceC.setCheckBy(user.getUserName());
			insuranceC.setCheckTime(new Date());
			insuranceCService.save(insuranceC);
			return renderResult(Global.TRUE, text("保存审批成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择通过或驳回！"));	
		}
		
		
	}
	
	/**
	 * 停用保险
	 */
	@RequiresPermissions("insurance:insuranceC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(InsuranceC insuranceC) {
		insuranceC.setStatus(InsuranceC.STATUS_DISABLE);
		insuranceCService.updateStatus(insuranceC);
		return renderResult(Global.TRUE, text("停用保险成功"));
	}
	
	/**
	 * 启用保险
	 */
	@RequiresPermissions("insurance:insuranceC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(InsuranceC insuranceC) {
		insuranceC.setStatus(InsuranceC.STATUS_NORMAL);
		insuranceCService.updateStatus(insuranceC);
		return renderResult(Global.TRUE, text("启用保险成功"));
	}
	
	/**
	 * 删除保险
	 */
	@RequiresPermissions("insurance:insuranceC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(InsuranceC insuranceC) {
		insuranceCService.delete(insuranceC);
		return renderResult(Global.TRUE, text("删除保险成功！"));
	}
	
}