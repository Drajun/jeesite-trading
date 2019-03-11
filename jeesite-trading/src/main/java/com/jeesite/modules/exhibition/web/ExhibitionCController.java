/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.exhibition.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.exhibition.entity.ExhibitionC;
import com.jeesite.modules.exhibition.service.ExhibitionCService;

/**
 * 展会管理Controller
 * @author longlou.d@foxmail,com
 * @version 2019-03-11
 */
@Controller
@RequestMapping(value = "${adminPath}/exhibition/exhibitionC")
public class ExhibitionCController extends BaseController {

	@Autowired
	private ExhibitionCService exhibitionCService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ExhibitionC get(String id, boolean isNewRecord) {
		return exhibitionCService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("exhibition:exhibitionC:view")
	@RequestMapping(value = {"list", ""})
	public String list(ExhibitionC exhibitionC, Model model) {
		model.addAttribute("exhibitionC", exhibitionC);
		return "modules/exhibition/exhibitionCList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("exhibition:exhibitionC:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ExhibitionC> listData(ExhibitionC exhibitionC, HttpServletRequest request, HttpServletResponse response) {
		exhibitionC.setPage(new Page<>(request, response));
		Page<ExhibitionC> page = exhibitionCService.findPage(exhibitionC);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("exhibition:exhibitionC:view")
	@RequestMapping(value = "form")
	public String form(ExhibitionC exhibitionC, Model model) {
		model.addAttribute("exhibitionC", exhibitionC);
		return "modules/exhibition/exhibitionCForm";
	}

	/**
	 * 保存展会
	 */
	@RequiresPermissions("exhibition:exhibitionC:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ExhibitionC exhibitionC) {
		exhibitionCService.save(exhibitionC);
		return renderResult(Global.TRUE, text("保存展会成功！"));
	}
	
	/**
	 * 停用展会
	 */
	@RequiresPermissions("exhibition:exhibitionC:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(ExhibitionC exhibitionC) {
		exhibitionC.setStatus(ExhibitionC.STATUS_DISABLE);
		exhibitionCService.updateStatus(exhibitionC);
		return renderResult(Global.TRUE, text("停用展会成功"));
	}
	
	/**
	 * 启用展会
	 */
	@RequiresPermissions("exhibition:exhibitionC:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(ExhibitionC exhibitionC) {
		exhibitionC.setStatus(ExhibitionC.STATUS_NORMAL);
		exhibitionCService.updateStatus(exhibitionC);
		return renderResult(Global.TRUE, text("启用展会成功"));
	}
	
	/**
	 * 删除展会
	 */
	@RequiresPermissions("exhibition:exhibitionC:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ExhibitionC exhibitionC) {
		exhibitionCService.delete(exhibitionC);
		return renderResult(Global.TRUE, text("删除展会成功！"));
	}
	
}