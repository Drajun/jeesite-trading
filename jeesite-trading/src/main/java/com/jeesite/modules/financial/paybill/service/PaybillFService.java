/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.financial.paybill.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.financial.paybill.entity.PaybillF;
import com.jeesite.modules.financial.paybill.dao.PaybillFDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 付款管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-04-09
 */
@Service
@Transactional(readOnly=true)
public class PaybillFService extends CrudService<PaybillFDao, PaybillF> {
	
	/**
	 * 获取单条数据
	 * @param paybillF
	 * @return
	 */
	@Override
	public PaybillF get(PaybillF paybillF) {
		return super.get(paybillF);
	}
	
	/**
	 * 查询分页数据
	 * @param paybillF 查询条件
	 * @param paybillF.page 分页对象
	 * @return
	 */
	@Override
	public Page<PaybillF> findPage(PaybillF paybillF) {
		return super.findPage(paybillF);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param paybillF
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(PaybillF paybillF) {
		super.save(paybillF);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(paybillF.getId(), "paybillF_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(paybillF.getId(), "paybillF_file");
	}
	
	/**
	 * 更新状态
	 * @param paybillF
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(PaybillF paybillF) {
		super.updateStatus(paybillF);
	}
	
	/**
	 * 删除数据
	 * @param paybillF
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(PaybillF paybillF) {
		super.delete(paybillF);
	}
	
}