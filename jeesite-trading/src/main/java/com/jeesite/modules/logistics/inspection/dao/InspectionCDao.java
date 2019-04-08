/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.inspection.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.logistics.inspection.entity.InspectionC;

/**
 * 报检管理DAO接口
 * @author longlou.d@foxmail.com
 * @version 2019-04-04
 */
@MyBatisDao
public interface InspectionCDao extends CrudDao<InspectionC> {
	
}