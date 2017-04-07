package org.activiti.web;
 
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.Page;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.tools.helper.RmJspHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dfhc.util.FileOperateHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.InputStream;
/**
 * 流程模型控制器
 * 
 * @author henryyan
 */
@Controller
@RequestMapping(value = "/workflow/model")
public class ModelController {
 
  protected Logger logger = LoggerFactory.getLogger(getClass());
 
  @Autowired
  RepositoryService repositoryService;
  @Autowired
  RuntimeService  runtimeService;
  @Autowired
  HistoryService historyService;
  @Autowired
  IdentityService identityService; 
  /**
   * 模型列表
   */
  @RequestMapping(value = "listmodel")
  public ModelAndView modelList() {
    ModelAndView mav = new ModelAndView("/activiti/explorer/model-list");
    List<Model> list = repositoryService.createModelQuery().list();
    //repositoryService.createProcessDefinitionQuery().list()
    mav.addObject("list", list);
    return mav;
  }
 
  /**
   * 创建模型
   */
  @RequestMapping(value = "create")
  public void create(@RequestParam("name") String name, @RequestParam("key") String key, @RequestParam("description") String description,
          HttpServletRequest request, HttpServletResponse response) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      ObjectNode editorNode = objectMapper.createObjectNode();
      editorNode.put("id", "canvas");
      editorNode.put("resourceId", "canvas");
      ObjectNode stencilSetNode = objectMapper.createObjectNode();
      stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
      editorNode.put("stencilset", stencilSetNode);
      Model modelData = repositoryService.newModel();
 
      ObjectNode modelObjectNode = objectMapper.createObjectNode();
      modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
      modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
      description = StringUtils.defaultString(description);
      modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
      modelData.setMetaInfo(modelObjectNode.toString());
      modelData.setName(name);
      modelData.setKey(StringUtils.defaultString(key));
 
      repositoryService.saveModel(modelData);
      repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
 
