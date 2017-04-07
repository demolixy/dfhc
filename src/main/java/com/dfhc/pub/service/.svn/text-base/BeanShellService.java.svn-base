/**
 * 
 */
package com.dfhc.pub.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scripting.bsh.BshScriptUtils;
import org.springframework.stereotype.Service;

import bsh.EvalError;

import com.dfhc.PjException;

/**
 * @author longsebo
 *
 */
@Service
public class BeanShellService {
	public void doBeanShell(String userId,String script){
		BsTransaction sh;
        try {	        	
            sh = (BsTransaction) BshScriptUtils.createBshObject(script, new Class[] { BsTransaction.class });
            sh.doSave(userId);
        } catch (EvalError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new PjException(e);
        }    			
	}
	/**
	 * 通过bean shell脚本 读取任务数据
	 * @param request
	 * @param script bean shell脚本
	 * @return
	 */
	public Map<String, Object> readTaskData(HttpServletRequest request,String script){
		BsTransaction sh;
        try {	        	
            sh = (BsTransaction) BshScriptUtils.createBshObject(script, new Class[] { BsTransaction.class });
            return sh.readTaskData(request);
        } catch (EvalError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new PjException(e);
        }    			
	}
	
	/**
	 * 通过bean shell脚本 写入任务数据
	 * @param request
	 * @param response
	 * @param script bean shell脚本 
	 * @throws EvalError
	 */
	public void doSaveTaskData(HttpServletRequest request, HttpServletResponse response, String script) throws EvalError{
		BsTransaction sh = (BsTransaction) BshScriptUtils.createBshObject(script, new Class[] { BsTransaction.class });
        sh.doSaveTaskData(request, response);
	}
}
