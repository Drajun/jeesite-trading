/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.inspection.web;

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
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.basic.product.service.ProductCService;
import com.jeesite.modules.logistics.consign.entity.ConsignC;
import com.jeesite.modules.logistics.inspection.entity.InspectionC;
import com.jeesite.modules.logistics.inspection.entity.InspectionProductC;
import com.jeesite.modules.logistics.inspection.service.InspectionCService;
import com.jeesite.modules.purandsell.purchase.entity.PurProductC;
import com.jeesite.modules.purandsell.sales.entity.ContractC;
import com.jeesite.modules.purandsell.sales.entity.SaleProductC;
import com.jeesite.modules.purandsell.sales.service.ContractCService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 报检管理Controller
 * @author longlou.d@foxmail.com
 * @version 2019-04-04
 */
@Controller
@RequestMapping(value = "${adminPath}/inspection/inspectionC")
public class InspectionCController extends BaseController {

	@Autowired
	private InspectionCService inspectionCService;
	@Autowired
	private ContractCService contractCService;
	@Autowired
	private ProductCService productCService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public InspectionC get(String id, boolean isNewRecord) {
		return inspectionCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("inspection:inspectionC:view")
	@RequestMapping(value = {"list", ""})
	public String list(InspectionC inspectionC, Model model) {
		model.addAttribute("inspectionC", inspectionC);
		return "logistics/inspection/inspectionCList";
	}
	
	/**
	 * 查询审批列表
	 */
	@RequiresPermissions("inspection:inspectionC:view")
	@RequestMapping(value = {"checkList", ""})
	public String checkList(InspectionC inspectionC, Model model) {
		model.addAttribute("inspectionC", inspectionC);
		return "logistics/inspectionCheck/inspectionCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("inspection:inspectionC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<InspectionC> listData(InspectionC inspectionC, HttpServletRequest request, HttpServletResponse response) {
		inspectionC.setPage(new Page<>(request, response));
		Page<InspectionC> page = inspectionCService.findPage(inspectionC);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("inspection:inspectionC:view")
	@RequestMapping(value = "form")
	public String form(InspectionC inspectionC, Model model) {
		//合同列表
		ContractC contract = new ContractC();
		List<ContractC> contractList = contractCService.findList(contract);
		model.addAttribute("contractList", contractList);
						
		//剔除不属于报检的货物
		if(inspectionC!=null&&inspectionC.getId()!=null&&!inspectionC.getId().isEmpty()){
			inspectionC.getInspectionProductCList().removeIf(list->list.getInspectionCId()==null||!list.getTabletype().equals("报检管理")
					||!list.getInspectionCId().getId().equals(inspectionC.getId()));			
		}
		
		model.addAttribute("inspectionC", inspectionC);
		return "logistics/inspection/inspectionCForm";
	}
	
	/**
	 * 查看审批表单
	 */
	@RequiresPermissions("inspection:inspectionC:view")
	@RequestMapping(value = "checkForm")
	public String checkForm(InspectionC inspectionC, Model model) {
		//剔除不属于报检的货物
		if(inspectionC!=null&&inspectionC.getId()!=null&&!inspectionC.getId().isEmpty()){
			inspectionC.getInspectionProductCList().removeIf(list->list.getInspectionCId()==null||!list.getTabletype().equals("报检管理")
					||!list.getInspectionCId().getId().equals(inspectionC.getId()));			
		}
				
		model.addAttribute("inspectionC", inspectionC);
		return "logistics/inspectionCheck/inspectionCForm";
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
	public List<InspectionProductC> fullProduct(ConsignC consignC, Model model, @Param("contractId")String contractId){
		if(contractId==null||contractId.isEmpty())
			return null;
		
		ContractC contract = new ContractC();
		contract.setId(contractId);
		ContractC contractC = contractCService.get(contract);
		List<InspectionProductC> products = new ArrayList<InspectionProductC>();
		
		//剔除非销售管理的货物
		if(contractC!=null&&contractC.getId()!=null&&!contractC.getId().isEmpty()){
			contractC.getSaleProductCList().removeIf(list->list.getTabletype()==null||!list.getTabletype().equals("销售合同")
					||!list.getContractCId().getId().equals(contractC.getId()));			
		}
		
		for(SaleProductC p : contractC.getSaleProductCList()){
			InspectionProductC product = new InspectionProductC();
			product.setInspectionCId(null);
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
	 * 保存报检
	 */
	@RequiresPermissions("inspection:inspectionC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated InspectionC inspectionC) {
		if(inspectionC.getStatu().equals("0")||inspectionC.getStatu().equals("1")){
			inspectionC.setCheckBy("");
			inspectionC.setCheckRemarks("");
			inspectionC.setCheckTime(new Date(0));
			inspectionCService.save(inspectionC);
			return renderResult(Global.TRUE, text("保存报检成功！"));
		}else{
			return renderResult(Global.FALSE, text("申请请选择草稿或待审！"));	
		}
	}
	
	/**
	 * 保存审批
	 */
	@RequiresPermissions("inspection:inspectionC:edit")
	@PostMapping(value = "checkSave")
	@ResponseBody
	public String checkSave(@Validated InspectionC inspectionC) {
		if(inspectionC.getStatu().equals("3")||inspectionC.getStatu().equals("2")){
			User user = UserUtils.getUser();
			inspectionC.setCheckBy(user.getUserName());
			inspectionC.setCheckTime(new Date());
			inspectionCService.save(inspectionC);
			
			//改变合同状态
			if(inspectionC.getStatu().equals("2")){
				ContractC contract = contractCService.get(inspectionC.getContractCode());
				if(contract!=null&&contract.getId()!=null){
					contract.setStatu("7");
					contractCService.save(contract);					
				}
			}
			
			return renderResult(Global.TRUE, text("保存审批成功！"));
		}else{
			return renderResult(Global.FALSE, text("审批请选择通过或驳回！"));	
		}
	}
	
	/**
	 * 停用报检
	 */
	@RequiresPermissions("inspection:inspectionC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(InspectionC inspectionC) {
		inspectionC.setStatus(InspectionC.STATUS_DISABLE);
		inspectionCService.updateStatus(inspectionC);
		return renderResult(Global.TRUE, text("停用报检成功"));
	}
	
	/**
	 * 启用报检
	 */
	@RequiresPermissions("inspection:inspectionC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(InspectionC inspectionC) {
		inspectionC.setStatus(InspectionC.STATUS_NORMAL);
		inspectionCService.updateStatus(inspectionC);
		return renderResult(Global.TRUE, text("启用报检成功"));
	}
	
	/**
	 * 删除报检
	 */
	@RequiresPermissions("inspection:inspectionC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(InspectionC inspectionC) {
		inspectionCService.delete(inspectionC);
		return renderResult(Global.TRUE, text("删除报检成功！"));
	}
	
}