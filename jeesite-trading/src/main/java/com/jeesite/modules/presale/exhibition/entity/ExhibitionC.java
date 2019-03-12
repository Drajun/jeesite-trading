/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.presale.exhibition.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
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
 * 展会管理Entity
 * @author longlou.d@foxmail,com
 * @version 2019-03-11
 */
@Table(name="exhibition_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="展会名称", queryType=QueryType.LIKE),
		@Column(name="start_time", attrName="startTime", label="开始时间", queryType=QueryType.LIKE),
		@Column(name="end_time", attrName="endTime", label="结束时间", queryType=QueryType.LIKE),
		@Column(name="district", attrName="district", label="地区"),
		@Column(name="address", attrName="address", label="详细地址", isQuery=false),
		@Column(name="budget", attrName="budget", label="预算", isQuery=false),
		@Column(name="check_by", attrName="checkBy", label="审批人", isQuery=false),
		@Column(name="check_time", attrName="checkTime", label="审批时间", isQuery=false),
		@Column(name="check_remarks", attrName="checkRemarks", label="审批备注", isQuery=false),
		@Column(includeEntity=DataEntity.class),
		@Column(name="statu", attrName="statu", label="展会申请状态", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class ExhibitionC extends DataEntity<ExhibitionC> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 展会名称
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String district;		// 地区
	private String address;		// 详细地址
	private Double budget;		// 预算
	private String checkBy;		// 审批人
	private Date checkTime;		// 审批时间
	private String checkRemarks;		// 审批备注
	private String statu;		// 展会申请状态
	
	public ExhibitionC() {
		this(null);
	}

	public ExhibitionC(String id){
		super(id);
	}
	
	@NotBlank(message="展会名称不能为空")
	@Length(min=0, max=64, message="展会名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="开始时间不能为空")
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
	
	@NotBlank(message="地区不能为空")
	@Length(min=0, max=64, message="地区长度不能超过 64 个字符")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	@NotBlank(message="详细地址不能为空")
	@Length(min=0, max=200, message="详细地址长度不能超过 200 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}
	
	public String getCheckBy() {
		return checkBy;
	}

	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	
	public String getCheckRemarks() {
		return checkRemarks;
	}

	public void setCheckRemarks(String checkRemarks) {
		this.checkRemarks = checkRemarks;
	}
	
	@NotBlank(message="展会申请状态不能为空")
	@Length(min=0, max=1, message="展会申请状态长度不能超过 1 个字符")
	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}
	
}