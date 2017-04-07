package com.dfhc.bus.weighingbill.rest;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.dfhc.util.HttpClientUtil;

public class WeighingBillRestControllerTest {

	/**
	 * 二次过磅
	 * @throws Exception 
	 */
	@Test
	public void testTwoWeigh() throws Exception {
//		String url="http://1u52s05192.51mypc.cn:9081/yulinchemical/api/weighingbill/twoWeigh";
		String url="http://localhost:8080/yulinchemical/api/weighingbill/twoWeigh";
		Map<String, String> bodyData = new HashMap<String,String>();
		
		bodyData.put("billid", "FCPMTBE20170329003");
		bodyData.put("fistTime", "2017-03-29 10:58:00");		
		bodyData.put("firstWeight", "6");
		bodyData.put("firstClerk", "wx11");
		bodyData.put("truckNumber", "陕A15263");
		bodyData.put("secondTime", "2017-03-29 10:59:02");		
		bodyData.put("secondWeight", "26");
		bodyData.put("secondClerk", "wx11");
		bodyData.put("weight", "20");
		System.out.println(HttpClientUtil.post(url, bodyData));
	}

}
