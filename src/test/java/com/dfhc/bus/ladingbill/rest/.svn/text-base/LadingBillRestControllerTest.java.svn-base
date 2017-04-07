package com.dfhc.bus.ladingbill.rest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.dfhc.util.HttpClientUtil;

public class LadingBillRestControllerTest {

	/**
	 * 获取提货单列表信息
	 * @throws Exception
	 */
	@Test
	public void testGetLadingBillList() throws Exception {
//		String url="http://1u52s05192.51mypc.cn:9081/yulinchemical/api/ladingbill/getLadingBillList";
		String url="http://localhost:8080/yulinchemical/api/ladingbill/getLadingBillList";
		Map<String, String> bodyData = new HashMap<String,String>();
		
		bodyData.put("truckNumber", "京A14831");
//		bodyData.put("billStatus", "'04','05'");		
		bodyData.put("billStatus", "04");
		//bodyData.put("startDate", "20170101");
		System.out.println(HttpClientUtil.post(url, bodyData));
	}
	/**
	 * 磅端设置安检通过
	 * @throws Exception
	 */
	@Test
	public void testUpdateLadingPass() throws Exception {
		String url="http://localhost:8080/yulinchemical/api/ladingbill/updateLadingPass";
		Map<String, String> bodyData = new HashMap<String,String>();
		
		bodyData.put("billid", "FCPBW20170316032");
		bodyData.put("status", "1");		
		System.out.println(HttpClientUtil.post(url, bodyData));
	}
	/**
	 * 作废车辆上传
	 * @throws Exception
	 */
	@Test
	public void testCancelLadingBill() throws Exception {
		String url="http://localhost:8080/yulinchemical/api/ladingbill/cancelLadingBill";
		Map<String, String> bodyData = new HashMap<String,String>();
		
		bodyData.put("billid", "FCPMTBE20170324011");	
		System.out.println(HttpClientUtil.post(url, bodyData));
	}
	/**
	 * 多个状态获取提货单列表信息
	 * @throws Exception
	 */
	@Test
	public void testGetLadingBillListForSign() throws Exception {
//		String url="http://localhost:8080/yulinchemical/api/ladingbill/getLadingBillListForSign";
		String url="http://localhost:8080/yulinchemical/api/ladingbill/getLadingBillListForSign";
		Map<String, String> bodyData = new HashMap<String,String>();
		
		bodyData.put("phoneNumber", "13800001111");
		bodyData.put("billStatus", "'01','02','03','11'");
		bodyData.put("startDate", "20170323");
		bodyData.put("endDate", "20170323");
//		bodyData.put("billid", "FCPDX20170322001");
//		bodyData.put("billStatus","03");
		System.out.println(HttpClientUtil.post(url, bodyData));
	}	
}
