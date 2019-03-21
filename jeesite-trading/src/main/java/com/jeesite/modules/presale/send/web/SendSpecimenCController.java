/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.presale.send.web;

import java.io.IOException;
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
import com.jeesite.modules.basic.customers.entity.CustomersC;
import com.jeesite.modules.basic.customers.service.CustomersCService;
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.basic.product.service.ProductCService;
import com.jeesite.modules.presale.send.entity.SendSpecimenC;
import com.jeesite.modules.presale.send.service.SendSpecimenCService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 寄样管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-03-20
 */
@Controller
@RequestMapping(value = "${adminPath}/send/sendSpecimenC")
public class SendSpecimenCController extends BaseController {

	@Autowired
	private SendSpecimenCService sendSpecimenCService;
	
	@Autowired
	private CustomersCService customersCService; 
	
	@Autowired
	private ProductCService productCService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public SendSpecimenC get(String id, boolean isNewRecord) {
		return sendSpecimenCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("send:sendSpecimenC:view")
	@RequestMapping(value = {"list", ""})
	public String list(SendSpecimenC sendSpecimenC, Model model) {
		model.addAttribute("sendSpecimenC", sendSpecimenC);
		return "presale/send/sendSpecimenCList";
	}
	
	/**
	 * 审批列表
	 */
	@RequiresPermissions("send:sendSpecimenC:view")
	@RequestMapping(value = {"checkList", ""})
	public String checkList(SendSpecimenC sendSpecimenC, Model model) {
		model.addAttribute("sendSpecimenC", sendSpecimenC);
		return "presale/sendCheck/sendSpecimenCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("send:sendSpecimenC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<SendSpecimenC> listData(SendSpecimenC sendSpecimenC, HttpServletRequest request, HttpServletResponse response) {
		sendSpecimenC.setPage(new Page<>(request, response));
		Page<SendSpecimenC> page = sendSpecimenCService.findPage(sendSpecimenC);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("send:sendSpecimenC:view")
	@RequestMapping(value = "form")
	public String form(SendSpecimenC sendSpecimenC, Model model) {
		//客户列表
		CustomersC customer = new CustomersC();
		List<CustomersC> customers =  customersCService.findList(customer);
		model.addAttribute("customers", customers);
		//剔除非寄样管理的货物
		if(sendSpecimenC!=null&&sendSpecimenC.getId()!=null&&!sendSpecimenC.getId().isEmpty()){
			sendSpecimenC.getSendProductCList().removeIf(list->list.getTabletype()==null||!list.getTabletype().equals("寄样管理")
					||!list.getSendSpecimenCId().getId().equals(sendSpecimenC.getId()));			
		}
		model.addAttribute("sendSpecimenC", sendSpecimenC);
		return "presale/send/sendSpecimenCForm";
	}
	
	/**
	 * 产品列表（货物）
	 */
	@RequiresPermissions("offer:offerC:view")
	@RequestMapping(value = "productList")
	public void productSelect(HttpServletResponse response){
		ProductC product = new ProductC();
		List<ProductC> products = productCService.findList(product);
		response.setCharacterEncoding("UTF-8");
		StringBuilder cust = new StringBuilder();
		cust.append("<select>");
		cust.append("<option value=></option>");
		for(ProductC c : products){
			cust.append("<option value=");
			cust.append(c.getId());
			cust.append(">");
			cust.append(c.getName());
			cust.append("</option>");
		}
		cust.append("</select>");
		
		try {
			response.getWriter().write(cust.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查看审批表单
	 */
	@RequiresPermissions("send:sendSpecimenC:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(SendSpecimenC sendSpecimenC, Model model) {
		//客户列表
		CustomersC customer = new CustomersC();
		List<CustomersC> customers =  customersCService.findList(customer);
		model.addAttribute("customers", customers);
		//剔除非寄样的货物
		if(sendSpecimenC!=null&&sendSpecimenC.getId()!=null&&!sendSpecimenC.getId().isEmpty()){
			sendSpecimenC.getSendProductCList().removeIf(list->list.getTabletype()==null||!list.getTabletype().equals("寄样管理")
					||!list.getSendSpecimenCId().getId().equals(sendSpecimenC.getId()));			
		}
		model.addAttribute("sendSpecimenC", sendSpecimenC);
		return "presale/sendCheck/sendSpecimenCForm";
	}
	
	/**
	 * 保存寄样
	 */
	@RequiresPermissions("send:sendSpecimenC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated SendSpecimenC sendSpecimenC) {
		if(sendSpecimenC.getStatu().equals("1")||sendSpecimenC.getStatu().equals("2")){
			sendSpecimenCService.save(sendSpecimenC);			
			return renderResult(Global.TRUE, text("保存寄样成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择草稿或待审！"));	
		}
	}
	
	/**
	 * 保存审批
	 */
	@RequiresPermissions("send:sendSpecimenC:edit")
	@PostMapping(value = "checkSave")
	@ResponseBody
	public String checkSave(@Validated SendSpecimenC sendSpecimenC) {
		if(sendSpecimenC.getStatu().equals("3")||sendSpecimenC.getStatu().equals("4")){
			User user = UserUtils.getUser();
			sendSpecimenC.setCheckBy(user.getUserName());
			sendSpecimenC.setCheckTime(new Date());
			sendSpecimenCService.save(sendSpecimenC);
			return renderResult(Global.TRUE, text("保存审批成功！"));			
		}else{
			return renderResult(Global.FALSE, text("审批请选择通过或驳回！"));	
		}
	}
	
	/**
	 * 停用寄样
	 */
	@RequiresPermissions("send:sendSpecimenC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(SendSpecimenC sendSpecimenC) {
		sendSpecimenC.setStatus(SendSpecimenC.STATUS_DISABLE);
		sendSpecimenCService.updateStatus(sendSpecimenC);
		return renderResult(Global.TRUE, text("停用寄样成功"));
	}
	
	/**
	 * 启用寄样
	 */
	@RequiresPermissions("send:sendSpecimenC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(SendSpecimenC sendSpecimenC) {
		sendSpecimenC.setStatus(SendSpecimenC.STATUS_NORMAL);
		sendSpecimenCService.updateStatus(sendSpecimenC);
		return renderResult(Global.TRUE, text("启用寄样成功"));
	}
	
	/**
	 * 删除寄样
	 */
	@RequiresPermissions("send:sendSpecimenC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(SendSpecimenC sendSpecimenC) {
		sendSpecimenCService.delete(sendSpecimenC);
		return renderResult(Global.TRUE, text("删除寄样成功！"));
	}
	
}