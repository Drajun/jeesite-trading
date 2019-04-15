package com.jeesite.modules.basic.home.web;


import java.util.Date;
import java.util.concurrent.Future;

import org.hyperic.sigar.FileSystemMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeesite.common.shiro.session.SessionDAO;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.basic.statistics.entity.Data;
import com.jeesite.modules.basic.statistics.service.DataService;

/**
 * 首页Controller
 * @author longlou.d@foxmail.com
 * @version 2019-4-10
 */
@Controller
@RequestMapping(value = "${adminPath}/basic/home")
public class HomeController extends BaseController {
	
	@Autowired
	private SessionDAO sessionDAO;
	
	@Autowired
	private DataService dataService;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "index")
	public String index(Model model){
		//在线用户数
		int activeUser = sessionDAO.getActiveSessions(true, true).size();
		model.addAttribute("activeUser", activeUser);
		
		try{
			//总营收
			Future<Data> reData = dataService.statisticsReByMonth(new Date());
			if(reData.isDone())
				model.addAttribute("totalRe",reData.get().getData());
			else
				model.addAttribute("totalRe", "正在计算");
			//总成本
			Future<Data> payData = dataService.statisticsPayByMonth(new Date());
			if(payData.isDone())
				model.addAttribute("totalPay",payData.get().getData());
			else
				model.addAttribute("totalPay", "正在计算");
			
			//总利润
			if(reData.isDone()&&payData.isDone())
				model.addAttribute("totalBenefits",reData.get().getData()-payData.get().getData());
			else
				model.addAttribute("totalBenefits","正在计算");
			
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("totalPay", "正在计算");
			model.addAttribute("totalRe", "正在计算");
			model.addAttribute("totalBenefits","正在计算");
		}
		
		return "/basic/home/index";
	}
	
}
