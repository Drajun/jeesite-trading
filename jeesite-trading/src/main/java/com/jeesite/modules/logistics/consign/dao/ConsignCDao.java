/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.consign.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.logistics.consign.entity.ConsignC;

/**
 * 托运管理DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-04-03
 */
@MyBatisDao
public interface ConsignCDao extends CrudDao<ConsignC> {
	
}