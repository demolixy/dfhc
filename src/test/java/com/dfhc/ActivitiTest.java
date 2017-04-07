/**
 * 
 */
package com.dfhc;

import static org.junit.Assert.*;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.taglibs.standard.tag.el.sql.SetDataSourceTag;
import org.junit.Test;
import org.junit.Assert.*;
/**
 * @author longsebo
 *
 */
public class ActivitiTest {
	@Test
	public void testStartProces() throws Exception{
		//创建流程引擎，使用内存数据库
		 ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();
		 //部署流程定义文件
		 RepositoryService repositoryService = processEngine.getRepositoryService();
		 try{
		 Deployment deploy = repositoryService.createDeployment().addClasspathResource("com/dfhc/activiti/helloworld/sayhelloleave.bpmn").deploy();
		 }
		 catch(Exception e){
			 System.out.println(e.getMessage());
		 }
		 //验证已部署流程定义
		  ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
		 assertEquals("leave",processDefinition.getKey());		 
		 
		 //启动流程并返回流程实例
		 RuntimeService runtimeService = processEngine.getRuntimeService();
		 
		 ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave");
		 
		 assertNotNull(processInstance);
		 System.out.println("pid="+processInstance.getId()+",pdid="+processInstance.getProcessDefinitionId());
	}
	@Test
	//测试完成任务
	public void testFinishTask(){
		ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault().setProcessEngineName("mysqlEngine")
		.setJdbcDriver("com.mysql.jdbc.Driver")
		.setJdbcUsername("root")
		.setJdbcPassword("root")
		.setJdbcUrl("jdbc:mysql://localhost/activiti?useUnicode=true&characterEncoding=UTF-8&jdbcCompliantTruncation=false&allowMultiQueries=true")
		.setDatabaseType("mysql")
		.buildProcessEngine();
		
		 ProcessEngine processEngine = ProcessEngines.getProcessEngine("mysqlEngine");
		 List<Task> tasks = processEngine.getTaskService().createTaskQuery().taskUnassigned().list();
		 for(Task task:tasks){
			 processEngine.getTaskService().claim(task.getId(), "1000201100000000001");
			 processEngine.getTaskService().complete(task.getId());
		 }
	}
}
