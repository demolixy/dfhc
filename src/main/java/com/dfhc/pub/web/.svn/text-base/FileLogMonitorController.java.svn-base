/**
 * 
 */
package com.dfhc.pub.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.tools.helper.RmJspHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dfhc.ISystemConstant;
import com.dfhc.pub.service.FileDownloadService;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.util.FileOperateHelper;
import com.dfhc.util.StringHelper;

/**
 * @author longsebo
 * 为了远程查看文件日志方便，增加此功能 
 */
@Controller
@RequestMapping(value = "/fileLogMonitor")
public class FileLogMonitorController {
	@Autowired
	private PubParamService pubParamService;
	@Autowired
	private FileDownloadService fileDownloadService;
	/**
	 * 列出日志文件列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "")
    public String listLogFiles(Model model, HttpServletRequest request) {
		//构造日志目录:rm.xml配置根目录+logs
		String logPath = pubParamService.getConfigRootPath();
		if(logPath.endsWith(File.separator)){
			logPath  = logPath+ISystemConstant.LOG_RELA_PATH;
		}else{
			logPath  = logPath+File.separator+ISystemConstant.LOG_RELA_PATH;
		}
		//获取日志目录下，文件列表
		List<String> fullPathFileNames = FileOperateHelper.searchFile(logPath, null);
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		for(String fullFileName:fullPathFileNames){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fullName", fullFileName);
			map.put("shortName", FileOperateHelper.getFileName(fullFileName));
			items.add(map);
		}
		model.addAttribute("filenames", items);
		return "/logmonitor/logFileNames";
	}
	/**
	 * 查看文件日志
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "viewLogFile")
    public String viewLogFile(Model model, HttpServletRequest request) throws Exception {
		String fileName = request.getParameter("selectLogFileName");
		if(!StringHelper.isEmpty(fileName)){
			RmPageVo pageVo = null;
			if(request.getSession()!=null){
				Integer lineCount = (Integer) request.getSession().getAttribute(fileName);
				if(lineCount==null){
					lineCount = FileOperateHelper.getFileLineNum(fileName);
				}
				pageVo = RmJspHelper.transctPageVo(request, lineCount);
			}
			//从指定的行开始读取指定行数的内容
			String fileContent = FileOperateHelper.readFileWithPage(fileName, pageVo.getStartIndex(),pageVo.getPageSize(),ISystemConstant.ENCODE_UTF_8);
			request.setAttribute("logContent", fileContent);
			request.setAttribute("fullFileName", fileName);
			request.setAttribute("shortFileName", FileOperateHelper.getFileName(fileName));
		}
		
		return "/logmonitor/viewLogFile";
	}
	/**
	 * 下载文件日志
	 * @param model
	 * @param request
	 * @param response 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "downLoadLogFile")
    public void downLoadLogFile(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = request.getParameter("selectLogFileName");
		if(!StringHelper.isEmpty(fileName)){
			fileDownloadService.download(fileName, response);
		}
	}
}
