/**
 * 
 */
package com.dfhc.pub.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quickbundle.project.RmProjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.bsh.BshScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bsh.EvalError;

import com.dfhc.pub.service.BeanShellService;
import com.dfhc.pub.service.BsTransaction;


/**
 * @author longsebo
 * 测试Controller
 */
@Controller
@RequestMapping(value = "/testController")
public class TestController {
	@Autowired
	private BeanShellService beanShellService;
	/**
	 * 测试beanshell事务
	 * @throws EvalError 
	 *
	 */
    @RequestMapping(value = "testBeanShell")
    public void testBeanShell(HttpServletRequest request,HttpServletResponse response) throws EvalError {
    	
    	
    	String srciptText = "import org.activiti.service.LeaveService;" +
    			          "import org.quickbundle.base.beans.factory.RmBeanFactory;" +
    			          "import org.activiti.vo.LeaveVo;"+
    			          "import com.dfhc.util.DateUtil;"+
    			          "import org.activiti.engine.runtime.ProcessInstance;"+
    			          "import java.util.Map;"+
    			          "import java.util.HashMap;"+
    			"doSave(String userId){" +
    			" LeaveService leaveService = RmBeanFactory.getBean(LeaveService.class.getName());" +
    			" LeaveVo vo = new LeaveVo();"+
    			" vo.setUserId(userId);"+
    			" vo.setApplyTime(DateUtil.getNowDate());"+
    			" vo.setEndTime(DateUtil.getNowDate());"+
    			" vo.setLeaveType(\"请假\");"+
    			" vo.setProcessInstanceId(\"unknow\");"+
    			" vo.setRealityEndTime(DateUtil.getNowDate());"+
    			" vo.setRealityStartTime(DateUtil.getNowDate());"+
    			" vo.setReason(\"过年!\");"+
    			" vo.setStartTime(DateUtil.getNowDate());"+
    			" Map variables = new HashMap();"+
    			" leaveService.save(vo);"+
    			//" LeaveVo vo1 = null;" +
    			//" vo1.setUserId(userId);"+
    			"}";
    	        
        //BsTransaction sh;
     	String userId = RmProjectHelper.getRmUserId(request);
        //sh = (BsTransaction) BshScriptUtils.createBshObject(srciptText, new Class[] { BsTransaction.class });
        //sh.doSave(userId);
    	beanShellService.doBeanShell(userId, srciptText);
            	
    }
}
