package com.dfhc.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.quickbundle.project.RmProjectHelper;

/**
 * @description 时间控制类,主要涵盖以下内容 1. 获取当前系统时间 2.
 *              获取指定日期增加指定年(月||日||时||分||秒||毫秒)后的日期,负数则减去日期 3. 日期按指定方式截取 4.
 *              获取指定格式日期 5. 日期比较 6. 根据所给年、月、日、时、分、秒，得到日期 7. 根据所给年、月、日，检验是否为合法日期。
 *              8.验证字符串日期，按指定日期格式，是否合法 9.获取当前时间的以前的或者当前日期今后的每一天的同一时间点,函数：getDayByDay(Date,int)。
 * @author longsebo
 * 
 */
public class DateUtil {
	public static SimpleDateFormat yymmddsdf = new SimpleDateFormat("yyyyMMdd");
	/**
	 * @description 获取当前系统时间
	 * @return 当前系统时间
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}
	
	/**
	 * @description 获取当前系统时间
	 * @return 当前系统时间
	 */
	public static Timestamp getNowTimestamp(){
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return new Timestamp(currentTime_2.getTime());
	}
	
	/**
	 * 日期到 timestamp 转换
	 * @param date
	 */
	public static Timestamp date2timestamp(Date date){
		return new Timestamp(date.getTime());
	}
	
	/**
	 * timestamp 到日期  转换
	 * @param date
	 */
	public static Date timestamp2date(Timestamp ts){
		return new Date(ts.getTime());
	}

