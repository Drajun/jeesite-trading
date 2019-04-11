package com.jeesite.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.jeesite.modules.Application;
import com.jeesite.modules.basic.statistics.dao.DataDao;
import com.jeesite.modules.basic.statistics.entity.Data;

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
}
