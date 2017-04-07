/**
 * 
 */
package com.dfhc.pub.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.quickbundle.tools.support.log.RmLogHelper;
import org.springframework.stereotype.Service;

import com.dfhc.util.FileOperateHelper;
import com.dfhc.util.StringHelper;

/**
 * 文件下载服务
 * @author 龙色波
 */
@Service
public class FileDownloadService {
	/**
	 * 下载文件
	 * @param fileName 文件名
	 * @param response http应答对象
	 * @throws Exception 
	 */
	public void download(String fileName, HttpServletResponse response) throws Exception{
		//判断文件是否存在
		if(!FileOperateHelper.isExists(fileName)){
			response.setContentType("text/html;charset=utf-8");   
			response.getWriter().print("<script>alert('没有找到文件!');</script>");
			return;
		}
		// 写流文件到前端浏览器   
		ServletOutputStream out = response.getOutputStream();   
		response.addHeader("Content-Disposition", "attachment;filename="+ new String((FileOperateHelper.getFileName(fileName)).getBytes("GBK"), "ISO8859-1"));
		response.setContentType("application/octet-stream;charset=UTF-8");   
		response.addHeader("Content-Length", ""+FileOperateHelper.getFileLength(fileName));
		BufferedInputStream bis = null;   
		BufferedOutputStream bos = null;   
		try {   
		     bis = new BufferedInputStream(new FileInputStream(fileName));   
		     bos = new BufferedOutputStream(out);   
		      byte[] buff = new byte[1024];   
		      int bytesRead;   
		      while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
		        bos.write(buff, 0, bytesRead);   
		      }   
		      out.flush();
	    } catch (IOException e) {   
	    	RmLogHelper.error(getClass(), StringHelper.exceptionToString(e));
	    	throw e;
	    } finally {   
	      if (bis != null)   
	         bis.close();   
	      if (bos != null)   
	         bos.close();   
	      if(out!=null)
	    	  out.close();
		}   
	}
}
