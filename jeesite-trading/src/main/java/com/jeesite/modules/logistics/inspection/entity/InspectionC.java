/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.inspection.entity;

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
 * 报检管理Entity
 * @author longlou.d@foxmail.com
 * @version 2019-04-04
 */
@Table(name="inspection_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="inspection_code", attrName="inspectionCode", label="报检编号"),
		@Column(name="inspection_time", attrName="inspectionTime", label="报检时间"),
		@Column(name="receiver", attrName="receiver", label="收货人", isQuery=false),
		@Column(name="transport_ways", attrName="transportWays", label="运输方式", isQuery=false),
		@Column(name="trading_ways", attrName="tradingWays", label="贸易方式", isQuery=false),
		@Column(name="store_addr", attrName="storeAddr", label="存货地址", isQuery=false),
		@Column(name="contract_code", attrName="contractCode", label="合同号", queryType=QueryType.LIKE),
		@Column(name="letter_credit", attrName="letterCredit", label="信用证", isQuery=false),
		@Column(name="using", attrName="using", label="用途", isQuery=false),
		@Column(name="send_time", attrName="sendTime", label="发货日期", isQuery=false),
		@Column(name="district", attrName="district", label="输往国家"),
		@Column(name="license", attrName="license", label="许可证", isQuery=false),
		@Column(name="start_addr", attrName="startAddr", label="起始地", queryType=QueryType.LIKE),
		@Column(name="end_addr", attrName="endAddr", label="目的地", queryType=QueryType.LIKE),
		@Column(name="container_spec", attrName="containerSpec", label="集装箱规格"),
		@Column(name="accessory_doc", attrName="accessoryDoc", label="随附单据", isQuery=false),
		@Column(name="inspection_cost", attrName="inspectionCost", label="报检费用", queryType=QueryType.GTE),
		@Column(name="operator", attrName="operator", label="操作员", isQuery=false),
		@Column(name="take_time", attrName="takeTime", label="领证时间"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="statu", attrName="statu", label="申请状态", isQuery=false),
		@Column(name="check_time", attrName="checkTime", label="审批时间"),
		@Column(name="check_by", attrName="checkBy", label="审批人"),
		@Column(name="check_remarks", attrName="checkRemarks", label="审批备注", queryType=QueryType.LIKE),
	}, orderBy="a.update_date DESC"
)
public class InspectionC extends DataEntity<InspectionC> {
	
	private static final long serialVersionUID = 1L;
	private String inspectionCode;		// 报检编号
	private Date inspectionTime;		// 报检时间
	private String receiver;		// 收货人
	private String transportWays;		// 运输方式
	private String tradingWays;		// 贸易方式
	private String storeAddr;		// 存货地址
	private String contractCode;		// 合同号
	private String letterCredit;		// 信用证
	private String using;		// 用途
	private Date sendTime;		// 发货日期
	private String district;		// 输往国家
	private String license;		// 许可证
	private String startAddr;		// 起始地
	private String endAddr;		// 目的地
	private String containerSpec;		// 集装箱规格
	private String accessoryDoc;		// 随附单据
	private Double inspectionCost;		// 报检费用
	private String operator;		// 操作员
	private Date takeTime;		// 领证时间
	private String statu;		// 申请状态
	private Date checkTime;		// 审批时间
	private String checkBy;		// 审批人
	private String checkRemarks;		// 审批备注
	private List<InspectionProductC> inspectionProductCList = ListUtils.newArrayList();		// 子表列表
	
	public InspectionC() {
		this(null);
	}

	public InspectionC(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="报检编号长度不能超过 64 个字符")
	public String getInspectionCode() {
		return inspectionCode;
	}

	public void setInspectionCode(String inspectionCode) {
		this.inspectionCode = inspectionCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="报检时间不能为空")
	public Date getInspectionTime() {
		return inspectionTime;
	}

	public void setInspectionTime(Date inspectionTime) {
		this.inspectionTime = inspectionTime;
	}
	
	@NotBlank(message="收货人不能为空")
	@Length(min=0, max=200, message="收货人长度不能超过 200 个字符")
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	@NotBlank(message="运输方式不能为空")
	@Length(min=0, max=64, message="运输方式长度不能超过 64 个字符")
	public String getTransportWays() {
		return transportWays;
	}

	public void setTransportWays(String transportWays) {
		this.transportWays = transportWays;
	}
	
	@NotBlank(message="贸易方式不能为空")
	@Length(min=0, max=1, message="贸易方式长度不能超过 1 个字符")
	public String getTradingWays() {
		return tradingWays;
	}

	public void setTradingWays(String tradingWays) {
		this.tradingWays = tradingWays;
	}
	
	@NotBlank(message="存货地址不能为空")
	@Length(min=0, max=100, message="存货地址长度不能超过 100 个字符")
	public String getStoreAddr() {
		return storeAddr;
	}

	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}
	
	@Length(min=0, max=64, message="合同号长度不能超过 64 个字符")
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	@Length(min=0, max=64, message="信用证长度不能超过 64 个字符")
	public String getLetterCredit() {
		return letterCredit;
	}

	public void setLetterCredit(String letterCredit) {
		this.letterCredit = letterCredit;
	}
	
	@NotBlank(message="用途不能为空")
	@Length(min=0, max=100, message="用途长度不能超过 100 个字符")
	public String getUsing() {
		return using;
	}

	public void setUsing(String using) {
		this.using = using;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="发货日期不能为空")
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	@NotBlank(message="输往国家不能为空")
	@Length(min=0, max=64, message="输往国家长度不能超过 64 个字符")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	@NotBlank(message="许可证不能为空")
	@Length(min=0, max=64, message="许可证长度不能超过 64 个字符")
	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
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
	
	@Length(min=0, max=100, message="集装箱规格长度不能超过 100 个字符")
	public String getContainerSpec() {
		return containerSpec;
	}

	public void setContainerSpec(String containerSpec) {
		this.containerSpec = containerSpec;
	}
	
	@Length(min=0, max=64, message="随附单据长度不能超过 64 个字符")
	public String getAccessoryDoc() {
		return accessoryDoc;
	}

	public void setAccessoryDoc(String accessoryDoc) {
		this.accessoryDoc = accessoryDoc;
	}
	
	@NotNull(message="报检费用不能为空")
	public Double getInspectionCost() {
		return inspectionCost;
	}

	public void setInspectionCost(Double inspectionCost) {
		this.inspectionCost = inspectionCost;
	}
	
	@Length(min=0, max=64, message="操作员长度不能超过 64 个字符")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
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
	
	public List<InspectionProductC> getInspectionProductCList() {
		return inspectionProductCList;
	}

	public void setInspectionProductCList(List<InspectionProductC> inspectionProductCList) {
		this.inspectionProductCList = inspectionProductCList;
	}
	
}