/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.presale.send.entity;

import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 寄样管理Entity
 * @author longlou.d@foxmail.com
 * @version 2019-03-20
 */
@Table(name="send_specimen_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="send_time", attrName="sendTime", label="寄样时间"),
		@Column(name="customers_c_id", attrName="customersCId", label="客户", isQuery=false),
		@Column(name="address", attrName="address", label="寄送地址", queryType=QueryType.LIKE),
		@Column(name="logistics_bill", attrName="logisticsBill", label="物流单号"),
		@Column(name="total_amount", attrName="totalAmount", label="总金额"),
		@Column(name="pre_arrive_time", attrName="preArriveTime", label="预计送达"),
		@Column(name="take_goods_time", attrName="takeGoodsTime", label="收货时间"),
		@Column(name="cus_comment", attrName="cusComment", label="客户评价"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="check_time", attrName="checkTime", label="审批时间"),
		@Column(name="check_by", attrName="checkBy", label="审批人"),
		@Column(name="check_remarks", attrName="checkRemarks", label="审批备注", queryType=QueryType.LIKE),
		@Column(name="statu", attrName="statu", label="statu", isQuery=false),
	}, orderBy="a.update_date DESC"
)
public class SendSpecimenC extends DataEntity<SendSpecimenC> {
	
	private static final long serialVersionUID = 1L;
	private Date sendTime;		// 寄样时间
	private String customersCId;		// 客户
	private String address;		// 寄送地址
	private String logisticsBill;		// 物流单号
	private Double totalAmount;		// 总金额
	private Date preArriveTime;		// 预计送达
	private Date takeGoodsTime;		// 收货时间
	private String cusComment;		// 客户评价
	private Date checkTime;		// 审批时间
	private String checkBy;		// 审批人
	private String checkRemarks;		// 审批备注
	private String statu;		// statu
	private List<SendProductC> sendProductCList = ListUtils.newArrayList();		// 子表列表
	
	public SendSpecimenC() {
		this(null);
	}

	public SendSpecimenC(String id){
		super(id);
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="寄样时间不能为空")
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	@NotBlank(message="客户不能为空")
	@Length(min=0, max=64, message="客户长度不能超过 64 个字符")
	public String getCustomersCId() {
		return customersCId;
	}

	public void setCustomersCId(String customersCId) {
		this.customersCId = customersCId;
	}
	
	@NotBlank(message="寄送地址不能为空")
	@Length(min=0, max=200, message="寄送地址长度不能超过 200 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=64, message="物流单号长度不能超过 64 个字符")
	public String getLogisticsBill() {
		return logisticsBill;
	}

	public void setLogisticsBill(String logisticsBill) {
		this.logisticsBill = logisticsBill;
	}
	
	@NotNull(message="总金额不能为空")
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="预计送达不能为空")
	public Date getPreArriveTime() {
		return preArriveTime;
	}

	public void setPreArriveTime(Date preArriveTime) {
		this.preArriveTime = preArriveTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTakeGoodsTime() {
		return takeGoodsTime;
	}

	public void setTakeGoodsTime(Date takeGoodsTime) {
		this.takeGoodsTime = takeGoodsTime;
	}
	
	@Length(min=0, max=200, message="客户评价长度不能超过 200 个字符")
	public String getCusComment() {
		return cusComment;
	}

	public void setCusComment(String cusComment) {
		this.cusComment = cusComment;
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
	
	@NotBlank(message="statu不能为空")
	@Length(min=0, max=1, message="statu长度不能超过 1 个字符")
	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}
	
	public List<SendProductC> getSendProductCList() {
		return sendProductCList;
	}

	public void setSendProductCList(List<SendProductC> sendProductCList) {
		this.sendProductCList = sendProductCList;
	}
	
}