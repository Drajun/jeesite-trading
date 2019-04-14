/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.financial.paybill.web;

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
import com.jeesite.modules.financial.paybill.entity.PaybillF;
import com.jeesite.modules.financial.paybill.service.PaybillFService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 付款管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-04-09
 */
@Controller
@RequestMapping(value = "${adminPath}/paybill/paybillF")
public class PaybillFController extends BaseController {

	@Autowired
	private PaybillFService paybillFService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public PaybillF get(String id, boolean isNewRecord) {
		return paybillFService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("paybill:paybillF:view")
	@RequestMapping(value = {"list", ""})
	public String list(PaybillF paybillF, Model model) {
		model.addAttribute("paybillF", paybillF);
		return "financial/paybill/paybillFList";
	}
	
	/**
	 * 审批列表
	 */
	@RequiresPermissions("paybill:paybillF:view")
	@RequestMapping(value = {"checkList", ""})
	public String checkList(PaybillF paybillF, Model model) {
		model.addAttribute("paybillF", paybillF);
		return "financial/paybillCheck/paybillFList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("paybill:paybillF:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<PaybillF> listData(PaybillF paybillF, HttpServletRequest request, HttpServletResponse response) {
		paybillF.setPage(new Page<>(request, response));
		Page<PaybillF> page = paybillFService.findPage(paybillF);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("paybill:paybillF:view")
	@RequestMapping(value = "form")
	public String form(PaybillF paybillF, Model model) {
		model.addAttribute("paybillF", paybillF);
		return "financial/paybill/paybillFForm";
	}
	
	/**
	 * 查看审批表单
	 */
	@RequiresPermissions("paybill:paybillF:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(PaybillF paybillF, Model model) {
		model.addAttribute("paybillF", paybillF);
		return "financial/paybillCheck/paybillFForm";
	}

	/**
	 * 保存付款管理
	 */
	@RequiresPermissions("paybill:paybillF:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated PaybillF paybillF) {
		if(paybillF.getStatu().equals("1")||paybillF.getStatu().equals("0")){
			paybillF.setCheckBy("");
			paybillF.setCheckRemarks("");
			paybillF.setCheckTime(new Date(0));
			//是初次则设置付款单编码
			if(paybillF.getId()==null||paybillF.getId()==""){
				String code = "PBF"+new Date().getTime()%999999999+"";				
				paybillF.setPaybillCode(code);
			}
			paybillFService.save(paybillF);
			return renderResult(Global.TRUE, text("保存付款管理成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择草稿或待审！"));	
		}
	}
	
	/**
	 * 保存审批管理
	 */
	@RequiresPermissions("paybill:paybillF:edit")
	@PostMapping(value = "checkSave")
	@ResponseBody
	public String checkSave(@Validated PaybillF paybillF) {
		if(paybillF.getStatu().equals("3")||paybillF.getStatu().equals("2")){
			User user = UserUtils.getUser();
			paybillF.setCheckBy(user.getUserName());
			paybillF.setCheckTime(new Date());
			paybillFService.save(paybillF);
			return renderResult(Global.TRUE, text("保存审批管理成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择通过或驳回！"));	
		}
	}
	
	/**
	 * 停用付款管理
	 */
	@RequiresPermissions("paybill:paybillF:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(PaybillF paybillF) {
		paybillF.setStatus(PaybillF.STATUS_DISABLE);
		paybillFService.updateStatus(paybillF);
		return renderResult(Global.TRUE, text("停用付款管理成功"));
	}
	
	/**
	 * 启用付款管理
	 */
	@RequiresPermissions("paybill:paybillF:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(PaybillF paybillF) {
		paybillF.setStatus(PaybillF.STATUS_NORMAL);
		paybillFService.updateStatus(paybillF);
		return renderResult(Global.TRUE, text("启用付款管理成功"));
	}
	
	/**
	 * 删除付款管理
	 */
	@RequiresPermissions("paybill:paybillF:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(PaybillF paybillF) {
		paybillFService.delete(paybillF);
		return renderResult(Global.TRUE, text("删除付款管理成功！"));
	}
	
}