package com.dfhc.pub.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
/**
 * 磅房模拟客户端
 * @author longsebo
 *
 */
public class Client2 {

	static Socket server;

	public static void main(String[] args) throws Exception {
//		CountDownLatch downLatch = new CountDownLatch(1);
		server = new Socket("1u52s05192.51mypc.cn", 8888);

		InputStream inputStream = server.getInputStream();

		OutputStream outputStream = server.getOutputStream();
		//byte[] data = new byte[1024];
		Map<String, Object> map = new HashMap<String, Object>(0);
		map.put("operation", "WEIGHING_01");
		String str = "d359392706dbecc596783ec3da1785670000027" + JSONObject.toJSONString(map) + "af92d2a9be3c6f82d86960c318c1a081";

				outputStream.write(str.getBytes("utf-8"));
				
				while (true) {
					
					int read = inputStream.available();
					if(read==0){
						Thread.sleep(1000);
						continue;
					}
					
					byte[] b = new byte[read];
					inputStream.read(b, 0, read);
					//System.arraycopy(data, 0, b, 0, read);
					System.out.println(new String(b, "UTF-8"));
				}
				

//			downLatch.await();
	}
}