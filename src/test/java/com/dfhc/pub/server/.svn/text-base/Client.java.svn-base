package com.dfhc.pub.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
/**
 * 门岗模拟客户端
 * @author longsebo
 *
 */
public class Client {

	static Socket server;

	public static void main(String[] args) throws Exception {
//		CountDownLatch downLatch = new CountDownLatch(1);
		server = new Socket("1u52s05192.51mypc.cn", 8888);
//		server = new Socket("127.0.0.1", 7777);

		InputStream in = server.getInputStream();

		OutputStream outputStream = server.getOutputStream();
		byte[] data = null;
		Map<String, Object> map = new HashMap<String, Object>(0);
		map.put("operation", "GATE_01");
		String str = "d359392706dbecc596783ec3da1785670000023" + JSONObject.toJSONString(map) + "af92d2a9be3c6f82d86960c318c1a081";
//		String str = "sadas";

		outputStream.write(str.getBytes("utf-8"));
		
		while (true) {
			
			int read = in.available();
			if(read==0){
				Thread.sleep(1000);
				continue;
			}else{
				byte[] b = new byte[read];
				in.read(b, 0, read);
				//System.arraycopy(data, 0, b, 0, read);
				System.out.println(new String(b, "UTF-8"));
			}
			
			
		}	

//			downLatch.await();
	}
}