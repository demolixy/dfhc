package com.dfhc.pub.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.orgauth.IOrgauthConstants;
import org.quickbundle.orgauth.rmparty.service.IRmPartyService;
import org.quickbundle.orgauth.rmparty.util.IRmPartyConstants;
import org.quickbundle.orgauth.rmparty.vo.RmPartyVo;
import org.quickbundle.orgauth.rmpartyrelation.service.IRmPartyRelationService;
import org.quickbundle.orgauth.rmpartyrelation.util.IRmPartyRelationConstants;
import org.quickbundle.orgauth.rmpartyrelation.vo.RmPartyRelationVo;
import org.quickbundle.orgauth.rmpartytype.service.impl.RmPartyTypeService;
import org.quickbundle.orgauth.rmpartytype.util.IRmPartyTypeConstants;
import org.quickbundle.orgauth.rmpartytype.vo.RmPartyTypeVo;
import org.quickbundle.orgauth.util.IRmOrgService;
import org.quickbundle.orgauth.util.impl.RmOrgService;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;
import org.quickbundle.tools.helper.RmPopulateHelper;
import org.quickbundle.tools.helper.RmStringHelper;
import org.quickbundle.util.RmSequenceSet;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dfhc.util.StringHelper;

/**
 * 
 * 授权菜单组织树管理服务
 * @author dfhc
 *
 */
