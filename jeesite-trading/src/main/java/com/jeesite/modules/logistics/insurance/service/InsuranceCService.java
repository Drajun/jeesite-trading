/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.insurance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.logistics.insurance.entity.InsuranceC;
import com.jeesite.modules.logistics.insurance.dao.InsuranceCDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 保险管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-04-08
 */
@Service
@Transactional(readOnly=true)
public class InsuranceCService extends CrudService<InsuranceCDao, InsuranceC> {
	
	/**
	 * 获取单条数据
	 * @param insuranceC
	 * @return
	 */
	@Override
	public InsuranceC get(InsuranceC insuranceC) {
		return super.get(insuranceC);
	}
	
	/**
	 * 查询分页数据
	 * @param insuranceC 查询条件
	 * @param insuranceC.page 分页对象
	 * @return
	 */
	@Override
	public Page<InsuranceC> findPage(InsuranceC insuranceC) {
		return super.findPage(insuranceC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param insuranceC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(InsuranceC insuranceC) {
		super.save(insuranceC);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(insuranceC.getId(), "insuranceC_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(insuranceC.getId(), "insuranceC_file");
	}
	
	/**
	 * 更新状态
	 * @param insuranceC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(InsuranceC insuranceC) {
		super.updateStatus(insuranceC);
	}
	
	/**
	 * 删除数据
	 * @param insuranceC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(InsuranceC insuranceC) {
		super.delete(insuranceC);
	}
	
}