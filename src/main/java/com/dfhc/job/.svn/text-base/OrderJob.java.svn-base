package com.dfhc.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quickbundle.base.beans.factory.RmBeanFactory;

import com.dfhc.bus.order.service.OrderService;

public class OrderJob implements  Job{

	
	private  OrderService  orderService;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		// 账户变动服务
		orderService = (OrderService) RmBeanFactory.getBean("com.dfhc.bus.order.service.OrderService");

		orderService.doQu();
		
		
	}
}
