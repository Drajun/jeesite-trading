/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.purandsell.sales.web;

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
import com.jeesite.modules.basic.printer.service.PrinterService;
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.basic.product.service.ProductCService;
import com.jeesite.modules.purandsell.sales.entity.ContractC;
import com.jeesite.modules.purandsell.sales.service.ContractCService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 销售合同Controller
 * @author longlou.d@foxmail.com
 * @version 2019-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/sales/contractC")
public class ContractCController extends BaseController {

	@Autowired
	private ContractCService contractCService;
	
	@Autowired
	private CustomersCService customersCService; 
	
	@Autowired
	private ProductCService productCService;

	@Autowired
	private PrinterService printerService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ContractC get(String id, boolean isNewRecord) {
		return contractCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("sales:contractC:view")
	@RequestMapping(value = {"list", ""})
	public String list(ContractC contractC, Model model) {
		model.addAttribute("contractC", contractC);
		return "purandsell/sales/contractCList";
	}
	
	/**
	 * 审批列表
	 */
	@RequiresPermissions("sales:contractC:view")
	@RequestMapping(value = {"checkList", ""})
	public String checkList(ContractC contractC, Model model) {
		model.addAttribute("contractC", contractC);
		return "purandsell/salesCheck/contractCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("sales:contractC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ContractC> listData(ContractC contractC, HttpServletRequest request, HttpServletResponse response) {
		contractC.setPage(new Page<>(request, response));
		Page<ContractC> page = contractCService.findPage(contractC);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("sales:contractC:view")
	@RequestMapping(value = "form")
	public String form(ContractC contractC, Model model) {
		//客户列表
		CustomersC customer = new CustomersC();
		List<CustomersC> customers =  customersCService.findList(customer);
		model.addAttribute("customers", customers);
		
		//剔除非销售管理的货物
		if(contractC!=null&&contractC.getId()!=null&&!contractC.getId().isEmpty()){
			contractC.getSaleProductCList().removeIf(list->list.getTabletype()==null||!list.getTabletype().equals("销售合同")
					||!list.getContractCId().getId().equals(contractC.getId()));			
		}
		model.addAttribute("contractC", contractC);
		return "purandsell/sales/contractCForm";
	}
	
	/**
	 * 产品列表（货物）
	 */
	@RequiresPermissions("sales:contractC:view")
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
	@RequiresPermissions("sales:contractC:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(ContractC contractC, Model model) {
		//客户列表
		CustomersC customer = new CustomersC();
		List<CustomersC> customers =  customersCService.findList(customer);
		model.addAttribute("customers", customers);
		
		//剔除非寄样管理的货物
		if(contractC!=null&&contractC.getId()!=null&&!contractC.getId().isEmpty()){
			contractC.getSaleProductCList().removeIf(list->list.getTabletype()==null||!list.getTabletype().equals("销售合同")
					||!list.getContractCId().getId().equals(contractC.getId()));			
		}
		model.addAttribute("contractC", contractC);
		return "purandsell/salesCheck/contractCForm";
	}

	/**
	 * 保存销售合同
	 */
	@RequiresPermissions("sales:contractC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ContractC contractC) {
		if(contractC.getStatu().equals("1")||contractC.getStatu().equals("0")){
			//是初次则设置合同编码
			if(contractC.getId()==null||contractC.getId()==""){
				String code = "LRB"+new Date().getTime()%9999999+"";				
				contractC.setContractCode(code);
			}
			contractCService.save(contractC);
			return renderResult(Global.TRUE, text("保存销售合同成功！"));			
		}else{
			return renderResult(Global.FALSE, text("审批请选择草稿或待审！"));
		}
	}
	
	/**
	 * 保存销售审批
	 */
	@RequiresPermissions("sales:contractC:edit")
	@PostMapping(value = "checkSave")
	@ResponseBody
	public String checkSave(@Validated ContractC contractC) {
		if(contractC.getStatu().equals("2")||contractC.getStatu().equals("3")){
			User user = UserUtils.getUser();
			contractC.setCheckBy(user.getUserName());
			contractC.setCheckTime(new Date());
			contractCService.save(contractC);
			return renderResult(Global.TRUE, text("保存销售审批成功！"));			
		}else{
			return renderResult(Global.FALSE, text("审批请选择通过或驳回！"));
		}
	}
	
	/**
	 * 停用销售合同
	 */
	@RequiresPermissions("sales:contractC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(ContractC contractC) {
		contractC.setStatus(ContractC.STATUS_DISABLE);
		contractCService.updateStatus(contractC);
		return renderResult(Global.TRUE, text("停用销售合同成功"));
	}
	
	/**
	 * 启用销售合同
	 */
	@RequiresPermissions("sales:contractC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(ContractC contractC) {
		contractC.setStatus(ContractC.STATUS_NORMAL);
		contractCService.updateStatus(contractC);
		return renderResult(Global.TRUE, text("启用销售合同成功"));
	}
	
	/**
	 * 删除销售合同
	 */
	@RequiresPermissions("sales:contractC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ContractC contractC) {
		contractCService.delete(contractC);
		return renderResult(Global.TRUE, text("删除销售合同成功！"));
	}
	
	/**
	 * 打印销售合同
	 */
	@RequiresPermissions("sales:contractC:view")
	@RequestMapping(value = "print")
	@ResponseBody
	public String print(ContractC contractC,HttpServletResponse response) {
		try {
			printerService.printSalesContract(contractC, response);
			return renderResult(Global.TRUE, text("打印完成！"));
		}catch (IOException e) {
			e.printStackTrace();
			return renderResult(Global.TRUE, text("打印出错！"));
		}
	}
	
}