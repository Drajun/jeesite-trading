/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.financial.paybill.entity;

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
 * 付款管理Entity
 * @author longlou.d@foxmail.com
 * @version 2019-04-09
 */
@Table(name="paybill_f", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="paybill_code", attrName="paybillCode", label="付款单号", queryType=QueryType.LIKE),
		@Column(name="paybill_time", attrName="paybillTime", label="付款时间"),
		@Column(name="paybill_type", attrName="paybillType", label="付款类型"),
		@Column(name="accessory_doc", attrName="accessoryDoc", label="随附单据", isQuery=false),
		@Column(name="bill_code", attrName="billCode", label="单据编号", isQuery=false),
		@Column(name="payment_term", attrName="paymentTerm", label="付款方式", isQuery=false),
		@Column(name="trade_amount", attrName="tradeAmount", label="交易金额", queryType=QueryType.GTE),
		@Column(name="actual_amount", attrName="actualAmount", label="实付金额", queryType=QueryType.GTE),
		@Column(name="payer", attrName="payer", label="收款方", queryType=QueryType.LIKE),
		@Column(name="pay_bank", attrName="payBank", label="开户银行", isQuery=false),
		@Column(name="check_num", attrName="checkNum", label="支票号码", isQuery=false),
		@Column(name="bank_account", attrName="bankAccount", label="银行账号", isQuery=false),
		@Column(name="letter_credit", attrName="letterCredit", label="信用证号", isQuery=false),
		@Column(includeEntity=DataEntity.class),
		@Column(name="statu", attrName="statu", label="申请状态"),
		@Column(name="check_time", attrName="checkTime", label="审批时间", isQuery=false),
		@Column(name="check_by", attrName="checkBy", label="审批人", isQuery=false),
		@Column(name="check_remarks", attrName="checkRemarks", label="审批备注", queryType=QueryType.LIKE),
	}, orderBy="a.update_date DESC"
)
public class PaybillF extends DataEntity<PaybillF> {
	
	private static final long serialVersionUID = 1L;
	private String paybillCode;		// 付款单号
	private Date paybillTime;		// 付款时间
	private String paybillType;		// 付款类型
	private String accessoryDoc;		// 随附单据
	private String billCode;		// 单据编号
	private String paymentTerm;		// 付款方式
	private Double tradeAmount;		// 交易金额
	private Double actualAmount;		// 实付金额
	private String payer;		// 收款方
	private String payBank;		// 开户银行
	private String checkNum;		// 支票号码
	private String bankAccount;		// 银行账号
	private String letterCredit;		// 信用证号
	private String statu;		// 申请状态
	private Date checkTime;		// 审批时间
	private String checkBy;		// 审批人
	private String checkRemarks;		// 审批备注
	
	public PaybillF() {
		this(null);
	}

	public PaybillF(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="付款单号长度不能超过 64 个字符")
	public String getPaybillCode() {
		return paybillCode;
	}

	public void setPaybillCode(String paybillCode) {
		this.paybillCode = paybillCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="付款时间不能为空")
	public Date getPaybillTime() {
		return paybillTime;
	}

	public void setPaybillTime(Date paybillTime) {
		this.paybillTime = paybillTime;
	}
	
	@NotBlank(message="付款类型不能为空")
	@Length(min=0, max=1, message="付款类型长度不能超过 1 个字符")
	public String getPaybillType() {
		return paybillType;
	}

	public void setPaybillType(String paybillType) {
		this.paybillType = paybillType;
	}
	
	@NotBlank(message="随附单据不能为空")
	@Length(min=0, max=64, message="随附单据长度不能超过 64 个字符")
	public String getAccessoryDoc() {
		return accessoryDoc;
	}

	public void setAccessoryDoc(String accessoryDoc) {
		this.accessoryDoc = accessoryDoc;
	}
	
	@Length(min=0, max=64, message="单据编号长度不能超过 64 个字符")
	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
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
	
	@NotNull(message="实付金额不能为空")
	public Double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}
	
	@NotBlank(message="收款方不能为空")
	@Length(min=0, max=100, message="收款方长度不能超过 100 个字符")
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