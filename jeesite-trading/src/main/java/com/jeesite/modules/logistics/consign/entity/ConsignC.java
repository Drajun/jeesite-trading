/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.consign.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 托运管理Entity
 * @author longlou.d@foxmail.com
 * @version 2019-04-03
 */
@Table(name="consign_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="consign_code", attrName="consignCode", label="代运编号"),
		@Column(name="apply_time", attrName="applyTime", label="申请日期"),
		@Column(name="start_addr", attrName="startAddr", label="起运地", queryType=QueryType.LIKE),
		@Column(name="end_addr", attrName="endAddr", label="目的地", queryType=QueryType.LIKE),
		@Column(name="contract_code", attrName="contractCode", label="合同编号", queryType=QueryType.LIKE),
		@Column(name="consign_company", attrName="consignCompany", label="承运公司", queryType=QueryType.LIKE),
		@Column(name="receiver", attrName="receiver", label="收货人", queryType=QueryType.LIKE),
		@Column(name="letter_credit", attrName="letterCredit", label="信用证号", isQuery=false),
		@Column(name="notify", attrName="notify", label="提醒人", isQuery=false),
		@Column(name="shipment_period", attrName="shipmentPeriod", label="装运期", isQuery=false),
		@Column(name="istransshipment", attrName="istransshipment", label="可否转运", isQuery=false),
		@Column(name="ispartial", attrName="ispartial", label="可否分批", isQuery=false),
		@Column(name="transportation_name", attrName="transportationName", label="运输工具名称", queryType=QueryType.LIKE),
		@Column(name="store_addr", attrName="storeAddr", label="存货地点", isQuery=false),
		@Column(name="lading_bill", attrName="ladingBill", label="提单号", isQuery=false),
		@Column(name="carriage_cost", attrName="carriageCost", label="运费", queryType=QueryType.GTE),
		@Column(name="total_amount", attrName="totalAmount", label="总金额", queryType=QueryType.GTE),
		@Column(name="payment_term", attrName="paymentTerm", label="付款方式", isQuery=false),
		@Column(name="payment_time", attrName="paymentTime", label="付款时间", isQuery=false),
		@Column(name="operator", attrName="operator", label="负责人", isQuery=false),
		@Column(name="operator_phone", attrName="operatorPhone", label="负责人电话", isQuery=false),
		@Column(includeEntity=DataEntity.class),
		@Column(name="statu", attrName="statu", label="申请状态"),
		@Column(name="check_time", attrName="checkTime", label="审批时间"),
		@Column(name="check_by", attrName="checkBy", label="审批人"),
		@Column(name="check_remarks", attrName="checkRemarks", label="审批备注", queryType=QueryType.LIKE),
	}, orderBy="a.update_date DESC"
)
public class ConsignC extends DataEntity<ConsignC> {
	
	private static final long serialVersionUID = 1L;
	private String consignCode;		// 代运编号
	private Date applyTime;		// 申请日期
	private String startAddr;		// 起运地
	private String endAddr;		// 目的地
	private String contractCode;		// 合同编号
	private String consignCompany;		// 承运公司
	private String receiver;		// 收货人
	private String letterCredit;		// 信用证号
	private String notify;		// 提醒人
	private Date shipmentPeriod;		// 装运期
	private String istransshipment;		// 可否转运
	private String ispartial;		// 可否分批
	private String transportationName;		// 运输工具名称
	private String storeAddr;		// 存货地点
	private String ladingBill;		// 提单号
	private Integer carriageCost;		// 运费
	private Integer totalAmount;		// 总金额
	private String paymentTerm;		// 付款方式
	private Date paymentTime;		// 付款时间
	private String operator;		// 负责人
	private String operatorPhone;		// 负责人电话
	private String statu;		// 申请状态
	private Date checkTime;		// 审批时间
	private String checkBy;		// 审批人
	private String checkRemarks;		// 审批备注
	private List<ConsignProductC> consignProductCList = ListUtils.newArrayList();		// 子表列表
	