@Service
//默认将类中的所有public函数纳入事务管理
@Transactional(readOnly = true)
public class PubPartyService {
	
	
	/**
	 * 根据cmd 命令跟新组织树节点
	 * @param cmd：请求来源
	 * @param party_type:团体类型
	 */
	public void  doUpdateOrgTree(String cmd,String party_type,HttpServletRequest request){
		
		if("relationViewRootNode".equals(cmd)) {
			RmPartyRelationVo prvo = new RmPartyRelationVo();
			RmPopulateHelper.populate(prvo, request);
			prvo.setParty_view_id(request.getParameter("view_id"));
			IRmPartyRelationService prService = (IRmPartyRelationService)RmBeanFactory.getBean(IRmPartyRelationConstants.SERVICE_KEY);
			prService.insert(prvo);
//			response.sendRedirect("manageParty.jsp?refresh_parent=1&view_id=" + request.getParameter("view_id") + "&child_party_code=" + request.getParameter("parent_party_code"));
		} else if("relationChildNode".equals(cmd)) {
			RmPartyRelationVo prvo = new RmPartyRelationVo();
			RmPopulateHelper.populate(prvo, request);
			prvo.setParty_view_id(request.getParameter("view_id"));
			IRmPartyRelationService prService = (IRmPartyRelationService)RmBeanFactory.getBean(IRmPartyRelationConstants.SERVICE_KEY);
			prService.insert(prvo);
//			response.sendRedirect("manageParty.jsp?refresh_parent=1&view_id=" + request.getParameter("view_id") + "&child_party_code=" + request.getParameter("parent_party_code"));
		} else if("addViewRootNode".equals(cmd)) {
			//团体
			RmPartyVo pvo = new RmPartyVo();
			RmPopulateHelper.populate(pvo, request);
			IRmPartyService pService = (IRmPartyService)RmBeanFactory.getBean(IRmPartyConstants.SERVICE_KEY);
			String id = pService.insert(pvo);

			//团体关系
			RmPartyRelationVo prvo = new RmPartyRelationVo();
			prvo.setParty_view_id(request.getParameter("view_id"));
			prvo.setChild_party_id(pvo.getId());
			prvo.setChild_party_name(pvo.getName());
			IRmPartyRelationService prService = (IRmPartyRelationService)RmBeanFactory.getBean(IRmPartyRelationConstants.SERVICE_KEY);
			prService.insert(prvo);
//			RmJspHelper.goUrlWithAlert(request, response, "创建根节点成功", "/jsp/util/refreshParent.jsp");
		} else if("insertChildNode".equals(cmd)) {
			//团体
			RmPartyVo pvo = new RmPartyVo();
			RmPopulateHelper.populate(pvo, request);
			IRmPartyService pService = (IRmPartyService)RmBeanFactory.getBean(IRmPartyConstants.SERVICE_KEY);
			String id = pService.insert(pvo);
			//团体关系
			RmPartyRelationVo prvo = new RmPartyRelationVo();
			prvo.setParty_view_id(request.getParameter("view_id"));
			prvo.setParent_party_id(request.getParameter("parent_party_id"));
			prvo.setChild_party_id(pvo.getId());
			prvo.setChild_party_name(pvo.getName());
			IRmPartyRelationService prService = (IRmPartyRelationService)RmBeanFactory.getBean(IRmPartyRelationConstants.SERVICE_KEY);
			prService.insert(prvo);
//			RmJspHelper.goUrlWithAlert(request, response, "新增子节点成功", "/jsp/util/refreshParent.jsp");
		} else if("deleteChildNode".equals(cmd)) {
			RmProjectHelper.getCommonServiceInstance().doUpdate(
				"delete from RM_PARTY_RELATION where CHILD_PARTY_CODE='" + request.getParameter("parent_party_code") + "' and party_view_id=" + request.getParameter("view_id"));
			IRmPartyService pService = (IRmPartyService)RmBeanFactory.getBean(IRmPartyConstants.SERVICE_KEY);
			pService.delete(request.getParameter("parent_party_id"));		
//			RmJspHelper.goUrlWithAlert(request, response, "删除本节点成功", "/jsp/util/refreshParent.jsp");
		} else if("deleteChildRelation".equals(cmd)) {
			RmProjectHelper.getCommonServiceInstance().doUpdateBatch(new String[]{
					"delete from RM_PARTY_RELATION where CHILD_PARTY_CODE='" + request.getParameter("parent_party_code") + "' and party_view_id=" + request.getParameter("view_id")
				});
//				RmJspHelper.goUrlWithAlert(request, response, "删除本关系成功", "/jsp/util/refreshParent.jsp");
		} else if("updateOrderCode".equals(request.getParameter("cmdInput"))) {
			RmProjectHelper.getCommonServiceInstance().doUpdate("update RM_PARTY_RELATION SET ORDER_CODE=? WHERE CHILD_PARTY_CODE=?", 
					new String[]{request.getParameter("order_code"), request.getParameter("child_party_code")});
		} else if("updateThisPartyName".equals(request.getParameter("cmdInput"))) {
			String thisPartyName = request.getParameter("this_party_name");
			String thisPartyId = request.getParameter("parent_party_id");
			RmProjectHelper.getCommonServiceInstance().doUpdateBatch(new String[]{
					"update RM_PARTY_RELATION SET CHILD_PARTY_NAME='" + thisPartyName + "' WHERE CHILD_PARTY_ID=" + thisPartyId, 
					"update RM_PARTY_RELATION SET PARENT_PARTY_NAME='" + thisPartyName + "' WHERE PARENT_PARTY_ID=" + thisPartyId
			});
			IRmPartyService pService = (IRmPartyService)RmBeanFactory.getBean(IRmPartyConstants.SERVICE_KEY);
			//团体
			RmPartyVo pvo = new RmPartyVo();
			RmPopulateHelper.populate(pvo, request);
			pvo.setId(thisPartyId);
			pvo.setName(thisPartyName);
			pService.update(pvo);
		}
		
	}
	
	
	/**
	 * 根据团体视图id和对应的子团体编码查找对应的团体关系
	 * @param view_id
	 * @param child_party_code
	 * @return
	 */
	public RmCommonVo queryChildRelationVo(String view_id,String  child_party_code){
    	//当前的party
    	RmCommonVo cvo = null;
    	if(StringHelper.isEmpty(child_party_code)) {
    		child_party_code = "";
    	} else {
    		List<RmCommonVo> lcvo = RmProjectHelper.getCommonServiceInstance().doQuery("select * from RM_PARTY_RELATION where child_party_code='" + child_party_code + "'");
    		if(lcvo.size() > 0) {
    			cvo = lcvo.get(0);
    		}
    	}
    	
    	return  cvo;
    	
    	
	}
	
	
	/**
	 * 根据团体视图id，和该节点团体id查找到对应的团体类型
	 * @param view_id
	 * @param child_party_id
	 * @return
	 */
	public List<RmPartyTypeVo> queryChildPartyType(String view_id,String child_party_id){
		
		String ptCondition = null; 
    	if(!StringHelper.isEmpty(child_party_id)){
    		ptCondition = "BS_KEYWORD in(select c.BS_KEYWORD from RM_PARTY a " +
    				"join RM_PARTY_TYPE_RELATION_RULE b on a.PARTY_TYPE_ID=b.PARENT_PARTY_TYPE_ID " +  
    				"join RM_PARTY_TYPE c on b.CHILD_PARTY_TYPE_ID=c.id " + 
    				"where b.PARTY_VIEW_ID=" + view_id + " and a.ID=" + child_party_id + ")";
    	} else {
    		ptCondition = "BS_KEYWORD in (select a.BS_KEYWORD from RM_PARTY_TYPE a join RM_PARTY_TYPE_RELATION_RULE b on a.ID=b.CHILD_PARTY_TYPE_ID " +
    				" where b.PARENT_PARTY_TYPE_ID='' or b.PARENT_PARTY_TYPE_ID is null )";
    	}
    	
    	//当前节点规则
    	RmPartyTypeService partyTypeService = (RmPartyTypeService)RmBeanFactory.getBean(IRmPartyTypeConstants.SERVICE_KEY);
    	List<RmPartyTypeVo> lAvailablePartyType = partyTypeService.queryByCondition(ptCondition, null);
    	
    	
    	return lAvailablePartyType;
	}
	
	
	
