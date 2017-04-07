package com.dfhc.util;

import java.math.BigDecimal;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
 
/**
 * 拼音工具类
 * 
 * @author lsf
 */
public class PinyinUtil {
	
	
	 private static final String UNIT = "万千佰拾亿千佰拾万千佰拾元角分";  
     private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";  
     private static final double MAX_VALUE = 9999999999999.99D;  
     
     
     /**
      * 小写金额转大写金额
      * 
      * @param inputString
      * @return
      */
     public static String getChangeAmount(BigDecimal  num) {  
    	 double v=Double.valueOf(num.toString());
	      if (v < 0 || v > MAX_VALUE){  
	          return "参数非法!";  
	      }  
	      
	      long l = Math.round(v * 100);  
	      
	      if (l == 0){  
	          return "零元整";  
	      }  
	      
	      String strValue = l + "";  
	      // i用来控制数  
	      int i = 0;  
	      // j用来控制单位  
	      int j = UNIT.length() - strValue.length();  
	      String rs = "";  
	      boolean isZero = false;  
	      for (; i < strValue.length(); i++, j++) {  
		       char ch = strValue.charAt(i);  
		       if (ch == '0') {  
			        isZero = true;  
			        if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万' || UNIT.charAt(j) == '元') {  
				         rs = rs + UNIT.charAt(j);  
				         isZero = false;  
			        }  
		       } else {  
			        if (isZero) {  
			         rs = rs + "零";  
			         isZero = false;  
			        }  
			        rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);  
		       }  
	      }  
	      if (!rs.endsWith("分")) {  
	       rs = rs + "整";  
	      }  
	      rs = rs.replaceAll("亿万", "亿");  
	      return rs;  
     }  
       
    /**
     * 将字符串中的中文转化为拼音,其他字符不变
     * 
     * @param inputString
     * @return
     */
    public static String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
 
        char[] input = inputString.trim().toCharArray();
        String output = "";
 
        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output += temp[0];
                } else
                    output += java.lang.Character.toString(input[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output;
    }
    /**  
     * 获取汉字串拼音首字母，英文字符不变  
     * @param chinese 汉字串  
     * @return 汉语拼音首字母  
     */  
    public static String getFirstSpell(String chinese) {   
            StringBuffer pybf = new StringBuffer();   
            char[] arr = chinese.toCharArray();   
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
            for (int i = 0; i < arr.length; i++) {   
                    if (arr[i] > 128) {   
                            try {   
                                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);   
                                    if (temp != null) {   
                                            pybf.append(temp[0].charAt(0));   
                                    }   
                            } catch (BadHanyuPinyinOutputFormatCombination e) {   
                                    e.printStackTrace();   
                            }   
                    } else {   
                            pybf.append(arr[i]);   
                    }   
            }   
            return pybf.toString().replaceAll("\\W", "").trim();   
    }   
    /**  
     * 获取汉字串拼音，英文字符不变  
     * @param chinese 汉字串  
     * @return 汉语拼音  
     */  
    public static String getFullSpell(String chinese) {   
            StringBuffer pybf = new StringBuffer();   
            char[] arr = chinese.toCharArray();   
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
            for (int i = 0; i < arr.length; i++) {   
                    if (arr[i] > 128) {   
                            try {   
                                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);   
                            } catch (BadHanyuPinyinOutputFormatCombination e) {   
                                    e.printStackTrace();   
                            }   
                    } else {   
                            pybf.append(arr[i]);   
                    }   
            }   
            return pybf.toString();   
    }  
} 

