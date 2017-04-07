package com.dfhc.pub.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 仓库模拟客户端
 * 
 * @author longsebo
 * 
 */
public class Client4 {

	static Socket server;

	public static void main(String[] args) throws Exception {
		// CountDownLatch downLatch = new CountDownLatch(1);
		//server = new Socket("1u52s05192.51mypc.cn", 8888);
		server = new Socket("127.0.0.1", 7777);
		InputStream inputStream = server.getInputStream();

		OutputStream outputStream = server.getOutputStream();
		//byte[] data = new byte[1024];
		Map<String, Object> map = new HashMap<String, Object>(0);
		map.put("operation", "WAREHOUSE_02");
		
		String jsonString = JSONObject.toJSONString(map);
		int len = jsonString.length();
		String jsonLen = String.format("%07d",len);
		String str = "d359392706dbecc596783ec3da178567"+jsonLen + jsonString + "af92d2a9be3c6f82d86960c318c1a081";
		outputStream.write(str.getBytes("utf-8"));

		while (true) {

			int read = inputStream.available();
			if (read == 0) {
				Thread.sleep(1000);
				continue;
			}

			byte[] b = new byte[read];
			inputStream.read(b, 0, read);
			//System.arraycopy(data, 0, b, 0, read);
			System.out.println(new String(b, "UTF-8"));
		}

		// downLatch.await();
	}
}