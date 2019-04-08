/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.consign.web;

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
import com.jeesite.modules.logistics.consign.entity.ConsignC;
import com.jeesite.modules.logistics.consign.entity.ConsignProductC;
import com.jeesite.modules.logistics.consign.service.ConsignCService;
import com.jeesite.modules.purandsell.purchase.entity.PurProductC;
import com.jeesite.modules.purandsell.purchase.entity.PurchaseC;
import com.jeesite.modules.purandsell.sales.entity.ContractC;
import com.jeesite.modules.purandsell.sales.entity.SaleProductC;
import com.jeesite.modules.purandsell.sales.service.ContractCService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 托运管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-04-03
 */
@Controller
@RequestMapping(value = "${adminPath}/consign/consignC")
public class ConsignCController extends BaseController {

	@Autowired
	private ConsignCService consignCService;
	@Autowired
	private ContractCService contractCService;
	@Autowired
	private ProductCService productCService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ConsignC get(String id, boolean isNewRecord) {
		return consignCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("consign:consignC:view")
	@RequestMapping(value = {"list", ""})
	public String list(ConsignC consignC, Model model) {
		model.addAttribute("consignC", consignC);
		return "logistics/consign/consignCList";
	}
	
	/**
	 * 查询审批列表
	 */
	@RequiresPermissions("consign:consignC:view")
	@RequestMapping(value = {"checkList", ""})
	public String checkList(ConsignC consignC, Model model) {
		model.addAttribute("consignC", consignC);
		return "logistics/consignCheck/consignCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("consign:consignC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ConsignC> listData(ConsignC consignC, HttpServletRequest request, HttpServletResponse response) {
		consignC.setPage(new Page<>(request, response));
		Page<ConsignC> page = consignCService.findPage(consignC);
		return page;
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
	 * 查看编辑表单
	 */
	@RequiresPermissions("consign:consignC:view")
	@RequestMapping(value = "form")
	public String form(ConsignC consignC, Model model) {
		//合同列表
		ContractC contract = new ContractC();
		List<ContractC> contractList = contractCService.findList(contract);
		model.addAttribute("contractList", contractList);
				
		//剔除不属于托运的货物
		if(consignC!=null&&consignC.getId()!=null&&!consignC.getId().isEmpty()){
			consignC.getConsignProductCList().removeIf(list->list.getConsignCId()==null||!list.getTabletype().equals("托运管理")
					||!list.getConsignCId().getId().equals(consignC.getId()));			
		}
		
		model.addAttribute("consignC", consignC);
		return "logistics/consign/consignCForm";
	}
	
	/**
	 * 查看审批表单
	 */
	@RequiresPermissions("consign:consignC:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(ConsignC consignC, Model model) {
		//剔除不属于托运的货物
		if(consignC!=null&&consignC.getId()!=null&&!consignC.getId().isEmpty()){
			consignC.getConsignProductCList().removeIf(list->list.getConsignCId()==null||!list.getTabletype().equals("托运管理")
					||!list.getConsignCId().getId().equals(consignC.getId()));			
		}
		
		model.addAttribute("consignC", consignC);
		return "logistics/consignCheck/consignCForm";
	}
	
	/**
	 * 填补货物
	 */
	@RequiresPermissions("purchase:purchaseC:view")
	@RequestMapping("fullProduct")
	@ResponseBody
	public List<ConsignProductC> fullProduct(ConsignC consignC, Model model, @Param("contractId")String contractId){
		if(contractId==null||contractId.isEmpty())
			return null;
		
		ContractC contract = new ContractC();
		contract.setId(contractId);
		ContractC contractC = contractCService.get(contract);
		List<ConsignProductC> products = new ArrayList<ConsignProductC>();
		
		//剔除非销售管理的货物
		if(contractC!=null&&contractC.getId()!=null&&!contractC.getId().isEmpty()){
			contractC.getSaleProductCList().removeIf(list->list.getTabletype()==null||!list.getTabletype().equals("销售合同")
					||!list.getContractCId().getId().equals(contractC.getId()));			
		}
		
		for(SaleProductC p : contractC.getSaleProductCList()){
			ConsignProductC product = new ConsignProductC();
			product.setConsignCId(null);
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
		
		return products;
	}

	/**
	 * 保存审批
	 */
	@RequiresPermissions("consign:consignC:edit")
	@PostMapping(value = "checkSave")
	@ResponseBody
	public String checkSave(@Validated ConsignC consignC) {
		if(consignC.getStatu().equals("3")||consignC.getStatu().equals("2")){
			User user = UserUtils.getUser();
			consignC.setCheckBy(user.getUserName());
			consignC.setCheckTime(new Date());
			consignCService.save(consignC);
			return renderResult(Global.TRUE, text("保存审批成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择通过或驳回！"));	
		}
	}
	
	/**
	 * 保存托运
	 */
	@RequiresPermissions("consign:consignC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ConsignC consignC) {
		if(consignC.getStatu().equals("0")||consignC.getStatu().equals("1")){
			consignCService.save(consignC);
			return renderResult(Global.TRUE, text("保存托运成功！"));
		}else{
			return renderResult(Global.FALSE, text("申请请选择草稿或待审！"));	
		}
	}
	
	/**
	 * 停用托运
	 */
	@RequiresPermissions("consign:consignC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(ConsignC consignC) {
		consignC.setStatus(ConsignC.STATUS_DISABLE);
		consignCService.updateStatus(consignC);
		return renderResult(Global.TRUE, text("停用托运成功"));
	}
	
	/**
	 * 启用托运
	 */
	@RequiresPermissions("consign:consignC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(ConsignC consignC) {
		consignC.setStatus(ConsignC.STATUS_NORMAL);
		consignCService.updateStatus(consignC);
		return renderResult(Global.TRUE, text("启用托运成功"));
	}
	
	/**
	 * 删除托运
	 */
	@RequiresPermissions("consign:consignC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ConsignC consignC) {
		consignCService.delete(consignC);
		return renderResult(Global.TRUE, text("删除托运成功！"));
	}
	
}