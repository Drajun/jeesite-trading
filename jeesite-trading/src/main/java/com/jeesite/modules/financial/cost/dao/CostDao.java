/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.financial.cost.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.financial.cost.entity.Cost;

/**
 * 成本记录DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-04-10
 */
@MyBatisDao
public interface CostDao extends CrudDao<Cost> {
	
}