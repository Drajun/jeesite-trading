/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.logistics.inspection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.basic.product.dao.ProductCDao;
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.logistics.inspection.dao.InspectionCDao;
import com.jeesite.modules.logistics.inspection.dao.InspectionProductCDao;
import com.jeesite.modules.logistics.inspection.entity.InspectionC;
import com.jeesite.modules.logistics.inspection.entity.InspectionProductC;

/**
 * 报检管理Service
 * @author longlou.d@foxmail.com
 * @version 2019-04-04
 */
@Service
@Transactional(readOnly=true)
public class InspectionCService extends CrudService<InspectionCDao, InspectionC> {
	
	@Autowired
	private InspectionProductCDao inspectionProductCDao;
	@Autowired
	private ProductCDao productDao;
	/**
	 * 获取单条数据
	 * @param inspectionC
	 * @return
	 */
	@Override
	public InspectionC get(InspectionC inspectionC) {
		InspectionC entity = super.get(inspectionC);
		if (entity != null){
			InspectionProductC inspectionProductC = new InspectionProductC(entity);
			inspectionProductC.setStatus(InspectionProductC.STATUS_NORMAL);
			entity.setInspectionProductCList(inspectionProductCDao.findList(inspectionProductC));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param inspectionC 查询条件
	 * @param inspectionC.page 分页对象
	 * @return
	 */
	@Override
	public Page<InspectionC> findPage(InspectionC inspectionC) {
		return super.findPage(inspectionC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param inspectionC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(InspectionC inspectionC) {
		super.save(inspectionC);
		// 保存上传附件
		FileUploadUtils.saveFileUpload(inspectionC.getId(), "inspectionC_file");
		// 保存 InspectionC子表
		for (InspectionProductC inspectionProductC : inspectionC.getInspectionProductCList()){
			if (!InspectionProductC.STATUS_DELETE.equals(inspectionProductC.getStatus())){
				if(inspectionProductC.getInspectionCId()!=null&&inspectionProductC.getTabletype()!=null&&!inspectionProductC.getTabletype().equals("报检管理"))
					continue;
				if(inspectionProductC.getInspectionCId()!=null&&inspectionProductC.getInspectionCId().getId()!=null&&!inspectionProductC.getInspectionCId().getId().equals(inspectionC.getId()))
					continue;
				
				inspectionProductC.setInspectionCId(inspectionC);
				
				ProductC product = productDao.get(new ProductC(inspectionProductC.getProductCId()));
				inspectionProductC.setName(product.getName());
				inspectionProductC.setTotalAmount(inspectionProductC.getPrice()*(double)inspectionProductC.getNumber());
				inspectionProductC.setProducCode(product.getProductCode());
				inspectionProductC.setSpec(product.getSpec());
				inspectionProductC.setTabletype("报检管理");
				if (inspectionProductC.getIsNewRecord()){
					inspectionProductC.preInsert();
					inspectionProductCDao.insert(inspectionProductC);
				}else{
					inspectionProductC.preUpdate();
					inspectionProductCDao.update(inspectionProductC);
				}
			}else{
				inspectionProductCDao.delete(inspectionProductC);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param inspectionC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(InspectionC inspectionC) {
		super.updateStatus(inspectionC);
	}
	
	/**
	 * 删除数据
	 * @param inspectionC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(InspectionC inspectionC) {
		super.delete(inspectionC);
		InspectionProductC inspectionProductC = new InspectionProductC();
		inspectionProductC.setInspectionCId(inspectionC);
		inspectionProductCDao.delete(inspectionProductC);
	}
	
}