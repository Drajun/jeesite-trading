package com.jeesite.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jeesite.modules.Application;
import com.jeesite.modules.basic.printer.service.Dictionaries;
import com.jeesite.modules.basic.printer.service.PrinterService;
import com.jeesite.modules.basic.statistics.dao.DataDao;
import com.jeesite.modules.basic.statistics.entity.Data;
import com.jeesite.modules.basic.statistics.service.DataService;

/**
 * 单元测试
 * @author longlou.d@foxmail.com
 * @version 2019-4-10
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
@Rollback(false)
public class UnitTest {
	
	@Autowired
	DataDao dataDao;
	@Autowired
	DataService dataService;
	@Autowired
	Dictionaries dict;
	
	@Test
	//按月份统计付款额
	public void statisticsPayByMonth() throws Exception{
		
		GregorianCalendar calendar = new GregorianCalendar(2019, 03, 1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(calendar.getTime());
		
		Data data = dataDao.statisticsPayByMonth(dateString);
		
		System.out.println(data.getData()+":"+data.getDatetime());

	}
	
	@Test
	//按年份统计销售额
	public void statisticsSalesByYear() throws Exception{
		GregorianCalendar calendar = new GregorianCalendar(2019, 03, 1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(calendar.getTime());
		
		List<Data> dataList = dataDao.statisticsSalesByYear(dateString);
		
		for(Data d : dataList){
			System.out.println(d.getData()+":"+d.getDatetime());
		}
	}
	
	@Test
	//按年份统计收益
	public void statisticsBenefitsByYear() throws Exception{
		GregorianCalendar calendar = new GregorianCalendar(2019, 03, 1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(calendar.getTime());
	    
	    List<Data> dataList = dataDao.statisticsBenefitsByYear(dateString);
		
		for(Data d : dataList){
			System.out.println(d.getData()+":"+d.getDatetime());
		}
	}
	
	@Test
	//按年份统计成本
	public void statisticsCostByYear() throws Exception{
		GregorianCalendar calendar = new GregorianCalendar(2019, 03, 1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(calendar.getTime());
	    
	    List<Data> dataList = dataDao.statisticsCostByYear(dateString);
		
		for(Data d : dataList){
			System.out.println(d.getData()+":"+d.getDatetime());
		}
	}
	
	@Test
	//按年份统计成本+收益
	public void statisticsCostAndBenefitsByYear() throws Exception{
		GregorianCalendar calendar = new GregorianCalendar(2019, 03, 1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(calendar.getTime());
	    
	    List<Data> costDataList =  dataDao.statisticsCostByYear(dateString);
		List<Data> benefitDataList =  dataDao.statisticsBenefitsByYear(dateString);
		
		List<String> costAndBenefits = new ArrayList<String>();
		
		for(int i = 0;i<costDataList.size()&&i<benefitDataList.size();i++){
			costAndBenefits.add(costDataList.get(i).getData()+","+benefitDataList.get(i).getData());
		}
		
		for(String d : costAndBenefits){
			System.out.println(d);
		}
	}
	
	@Test
	public void regressionAnalysis() throws InterruptedException, ExecutionException{
		List<Double> x = new ArrayList<Double>();
		List<Double> y = new ArrayList<Double>();
		
		x.add(1.0);x.add(2.0);x.add(3.0);
		y.add(2.0);y.add(3.0);y.add(4.0);
		
		//相关系数
		Future<Double> correlation =  dataService.correlationCoefficient(x, y);
		System.out.println(String.format("correlation:%.3f", correlation.get()));
		
		//参数a,b
		Future<Double> a = dataService.calculateA(x, y);
		Future<Double> b = dataService.calculateB(x, y,a.get());
		System.out.println(String.format("a:%.3f", a.get()));
		System.out.println(String.format("b:%.3f", b.get()));
		
		//判断系数
		Future<Double> determina =  dataService.determinationCoefficient(x, y,a.get(),b.get());
		System.out.println(String.format("determina:%.3f", determina.get()));
	}
	
	@Test
	//获取字典数据
	public void getDictionaries(){
		dict.init();
		System.out.println(dict.getTransportWay("1"));
	}
	
	@Test
	public void getStringNull() {
		String s = null;
		StringBuffer sb = new StringBuffer();
		sb.append(s);
		System.out.println(sb.toString());
	}
}
