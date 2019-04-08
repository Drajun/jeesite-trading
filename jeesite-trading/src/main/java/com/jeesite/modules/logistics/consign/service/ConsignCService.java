/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.consign.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.logistics.consign.entity.ConsignC;
import com.jeesite.modules.logistics.consign.dao.ConsignCDao;
import com.jeesite.modules.basic.product.dao.ProductCDao;
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.logistics.consign.entity.ConsignProductC;
import com.jeesite.modules.logistics.consign.dao.ConsignProductCDao;

/**
 * 托运管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-04-03
 */
@Service
@Transactional(readOnly=true)
public class ConsignCService extends CrudService<ConsignCDao, ConsignC> {
	
	@Autowired
	private ConsignProductCDao consignProductCDao;
	@Autowired
	private ProductCDao productDao;
	
	/**
	 * 获取单条数据
	 * @param consignC
	 * @return
	 */
	@Override
	public ConsignC get(ConsignC consignC) {
		ConsignC entity = super.get(consignC);
		if (entity != null){
			ConsignProductC consignProductC = new ConsignProductC(entity);
			consignProductC.setStatus(ConsignProductC.STATUS_NORMAL);
			entity.setConsignProductCList(consignProductCDao.findList(consignProductC));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param consignC 查询条件
	 * @param consignC.page 分页对象
	 * @return
	 */
	@Override
	public Page<ConsignC> findPage(ConsignC consignC) {
		return super.findPage(consignC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param consignC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ConsignC consignC) {
		super.save(consignC);
		// 保存上传附件
		FileUploadUtils.saveFileUpload(consignC.getId(), "consignC_file");
		// 保存 ConsignC子表
		for (ConsignProductC consignProductC : consignC.getConsignProductCList()){
			if (!ConsignProductC.STATUS_DELETE.equals(consignProductC.getStatus())){
				if(consignProductC.getConsignCId()!=null&&consignProductC.getTabletype()!=null&&!consignProductC.getTabletype().equals("托运管理"))
					continue;
				if(consignProductC.getConsignCId()!=null&&consignProductC.getConsignCId().getId()!=null&&!consignProductC.getConsignCId().getId().equals(consignC.getId()))
					continue;

				consignProductC.setConsignCId(consignC);
				
				ProductC product = productDao.get(new ProductC(consignProductC.getProductCId()));
				consignProductC.setName(product.getName());
				consignProductC.setTotalAmount(consignProductC.getPrice()*(double)consignProductC.getNumber());
				consignProductC.setProducCode(product.getProductCode());
				consignProductC.setSpec(product.getSpec());
				consignProductC.setTabletype("托运管理");
				if (consignProductC.getIsNewRecord()){
					consignProductC.preInsert();
					consignProductCDao.insert(consignProductC);
				}else{
					consignProductC.preUpdate();
					consignProductCDao.update(consignProductC);
				}
			}else{
				consignProductCDao.delete(consignProductC);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param consignC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ConsignC consignC) {
		super.updateStatus(consignC);
	}
	
	/**
	 * 删除数据
	 * @param consignC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ConsignC consignC) {
		super.delete(consignC);
		ConsignProductC consignProductC = new ConsignProductC();
		consignProductC.setConsignCId(consignC);
		consignProductCDao.delete(consignProductC);
	}
	
}