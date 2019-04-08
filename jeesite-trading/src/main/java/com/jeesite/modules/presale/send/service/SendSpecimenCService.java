/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.presale.send.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.basic.product.dao.ProductCDao;
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.presale.send.dao.SendProductCDao;
import com.jeesite.modules.presale.send.dao.SendSpecimenCDao;
import com.jeesite.modules.presale.send.entity.SendProductC;
import com.jeesite.modules.presale.send.entity.SendSpecimenC;

/**
 * 寄样管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-03-20
 */
@Service
@Transactional(readOnly=true)
public class SendSpecimenCService extends CrudService<SendSpecimenCDao, SendSpecimenC> {
	
	@Autowired
	private SendProductCDao referenceProductCDao;
	@Autowired
	private ProductCDao productDao;
	
	/**
	 * 获取单条数据
	 * @param sendSpecimenC
	 * @return
	 */
	@Override
	public SendSpecimenC get(SendSpecimenC sendSpecimenC) {
		SendSpecimenC entity = super.get(sendSpecimenC);
		if (entity != null){
			SendProductC referenceProductC = new SendProductC(entity);
			referenceProductC.setStatus(SendProductC.STATUS_NORMAL);
			entity.setSendProductCList(referenceProductCDao.findList(referenceProductC));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param sendSpecimenC 查询条件
	 * @param sendSpecimenC.page 分页对象
	 * @return
	 */
	@Override
	public Page<SendSpecimenC> findPage(SendSpecimenC sendSpecimenC) {
		return super.findPage(sendSpecimenC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param sendSpecimenC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(SendSpecimenC sendSpecimenC) {
		super.save(sendSpecimenC);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(sendSpecimenC.getId(), "sendSpecimenC_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(sendSpecimenC.getId(), "sendSpecimenC_file");
		// 保存 SendSpecimenC子表
		for (SendProductC referenceProductC : sendSpecimenC.getSendProductCList()){
			if (!SendProductC.STATUS_DELETE.equals(referenceProductC.getStatus())){
				if(referenceProductC.getSendSpecimenCId()!=null&&referenceProductC.getTabletype()!=null&&!referenceProductC.getTabletype().equals("寄样管理"))
					continue;
				if(referenceProductC.getSendSpecimenCId()!=null&&referenceProductC.getSendSpecimenCId().getId()!=null&&!referenceProductC.getSendSpecimenCId().getId().equals(sendSpecimenC.getId()))
					continue;
				
				referenceProductC.setSendSpecimenCId(sendSpecimenC);
				
				ProductC product = productDao.get(new ProductC(referenceProductC.getProductCId()));
				referenceProductC.setName(product.getName());
				referenceProductC.setProducCode(product.getProductCode());
				referenceProductC.setSpec(product.getSpec());
				referenceProductC.setTotalAmount(referenceProductC.getPrice()*(double)referenceProductC.getNumber());
				referenceProductC.setTabletype("寄样管理");
				if (referenceProductC.getIsNewRecord()){
					referenceProductC.preInsert();
					referenceProductCDao.insert(referenceProductC);
				}else{
					referenceProductC.preUpdate();
					referenceProductCDao.update(referenceProductC);
				}
			}else{
				referenceProductCDao.delete(referenceProductC);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param sendSpecimenC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(SendSpecimenC sendSpecimenC) {
		super.updateStatus(sendSpecimenC);
	}
	
	/**
	 * 删除数据
	 * @param sendSpecimenC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(SendSpecimenC sendSpecimenC) {
		super.delete(sendSpecimenC);
		SendProductC referenceProductC = new SendProductC();
		referenceProductC.setSendSpecimenCId(sendSpecimenC);
		referenceProductCDao.delete(referenceProductC);
	}
	
}