package com.dfhc.pub.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.tools.support.log.RmLogHelper;
import org.slf4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.dfhc.ISystemConstant;
import com.dfhc.util.ConvertHelper;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;

public class SocketHandleThread implements Runnable {

	private static final Logger logLogin = RmLogHelper.getLogger("rmSocket");
	
	private Socket clientConnect;
	
	private InputStream in = null;
	
	private OutputStream out = null;
	
	/**
	 * 日志记录guid
	 */
	private long logGuid;
	
	private String charset = null;
	
	//创建带延迟的线程池   TODO 数量测试完修改成可配置
//	private ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);
	
	public SocketHandleThread(Socket clientConnect){
		this.clientConnect = clientConnect;
	}
	
	
	@Override
	public void run() {
		try {
			logGuid = System.currentTimeMillis();
			charset = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_SIGN, ISystemConstant.DICTIONARY_SOCKET_SIGN_04);
			in = clientConnect.getInputStream();

			
//			clientConnect.setSoLinger(true, 0);
			//设置超时时间  超过则抛出SocketTimeoutException
//			clientConnect.setSoTimeout(Integer.valueOf(RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_SIGN, ISystemConstant.DICTIONARY_SOCKET_SIGN_07)));
			
			int size = 0;
			byte[] data = new byte[1024];
			
			byte[] temp = null;
			String readData = null;
			long timeOut = Long.valueOf(RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_SIGN, ISystemConstant.DICTIONARY_SOCKET_SIGN_07));
			long preTime = System.currentTimeMillis();
			while (true) {
				
				if((size = in.available()) > 0){
					in.read(data, 0, size);
					
					if(temp == null){
						temp = new byte[size];
						System.arraycopy(data, 0, temp, 0, size);
					}else{
						System.arraycopy(data, 0, temp, temp.length, size);
					}
					readData = new String(temp, charset);
					//读完跳出循环
					if(SocketCommunicationUtil.isReadOver(readData)){
						break;
					}
					
				}else{
					//超时退出
					if(((System.currentTimeMillis()-preTime)/1000) > timeOut){
						break;
					}else{
						if(temp == null){
							try {
								Thread.sleep(100);
								continue;
							} catch (InterruptedException e) {
								logLogin.info("日志guid:"+logGuid+" "+StringHelper.exceptionToString(e));
								return;
							}
						}
						
						readData = new String(temp, charset);
						//读完跳出循环
						if(SocketCommunicationUtil.isReadOver(readData)){
							break;
						}
					}
					
				}
				
			}
			Map<String, Object> variable = new HashMap<String, Object>();
			
			SocketCommunicationUtil.getCommuniInfo(readData, variable);
			
			logLogin.info("日志guid:"+logGuid+" "+"接到的数据：" + readData);
			
			//如果数据不符合约定 则断开连接
			if(ISystemConstant.DICTIONARY_SOCKET_SIGN_FAIL.equals(variable.get(ISystemConstant.AJAX_STATUS))){
				SocketCommunicationUtil.close(logGuid,clientConnect);
				logLogin.info("日志guid:"+logGuid+" "+"数据不合法断开：" + readData);
				return;
			}
			
			JSONObject json = (JSONObject) variable.get(ISystemConstant.DICTIONARY_SOCKET_JSON_DATA_KEY);
			//操作标识 哪块大屏
			String operation = json.getString(ISystemConstant.DICTIONARY_SOCKET_OPERATION);
			
			Map map = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_OPERATION);
			//门岗列表
			List gateList = toDevList(map,ISystemConstant.DICTIONARY_SOCKET_01);
			//磅房列表
			List poundList = toDevList(map,ISystemConstant.DICTIONARY_SOCKET_02);
			//仓库列表
			List warehouseList = toDevList(map,ISystemConstant.DICTIONARY_SOCKET_03);
			String billStatus = null;
			//检查设备id是否已经注册
			SocketCacheVo socketCacheVo = SocketQueue.getInstance().removeByClientId(logGuid, operation);
			if(socketCacheVo!=null){				 
				logLogin.info("日志guid:"+logGuid+",发现client id重复， 关闭 client id:"+socketCacheVo.getClientId()+"，旧socket:"+socketCacheVo.toString());
				//关闭旧连接				
				SocketCommunicationUtil.close(logGuid,socketCacheVo.getSocket());
			}
			if(gateList.indexOf(operation)>=0){//门岗大屏				
				billStatus = ISystemConstant.DICTIONARY_LADING_BILL_STATUS_02;
			}else if(poundList.indexOf(operation)>=0){//磅房大屏
				billStatus = ISystemConstant.DICTIONARY_LADING_BILL_STATUS_04;
			}else if(warehouseList.indexOf(operation)>=0){//仓库大屏
				billStatus = ISystemConstant.DICTIONARY_LADING_BILL_STATUS_05;
			}else{
				SocketCommunicationUtil.close(logGuid,clientConnect);
				logLogin.info("日志guid:"+logGuid+" "+"无效设备标识:"+operation);
				return;
			}
			
			variable.clear();
			write(variable, operation, billStatus);
		
		}catch (UnsupportedEncodingException e) {
			SocketCommunicationUtil.close(logGuid,clientConnect);
			logLogin.info("日志guid:"+logGuid+" "+StringHelper.exceptionToString(e));			
		} catch (IOException e) {
			SocketCommunicationUtil.close(logGuid,clientConnect);
			logLogin.info("日志guid:"+logGuid+" "+StringHelper.exceptionToString(e));
		}

	}

	/**
	 * 转换配置参数为设备列表
	 * @param map
	 * @param key
	 */
	private List toDevList(Map map, String key) {
		String devstr[] = StringHelper.splitString((String)map.get(key),",");
		return ConvertHelper.toList(devstr);
	}
	/**
	 * 写入应答数据
	 * @param variable
	 * @param operation
	 * @param billStatus
	 */
	public void write(Map<String, Object> variable, String operation, String billStatus){
		try {
			out = clientConnect.getOutputStream();
			variable.clear();
			
			variable.put(ISystemConstant.AJAX_STATUS, ISystemConstant.DICTIONARY_SOCKET_SIGN_SUCCESS);
			
			out.write(SocketCommunicationUtil.packingData(JSONObject.toJSONString(variable)).getBytes(charset));
			out.flush();
			
			logLogin.info("日志guid:"+logGuid+" 设备id:"+operation + "应答完毕！");
			
			SocketCacheVo vo = new SocketCacheVo();
			
			vo.setClient(clientConnect);
			
			vo.setClientId(operation);
			
			vo.setIn(in);
			
			vo.setOut(out);
			//设置最后发送心跳包时间
			vo.setLastHeartbeatTime(DateUtil.getNowDate());
			SocketQueue.getInstance().put(logGuid,vo);
			//logLogin.info("日志guid:"+logGuid+" "+operation + "放入队列！");
			
			//创建线程 执行定时任务 
		} catch (UnsupportedEncodingException e) {
			SocketCommunicationUtil.close(logGuid,clientConnect);
			logLogin.info("日志guid:"+logGuid+" "+StringHelper.exceptionToString(e));
		} catch (IOException e) {
			SocketCommunicationUtil.close(logGuid,clientConnect);
			logLogin.info("日志guid:"+logGuid+" "+StringHelper.exceptionToString(e));
		}
		
		
	}
	
}
