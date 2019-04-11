package com.jeesite.modules.basic.statistics.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.basic.statistics.dao.DataDao;
import com.jeesite.modules.basic.statistics.entity.Data;

/**
 * 数据统计Service
 * @author longlou.d@foxmail.com
 * @version 2019-03-12
 */
@Service
@Transactional(readOnly=true)
public class DataService extends CrudService<DataDao, Data> {
	@Autowired
	private DataDao dataDao;
	
	/**
	 * 根据月份统计付款额
	 * @param month
	 */
	public Data statisticsPayByMonth(String month){
		return dataDao.statisticsPayByMonth(month);
	}
	
	/**
	 * 根据年份统计销售额
	 * @param year
	 */
	public List<Data> statisticsSalesByYear(String year){
		return dataDao.statisticsSalesByYear(year);
	}
	
	/**
	 * 根据年份统计成本
	 * @param year
	 */
	public List<Data> statisticsCostByYear(String year){
		return dataDao.statisticsCostByYear(year);
	}
	
	/**
	 * 根据年份统计收益
	 * @param year
	 */
	public List<Data> statisticsBenefitsByYear(String year){
		return dataDao.statisticsBenefitsByYear(year);
	}
	
	/**
	 * 相关系数计算
	 */
	@Async("taskExecutor")
	public Future<Double> correlationCoefficient(List<Double> x,List<Double> y){
		double Ex=0,Ey=0,Exy=0,Cov=0,Ey2=0,Ex2=0,r=0;
		for(int i=0;i<x.size()&&i<y.size();i++){
			Exy+=x.get(i)*y.get(i);
			Ex2+=x.get(i)*x.get(i);
			Ey2+=y.get(i)*y.get(i);
		}
		
		Ex=average(x);
		Ey=average(y);
		Exy=Exy/x.size();
		Ex2=Ex2/x.size();
		Ey2=Ey2/y.size();
		Cov=Exy-Ex*Ey;
		
		r=Cov/(Math.sqrt(Ex2)*Math.sqrt(Ey2));

		return new AsyncResult<Double>(r);
	}
	
	/**
	 * 判定系数(拟合度)计算
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@Async("taskExecutor")
	public Future<Double> determinationCoefficient(List<Double> x,List<Double> y) throws InterruptedException, ExecutionException{
		double SSE=0,SST=0;
		double a = calculateA(x,y).get();
		double b = calculateB(x,y,a).get();
		double averageY = average(y);
		
		//计算SSE、SSR
		for(int i=0;i<x.size()&&i<y.size();i++){
			SSE+=Math.pow(y.get(i)-(a*x.get(i)+b),2);
			SST+=y.get(i)-averageY;
		}
		
		return new AsyncResult<Double>((1-SSE)/SST);
	}
	
	/**
	 * 计算参数a
	 */
	@Async("taskExecutor")
	public Future<Double> calculateA(List<Double> x,List<Double> y){
		double upper=0,lower=0;
		double averageX=average(x);
		double averageY=average(y);
		
		for(int i=0;i<x.size()&&i<y.size();i++){
			upper+=(x.get(i)-averageX)*(y.get(i)-averageY);
			lower+=Math.pow(x.get(i)-averageX,2);
		}
		
		return new AsyncResult<Double>(upper/lower);
	}
	
	/**
	 * 计算参数b
	 */
	@Async("taskExecutor")
	public Future<Double> calculateB(List<Double> x,List<Double> y,double a){
		return new AsyncResult<Double>(average(y)-a*average(x));
	}
	
	/**
	 * 计算平均数
	 */
	public double average(List<Double> x){
		double averageX=0;
		for(int i=0;i<x.size();i++){
			averageX+=x.get(i);
		}
		return averageX/x.size();
	}
	
	
}
