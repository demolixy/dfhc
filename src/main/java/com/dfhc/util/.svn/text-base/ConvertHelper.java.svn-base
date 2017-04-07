package com.dfhc.util;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;



/**
 * 转换构造器，用于变量类型间的转换<br>
 * 提供方法toInt(),regexString(),toBoolean(),serialObject(),toXml(),fromXml()等方法
 * 
 * @author 龙色波 2010-02-04
 */
@SuppressWarnings("unchecked")
public class ConvertHelper {
	/**
	 * 定义CHARACTER_NUMBER_ALL为不可再赋值的静态变量，值为字符串\\w\\S*
	 */
	public final static String CHARACTER_NUMBER_ALL = ".*\\w\\S*";
	public final static String CHARACTER_CHINESE_ALL = ".*([\u4E00-\u9FA5]|[\uFE30-\uFFA0]).*";

	// private static Locale[] locales = Locale.getAvailableLocales();

	public static Object toValue(String str, Class<?> clz) {
		if (clz.equals(String.class))
			return str;
		if (clz.equals(Double.class))
			return new Double(str);
		if (clz.equals(Integer.class))
			return new Integer(str);
		if (clz.equals(Float.class))
			return new Float(str);

		return str;
	}

	public static Integer toInteger(int i) {
		return Integer.valueOf(i);
	}

	public static boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 静态方法,toInt()方法重载将double 转换为int 类型
	 * 
	 * @param doub
	 *            用 Double 表示的值
	 * @return 该值被转换为 int 类型该对象表示的数值
	 */
	public static int toInt(double doub) {
		return Double.valueOf(doub).intValue();
	}
	/**
	 * 静态方法,toInt()方法重载将long 转换为int 类型
	 * 
	 * @param long
	 *            用 Long 表示的值
	 * @return 该值被转换为 int 类型该对象表示的数值
	 */
	public static int toInt(long l) {
		return Long.valueOf(l).intValue();
	}
	/**
	 * 静态布尔方法,判断字符串是否匹配给定的正则表达式
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @param regex
	 *            用来匹配字符串的正则表达式
	 * @return
	 */
	public static boolean regexString(String str, String regex) {
		if (str.trim().matches(regex))
			return true;
		else
			return false;
	}

	/**
	 * 静态布尔方法,将String 转换为基本 boolean 值
	 * 
	 * @param str
	 *            要转换的String
	 * @return false或对象的基本 boolean 值
	 */
	public static boolean toboolean(String str) {
		if (str == null)
			return false;

		return Boolean.valueOf(str);
	}

	/**
	 * 静态布尔方法,将String 转换为基本 Boolean 值
	 * 
	 * @param str
	 *            要转换的String
	 * @return false或对象的基本 Boolean 值
	 */
	public static boolean toBoolean(String str) {
		if (str == null)
			return false;

		return Boolean.valueOf(str);
	}

	/**
	 * 将字符串数组转换为List
	 * 
	 * @param objs
	 *            对象数组
	 * @return List 返回转换后List
	 */

	public static List toList(String strs[]) {
		List retList = new ArrayList();
		if (strs != null) {
			for (int i = 0; i < strs.length; i++)
				retList.add(strs[i]);
		}
		return retList;
	}

	/**
	 * 将哈希表转换为List
	 * 
	 * @param map
	 * @return List 返回转换后List
	 */
	public static List toList(Map map) {
		return new ArrayList(map.values());
	}

	/**
	 * 将格式为{data1},{data2},....{datan}.去掉{},把data1,data2,...datan放入列表返回
	 * 
	 * @param str
	 * @return
	 */
	public static List toList(String str) {
		List retList = new ArrayList();
		String strDatas[] = StringHelper.splitString(str, ",");
		int spos, epos;

		if (strDatas != null) {
			for (int i = 0; i < strDatas.length; i++) {
				spos = strDatas[i].indexOf("{");
				if (spos == -1)
					spos = 0;
				else
					spos = spos + 1;
				epos = strDatas[i].indexOf("}", spos);
				if (epos == -1)
					epos = strDatas[i].length();
				retList.add(strDatas[i].substring(spos, epos));
			}
		}
		return retList;
	}

