package com.dfhc.bus.gate.rest;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.dfhc.util.HttpClientUtil;

public class GateRestControllerTest {
	/**
	 * 门岗对车辆出入厂操作
	 * @throws Exception
	 */
	@Test
	public void testGateOperation() throws Exception {
		String url="http://1u52s05192.51mypc.cn:9081/yulinchemical/api/gate/gateOperation";
//		String url="http://127.0.0.1:8080/yulinchemical/api/gate/gateOperation";
		Map<String, String> bodyData = new HashMap<String,String>();
		
		bodyData.put("truckNumber", "京A12761");
		bodyData.put("operationCode", "03");
		System.out.println(HttpClientUtil.post(url, bodyData));
	}
	/**
	 * 车辆是否可以出入厂
	 * @throws Exception 
	 */
	@Test
	public void testWhetherGoOutAndComeIn() throws Exception {
		String url="http://127.0.0.1:8080/yulinchemical/api/gate/whetherGoOutAndComeIn";
		Map<String, String> bodyData = new HashMap<String,String>();
		
		bodyData.put("truckNumber", "京A12761");
		bodyData.put("type", "1");
		System.out.println(HttpClientUtil.post(url, bodyData));
	}

}
