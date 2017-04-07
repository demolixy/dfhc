package com.dfhc.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
	private Map<String,Object> map = new HashMap<String,Object>();
	
	public MapUtil(Object...strings){
		if(strings!=null && strings.length>1){
			for(int i = 0;i<strings.length;i=i+2){
				if(i+1==strings.length)
					break;
				map.put(strings[i].toString(), strings[i+1]);
			}
		}
	}
	public MapUtil(){}
	
	public Map<String,Object> get(){
		return map;
	}
}
