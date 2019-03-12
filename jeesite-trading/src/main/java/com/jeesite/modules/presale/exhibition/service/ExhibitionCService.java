/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.presale.exhibition.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.presale.exhibition.entity.ExhibitionC;
import com.jeesite.modules.presale.exhibition.dao.ExhibitionCDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 展会管理Service
 * @author longlou.d@foxmail,com
 * @version 2019-03-11
 */
@Service
@Transactional(readOnly=true)
public class ExhibitionCService extends CrudService<ExhibitionCDao, ExhibitionC> {
	
	/**
	 * 获取单条数据
	 * @param exhibitionC
	 * @return
	 */
	@Override
	public ExhibitionC get(ExhibitionC exhibitionC) {
		return super.get(exhibitionC);
	}
	
	/**
	 * 查询分页数据
	 * @param exhibitionC 查询条件
	 * @param exhibitionC.page 分页对象
	 * @return
	 */
	@Override
	public Page<ExhibitionC> findPage(ExhibitionC exhibitionC) {
		return super.findPage(exhibitionC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param exhibitionC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ExhibitionC exhibitionC) {
		super.save(exhibitionC);
		// 保存上传附件
		FileUploadUtils.saveFileUpload(exhibitionC.getId(), "exhibitionC_file");
	}
	
	/**
	 * 更新状态
	 * @param exhibitionC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ExhibitionC exhibitionC) {
		super.updateStatus(exhibitionC);
	}
	
	/**
	 * 删除数据
	 * @param exhibitionC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ExhibitionC exhibitionC) {
		super.delete(exhibitionC);
	}
	
}