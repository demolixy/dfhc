package com.dfhc.util;

import java.security.MessageDigest;

import org.quickbundle.project.IGlobalConstants;
import org.quickbundle.project.RmProjectHelper;

import com.dfhc.ISystemConstant;


public class MD5Util implements IGlobalConstants {
	
	
	   /**
	    * 根据key生成新的加密串
	    * @param inStr
	    * @param key
	    * @return
	    */
	  public static String md5Encode(String inStr,String key) {
	    	inStr += key;
	        MessageDigest md5 = null;
	        StringBuffer hexValue = new StringBuffer();
	        try {
	            md5 = MessageDigest.getInstance("MD5");
				byte[] byteArray = inStr.getBytes("UTF-8");
		        byte[] md5Bytes = md5.digest(byteArray);
		        for (int i = 0; i < md5Bytes.length; i++) {
		            int val = ((int) md5Bytes[i]) & 0xff;
		            if (val < 16) {
		                hexValue.append("0");
		            }
		            hexValue.append(Integer.toHexString(val));
		        }
			} catch (Exception e) {
				RmProjectHelper.logError("系统异常", e);
			}
	        return hexValue.toString();
	    }
	  
    /*** 
     * MD5加密 生成32位md5码
     * @param 待加密字符串
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr) {
    	inStr += ISystemConstant.DIGEST_MD5;
        MessageDigest md5 = null;
        StringBuffer hexValue = new StringBuffer();
        try {
            md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = inStr.getBytes("UTF-8");
	        byte[] md5Bytes = md5.digest(byteArray);
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16) {
	                hexValue.append("0");
	            }
	            hexValue.append(Integer.toHexString(val));
	        }
		} catch (Exception e) {
			RmProjectHelper.logError("系统异常", e);
		}
        return hexValue.toString();
    }

    /**
     * 测试主函数
     * @param args
     * @throws Exception 
     */
    public static void main(String args[]) throws Exception {
        String str = "{\"reqType\": \"29\",\"version\":\"1.0\",\"appId\": \"\",\"appKey\": \"\",\"token\": \"d074cb49-52d7-4093-b58a-991a2537cdf4\",business:{ \"name\": \"33.jpg\"} }";
        RmProjectHelper.logError("系统调试","原始：" + str);
        RmProjectHelper.logError("系统调试","MD5后：" + md5Encode(str));
    }
    
    
    /*** 
     * MD5加密 生成32位md5码
     * @param 待加密字符串
     * @return 返回32位md5码
     */
    public static String md5EncodeWeb(String inStr) {
        MessageDigest md5 = null;
        StringBuffer hexValue = new StringBuffer();
        try {
            md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = inStr.getBytes("UTF-8");
	        byte[] md5Bytes = md5.digest(byteArray);
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16) {
	                hexValue.append("0");
	            }
	            hexValue.append(Integer.toHexString(val));
	        }
		} catch (Exception e) {
			RmProjectHelper.logError("系统异常", e);
		}
        return hexValue.toString();
    }
}