	/**
	 * 根据当前登录的userid 获取用户角色，查询对应节点，根据节点直接查询对应的待办事项
	 * @param userId
	 * @return
	 */
	public List<RmCommonVo> getAllNodeByRole(String userId){
		
		List<RmCommonVo> list=new ArrayList<RmCommonVo>();
		//定义当前用户所有的party_id
		Set<String> sParty_id = new RmSequenceSet<String>();
		
		//放入用户ID
		sParty_id.add(userId);
		//orgauth initUserInfo begin
		IRmOrgService orgService = RmOrgService.getInstance();
		//所有祖先团体(包含父团体)
		List<RmCommonVo> lAncestor = orgService.listAncestor(userId,IOrgauthConstants.PartyView.DEFAULT.id());
		Set<String> sAncestor_party_id = orgService.listAncestor_party_id(lAncestor);
		Set<String> sOwner_party_id = orgService.listOwner_party_id(lAncestor, IOrgauthConstants.OrgTree.COMPANY.value());
		
		//放入所有祖先团体
		sParty_id.addAll(sAncestor_party_id);
		
		//取关联角色的团体ID
		String sqlRole = "select ROLE_ID from RM_PARTY_ROLE where OWNER_PARTY_ID in(" + RmStringHelper.parseToSQLString(sParty_id.toArray(new String[0])) + ") and (OWNER_ORG_ID in(" + RmStringHelper.parseToSQLStringApos(sAncestor_party_id.toArray(new String[0])) + ") or OWNER_ORG_ID is null or OWNER_ORG_ID='')";
		List<String> lRole_id = RmProjectHelper.getCommonServiceInstance().query(sqlRole, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("ROLE_ID");
			}
		});
		sParty_id.addAll(lRole_id);
		
		//取菜单权限
		String sqlParty=RmStringHelper.parseToSQLString(sParty_id.toArray(new String[0]));
		
		StringBuilder sqlMenuList=new StringBuilder();
		sqlMenuList.append(" select '0' as countNum, BASE_TODO_MENU_MAP.MENU_NAME name,BASE_TODO_MENU_MAP.MENU_SQL dealCountSql,BASE_TODO_MENU_MAP.MENU_URL url ");
		sqlMenuList.append(" from BASE_TODO_MENU_MAP ");
		sqlMenuList.append(" inner join ( ");
		sqlMenuList.append("select distinct RM_FUNCTION_NODE.NAME,RM_FUNCTION_NODE.DATA_VALUE from RM_FUNCTION_NODE ");
		sqlMenuList.append("join RM_AUTHORIZE_RESOURCE on RM_FUNCTION_NODE.TOTAL_CODE=RM_AUTHORIZE_RESOURCE.OLD_RESOURCE_ID ");
		sqlMenuList.append("and RM_AUTHORIZE_RESOURCE.AUTHORIZE_ID=");
		sqlMenuList.append(IOrgauthConstants.Authorize.FUNCTION_NODE.id() );
		sqlMenuList.append(" and RM_FUNCTION_NODE.is_leaf='1' left join RM_AUTHORIZE_RESOURCE_RECORD on RM_AUTHORIZE_RESOURCE_RECORD.AUTHORIZE_RESOURCE_ID=RM_AUTHORIZE_RESOURCE.ID ");
		sqlMenuList.append(" where RM_AUTHORIZE_RESOURCE.DEFAULT_ACCESS='1' or RM_AUTHORIZE_RESOURCE_RECORD.PARTY_ID in(");
		sqlMenuList.append(sqlParty);
		sqlMenuList.append(")) node on node.DATA_VALUE=BASE_TODO_MENU_MAP.MENU_URL");
		
		list=RmProjectHelper.getCommonServiceInstance().doQuery(sqlMenuList.toString());
		
		 if(list!=null && list.size()>0){
			 
			 for(RmCommonVo vo:list){
				 
				 int countNum=0;
				 String sql=vo.getString("dealCountSql");
				 if(!StringHelper.isEmpty(sql)){
					 
					 countNum=RmProjectHelper.getCommonServiceInstance().doQueryForInt(sql);
					 
					 vo.put("countNum", countNum);
				 }
			 }
		 }
		
		 return list;
	}
	

}
