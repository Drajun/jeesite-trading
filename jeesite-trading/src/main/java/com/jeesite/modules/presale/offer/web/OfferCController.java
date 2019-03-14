/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.presale.offer.web;

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
import com.jeesite.modules.presale.offer.entity.OfferC;
import com.jeesite.modules.presale.offer.service.OfferCService;
import com.jeesite.modules.sys.entity.User;

/**
 * 报价管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/offer/offerC")
public class OfferCController extends BaseController {

	@Autowired
	private OfferCService offerCService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public OfferC get(String id, boolean isNewRecord) {
		return offerCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("offer:offerC:view")
	@RequestMapping(value = {"list", ""})
	public String list(OfferC offerC, Model model) {
		model.addAttribute("offerC", offerC);
		return "presale/offer/offerCList";
	}
	
	/**
	 * 审批列表
	 */
	@RequiresPermissions("offer:offerC:view")
	@RequestMapping(value = {"checkList", ""})
	public String checkList(OfferC offerC, Model model) {
		model.addAttribute("offerC", offerC);
		return "presale/offerCheck/offerCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("offer:offerC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<OfferC> listData(OfferC offerC, HttpServletRequest request, HttpServletResponse response) {
		offerC.setPage(new Page<>(request, response));
		Page<OfferC> page = offerCService.findPage(offerC);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("offer:offerC:view")
	@RequestMapping(value = "form")
	public String form(OfferC offerC, Model model) {
		model.addAttribute("offerC", offerC);
		return "presale/offer/offerCForm";
	}
	
	/**
	 * 查看审批编辑表单
	 */
	@RequiresPermissions("offer:offerC:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(OfferC offerC, Model model) {
		model.addAttribute("offerC", offerC);
		return "presale/offerCheck/offerCForm";
	}

	/**
	 * 保存报价
	 */
	@RequiresPermissions("offer:offerC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated OfferC offerC) {
		if(offerC.getStatu().equals("1")||offerC.getStatu().equals("2")){
			offerCService.save(offerC);
			return renderResult(Global.TRUE, text("保存报价成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择草稿或待审！"));	
		}
	}
	
	/**
	 * 保存报价审批
	 */
	@RequiresPermissions("offer:offerC:edit")
	@PostMapping(value = "checkSave")
	@ResponseBody
	public String checkSave(@Validated OfferC offerC, User user) {
		if(offerC.getStatu().equals("3")||offerC.getStatu().equals("4")){
			offerC.setCheckBy(user.getUserName());
			offerC.setCheckTime(new Date());
			offerCService.save(offerC);
			return renderResult(Global.TRUE, text("保存报价审批成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择通过或驳回！"));	
		}
	}
	
	/**
	 * 停用报价
	 */
	@RequiresPermissions("offer:offerC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(OfferC offerC) {
		offerC.setStatus(OfferC.STATUS_DISABLE);
		offerCService.updateStatus(offerC);
		return renderResult(Global.TRUE, text("停用报价成功"));
	}
	
	/**
	 * 启用报价
	 */
	@RequiresPermissions("offer:offerC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(OfferC offerC) {
		offerC.setStatus(OfferC.STATUS_NORMAL);
		offerCService.updateStatus(offerC);
		return renderResult(Global.TRUE, text("启用报价成功"));
	}
	
	/**
	 * 删除报价
	 */
	@RequiresPermissions("offer:offerC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(OfferC offerC) {
		offerCService.delete(offerC);
		return renderResult(Global.TRUE, text("删除报价成功！"));
	}
	
}