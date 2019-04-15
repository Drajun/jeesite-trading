/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.purandsell.returnc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.purandsell.returnc.entity.ReturnC;
import com.jeesite.modules.purandsell.returnc.dao.ReturnCDao;
import com.jeesite.modules.basic.product.dao.ProductCDao;
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.purandsell.returnc.entity.ReturnProductC;
import com.jeesite.modules.purandsell.returnc.dao.ReturnProductCDao;

/**
 * 退换管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-04-15
 */
@Service
@Transactional(readOnly=true)
public class ReturnCService extends CrudService<ReturnCDao, ReturnC> {
	
	@Autowired
	private ReturnProductCDao returnProductCDao;
	@Autowired
	private ProductCDao productDao;
	
	/**
	 * 获取单条数据
	 * @param returnC
	 * @return
	 */
	@Override
	public ReturnC get(ReturnC returnC) {
		ReturnC entity = super.get(returnC);
		if (entity != null){
			ReturnProductC returnProductC = new ReturnProductC(entity);
			returnProductC.setStatus(ReturnProductC.STATUS_NORMAL);
			entity.setReturnProductCList(returnProductCDao.findList(returnProductC));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param returnC 查询条件
	 * @param returnC.page 分页对象
	 * @return
	 */
	@Override
	public Page<ReturnC> findPage(ReturnC returnC) {
		return super.findPage(returnC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param returnC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ReturnC returnC) {
		super.save(returnC);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(returnC.getId(), "returnC_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(returnC.getId(), "returnC_file");
		// 保存 ReturnC子表
		for (ReturnProductC returnProductC : returnC.getReturnProductCList()){
			if (!ReturnProductC.STATUS_DELETE.equals(returnProductC.getStatus())){
				if(returnProductC.getReturnCId()!=null&&returnProductC.getTabletype()!=null&&!returnProductC.getTabletype().equals("退换管理"))
					continue;
				if(returnProductC.getReturnCId()!=null&&returnProductC.getReturnCId().getId()!=null&&!returnProductC.getReturnCId().getId().equals(returnC.getId()))
					continue;
				
				returnProductC.setReturnCId(returnC);
				
				ProductC product = productDao.get(new ProductC(returnProductC.getProductCId()));
				returnProductC.setName(product.getName());
				returnProductC.setTotalAmount(returnProductC.getPrice()*(double)returnProductC.getNumber());
				returnProductC.setProducCode(product.getProductCode());
				returnProductC.setSpec(product.getSpec());
				returnProductC.setTabletype("退换管理");
				
				if (returnProductC.getIsNewRecord()){
					returnProductC.preInsert();
					returnProductCDao.insert(returnProductC);
				}else{
					returnProductC.preUpdate();
					returnProductCDao.update(returnProductC);
				}
			}else{
				returnProductCDao.delete(returnProductC);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param returnC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ReturnC returnC) {
		super.updateStatus(returnC);
	}
	
	/**
	 * 删除数据
	 * @param returnC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ReturnC returnC) {
		super.delete(returnC);
		ReturnProductC returnProductC = new ReturnProductC();
		returnProductC.setReturnCId(returnC);
		returnProductCDao.delete(returnProductC);
	}
	
}