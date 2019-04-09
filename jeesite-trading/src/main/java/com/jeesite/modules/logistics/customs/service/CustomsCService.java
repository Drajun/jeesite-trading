/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.customs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.logistics.customs.entity.CustomsC;
import com.jeesite.modules.logistics.customs.dao.CustomsCDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 报关委托Service
 * @author longlou.d@foxmail.com
 * @version 2019-04-09
 */
@Service
@Transactional(readOnly=true)
public class CustomsCService extends CrudService<CustomsCDao, CustomsC> {
	
	/**
	 * 获取单条数据
	 * @param customsC
	 * @return
	 */
	@Override
	public CustomsC get(CustomsC customsC) {
		return super.get(customsC);
	}
	
	/**
	 * 查询分页数据
	 * @param customsC 查询条件
	 * @param customsC.page 分页对象
	 * @return
	 */
	@Override
	public Page<CustomsC> findPage(CustomsC customsC) {
		return super.findPage(customsC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param customsC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CustomsC customsC) {
		super.save(customsC);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(customsC.getId(), "customsC_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(customsC.getId(), "customsC_file");
	}
	
	/**
	 * 更新状态
	 * @param customsC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CustomsC customsC) {
		super.updateStatus(customsC);
	}
	
	/**
	 * 删除数据
	 * @param customsC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CustomsC customsC) {
		super.delete(customsC);
	}
	
}