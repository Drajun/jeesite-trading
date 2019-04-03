/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.purandsell.sales.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
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
 * 销售合同Entity
 * @author longlou.d@foxmail.com
 * @version 2019-03-21
 */
@Table(name="contract_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="contract_code", attrName="contractCode", label="合同编码", queryType=QueryType.LIKE),
		@Column(name="customers_c_id", attrName="customersCId", label="客户", isQuery=false),
		@Column(name="sign_time", attrName="signTime", label="签约时间"),
		@Column(name="sign_addr", attrName="signAddr", label="签约地址", queryType=QueryType.LIKE),
		@Column(name="price_term", attrName="priceTerm", label="价格条款", isQuery=false),
		@Column(name="breach_contract", attrName="breachContract", label="违约条款"),
		@Column(name="total_amount", attrName="totalAmount", label="总金额", queryType=QueryType.GTE),
		@Column(name="shipment_time", attrName="shipmentTime", label="装运时间"),
		@Column(name="transport_way", attrName="transportWay", label="运输方式", isQuery=false),
		@Column(name="start_addr", attrName="startAddr", label="起始地", queryType=QueryType.LIKE),
		@Column(name="end_addr", attrName="endAddr", label="目的地", queryType=QueryType.LIKE),
		@Column(name="payment_term", attrName="paymentTerm", label="付款方式"),
		@Column(name="payment_time", attrName="paymentTime", label="付款截止"),
		@Column(name="insurance_term", attrName="insuranceTerm", label="保险条款", isQuery=false),
		@Column(name="statu", attrName="statu", label="销售状态", isQuery=false),
		@Column(includeEntity=DataEntity.class),
		@Column(name="check_time", attrName="checkTime", label="审批时间"),
		@Column(name="check_by", attrName="checkBy", label="审批人", isQuery=false),
		@Column(name="check_remarks", attrName="checkRemarks", label="审批备注", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class ContractC extends DataEntity<ContractC> {
	
	private static final long serialVersionUID = 1L;
	private String contractCode;		// 合同编码
	private String customersCId;		// 客户
	private Date signTime;		// 签约时间
	private String signAddr;		// 签约地址
	private String priceTerm;		// 价格条款
	private String breachContract;		// 违约条款
	private Double totalAmount;		// 总金额
	private Date shipmentTime;		// 装运时间
	private String transportWay;		// 运输方式
	private String startAddr;		// 起始地
	private String endAddr;		// 目的地
	private String paymentTerm;		// 付款方式
	private Date paymentTime;		// 付款截止
	private String insuranceTerm;		// 保险条款
	private String statu;		// 销售状态
	private Date checkTime;		// 审批时间
	private String checkBy;		// 审批人
	private String checkRemarks;		// 审批备注
	private List<SaleProductC> saleProductCList = ListUtils.newArrayList();		// 子表列表
	
	public ContractC() {
		this(null);
	}

	public ContractC(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="合同编码长度不能超过 64 个字符")
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
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
	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	
	@Length(min=0, max=200, message="签约地址长度不能超过 200 个字符")
	public String getSignAddr() {
		return signAddr;
	}

	public void setSignAddr(String signAddr) {
		this.signAddr = signAddr;
	}
	
	@NotBlank(message="价格条款不能为空")
	@Length(min=0, max=1, message="价格条款长度不能超过 1 个字符")
	public String getPriceTerm() {
		return priceTerm;
	}

	public void setPriceTerm(String priceTerm) {
		this.priceTerm = priceTerm;
	}
	
	@Length(min=0, max=200, message="违约条款长度不能超过 200 个字符")
	public String getBreachContract() {
		return breachContract;
	}

	public void setBreachContract(String breachContract) {
		this.breachContract = breachContract;
	}
	
	@NotNull(message="总金额不能为空")
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="装运时间不能为空")
	public Date getShipmentTime() {
		return shipmentTime;
	}

	public void setShipmentTime(Date shipmentTime) {
		this.shipmentTime = shipmentTime;
	}
	
	@NotBlank(message="运输方式不能为空")
	@Length(min=0, max=64, message="运输方式长度不能超过 64 个字符")
	public String getTransportWay() {
		return transportWay;
	}

	public void setTransportWay(String transportWay) {
		this.transportWay = transportWay;
	}
	
	@NotBlank(message="起始地不能为空")
	@Length(min=0, max=64, message="起始地长度不能超过 64 个字符")
	public String getStartAddr() {
		return startAddr;
	}

	public void setStartAddr(String startAddr) {
		this.startAddr = startAddr;
	}
	
	@NotBlank(message="目的地不能为空")
	@Length(min=0, max=64, message="目的地长度不能超过 64 个字符")
	public String getEndAddr() {
		return endAddr;
	}

	public void setEndAddr(String endAddr) {
		this.endAddr = endAddr;
	}
	
	@NotBlank(message="付款方式不能为空")
	@Length(min=0, max=10, message="付款方式长度不能超过 10 个字符")
	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="付款截止不能为空")
	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	
	@NotBlank(message="保险条款不能为空")
	@Length(min=0, max=200, message="保险条款长度不能超过 200 个字符")
	public String getInsuranceTerm() {
		return insuranceTerm;
	}

	public void setInsuranceTerm(String insuranceTerm) {
		this.insuranceTerm = insuranceTerm;
	}
	
	@NotBlank(message="销售状态不能为空")
	@Length(min=0, max=1, message="销售状态长度不能超过 1 个字符")
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
	
	public List<SaleProductC> getSaleProductCList() {
		return saleProductCList;
	}

	public void setSaleProductCList(List<SaleProductC> saleProductCList) {
		this.saleProductCList = saleProductCList;
	}
	
}