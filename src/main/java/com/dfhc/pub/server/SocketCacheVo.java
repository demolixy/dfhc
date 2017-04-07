package com.dfhc.pub.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import org.quickbundle.base.vo.RmValueObject;

public class SocketCacheVo extends RmValueObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户端socket标识
	 */
	private String clientId;
	/**
	 * socket
	 */
	private Socket socket;
	/**
	 * socket 输入流
	 */
	private InputStream in;
	/**
	 * socket 输出流
	 */
	private OutputStream out;
	/**
	 * 最后一次心跳时间(正常推送也算心跳)
	 */
	private Date lastHeartbeatTime;
	
	
	public InputStream getIn() {
		return in;
	}

	public void setIn(InputStream in) {
		this.in = in;
	}

	public OutputStream getOut() {
		return out;
	}

	public void setOut(OutputStream out) {
		this.out = out;
	}

	public SocketCacheVo() {
	}

	public Socket getSocket() {
		return socket;
	}

	public void setClient(Socket client) {
		this.socket = client;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		SocketCacheVo vo = (SocketCacheVo) obj;
		return this.clientId.equals(vo.clientId);
	}

	public int hashCode() {
		int hash = 17;
		hash = 37 * hash + clientId.hashCode();
		return hash;
	}

	@Override
	public String toString() {
		return "SocketCacheVo [clientId=" + clientId + ", client=" + socket
				+ ", in=" + in + ", out=" + out + "]";
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Date getLastHeartbeatTime() {
		return lastHeartbeatTime;
	}

	public void setLastHeartbeatTime(Date lastHeartbeatTime) {
		this.lastHeartbeatTime = lastHeartbeatTime;
	}


	
}
