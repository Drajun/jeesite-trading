/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.financial.rebill.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.financial.rebill.entity.RebillF;

/**
 * 收款管理DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-04-09
 */
@MyBatisDao
public interface RebillFDao extends CrudDao<RebillF> {
	
}