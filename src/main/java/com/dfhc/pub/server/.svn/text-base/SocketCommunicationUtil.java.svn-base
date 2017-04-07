package com.dfhc.pub.server;
import java.net.Socket;
import java.util.Map;

import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.tools.support.log.RmLogHelper;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.dfhc.ISystemConstant;
import com.dfhc.util.PatternUtil;
import com.dfhc.util.StringHelper;

public class SocketCommunicationUtil {
	private static final Logger logSocket = RmLogHelper.getLogger("rmSocket");
	/**
	 * 从客户端发过来的数据中获取真正的json数据  并验证是否符合基本约定
	 * @param str 客户端发来的数据
	 * @param variable
	 * @return
	 */
	public static Map<String, Object> getCommuniInfo(String str, Map<String, Object> variable){
		
		if(StringHelper.isEmpty(str)){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.DICTIONARY_SOCKET_SIGN_FAIL);
			
			return variable;
		}
		
		Map signMap = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_SIGN);
		
		//前后缀长度
		Integer preAndSufFixLen = Integer.valueOf(signMap.get(ISystemConstant.DICTIONARY_SOCKET_SIGN_05).toString());
		//数据长度位数
		Integer figuresLen = Integer.valueOf(signMap.get(ISystemConstant.DICTIONARY_SOCKET_SIGN_06).toString());
		
		if(str.length() < (preAndSufFixLen*2)+figuresLen){
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.DICTIONARY_SOCKET_SIGN_FAIL);
			
			return variable;
		}
		
		//前缀
		String prefix = str.substring(0, preAndSufFixLen);
		
		//验证前缀
		if(!signMap.get(ISystemConstant.DICTIONARY_SOCKET_SIGN_01).equals(prefix)){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.DICTIONARY_SOCKET_SIGN_FAIL);
			
			return variable;
		}
		//DDICTIONARY_SOCKET_SIGN_06
		
		String jsonLenStr = str.substring(preAndSufFixLen, preAndSufFixLen+figuresLen);
		
		//验证json长度
		if(!PatternUtil.checkNumber2(jsonLenStr, "[0-9]{" + signMap.get(ISystemConstant.DICTIONARY_SOCKET_SIGN_06) + "}")){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.DICTIONARY_SOCKET_SIGN_FAIL);
			
			return variable;
		}
		
		
		//json数据长度
		Integer dataLen = Integer.valueOf(jsonLenStr);
		
		//真正的json数据
		String jsonStr = str.substring(preAndSufFixLen+figuresLen, preAndSufFixLen+figuresLen+dataLen);
		
		String suffix = str.substring(preAndSufFixLen+figuresLen+dataLen);
		
		//验证后缀
		String strFix = (String) signMap.get(ISystemConstant.DICTIONARY_SOCKET_SIGN_02);
		System.out.println(strFix.equals(suffix));
		if(!strFix.equals(suffix)){
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.DICTIONARY_SOCKET_SIGN_FAIL);
			
			return variable;
		}
		
		try {
			JSONObject json = JSONObject.parseObject(jsonStr);
			
			variable.put(ISystemConstant.DICTIONARY_SOCKET_JSON_DATA_KEY, json);
			return variable;
		} catch (Exception e) {
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.DICTIONARY_SOCKET_SIGN_FAIL);
			
			return variable;
		}
		
		
	}
	
	
	public static void close(long logGuid,Socket socket){
		try {
			if(socket != null){
				try{
					socket.shutdownInput();
					logSocket.info("日志guid:"+logGuid+" 调用socket.shutdownInput()成功!");
				}catch(Exception e){
					logSocket.info("日志guid:"+logGuid+" 调用socket.shutdownInput()失败: "+StringHelper.exceptionToString(e));
				}
				try{
					socket.shutdownOutput();
					logSocket.info("日志guid:"+logGuid+" 调用socket.shutdownOutput()成功!");
				}catch(Exception e){
					logSocket.info("日志guid:"+logGuid+" 调用socket.shutdownOutput()失败:"+StringHelper.exceptionToString(e));
				}
				try{
				  socket.close();
				  logSocket.info("日志guid:"+logGuid+" 调用socket.close()成功!");
				}catch(Exception e){
					logSocket.info("日志guid:"+logGuid+" 调用socket.close()失败:"+StringHelper.exceptionToString(e));
				}
			}		
		}finally{
			socket = null;
		}
	}
	
	public static boolean isClose(Socket socket){
		
		if(socket == null){
			return false;
		}
		//isConnected方法所判断的并不是Socket对象的当前连接状态，
		//而是Socket对象是否曾经连接成功过，如果成功连接过，即使现在isClose返回true，isConnected仍然返回true
		if(!socket.isClosed() && socket.isConnected()){
			return true;
		}
		
		return false;
		
	}
	
	/**
	 *  包装需要传输的数据
	 * @param jsonStr
	 * @return
	 */
	public static String packingData(String jsonStr){
		StringBuilder sb = new StringBuilder();
		
		Map map = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_SIGN);
		sb.append(map.get(ISystemConstant.DICTIONARY_SOCKET_SIGN_01));
		sb.append(formatDataLen(jsonStr));
		sb.append(jsonStr);
		sb.append(map.get(ISystemConstant.DICTIONARY_SOCKET_SIGN_02));
		return sb.toString();
	}
	
	/**
	 * 格式化数据的长度值
	 * @param jsonStr
	 * @return
	 */
	public static String formatDataLen(String jsonStr){
		Integer dataLen = Integer.valueOf(RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_SIGN, ISystemConstant.DICTIONARY_SOCKET_SIGN_06));
		return String.format("%0" + dataLen + "d", jsonStr.length());
		
	}
	

	/**
	 * 根据首尾标识 判断是否读完
	 * @param data
	 * @return
	 */
	public static boolean isReadOver(String data){
		if((data.indexOf(RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_SIGN, ISystemConstant.DICTIONARY_SOCKET_SIGN_01)) != -1) && !(data.indexOf(RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_SIGN, ISystemConstant.DICTIONARY_SOCKET_SIGN_02)) == -1)){
			return true;
		}
		return false;
	}
	/**
	 *  包装心跳包
	 * @param jsonStr
	 * @return
	 */
	public static String packingHeartbeatData(){
		StringBuilder sb = new StringBuilder();		
		Map map = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_SIGN);
		sb.append(map.get(ISystemConstant.DICTIONARY_SOCKET_SIGN_01));		
		sb.append(map.get(ISystemConstant.DICTIONARY_SOCKET_SIGN_02));
		return sb.toString();
	}	
}