	/**
	 * timestamp 到日期字符串  转换
	 * @param date
	 */
	public static String timestamp2String(Timestamp ts){
		return DateUtil.getDateStr(new Date(ts.getTime()),"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 
	 * 方法功能描述: 取当前日期或时间
	 * 
	 * @param 参数名
	 *            参数作用描述 …
	 * @return 返回类型 返回值描述，如果有返回值，则写。没有不用写，可以描述什么情况 返回什么值
	 * @remark 该方法使用注意事项描述 是可选
	 */
	public static String getNowDate(String pattern) {
		return DateUtil.getDateStr(new Date(), pattern);
	}

	/**
	 * @description 获取指定格式当前系统时间
	 * @return 当前系统时间
	 */
	public static Date getFormatedNowDate(String pattern) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * @description 获取指定日期增加指定年数后的日期
	 * @param date
	 *            指定日期
	 * @param amount
	 *            指定年数
	 * @return 返回指定日期增加指定年数后的日期
	 */
	public static Date addYears(Date date, int amount) {
		return DateUtils.addYears(date, amount);
	}

	/**
	 * @description 获取指定日期增加指定月数后的日期
	 * @param date
	 *            指定日期
	 * @param amount
	 *            指定年数
	 * @return 返回指定日期增加指定月数后的日期
	 */
	public static Date addMonths(Date date, int amount) {
		return DateUtils.addMonths(date, amount);
	}

	/**
	 * @description 获取指定日期增加指定周数后的日期
	 * @param date
	 *            指定日期
	 * @param amount
	 *            指定年数
	 * @return 返回指定日期增加指定周数后的日期
	 */
	public static Date addWeeks(Date date, int amount) {
		return DateUtils.addWeeks(date, amount);
	}

	/**
	 * @description 获取指定日期增加指定天数后的日期
	 * @param date
	 *            指定日期
	 * @param amount
	 *            指定年数
	 * @return 返回指定日期增加指定天数后的日期
	 */
	public static Date addDays(Date date, int amount) {
		return DateUtils.addDays(date, amount);
	}
	
	/**
	 * @description 获取指定日期增加指定天数后的日期
	 * @param date
	 *            指定日期"yyyyMMdd"
	 * @param amount
	 *            增加天数
	 * @return 返回指定日期增加指定天数后的日期
	 * @throws ParseException 
	 */
	
	public static String addDays(String date,int amount) throws ParseException{
		return yymmddsdf.format(addDays(yymmddsdf.parse(date),amount));
	}
	
	public static String getNxtDay(String date) throws ParseException{
		return yymmddsdf.format(addDays(yymmddsdf.parse(date),1));
	}

	/**
	 * @description 获取指定日期增加指定小时数后的日期
	 * @param date
	 *            指定日期
	 * @param amount
	 *            指定年数
	 * @return 返回指定日期增加指定小时数后的日期
	 */
	public static Date addHours(Date date, int amount) {
		return DateUtils.addHours(date, amount);
	}

	/**
	 * @description 获取指定日期增加指定分钟数后的日期
	 * @param date
	 *            指定日期
	 * @param amount
	 *            指定年数
	 * @return 返回指定日期增加指定分钟数后的日期
	 */
	public static Date addMinutes(Date date, int amount) {
		return DateUtils.addMinutes(date, amount);
	}

	/**
	 * @description 获取指定日期增加指定秒数后的日期
	 * @param date
	 *            指定日期
	 * @param amount
	 *            指定年数
	 * @return 返回指定日期增加指定秒数后的日期
	 */
	public static Date addSeconds(Date date, int amount) {
		return DateUtils.addSeconds(date, amount);
	}

	/**
	 * @description 获取指定日期增加指定毫秒数后的日期
	 * @param date
	 *            指定日期
	 * @param amount
	 *            指定年数
	 * @return 返回指定日期增加指定毫秒数后的日期
	 */
	public static Date addMilliseconds(Date date, int amount) {
		return DateUtils.addMilliseconds(date, amount);
	}

	/**
	 * @description 指定日期格式的区域field,将指定日期date解析为:指定日期格式区域和区域之前的日期保留, 区域后的日期置为0;
	 *              e.g ceiling(new
	 *              Date("28 Mar 2002 13:45:01.231"),Calander.HOUR)
	 *              将返回小时区域及小时区域之前的时间段,后面的日期记为0,即28 Mar 2002 13:00:00.000;
	 * @param date
	 *            需要解析的日期
	 * @param field
	 *            需要保留日期区域的结束点 : Calendar.YERA;Calendar.MONTH;Calendar.DAYS 等;
	 * @return 返回小时区域及小时区域之前的时间段,后面的日期记为0
	 */
	public static Date ceiling(Date date, int field) {
		return DateUtils.ceiling(date, field);
	}

	/**
	 * @description 判断两日期是否是同一天(不包括时分秒)
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		return DateUtils.isSameDay(date1, date2);
	}

	/**
	 * @description 判断两日期是否是同一时间(精确位到秒)
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameInstant(Date date1, Date date2) {
		return DateUtils.isSameInstant(date1, date2);
	}

	/**
	 * @description 将date类型转换为Calendar;
	 * @param date
	 * @return
	 */
	public static Calendar toCalendar(Date date) {
		return DateUtils.toCalendar(date);
	}

	/**
	 * @description 返回fragment内的毫秒数; January 1, 2008 7:15:10.538 with
	 *              Calendar.SECOND as fragment will return 538; January 6, 2008
	 *              7:15:10.538 with Calendar.MINUTE as fragment will return
	 *              10538 (10*1000 + 538);
	 * @param date
	 *            指定日期
	 * @param fragment
	 *            日期范围: Calendar.YERA;Calendar.MONTH;Calendar.DAYS 等;
	 * @return
	 */
	public static long getFragmentInMilliseconds(Date date, int fragment) {
		return DateUtils.getFragmentInMilliseconds(date, fragment);
	}

	/**
	 * @description: see getFragmentInMilliseconds
	 * @param date
	 *            指定日期
	 * @param fragment
	 *            日期范围: Calendar.YERA;Calendar.MONTH;Calendar.DAYS 等;
	 * @return
	 */
	public static long getFragmentInSeconds(Date date, int fragment) {
		return DateUtils.getFragmentInSeconds(date, fragment);
	}

	/**
	 * @description: see getFragmentInMilliseconds
	 * @param date
	 *            指定日期
	 * @param fragment
	 *            日期范围: Calendar.YERA;Calendar.MONTH;Calendar.DAYS 等;
	 * @return
	 */
	public static long getFragmentInMinutes(Date date, int fragment) {
		return DateUtils.getFragmentInMinutes(date, fragment);
	}

	/**
	 * @description: see getFragmentInMilliseconds
	 * @param date
	 *            指定日期
	 * @param fragment
	 *            日期范围: Calendar.YERA;Calendar.MONTH;Calendar.DAYS 等;
	 * @return
	 */
	public static long getFragmentInHours(Date date, int fragment) {
		return DateUtils.getFragmentInHours(date, fragment);
	}

	/**
	 * @description: see getFragmentInMilliseconds
	 * @param date
	 *            指定日期
	 * @param fragment
	 *            日期范围: Calendar.YERA;Calendar.MONTH;Calendar.DAYS 等;
	 * @return
	 */
	public static long getFragmentInDays(Date date, int fragment) {
		return DateUtils.getFragmentInDays(date, fragment);
	}

	/**
	 * @description 比较两个日期在指定日期范围内是否一致
	 * @param date1
	 * @param date2
	 * @param field
	 *            日期范围 : Calendar.YERA;Calendar.MONTH;Calendar.DAYS 等;
	 * @return
	 */
	public static boolean truncatedEquals(Date date1, Date date2, int field) {
		return DateUtils.truncatedEquals(date1, date2, field);
	}

	/**
	 * @description 比较两个日期在指定日期范围内的大小
	 * @param date1
	 * @param date2
	 * @param field
	 * @return d1<d2 返回-1; d1=d2 返回0; d1>d2 返回1
	 */
	public static int truncatedCompareTo(Date date1, Date date2, int field) {
		return DateUtils.truncatedCompareTo(date1, date2, field);
	}

	/**
	 * @description 按指定格式获取指定时间字符串
	 * @param date
	 *            待转换日期
	 * @param patterns
	 *            时间格式 e.g yyyy-MM-dd HH:mm:ss
	 * @return 返回指定格式指定时间字符串
	 */
	public static String getDateStr(Date date, String patterns) {
		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat formatter = new SimpleDateFormat(patterns);
		String dateString = formatter.format(date);
		return dateString;
	}
	/**
	 * @author 徐鹏宇
	 * @description 按指定格式获取指定时间字符串
	 * @param date
	 *            待转换日期
	 * @param patterns
	 *            时间格式 e.g yyyy-MM-dd HH:mm:ss
	 * @return 返回指定格式指定时间字符串
	 */
	public static String getDateStrExNull(java.sql.Date date, String patterns) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(patterns);
		String dateString = formatter.format(new Date(date.getTime()));
		return dateString;
	}
	/**
	 * @description 将待转换字符串日期根据指定格式转换为日期类型, 日期字符串涵盖的日期格式需要与指定的格式一致
	 * @param strDate
	 *            待转换字符串
	 * @param patterns
	 *            日期转换格式
	 * @return 返回指定字符串日期根据指定格式转换后的日期类型对象;
	 */
	public static Date getStrDate(String strDate, String patterns) {
		if (StringUtils.isBlank(strDate)) {
			return new Date();
		}
		SimpleDateFormat formatter = new SimpleDateFormat(patterns);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	public static void main(String[] args){
		System.out.print(getStrDate("23:23:11","hh:mm:ss"));
	}
	

	/**
	 * @description 获取间隔时间
	 * @param d1
	 *            开始时间
	 * @param d2
	 *            结束时间
	 * @return
	 */
	public static int getDaysBetween(java.util.Calendar d1,
			java.util.Calendar d2) {
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(java.util.Calendar.DAY_OF_YEAR)
				- d1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = d2.get(java.util.Calendar.YEAR);
		if (d1.get(java.util.Calendar.YEAR) != y2) {
			d1 = (java.util.Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				d1.add(java.util.Calendar.YEAR, 1);
			} while (d1.get(java.util.Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * @description 获取间隔时间
	 * @param start_date
	 *            开始时间
	 * @param end_date
	 *            结束时间
	 * @param pattern
	 *            日期格式
	 * @return
	 */
	public static int getDaysBetweenByStr(String start_date, String end_date,
			String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date s_date = null;
		Date e_date = null;
		try {
			s_date = sdf.parse(start_date);
			e_date = sdf.parse(end_date);
		} catch (ParseException e) {
			RmProjectHelper.logError("系统异常", e);
		}
		Calendar cal_start = toCalendar(s_date);
		Calendar cal_end = toCalendar(e_date);
		return getDaysBetween(cal_start, cal_end);
	}

	/**
	 * @description 根据所给年、月、日、时、分、秒，得到日期(java.sql.Date类型)。
	 *              年、月、日不合法会抛IllegalArgumentException
	 * 
	 * @param yyyy
	 *            4位年
	 * @param MM
	 *            月
	 * @param dd
	 *            日
	 * @param HH
	 *            时
	 * @param mm
	 *            分
	 * @param ss
	 *            秒
	 * 
	 * @return
	 */
	public static java.sql.Date getSqlTypeDate(int yyyy, int MM, int dd,
			int HH, int mm, int ss) {
		if (!verityDate(yyyy, MM, dd)) {
			throw new IllegalArgumentException("不是有效的时间");
		}

		Calendar oneCalendar = Calendar.getInstance();
		oneCalendar.clear();
		oneCalendar.set(yyyy, MM - 1, dd, HH, mm, ss);
		return new java.sql.Date(oneCalendar.getTime().getTime());
	}

	/**
	 * @description 根据所给年、月、日、时、分、秒，得到日期(java.util.Date类型)。
	 *              年、月、日不合法会抛IllegalArgumentException
	 * 
	 * @param yyyy
	 *            4位年
	 * @param MM
	 *            月
	 * @param dd
	 *            日
	 * @param HH
	 *            时
	 * @param mm
	 *            分
	 * @param ss
	 *            秒
	 * 
	 * @return
	 */
	public static java.util.Date getUtilTypeDate(int yyyy, int MM, int dd,
			int HH, int mm, int ss) {
		if (!verityDate(yyyy, MM, dd)) {
			throw new IllegalArgumentException("不是有效的时间");
		}

		Calendar oneCalendar = Calendar.getInstance();
		oneCalendar.clear();
		oneCalendar.set(yyyy, MM - 1, dd, HH, mm, ss);
		return new java.util.Date(oneCalendar.getTime().getTime());
	}

	/**
	 * @description 重写上面getSqlTypeDate方法 根据所给年、月、日，得到日期(java.sql.Date类型)。
	 *              年、月、日不合法会抛IllegalArgumentException
	 * @param yyyy
	 *            4位年
	 * @param MM
	 *            月
	 * @param dd
	 *            日
	 * 
	 * @return
	 */
	public static java.sql.Date getSqlTypeDate(int yyyy, int MM, int dd) {
		if (!verityDate(yyyy, MM, dd)) {
			throw new IllegalArgumentException("不是有效的时间");
		}

		Calendar oneCalendar = Calendar.getInstance();
		oneCalendar.clear();
		oneCalendar.set(yyyy, MM - 1, dd);
		return new java.sql.Date(oneCalendar.getTime().getTime());
	}

	/**
	 * @description 根据所给年、月、日、时、分、秒，得到日期(java.util.Date类型)。
	 *              年、月、日不合法会抛IllegalArgumentException
	 * 
	 * @param yyyy
	 *            4位年
	 * @param MM
	 *            月
	 * @param dd
	 *            日
	 * 
	 * @return
	 */
	public static java.util.Date getUtilTypeDate(int yyyy, int MM, int dd) {
		if (!verityDate(yyyy, MM, dd)) {
			throw new IllegalArgumentException("不是有效的时间");
		}

		Calendar oneCalendar = Calendar.getInstance();
		oneCalendar.clear();
		oneCalendar.set(yyyy, MM - 1, dd);
		return new java.util.Date(oneCalendar.getTime().getTime());
	}

	/**
	 * @description 转换字符串格式日期为java.sql.Date日期，如果原始字符串为null则返回null。
	 *              自动支持从以下格式字符串转换：<br>
	 *              1、yyyyMMddHHmmss格式；2、yyyyMMdd格式；3、yyyy-MM-dd HH:mm:ss格式
	 *              ；4、yyyy-MM-dd格式
	 * @param s
	 *            需要转换的字符串
	 * @return java.sql.Date 转换完成的java.sql.Date类型对象
	 */
	public static java.sql.Date converToDate(String s) throws Exception {
		if (s == null || s.trim().equals("")) {
			return null;
		}

		if (s.length() == 6) {
			s = s + "01";
		}

		String yyyy = "", MM = "", dd = "";
		String HH = "00", mm = "00", ss = "00";
		int len = s.length();

		if (len == 8 && numberVerify(s)) {
			yyyy = s.substring(0, 4);
			MM = s.substring(4, 6);
			dd = s.substring(6, 8);
		} else if (len == 14) {
			yyyy = s.substring(0, 4);
			MM = s.substring(4, 6);
			dd = s.substring(6, 8);
			HH = s.substring(8, 10);
			mm = s.substring(10, 12);
			ss = s.substring(12, 14);
		} else {
			String val = "-";
			if (s.indexOf("/") >= 0) {
				val = "/";
			}
			if (s.indexOf(".") >= 0) {
				s = s.substring(0, s.indexOf("."));
			}

			String temp = "";
			boolean flag = false;

			if (s.indexOf(val) != -1) {
				yyyy = s.substring(0, s.indexOf(val));
				flag = true;
			}

			if (s.indexOf(val) != -1) {
				if (s.indexOf(val) + 1 < s.lastIndexOf(val)) {
					MM = s.substring(s.indexOf(val) + 1, s.lastIndexOf(val));
					flag = true;
				} else {
					throw new Exception("输入时间格式有误");
				}
			}

			if (s.indexOf(val) != -1) {
				temp = s.substring(s.lastIndexOf(val) + 1, s.length());
				flag = true;
			}

			if (flag == false) {
				throw new Exception("输入时间格式有误");
			}

			if (temp.indexOf(" ") > 0) {
				// 有时分秒
				dd = temp.substring(0, temp.indexOf(" "));
				temp = temp.substring(temp.indexOf(" ") + 1, temp.length());
				HH = temp.substring(0, temp.indexOf(":"));
				mm = temp.substring(temp.indexOf(":") + 1,
						temp.lastIndexOf(":"));
				ss = temp.substring(temp.lastIndexOf(":") + 1, temp.length());
			} else {
				dd = temp;
			}
		}

		return getSqlTypeDate(Integer.parseInt(yyyy), Integer.parseInt(MM),
				Integer.parseInt(dd), Integer.parseInt(HH),
				Integer.parseInt(mm), Integer.parseInt(ss));
	}

	/**
	 * @description 根据所给年、月、日，检验是否为合法日期。
	 * @param yyyy
	 *            4位年
	 * @param MM
	 *            月
	 * @param dd
	 *            日
	 * @return
	 */
	public static boolean verityDate(int yyyy, int MM, int dd) {
		boolean flag = false;

		if (MM >= 1 && MM <= 12 && dd >= 1 && dd <= 31) {
			if (MM == 4 || MM == 6 || MM == 9 || MM == 11) {
				if (dd <= 30) {
					flag = true;
				}
			} else if (MM == 2) {
				if (yyyy % 100 != 0 && yyyy % 4 == 0 || yyyy % 400 == 0) {
					if (dd <= 29) {
						flag = true;
					}
				} else if (dd <= 28) {
					flag = true;
				}
			} else {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * @description 将Date类型转换成Timestamp类型
	 * @param dt
	 * @return
	 */
	public static Timestamp convertToTimestamp(java.util.Date dt) {
		return dt == null ? null : new Timestamp(dt.getTime());
	}

	/**
	 * @description 得到当前月份的开始日期(以java.util.Date类型返回)
	 * @param currentDate
	 *            当前日期
	 * @return 当前月份的开始日期
	 * 
	 */
	public static Date getCurBeginCycleDate(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);

		String year = "" + calendar.get(Calendar.YEAR);
		String month = calendar.get(Calendar.MONTH) + 1 + "";
		if (month.length() < 2) {
			month = "0" + month;
		}
		String dateStr = year + "-" + month + "-01 00:00:00";
		return DateUtil.parser(dateStr);
	}

	/**
	 * @description 得到当前月份的开始日期(以String方式返回)
	 * @param currentDate
	 *            当前日期
	 * @return 当前月份的开始日期
	 * 
	 */
	public static String getCurBeginCycleDateString(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);

		String year = "" + calendar.get(Calendar.YEAR);
		String month = calendar.get(Calendar.MONTH) + 1 + "";
		if (month.length() < 2) {
			month = "0" + month;
		}
		String dateStr = year + "-" + month + "-01 00:00:00";
		return dateStr;
	}

	/**
	 * @description 取得当前月的结束日期
	 * @param currentDate
	 *            当前日期
	 * @return 当前月的结束日期
	 * 
	 */
	public static Date getCurEndCycleDate(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);

		String year = "" + calendar.get(Calendar.YEAR);
		String month = calendar.get(Calendar.MONTH) + 2 + "";
		if (month.length() < 2) {
			month = "0" + month;
		}
		String dateStr = year + "-" + month + "-01 23:59:59";
		calendar.setTime(DateUtil.parser(dateStr));
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * @description 获取某年某月的最后一天
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 最后一天(日期中天的数字值，如2月29日中的29)
	 */
	private static int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	/**
	 * @description 根据一个日期，返回是星期几的字符串
	 * @param sdate
	 *            时间格式的字符串，允许形式为:yyyy-MM-dd,yyyy-MM-dd hh:mm:ss
	 * @return String 星期几的汉语字符串
	 */
	public static String getWeek(String sdate) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(sdate, pos);

		Calendar c = Calendar.getInstance();
		c.setTime(strtodate);

		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * @description 获得本年有多少天
	 * @return int 本年有多少天数
	 * 
	 */
	public static int getMaxYear() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		return MaxYear;
	}

	/**
	 * @description 获得本年第一天的日期
	 * @return preYearDay 本年第一天的日期
	 * 
	 */
	public static String getCurrentYearFirst() {
		int yearPlus = getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preYearDay = df.format(yearDay);
		return preYearDay;
	}

	/**
	 * @description 获得本年最后一天的日期
	 * @return years 本年最后一天的日期
	 * 
	 */
	public static String getCurrentYearEnd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		return years + "-12-31";
	}

	/**
	 * @description 获取本季度最后一天 返回yyyyMMdd
	 * @param dateFormat
	 *            日期格式，只允许yyyyMMdd yyyy-MM-dd
	 * @return String 获取本季度最后一天
	 * 
	 */
	//
	public static String getThisLastDaySeason(String dateFormat) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(new Date());
		int month = cd.get(Calendar.MONTH);
		return getThisLastDaySeason(month + 1, dateFormat);
	}

	/**
	 * 
	 * @description 获取当前日期所在月的第一天
	 * @return 当前日期所在月的第一天的字符串
	 */
	public static String getCurrFirstDayOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}
	/**
	 * 
	 * @description 获取指定日期所在月的第一天
	 * @param date utildate 对象
	 * @param dateFmt 日期格式
	 * @return 指定日期所在月的第一天的字符串
	 */
	public static String getFirstDayOfMonth(java.util.Date date,String dateFmt) {
		SimpleDateFormat format = new SimpleDateFormat(dateFmt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}
	/**
	 * @description 获取指定日期所在周的最后一天日期(周的最后一天应是周日的日期)
	 * @param strDate
	 *            日期字符串，只允许yyyyMMdd,yyyy-MM-dd
	 * @return 指定日期所在周的最后一天日期的字符串
	 * 
	 */
	public static String getLastDayOfWeek(String strDate, String showFormat) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parser(strDate, showFormat));
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		String[] s = getDateOfThisWeek(calendar);
		return s[1];
	}

	/**
	 * @description 获取指定日期所在周的最后一天日期
	 * @param cal
	 *            指定的日期
	 * @return result 指定日期所在周的最后一天日期
	 * 
	 */
	public static String[] getDateOfThisWeek(Calendar cal) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Calendar cal = Calendar.getInstance();
		int index = cal.get(Calendar.DAY_OF_WEEK); // 今天是本周的第几天
		// 转成中国的习惯,如果是第一天,则转化为第七天(星期天外国为一周的第一天,而中国为每周的最后一天)
		if (index == 1)
			index = 8;
		cal.add(Calendar.DATE, -(index - 2));

		String start = (sdf.format(cal.getTime()));

		cal.add(Calendar.DATE, 6);
		String end = (sdf.format(cal.getTime()));
		String[] result = new String[] { start, end };
		return result;
	}

	/**
	 * @description 获得当前日期所在周的第一天的日期
	 * @return s 当前日期所在周的第一天的日期
	 * 
	 */
	public static String getCurrFirstDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		String[] s = getDateOfThisWeek(cal);
		return s[0];
	}

