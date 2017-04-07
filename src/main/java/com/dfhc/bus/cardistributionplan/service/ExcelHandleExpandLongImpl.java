package com.dfhc.bus.cardistributionplan.service;

import java.math.BigDecimal;

import com.dfhc.pub.service.IExcelHandleExpand;
import com.dfhc.util.DateUtil;

public class ExcelHandleExpandLongImpl implements IExcelHandleExpand{

	@Override
	public Object handle(Object obj) {
		String dateStr = (String) obj;
		
		
		return new BigDecimal(dateStr).longValue();
	}

	

}
