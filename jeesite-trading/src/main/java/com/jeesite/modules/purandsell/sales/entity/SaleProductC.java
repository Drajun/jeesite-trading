/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.purandsell.sales.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 销售合同Entity
 * @author longlou.d@foxmail.com
 * @version 2019-03-21
 */
@Table(name="reference_product_c", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="reference_id", attrName="contractCId.id", label="寄样单ID", isQuery=false),
		@Column(name="product_c_id", attrName="productCId", label="货物选择", isQuery=false),
		@Column(name="name", attrName="name", label="货物名称", isQuery=false),
		@Column(name="produc_code", attrName="producCode", label="货物编码", queryType=QueryType.LIKE),
		@Column(name="price", attrName="price", label="单价", queryType=QueryType.GTE),
		@Column(name="number", attrName="number", label="数量", queryType=QueryType.GTE),
		@Column(name="total_amount", attrName="totalAmount", label="总金额", queryType=QueryType.GTE),
		@Column(name="package_unit", attrName="packageUnit", label="包装单位", isQuery=false),
		@Column(name="net_weight", attrName="netWeight", label="净重", isQuery=false),
		@Column(name="gross_weight", attrName="grossWeight", label="毛重", isQuery=false),
		@Column(name="spec", attrName="spec", label="规格", queryType=QueryType.LIKE),
		@Column(name="per_box_num", attrName="perBoxNum", label="每箱件数", isQuery=false),
		@Column(name="ctns_measrure", attrName="ctnsMeasrure", label="外箱尺寸", isQuery=false),
		@Column(name="single_package_type", attrName="singlePackageType", label="单件包装方式", isQuery=false),
		@Column(name="inner_package_type", attrName="innerPackageType", label="内包装方式", isQuery=false),
		@Column(name="factory_c_id", attrName="factoryCId", label="生产厂商", isQuery=false),
		@Column(includeEntity=DataEntity.class),
		@Column(name="statu", attrName="statu", label="货物状态", isQuery=false),
		@Column(name="tabletype", attrName="tabletype", label="tabletype", isQuery=false),
	}, orderBy="a.create_date ASC"
)
public class SaleProductC extends DataEntity<SaleProductC> {
	
	private static final long serialVersionUID = 1L;
	private ContractC contractCId;		// 寄样单ID 父类
	private String productCId;		// 货物选择
	private String name;		// 货物名称
	private String producCode;		// 货物编码
	private Double price;		// 单价
	private Long number;		// 数量
	private Double totalAmount;		// 总金额
	private String packageUnit;		// 包装单位
	private Double netWeight;		// 净重
	private Double grossWeight;		// 毛重
	private String spec;		// 规格
	private Long perBoxNum;		// 每箱件数
	private String ctnsMeasrure;		// 外箱尺寸
	private String singlePackageType;		// 单件包装方式
	private String innerPackageType;		// 内包装方式
	private String factoryCId;		// 生产厂商
	private String statu;		// 货物状态
	private String tabletype;		// tabletype
	
	public SaleProductC() {
		this(null);
	}


	public SaleProductC(ContractC contractCId){
		this.contractCId = contractCId;
	}
	
	public ContractC getContractCId() {
		return contractCId;
	}

	public void setContractCId(ContractC contractCId) {
		this.contractCId = contractCId;
	}
	
	@NotBlank(message="货物选择不能为空")
	@Length(min=0, max=64, message="货物选择长度不能超过 64 个字符")
	public String getProductCId() {
		return productCId;
	}

	public void setProductCId(String productCId) {
		this.productCId = productCId;
	}
	
	@Length(min=0, max=64, message="货物名称长度不能超过 64 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="货物编码长度不能超过 64 个字符")
	public String getProducCode() {
		return producCode;
	}

	public void setProducCode(String producCode) {
		this.producCode = producCode;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@NotNull(message="数量不能为空")
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Length(min=0, max=20, message="包装单位长度不能超过 20 个字符")
	public String getPackageUnit() {
		return packageUnit;
	}

	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}
	
	@NotNull(message="净重不能为空")
	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}
	
	@NotNull(message="毛重不能为空")
	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}
	
	@Length(min=0, max=200, message="规格长度不能超过 200 个字符")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public Long getPerBoxNum() {
		return perBoxNum;
	}

	public void setPerBoxNum(Long perBoxNum) {
		this.perBoxNum = perBoxNum;
	}
	
	public String getCtnsMeasrure() {
		return ctnsMeasrure;
	}

	public void setCtnsMeasrure(String ctnsMeasrure) {
		this.ctnsMeasrure = ctnsMeasrure;
	}
	
	@Length(min=0, max=64, message="单件包装方式长度不能超过 64 个字符")
	public String getSinglePackageType() {
		return singlePackageType;
	}

	public void setSinglePackageType(String singlePackageType) {
		this.singlePackageType = singlePackageType;
	}
	
	@Length(min=0, max=64, message="内包装方式长度不能超过 64 个字符")
	public String getInnerPackageType() {
		return innerPackageType;
	}

	public void setInnerPackageType(String innerPackageType) {
		this.innerPackageType = innerPackageType;
	}
	
	public String getFactoryCId() {
		return factoryCId;
	}

	public void setFactoryCId(String factoryCId) {
		this.factoryCId = factoryCId;
	}
	
	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}
	
	public String getTabletype() {
		return tabletype;
	}

	public void setTabletype(String tabletype) {
		this.tabletype = tabletype;
	}
	
}