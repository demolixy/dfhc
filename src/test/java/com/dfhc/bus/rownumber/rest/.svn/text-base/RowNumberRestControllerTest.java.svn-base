package com.dfhc.bus.rownumber.rest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.dfhc.util.HttpClientUtil;

public class RowNumberRestControllerTest {

	@Test
	//车辆队列接口
	public void testGetCarSequenceList() throws Exception {
		String url="http://1u52s05192.51mypc.cn:9081/yulinchemical/api/rownumber/getCarSequenceList";
//		String url="http://localhost:8080/yulinchemical/api/rownumber/getCarSequenceList";
		Map<String, String> bodyData = new HashMap<String,String>();
		
		bodyData.put("billStatus", "04");
		System.out.println(HttpClientUtil.post(url, bodyData));
	}
	@Test
	//签到接口
	public void testSign() throws Exception {
//		String url="http://1u52s05192.51mypc.cn:9082/yulinchemical/api/rownumber/sign";
		String url="http://localhost:8080/yulinchemical/api/rownumber/sign?billid=FCPMTBE20170325002";
		Map<String, String> bodyData = new HashMap<String,String>();
		
//		bodyData.put("billid", "FCPMTBE20170325002");
		System.out.println(HttpClientUtil.get(url));
	}

}
