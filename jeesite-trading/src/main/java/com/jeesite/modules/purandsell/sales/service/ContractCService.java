/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.purandsell.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.basic.product.dao.ProductCDao;
import com.jeesite.modules.basic.product.entity.ProductC;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.purandsell.sales.dao.ContractCDao;
import com.jeesite.modules.purandsell.sales.dao.SaleProductCDao;
import com.jeesite.modules.purandsell.sales.entity.ContractC;
import com.jeesite.modules.purandsell.sales.entity.SaleProductC;

/**
 * 销售合同Service
 * @author longlou.d@foxmail.com
 * @version 2019-03-21
 */
@Service
@Transactional(readOnly=true)
public class ContractCService extends CrudService<ContractCDao, ContractC> {
	
	@Autowired
	private SaleProductCDao saleProductCDao;
	@Autowired
	private ProductCDao productDao;
	/**
	 * 获取单条数据
	 * @param contractC
	 * @return
	 */
	@Override
	public ContractC get(ContractC contractC) {
		ContractC entity = super.get(contractC);
		if (entity != null){
			SaleProductC saleProductC = new SaleProductC(entity);
			saleProductC.setStatus(SaleProductC.STATUS_NORMAL);
			entity.setSaleProductCList(saleProductCDao.findList(saleProductC));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param contractC 查询条件
	 * @param contractC.page 分页对象
	 * @return
	 */
	@Override
	public Page<ContractC> findPage(ContractC contractC) {
		return super.findPage(contractC);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param contractC
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ContractC contractC) {
		super.save(contractC);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(contractC.getId(), "contractC_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(contractC.getId(), "contractC_file");
		// 保存 ContractC子表				
		for (SaleProductC saleProductC : contractC.getSaleProductCList()){
			if (!SaleProductC.STATUS_DELETE.equals(saleProductC.getStatus())){
				if(saleProductC.getContractCId()!=null&&saleProductC.getTabletype()!=null&&!saleProductC.getTabletype().equals("销售合同"))
					continue;
				if(saleProductC.getContractCId()!=null&&saleProductC.getContractCId().getId()!=null&&!saleProductC.getContractCId().getId().equals(contractC.getId()))
					continue;
					
				saleProductC.setContractCId(contractC);
				
				ProductC product = productDao.get(new ProductC(saleProductC.getProductCId()));
				saleProductC.setName(product.getName());
				saleProductC.setTotalAmount(saleProductC.getPrice()*(double)saleProductC.getNumber());
				saleProductC.setProducCode(product.getProductCode());
				saleProductC.setSpec(product.getSpec());
				saleProductC.setTabletype("销售合同");
				if (saleProductC.getIsNewRecord()){
					saleProductC.preInsert();
					saleProductCDao.insert(saleProductC);
				}else{
					saleProductC.preUpdate();
					saleProductCDao.update(saleProductC);
				}
			}else{
				saleProductCDao.delete(saleProductC);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param contractC
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ContractC contractC) {
		super.updateStatus(contractC);
	}
	
	/**
	 * 删除数据
	 * @param contractC
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ContractC contractC) {
		super.delete(contractC);
		SaleProductC saleProductC = new SaleProductC();
		saleProductC.setContractCId(contractC);
		saleProductCDao.delete(saleProductC);
	}
	
}