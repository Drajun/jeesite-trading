/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.exhibition.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.exhibition.entity.ExhibitionC;

/**
 * 展会管理DAO接口
 * @author longlou.d@foxmail,com
 * @version 2019-03-11
 */
@MyBatisDao
public interface ExhibitionCDao extends CrudDao<ExhibitionC> {
	
}