	public ConsignC() {
		this(null);
	}

	public ConsignC(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="代运编号长度不能超过 64 个字符")
	public String getConsignCode() {
		return consignCode;
	}

	public void setConsignCode(String consignCode) {
		this.consignCode = consignCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="申请日期不能为空")
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	@NotBlank(message="起运地不能为空")
	@Length(min=0, max=64, message="起运地长度不能超过 64 个字符")
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
	
	@Length(min=0, max=64, message="合同编号长度不能超过 64 个字符")
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	@NotBlank(message="承运公司不能为空")
	@Length(min=0, max=64, message="承运公司长度不能超过 64 个字符")
	public String getConsignCompany() {
		return consignCompany;
	}

	public void setConsignCompany(String consignCompany) {
		this.consignCompany = consignCompany;
	}
	
	@NotBlank(message="收货人不能为空")
	@Length(min=0, max=200, message="收货人长度不能超过 200 个字符")
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	@Length(min=0, max=64, message="信用证号长度不能超过 64 个字符")
	public String getLetterCredit() {
		return letterCredit;
	}

	public void setLetterCredit(String letterCredit) {
		this.letterCredit = letterCredit;
	}
	
	@NotBlank(message="提醒人不能为空")
	@Length(min=0, max=200, message="提醒人长度不能超过 200 个字符")
	public String getNotify() {
		return notify;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="装运期不能为空")
	public Date getShipmentPeriod() {
		return shipmentPeriod;
	}

	public void setShipmentPeriod(Date shipmentPeriod) {
		this.shipmentPeriod = shipmentPeriod;
	}
	
	@NotBlank(message="可否转运不能为空")
	@Length(min=0, max=1, message="可否转运长度不能超过 1 个字符")
	public String getIstransshipment() {
		return istransshipment;
	}

	public void setIstransshipment(String istransshipment) {
		this.istransshipment = istransshipment;
	}
	
	@NotBlank(message="可否分批不能为空")
	@Length(min=0, max=1, message="可否分批长度不能超过 1 个字符")
	public String getIspartial() {
		return ispartial;
	}

	public void setIspartial(String ispartial) {
		this.ispartial = ispartial;
	}
	
	@NotBlank(message="运输工具名称不能为空")
	@Length(min=0, max=64, message="运输工具名称长度不能超过 64 个字符")
	public String getTransportationName() {
		return transportationName;
	}

	public void setTransportationName(String transportationName) {
		this.transportationName = transportationName;
	}
	
	@NotBlank(message="存货地点不能为空")
	@Length(min=0, max=100, message="存货地点长度不能超过 100 个字符")
	public String getStoreAddr() {
		return storeAddr;
	}

	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}
	
	@Length(min=0, max=64, message="提单号长度不能超过 64 个字符")
	public String getLadingBill() {
		return ladingBill;
	}

	public void setLadingBill(String ladingBill) {
		this.ladingBill = ladingBill;
	}
	
	@NotNull(message="运费不能为空")
	public Integer getCarriageCost() {
		return carriageCost;
	}

	public void setCarriageCost(Integer carriageCost) {
		this.carriageCost = carriageCost;
	}
	
	@NotNull(message="总金额不能为空")
	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
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
	@NotNull(message="付款时间不能为空")
	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	
	@Length(min=0, max=64, message="负责人长度不能超过 64 个字符")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@Length(min=0, max=64, message="负责人电话长度不能超过 64 个字符")
	public String getOperatorPhone() {
		return operatorPhone;
	}

	public void setOperatorPhone(String operatorPhone) {
		this.operatorPhone = operatorPhone;
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
	
	public List<ConsignProductC> getConsignProductCList() {
		return consignProductCList;
	}

	public void setConsignProductCList(List<ConsignProductC> consignProductCList) {
		this.consignProductCList = consignProductCList;
	}
	
}