/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.financial.cost.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.financial.cost.entity.Cost;
import com.jeesite.modules.financial.cost.dao.CostDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 成本记录Service
 * @author longlou.d@foxmail.com
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly=true)
public class CostService extends CrudService<CostDao, Cost> {
	
	/**
	 * 获取单条数据
	 * @param cost
	 * @return
	 */
	@Override
	public Cost get(Cost cost) {
		return super.get(cost);
	}
	
	/**
	 * 查询分页数据
	 * @param cost 查询条件
	 * @param cost.page 分页对象
	 * @return
	 */
	@Override
	public Page<Cost> findPage(Cost cost) {
		return super.findPage(cost);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param cost
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Cost cost) {
		double totalCost = cost.getPurchaseCost()+cost.getEmployeeSalary()+cost.getEmployeeTrain()+cost.getPowerWaterBill()
				+cost.getBusinessExpense()+cost.getOfficeExpense()+cost.getOtherCost();
		cost.setTotalCost(totalCost);
		super.save(cost);
		// 保存上传附件
		FileUploadUtils.saveFileUpload(cost.getId(), "cost_file");
	}
	
	/**
	 * 更新状态
	 * @param cost
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Cost cost) {
		super.updateStatus(cost);
	}
	
	/**
	 * 删除数据
	 * @param cost
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Cost cost) {
		super.delete(cost);
	}
	
}