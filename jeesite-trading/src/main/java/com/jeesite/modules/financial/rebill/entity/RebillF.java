/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.financial.rebill.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 收款管理Entity
 * @author longlou.d@foxmail.com
 * @version 2019-04-09
 */
@Table(name="rebill_f", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="rebill_code", attrName="rebillCode", label="收款单号", queryType=QueryType.LIKE),
		@Column(name="rebill_time", attrName="rebillTime", label="收款时间"),
		@Column(name="rebill_type", attrName="rebillType", label="收款类型"),
		@Column(name="accessory_doc", attrName="accessoryDoc", label="随附单据", isQuery=false),
		@Column(name="bill_code", attrName="billCode", label="单据编号"),
		@Column(name="payment_term", attrName="paymentTerm", label="付款方式"),
		@Column(name="trade_amount", attrName="tradeAmount", label="交易金额", queryType=QueryType.GTE),
		@Column(name="actual_amount", attrName="actualAmount", label="实收金额", queryType=QueryType.GTE),
		@Column(name="payer", attrName="payer", label="付款方", queryType=QueryType.LIKE),
		@Column(name="pay_bank", attrName="payBank", label="开户银行", isQuery=false),
		@Column(name="check_num", attrName="checkNum", label="支票号码", isQuery=false),
		@Column(name="bank_account", attrName="bankAccount", label="银行账号", isQuery=false),
		@Column(name="letter_credit", attrName="letterCredit", label="信用证号", isQuery=false),
		@Column(name="status", attrName="status", label="状态", isUpdate=false, isQuery=false),
		@Column(name="create_by", attrName="createBy", label="创建者", isUpdate=false, isQuery=false),
		@Column(name="create_date", attrName="createDate", label="创建时间", isUpdate=false, isQuery=false),
		@Column(name="update_by", attrName="updateBy", label="更新者", isQuery=false),
		@Column(name="update_date", attrName="updateDate", label="更新时间", isQuery=false),
		@Column(name="remarks", attrName="remarks", label="备注信息", queryType=QueryType.LIKE),
		@Column(name="statu", attrName="statu", label="申请状态"),
		@Column(name="check_time", attrName="checkTime", label="审批时间"),
		@Column(name="check_by", attrName="checkBy", label="审批人"),
		@Column(name="check_remarks", attrName="checkRemarks", label="审批备注", queryType=QueryType.LIKE),
	}, orderBy="a.update_date DESC"
)
public class RebillF extends DataEntity<RebillF> {
	
	private static final long serialVersionUID = 1L;
	private String rebillCode;		// 收款单号
	private Date rebillTime;		// 收款时间
	private String rebillType;		// 收款类型
	private String accessoryDoc;		// 随附单据
	private String billCode;		//单据编号
	private String paymentTerm;		// 付款方式
	private Double tradeAmount;		// 交易金额
	private Double actualAmount;		// 实收金额
	private String payer;		// 付款方
	private String payBank;		// 开户银行
	private String checkNum;		// 支票号码
	private String bankAccount;		// 银行账号
	private String letterCredit;		// 信用证号
	private String statu;		// 申请状态
	private Date checkTime;		// 审批时间
	private String checkBy;		// 审批人
	private String checkRemarks;		// 审批备注
	
	public RebillF() {
		this(null);
	}

	public RebillF(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="单据编号长度不能超过 64 个字符")
	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	@Length(min=0, max=64, message="收款单号长度不能超过 64 个字符")
	public String getRebillCode() {
		return rebillCode;
	}

	public void setRebillCode(String rebillCode) {
		this.rebillCode = rebillCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="收款时间不能为空")
	public Date getRebillTime() {
		return rebillTime;
	}

	public void setRebillTime(Date rebillTime) {
		this.rebillTime = rebillTime;
	}
	
	@NotBlank(message="收款类型不能为空")
	@Length(min=0, max=1, message="收款类型长度不能超过 1 个字符")
	public String getRebillType() {
		return rebillType;
	}

	public void setRebillType(String rebillType) {
		this.rebillType = rebillType;
	}
	
	@NotBlank(message="随附单据不能为空")
	@Length(min=0, max=64, message="随附单据长度不能超过 64 个字符")
	public String getAccessoryDoc() {
		return accessoryDoc;
	}

	public void setAccessoryDoc(String accessoryDoc) {
		this.accessoryDoc = accessoryDoc;
	}
	
	@NotBlank(message="付款方式不能为空")
	@Length(min=0, max=10, message="付款方式长度不能超过 10 个字符")
	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	
	@NotNull(message="交易金额不能为空")
	public Double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	
	@NotNull(message="实收金额不能为空")
	public Double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}
	
	@NotBlank(message="付款方不能为空")
	@Length(min=0, max=100, message="付款方长度不能超过 100 个字符")
	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}
	
	@Length(min=0, max=64, message="开户银行长度不能超过 64 个字符")
	public String getPayBank() {
		return payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}
	
	@Length(min=0, max=64, message="支票号码长度不能超过 64 个字符")
	public String getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	
	@Length(min=0, max=64, message="银行账号长度不能超过 64 个字符")
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	@Length(min=0, max=64, message="信用证号长度不能超过 64 个字符")
	public String getLetterCredit() {
		return letterCredit;
	}

	public void setLetterCredit(String letterCredit) {
		this.letterCredit = letterCredit;
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