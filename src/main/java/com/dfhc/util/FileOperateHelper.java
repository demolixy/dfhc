/**
 * 
 */
package com.dfhc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;

import com.dfhc.ISystemConstant;

/**
 * 文件/目录操作工具类
 * 
 * @author longsebo
 */
public class FileOperateHelper {

	// 获取配置文件路径
	static ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
	/**
	 * 获取数据库中配置的根路径方法
	 * @param typeKey 路径类型
	 * @param dataKey 路径值类型
	 * @return
	 */
    public static String getFilePath(String typeKey,String dataKey){
    	String sql="SELECT RM_CODE_DATA.DATA_VALUE as dataValue  FROM RM_CODE_DATA ,RM_CODE_TYPE WHERE RM_CODE_DATA.CODE_TYPE_ID=RM_CODE_TYPE.ID"+
        "  AND  RM_CODE_TYPE.TYPE_KEYWORD='"+typeKey+"'  AND RM_CODE_DATA.DATA_KEY='"+dataKey+"'";
        List<RmCommonVo> list = RmProjectHelper.getCommonServiceInstance().doQuery(sql);
        if(list!=null && list.size()>0){
        	return list.get(0).getString("dataValue");
        }
    	return null;
    }
	/**
	 * 获取根路径（根据配置文件）
	 * 
	 * @return
	 */
	public static String getRootPath(String type) {
		String root_path = null;
		if("orderType".equals(type)){
			root_path= bundle.getString("orderPath");//订单多附件
		}	else if(type!=null && type.equals("product")){//上传的是样品图片
        	root_path= bundle.getString("root_file_picPath");
        }else if(type!=null && type.equals("report")){//上传的是检验报告
			root_path= bundle.getString("root_file_path");
		}else{//缩略图存放路径
			root_path= bundle.getString("root_file_picslPath");
		}
		if (!root_path.endsWith(File.separator)) {
			root_path = root_path + File.separator;
		}
		
		return root_path;

	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/fqf
	 * @return boolean
	 * @throws Exception
	 */
	public static void newFolder(String folderPath) throws Exception {

		String filePath = folderPath;
		filePath = filePath.toString();
		java.io.File myFilePath = new java.io.File(filePath);

		if (!myFilePath.exists()) {
			if (!myFilePath.mkdirs()) {
				throw new Exception("createDir" + folderPath + "fail");
			}
		}
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String 文件内容
	 * @return boolean
	 * @throws IOException
	 * @throws Exception
	 */
	public static void newFile(String filePathAndName, String fileContent)
			throws IOException, Exception {

		// 如果为空指针,则不写数据到文件
		if (fileContent != null) {
			FileOutputStream out = new FileOutputStream(filePathAndName);
			try {
				out.write(fileContent.getBytes());
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} else {
			File f = new File(filePathAndName);
			f.createNewFile();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 * @throws Exception
	 */
	public static void delFile(String filePathAndName) throws Exception {
		String filePath = filePathAndName;
		filePath = filePath.toString();
		java.io.File myDelFile = new java.io.File(filePath);
		if (myDelFile.exists()) {
			if (!myDelFile.delete()) {
				throw new Exception("deleteFile" + filePathAndName + " fail!");
			}
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param filePathAndName
	 *            String 文件夹路径及名称 如c:/fqf
	 * @param fileContent
	 *            String
	 * @return boolean
	 * @throws Exception
	 */
	public static void delFolder(String folderPath) throws Exception {
		delAllFile(folderPath); // 删除完里面所有内容
		String filePath = folderPath;
		filePath = filePath.toString();
		java.io.File myFilePath = new java.io.File(filePath);
		if (!myFilePath.delete()) { // 删除空文件夹
			throw new Exception("deleteFile " + folderPath + " fail");
		}
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 * @throws Exception
	 */
	public static void delAllFile(String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				if (!temp.delete()) {
					throw new Exception("deleteFile" + temp.getName() + " fail");
				}
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 * @throws Exception
	 */
	public static void copyFile(String oldPath, String newPath)
			throws Exception {

		int bytesum = 0;
		int byteread = 0;
		File oldfile = new File(oldPath);
		if (oldfile.exists()) { // 文件存在时
			InputStream inStream = new FileInputStream(oldPath); // 读入原文件
			FileOutputStream fs = new FileOutputStream(newPath);
			byte[] buffer = new byte[10240];
			try {
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
			} finally {
				if (inStream != null)
					inStream.close();
				if (fs != null)
					fs.close();
			}
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 * @throws IOException
	 * @throws Exception
	 */
	public static void copyFolder(String oldPath, String newPath)
			throws IOException, Exception {
		File mkdirsfile = new File(newPath);
		if (!mkdirsfile.mkdirs()) {
			throw new Exception("createDir" + newPath + " fail!");
		}

		File a = new File(oldPath);
		String[] file = a.list();
		File temp = null;
		for (int i = 0; i < file.length; i++) {
			if (oldPath.endsWith(File.separator)) {
				temp = new File(oldPath + file[i]);
			} else {
				temp = new File(oldPath + File.separator + file[i]);
			}

			if (temp.isFile()) {
				FileInputStream input = new FileInputStream(temp);
				FileOutputStream output = new FileOutputStream(newPath + "/"
						+ (temp.getName()).toString());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (temp.isDirectory()) {// 如果是子文件夹
				copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
			}
		}

	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 * @throws Exception
	 */
	public static void moveFile(String oldPath, String newPath)
			throws Exception {
		copyFile(oldPath, newPath);
		delFile(oldPath);

	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 * @throws IOException
	 * @throws Exception
	 */
	public static void moveFolder(String oldPath, String newPath)
			throws IOException, Exception {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);

	}

	/**
	 * 判断是否为目录
	 * 
	 * @param path
	 *            目录或者文件名
	 * @return 是目录返回true;否则返回false
	 */
	public static boolean isFolder(String path) {
		if (path != null) {
			File file = new File(path);
			return file.isDirectory();
		} else
			return false;
	}

	/**
	 * 判断目录是否存在
	 * 
	 * @param path
	 *            目录或者文件名
	 * @return 是目录返回true;否则返回false
	 */
	public static boolean isExists(String path) {
		if (path != null) {
			File file = new File(path);
			return file.exists();
		} else
			return false;
	}

	/**
	 * 搜索目录以及下级目录文件
	 * 
	 * @param path
	 * @param logFileFilter
	 * @return
	 */
	public static List<String> searchFile(String path, FilenameFilter fileFilter) {
		List<String> retList = new ArrayList<String>();
		if (isFolder(path)) {
			File file = new File(path);
			File[] subFiles = file.listFiles(fileFilter);
			for (int i = 0; i < subFiles.length; i++) {
				if (subFiles[i].isFile()) {
					retList.add(subFiles[i].getAbsolutePath());
				} else {
					retList.addAll(searchFile(subFiles[i].getAbsolutePath(),
							fileFilter));
				}
			}
		}
		return retList;
	}

	/**
	 * 根据文件名获取文件路径
	 * 
	 * @param fullPathFileName
	 * @return
	 */
	public static String getPath(String fullPathFileName) {
		int pos;
		pos = fullPathFileName.lastIndexOf("\\");
		if (pos == -1) {
			pos = fullPathFileName.lastIndexOf("/");
			if (pos == -1) {
				return fullPathFileName;
			}
		}
		return fullPathFileName.substring(0, pos);
	}

	/**
	 * 获取文件长度
	 * 
	 * @param fullPathFileName
	 * @return
	 * @throws Exception
	 */
	public static long getFileLength(String fullPathFileName) throws Exception {
		File file = new File(fullPathFileName);
		try {
			if (file.exists()) {
				return file.length();
			} else {
				return 0L;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取子目录列表,不递归
	 * 
	 * @param parentPath
	 *            父目录
	 * @return
	 * @throws Exception
	 */
	public static List<String> getSubPath(String parentPath) throws Exception {
		List<String> subPaths = new ArrayList<String>();
		if (parentPath == null || parentPath.length() == 0)
			throw new Exception("parentPathIsEmpty");
		File file = null;
		file = new File(parentPath);
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					subPaths.add(files[i].getAbsolutePath());
				}
			}
		}
		return subPaths;
	}

	/**
	 * 获取最末级目录
	 * 
	 * @param path
	 * @return
	 */
	public static String getLastLevelPath(String path) {
		int pos;
		pos = path.lastIndexOf('\\');
		if (pos == -1) {
			pos = path.lastIndexOf('/');
			if (pos == -1) {
				return path;
			} else {
				return path.substring(pos + 1);
			}
		} else {
			int pos1;
			pos1 = path.lastIndexOf('/');
			if (pos1 == -1) {
				return path.substring(pos + 1);
			} else {
				if (pos1 > pos) {
					return path.substring(pos1 + 1);
				} else {
					return path.substring(pos + 1);
				}
			}
		}

	}

	/**
	 * @description 将指定文件内容根据指定编码写入返回字符串
	 * @param file
	 * @param encoding
	 * @return 包含文件内容的字符串
	 * @throws Exception
	 */
	public static String readFileToString(File file, String encoding)
			throws Exception {
		try {
			return FileUtils.readFileToString(file, encoding);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * @description 将指定字符串根据指定编码写入文件
	 * @param file
	 * @param data
	 * @param encoding
	 * @throws Exception
	 */
	public static void writeStringToFile(File file, String data, String encoding)
			throws Exception {
		FileUtils.writeStringToFile(file, data, encoding);
	}

	/**
	 * @description 将指定字符串根据指定编码写入文件
	 * @param file
	 * @param data
	 * @param encoding
	 * @param bAppend
	 *            是否追加
	 * @throws Exception
	 */
	public static void writeStringToFile(File file, String data,
			String encoding, boolean bAppend) throws Exception {
		FileUtils.writeStringToFile(file, data, encoding, bAppend);
	}

	/**
	 * @description 查找指定路径directory中含有extensions[]关键字的文件集合
	 * @param directory
	 *            指定查找路径
	 * @param extensions
	 *            查找关键子 e.g {"java","xml"} ; null表示查询所有文件
	 * @param recursive
	 *            是否查询子文件夹
	 * @return 指定路径directory中含有extensions[]关键字的文件集合
	 */
	public static Collection<File> listFiles(File directory,
			String extensions[], boolean recursive) {
		return FileUtils.listFiles(directory, extensions, recursive);
	}

	public static List<String> getFileNamesForLinux(String path) {
		List<String> fileNames = new ArrayList<String>();
		try {
			String command = "ls " + path;
			BufferedReader bufReader = null;
			Process process = null;
			InputStreamReader file = null;
			InputStream in = null;
			try {
				String outputString;
				process = Runtime.getRuntime().exec(command);
				in = process.getInputStream();
				file = new InputStreamReader(in, "UTF-8");
				bufReader = new BufferedReader(file);
				while ((outputString = bufReader.readLine()) != null) {
					System.out.println(outputString);
					// 转为utf-8
					// String convString = new
					// String(outputString.getBytes(),ISystemConstant.EPP_CHARSET);
					// System.out.println("convrt filename:"+convString);
					fileNames.add(outputString);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (file != null) {
						file.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (bufReader != null) {
						bufReader.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (process != null) {
						process.destroy();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileNames;
	}

	/**
	 * 从绝对文件路径获取文件名
	 * 
	 * @param fullFileName
	 * @return 文件名
	 */
	public static String getFileName(String fullFileName) {
		if (fullFileName == null || fullFileName.trim().length() == 0)
			return "";
		int lastpos;
		lastpos = getLastPathIndicatePos(fullFileName);
		if (lastpos == -1) {
			return fullFileName;
		}
		return fullFileName.substring(lastpos + 1);
	}

	/**
	 * 获取最后路径标识位置
	 * 
	 * @param fullFileName
	 *            全文件路径
	 * @return 返回最后标识位置
	 */
	private static int getLastPathIndicatePos(String fullFileName) {
		int lastpos;
		lastpos = fullFileName.lastIndexOf('\\');
		if (lastpos == -1) {
			lastpos = fullFileName.lastIndexOf('/');
		} else {
			int lastpos1 = fullFileName.lastIndexOf('/');
			if (lastpos1 > lastpos) {
				lastpos = lastpos1;
			}
		}

		return lastpos;
	}

	/**
	 * 获取文件名后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		if (fileName != null) {
			int pos;
			pos = fileName.lastIndexOf(".");
			if (pos == -1) {
				return "";
			} else {
				return fileName.substring(pos);
			}
		} else {
			return null;
		}
	}

	/**
	 * 使用linux命令获取文件名
	 * 
	 * @param path
	 * @return
	 */

	/**
	 * 获取文件名前缀.例如c:\1.txt返回1
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFilePrefix(String fileName) {
		if (fileName != null) {
			String simpleFileName;
			simpleFileName = getFileName(fileName);
			int pos;
			pos = simpleFileName.lastIndexOf(".");
			if (pos == -1) {
				return simpleFileName;
			} else {
				return simpleFileName.substring(0, pos);
			}
		} else {
			return null;
		}
	}
	/**
	 * 获取文本文件行数
	 * @param fileName 文件名
	 * @return
	 * @throws Exception 
	 */
	public static Integer getFileLineNum(String fileName) throws Exception {
		Reader reader = null;
		char buffer[];
		int rbytes =0;
		int len = 102400;
		String splitSegment[]=null;
		StringBuilder totalBuffer = new StringBuilder();
		boolean isComplete;
		
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		String splitStr=ISystemConstant.CRLF;
		Integer lineCrNum =0;
		try{
			buffer = new char[len];
			fileInputStream = new FileInputStream(fileName);
			inputStreamReader = new InputStreamReader(
					fileInputStream, ISystemConstant.ENCODE_UTF_8);
			reader = new BufferedReader(inputStreamReader);
			
			do{
			  rbytes = reader.read(buffer,0,len);
			  if(rbytes>0){
				  totalBuffer.append(buffer,0,rbytes);
 			      //将splitStr拆分为多个段
				  String nativeSegment = totalBuffer.toString();
				  splitSegment = StringHelper.splitString(nativeSegment, splitStr);
				  //判断最后一个段是否完整,不完整，最后一段覆盖totalBuffer
				  isComplete  = lastSegmentIsComplete(nativeSegment,splitStr);
				  lineCrNum = lineCrNum+StringHelper.findStrNum(totalBuffer.toString(), splitStr);
				  if(!isComplete){
					  totalBuffer.delete(0, totalBuffer.length());
					  if(splitSegment.length>0){
						  totalBuffer.append(splitSegment[splitSegment.length-1]);
					  }
				  }else{
					  totalBuffer.delete(0, totalBuffer.length());
				  }
			  }
			  
			}while(rbytes>=len);
			return lineCrNum;
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(inputStreamReader!=null){
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 判断最后那段是否完整
	 * @param nativeSegment
	 * @return  完整返回true;否则返回false
	 */
	private static boolean lastSegmentIsComplete(String nativeSegment,String splitStr) {
		int pos = nativeSegment.lastIndexOf(splitStr);
		if(pos==-1){
			return false;
		}
		//截取从pos到结尾
		String remainder = nativeSegment.substring(pos+1);
		Pattern isCompletePattern;
		
		String regEx="[A-Za-z0-9]+";
		isCompletePattern = Pattern.compile(regEx);
		
		Matcher matcher = isCompletePattern.matcher(remainder);
		// 查找字符串中是否有数字或字符,如果有则不完整；否则认为完整
		return !matcher.find();
		
	}
	/**
	 * 翻页方式读取文件
	 * @param fileName 文件名
	 * @param startLine 开始行   从1开始
	 * @param pageSize 页大写
	 * @param encode 编码
	 * @return
	 * @throws IOException 
	 */
	public static String readFileWithPage(String fileName, int startLine,
			int pageSize, String encode) throws IOException {
		Reader reader = null;
		char buffer[];
		int rbytes =0;
		int len = 102400;
		String splitSegment[];
		StringBuilder totalBuffer = new StringBuilder();
		StringBuilder returnBuffer = new StringBuilder();
		boolean isComplete;
		
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		String splitStr=ISystemConstant.CRLF;
		int readLineNum =0;//统计已读行数
		int lastLineNum = startLine+pageSize;
		//int addedLineNum = 0;//统计已添加的行数
		try{
			buffer = new char[len];
			fileInputStream = new FileInputStream(fileName);
			inputStreamReader = new InputStreamReader(
					fileInputStream, ISystemConstant.ENCODE_UTF_8);
			reader = new BufferedReader(inputStreamReader);
			do{
			  rbytes = reader.read(buffer);
			  if(rbytes>0){
				  totalBuffer.append(buffer,0,rbytes);
 			      //将splitStr拆分为多个段
				  String nativeSegment = totalBuffer.toString();
				  splitSegment = StringHelper.splitString(nativeSegment, splitStr);
				  //判断最后一个段是否完整,不完整，最后一段覆盖totalBuffer
				  isComplete  = lastSegmentIsComplete(nativeSegment,splitStr);
				  int findStrNum = StringHelper.findStrNum(totalBuffer.toString(), splitStr);
				  readLineNum = readLineNum+findStrNum;
				  //readLineNum>startLine
				  if(readLineNum>=startLine){
					  //
					  int oldReadLineNum = readLineNum - findStrNum;
					  
					  //boolean bIsEmpty ;
					  //bIsEmpty = returnBuffer.length()==0;
					  if(oldReadLineNum==0){
						  oldReadLineNum = oldReadLineNum+ startLine-1;
					  }
					  for(int i=oldReadLineNum;i<lastLineNum-1 && i<readLineNum;i++){
						  if(oldReadLineNum<splitSegment.length){
							  returnBuffer.append(splitSegment[i]).append(splitStr);
						  }else{
							  returnBuffer.append(splitSegment[i-oldReadLineNum]).append(splitStr);
						  }
					  }
				  }
				  if(!isComplete){
					  totalBuffer.delete(0, totalBuffer.length());
					  if(splitSegment.length>0){
						  totalBuffer.append(splitSegment[splitSegment.length-1]);
					  }
				  }else{
					  totalBuffer.delete(0, totalBuffer.length());
				  }
			  }
			}while(rbytes>=len && readLineNum<startLine+pageSize);
			return returnBuffer.toString();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(inputStreamReader!=null){
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

/**
 * 通用的文件名过滤器
 * 
 * 作者:龙色波 日期:2013-12-3
 */
class CommentFileFilter implements FileFilter {
	private String filterName;

	CommentFileFilter(String filterName) {
		this.filterName = filterName;
	}

	// @Override
	public boolean accept(File pathname) {
		if (pathname != null && filterName != null) {
			// 如果为*.xxx，则后匹配
			String testFile = pathname.getAbsolutePath();
			if (filterName.startsWith("*")) {
				if (testFile.endsWith(filterName.substring(1))) {
					return true;
				} else {
					return false;
				}
				// 如果为xxx.* 则为前匹配
			} else if (filterName.endsWith("*")) {
				if (testFile.startsWith(filterName.substring(1,
						filterName.length() - 1))) {
					return true;
				} else {
					return false;
				}
				// 否则，则文件名要完全相同
			} else {
				return testFile.equals(filterName);
			}
		} else {
			return false;
		}
	}

}
