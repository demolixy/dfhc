package com.dfhc.util;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Locale;

import com.dfhc.ISystemConstant;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * 包装freemarker,是根据模版产生目标文件的工具类
 * @author longsebo
 *
 */
public class TemplateHelper {
	
	/**
	 * 封装freemarker,根据模版产生目标文件,注意templatePath采用类路径做根路径,而resultPath则以当前项目作为根路径
	 * @param paramMap
	 * @param templateFileName 模板文件名
	 * @param resultFile  结果文件名
	 */
	public static void createFileByPath(Object paramMap, String templateFileName,String resultFile) throws TemplateHelperException{
		Configuration freemarkerCfg = new Configuration();
		
//		if(resultPath != null && resultPath.indexOf(ISysConstant.DIV) == 0){
//			resultPath = resultPath.substring(1);
//		}
		if(StringHelper.isEmpty(resultFile)){
			throw new TemplateHelperException("结果文件为空!");
		}
		Template template;
		try {
			freemarkerCfg.setDirectoryForTemplateLoading(new File(FileOperateHelper.getPath(templateFileName)));
			freemarkerCfg.setEncoding(Locale.getDefault(), ISystemConstant.ENCODE_UTF_8);
			template = freemarkerCfg.getTemplate(FileOperateHelper.getFileName(templateFileName));
			template.setEncoding(ISystemConstant.ENCODE_UTF_8);
			
			File toFile = new File(resultFile);
			String path;
			path = FileOperateHelper.getPath(resultFile);
			FileOperateHelper.newFolder(path);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(toFile), ISystemConstant.ENCODE_UTF_8));
			template.process(paramMap, out);// 作为参数的根对象交给模版，模版中根据名字直接访问参数对象
			out.flush();
			out.close();			
		} catch (UnsupportedEncodingException e) {
			throw new TemplateHelperException("encodeError", e);
		} catch (FileNotFoundException e) {
			throw new TemplateHelperException("FileNotFound:"+templateFileName);
		} catch (TemplateException e) {
			throw new TemplateHelperException("creationTemplateError!Reason:"+e);
		} catch (IOException e) {
			throw new TemplateHelperException("FileIOError!Reason:"+e);
		} catch (Exception e) {
			throw new TemplateHelperException("FileError!Reason:"+e);
		}
	}
	/**
	 * 封装freemarker,根据模版产生目标串
	 * @param paramMap
	 * @param templatePath
	 * @return 返回目标串
	 */
	public static String createContentByPath(Object paramMap, String templateFileName) throws TemplateHelperException{
		Configuration freemarkerCfg = new Configuration();
		Template template;
		try {
			freemarkerCfg.setDirectoryForTemplateLoading(new File(FileOperateHelper.getPath(templateFileName)));
			freemarkerCfg.setEncoding(Locale.getDefault(), ISystemConstant.ENCODE_UTF_8);
			template = freemarkerCfg.getTemplate(FileOperateHelper.getFileName(templateFileName));
			template.setEncoding(ISystemConstant.ENCODE_UTF_8);
			
			ByteArrayOutputStream byteout = new ByteArrayOutputStream(); 
			Writer out = new BufferedWriter(new OutputStreamWriter(
					byteout, ISystemConstant.ENCODE_UTF_8));
			template.process(paramMap, out);// 作为参数的根对象交给模版，模版中根据名字直接访问参数对象
			out.flush();
			out.close();
			return byteout.toString();
		} catch (UnsupportedEncodingException e) {
			throw new TemplateHelperException("encodeError", e);
		} catch (FileNotFoundException e) {
			throw new TemplateHelperException("FileNotFound:"+templateFileName);
		} catch (TemplateException e) {
			throw new TemplateHelperException("creationTemplateError!Reason:"+e);
		} catch (IOException e) {
			throw new TemplateHelperException("FileIOError!Reason:"+e);
		}
	}
	
	/**
	 * 封装freemarker,根据模版产生目标串
	 * @param paramMap
	 * @param templatePath
	 * @return 返回目标串
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public static  String createContentByString(Object paramMap, String templateString) throws IOException, TemplateException{
		Reader reader = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(templateString.getBytes(ISystemConstant.ENCODE_UTF_8)), ISystemConstant.ENCODE_UTF_8));
		Template template = new Template(null, reader, null);
		//执行插值，并输出到指定的输出流中 
		ByteArrayOutputStream byteout = new ByteArrayOutputStream(); 
		Writer out = new BufferedWriter(new OutputStreamWriter(
				byteout, ISystemConstant.ENCODE_UTF_8));
		template.process(paramMap, out);// 作为参数的根对象交给模版，模版中根据名字直接访问参数对象
		out.flush();
		out.close();
		return byteout.toString(ISystemConstant.ENCODE_UTF_8);
	}
}