      response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
    } catch (Exception e) {
      logger.error("创建模型失败：", e);
    }
  }
 
  /**
   * 根据Model部署流程
   */
  @RequestMapping(value = "deploy/{modelId}")
  public String deploy(@PathVariable("modelId") String modelId, RedirectAttributes redirectAttributes) {
    try {
      Model modelData = repositoryService.getModel(modelId);
      ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
      byte[] bpmnBytes = null;
 
      BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
     // bpmnBytes = new BpmnXMLConverter().convertToXML(model);
      bpmnBytes =new BpmnXMLConverter().convertToXML(model,"GBK");
 
      String processName = modelData.getName() + ".bpmn20.xml";
      Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();
      redirectAttributes.addFlashAttribute("message", "部署成功，部署ID=" + deployment.getId());
    } catch (Exception e) {
      logger.error("根据模型部署流程失败：modelId={}", modelId, e);
    }
    return "redirect:/workflow/model/listmodel";
  }
 
  /**
   * 导出model的xml文件
   */
  @RequestMapping(value = "export/{modelId}")
  public void export(@PathVariable("modelId") String modelId, HttpServletResponse response) {
    try {
      Model modelData = repositoryService.getModel(modelId);
      BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
      JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
      BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
      BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
      byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);
 
      ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
      IOUtils.copy(in, response.getOutputStream());
      String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
      response.setHeader("Content-Disposition", "attachment; filename=" + filename);
      response.flushBuffer();
    } catch (Exception e) {
      logger.error("导出model的xml文件失败：modelId={}", modelId, e);
    }
  }
  @RequestMapping(value = "delete/{modelId}")
  public String delete(@PathVariable("modelId") String modelId) {
      repositoryService.deleteModel(modelId);
      return "redirect:/workflow/model/listmodel";
  }
  /**
   * 列出所有流程实例
   */
  @RequestMapping(value = "listinstance")
  public String listinstance(HttpServletRequest request) {
	  RmPageVo pageVo = RmJspHelper.transctPageVo(request, (int) runtimeService.createProcessInstanceQuery().count());
	  List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().orderByProcessDefinitionId().desc().listPage(pageVo.getStartIndex()-1, pageVo.getPageSize());
	  request.setAttribute("beans", list);
	  String ctx = request.getContextPath(); 
	  request.setAttribute("ctx", ctx);
	  //return "/activiti/explorer/running-manage";
	  return "/activiti/wk/listInstance";
  }
  /**
   * 列出所有流程实例(业务申请)
   */
  @RequestMapping(value = "businessApplication")
  public String businessApplication(HttpServletRequest request) {
//	  org.activiti.web.Page<ProcessDefinition> page = new org.activiti.web.Page<ProcessDefinition>(PageUtil.PAGE_SIZE);
//	  int[] pageParams = PageUtil.init(page, request);
//	  List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().listPage(pageParams[0], pageParams[1]);
//	  page.setResult(list);
//	  page.setTotalCount(runtimeService.createProcessInstanceQuery().count());
//	  request.setAttribute("page", page);
//	  return "/activiti/explorer/business-application";
	  RmPageVo pageVo = RmJspHelper.transctPageVo(request, (int) repositoryService.createProcessDefinitionQuery().count());
	  List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionId().desc().listPage(pageVo.getStartIndex()-1, pageVo.getPageSize());
	  request.setAttribute("beans", list);
	  String ctx = request.getContextPath(); 
	  request.setAttribute("ctx", ctx);
	  //return "/activiti/explorer/running-manage";
	  return "/activiti/wk/listBusinessApplication";
  }

  @RequestMapping(value = "start/{processDefinitionId}")
  public String start(@PathVariable("processDefinitionId") String processDefinitionId, ServletRequest request) {
	  identityService.setAuthenticatedUserId(RmProjectHelper.getRmUserId(request));
      runtimeService.startProcessInstanceById(processDefinitionId);
      return "redirect:/workflow/model/listprocedef";
  }
  /**
   * 流程列表
   */
  @RequestMapping(value = "listprocedef")
  public String procedefList(HttpServletRequest request) {
    org.activiti.web.Page<ProcessDefinition> page = new org.activiti.web.Page<ProcessDefinition>(PageUtil.PAGE_SIZE);
    int[] pageParams = PageUtil.init(page, request);
    List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().listPage(pageParams[0], pageParams[1]);
	page.setResult(list);
	page.setTotalCount(runtimeService.createProcessInstanceQuery().count());
	request.setAttribute("page", page);
	
    return "/activiti/explorer/process-list";   
  }
  /**
   * 文件方式部署流程资源
   */
  @RequestMapping(value = "/filedeploy")
  public String deploy(@RequestParam(value = "file", required = true) MultipartFile file) {

      // 获取上传的文件名
      String fileName = file.getOriginalFilename();

      try {
          // 得到输入流（字节流）对象
          InputStream fileInputStream = file.getInputStream();

          // 文件的扩展名
          String extension = FileOperateHelper.getFileSuffix(fileName);

          // zip或者bar类型的文件用ZipInputStream方式部署
          DeploymentBuilder deployment = repositoryService.createDeployment();
          if (extension.equalsIgnoreCase(".zip") || extension.equalsIgnoreCase(".bar")) {
              ZipInputStream zip = new ZipInputStream(fileInputStream);
              deployment.addZipInputStream(zip);
          } else {
              // 其他类型的文件直接部署
              deployment.addInputStream(fileName, fileInputStream);
          }
          deployment.deploy();
      } catch (Exception e) {
          logger.error("error on deploy process, because of file input stream");
      }

      return "redirect:listprocedef";
  }  
  /**
   * 读取流程资源
   *
   * @param processDefinitionId 流程定义ID
   * @param resourceName        资源名称
   */
  @RequestMapping(value = "/read-resource")
  public void readResource(@RequestParam("pdid") String processDefinitionId, @RequestParam("resourceName") String resourceName, HttpServletResponse response,HttpServletRequest request)
          throws Exception {
	  String temp = request.getParameter("resourceName");
      ProcessDefinitionQuery pdq = repositoryService.createProcessDefinitionQuery();
      ProcessDefinition pd = pdq.processDefinitionId(processDefinitionId).singleResult();

      // 通过接口读取
      InputStream resourceAsStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), resourceName);

      // 输出资源内容到相应对象
      byte[] b = new byte[1024];
      int len = -1;
      while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
          response.getOutputStream().write(b, 0, len);
      }
  }
  /**
   * 删除已部署的流程
   * @param deploymentId  部署id
   * @return
   */
  @RequestMapping(value = "deleteDeployment/{deploymentId}")
  public String deleteDeployment(@PathVariable("deploymentId") String deploymentId) {
      repositoryService.deleteDeployment(deploymentId,true);      
      return "redirect:/workflow/model/listprocedef";
  }
  /**
   * 挂起流程实例
   * @param processInstanceId  实例id
   * @return
   */
  @RequestMapping(value = "update/suspend/{processInstanceId}")
  public String suspendProcessInstanceById(@PathVariable("processInstanceId") String processInstanceId) {
      runtimeService.suspendProcessInstanceById(processInstanceId); 
      return "redirect:/workflow/model/listinstance";
  }
  /**
   * 激活流程实例
   * @param processInstanceId  实例id
   * @return
   */
  @RequestMapping(value = "update/active/{processInstanceId}")
  public String activateProcessInstanceById(@PathVariable("processInstanceId") String processInstanceId) {
      runtimeService.activateProcessInstanceById(processInstanceId);      
      return "redirect:/workflow/model/listinstance";
  }
  /**
   * 删除流程实例
   * @param processInstanceId  实例id
   * @return
   */
  @RequestMapping(value = "update/delete/{processInstanceId}")
  public String deleteProcessInstanceById(@PathVariable("processInstanceId") String processInstanceId) {
      //runtimeService.activateProcessInstanceById(processInstanceId);
	  runtimeService.deleteProcessInstance(processInstanceId, "");
	  historyService.deleteHistoricProcessInstance(processInstanceId);
      return "redirect:/workflow/model/listinstance";
  }
}