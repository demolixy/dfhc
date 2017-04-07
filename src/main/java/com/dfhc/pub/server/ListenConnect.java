package com.dfhc.pub.server;

import java.io.IOException;
import java.net.ServerSocket;

import org.quickbundle.project.RmGlobalReference;
import org.quickbundle.tools.support.log.RmLogHelper;
import org.slf4j.Logger;

import com.dfhc.ISystemConstant;
import com.dfhc.util.ConvertHelper;
import com.dfhc.util.StringHelper;

public class ListenConnect {
	private static final Logger logSocket = RmLogHelper.getLogger("rmSocket");
	private ListenConnect(){}
	
	private static class Single{
		private static ListenConnect CONNECT = new ListenConnect();
	}
	
	public static ListenConnect getInstance(){
		return Single.CONNECT;
	}
	
	public void listen(){
		ServerSocket server = null;
		try {
			//获取侦听端口参数
			String listenPort = RmGlobalReference.get(ISystemConstant.DICTIONARY_SOCKET_SIGN, ISystemConstant.DICTIONARY_SOCKET_SIGN_09);
			int port =0;
			try {
				port = ConvertHelper.toInt(listenPort);
			} catch (Exception e) {
				e.printStackTrace();
				//使用默认端口
				port = ISystemConstant.DEFAULT_PUSH_LISTEN_PORT;
			}
			server = new ServerSocket(port);
			logSocket.info("启动推送侦听，端口为:"+port);
//			server.setReuseAddress(true);
			
			while (true) {
				new Thread(new SocketHandleThread(server.accept())).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			logSocket.info(StringHelper.exceptionToString(e));
		}
	}
}
