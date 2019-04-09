/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.financial.rebill.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.financial.rebill.entity.RebillF;
import com.jeesite.modules.financial.rebill.dao.RebillFDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 收款管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-04-09
 */
@Service
@Transactional(readOnly=true)
public class RebillFService extends CrudService<RebillFDao, RebillF> {
	
	/**
	 * 获取单条数据
	 * @param rebillF
	 * @return
	 */
	@Override
	public RebillF get(RebillF rebillF) {
		return super.get(rebillF);
	}
	
	/**
	 * 查询分页数据
	 * @param rebillF 查询条件
	 * @param rebillF.page 分页对象
	 * @return
	 */
	@Override
	public Page<RebillF> findPage(RebillF rebillF) {
		return super.findPage(rebillF);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param rebillF
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(RebillF rebillF) {
		super.save(rebillF);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(rebillF.getId(), "rebillF_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(rebillF.getId(), "rebillF_file");
	}
	
	/**
	 * 更新状态
	 * @param rebillF
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(RebillF rebillF) {
		super.updateStatus(rebillF);
	}
	
	/**
	 * 删除数据
	 * @param rebillF
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(RebillF rebillF) {
		super.delete(rebillF);
	}
	
}