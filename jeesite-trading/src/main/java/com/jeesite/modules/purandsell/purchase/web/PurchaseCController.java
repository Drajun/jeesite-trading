/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.purandsell.purchase.web;

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
import com.jeesite.modules.basic.factory.entity.FactoryC;
import com.jeesite.modules.basic.factory.service.FactoryCService;
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.basic.product.service.ProductCService;
import com.jeesite.modules.purandsell.purchase.entity.PurProductC;
import com.jeesite.modules.purandsell.purchase.entity.PurchaseC;
import com.jeesite.modules.purandsell.purchase.service.PurchaseCService;
import com.jeesite.modules.purandsell.sales.entity.ContractC;
import com.jeesite.modules.purandsell.sales.entity.SaleProductC;
import com.jeesite.modules.purandsell.sales.service.ContractCService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 订购管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-03-22
 */
@Controller
@RequestMapping(value = "${adminPath}/purchase/purchaseC")
public class PurchaseCController extends BaseController {

	@Autowired
	private PurchaseCService purchaseCService;
	@Autowired
	private ContractCService contractCService;
	@Autowired
	private FactoryCService factoryCService;
	@Autowired
	private ProductCService productCService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public PurchaseC get(String id, boolean isNewRecord) {
		return purchaseCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("purchase:purchaseC:view")
	@RequestMapping(value = {"list", ""})
	public String list(PurchaseC purchaseC, Model model) {
		model.addAttribute("purchaseC", purchaseC);
		return "purandsell/purchase/purchaseCList";
	}
	
	/**
	 * 审批列表
	 */
	@RequiresPermissions("purchase:purchaseC:view")
	@RequestMapping(value = {"checkList", ""})
	public String checkList(PurchaseC purchaseC, Model model) {
		model.addAttribute("purchaseC", purchaseC);
		return "purandsell/purchaseCheck/purchaseCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("purchase:purchaseC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<PurchaseC> listData(PurchaseC purchaseC, HttpServletRequest request, HttpServletResponse response) {
		purchaseC.setPage(new Page<>(request, response));
		Page<PurchaseC> page = purchaseCService.findPage(purchaseC);
		return page;
	}
	
	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("purchase:purchaseC:view")
	@RequestMapping(value = "form")
	public String form(PurchaseC purchaseC, Model model) {
		//合同列表
		ContractC contract = new ContractC();
		List<ContractC> contractList = contractCService.findList(contract);
		model.addAttribute("contractList", contractList);
		
		//生产厂商列表
		FactoryC factory = new FactoryC();
		List<FactoryC> factoryList = factoryCService.findList(factory);
		model.addAttribute("factoryList", factoryList);
		
		//剔除不属于订购的货物
		if(purchaseC!=null&&purchaseC.getId()!=null&&!purchaseC.getId().isEmpty()){
			purchaseC.getPurProductCList().removeIf(list->list.getTabletype()==null||!list.getTabletype().equals("订购合同")
					||!list.getPurchaseCId().getId().equals(purchaseC.getId()));			
		}
		
		model.addAttribute("purchaseC", purchaseC);
		
		return "purandsell/purchase/purchaseCForm";
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
	@RequiresPermissions("purchase:purchaseC:view")
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
	 * 查看审批表单
	 */
	@RequiresPermissions("purchase:purchaseC:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(PurchaseC purchaseC, Model model) {
		//生产厂商列表
		FactoryC factory = new FactoryC();
		List<FactoryC> factoryList = factoryCService.findList(factory);
		model.addAttribute("factoryList", factoryList);
		
		//剔除不属于订购的货物
		if(purchaseC!=null&&purchaseC.getId()!=null&&!purchaseC.getId().isEmpty()){
			purchaseC.getPurProductCList().removeIf(list->list.getPurchaseCId()==null||!list.getPurchaseCId().getId().equals(purchaseC.getId()));			
		}
		
		model.addAttribute("purchaseC", purchaseC);
		
		return "purandsell/purchaseCheck/purchaseCForm";
	}
	
	/**
	 * 保存订购
	 */
	@RequiresPermissions("purchase:purchaseC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated PurchaseC purchaseC) {
		if(purchaseC.getStatu().equals("1")||purchaseC.getStatu().equals("0")){
			purchaseC.setCheckBy("");
			purchaseC.setCheckRemarks("");
			purchaseC.setCheckTime(new Date(0));
			//是初次则设置合同编码
			if(purchaseC.getId()==null||purchaseC.getId()==""){
				String code = "LRP"+new Date().getTime()%9999999+"";				
				purchaseC.setContractCode(code);
			}
			purchaseCService.save(purchaseC);			
			return renderResult(Global.TRUE, text("保存订购成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择草稿或待审！"));	
		}
	}
	
	/**
	 * 保存审批
	 */
	@RequiresPermissions("purchase:purchaseC:edit")
	@PostMapping(value = "checkSave")
	@ResponseBody
	public String checkSave(@Validated PurchaseC purchaseC) {
		if(purchaseC.getStatu().equals("3")||purchaseC.getStatu().equals("2")){
			User user = UserUtils.getUser();
			purchaseC.setCheckBy(user.getUserName());
			purchaseC.setCheckTime(new Date());
			purchaseCService.save(purchaseC);
			return renderResult(Global.TRUE, text("保存审批成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择通过或驳回！"));	
		}
	}
	
	/**
	 * 停用订购
	 */
	@RequiresPermissions("purchase:purchaseC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(PurchaseC purchaseC) {
		purchaseC.setStatus(PurchaseC.STATUS_DISABLE);
		purchaseCService.updateStatus(purchaseC);
		return renderResult(Global.TRUE, text("停用订购成功"));
	}
	
	/**
	 * 启用订购
	 */
	@RequiresPermissions("purchase:purchaseC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(PurchaseC purchaseC) {
		purchaseC.setStatus(PurchaseC.STATUS_NORMAL);
		purchaseCService.updateStatus(purchaseC);
		return renderResult(Global.TRUE, text("启用订购成功"));
	}
	
	/**
	 * 删除订购
	 */
	@RequiresPermissions("purchase:purchaseC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(PurchaseC purchaseC) {
		purchaseCService.delete(purchaseC);
		return renderResult(Global.TRUE, text("删除订购成功！"));
	}
	
}