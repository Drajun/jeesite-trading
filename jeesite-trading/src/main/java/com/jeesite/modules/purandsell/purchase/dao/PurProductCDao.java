/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.purandsell.purchase.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.purandsell.purchase.entity.PurProductC;

/**
 * 订购管理DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-03-22
 */
@MyBatisDao
public interface PurProductCDao extends CrudDao<PurProductC> {
	
}