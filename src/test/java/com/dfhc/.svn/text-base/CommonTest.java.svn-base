package com.dfhc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.dfhc.util.DateUtil;

public class CommonTest {
	
	@Test
	public void testAddDays(){
		
		Date myDate=DateUtil.addDays(new Date(), 1);
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.format(myDate));
	}
	
	@Test
   public  void testDays(){
	   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	   java.util.Date nowDate=DateUtil.getNowDate();
	   java.util.Date nextDate=DateUtil.addMonths(nowDate, 3);
	   int days= DateUtil.getDaysBetweenByStr(format.format(nowDate), format.format(nextDate), "yyyy-MM-dd");
	   System.out.println("days:"+days);
	   System.out.println("nowDate:"+format.format(nowDate));
	   System.out.println("nextDate:"+format.format(nextDate));
   }
   @Test
   public void testFormatHex(){
	   String rule="%s%03x";
	   System.out.println(String.format(rule, "A",100));
	   System.out.println(String.format(rule, "A",4095));
   }
   @Test
   public void testConstant(){
	   System.out.println(ISystemConstant.AJAX_BEAN);
	   CollectionUtils.isEmpty(null);
   }
}