	public static String toString(Object currentLeaf) {
		if (currentLeaf != null)
			return String.valueOf(currentLeaf);
		else
			return null;
	}
	public static String toString(int i) {
		return String.valueOf(i);
	}
	/**
	 * 将Double转换为字符串,并去掉科学计算法
	 * 
	 * @param d
	 * @param scale
	 *            精度
	 * @param trimzero
	 *            自动截断最后全0的数字,例如:3.000=>3
	 * @return
	 */
	public static String toString(double d, int scale, boolean trimzero) {
		StringBuffer format=new StringBuffer("###0");
		if (scale > 0) {
			format.append(".");
			for (int i = 0; i < scale; i++) {
				format.append("0");
			}
		}
		DecimalFormat a = new DecimalFormat(format.toString());
		String orgVal;
		orgVal = a.format(d);
		if (trimzero) {
			int i, pos;
			String postfix;
			pos = orgVal.indexOf('.');
			if (pos == -1)
				return orgVal;
			postfix = orgVal.substring(pos + 1);
			// 截断最后的一串0
			for (i = postfix.length() - 1; i >= 0; i--) {
				if (!postfix.substring(i, i + 1).equals("0"))
					break;
			}
			if (i < 0)
				return orgVal.substring(0, pos);
			else {
				orgVal = orgVal.substring(0, pos + 1)
						+ postfix.substring(0, i + 1);
			}
		}
		return orgVal;
	}

	/**
	 * 将Double转换为字符串,并去掉科学计算法
	 * 
	 * @param d
	 * @param scale
	 *            精度
	 * @return
	 */
	public static String toString(Double d, int scale, boolean trimzero) {
		return toString(d.doubleValue(), scale, trimzero);
	}

	

	/**
	 * 将List转换为Comparable数组
	 * 
	 * @param parameters
	 * @return
	 */
	public static Comparable[] toArray(List<Comparable> parameters) {
		Comparable objs[];
		int i = 0;
		if (parameters != null) {
			objs = new Comparable[parameters.size()];
			for (Comparable o : parameters) {
				objs[i++] = o;
			}
			return objs;
		}
		return null;
	}

	/**
	 * 将List转换为类数组
	 * 
	 * @param parameters
	 * @return
	 */
	public static Class<?>[] toClassArrayFromObjects(List<Object> parameters) {
		Class<?> objs[];
		int i = 0;
		if (parameters != null) {
			objs = new Class<?>[parameters.size()];
			for (Object o : parameters) {
				objs[i++] = o.getClass();
			}
			return objs;
		}
		return null;
	}

