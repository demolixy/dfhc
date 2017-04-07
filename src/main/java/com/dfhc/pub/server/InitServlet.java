package com.dfhc.pub.server;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;




public class InitServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	//异步启动现在 避免占用tomcat启动时间
	@Override
	public void init() throws ServletException {
		FutureTask<String> future = new FutureTask<>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				
				ListenConnect.getInstance().listen();
				
				return "socket start!";
			}
		});
		
		new Thread(future).start();
		FutureTask<String> future2 = new FutureTask<>(new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				
				//new Thread(nnew LintenData()).start();
				new PushThread().start();
				return "push thread start!";
			}
		});
		
		new Thread(future2).start();
	}
	
}
