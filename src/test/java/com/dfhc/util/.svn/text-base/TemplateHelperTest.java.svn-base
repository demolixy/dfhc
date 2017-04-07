package com.dfhc.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.TemplateException;

public class TemplateHelperTest {

	
	@Test
	public void testCreateContentByString() {
		//fail("Not yet implemented");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "小明");
		String templateString = "<input type='text' value='${name}'>";
		try {
			templateString = TemplateHelper.createContentByString(paramMap, templateString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(templateString);
	}

}
