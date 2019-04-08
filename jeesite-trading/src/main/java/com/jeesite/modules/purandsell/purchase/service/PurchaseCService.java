/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.purandsell.purchase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.purandsell.purchase.entity.PurchaseC;
import com.jeesite.modules.purandsell.purchase.dao.PurchaseCDao;
import com.jeesite.modules.basic.product.dao.ProductCDao;
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.purandsell.purchase.entity.PurProductC;
import com.jeesite.modules.purandsell.purchase.dao.PurProductCDao;

/**
 * 订购管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-03-22
 */
@Service
@Transactional(readOnly=true)
public class PurchaseCService extends CrudService<PurchaseCDao, PurchaseC> {
	
	@Autowired
	private PurProductCDao purProductCDao;
	@Autowired
	private ProductCDao productDao;
	
	/**
	 * 获取单条数据
	 * @param purchaseC
	 * @return
	 */
	@Override
	public PurchaseC get(PurchaseC purchaseC) {
		PurchaseC entity = super.get(purchaseC);
		if (entity != null){
			PurProductC purProductC = new PurProductC(entity);
			purProductC.setStatus(PurProductC.STATUS_NORMAL);
			entity.setPurProductCList(purProductCDao.findList(purProductC));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param purchaseC 查询条件
	 * @param purchaseC.page 分页对象
	 * @return
	 */
	@Override
	public Page<PurchaseC> findPage(PurchaseC purchaseC) {
		return super.findPage(purchaseC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param purchaseC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(PurchaseC purchaseC) {
		super.save(purchaseC);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(purchaseC.getId(), "purchaseC_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(purchaseC.getId(), "purchaseC_file");
		// 保存 PurchaseC子表
		for (PurProductC purProductC : purchaseC.getPurProductCList()){
			if (!PurProductC.STATUS_DELETE.equals(purProductC.getStatus())){
				if(purProductC.getPurchaseCId()!=null&&purProductC.getTabletype()!=null&&!purProductC.getTabletype().equals("订购合同"))
					continue;
				if(purProductC.getPurchaseCId()!=null&&purProductC.getPurchaseCId().getId()!=null&&!purProductC.getPurchaseCId().getId().equals(purchaseC.getId()))
					continue;
				
				purProductC.setPurchaseCId(purchaseC);
				
				ProductC product = productDao.get(new ProductC(purProductC.getProductCId()));
				purProductC.setName(product.getName());
				purProductC.setTotalAmount(purProductC.getPrice()*(double)purProductC.getNumber());
				purProductC.setProducCode(product.getProductCode());
				purProductC.setSpec(product.getSpec());
				purProductC.setTabletype("订购合同");
				
				if (purProductC.getIsNewRecord()){
					purProductC.preInsert();
					purProductCDao.insert(purProductC);
				}else{
					purProductC.preUpdate();
					purProductCDao.update(purProductC);
				}
			}else{
				purProductCDao.delete(purProductC);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param purchaseC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(PurchaseC purchaseC) {
		super.updateStatus(purchaseC);
	}
	
	/**
	 * 删除数据
	 * @param purchaseC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(PurchaseC purchaseC) {
		super.delete(purchaseC);
		PurProductC purProductC = new PurProductC();
		purProductC.setPurchaseCId(purchaseC);
		purProductCDao.delete(purProductC);
	}
	
}