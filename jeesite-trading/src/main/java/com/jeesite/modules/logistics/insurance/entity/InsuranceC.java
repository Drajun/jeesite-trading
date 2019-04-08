/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.insurance.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 保险管理Entity
 * @author longlou.d@foxmail.com
 * @version 2019-04-08
 */
@Table(name="insurance_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="insurance_code", attrName="insuranceCode", label="保险单号", queryType=QueryType.LIKE),
		@Column(name="insurance_company", attrName="insuranceCompany", label="保险公司", queryType=QueryType.LIKE),
		@Column(name="insurance_type", attrName="insuranceType", label="保险类型", isQuery=false),
		@Column(name="start_time", attrName="startTime", label="起始时间", queryType=QueryType.GTE),
		@Column(name="end_time", attrName="endTime", label="结束时间"),
		@Column(name="contract_code", attrName="contractCode", label="销售合同"),
		@Column(name="transport_ways", attrName="transportWays", label="运输方式", isQuery=false),
		@Column(name="send_time", attrName="sendTime", label="起运时间", isQuery=false),
		@Column(name="lading_bill", attrName="ladingBill", label="提单号", isQuery=false),
		@Column(name="start_addr", attrName="startAddr", label="起始地", queryType=QueryType.LIKE),
		@Column(name="end_addr", attrName="endAddr", label="目的地", queryType=QueryType.LIKE),
		@Column(name="main_insurance", attrName="mainInsurance", label="主要险别"),
		@Column(name="extra_insurance", attrName="extraInsurance", label="附加险"),
		@Column(name="insurance_rate", attrName="insuranceRate", label="保险费率", comment="保险费率(%)", queryType=QueryType.GTE),
		@Column(name="insurance_cost", attrName="insuranceCost", label="保险总费", queryType=QueryType.GTE),
		@Column(name="operator", attrName="operator", label="经办人", isQuery=false),
		@Column(name="operator_phone", attrName="operatorPhone", label="经办人手机", isQuery=false),
		@Column(includeEntity=DataEntity.class),
		@Column(name="statu", attrName="statu", label="申请状态"),
		@Column(name="check_time", attrName="checkTime", label="审批时间"),
		@Column(name="check_by", attrName="checkBy", label="审批人"),
		@Column(name="check_remarks", attrName="checkRemarks", label="审批备注", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class InsuranceC extends DataEntity<InsuranceC> {
	
	private static final long serialVersionUID = 1L;
	private String insuranceCode;		// 保险单号
	private String insuranceCompany;		// 保险公司
	private String insuranceType;		// 保险类型
	private Date startTime;		// 起始时间
	private Date endTime;		// 结束时间
	private String contractCode;		// 销售合同
	private String transportWays;		// 运输方式
	private Date sendTime;		// 起运时间
	private String ladingBill;		// 提单号
	private String startAddr;		// 起始地
	private String endAddr;		// 目的地
	private String mainInsurance;		// 主要险别
	private String extraInsurance;		// 附加险
	private Double insuranceRate;		// 保险费率(%)
	private Double insuranceCost;		// 保险总费
	private String operator;		// 经办人
	private String operatorPhone;		// 经办人手机
	private String statu;		// 申请状态
	private Date checkTime;		// 审批时间
	private String checkBy;		// 审批人
	private String checkRemarks;		// 审批备注
	
	public InsuranceC() {
		this(null);
	}

	public InsuranceC(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="保险单号长度不能超过 64 个字符")
	public String getInsuranceCode() {
		return insuranceCode;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}
	
	@NotBlank(message="保险公司不能为空")
	@Length(min=0, max=64, message="保险公司长度不能超过 64 个字符")
	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	
	@NotBlank(message="保险类型不能为空")
	@Length(min=0, max=1, message="保险类型长度不能超过 1 个字符")
	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="起始时间不能为空")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="结束时间不能为空")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=64, message="销售合同长度不能超过 64 个字符")
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
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
	@NotNull(message="起运时间不能为空")
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	@NotBlank(message="提单号不能为空")
	@Length(min=0, max=64, message="提单号长度不能超过 64 个字符")
	public String getLadingBill() {
		return ladingBill;
	}

	public void setLadingBill(String ladingBill) {
		this.ladingBill = ladingBill;
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
	
	@NotBlank(message="主要险别不能为空")
	@Length(min=0, max=64, message="主要险别长度不能超过 64 个字符")
	public String getMainInsurance() {
		return mainInsurance;
	}

	public void setMainInsurance(String mainInsurance) {
		this.mainInsurance = mainInsurance;
	}
	
	@Length(min=0, max=64, message="附加险长度不能超过 64 个字符")
	public String getExtraInsurance() {
		return extraInsurance;
	}

	public void setExtraInsurance(String extraInsurance) {
		this.extraInsurance = extraInsurance;
	}
	
	@NotNull(message="保险费率不能为空")
	public Double getInsuranceRate() {
		return insuranceRate;
	}

	public void setInsuranceRate(Double insuranceRate) {
		this.insuranceRate = insuranceRate;
	}
	
	@NotNull(message="保险总费不能为空")
	public Double getInsuranceCost() {
		return insuranceCost;
	}

	public void setInsuranceCost(Double insuranceCost) {
		this.insuranceCost = insuranceCost;
	}
	
	@Length(min=0, max=64, message="经办人长度不能超过 64 个字符")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@Length(min=0, max=64, message="经办人手机长度不能超过 64 个字符")
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
	
}