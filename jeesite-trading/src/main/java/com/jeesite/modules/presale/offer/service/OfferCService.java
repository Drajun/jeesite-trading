/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.presale.offer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.presale.offer.entity.OfferC;
import com.jeesite.modules.presale.offer.dao.OfferCDao;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.presale.offer.entity.ReferenceProductC;
import com.jeesite.modules.presale.offer.dao.ReferenceProductCDao;

/**
 * 报价管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly=true)
public class OfferCService extends CrudService<OfferCDao, OfferC> {
	
	@Autowired
	private ReferenceProductCDao referenceProductCDao;
	
	/**
	 * 获取单条数据
	 * @param offerC
	 * @return
	 */
	@Override
	public OfferC get(OfferC offerC) {
		OfferC entity = super.get(offerC);
		if (entity != null){
			ReferenceProductC referenceProductC = new ReferenceProductC(entity);
			referenceProductC.setStatus(ReferenceProductC.STATUS_NORMAL);
			entity.setReferenceProductCList(referenceProductCDao.findList(referenceProductC));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param offerC 查询条件
	 * @param offerC.page 分页对象
	 * @return
	 */
	@Override
	public Page<OfferC> findPage(OfferC offerC) {
		return super.findPage(offerC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param offerC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(OfferC offerC) {
		super.save(offerC);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(offerC.getId(), "offerC_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(offerC.getId(), "offerC_file");
		// 保存 OfferC子表
		for (ReferenceProductC referenceProductC : offerC.getReferenceProductCList()){
			if (!ReferenceProductC.STATUS_DELETE.equals(referenceProductC.getStatus())){
				referenceProductC.setOfferCId(offerC);
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
	 * @param offerC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(OfferC offerC) {
		super.updateStatus(offerC);
	}
	
	/**
	 * 删除数据
	 * @param offerC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(OfferC offerC) {
		super.delete(offerC);
		ReferenceProductC referenceProductC = new ReferenceProductC();
		referenceProductC.setOfferCId(offerC);
		referenceProductCDao.delete(referenceProductC);
	}
	
}