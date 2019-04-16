/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.purandsell.returnc.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import com.jeesite.modules.basic.factory.service.FactoryCService;
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.basic.product.service.ProductCService;
import com.jeesite.modules.purandsell.purchase.entity.PurProductC;
import com.jeesite.modules.purandsell.purchase.entity.PurchaseC;
import com.jeesite.modules.purandsell.returnc.entity.ReturnC;
import com.jeesite.modules.purandsell.returnc.service.ReturnCService;
import com.jeesite.modules.purandsell.sales.entity.ContractC;
import com.jeesite.modules.purandsell.sales.entity.SaleProductC;
import com.jeesite.modules.purandsell.sales.service.ContractCService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 退换管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-04-15
 */
@Controller
@RequestMapping(value = "${adminPath}/returnc/returnC")
public class ReturnCController extends BaseController {

	@Autowired
	private ReturnCService returnCService;
	@Autowired
	private ContractCService contractCService;
	@Autowired
	private ProductCService productCService;
	@Autowired
	private CustomersCService customersCService; 
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ReturnC get(String id, boolean isNewRecord) {
		return returnCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("returnc:returnC:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReturnC returnC, Model model) {
		model.addAttribute("returnC", returnC);
		return "purandsell/returnc/returnCList";
	}
	
	/**
	 * 审批列表
	 */
	@RequiresPermissions("returnc:returnC:view")
	@RequestMapping(value = {"checkList", ""})
	public String checkList(ReturnC returnC, Model model) {
		model.addAttribute("returnC", returnC);
		return "purandsell/returncCheck/returnCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("returnc:returnC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ReturnC> listData(ReturnC returnC, HttpServletRequest request, HttpServletResponse response) {
		returnC.setPage(new Page<>(request, response));
		Page<ReturnC> page = returnCService.findPage(returnC);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("returnc:returnC:view")
	@RequestMapping(value = "form")
	public String form(ReturnC returnC, Model model) {
		//合同列表
		ContractC contract = new ContractC();
		List<ContractC> contractList = contractCService.findList(contract);
		contractList.removeIf(list->list.getStatu().equals("0")||list.getStatu().equals("1")||list.getStatu().equals("3")||list.getStatu().equals("E")||list.getStatu().equals("F")||list.getStatu().equals("G"));
		model.addAttribute("contractList", contractList);
		
		//客户列表
		CustomersC customer = new CustomersC();
		List<CustomersC> customers =  customersCService.findList(customer);
		model.addAttribute("customers", customers);
				
		//剔除不属于退换的货物
		if(returnC!=null&&returnC.getId()!=null&&!returnC.getId().isEmpty()){
			returnC.getReturnProductCList().removeIf(list->list.getReturnCId()==null||!list.getReturnCId().getId().equals(returnC.getId()));			
		}
		
		model.addAttribute("returnC", returnC);
		return "purandsell/returnc/returnCForm";
	}
	
	/**
	 * 查看审批表单
	 */
	@RequiresPermissions("returnc:returnC:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(ReturnC returnC, Model model) {
		
		//客户列表
		CustomersC customer = new CustomersC();
		List<CustomersC> customers =  customersCService.findList(customer);
		model.addAttribute("customers", customers);
		
		//剔除不属于退换的货物
		if(returnC!=null&&returnC.getId()!=null&&!returnC.getId().isEmpty()){
			returnC.getReturnProductCList().removeIf(list->list.getReturnCId()==null||!list.getReturnCId().getId().equals(returnC.getId()));			
		}
		model.addAttribute("returnC", returnC);
		return "purandsell/returncCheck/returnCForm";
	}

	/**
	 * 保存退换
	 */
	@RequiresPermissions("returnc:returnC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ReturnC returnC) {
		if(returnC.getStatu().equals("1")||returnC.getStatu().equals("2")){
			returnC.setCheckBy("");
			returnC.setCheckRemarks("");
			returnC.setCheckTime(new Date(0));
			//是初次则设置合同编码
			if(returnC.getId()==null||returnC.getId()==""){
				String code = "LRRE"+new Date().getTime()%9999999+"";				
				returnC.setReturnCode(code);
			}
			returnCService.save(returnC);			
			return renderResult(Global.TRUE, text("保存退换成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择草稿或待审！"));	
		}
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
	 * 填补货物
	 */
	@RequiresPermissions("returnc:returnC:view")
	@RequestMapping("fullProduct")
	@ResponseBody
	public List<PurProductC> fullProduct(PurchaseC purchaseC, Model model, @Param("contractId")String contractId){
		if(contractId==null||contractId.isEmpty())
			return null;
		
		ContractC contract = new ContractC();
		contract.setId(contractId);
		ContractC contractC = contractCService.get(contract);
		List<PurProductC> products = new ArrayList<PurProductC>();
		
		//剔除非销售管理的货物
		if(contractC!=null&&contractC.getId()!=null&&!contractC.getId().isEmpty()){
			contractC.getSaleProductCList().removeIf(list->list.getTabletype()==null||!list.getTabletype().equals("销售合同")
					||!list.getContractCId().getId().equals(contractC.getId()));			
		}
		
		for(SaleProductC p : contractC.getSaleProductCList()){
			PurProductC product = new PurProductC();
			product.setPurchaseCId(null);
			product.setProductCId(p.getProductCId());
			product.setProducCode(p.getProducCode());
			product.setName(p.getName());
			product.setPrice(p.getPrice());
			product.setNumber(p.getNumber());
			product.setPackageUnit(p.getPackageUnit());
			product.setNetWeight(p.getNetWeight());
			product.setGrossWeight(p.getGrossWeight());
			product.setSpec(p.getSpec());
			product.setSinglePackageType(p.getSinglePackageType());
			product.setInnerPackageType(p.getInnerPackageType());
			products.add(product);
		}
		purchaseC.setPurProductCList(products);
		
		return products;
	}
	
	/**
	 * 保存审批
	 */
	@RequiresPermissions("returnc:returnC:edit")
	@PostMapping(value = "checkSave")
	@ResponseBody
	public String checkSave(@Validated ReturnC returnC) {
		if(returnC.getStatu().equals("3")||returnC.getStatu().equals("4")){
			User user = UserUtils.getUser();
			returnC.setCheckBy(user.getUserName());
			returnC.setCheckTime(new Date());
			returnCService.save(returnC);
			
			//更改销售合同状态
			if(returnC.getStatu().equals("3")){
				ContractC contract = contractCService.get(returnC.getContractCode());
				if(contract!=null&&contract.getId()!=null){
					if(returnC.getReturnType().equals("0")||returnC.getReturnType().equals("2")||returnC.getReturnType().equals("3"))
						contract.setStatu("E");
					else if(returnC.getReturnType().equals("1"))
						contract.setStatu("G");
					else
						contract.setStatu("F");
					contractCService.save(contract);
				}
			}
			
			return renderResult(Global.TRUE, text("保存审批成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择通过或驳回！"));	
		}
	}
	
	/**
	 * 停用退换
	 */
	@RequiresPermissions("returnc:returnC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(ReturnC returnC) {
		returnC.setStatus(ReturnC.STATUS_DISABLE);
		returnCService.updateStatus(returnC);
		return renderResult(Global.TRUE, text("停用退换成功"));
	}
	
	/**
	 * 启用退换
	 */
	@RequiresPermissions("returnc:returnC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(ReturnC returnC) {
		returnC.setStatus(ReturnC.STATUS_NORMAL);
		returnCService.updateStatus(returnC);
		return renderResult(Global.TRUE, text("启用退换成功"));
	}
	
	/**
	 * 删除退换
	 */
	@RequiresPermissions("returnc:returnC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ReturnC returnC) {
		returnCService.delete(returnC);
		return renderResult(Global.TRUE, text("删除退换成功！"));
	}
	
}