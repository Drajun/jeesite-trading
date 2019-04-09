/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.customs.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 报关委托Entity
 * @author longlou.d@foxmail.com
 * @version 2019-04-09
 */
@Table(name="customs_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="entrust_code", attrName="entrustCode", label="委托编号"),
		@Column(name="entrust_time", attrName="entrustTime", label="委托时间"),
		@Column(name="operator", attrName="operator", label="经办人"),
		@Column(name="operator_idn", attrName="operatorIdn", label="经办人身份证", isQuery=false),
		@Column(name="is_scottare", attrName="isScottare", label="是否代缴税款", isQuery=false),
		@Column(name="is_drawback", attrName="isDrawback", label="是否退税", isQuery=false),
		@Column(name="transportation_name", attrName="transportationName", label="船名航次", queryType=QueryType.LIKE),
		@Column(name="tax_num", attrName="taxNum", label="税务登记号", isQuery=false),
		@Column(name="contract_code", attrName="contractCode", label="合同编号", queryType=QueryType.LIKE),
		@Column(name="accessory_doc", attrName="accessoryDoc", label="随附单据", isQuery=false),
		@Column(name="agency_company", attrName="agencyCompany", label="代理单位", queryType=QueryType.LIKE),
		@Column(name="agency_num", attrName="agencyNum", label="代理单位编号", queryType=QueryType.LIKE),
		@Column(name="agency_address", attrName="agencyAddress", label="代理单位地址", isQuery=false),
		@Column(name="telephone", attrName="telephone", label="联系电话", queryType=QueryType.LIKE),
		@Column(name="entrust_cost", attrName="entrustCost", label="委托费用", queryType=QueryType.GTE),
		@Column(name="agency_name", attrName="agencyName", label="代理人姓名", queryType=QueryType.LIKE),
		@Column(name="agency_idn", attrName="agencyIdn", label="代理人身份证", isQuery=false),
		@Column(name="customs_num", attrName="customsNum", label="报关单号", queryType=QueryType.LIKE),
		@Column(name="customs_time", attrName="customsTime", label="报关时间"),
		@Column(name="allow_date", attrName="allowDate", label="放行时间"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="statu", attrName="statu", label="申请状态"),
		@Column(name="check_time", attrName="checkTime", label="审批时间", isQuery=false),
		@Column(name="check_by", attrName="checkBy", label="审批人", isQuery=false),
		@Column(name="check_remarks", attrName="checkRemarks", label="审批备注", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class CustomsC extends DataEntity<CustomsC> {
	
	private static final long serialVersionUID = 1L;
	private String entrustCode;		//委托编号
	private Date entrustTime;		// 委托时间
	private String operator;		// 经办人
	private String operatorIdn;		// 经办人身份证
	private String isScottare;		// 是否代缴税款
	private String isDrawback;		// 是否退税
	private String transportationName;		// 船名航次
	private String taxNum;		// 税务登记号
	private String contractCode;		// 合同编号
	private String accessoryDoc;		// 随附单据
	private String agencyCompany;		// 代理单位
	private String agencyNum;		// 代理单位编号
	private String agencyAddress;		// 代理单位地址
	private String telephone;		// 联系电话
	private Double entrustCost;		// 委托费用
	private String agencyName;		// 代理人姓名
	private String agencyIdn;		// 代理人身份证
	private String customsNum;		// 报关单号
	private Date customsTime;		// 报关时间
	private Date allowDate;		// 放行时间
	private String statu;		// 申请状态
	private Date checkTime;		// 审批时间
	private String checkBy;		// 审批人
	private String checkRemarks;		// 审批备注
	
	public CustomsC() {
		this(null);
	}

	public CustomsC(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="委托编号长度不能超过 64 个字符")
	public String getEntrustCode() {
		return entrustCode;
	}

	public void setEntrustCode(String entrustCode) {
		this.entrustCode = entrustCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="委托时间不能为空")
	public Date getEntrustTime() {
		return entrustTime;
	}

	public void setEntrustTime(Date entrustTime) {
		this.entrustTime = entrustTime;
	}
	
	@NotBlank(message="经办人不能为空")
	@Length(min=0, max=64, message="经办人长度不能超过 64 个字符")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@NotBlank(message="经办人身份证不能为空")
	@Length(min=0, max=64, message="经办人身份证长度不能超过 64 个字符")
	public String getOperatorIdn() {
		return operatorIdn;
	}

	public void setOperatorIdn(String operatorIdn) {
		this.operatorIdn = operatorIdn;
	}
	
	@NotBlank(message="是否代缴税款不能为空")
	@Length(min=0, max=1, message="是否代缴税款长度不能超过 1 个字符")
	public String getIsScottare() {
		return isScottare;
	}

	public void setIsScottare(String isScottare) {
		this.isScottare = isScottare;
	}
	
	@NotBlank(message="是否退税不能为空")
	@Length(min=0, max=1, message="是否退税长度不能超过 1 个字符")
	public String getIsDrawback() {
		return isDrawback;
	}

	public void setIsDrawback(String isDrawback) {
		this.isDrawback = isDrawback;
	}
	
	@NotBlank(message="船名航次不能为空")
	@Length(min=0, max=100, message="船名航次长度不能超过 100 个字符")
	public String getTransportationName() {
		return transportationName;
	}

	public void setTransportationName(String transportationName) {
		this.transportationName = transportationName;
	}
	
	public String getTaxNum() {
		return taxNum;
	}

	public void setTaxNum(String taxNum) {
		this.taxNum = taxNum;
	}
	
	@Length(min=0, max=64, message="合同编号长度不能超过 64 个字符")
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	@NotBlank(message="随附单据不能为空")
	@Length(min=0, max=64, message="随附单据长度不能超过 64 个字符")
	public String getAccessoryDoc() {
		return accessoryDoc;
	}

	public void setAccessoryDoc(String accessoryDoc) {
		this.accessoryDoc = accessoryDoc;
	}
	
	@NotBlank(message="代理单位不能为空")
	@Length(min=0, max=64, message="代理单位长度不能超过 64 个字符")
	public String getAgencyCompany() {
		return agencyCompany;
	}

	public void setAgencyCompany(String agencyCompany) {
		this.agencyCompany = agencyCompany;
	}
	
	@NotBlank(message="代理单位编号不能为空")
	@Length(min=0, max=64, message="代理单位编号长度不能超过 64 个字符")
	public String getAgencyNum() {
		return agencyNum;
	}

	public void setAgencyNum(String agencyNum) {
		this.agencyNum = agencyNum;
	}
	
	@NotBlank(message="代理单位地址不能为空")
	@Length(min=0, max=200, message="代理单位地址长度不能超过 200 个字符")
	public String getAgencyAddress() {
		return agencyAddress;
	}

	public void setAgencyAddress(String agencyAddress) {
		this.agencyAddress = agencyAddress;
	}
	
	@NotBlank(message="联系电话不能为空")
	@Length(min=0, max=64, message="联系电话长度不能超过 64 个字符")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@NotNull(message="委托费用不能为空")
	public Double getEntrustCost() {
		return entrustCost;
	}

	public void setEntrustCost(Double entrustCost) {
		this.entrustCost = entrustCost;
	}
	
	@Length(min=0, max=64, message="代理人姓名长度不能超过 64 个字符")
	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	
	@Length(min=0, max=64, message="代理人身份证长度不能超过 64 个字符")
	public String getAgencyIdn() {
		return agencyIdn;
	}

	public void setAgencyIdn(String agencyIdn) {
		this.agencyIdn = agencyIdn;
	}
	
	@Length(min=0, max=64, message="报关单号长度不能超过 64 个字符")
	public String getCustomsNum() {
		return customsNum;
	}

	public void setCustomsNum(String customsNum) {
		this.customsNum = customsNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCustomsTime() {
		return customsTime;
	}

	public void setCustomsTime(Date customsTime) {
		this.customsTime = customsTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAllowDate() {
		return allowDate;
	}

	public void setAllowDate(Date allowDate) {
		this.allowDate = allowDate;
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