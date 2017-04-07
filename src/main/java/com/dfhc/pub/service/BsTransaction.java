/**
 * 
 */
package com.dfhc.pub.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author longsebo
 * 测试bean shell事务接口
 */
@Service
@Transactional
public interface BsTransaction {
	@Transactional
	public void doSave(String userId);
	
	public Map<String, Object> readTaskData(HttpServletRequest request);
	
	@Transactional
	public void doSaveTaskData(HttpServletRequest request, HttpServletResponse response);
}
