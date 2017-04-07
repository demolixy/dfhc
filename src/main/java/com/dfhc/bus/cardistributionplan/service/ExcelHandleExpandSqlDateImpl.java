package com.dfhc.bus.cardistributionplan.service;

import com.dfhc.pub.service.IExcelHandleExpand;
import com.dfhc.util.DateUtil;

public class ExcelHandleExpandSqlDateImpl implements IExcelHandleExpand{

	@Override
	public Object handle(Object obj) {
		String dateStr = (String) obj;
		//2017/1/1 1:00
		
		return DateUtil.toSqlDate(DateUtil.parser(dateStr, "yyyy/M/d H:m"));
	}

	

}
