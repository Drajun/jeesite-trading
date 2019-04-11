/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.financial.cost.entity;

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
 * 成本记录Entity
 * @author longlou.d@foxmail.com
 * @version 2019-04-10
 */
@Table(name="cost", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="record_date", attrName="recordDate", label="记录日期"),
		@Column(name="employee_salary", attrName="employeeSalary", label="员工薪资"),
		@Column(name="power_water_bill", attrName="powerWaterBill", label="公司水电"),
		@Column(name="business_expense", attrName="businessExpense", label="公务报销"),
		@Column(name="office_expense", attrName="officeExpense", label="办公报销"),
		@Column(name="employee_train", attrName="employeeTrain", label="人员培训"),
		@Column(name="purchase_cost", attrName="purchaseCost", label="订购成本"),
		@Column(name="other_cost", attrName="otherCost", label="其他成本"),
		@Column(name="total_cost", attrName="totalCost", label="总成本"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Cost extends DataEntity<Cost> {
	
	private static final long serialVersionUID = 1L;
	private Date recordDate;			// 记录日期
	private Double employeeSalary;		// 员工薪资
	private Double powerWaterBill;		// 公司水电
	private Double businessExpense;		// 公务报销
	private Double officeExpense;		// 办公报销
	private Double employeeTrain;		// 人员培训
	private Double purchaseCost;		// 订购成本
	private Double otherCost;			// 其他成本
	private Double totalCost;			// 总成本
	
	public Cost() {
		this(null);
	}

	public Cost(String id){
		super(id);
	}
	
	@JsonFormat(pattern = "yyyy-MM")
	@NotNull(message="记录日期不能为空")
	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
	@NotNull(message="员工薪资不能为空")
	public Double getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(Double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	
	@NotNull(message="公司水电不能为空")
	public Double getPowerWaterBill() {
		return powerWaterBill;
	}

	public void setPowerWaterBill(Double powerWaterBill) {
		this.powerWaterBill = powerWaterBill;
	}
	
	@NotNull(message="公务报销不能为空")
	public Double getBusinessExpense() {
		return businessExpense;
	}

	public void setBusinessExpense(Double businessExpense) {
		this.businessExpense = businessExpense;
	}
	
	@NotNull(message="办公报销不能为空")
	public Double getOfficeExpense() {
		return officeExpense;
	}

	public void setOfficeExpense(Double officeExpense) {
		this.officeExpense = officeExpense;
	}
	
	@NotNull(message="人员培训不能为空")
	public Double getEmployeeTrain() {
		return employeeTrain;
	}

	public void setEmployeeTrain(Double employeeTrain) {
		this.employeeTrain = employeeTrain;
	}
	
	@NotNull(message="订购成本不能为空")
	public Double getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(Double purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	public Double getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(Double otherCost) {
		this.otherCost = otherCost;
	}
	
	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
}