	/**
	 * @description 获得当前月的最后一天
	 * @return s 当前月的最后一天
	 * 
	 */
	public static String getCurrLastDayOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}
	/**
	 * @description 获得指定日期所在月的最后一天
	 * @param date util date
	 * @param dateFmt  日期格式
	 * @return s 当前月的最后一天
	 */
	public static String getLastDayOfMonth(java.util.Date date,String dateFmt) {
		SimpleDateFormat format = new SimpleDateFormat(dateFmt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}
	/**
	 * @description 获得当前月的第一天
	 * @return s 当前月的最后一天 的字符串
	 * 
	 */
	public static String getLastLastDayOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}

	/**
	 * @description 获得上N个月的第一天 (如果想获得下N个月的第一天，就输入负数)
	 * @return s 上N个月的最后一天 的字符串
	 * 
	 */
	public static String getLastFirstDayOfMonth(int n) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - n);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}

	/**
	 * @description 获得上个月的最后一天
	 * @return s 上个月的最后一天 的字符串
	 * 
	 */
	public static String getLastedLastDayOfMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}

	/**
	 * @description 获得上N月的最后一天(如果想获得下N个月的第一天，就输入负数)
	 * @return s 获得上N个月的最后一天 的字符串
	 * 
	 */
	public static String getLastedLastDayOfMonth(int n) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - n);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}

	/**
	 * @description 获得本年的第一天
	 * @return s 本年的第一天的字符串
	 * 
	 */
	public static String getCurrFirstDayOfYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
		return format.format(calendar.getTime());
	}

	/**
	 * @description 获得去年的第一天
	 * @return s 去年的第一天的字符串
	 * 
	 */
	public static String getLastedFirstDayOfYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, year - 2);
		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
		return format.format(calendar.getTime());
	}

	/**
	 * @description 获得N年前的第一天(如果想获得以后N年的第一天，就输入负数)
	 * @return s 去年的第一天的字符串
	 * 
	 */
	public static String getLastedFirstDayOfYear(int n) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, year - n);
		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
		return format.format(calendar.getTime());
	}

	/**
	 * @description 获得本年的最后一天
	 * @return s 本年的最后一天的字符串
	 * 
	 */
	public static String getLastedLastDayOfYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, year - 2);
		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
		return format.format(calendar.getTime());
	}

	/**
	 * @description 获得N年前的最后一天(如果想获得以后N年的最后一天，就输入负数)
	 * @param n
	 *            年数
	 * @return s 获得N年前的最后一天的字符串
	 * 
	 */
	public static String getLastedLastDayOfYear(int n) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, year - n);
		calendar.set(Calendar.DAY_OF_YEAR,
				calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
		return format.format(calendar.getTime());
	}

	/**
	 * @description 获取两个日期之间相差的月份数
	 * @param cal1
	 *            开始日期
	 * @param cal2
	 *            结束日期
	 * @param flag
	 *            false 为全月舍
	 * @return 返回的月份数
	 * 
	 */
	public static long getMonthsBetweenDate(Calendar cal1, Calendar cal2,
			boolean flag) {
		long month = 0L;
		while (cal1.before(cal2)) {
			cal1.add(Calendar.MONTH, 1);
			month++;
			if (flag) {
				if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
						&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
						&& cal1.get(Calendar.DAY_OF_MONTH) > cal2
								.get(Calendar.DAY_OF_MONTH)) {
					month--;
					break;
				}
				if (cal1.get(Calendar.MONTH) > cal2.get(Calendar.MONTH)
						&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)

				) {
					month--;
					break;
				}
			}
		}
		return month;
	}

	/**
	 * 
	 * description 格式化日期
	 * 
	 * @param testDate
	 *            符合格式的字符串
	 * @return 格式后的日期
	 * 
	 */
	public static Date parser(Date testDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(testDate);
		calendar.clear(Calendar.MILLISECOND);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.HOUR_OF_DAY);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(sdf.format(calendar.getTime()));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * description 格式化日期
	 * 
	 * @param strDate
	 *            符合格式的字符串
	 * @return 格式后的日期
	 * 
	 */
	public static Date parser(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * description 格式化日期
	 * 
	 * @param strDate
	 *            符合格式的字符串
	 * @return 格式后的日期
	 * 
	 */
	public static Date parser8Date(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * description 格式化日期
	 * 
	 * @param strDate
	 *            符合格式的字符串
	 * @param format
	 *            自定日期格式
	 * @return 格式后的日期
	 * 
	 */
	public static Date parser(String strDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			RmProjectHelper.logError("系统异常", e);
			return null;
		}
	}

	/**
	 * description 检证字符是否是数字
	 * 
	 * @param name
	 *            需要检证的字符
	 * @return 是否是数字
	 * 
	 */
	public static boolean numberVerify(String name) {
		if (name == null) {
			return false;
		}

		boolean isNumberChar = true;
		int i = 0;

		while ((isNumberChar) && (i < name.length())) {
			char x = name.charAt(i);
			if ((x < '0') || (x > '9'))
				isNumberChar = false;
			++i;
		}
		return isNumberChar;
	}

	/**
	 * description 是否闰年
	 * 
	 * @param year
	 *            年
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	public static int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	/**
	 * description 获取本季度最后一天 返回yyyyMMdd
	 * 
	 * @param year
	 *            月份的数字
	 * @param dateFormat
	 *            日期格式，只允许yyyyMMdd yyyy-MM-dd
	 * @return
	 */
	public static String getThisLastDaySeason(int month, String showFormat) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		String interStr = "";// 日期字符串中年月日之间间格的字符串
		SimpleDateFormat sdf = new SimpleDateFormat(showFormat);

		if (showFormat.equals("yyyy-MM-dd")) {
			interStr = "-";
		}

		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int end_month = array[season - 1][2];

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);

		int end_days = getLastDayOfMonth(years_value, end_month);
		String dateStr = years_value + interStr
				+ (end_month < 10 ? "0" + end_month : end_month) + interStr
				+ (end_days < 10 ? "0" + end_days : end_days);

		if (dateStr == null) {
			return null;
		}

		Date d = parser(dateStr, showFormat);

		String formatedDateStr = sdf.format(d);

		return formatedDateStr;
	}

	/**
	 * description 获取两个日期之间相差的月份数
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param flag
	 *            false 为全月舍
	 * @return 返回的月份数
	 */
	public static long getMonthsBetweenDate(Date startDate, Date endDate,
			boolean flag) {
		Calendar cal1 = Calendar.getInstance();

		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(startDate);
		cal2.setTime(endDate);
		if (endDate.before(startDate)) {
			cal1.setTime(endDate);
			cal2.setTime(startDate);
		}

		cal1.clear(Calendar.MILLISECOND);
		cal1.clear(Calendar.SECOND);
		cal1.clear(Calendar.MINUTE);
		cal1.clear(Calendar.HOUR_OF_DAY);

		cal2.clear(Calendar.MILLISECOND);
		cal2.clear(Calendar.SECOND);
		cal2.clear(Calendar.MINUTE);
		cal2.clear(Calendar.HOUR_OF_DAY);

		return DateUtil.getMonthsBetweenDate(cal1, cal2, flag);

	}

	/**
	 * 合约生成批次编号，时间戳加四位随机码
	 * 
	 * @return
	 */
	public static String timeSequence() {
		String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		str = "AG" + str
				+ new DecimalFormat("0000").format(Math.random() * 1000);
		return str;
	}

	/**
	 * @description 获取当前系统时间,YYYY-MM-dd格式
	 * @return 当前系统时间
	 */
	public static String getNowDateString() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);

		return dateString;
	}

	public static String getNowDateString_8() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);

		return dateString;
	}
	
	public static String getNowHHMMSS() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("hhmmss");
		String dateString = formatter.format(currentTime);

		return dateString;
	}

	public static String getDateString_8(Date time) {
		if (time != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String dateString = formatter.format(time);
			return dateString;
		} else {
			return "";
		}

	}

	/**
	 * @description 获取当前系统时间戳,yyyyMMddHHmmss格式
	 * @return 当前系统时间戳
	 */
	public static String getNowTimeStampString() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 判断字符串日期，按指定格式是否合法
	 * 
	 * 例子: isValidateDate("2013-01-01","yyyyMMdd")= false
	 * isValidateDate("20130230","yyyyMMdd")= false
	 * isValidateDate("20130228","yyyyMMdd")= true
	 * 
	 * @param date
	 *            字符串日期
	 * @param fmt
	 *            日期格式
	 * @return 合法返回true;否则返回false
	 */
	public static boolean isValidateDate(String date, String fmt) {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		// 严格日期格式检查
		sdf.setLenient(false);
		try {
			sdf.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	/*********************************************************** 
	 * @author Jarvis 
	 * <br>E-mail:<a  href="MarsDragon@live.cn">MarsDragon@live.cn</a>
	 * @description<br>Create time： 2013-6-26 下午12:36:19
	 * <br>函数功能描述： 获取当前时间的以前的或者当前日期今后的每一天的同一时间点。
	 * <br>@param day	负数则为获取当前时间的过去时间如：-1 获取当前日期的前一天的同一时间点日期，正数则是获取当前时期的第二天的同一时间点日期，0 则获取当前日期的同一时间点日期。
	 * <br>@param date	当前日期对象。
	 * <br>@return 返回要获取的日期。
	 *************************************************************/
	public static Date getDayByDay(Date date,int day){
		return new Date(date.getTime()+0x5265C00L*day); 
	}
	
	
	public static String get32bitNowDate(){
		StringBuffer sb = new StringBuffer();
		Calendar cd = Calendar.getInstance();
		sb.append(Integer.toString(cd.get(Calendar.MONTH),32));
		sb.append(Integer.toString(cd.get(Calendar.DAY_OF_MONTH), 32) );
		sb.append(Integer.toString(cd.get(Calendar.YEAR)-1970,32));
		return sb.toString();
	}
	
	public static String get32bitNowDayInMonth(){
		StringBuffer sb = new StringBuffer();
		Calendar cd = Calendar.getInstance();
		sb.append(Integer.toString(cd.get(Calendar.DAY_OF_MONTH), 32) );
		return sb.toString();
	}
	/**
	 * 获取当前年份
	 * @return
	 */
	public static Integer getCurrentYear(){
		Calendar cd = Calendar.getInstance();
		return cd.get(Calendar.YEAR);
		
	}
	/**
	 * 获取当前月份
	 * @return
	 */
	public static Integer getCurrentMonth(){
		Calendar cd = Calendar.getInstance();
		return cd.get(Calendar.MONTH)+1;
		
	}
	/**
	 * 获取指定日期所在月份
	 * @return
	 */
	public static Integer getMonth(java.util.Date date){
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		return cd.get(Calendar.MONTH)+1;		
	}

	/**
	 * 获取当前号(一个月中第几天)
	 * @return
	 */
	public static Integer getCurrentDay(){
		Calendar cd = Calendar.getInstance();
		return cd.get(Calendar.DAY_OF_MONTH);
		
	}
	public static String getNowDayInWeek(){
		Calendar cd = Calendar.getInstance();
		return Integer.toString(cd.get(Calendar.DAY_OF_WEEK));
	}
	/**
	 * @description 获得指定日期所在月份最后一天
	 * @return Date 当前月的最后一天 
	 */
	public static java.sql.Date getLastLastDayOfMonth(java.sql.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month );
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new java.sql.Date(calendar.getTimeInMillis());
	}	
	/**
	 * 将java.sql.Date转换为java.util.Date
	 * @param date
	 * @return
	 */
	public static Date toUtilDate(java.sql.Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		return calendar.getTime();
	}
	/**
	 * 将java.util.Date转换为java.sql.Date
	 * @param date
	 * @return
	 */
	public static java.sql.Date toSqlDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
		return sqlDate;
	}
	/**
	 * 将java.sql.Date转换为日历
	 * @param date 
	 * @return 日历
	 */
	public static Calendar toCalendar(java.sql.Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		return calendar;
	}
	/**
	 * 改变系统日期 ,仅仅支持window及linux系统
	 * @param t 
	 */
	public static void changeSystemDate(java.sql.Timestamp t){
		String osName = System.getProperty("os.name");  
		String cmd = "";  
		try {  
		    if (osName.matches("^(?i)Windows.*$")) {// Window 系统  
			    // 格式 HH:mm:ss  
			    //cmd = "  cmd /c time 12:00:00";  
			    //Runtime.getRuntime().exec(cmd);  
			    // 格式：yyyy-MM-dd  
			    cmd = String.format(" cmd /c date %tY-%tm-%td", t,t,t);  
			    Runtime.getRuntime().exec(cmd);  
			} else {// Linux 系统  
			   // 格式：yyyyMMdd  
				cmd = String.format(" date -s %tY%tm%td", t,t,t);
			    Runtime.getRuntime().exec(cmd);  
			   // 格式 HH:mm:ss  
			   //cmd = "  date -s 22:35:00";  
			   //Runtime.getRuntime().exec(cmd);  
			}  
		} catch (IOException e) {  
		    RmProjectHelper.logError("系统异常", e);  
		}  
	}
	/**
	 * 改变系统时间 ,仅仅支持window及linux系统
	 * @param t 
	 */
	public static void changeSystemTime(java.sql.Timestamp t){
		String osName = System.getProperty("os.name");  
		String cmd = "";  
		try {  
		    if (osName.matches("^(?i)Windows.*$")) {// Window 系统  
			    // 格式 HH:mm:ss  
				cmd = String.format("  cmd /c time %tH:%tM:%tS", t,t,t);
			    Runtime.getRuntime().exec(cmd);  
			} else {// Linux 系统  
			   // 格式 HH:mm:ss  
			   cmd = String.format(" date -s %tH:%tM:%tS", t,t,t);
			   Runtime.getRuntime().exec(cmd);  
			}  
		} catch (IOException e) {  
		    RmProjectHelper.logError("系统异常", e);  
		}  
	}
	/**
	 * 获取知道日期对象的小时数 
	 * @return
	 */
	public static Integer getCurrentHour(java.sql.Date date){
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		return cd.get(Calendar.HOUR_OF_DAY);
		
	}
	/**
	 * 获取年份
	 * @param t
	 * @return
	 */
	public static Integer getYear(Timestamp t) {
		Calendar cd = Calendar.getInstance();
		cd.setTimeInMillis(t.getTime());		
		return cd.get(Calendar.YEAR);
	}
	/**
	 * 获取小时
	 * @param t
	 * @return
	 */
	public static Integer getHour(Timestamp t) {
		Calendar cd = Calendar.getInstance();
		cd.setTimeInMillis(t.getTime());		
		return cd.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * 获取年份
	 * @param dbDate
	 * @return
	 */
	public static Integer getYear(java.sql.Date dbDate) {
		Calendar cd = Calendar.getInstance();
		cd.setTimeInMillis(dbDate.getTime());		
		return cd.get(Calendar.YEAR);
	}
	/**
	 * @description 获取两个日期之间相差的分钟数
	 * @param d1
	 *            开始日期
	 * @param d2
	 *            结束日期
	 * @return 返回的相差分钟数
	 * 
	 */
	public static long getMinuteBetweenDate(java.util.Date d1, java.util.Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();//现在的时间
        c2.setTime(d2);
        long i = Math.abs(c2.getTimeInMillis() - c1.getTimeInMillis());//得到的毫秒数
        return i / (1000 * 60);//分数
	}
	/**
	 * @description 获取两个日期之间相差的秒数
	 * @param d1
	 *            开始日期
	 * @param d2
	 *            结束日期
	 * @return 返回的相差分钟数
	 * 
	 */
	public static long getSecondBetweenDate(java.util.Date d1, java.util.Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();//现在的时间
        c2.setTime(d2);
        long i = Math.abs(c2.getTimeInMillis() - c1.getTimeInMillis());//得到的毫秒数
        return i / 1000 ;//秒数
	}
}
