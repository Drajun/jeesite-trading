/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.customs.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.logistics.customs.entity.CustomsC;

/**
 * 报关委托DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-04-09
 */
@MyBatisDao
public interface CustomsCDao extends CrudDao<CustomsC> {
	
}