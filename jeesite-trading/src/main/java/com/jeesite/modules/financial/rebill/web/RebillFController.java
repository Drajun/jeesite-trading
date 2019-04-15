/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.financial.rebill.web;

import java.util.Date;

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
import com.jeesite.modules.financial.rebill.entity.RebillF;
import com.jeesite.modules.financial.rebill.service.RebillFService;
import com.jeesite.modules.purandsell.sales.entity.ContractC;
import com.jeesite.modules.purandsell.sales.service.ContractCService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 收款管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-04-09
 */
@Controller
@RequestMapping(value = "${adminPath}/rebill/rebillF")
public class RebillFController extends BaseController {

	@Autowired
	private RebillFService rebillFService;
	@Autowired
	private ContractCService contractCService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public RebillF get(String id, boolean isNewRecord) {
		return rebillFService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("rebill:rebillF:view")
	@RequestMapping(value = {"list", ""})
	public String list(RebillF rebillF, Model model) {
		model.addAttribute("rebillF", rebillF);
		return "financial/rebill/rebillFList";
	}
	
	/**
	 * 审批列表
	 */
	@RequiresPermissions("rebill:rebillF:view")
	@RequestMapping(value = {"checkList", ""})
	public String checkList(RebillF rebillF, Model model) {
		model.addAttribute("rebillF", rebillF);
		return "financial/rebillCheck/rebillFList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("rebill:rebillF:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<RebillF> listData(RebillF rebillF, HttpServletRequest request, HttpServletResponse response) {
		rebillF.setPage(new Page<>(request, response));
		Page<RebillF> page = rebillFService.findPage(rebillF);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("rebill:rebillF:view")
	@RequestMapping(value = "form")
	public String form(RebillF rebillF, Model model) {
		model.addAttribute("rebillF", rebillF);
		return "financial/rebill/rebillFForm";
	}
	
	/**
	 * 查看审批表单
	 */
	@RequiresPermissions("rebill:rebillF:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(RebillF rebillF, Model model) {
		model.addAttribute("rebillF", rebillF);
		return "financial/rebillCheck/rebillFForm";
	}

	/**
	 * 保存收款
	 */
	@RequiresPermissions("rebill:rebillF:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated RebillF rebillF) {
		if(rebillF.getStatu().equals("1")||rebillF.getStatu().equals("0")){
			rebillF.setCheckBy("");
			rebillF.setCheckRemarks("");
			rebillF.setCheckTime(new Date(0));
			//是初次则设置收款单编码
			if(rebillF.getId()==null||rebillF.getId()==""){
				String code = "RBF"+new Date().getTime()%999999999+"";				
				rebillF.setRebillCode(code);
			}
			rebillFService.save(rebillF);
			return renderResult(Global.TRUE, text("保存收款成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择草稿或待审！"));	
		}
	}
	
	/**
	 * 保存审批
	 */
	@RequiresPermissions("rebill:rebillF:edit")
	@PostMapping(value = "checkSave")
	@ResponseBody
	public String checkSave(@Validated RebillF rebillF) {
		if(rebillF.getStatu().equals("3")||rebillF.getStatu().equals("2")){
			User user = UserUtils.getUser();
			rebillF.setCheckBy(user.getUserName());
			rebillF.setCheckTime(new Date());
			rebillFService.save(rebillF);
			
			//改变销售合同状态
			if(rebillF.getStatu().equals("2")&&rebillF.getRebillType().equals("5")){
				ContractC contract = contractCService.get(rebillF.getBillCode());
				if(contract!=null&&contract.getId()!=null){
					contract.setStatu("D");
					contractCService.save(contract);
				}
			}
			
			return renderResult(Global.TRUE, text("保存审批成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择通过或驳回！"));	
		}
	}
	
	/**
	 * 停用收款
	 */
	@RequiresPermissions("rebill:rebillF:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(RebillF rebillF) {
		rebillF.setStatus(RebillF.STATUS_DISABLE);
		rebillFService.updateStatus(rebillF);
		return renderResult(Global.TRUE, text("停用收款成功"));
	}
	
	/**
	 * 启用收款
	 */
	@RequiresPermissions("rebill:rebillF:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(RebillF rebillF) {
		rebillF.setStatus(RebillF.STATUS_NORMAL);
		rebillFService.updateStatus(rebillF);
		return renderResult(Global.TRUE, text("启用收款成功"));
	}
	
	/**
	 * 删除收款
	 */
	@RequiresPermissions("rebill:rebillF:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(RebillF rebillF) {
		rebillFService.delete(rebillF);
		return renderResult(Global.TRUE, text("删除收款成功！"));
	}
	
}