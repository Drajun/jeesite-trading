/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.presale.offer.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 报价管理Entity
 * @author longlou.d@foxmail.com
 * @version 2019-03-13
 */
@Table(name="offer_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="customers_c_id", attrName="customersCId", label="客户"),
		@Column(name="offer_time", attrName="offerTime", label="报价日期"),
		@Column(name="deadline", attrName="deadline", label="截止日期"),
		@Column(name="price_term", attrName="priceTerm", label="价格条款", isQuery=false),
		@Column(name="total_amount", attrName="totalAmount", label="总价", queryType=QueryType.GTE),
		@Column(name="transport_ways", attrName="transportWays", label="运输方式", isQuery=false),
		@Column(name="expect_deliver_time", attrName="expectDeliverTime", label="预计交货日期"),
		@Column(name="paymentterrms", attrName="paymentterrms", label="付款方式", isQuery=false),
		@Column(name="package_cost", attrName="packageCost", label="包装费用", isQuery=false),
		@Column(name="carriage_cost", attrName="carriageCost", label="运费", isQuery=false),
		@Column(name="inspection_cost", attrName="inspectionCost", label="商检费用", isQuery=false),
		@Column(name="customs_cost", attrName="customsCost", label="报关费用", isQuery=false),
		@Column(name="insurance_cost", attrName="insuranceCost", label="保险费用", isQuery=false),
		@Column(name="export_tax", attrName="exportTax", label="出口税率", comment="出口税率(%)", isQuery=false),
		@Column(name="return_tax", attrName="returnTax", label="退税", comment="退税(%)", isQuery=false),
		@Column(name="others_cost", attrName="othersCost", label="其他费用", isQuery=false),
		@Column(name="cust_require", attrName="custRequire", label="客户要求"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="statu", attrName="statu", label="申请状态"),
		@Column(name="check_time", attrName="checkTime", label="审批时间", isQuery=false),
		@Column(name="check_by", attrName="checkBy", label="审批人", isQuery=false),
		@Column(name="check_remarks", attrName="checkRemarks", label="审批备注", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class OfferC extends DataEntity<OfferC> {
	
	private static final long serialVersionUID = 1L;
	private String customersCId;		// 客户
	private Date offerTime;		// 报价日期
	private Date deadline;		// 截止日期
	private String priceTerm;		// 价格条款
	private Double totalAmount;		// 总价
	private String transportWays;		// 运输方式
	private Date expectDeliverTime;		// 预计交货日期
	private String paymentterrms;		// 付款方式
	private Double packageCost;		// 包装费用
	private Double carriageCost;		// 运费
	private Double inspectionCost;		// 商检费用
	private Double customsCost;		// 报关费用
	private Double insuranceCost;		// 保险费用
	private Double exportTax;		// 出口税率(%)
	private Double returnTax;		// 退税(%)
	private Double othersCost;		// 其他费用
	private String custRequire;		// 客户要求
	private String statu;		// 申请状态
	private Date checkTime;		// 审批时间
	private String checkBy;		// 审批人
	private String checkRemarks;		// 审批备注
	private List<ReferenceProductC> referenceProductCList = ListUtils.newArrayList();		// 子表列表
	
	public OfferC() {
		this(null);
	}

	public OfferC(String id){
		super(id);
	}
	
	@NotBlank(message="客户不能为空")
	@Length(min=0, max=64, message="客户长度不能超过 64 个字符")
	public String getCustomersCId() {
		return customersCId;
	}

	public void setCustomersCId(String customersCId) {
		this.customersCId = customersCId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="报价日期不能为空")
	public Date getOfferTime() {
		return offerTime;
	}

	public void setOfferTime(Date offerTime) {
		this.offerTime = offerTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="截止日期不能为空")
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	@NotBlank(message="价格条款不能为空")
	@Length(min=0, max=1, message="价格条款长度不能超过 1 个字符")
	public String getPriceTerm() {
		return priceTerm;
	}

	public void setPriceTerm(String priceTerm) {
		this.priceTerm = priceTerm;
	}
	
	@NotNull(message="总价不能为空")
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@NotBlank(message="运输方式不能为空")
	@Length(min=0, max=64, message="运输方式长度不能超过 64 个字符")
	public String getTransportWays() {
		return transportWays;
	}

	public void setTransportWays(String transportWays) {
		this.transportWays = transportWays;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpectDeliverTime() {
		return expectDeliverTime;
	}

	public void setExpectDeliverTime(Date expectDeliverTime) {
		this.expectDeliverTime = expectDeliverTime;
	}
	
	@Length(min=0, max=10, message="付款方式长度不能超过 10 个字符")
	public String getPaymentterrms() {
		return paymentterrms;
	}

	public void setPaymentterrms(String paymentterrms) {
		this.paymentterrms = paymentterrms;
	}
	
	public Double getPackageCost() {
		return packageCost;
	}

	public void setPackageCost(Double packageCost) {
		this.packageCost = packageCost;
	}
	
	public Double getCarriageCost() {
		return carriageCost;
	}

	public void setCarriageCost(Double carriageCost) {
		this.carriageCost = carriageCost;
	}
	
	public Double getInspectionCost() {
		return inspectionCost;
	}

	public void setInspectionCost(Double inspectionCost) {
		this.inspectionCost = inspectionCost;
	}
	
	public Double getCustomsCost() {
		return customsCost;
	}

	public void setCustomsCost(Double customsCost) {
		this.customsCost = customsCost;
	}
	
	public Double getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(Double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}
	
	public Double getExportTax() {
		return exportTax;
	}

	public void setExportTax(Double exportTax) {
		this.exportTax = exportTax;
	}
	
	public Double getReturnTax() {
		return returnTax;
	}

	public void setReturnTax(Double returnTax) {
		this.returnTax = returnTax;
	}
	
	public Double getOthersCost() {
		return othersCost;
	}

	public void setOthersCost(Double othersCost) {
		this.othersCost = othersCost;
	}
	
	@Length(min=0, max=300, message="客户要求长度不能超过 300 个字符")
	public String getCustRequire() {
		return custRequire;
	}

	public void setCustRequire(String custRequire) {
		this.custRequire = custRequire;
	}
	
	@NotBlank(message="申请状态不能为空")
	@Length(min=0, max=1, message="申请状态长度不能超过 1 个字符")
	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	
	@Length(min=0, max=64, message="审批人长度不能超过 64 个字符")
	public String getCheckBy() {
		return checkBy;
	}

	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}
	
	@Length(min=0, max=200, message="审批备注长度不能超过 200 个字符")
	public String getCheckRemarks() {
		return checkRemarks;
	}

	public void setCheckRemarks(String checkRemarks) {
		this.checkRemarks = checkRemarks;
	}
	
	public List<ReferenceProductC> getReferenceProductCList() {
		return referenceProductCList;
	}

	public void setReferenceProductCList(List<ReferenceProductC> referenceProductCList) {
		this.referenceProductCList = referenceProductCList;
	}
	
}