	/**
	 * 将对象数组转换为字符串
	 * 
	 * @param objs
	 * @return
	 */
	public static String toString(Object[] objs) {
		if (objs == null) {
			return "null";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		for (int i = 0; i < objs.length; i++) {
			buffer.append(toString(objs[i]));
			if (i < objs.length - 1)
				buffer.append(",");
		}
		buffer.append("}");
		return buffer.toString();
	}

	/**
	 * 判断字符串串是否不包含乱码
	 * 
	 * @param value
	 * @return
	 */
	public static boolean regexString(String value) {
		if (!StringHelper.isEmpty(value)) {
			if (value.trim().matches(CHARACTER_NUMBER_ALL))
				return true;
			else if (value.trim().matches(CHARACTER_CHINESE_ALL))
				return true;
			else
				return false;
		} else
			return true;
	}

	/**
	 * 将原始方法转换为不带括号的方法名
	 * 
	 * @param strOrgMethod
	 * @return
	 */
	public static String toSimpleMethod(String strOrgMethod) {
		if (StringHelper.isEmpty(strOrgMethod))
			return strOrgMethod;
		int leftPos = strOrgMethod.indexOf("(");
		int rightPos = strOrgMethod.indexOf(")");
		if (leftPos > -1) {
			return strOrgMethod.substring(0, leftPos);
		} else if (rightPos > -1) {
			return strOrgMethod.substring(0, rightPos);
		} else {
			return strOrgMethod;
		}
	}

	/**
	 * 将字符串转换为Character 对象
	 * 
	 * @param value
	 * @return
	 */
	public static Character toChar(String value) {
		if (value == null || value.length() == 0) {
			return null;
		} else {
			return Character.valueOf(value.charAt(0));
		}
	}

	/**
	 * 将像素在指定的分辨率下转换为毫米
	 * 
	 * @param px
	 * @param resolution
	 *            一般为150
	 * @return
	 * @remark 一英寸=2.54厘米
	 */
	public static double pt2mm(int px, int resolution) {
		return ((double)px / (double)resolution) * 25.4;
	}

	/**
	 * Converts millimeters (mm) to points (pt)
	 * 
	 * @param mm
	 *            the value in mm
	 * @return the value in pt
	 */
	public static double mm2pt(double mm) {
		return mm * 2.835;
	}

	/**
	 * Converts points (pt) to millimeters (mm)
	 * 
	 * @param pt
	 *            the value in pt
	 * @return the value in mm
	 */
	public static double pt2mm(double pt) {
		return pt / 2.835;
	}

	/**
	 * Converts millimeters (mm) to inches (in)
	 * 
	 * @param mm
	 *            the value in mm
	 * @return the value in inches
	 */
	public static double mm2in(double mm) {
		return mm / 25.4;
	}

	/**
	 * Converts inches (in) to millimeters (mm)
	 * 
	 * @param in
	 *            the value in inches
	 * @return the value in mm
	 */
	public static double in2mm(double in) {
		return in * 25.4;
	}

	/**
	 * Converts millimeters (mm) to pixels (px)
	 * 
	 * @param mm
	 *            the value in mm
	 * @param resolution
	 *            the resolution in dpi (dots per inch)
	 * @return the value in pixels
	 */
	public static int mm2px(double mm, int resolution) {
		return (int) Math.round(mm2in(mm) * resolution);
	}

	/**
	 * 将int转换为short
	 * 
	 * @param value
	 * @return
	 */
	public static short toShort(int value) {
		return (short) value;
	}

	/**
	 * 将int转换为byte
	 * 
	 * @param value
	 * @return
	 */
	public static byte toByte(int value) {
		return (byte) value;
	}

	/**
	 * 将整数转换为十六进制串
	 * 
	 * @param r
	 * @return
	 */
	public static String toHex(int i) {
		if (i < 16)
			return toHexChar(i);
		List<Integer> result = new ArrayList<Integer>();
		int quotient, residue;
		while (i > 16) {
			quotient = i / 16;
			residue = i % 16;
			if (quotient < 16) {
				result.add(0, quotient);
			}
			if (residue < 16) {
				result.add(0, residue);
				break;
			}
			// 替换被除数
			i = quotient;
		}
		StringBuffer buffer = new StringBuffer();
		for (int j = 0; j < result.size(); j++) {
			buffer.append(toHexChar(result.get(j)));
		}
		return buffer.toString();
	}

	/**
	 * 将0~15的数字转换为十六进字符
	 * 
	 * @param i
	 * @return
	 */
	private static String toHexChar(int i) {
		char ch;
		if (i >= 0 && i <= 9)
			return toString(i);
		else if (i >= 10 && i <= 15) {
			ch = (char) ('A' - 10 + i);
			return toString(ch);
		}
		return "";
	}

	/**
	 * 将对象转换为布尔型
	 * 
	 * @param obj
	 *            对象
	 * @return
	 */
	public static boolean toBoolean(Object obj) {
		if (obj != null) {
			if (!(obj instanceof Boolean))
				return toBoolean(String.valueOf(obj));
			else
				return (Boolean) obj;
		}
		return false;
	}

	/**
	 * 转换对象为时区
	 * 
	 * @param obj
	 * @return
	 */
	public static TimeZone toTimeZone(Object obj) {
		if (obj != null) {
			if (!(obj instanceof TimeZone)) {
				return TimeZone.getTimeZone(String.valueOf(obj));
			} else {
				return (TimeZone) obj;
			}
		} else
			return null;
	}

	/**
	 * 根据timeZoneID构造TimeZone对象
	 * 
	 * @param id
	 * @return
	 */
	public static TimeZone toTimeZoneByID(String id) {
		if (StringHelper.isEmpty(id)) {
			return TimeZone.getDefault();
		} else {
			return TimeZone.getTimeZone(id);
		}
	}

	/**
	 * 将Double列表转换为double数组
	 * 
	 * @param list
	 * @return
	 */
	public static double[] todouble(List<Double> list) {
		if (list != null) {
			double doubleArray[] = new double[list.size()];
			for (int i = 0; i < list.size(); i++) {
				doubleArray[i] = list.get(i).doubleValue();
			}
			return doubleArray;
		} else
			return null;
	}

	/**
	 * 将对象转换为Int
	 * 
	 * @param obj
	 *            对象
	 * @return
	 * @throws HelperException
	 */
	public static int toInt(Object obj) throws Exception {
		if (obj == null) {
			throw new Exception("objIsNull");
		}

		if (obj instanceof Double) {
			// 四舍五入
			return new BigDecimal((Double) obj).setScale(0,
					BigDecimal.ROUND_HALF_UP).intValue();
		} else if (obj instanceof Float) {
			// 四舍五入
			return new BigDecimal((Float) obj).setScale(0,
					BigDecimal.ROUND_HALF_UP).intValue();
		} else {
			return toInt(toString(obj));
		}
	}

	/**
	 * 静态方法,将String 转换为int 类型
	 * 
	 * @param str
	 *            要转换为 Integer 的 String
	 * @return 转换为 int 类型后该对象表示的数值
	 * @throws HelperException
	 */
	public static int toInt(String str) throws Exception {
		try {
			return new Integer(str).intValue();
		} catch (NumberFormatException e) {
			throw new Exception("Integer convertError!Reason:"+ e.getMessage());
		}
	}

	/**
	 * 将字符串转换为Integer
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static Integer toInteger(String value) throws Exception {
		try {
			return Integer.valueOf(value);
		} catch (NumberFormatException e) {
			throw new Exception("Integer convertError!Reason:"+ e.getMessage());
		}
	}
	/**
	 * 空指针转为""
	 * @param value
	 * @return
	 */
	public static String nullToString(String value){
		if(value==null){
			return "";
		}else{
			return value;
		}
	}
	/**
	 * 静态方法,将String 转换为long 类型
	 * 
	 * @param str
	 *            要转换为 Long 的 String
	 * @return 转换为 long 类型后该对象表示的数值
	 * @throws HelperException
	 */
	public static long toLong(String str) throws Exception {
		try {
			return new Long(str).longValue();
		} catch (NumberFormatException e) {
			throw new Exception("Long convertError!Reason:"+ e.getMessage());
		}
	}
	/**
	 * 将字节数组转换为字节输入流
	 * @param taskData 任务数据
	 * @return
	 */
	public static ByteArrayInputStream toStream(byte[] taskData) {
		return new ByteArrayInputStream(taskData);
	}
	/**
	 * 将字符串转换为BigDecimal 
	 * @param val
	 * @return
	 * @throws Exception 
	 */
	public static BigDecimal toBigDecimal(String val) throws Exception{
		try{
			return new BigDecimal(val);
		}catch(NumberFormatException e){
			throw new Exception("BigDecimal convertError!Reason:"+ e.getMessage());
		}
	}
	/**
	 * 将int转换为BigDecimal
	 * @param i
	 * @return
	 */
	public static BigDecimal toBigDecimal(int i) {
		return BigDecimal.valueOf(i);
	}
	/**
	 * 将字符串转换为BigDecimal 
	 * @param val
	 * @return
	 * @throws Exception 
	 */
	public static BigInteger toBigInt(String val) throws Exception{
		try{
			return new BigInteger(val);
		}catch(NumberFormatException e){
			throw new Exception("BigDecimal convertError!Reason:"+ e.getMessage());
		}
	}
	/**
	 * 将BigDecimal转换为字符串
	 * @param val
	 * @param scale
	 * @return
	 */
	public static String toString(BigDecimal val,int scale){
		if(val!=null){
			return val.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
		}else{
			//空对象,当0处理
			return "0";
		}
	}
	/**
	 * 将字符串格式的日期按指定格式转换为java.util.Date对象
	 * @param strDate 字符串日期
	 * @param dateFmt 日期格式
	 * @return
	 * @throws ParseException 
	 */
	public static Date toUtilDate(String strDate, String dateFmt) throws ParseException {		
		return DateUtils.parseDate(strDate, new String[]{dateFmt});
	}
}
