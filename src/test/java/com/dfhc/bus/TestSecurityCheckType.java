package com.dfhc.bus;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.dfhc.base.securityindex.dao.SecurityIndexDao;
import com.dfhc.base.securityindex.service.SecurityIndexService;
import com.dfhc.base.securityindex.vo.SecurityIndexVo;

public class TestSecurityCheckType {

	@Test
	public void testListMapOfStringObjectString() {
		SecurityIndexService securityIndexService = new SecurityIndexService();
		SecurityIndexDao securityIndexDao =new SecurityIndexDao();
		Map<String,Object> searchPara =new HashMap<String,Object>();
		searchPara .put("checkType", "01");
    	 //充装前检查，根据id获取安检指标
		List<SecurityIndexVo> list =securityIndexDao.list(searchPara, "",1,10,false);
		
		System.out.println(list.get(0).getCheckType());
		fail("Not yet implemented");
	}

}
