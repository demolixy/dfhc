package org.quickbundle.project.filter;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.quickbundle.orgauth.IOrgauthConstants;
import org.quickbundle.orgauth.util.IRmOrgService;
import org.quickbundle.orgauth.util.impl.RmOrgService;
import org.quickbundle.project.IGlobalConstants;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmStringHelper;
import org.quickbundle.util.RmSequenceSet;
import org.springframework.jdbc.core.RowMapper;

import com.dfhc.util.StringHelper;

/**
 * 根据session查看当前用户，根据用户id 查看对应的角色 。根据角色查看对应的授权url
 * 
 * 
 * @author dfhc
 *
 */
public class NeedDealFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * 业务处理部分
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//处理权限 begin
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		boolean isLogin = false;
		HttpSession session = RmJspHelper.getSession(request, false);
		
        if(session != null && session.getAttribute(IGlobalConstants.RM_USER_VO) != null) {
            isLogin = true; 
        }
        
		if(isLogin){//已登录查看对应用户 id  获取到对应角色id
			
			String party_id=RmProjectHelper.getRmUserVo(request).getParty_id_org();//团体id
			String userId=RmProjectHelper.getRmUserId(request);
			
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
			sqlMenuList.append(" select BASE_TODO_MENU_MAP.MENU_NAME name,BASE_TODO_MENU_MAP.MENU_SQL dealCountSql,BASE_TODO_MENU_MAP.MENU_URL url ");
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
			
			List<RmCommonVo> list=RmProjectHelper.getCommonServiceInstance().doQuery(sqlMenuList.toString());
			
			 if(list!=null && list.size()>0){
				 
				 for(RmCommonVo vo:list){
					 
					 int countNum=0;
					 String sql=vo.getString("dealCountSql");
					 if(!StringHelper.isEmpty(sql)){
						 
						 List<RmCommonVo> listCount=RmProjectHelper.getCommonServiceInstance().doQuery(sql);
						 
						 if(listCount!=null && listCount.size()>0){
							 
							 BigDecimal countNumBig=(BigDecimal)listCount.get(0).get("countNum");
							 countNumBig=countNumBig==null?BigDecimal.ZERO:countNumBig;
							 countNum=countNumBig.intValue();
							 vo.put("countNum", countNum);
							 
						 }
					 }
				 }
				 
				 session.setAttribute("listDeal", list);
			 }
			
			filterChain.doFilter(request, response);
			
		}else{
			
			filterChain.doFilter(request, response);
		}
		

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
