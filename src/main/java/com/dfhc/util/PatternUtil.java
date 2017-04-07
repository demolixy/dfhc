package com.dfhc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

	
	/**
	 * 验证车牌号
	 * @param carNum
	 * @return
	 */
	public static boolean checkedCarNum(String carNum){
		Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$");
		
		Matcher matcher = pattern.matcher(carNum);
		return matcher.matches();
	}
	
	/**
	 * 验证手机号码（暂缺固定电话的验证）
	 */
	public static boolean checkTelephone(String telephone){
		Pattern p = Pattern.compile("^((13[0-9])|(147)|(17[0,6-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher matcher = p.matcher(telephone);
		return matcher.matches();
	}

	/**
	 * 简单验证名称是否是汉子
	 * @param name
	 * @return
	 */
	public static boolean checkedName(String name){
		Pattern p = Pattern.compile("^[\u4e00-\u9fa5]{2,}$");
		Matcher matcher = p.matcher(name);
		return matcher.matches();
	}
	/**
	 * 验证不是0开头的数字
	 * @param name
	 * @return
	 */
	public static boolean checkedNumber(String num){
		Pattern p = Pattern.compile("^[1-9]{1}[0-9]{0,}$");
		Matcher matcher = p.matcher(num);
		return matcher.matches();
	}
	
	
	public static boolean checkNumber2(String num, String pattern){
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(num);
		return matcher.matches();
	}
	
	
}
