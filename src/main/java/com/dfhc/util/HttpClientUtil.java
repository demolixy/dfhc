/**
 * 
 */
package com.dfhc.util;

import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.quickbundle.project.RmProjectHelper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dfhc.ISystemConstant;

/**
 * HttpClient工具类
 * 
 * @author dfhc
 */
public class HttpClientUtil {
	/**
	 * 使用post 提交到url
	 * 
	 * @param url  目标url
	 * @param bodyData 表体数据
	 * @throws Exception
	 */
	public static String post(String url, Map<String, String> bodyData)
			throws Exception {
		// 定义httpClient的实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String retVal;
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			Iterator<String> itKeys = bodyData.keySet().iterator();
			while (itKeys.hasNext()) {
				String key = itKeys.next();
				String val = bodyData.get(key);
				nvps.add(new BasicNameValuePair(key, val));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,
					ISystemConstant.ENCODE_UTF_8));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);
			
			try {
				RmProjectHelper.logError("系统调试",response2.getStatusLine());
				HttpEntity entity2 = (HttpEntity) response2.getEntity();
				if(entity2!=null){
					retVal = EntityUtils.toString(entity2, "UTF-8");
				}else{
					retVal = null;
				}
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume( entity2);
				return retVal;
			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}

	}
	
	
	public  static String get(String url) throws IOException{
		
		String returnVal="";
		// 定义httpClient的实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet=new HttpGet(url);

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
			httpGet.setConfig(requestConfig);
			try {
				CloseableHttpResponse response2 = httpclient.execute(httpGet);//执行请求
				RmProjectHelper.logError("response2:", response2);
				HttpEntity entity2 = (HttpEntity) response2.getEntity();
				if(entity2!=null){
					
					returnVal = EntityUtils.toString(entity2, "UTF-8");
					
				}else{
					returnVal = null;
				}
				
			} catch (ClientProtocolException e) {
				
				RmProjectHelper.logError("retVal", e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				RmProjectHelper.logError("retVal", e.getMessage());
				
			}finally{
				
				if(httpclient!=null){
					
					httpclient.close();
				}
				
			}
			
		
		return returnVal;
		
	}
	
	public static Map<String, Object> parseJSON2Map(String bizData) {
		Map<String, Object> ret = new HashMap<String, Object>();
		
    	try{
    		JSONObject bizDataJson = JSONObject.parseObject(bizData);
    		for(Object key:bizDataJson.keySet()){
    			Object value = bizDataJson.get(key);
    			if(value instanceof JSONArray){
    				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();  
                    Iterator<Object> it = ((JSONArray) value).iterator();

                    while(it.hasNext()){  
                        JSONObject json2 = (JSONObject)it.next();  
                        list.add(parseJSON2Map(json2.toString()));  
                    }  
                    ret.put(String.valueOf(key),list);  
    			}else{
    				ret.put(String.valueOf(key), String.valueOf(value));
    			}
    		}
    	}catch(Exception e){
    		RmProjectHelper.logError("系统异常", e);
    	}
		return ret;
	}
	
	
	/**
	 * 使用post 提交到url
	 * 
	 * @param url  目标url
	 * @param postDataXML 表体数据
	 * @throws Exception
	 */
	public static String post(String url, String postDataXML)
			throws Exception {
		// 定义httpClient的实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String retVal;
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
		    StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
	        httpPost.addHeader("Content-Type", "text/xml");
	        httpPost.setEntity(postEntity);
			CloseableHttpResponse response2 = httpclient.execute(httpPost);
			
			try {
				RmProjectHelper.logError("系统调试",response2.getStatusLine());
				HttpEntity entity2 = (HttpEntity) response2.getEntity();
				if(entity2!=null){
					retVal = EntityUtils.toString(entity2, "UTF-8");
				}else{
					retVal = null;
				}
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume( entity2);
				return retVal;
			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}

	}
	
	
}
