package com.dfhc.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.springframework.transaction.annotation.Transactional;

import com.dfhc.pub.service.BsTransaction;
import com.dfhc.wk.generalbusinessprocess.service.GeneralBusinessProcessService;
import com.dfhc.wk.generalbusinessprocess.vo.GeneralBusinessProcessVo;

public class ScriptCode implements BsTransaction {

	@Override
	@Transactional
	public void doSave(String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> readTaskData(HttpServletRequest request) {
		Map result = new HashMap();
		GeneralBusinessProcessService generalBusinessProcessService = (GeneralBusinessProcessService) RmBeanFactory.getBean(GeneralBusinessProcessService.class.getName());
		List vos = generalBusinessProcessService.list(result, null);
		String idsss = "没有数据";
		if(vos.size()>0){
			idsss= ((GeneralBusinessProcessVo)vos.get(0)).getProcName();
		}
		result.put("idsss",idsss);
		return result;
	}

	@Override
	@Transactional
	public void doSaveTaskData(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

}
