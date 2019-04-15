/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.purandsell.sales.dao;

import io.lettuce.core.dynamic.annotation.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.purandsell.sales.entity.ContractC;

/**
 * 销售合同DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-03-21
 */
@MyBatisDao
public interface ContractCDao extends CrudDao<ContractC> {
	public ContractC getContractByCode(@Param("code")String code);
}