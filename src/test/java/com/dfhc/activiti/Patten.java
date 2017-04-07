package com.dfhc.activiti;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class Patten {
	@Test
	public void passsss(){
		String a = "<select name=\"businessAttribute70\"sfds titdfdsle=\"businessAttribute70\" leipiplugins=\"select\" mapkey=\"id\" mapvalue=\"productName\" datasetid=\"4042201701120000001\" datrasettype=\"01\" desc=\"副产品名称\">";
		String patternString = "<select.*?name=\"(.*?)\".*?datasetid=\"(.*?)\".*?";    	 
        Pattern pattern = Pattern.compile(patternString);   
        Matcher matcher = pattern.matcher(a);
        while (true) {
        	if(matcher.find()){
        		int count = matcher.groupCount();
        		for (int i = 0; i <= count; i++) {
        			System.out.println(matcher.group(i));
				}
        		
        	}else{
        		break;
        	}
        } 
	}
}
