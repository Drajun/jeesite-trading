/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.purandsell.returnc.entity;

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
 * 退换管理Entity
 * @author longlou.d@foxmail.com
 * @version 2019-04-15
 */
@Table(name="return_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="return_code", attrName="returnCode", label="退换单号", queryType=QueryType.LIKE),
		@Column(name="return_type", attrName="returnType", label="退换类型"),
		@Column(name="goods_statu", attrName="goodsStatu", label="货物状态"),
		@Column(name="contract_code", attrName="contractCode", label="合同编号", queryType=QueryType.LIKE),
		@Column(name="return_date", attrName="returnDate", label="退换日期"),
		@Column(name="total_amount", attrName="totalAmount", label="退款金额", isQuery=false),
		@Column(name="return_causes", attrName="returnCauses", label="退换原因", queryType=QueryType.GTE),
		@Column(name="returner", attrName="returner", label="退换单位", isQuery=false),
		@Column(name="returner_phone", attrName="returnerPhone", label="联系电话", isQuery=false),
		@Column(name="return_district", attrName="returnDistrict", label="退换地区"),
		@Column(name="return_address", attrName="returnAddress", label="退换地址", isQuery=false),
		@Column(name="statu", attrName="statu", label="申请状态"),
		@Column(name="check_time", attrName="checkTime", label="审批时间", isQuery=false),
		@Column(name="check_by", attrName="checkBy", label="审批人", isQuery=false),
		@Column(name="check_remarks", attrName="checkRemarks", label="审批备注", isQuery=false),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class ReturnC extends DataEntity<ReturnC> {
	
	private static final long serialVersionUID = 1L;
	private String returnCode;		// 退换单号
	private String returnType;		// 退换类型
	private String goodsStatu;		// 货物状态
	private String contractCode;		// 合同编号
	private Date returnDate;		// 退换日期
	private Double totalAmount;		// 退款金额
	private String returnCauses;		// 退换原因
	private String returner;		// 退换单位
	private String returnerPhone;		// 联系电话
	private String returnDistrict;		// 退换地区
	private String returnAddress;		// 退换地址
	private String statu;		// 申请状态
	private Date checkTime;		// 审批时间
	private String checkBy;		// 审批人
	private String checkRemarks;		// 审批备注
	private List<ReturnProductC> returnProductCList = ListUtils.newArrayList();		// 子表列表
	
	public ReturnC() {
		this(null);
	}

	public ReturnC(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="退换单号长度不能超过 64 个字符")
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	@NotBlank(message="退换类型不能为空")
	@Length(min=0, max=1, message="退换类型长度不能超过 1 个字符")
	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	@NotBlank(message="货物状态不能为空")
	@Length(min=0, max=1, message="货物状态长度不能超过 1 个字符")
	public String getGoodsStatu() {
		return goodsStatu;
	}

	public void setGoodsStatu(String goodsStatu) {
		this.goodsStatu = goodsStatu;
	}
	
	@NotBlank(message="合同编号不能为空")
	@Length(min=0, max=64, message="合同编号长度不能超过 64 个字符")
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="退换日期不能为空")
	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@NotBlank(message="退换原因不能为空")
	@Length(min=0, max=200, message="退换原因长度不能超过 200 个字符")
	public String getReturnCauses() {
		return returnCauses;
	}

	public void setReturnCauses(String returnCauses) {
		this.returnCauses = returnCauses;
	}
	
	@NotBlank(message="退换单位不能为空")
	@Length(min=0, max=64, message="退换单位长度不能超过 64 个字符")
	public String getReturner() {
		return returner;
	}

	public void setReturner(String returner) {
		this.returner = returner;
	}
	
	@NotBlank(message="联系电话不能为空")
	@Length(min=0, max=64, message="联系电话长度不能超过 64 个字符")
	public String getReturnerPhone() {
		return returnerPhone;
	}

	public void setReturnerPhone(String returnerPhone) {
		this.returnerPhone = returnerPhone;
	}
	
	@NotBlank(message="退换地区不能为空")
	@Length(min=0, max=64, message="退换地区长度不能超过 64 个字符")
	public String getReturnDistrict() {
		return returnDistrict;
	}

	public void setReturnDistrict(String returnDistrict) {
		this.returnDistrict = returnDistrict;
	}
	
	@NotBlank(message="退换地址不能为空")
	@Length(min=0, max=100, message="退换地址长度不能超过 100 个字符")
	public String getReturnAddress() {
		return returnAddress;
	}

	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}
	
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
	
	public List<ReturnProductC> getReturnProductCList() {
		return returnProductCList;
	}

	public void setReturnProductCList(List<ReturnProductC> returnProductCList) {
		this.returnProductCList = returnProductCList;
	}
	
}