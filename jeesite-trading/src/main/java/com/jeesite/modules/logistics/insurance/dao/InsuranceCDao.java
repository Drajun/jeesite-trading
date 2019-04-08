/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.insurance.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.logistics.insurance.entity.InsuranceC;

/**
 * 保险管理DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-04-08
 */
@MyBatisDao
public interface InsuranceCDao extends CrudDao<InsuranceC> {
	
}