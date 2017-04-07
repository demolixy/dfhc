package com.dfhc.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 * 
 * @author 龙色波
 * 
 */
public class StringHelper {
	/**
	 * 在指定的字符串,正向搜索指定字符串任一字符出现位置,只要找到其中一个字符就返回,没有找到时,返回-1
	 * 
	 * @param startpos
	 *            strMonther 开始位置
	 * @param strMonther
	 *            被搜索的串
	 * @param strChild
	 * @return
	 */
	public static int findInStr(int startpos, String strMonther, String strChild) {
		for (int i = startpos; i < strMonther.length(); i++) {
			if (strChild.indexOf(strMonther.charAt(i)) >= 0)
				return i;
		}
		return -1;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		int beginindex;

		if (str == null || str.length() == 0)
			return false;
		String strTest = str.trim();
		if (strTest.length() == 0)
			return false;
		// 带符号,则跳过
		if (strTest.charAt(0) == '-' || strTest.charAt(0) == '+')
			beginindex = 1;
		else
			beginindex = 0;
		for (int i = beginindex; i < str.length(); i++) {
			if (str.charAt(i) == '.' || str.charAt(i) == '%')
				continue;
			else if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
				continue;
			else
				return false;
		}
		// 只有符号不是数字
		if (beginindex == 1 && str.length() < 2)
			return false;
		else
			return true;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(char ch) {
		if (ch >= '0' && ch <= '9')
			return true;
		else
			return false;
	}

	/**
	 * 判断字符是否为字母
	 * 
	 * @param ch
	 *            测试字符
	 * @return boolean 为字母是返回true;否则返回false
	 */
	public static boolean isLetter(char ch) {
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
			return true;
		else
			return false;
	}

	/**
	 * 在指定的字符串,反向搜索指定字符串任一字符出现位置,只要找到其中一个字符就返回,没有找到时,返回-1
	 * 
	 * @param startpos
	 *            strMonther 开始位置
	 * @param strMonther
	 *            被搜索的串
	 * @param strChild
	 * @return
	 */
	public static int reversalFindInStr(int startpos, String strMonther,
			String strChild) {
		for (int i = startpos; i >= 0; i--) {
			if (strChild.indexOf(strMonther.charAt(i)) >= 0)
				return i;
		}
		return -1;
	}

	/**
	 * 查询strFind在strMonther出现的次数
	 * 
	 * @param strMonther
	 *            母串
	 * @param strFind
	 *            搜索串
	 * @return int 匹配次数
	 */
	public static int findStrNum(String strMonther, String strFind) {
		int startpos, endpos;
		int sum = 0;
		endpos = 0;
		startpos = 0;
		while (endpos >= 0) {
			endpos = strMonther.indexOf(strFind, startpos);
			if (endpos != -1) {
				sum++;
				startpos = endpos + 1;
			}
		}
		return sum;
	}

	/**
	 * 转换用指定字符分隔的字符串为字符串
	 * 
	 * @param s
	 *            需要转换的字符串
	 * @param ch
	 *            分割字符
	 * @return String[] 分隔后的字符串
	 * @remark 使用String.split方法时,当参数为'.'不能正常转换
	 */
	public static String[] split(String s, char ch) {
		return splitString(s, String.valueOf(ch));
	}

	/**
	 * 判断字符串是否拆分
	 * 
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return boolean 是拆分返回true;否则返回false
	 */
	public static boolean isSplit(String str, String separator) {
		if (splitString(str, separator).length > 1)
			return true;
		else
			return false;
	}

	/**
	 * 拆分字符串，将用一定符号分割的字符串拆分为一个数组
	 * 
	 * @param str
	 *            要进行拆分的字符串
	 * @param separator
	 *            分隔符
	 * @return 拆分后的数组
	 * @remark 不能使用str.spilt.拆分1,2,3,,,,有问题
	 */
	public static String[] splitString(String str, String separator) {
		StringBuffer buff = new StringBuffer();
		buff.append(str);
		return splitString(buff, separator);
	}

	/**
	 * 判断字符串是否为重复字符串格式,例如A:4代表四个字符A
	 * 
	 * @param str
	 *            检测串
	 * @param repeat
	 *            重复指示串
	 * @return boolean 是重复格式时,返回true;否则返回false
	 */
	public static boolean isRepeat(String str, String repeat) {
		if (str == null)
			return false;
		int pos;
		pos = str.indexOf(repeat);
		// 没有找到或者最后一个字符或者第一个
		if (pos == -1 || pos == str.length() - 1 || pos == 0)
			return false;
		// 下一个字符必须为数字
		if (!isNumber(String.valueOf(str.charAt(pos + 1))))
			return false;
		else
			return true;
	}

	/**
	 * 在指定的字符串,正向搜索指定字符串数组任一字符串出现位置,只要找到其中一个字符串就返回,没有找到时,返回-1
	 * 
	 * @param startpos
	 *            strMonther 开始位置
	 * @param strMonthers
	 *            被搜索的字符串
	 * @param strChilds
	 *            搜索字符串数组
	 * @return List 返回找到位置和对应strChilds索引,List第一个元素为找到位置,第二个元素为strChilds索引
	 */
	public static List<Object> findInStrArray(int startpos, String strMonther,
			String strChilds[]) {
		return findInStrArray(startpos, strMonther, strChilds, true);
	}

	/**
	 * 在指定的字符串,正向搜索指定字符串数组任一字符串出现位置,只要找到其中一个字符串就返回,没有找到时,返回-1
	 * 
	 * @param startpos
	 *            strMonther 开始位置
	 * @param strMonthers
	 *            被搜索的字符串
	 * @param strChilds
	 *            搜索字符串数组
	 * @param caseInsensitive
	 *            是否区分大小写 true--区分,false---不区分
	 * @return List 返回找到位置和对应strChilds索引,List第一个元素为找到位置,第二个元素为strChilds索引
	 */
	public static List<Object> findInStrArray(int startpos, String strMonther,
			String strChilds[], boolean caseInsensitive) {
		int pos;
		int minPos, minIndex;

		// 初始化位置
		minPos = strMonther.length();
		minIndex = strChilds.length;
		List<Object> retVal = new ArrayList<Object>();
		for (int j = 0; j < strChilds.length; j++) {
			if (caseInsensitive) {
				pos = strMonther.indexOf(strChilds[j], startpos);
			} else {
				pos = findStrIgnoreCase(strMonther, strChilds[j], startpos);
			}
			if (pos >= 0) {
				// 取首先找到的位置
				if (pos < minPos) {
					minPos = pos;
					minIndex = j;
				}
			}
		}
		if (minPos == strMonther.length()) {
			retVal.add(new Integer(-1));
			retVal.add(new Integer(-1));
		} else {
			retVal.add(new Integer(minPos));
			retVal.add(Integer.valueOf(minIndex));
		}
		return retVal;
	}

	/**
	 * 字符串忽略大小写全部替换
	 * 
	 * @param strMonther
	 *            原字符串
	 * @param strOld
	 *            被替换的字符串
	 * @param strNew
	 *            替换新串
	 * @return String 返回替换后的字符串
	 */
	public static String replaceAllIgnoreCase(String strMonther, String strOld,
			String strNew) {
		StringBuffer strReturn = new StringBuffer();
		String strLowerCase, strLowerCaseFind;
		int spos, epos;
		int len;
		if (strMonther == null)
			return null;
		strLowerCase = strMonther.toLowerCase();
		strLowerCaseFind = strOld.toLowerCase();
		spos = epos = 0;
		len = strOld.length();
		while (epos >= 0) {
			epos = strLowerCase.indexOf(strLowerCaseFind, spos);
			if (epos >= 0) {
				// 连接字符串
				strReturn.append(strMonther.substring(spos, epos)).append(
						strNew);
				// 移动位置
				spos = epos + len;
			} else {
				strReturn.append(strMonther.substring(spos));
			}
		}
		return strReturn.toString();
	}

	/**
	 * 截断字符串空格
	 * 
	 * @return String
	 */
	public static String trim(String str) {
		if (str != null)
			return str.trim();
		else
			return str;
	}

	/**
	 * 判断字符串是否为空串或null
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		String testStr;
		if (str == null) {
			return true;
		}
		testStr = trim(str);
		if (testStr.length() == 0)
			return true;
		else
			return false;
	}

	/**
	 * 使用StringBuffer替换字符串
	 * 
	 * @param buffer
	 *            母串
	 * @param oldStr
	 *            被替换的旧串
	 * @param newStr
	 *            需要替换的新串
	 * @return
	 */
	public static StringBuffer replaceAll(StringBuffer buffer, String oldStr,
			String newStr) {
		int epos, spos;
		int len, nlen;

		if (buffer == null || oldStr == null || newStr == null)
			return null;
		epos = spos = 0;
		len = oldStr.length();
		nlen = newStr.length();
		StringBuffer newBuffer = new StringBuffer();
		newBuffer.append(buffer);
		while (epos >= 0) {
			epos = newBuffer.indexOf(oldStr, spos);
			if (epos >= 0) {
				newBuffer = newBuffer.replace(epos, epos + len, newStr);
				spos = epos + nlen;
			}

		}
		return newBuffer;
	}

	/**
	 * 拆分字符串，将用一定符号分割的字符串拆分为一个数组
	 * 
	 * @param str
	 *            要进行拆分的字符串
	 * @param separator
	 *            分隔符
	 * @return 拆分后的数组
	 * @remark 不能使用str.spilt.拆分1,2,3,,,,有问题
	 */
	public static String[] splitString(StringBuffer str, String separator) {
		List<String> list = new ArrayList<String>();
		String strtemp;
		int spos, epos;
		int len;

		epos = 0;
		spos = 0;
		len = separator.length();
		while (epos >= 0 && spos <= str.length() - 1) {
			epos = str.indexOf(separator, spos);
			if (epos == -1) {
				strtemp = str.substring(spos);
				if (strtemp != null)
					list.add(strtemp);
			} else {
				if (spos <= epos) {
					strtemp = str.substring(spos, epos);
				} else {
					strtemp = "";
				}
				if (strtemp != null)
					list.add(strtemp);
			}

			spos = epos + len;
		}
		// 如果以分隔符为结束的，则添加“”
		if (epos != -1) {
			list.add("");
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 使用StringBuffer替换字符串
	 * 
	 * @param buffer
	 *            母串
	 * @param oldStr
	 *            被替换的旧串
	 * @param newStr
	 *            需要替换的新串
	 * @return
	 */
	public static StringBuffer replaceAll(String str, String oldStr,
			String newStr) {
		StringBuffer buff = new StringBuffer();

		buff.append(str);
		return replaceAll(buff, oldStr, newStr);
	}

	/**
	 * 转换成小写
	 * 
	 * @param ch
	 *            字符
	 * @return 小写字符
	 */
	public static char toLower(char ch) {

		if (ch >= 'a' && ch <= 'z')
			return ch;
		else if (ch >= 'A' && ch <= 'Z')
			return (char) ((int) ch - (int) 'A' + (int) 'a');
		else
			return ch;
	}

	/**
	 * 转换成大写
	 * 
	 * @param ch
	 *            字符
	 * @return 大写字符
	 */
	public static char toUpper(char ch) {

		if (ch >= 'A' && ch <= 'Z')
			return ch;
		else if (ch >= 'a' && ch <= 'z')
			return (char) ((int) ch - (int) 'a' + (int) 'A');
		else
			return ch;
	}

	/**
	 * 首字母变大写
	 * 
	 * @param value
	 * @return
	 */
	public static String toUpperFirst(String value) {
		if (!StringHelper.isEmpty(value)) {
			return toUpper(value.charAt(0)) + value.substring(1);
		} else
			return value;
	}

	/**
	 * 首字母变小写
	 * 
	 * @param value
	 * @return
	 */
	public static String toLowerFirst(String value) {
		if (!StringHelper.isEmpty(value)) {
			return toLower(value.charAt(0)) + value.substring(1);
		} else
			return value;
	}

	/**
	 * 忽略大小写查找
	 * 
	 * @param strMonther
	 *            母串
	 * @param value
	 *            字符串
	 * @param startpos
	 *            开始
	 * @return int 返回查找位置
	 */
	public static int findStrIgnoreCase(String strMonther, String value,
			int startpos) {
		return strMonther.toLowerCase().indexOf(value.toLowerCase(), startpos);

	}

	/**
	 * 截断前后不可见字符:目前不可见字符包括空格,TAB,回车,换行 \t\n\x0B\f\r
	 * 
	 * @param value
	 * @return
	 */
	public static String trimNotVisibleChar(String value) {

		if (value != null && value.length() > 0) {
			int beforePos, afterPos;
			beforePos = 0;
			afterPos = value.length();
			// 前截
			for (int i = 0; i < value.length(); i++) {
				if (value.charAt(i) == ' ' || value.charAt(i) == '\t'
						|| value.charAt(i) == 10 || value.charAt(i) == 13
						|| value.charAt(i) == 12 || value.charAt(i) == '\f') {
					beforePos++;
				} else {
					break;
				}
			}
			if (beforePos < value.length() - 1) {
				// 后截
				for (int i = value.length() - 1; i >= 0; i--) {
					if (value.charAt(i) == ' ' || value.charAt(i) == '\t'
							|| value.charAt(i) == 10 || value.charAt(i) == 13
							|| value.charAt(i) == 12 || value.charAt(i) == '\f') {
						afterPos--;
					} else {
						break;
					}
				}
			}
			return value.substring(beforePos, afterPos);
		} else
			return value;
	}

	/**
	 * 判断字符是否不可见字符,目前不可见字符包括空格,TAB,回车,换行
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isNotVisibleChar(char ch) {
		if (ch == ' ' || ch == '\t' || ch == 10 || ch == 13)
			return true;
		else
			return false;
	}

	/**
	 * 在指定的字符串中查找若干单词组成的串，单词之间由 一个或多个不可见字符组成:\t\n\x0B\f\r
	 * 
	 * @param mother
	 *            母串
	 * @param words
	 *            词组
	 * @param caseInsensitive
	 *            是否忽略大小写
	 * @param multiLine
	 *            是否支持多行
	 * @return 没有找到返回null
	 */
	public static String findStringSeparateByNotVisibleChar(String mother,
			String words[], boolean caseInsensitive, boolean multiLine) {
		int flag = 0;
		StringBuffer format;

		if (caseInsensitive)
			flag = Pattern.CASE_INSENSITIVE;
		if (multiLine)
			flag = flag | Pattern.MULTILINE;
		format = new StringBuffer();
		for (int i = 0; i < words.length; i++) {
			format.append(words[i]);
			if (i < words.length - 1)
				format.append("(\\s+)");
		}
		Pattern p;
		if (flag != 0) {
			p = Pattern.compile(format.toString(), flag);
		} else {
			p = Pattern.compile(format.toString());
		}
		Matcher m = p.matcher(mother);
		if (m.find()) {
			return m.group();
		} else
			return null;
	}

	/**
	 * 将一段文字拆分一个个单词
	 * 
	 * @param value
	 * @return
	 */
	public static List<String> splitWord(String value) {
		List<String> retList = new ArrayList<String>();
		if (!isEmpty(value)) {
			Pattern p = Pattern.compile("\\w+", Pattern.MULTILINE);
			Matcher m = p.matcher(value);

			while (m.find())
				retList.add(m.group());
			return retList;
		} else {
			if (value != null)
				retList.add(value);
			return retList;
		}

	}
	/**
	 * 前台类型转换
	 * 
	 * @param value
	 * @return
	 */
	public static String frObjToString(java.sql.Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(new Date(date.getTime()));
		return dateString;
	}
	/**
	 * 前台类型转换
	 * 
	 * @param value
	 * @return
	 */
	public static String frTimestampToString(Timestamp stamp ,String pattern) {
		if(stamp==null)
			return "";
		Date date = new Date(stamp.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
		
	}
	/**
	 * 前台类型转换
	 * 
	 * @param value
	 * @return
	 */
	public static String frDateToString(java.sql.Date date) {
		return frDateToStringByPat(date,"yyyy年MM月dd日");
	}
	/**
	 * 前台类型转换
	 * 
	 * @param value
	 * @return
	 */
	public static String frDateToStringByPat(java.sql.Date date,String pattern) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateString = formatter.format(new Date(date.getTime()));
		return dateString;
	}
	public static String frDateToStringByPat(java.sql.Timestamp stamp,String pattern) {
		if (stamp == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateString = formatter.format(new Date(stamp.getTime()));
		return dateString;
	}
	public static String frRateToPointString(BigDecimal dec) {
		if (dec == null) {
			return "";
		}		
		return dec.multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP).toString();
	}
	/**
	 * 前台利率    *100 后面加百分号
	 * 
	 * @param value
	 * @return
	 */
	public static String frRateToString(BigDecimal yield_rate) {
		if (yield_rate == null) {
			return "";
		}
		return yield_rate.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString()+"%";
	}
	
	/**
	 * 判断字符串是否为sql关键字
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isSqlKey(String value) {
		String sqlkey[] = new String[] { "asc", "desc", "where", "from",
				"select" };
		String peekValue;

		peekValue = trimNotVisibleChar(value);
		for (int i = 0; i < sqlkey.length; i++) {
			if (sqlkey[i].equalsIgnoreCase(peekValue))
				return true;
		}
		return false;
	}

	/**
	 * 异常转换为字符串
	 * 
	 * @param e
	 * @return
	 */
	public static String exceptionToString(Throwable e) {
		if (e != null) {
			PrintStream stream;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			stream = new PrintStream(out);
			e.printStackTrace(stream);
			return new String(out.toByteArray());
		} else {
			return "";
		}
	}
	/**
	 * 构造指定次数重复的字符串
	 * @param val 字符串值 
	 * @param num  次数
	 * @return 返回字符串
	 */
	public static String repeatString(String val,int num){
		StringBuilder buff = new StringBuilder();
		
		if(val!=null){
			for(int i=0;i<num;i++){
				buff.append(val);
			}
			return buff.toString();
		}else{
			return null;
		}
	}
	/**
	 * ids  添加一条ID
	 */
	public static String idsListToStr(List<String> idsList){
		if(idsList==null || idsList.size()==0)
			return "";
		StringBuilder sb = new StringBuilder();
		for(int a=0;a<idsList.size()-1;a++){
			sb.append(idsList.get(a).trim());
			sb.append(",");
		}
		sb.append(idsList.get(idsList.size()-1));
		return sb.toString();
	}
	/**
	 * 去掉回车换行
	 * @param val
	 * @return
	 */
	public static String trimCrlf(String val){
		if(val==null||val.equals("")){
			return val;
		}else{
			char lastchar = val.charAt(val.length()-1);
			if(lastchar=='\r'||lastchar=='\n'){
				val = val.substring(0, val.length()-1);
			}
			lastchar = val.charAt(val.length()-1);
			if(lastchar=='\r'||lastchar=='\n'){
				val = val.substring(0, val.length()-1);
			}
			return val;
		}
	}
	/**
	 * ids[]转换为逗号分隔串 例如 String ids[]={a,b,c}=>'a','b','c'
	 */
	public static String idsListToStr(String ids[]){
		if(ids==null || ids.length==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<ids.length;i++){
			if(ids[i]!=null){
				sb.append("'").append(ids[i].trim()).append("'");
			}else{
				sb.append("'").append(ids[i]).append("'");
			}
			if(i<ids.length-1){
				sb.append(",");
			}
		}
		return sb.toString();
	}
	/**
	 * ids[]转换为逗号分隔串 例如 String ids[]={a,b,c}=>'a','b','c'
	 */
	public static String arrayToStr(String...ids){
		if(ids==null || ids.length==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<ids.length;i++){
			if(ids[i]!=null){
				sb.append("'").append(ids[i].trim()).append("'");
			}else{
				sb.append("'").append(ids[i]).append("'");
			}
			if(i<ids.length-1){
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 根据正则把结果存到list里面
	 * @param matcher
	 * @return
	 */
	public static List<String> getListFormMatcher(Matcher matcher){
		List<String> resultList = new ArrayList<String>();
		while (true) {
        	if(matcher.find()){
        		int count = matcher.groupCount();
        		for (int i = 0; i <= count; i++) {
        			resultList.add(matcher.group(i));
				}
        	}else{
        		break;
        	}
        } 
		return resultList;
	}
	
	/**
	 * 根据两个标识把字符串转换成map
	 * @param queryString 要转换的字符串
	 * @param mark1 标识1
	 * @param mark2 标识2
	 * @return map
	 */
	public static Map<String, Object> getParamMap(String queryString, String mark1, String mark2){
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	String[] queryArr = queryString.split(mark1);
    	for (String query : queryArr) {
			String[] quArr = query.split(mark2);
			paramMap.put(quArr[0], quArr[1]);
		}
    	return paramMap;
	}
}
