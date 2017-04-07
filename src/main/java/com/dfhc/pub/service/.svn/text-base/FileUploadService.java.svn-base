package com.dfhc.pub.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quickbundle.tools.support.log.RmLogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.util.DateUtil;
import com.dfhc.util.FileOperateHelper;
import com.dfhc.util.StringHelper;



@Service
//默认将类中的所有public函数纳入事务管理
@Transactional(readOnly = true)
public class FileUploadService {

	@Autowired
	private PubParamService  pubParamService;
	
	/**
     * 上传附件方法
     * @param file
     * @param id
     * @return
     */
    public String uploadFile(MultipartFile file,String id,String type){
    	String nowDate = DateUtil.getDateStr(new Date(),"yyyyMMdd");

    	String filePath = null;//附件保存的路径
    	String taxFileName = null;//文件名称
    	String fileRelativePath=null;
    	if(file!=null&&!file.isEmpty()){
        	taxFileName=DateUtil.getNowTimeStampString()+"_"+file.getOriginalFilename();
        	fileRelativePath=nowDate+File.separator+id+File.separator+taxFileName;
        	String  rootPath=pubParamService.getPurRootPath(type);
  			filePath = rootPath+File.separator+nowDate+File.separator+id+File.separator;
  			File oldFile = new File(filePath);    
  	        File[] list = oldFile.listFiles();     
			
  			File targetFile = new File(filePath, taxFileName);
  			try {
  				if (!FileOperateHelper.isExists(filePath)) {
  						FileOperateHelper.newFolder(filePath);			
  				}
  				
  				//汽运配送图片类型的    文件夹里只有一张图片
  				if(ISystemConstant.DICTIONARY_ROOT_PATH_01.equals(type)){
  		    		List<File> deleteFile=new ArrayList<File>();
  		    		if(list!=null && list.length>0){
  		    			for (int i = 0;i<list.length;i++)
  			        {        
  			        	if(list[i].isFile())//判断是否是文件
  			        	{   
  			        			deleteFile.add(list[i]);
  			            }
  		        	} 
  		    		}
  		    		if(deleteFile.size()>0)
  		    		{
  		    			for(File dfile:deleteFile)
  		    			{
  		    				dfile.delete();
  		    			}
  		    		}
  					
  				}
  				// 保存
  		    	file.transferTo(targetFile);
  			
  			} catch (Exception e) {
  				RmLogHelper.error(getClass(), StringHelper.exceptionToString(e));
  				throw new RuntimeException(e);
  			}
        }
    	return fileRelativePath;
    }
}
