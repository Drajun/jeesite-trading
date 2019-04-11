package com.jeesite.modules.basic.statistics.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.basic.statistics.entity.Data;
import com.jeesite.modules.basic.statistics.service.DataService;

/**
 * 数据统计Controller
 * @author longlou.d@foxmail.com
 * @version 2019-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/Data/Data")
public class DataController extends BaseController{

	@Autowired
	private DataService dataService;
	
	/**
	 * 成本收益页面
	 */
	@RequiresPermissions("Data:Data:view")
	@RequestMapping(value = {"costAndBenefitsChart", ""})
	public String costAndBenefitsChart(){
		return "statistics/costAndBenefits/costAndBenefitsChart";
	}
	
	/**
	 * 销售统计数据
	 */
	@RequiresPermissions("Data:Data:view")
	@RequestMapping(value = {"salesAmountByYearData", ""})
	@ResponseBody
	public Model salesAmountByYearData(Model model, String date){
		//按年份统计
		String year = date.split("-")[0];
		GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(year), 1, 1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(calendar.getTime());
		List<Data> dataList =  dataService.statisticsSalesByYear(dateString);
		
		List<String> name = new ArrayList<String>();
		List<Double> value = new ArrayList<Double>();
		
		for(Data d : dataList){
			name.add(d.getDatetime()+"月");
			value.add(d.getData());
		}
		
		model.addAttribute("year", year);
		model.addAttribute("name", name);
		model.addAttribute("value", value);
		return model;
	}
	
	/**
	 * 销售额与成本统计数据
	 */
	@RequiresPermissions("Data:Data:view")
	@RequestMapping(value = {"costAndBenefitsByYearData", ""})
	@ResponseBody
	public Model costAndBenefitsByYearData(Model model, String date){
		//按年份统计
		String year = date.split("-")[0];
		GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(year), 1, 1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String dateString = formatter.format(calendar.getTime());
		
		//获取成本与收益值
		List<Data> costList =  dataService.statisticsCostByYear(dateString);
		List<Data> benefitList =  dataService.statisticsBenefitsByYear(dateString);
		
		//获取实际值
		List<Double> costDataList = new ArrayList<Double>();
		List<Double> benefitDataList = new ArrayList<Double>();
		
		//封装数据
		List<List<Double>> costAndBenefits = new ArrayList<List<Double>>();
		for(int i = 0;i<costList.size()&&i<benefitList.size();i++){
			List<Double> temp = new ArrayList<Double>();
			temp.add(costList.get(i).getData());
			temp.add(benefitList.get(i).getData());
			temp.add(Double.parseDouble(costList.get(i).getDatetime()));
			costAndBenefits.add(temp);
			
			costDataList.add(costList.get(i).getData());
			benefitDataList.add(benefitList.get(i).getData());
		}

		//拟合度
		Future<Double> determinationCoefficient;
		//相关系数
		Future<Double> correlationCoefficient;
		//回归方程参数a
		Future<Double> regressionA;
		//回归方程参数b
		Future<Double> regressionB;
		try {
			determinationCoefficient = dataService.determinationCoefficient(costDataList, benefitDataList);
			correlationCoefficient = dataService.correlationCoefficient(costDataList, benefitDataList);
			regressionA=dataService.calculateA(costDataList, benefitDataList);
			regressionB=dataService.calculateB(costDataList, benefitDataList,regressionA.get());
			
			model.addAttribute("determinationCoefficient", determinationCoefficient.get());
			model.addAttribute("correlationCoefficient", correlationCoefficient.get());
			model.addAttribute("regressionA", regressionA.get());
			model.addAttribute("regressionB", regressionB.get());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("false", renderResult(Global.FALSE, text("计算出错")));
			return model;
		}
		
		
		model.addAttribute("year", year);
		model.addAttribute("costAndBenefits", costAndBenefits);
		return model;
	}
}
