package com.dfhc.pub.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.config.RmConfig;
import org.quickbundle.orgauth.IOrgauthConstants;
import org.quickbundle.orgauth.cache.RmFunctionNodeCache;
import org.quickbundle.orgauth.rmfunctionnode.vo.RmFunctionNodeVo;
import org.quickbundle.orgauth.rmparty.service.IRmPartyService;
import org.quickbundle.orgauth.rmparty.util.IRmPartyConstants;
import org.quickbundle.orgauth.rmparty.vo.RmPartyVo;
import org.quickbundle.orgauth.rmpartytype.vo.RmPartyTypeVo;
import org.quickbundle.orgauth.rmuser.service.IRmUserService;
import org.quickbundle.orgauth.rmuser.util.IRmUserConstants;
import org.quickbundle.project.IGlobalConstants;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.common.vo.RmCommonVo;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmSqlHelper;
import org.quickbundle.tools.helper.RmStringHelper;
import org.quickbundle.tools.support.tree.DeepTreeVo;
import org.quickbundle.tools.support.tree.DeepTreeXmlHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.base.price.service.PriceService;
import com.dfhc.base.price.vo.PriceVo;
import com.dfhc.base.price.vo.TodayCarTabVo;
import com.dfhc.bus.cardistributionplan.service.CarDistributionPlanService;
import com.dfhc.bus.carrequisitionplan.service.CarRequisitionPlanService;
import com.dfhc.bus.order.service.OrderService;
import com.dfhc.bus.recipients.service.RecipientsService;
import com.dfhc.bus.recipients.vo.RecipientsVo;
import com.dfhc.bus.trainsrequisitionplan.service.TrainsRequisitionPlanService;
import com.dfhc.pub.service.FileDownloadService;
import com.dfhc.pub.service.ImageShowService;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.pub.service.PubPartyService;
import com.dfhc.rm.user.service.UserService;
import com.dfhc.rm.user.vo.UserVo;
import com.dfhc.util.ImageUtil;
import com.dfhc.util.StringHelper;

//---
@Controller
@RequestMapping(value = "/pubController")
public class PubController implements IGlobalConstants {
	@Autowired
	ImageShowService imageShowService;
	@Autowired
	FileDownloadService fileDownloadService;
	@Autowired
	PubParamService pubParamService;
	@Autowired
	PriceService priceService;
	@Autowired
	RecipientsService recipientsService;
	@Autowired
	UserService userService;
	/**
	 * 组织树公告服务
	 */
	@Autowired
	PubPartyService pubPartyService;
	@Autowired
	OrderService  orderService;
	@Autowired
	TrainsRequisitionPlanService  trainsRequisitionPlanService;
	@Autowired
	CarDistributionPlanService  carDistributionPlanService;
	@Autowired
	CarRequisitionPlanService  carRequisitionPlanService;

	/**
	 * 显示图片对象
	 * 
	 * @return
	 */
	public ImageShowService getImageShowService() {
		return (ImageShowService) RmBeanFactory.getBean(ImageShowService.class
				.getName()); // 得到Service对象,受事务控制
	}
	/**
	 * 获取party
	 * 
	 * @return Service对象
	 */
	public IRmPartyService getPartyService() {
		return (IRmPartyService) RmBeanFactory
				.getBean(IRmPartyConstants.SERVICE_KEY); // 得到Service对象,受事务控制
	}

	@RequestMapping(value = "getParty")
	public String list(Model model, HttpServletRequest request) {
		String view_id = request.getParameter("view_id");
		if (view_id == null || view_id.trim().length() == 0) {
			// view_id的默认值是RM_PARTY_VIEW表的第一条记录
			List<RmCommonVo> lPartyViewId = RmProjectHelper
					.getCommonServiceInstance().doQuery(
							"SELECT ID FROM RM_PARTY_VIEW ORDER BY ID", 1, 1);
			if (lPartyViewId.size() > 0) {
				view_id = lPartyViewId.get(0).getString("id");
			} else {
				view_id = IOrgauthConstants.PartyView.DEFAULT.id();
			}
		}

		// 优先获取parent_party_code
		String parent_party_code = request.getParameter("parent_party_code");

		// 如果parent_party_code为null，获取parent_party_id且默认为""
		String parent_party_id = null;
		if (parent_party_code == null) {
			parent_party_id = request.getParameter("parent_party_id");
			if (parent_party_id == null) {
				parent_party_id = "";
			}
		}

		// 是否包含自身，默认为0。只有懒加载时有效
		boolean include_self = false;
		include_self = "1".equals(request.getParameter("include_self"));

		// 获取show_bk
		String[] aShow_bk = new String[0];
		String show_bk = request.getParameter("show_bk");
		if (show_bk != null && show_bk.trim().length() > 0) {
			String tempBk = show_bk.replaceAll("^,+", "").replaceAll(",+$", "");
			if (tempBk.trim().length() > 0) {
				aShow_bk = tempBk.trim().split(",");
			}
		}

		// 获取lazy_init，默认为1
		String lazy_init = request.getParameter("lazy_init");
		if (lazy_init == null || lazy_init.length() == 0
				|| (!"1".equals(lazy_init) && !"0".equals(lazy_init))) {
			lazy_init = "0";
		}
		// 获取允许挂接用户 add by longsebo 2016-11-14
		String is_allow_hanging_user = "";
		if (!StringHelper
				.isEmpty(request.getParameter("is_allow_hanging_user"))) {
			is_allow_hanging_user = request
					.getParameter("is_allow_hanging_user");
		}
		// 获取允许挂接角色
		String is_allow_hanging_role = "";
		if (!StringHelper
				.isEmpty(request.getParameter("is_allow_hanging_role"))) {
			is_allow_hanging_role = request
					.getParameter("is_allow_hanging_role");
		}
		// 查询树节点的sql语句
		StringBuilder sbSql = new StringBuilder();
		sbSql.append("select PR.PARENT_PARTY_ID, PR.CHILD_PARTY_ID, PR.PARENT_PARTY_CODE, PR.CHILD_PARTY_CODE, "
				+ "PR.CHILD_IS_LEAF, PR.CHILD_PARTY_NAME, P.PARTY_TYPE_ID as CHILD_PARTY_TYPE_ID, PT.ICON as CHILD_ICON, "
				+ "PT.BS_KEYWORD as CHILD_BS_KEYWORD, PR.CHILD_IS_MAIN_RELATION, PR.ORDER_CODE from RM_PARTY_RELATION PR "
				+ "join RM_PARTY P on PR.CHILD_PARTY_ID=P.ID "
				+ "join RM_PARTY_TYPE PT on P.PARTY_TYPE_ID=PT.ID where ");

		// 懒加载，只获取1层子节点
		if ("1".equals(lazy_init)) {
			// 如果parent_party_code未指定，按parent_party_id查询
			if (parent_party_code == null) {
				if ("".equals(parent_party_id)) {
					sbSql.append(" (PR.PARENT_PARTY_ID is null or PR.PARENT_PARTY_ID='')");
				} else {
					if (include_self) { // 查自身
						sbSql.append(" PR.CHILD_PARTY_ID=" + parent_party_id);
					} else { // 查父节点
						sbSql.append(" PR.PARENT_PARTY_ID=" + parent_party_id);
					}
				}
			} else { // parent_party_code不为空则按parent_party_code查询
				if ("".equals(parent_party_code)) {
					sbSql.append(" (PR.PARENT_PARTY_CODE is null or PR.PARENT_PARTY_CODE='')");
				} else {
					if (include_self) { // 查自身
						sbSql.append(" PR.CHILD_PARTY_CODE='"
								+ parent_party_code + "'");
					} else { // 查父节点
						sbSql.append(" PR.PARENT_PARTY_CODE='"
								+ parent_party_code + "'");
					}
				}
			}
			// 如果show_bk不为空，过滤
			if (aShow_bk.length > 0) {
				sbSql.append(" and PT.BS_KEYWORD in ("
						+ RmStringHelper.parseToSQLStringApos(aShow_bk) + ")");
			}
		} else {
			// 如果parent_party_code未指定，按parent_party_id查询
			if (parent_party_code == null) {
				StringBuilder sql_parentParty = new StringBuilder();
				sql_parentParty
						.append("select PR.CHILD_PARTY_CODE, PARENT_PARTY_CODE, CHILD_IS_MAIN_RELATION from RM_PARTY_RELATION PR where PR.PARTY_VIEW_ID="
								+ view_id);
				if ("".equals(parent_party_id)) {
					sql_parentParty
							.append(" and (PR.PARENT_PARTY_ID is null or PR.PARENT_PARTY_ID='')");
				} else {
					sql_parentParty.append(" and PR.PARENT_PARTY_ID="
							+ parent_party_id);
				}
				List<String> lParentPartyCode = RmProjectHelper
						.getCommonServiceInstance().query(
								sql_parentParty.toString(), new RowMapper() {
									public Object mapRow(ResultSet rs, int i)
											throws SQLException {
										return rs.getString("CHILD_PARTY_CODE");
									}
								});
				int index = 0;
				if (lParentPartyCode.size() > 0) {
					for (String parentPartyCode : lParentPartyCode) {
						if (index == 0) {
							sbSql.append(" (PR.CHILD_PARTY_CODE like '"
									+ parentPartyCode + "%'");
						} else {
							sbSql.append(" or PR.CHILD_PARTY_CODE like '"
									+ parentPartyCode + "%'");
						}
						index++;
					}
					sbSql.append(")");
				} else {
					sbSql.append(" PR.CHILD_PARTY_CODE like '" + NOT_EXIST_ID
							+ "%'");
				}
			} else {
				sbSql.append(" PR.CHILD_PARTY_CODE like '" + parent_party_code
						+ "%'");
			}
			// 如果show_bk不为空，过滤
			if (aShow_bk.length > 0) {
				sbSql.append(" and PT.BS_KEYWORD in ("
						+ RmStringHelper.parseToSQLStringApos(aShow_bk) + ")");
			}
		}
		// 增加是否允许挂接用户，角色条件 add by longsebo 2016-11-14
		if (!StringHelper.isEmpty(is_allow_hanging_user)) {
			sbSql.append("and PT.IS_ALLOW_HANGING_USER='")
					.append(is_allow_hanging_user).append("'");
		}
		if (!StringHelper.isEmpty(is_allow_hanging_role)) {
			sbSql.append("and PT.IS_ALLOW_HANGING_ROLE='")
					.append(is_allow_hanging_role).append("'");
		}
		sbSql.append(" and PR.PARTY_VIEW_ID=" + view_id);
		List<RmCommonVo> lcvo = RmProjectHelper.getCommonServiceInstance()
				.doQuery(sbSql.toString());
		model.addAttribute("beans", lcvo);
		model.addAttribute("last", lcvo.size());
		model.addAttribute("objKey", request.getParameter("objKey"));// form对应要回写的input的那么属性，第一个为要会写的id，
		model.addAttribute("fromName", request.getParameter("formName"));// from名称name属性
		model.addAttribute("view_id", view_id);// from名称name属性
		model.addAttribute("source", request.getParameter("source"));// 标志请求从哪儿来的，source=orgBack：说明是后台组织结构,
																		// source=assignDepts说明来自activiti人员分配
																		// source=orgFront说明来自前台选择组织机构
		if ("orgFront".equals(request.getParameter("source"))) {
			return "/ztree/orgFront";
		} else if ("orgBack".equals(request.getParameter("source"))) {
			return "/ztree/radioBack";
		} else if ("assignDepts".equals(request.getParameter("source"))) {
			return "/ztree/assignDepts";
		} else if ("partymanage".equals(request.getParameter("source"))) {// 受控菜单组织树管理
			return "/ztree/orgPartyTreeManage";
		} else {
			return "/ztree/radio";
		}

	}

	/**
	 * 授权菜单的组织机构树管理
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "managePartyTab")
	public String managePartyTab(HttpServletRequest request, Model model) {

		String cmd = request.getParameter("cmd");// 来自哪儿
		String view_id = request.getParameter("view_id");// 视图id
		List<RmCommonVo> lcvo = RmProjectHelper.getCommonServiceInstance()
				.doQuery("select ID, NAME from RM_PARTY_VIEW");
		Map<String, String> mpv = new HashMap<String, String>();
		for (RmCommonVo cvo : lcvo) {
			mpv.put(cvo.getString("id"), cvo.getString("name"));
			if (view_id == null) {
				view_id = cvo.getString("id");
			}
		}

		String sValue = "";
		if (IOrgauthConstants.PartyView.DEFAULT.id().equals(view_id)) {
			sValue = IOrgauthConstants.OrgTree.DEFAULT.value();
		}

		if (StringHelper.isEmpty(cmd)) {

			cmd = sValue;
		}

		model.addAttribute("cmd", cmd);
		model.addAttribute("view_id", view_id);

		this.list(model, request);// 查组织树节点数据

		model.addAttribute("child_party_code",
				request.getParameter("child_party_code"));

		return "/yulinchemical/system/managePartyTab";

	}

	@RequestMapping(value = "ajaxGetChildParty", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxGetChildParty(HttpServletRequest request,
			Model model) {

		Map<String, Object> result = new HashMap<String, Object>();

		String cmd = request.getParameter("cmd");
		String party_type = request.getParameter("party_type");

		pubPartyService.doUpdateOrgTree(cmd, party_type, request);// 操作组织树
		String view_id = request.getParameter("view_id");

		String child_party_code = request.getParameter("child_party_code");// 获取子团体编码
		RmCommonVo childPartyRelationVo = pubPartyService.queryChildRelationVo(
				view_id, child_party_code);// 根据自团体编码查询自团体的团体关心
		String child_party_id = "";// 子团体id
		if (childPartyRelationVo != null) {

			child_party_id = childPartyRelationVo.getString("child_party_id");// 获取子团体id
		}
		List<RmPartyTypeVo> listRmPartyTypeVos = pubPartyService
				.queryChildPartyType(view_id, child_party_id);
		//
		// model.addAttribute("childPartyRelationVo",
		// childPartyRelationVo);//团体关系
		// model.addAttribute("listRmPartyTypeVos", listRmPartyTypeVos);//团体类型
		result.put("listRmPartyTypeVos", listRmPartyTypeVos);
		result.put("childPartyRelationVo", childPartyRelationVo);

		return result;

	}

	/**
	 * 获取部分关联参照页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getPartyReference")
	public String getPartyReference(HttpServletRequest request, Model model) {

		request.setAttribute(REQUEST_REFERENCE_INPUT_TYPE,
				request.getParameter(REQUEST_REFERENCE_INPUT_TYPE)); // 传送输入方式,checkbox或radio

		return "/yulinchemical/system/listPartyReference";
	}

	/**
	 * ajax 加载组织页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "ajaxListPartyReference")
	public void ajaxListPartyReference(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		IRmPartyService service = getPartyService();

		String queryCondition = getQueryCondition(request); // 从request中获得查询条件
		String page = request.getParameter("page") == null ? "1" : request
				.getParameter("page");
		String rows = request.getParameter("rows") == null ? ISystemConstant.DEFAULT_PAGE_SIZE
				: request.getParameter("rows");
		int rowsInt = Integer.parseInt(rows);// 页大小
		int pageInt = Integer.parseInt(page);// 当前页
		RmPageVo pageVo = RmJspHelper.transctPageVo(request,
				service.getRecordCount(queryCondition));
		pageVo.setPageSize(rowsInt);
		pageVo.setCurrentPage(pageInt);
		String orderStr = RmJspHelper.getOrderStr(request); // 得到排序信息
		List<RmPartyVo> beans = service.queryByCondition(queryCondition,
				orderStr, pageVo.getStartIndex(), pageVo.getPageSize()); // 按条件查询全部,带排序

		dataMap.put("page", pageVo.getCurrentPage());
		dataMap.put("total", pageVo.getPageCount());
		dataMap.put("records", pageVo.getRecordCount());
		dataMap.put("rows", beans);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(JSONObject.toJSONString(dataMap));

	}

	/**
	 * 团体参照查询
	 * 
	 * @param request
	 * @return
	 */
	private String getQueryCondition(HttpServletRequest request) {

		String queryCondition = "";

		List<String> lQuery = new ArrayList<String>();
		lQuery.add(RmSqlHelper.buildQueryStr(IRmPartyConstants.TABLE_NAME
				+ ".name", request.getParameter("name"),
				RmSqlHelper.TYPE_CHAR_LIKE));

		lQuery.add(RmSqlHelper.buildQueryStr(IRmPartyConstants.TABLE_NAME
				+ ".is_inherit", request.getParameter("is_inherit"),
				RmSqlHelper.TYPE_CUSTOM, "='", "'"));

		lQuery.add(RmSqlHelper.buildQueryStr(IRmPartyConstants.TABLE_NAME
				+ ".email", request.getParameter("email"),
				RmSqlHelper.TYPE_CHAR_LIKE));
		queryCondition = RmSqlHelper.appendQueryStr(lQuery
				.toArray(new String[0]));

		return queryCondition;

	}

	/**
	 * 功能菜单按钮
	 * 
	 * @param request
	 * @param orgTree
	 * @param authorityMenuTree
	 * @return
	 */
	@RequestMapping(value = "getMenuTree", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map getMenuTree(Model model, HttpServletRequest request) {
		boolean authorityMenuTree = false;
		if (request.getParameter("authorityMenuTree") == null
				&& "" == request.getParameter("authorityMenuTree")) {
			authorityMenuTree = false;
		} else {
			authorityMenuTree = Boolean.valueOf(request
					.getParameter("authorityMenuTree"));
		}

		Map<String, Object> map = new HashMap<String, Object>();
		DeepTreeXmlHandler dt = new DeepTreeXmlHandler();
		String totalCode = request.getParameter("total_code");
		String id = request.getParameter("id");
		if (totalCode == null) {
			totalCode = "";
		}
		if (!authorityMenuTree) {
			// 获取lazy_init，默认为1
			String lazy_init = request.getParameter("lazy_init");
			if (lazy_init == null || lazy_init.length() == 0
					|| (!"1".equals(lazy_init) && !"0".equals(lazy_init))) {
				lazy_init = "1";
			}
			StringBuilder sql = new StringBuilder();
			sql.append("select ID,NODE_TYPE,ENABLE_STATUS,NAME,DATA_VALUE,TOTAL_CODE,IS_LEAF,ICON from RM_FUNCTION_NODE ");
			// 启用禁用: 0=禁用,1=启用
			String enableStatus = request.getParameter("enable_status");
			// 节点类型：1=子系统、一级模块,2=子模块、功能,3=页面按钮
			String nodeType = request.getParameter("node_type");
			// 懒加载，只获取1层子节点
			if ("1".equals(lazy_init)) {
				sql.append(" WHERE ");
				if (RmStringHelper.checkNotEmpty(totalCode)) {
					sql.append("TOTAL_CODE like '" + totalCode + "%' and ");
				}

				sql.append(" ");
				sql.append(RmSqlHelper.getFunction(RmSqlHelper.Function.LENGTH,
						RmConfig.getSingleton().getDatabaseProductName()));
				sql.append("(TOTAL_CODE) = "
						+ (RmConfig.getSingleton().getDefaultTreeCodeFirst()
								.length() + totalCode.length()));
				if (RmStringHelper.checkNotEmpty(enableStatus)
						&& "1".equals(enableStatus)) {
					sql.append(" and ENABLE_STATUS='1'");
				}
				if (RmStringHelper.checkNotEmpty(nodeType)
						&& "3".equals(nodeType)) {
					sql.append(" and NODE_TYPE!='3'");
				}
				sql.append(" order by ");
				sql.append(RmSqlHelper.getFunction(RmSqlHelper.Function.SUBSTR,
						RmConfig.getSingleton().getDatabaseProductName()));
				sql.append("(TOTAL_CODE, 1, ");
				sql.append(RmSqlHelper.getFunction(RmSqlHelper.Function.LENGTH,
						RmConfig.getSingleton().getDatabaseProductName()));
				sql.append("(TOTAL_CODE)-3)");
				sql.append(", ORDER_CODE");
				// sql.append(" order by TOTAL_CODE");

				List<RmCommonVo> lcvo = RmProjectHelper
						.getCommonServiceInstance().doQuery(sql.toString());
				if (totalCode.length() < 7) {
					model.addAttribute("isParent", true);
				}
				map.put("lcvo", lcvo);
				model.addAttribute("beans", lcvo);
			} else {
				if (RmStringHelper.checkNotEmpty(enableStatus)
						&& "1".equals(enableStatus)) {
					sql.append(" where ENABLE_STATUS='1'");
				}
				sql.append(" order by TOTAL_CODE");
				List<RmCommonVo> lcvo = RmProjectHelper
						.getCommonServiceInstance().doQuery(sql.toString());
				map.put("lcvo", lcvo);
				model.addAttribute("beans", lcvo);
			}
		} else { // 从全局缓存读取菜单项
			List<String> lcvo = RmProjectHelper.getRmUserVo(request)
					.getMenuList();
			List<Object> myTrees = new ArrayList<Object>();
			// model.addAttribute("beans", lcvo);
			for (String thisCode : lcvo) {
				if (thisCode.startsWith(totalCode)) {
					RmFunctionNodeVo fnvo = RmFunctionNodeCache
							.getFunctionNode(thisCode);
					if (fnvo != null) {
						String hasChild = "1";
						DeepTreeVo dtv = new DeepTreeVo(thisCode,
								fnvo.getName(), hasChild, "");
						if (fnvo.getData_value() != null
								&& fnvo.getData_value().length() > 0) {
							// String finalHref =
							// RmUrlHelper.replaceParameter(fnvo.getData_value(),
							// request);
							String finalHref = fnvo.getData_value();
							dtv.setHrefPath(request.getContextPath()
									+ finalHref);
						}
						dtv.setTarget("contentFrame");
						if (fnvo.getIcon() != null
								&& fnvo.getIcon().startsWith("/")) {
							dtv.setLogoImagePath(request.getContextPath()
									+ fnvo.getIcon());
						}
						if (thisCode.length() == totalCode.length()
								+ RmConfig.getSingleton()
										.getDefaultTreeCodeFirst().length()) {
							dt.addTreeNode(dtv);
						} else {
							dt.addTreeNode(
									thisCode.substring(0, thisCode.length()
											- RmConfig.getSingleton()
													.getDefaultTreeCodeFirst()
													.length()), dtv);
						}
					}
					myTrees.add(fnvo);
				}
			}
			map.put("lcvo", myTrees);
		}

		return map;
	}

	/**
	 * 获取带有权限的功能菜单
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getTreeOrg")
	public String getTreeOrg(Model model, HttpServletRequest request) {
		// List list=new ArrayList();
		// IRmCommonService cs = RmProjectHelper.getCommonServiceInstance();
		List<String> codeList = RmProjectHelper.getRmUserVo(request)
				.getMenuList();
		if (codeList != null && codeList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < codeList.size(); i++) {
				if (codeList.get(i) != null && !"".equals(codeList.get(i))) {
					sb.append("'");
					sb.append(codeList.get(i));
					sb.append("'");
					if (i < codeList.size() - 1) {
						sb.append(",");
					}
				}
			}
			String sql = sqlJoinAuthorize_resource(
					RmProjectHelper.getRmUserVo(request).getParty_id_all(),
					"RM_FUNCTION_NODE",
					"RM_FUNCTION_NODE",
					"TOTAL_CODE",
					"RM_FUNCTION_NODE.ID, (select count(*) from RM_FUNCTION_NODE fn where fn.NODE_TYPE!='3' and fn.TOTAL_CODE like concat(RM_FUNCTION_NODE.TOTAL_CODE,'1%')) CHILD_COUNT, RM_FUNCTION_NODE.NODE_TYPE, RM_FUNCTION_NODE.ORDER_CODE, RM_FUNCTION_NODE.FUNCTION_PROPERTY, RM_FUNCTION_NODE.ENABLE_STATUS, RM_FUNCTION_NODE.NAME, RM_FUNCTION_NODE.DESCRIPTION, RM_FUNCTION_NODE.DATA_VALUE, RM_FUNCTION_NODE.TOTAL_CODE, RM_FUNCTION_NODE.IS_LEAF, RM_FUNCTION_NODE.ICON",
					" and RM_FUNCTION_NODE.TOTAL_CODE in ("
							+ sb.toString()
							+ ") and length(RM_FUNCTION_NODE.TOTAL_CODE)=6 and RM_FUNCTION_NODE.ENABLE_STATUS='1' and RM_FUNCTION_NODE.NODE_TYPE!='3' order by RM_FUNCTION_NODE.ORDER_CODE, SUBSTR(RM_FUNCTION_NODE.TOTAL_CODE,1,length(RM_FUNCTION_NODE.TOTAL_CODE)"
							+ ")");
			List<RmCommonVo> lvos = RmProjectHelper.getCommonServiceInstance()
					.doQuery(sql);
			model.addAttribute("lvos", lvos);
		}
		return "ztree/leftOrgZtree";

	}

	public static String sqlJoinAuthorize_resource(String[] party_ids,
			String tableName, String tableNameAlias, String joinColumnName,
			String selectStr, String whereOrderStr) {
		StringBuilder sb = new StringBuilder();
		// sb.append("select distinct(ar.OLD_RESOURCE_ID), ar.DEFAULT_ACCESS, ar.DEFAULT_IS_AFFIX_DATA, ar.DEFAULT_ACCESS_TYPE, ar.DEFAULT_IS_RECURSIVE, arr.AUTHORIZE_STATUS, arr.IS_AFFIX_DATA, arr.ACCESS_TYPE, arr.IS_RECURSIVE, ");
		sb.append("select distinct(ar.OLD_RESOURCE_ID), ");
		sb.append(selectStr);
		sb.append(" from RM_AUTHORIZE_RESOURCE ar left join RM_AUTHORIZE_RESOURCE_RECORD arr on ar.id=arr.AUTHORIZE_RESOURCE_ID join ");
		sb.append(tableName);
		sb.append(" ");
		sb.append(tableNameAlias);
		sb.append(" on ar.OLD_RESOURCE_ID=");
		sb.append(tableNameAlias);
		sb.append(".");
		sb.append(joinColumnName);
		sb.append(" where (ar.DEFAULT_ACCESS='1' or arr.PARTY_ID in(");
		sb.append(RmStringHelper.parseToSQLString(party_ids));
		sb.append(") )");
		sb.append(whereOrderStr != null ? whereOrderStr : "");
		return sb.toString();
	}

	/**
	 * 下载文件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "download")
	public void download(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String relaPath = request.getParameter(ISystemConstant.RELA_PATH);
		if (!StringHelper.isEmpty(relaPath)) {
			if (relaPath.contains("..")) {
				throw new PjException("请求路径不合法");
			}
		}
		if (!StringHelper.isEmpty(relaPath)) {
			// 构造完整的文件名路径
			String rootPath;
			rootPath = pubParamService.getRootPath();
			String fullImagePath;
			fullImagePath = rootPath + relaPath;
			fileDownloadService.download(fullImagePath, response);
		}
	}

	/**
	 * 完整的文件名路径下载文件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "pathdownload")
	public void pathload(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String relaPath = request.getParameter(ISystemConstant.RELA_PATH);
		String paramsTrans = new String(relaPath.toString().getBytes("ISO-8859-1"),
				"UTF-8");
		relaPath = java.net.URLDecoder.decode(paramsTrans, "UTF-8");
		if (!StringHelper.isEmpty(relaPath)) {
			if (relaPath.contains("..")) {
				throw new PjException("请求路径不合法");
			}
		}
		if (!StringHelper.isEmpty(relaPath)) {
			System.out.println(relaPath);
			String rootPath;
			rootPath = pubParamService.getPurRootPath("03");
			String fullImagePath;
			fullImagePath = rootPath + relaPath;
			fileDownloadService.download(fullImagePath, response);
		}
	}
	
	/**
	 * 显示图片
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "showImg")
	public void showImg(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String relaPath = request.getParameter(ISystemConstant.RELA_PATH);
		String type = request.getParameter("type");

		if (!StringHelper.isEmpty(relaPath)) {
			// 构造完整的图片路径
			String rootPath;
			if(StringHelper.isEmpty(type)){
				rootPath = pubParamService.getRootPath();
			}else{
				rootPath = pubParamService.getPurRootPath(type);
			}
			String fullImagePath;
			fullImagePath = rootPath + relaPath;
			imageShowService.show(fullImagePath, response);
		}
	}

	/**
	 * activiti 人员组织机构
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "showAssinDetp")
	public String showAssinDetp(Model model, HttpServletRequest request) {
		String view_id = request.getParameter("view_id");
		List<RmCommonVo> lcvo = RmProjectHelper.getCommonServiceInstance()
				.doQuery("select ID, NAME from RM_PARTY_VIEW");
		Map<String, String> mpv = new HashMap<String, String>();
		for (RmCommonVo cvo : lcvo) {
			mpv.put(cvo.getString("id"), cvo.getString("name"));
			if (view_id == null) {
				view_id = cvo.getString("id");
			}
		}

		String sValue = "";
		if (IOrgauthConstants.PartyView.DEFAULT.id().equals(view_id)) {
			sValue = IOrgauthConstants.OrgTree.DEFAULT.value();
		}

		String cmd = request.getParameter("cmd") != null ? request
				.getParameter("cmd") : sValue;
		if (view_id == null) {

			view_id = "";
		}
		return "redirect:/pubController/getParty?cmd=" + cmd + "&view_id="
				+ view_id
				+ "&is_href=1&reference=1&enableCookie=true&source=assignDepts";
	}

	/**
	 * 2016/12/31根据total_code,获取该节点的下级菜单
	 * 
	 * @param model
	 * @param reques
	 * @return
	 */
	@RequestMapping(value = "getTreeOrgAjax", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getTreeOrgAjax(Model model,
			HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<RmCommonVo> lvos = new ArrayList<RmCommonVo>();
		String total_code = request.getParameter("total_code");

		if (total_code == null || "".equals(total_code)
				|| total_code.equals("null")) {
			map.put("lvos", lvos);
			return map;
		}
		HttpSession session = RmJspHelper.getSession(request, false);
		if(session==null){
			
			throw new PjException("请重新登录！");
		}
		List<RmCommonVo> child_left_true = (List<RmCommonVo>) session
				.getAttribute("child_" + total_code);// 从session中获取左侧一级菜单
		if (child_left_true != null && child_left_true.size() > 0) {// 说明session中有数据
			map.put("lvos", child_left_true);// 将父节点对应的子节点从session 中取出 放入map 返回
			return map;
		}
		List<String> codeList = RmProjectHelper.getRmUserVo(request)
				.getMenuList();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < codeList.size(); i++) {
			if (codeList.get(i) != null && !"".equals(codeList.get(i))) {
				sb.append("'");
				sb.append(codeList.get(i));
				sb.append("'");
				if (i < codeList.size() - 1) {
					sb.append(",");
				}
			}
		}

		String sql = sqlJoinAuthorize_resource(
				RmProjectHelper.getRmUserVo(request).getParty_id_all(),
				"RM_FUNCTION_NODE",
				"RM_FUNCTION_NODE",
				"TOTAL_CODE",
				"RM_FUNCTION_NODE.ID, (select count(*) from RM_FUNCTION_NODE fn where fn.NODE_TYPE!='3' and fn.TOTAL_CODE like concat(RM_FUNCTION_NODE.TOTAL_CODE,'1%')) CHILD_COUNT, RM_FUNCTION_NODE.NODE_TYPE, RM_FUNCTION_NODE.ORDER_CODE, RM_FUNCTION_NODE.FUNCTION_PROPERTY, RM_FUNCTION_NODE.ENABLE_STATUS, RM_FUNCTION_NODE.NAME, RM_FUNCTION_NODE.DESCRIPTION, RM_FUNCTION_NODE.DATA_VALUE, RM_FUNCTION_NODE.TOTAL_CODE, RM_FUNCTION_NODE.IS_LEAF, RM_FUNCTION_NODE.ICON",
				" and length(RM_FUNCTION_NODE.TOTAL_CODE)>6 and RM_FUNCTION_NODE.TOTAL_CODE like '"
						+ total_code
						+ "%'and RM_FUNCTION_NODE.TOTAL_CODE in("
						+ sb
						+ ") and RM_FUNCTION_NODE.ENABLE_STATUS='1' and RM_FUNCTION_NODE.NODE_TYPE!='3' order by SUBSTR(RM_FUNCTION_NODE.TOTAL_CODE,1,length(RM_FUNCTION_NODE.TOTAL_CODE)"
						+ "),RM_FUNCTION_NODE.ORDER_CODE");
		lvos = RmProjectHelper.getCommonServiceInstance().doQuery(sql);
		session.setAttribute("child_" + total_code, lvos);
		map.put("lvos", lvos);
		return map;
	}

	/**
	 * 2016/12/31系统后台登录后重定向该节点,查询出左侧受控树菜单，讲该一级菜单放入session 中
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "index")
	public String backLoginToIndex(Model model, HttpServletRequest request) {

		HttpSession session = RmJspHelper.getSession(request, false);

		List<RmCommonVo> sessionLeftSides = (List<RmCommonVo>) session.getAttribute("leftSides");// 从session中获取左侧一级菜单

		String  goToUrl=null;
		UserVo  useVo=userService.get(RmProjectHelper.getRmUserId(request));
		if(ISystemConstant.DICTIONARY_USER_TYPE_0.equals(useVo.getUserType())){
			//客户
			goToUrl="back/index0";
			doCustomerHomepage(request, model, useVo.getId());
			
		}else  if(ISystemConstant.DICTIONARY_USER_TYPE_4.equals(useVo.getUserType())){
			//物流公司
			goToUrl="back/index2";
			doLogisticsCompanyHomepage(request, model, useVo);
		}else {
			goToUrl="back/index";
			getPriceMsg(request, model);//index页面上的tab数据展示
		}

		if (sessionLeftSides != null && sessionLeftSides.size() > 0) {// 说明session中有数据
			return  goToUrl;
		}

		List<String> codeList = RmProjectHelper.getRmUserVo(request)
				.getMenuList();
		if (codeList != null && codeList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < codeList.size(); i++) {
				if (codeList.get(i) != null && !"".equals(codeList.get(i))) {
					sb.append("'");
					sb.append(codeList.get(i));
					sb.append("'");
					if (i < codeList.size() - 1) {
						sb.append(",");
					}
				}
			}
			String sql = sqlJoinAuthorize_resource(
					RmProjectHelper.getRmUserVo(request).getParty_id_all(),
					"RM_FUNCTION_NODE",
					"RM_FUNCTION_NODE",
					"TOTAL_CODE",
					"RM_FUNCTION_NODE.ID, (select count(*) from RM_FUNCTION_NODE fn where fn.NODE_TYPE!='3' and fn.TOTAL_CODE like concat(RM_FUNCTION_NODE.TOTAL_CODE,'1%')) CHILD_COUNT, RM_FUNCTION_NODE.NODE_TYPE, RM_FUNCTION_NODE.ORDER_CODE, RM_FUNCTION_NODE.FUNCTION_PROPERTY, RM_FUNCTION_NODE.ENABLE_STATUS, RM_FUNCTION_NODE.NAME, RM_FUNCTION_NODE.DESCRIPTION, RM_FUNCTION_NODE.DATA_VALUE, RM_FUNCTION_NODE.TOTAL_CODE, RM_FUNCTION_NODE.IS_LEAF, RM_FUNCTION_NODE.ICON",
					" and RM_FUNCTION_NODE.TOTAL_CODE in ("
							+ sb.toString()
							+ ") and length(RM_FUNCTION_NODE.TOTAL_CODE)=6 and RM_FUNCTION_NODE.ENABLE_STATUS='1' and RM_FUNCTION_NODE.NODE_TYPE!='3' order by RM_FUNCTION_NODE.ORDER_CODE, SUBSTR(RM_FUNCTION_NODE.TOTAL_CODE,1,length(RM_FUNCTION_NODE.TOTAL_CODE)"
							+ ")");
			List<RmCommonVo> lvos = RmProjectHelper.getCommonServiceInstance()
					.doQuery(sql);

			session.setAttribute("leftSides", lvos);// 将查出的一级受控菜单放入session 中

			lvos = (List<RmCommonVo>) session.getAttribute("leftSides");
			System.out.println(lvos.size());
		} else {
			List<RmCommonVo> lvos = new ArrayList<RmCommonVo>();
			session.setAttribute("leftSides", lvos);// session中没有受控菜单放入空
		}
		
		
		return goToUrl;
	}

	/**
	 * 修改后台登录密码
	 */
	@RequestMapping(value = "updateSysPsd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> updateSysPsd(Model model,
			HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();

		String old_password = request.getParameter("old_password");
		String password = request.getParameter("password");
		String password_again = request.getParameter("password_again");

		if (StringHelper.isEmpty(old_password)
				|| StringHelper.isEmpty(password)
				|| StringHelper.isEmpty(password_again)) {

			throw new PjException("密码不可为空");
		}

		if (!password_again.equals(password)) {

			throw new PjException("两次输入的新密码不一致");

		}
		// 当前登录人的用户id
		IRmUserService userService = (IRmUserService) RmBeanFactory
				.getBean(IRmUserConstants.SERVICE_KEY);
		org.quickbundle.orgauth.rmuser.vo.RmUserVo vo = userService
				.find(RmProjectHelper.getRmUserId(request));

		String uuid = vo.getAttribute2() == null ? "" : vo.getAttribute2();

		old_password = pubParamService.signRmUserPwd(uuid, old_password);// 验证旧密码

		if (!vo.getPassword().equals(old_password)) {

			throw new PjException("旧密码输入错误");
		}
		boolean success = userService.executeChangePassword(vo.getId(),
				request.getParameter("password"));
		if (!success) {
			throw new PjException("密码修改失败");

		}
		return result;

	}

	/**
	 * 公共上传图片方法
	 * 
	 * @param response
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value="picUpload")
	public String picUpload(HttpServletResponse response,HttpServletRequest request,
			@RequestParam("appealPic") MultipartFile file, @RequestParam(value="id", required=true)String id,@RequestParam(value="billType", required=true)String billType) {
		response.setContentType("text/html; charset=utf-8");
		try {
			if (file == null || file.isEmpty()) {
				response.getWriter().write("{'message':'文件为空'}");

				return null;
			}
			BufferedImage bi = ImageIO.read(file.getInputStream());
			
			if(bi == null){
				response.getWriter().write("{'message':'上传文件不是图片！'}");
				return null;
			}
			
			if(!ImageUtil.isPicture(file.getOriginalFilename(), null)){
				response.getWriter().write("{'message':'文件后缀不对！'}");
				return null;
			}
			//图片相对路劲
			String fullPath =null;
			if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(billType)){
				//销售订单
				fullPath=orderService.upload(file, id, request);
			}else if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(billType)){
				//汽运配送 
				
			}else if(ISystemConstant.DICTIONARY_BILL_TYPE_03.equals(billType)){
				//汽运调用
				fullPath=carRequisitionPlanService.upload(file, id, request);
			}else if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(billType)){
				//铁路
				fullPath=trainsRequisitionPlanService.upload(file, id, request);
			}

			response.getWriter().write("{'path':'" + fullPath + "', 'fileName':'" + fullPath.substring(fullPath.lastIndexOf(File.separator) + 1) + "'}");
			return null;
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			response.getWriter().write("{'message':'上传失败,请重新上传'}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="deleteImg", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> deleteImg(HttpServletRequest request, @RequestParam(value="id", required=true)String id, @RequestParam(value="imgPath", required=true)String imgPath, @RequestParam(value="billType", required=true)String billType){

		Map<String, Object> result  =  new HashMap<String, Object>();
		
		
		if(ISystemConstant.DICTIONARY_BILL_TYPE_01.equals(billType)){
			//销售订单
			
			orderService.deleteOrderImg(request, id, imgPath,result);
			
		}else if(ISystemConstant.DICTIONARY_BILL_TYPE_02.equals(billType)){
			//汽运配送 
			
		}else if(ISystemConstant.DICTIONARY_BILL_TYPE_03.equals(billType)){
			//汽运调用
		}else if(ISystemConstant.DICTIONARY_BILL_TYPE_04.equals(billType)){
			//铁路
		}
		
		
		if(ISystemConstant.AJAX_RESULT_FAIL.equals(result.get(ISystemConstant.AJAX_STATUS))){
			return result;
		}
		
		 pubParamService.deleteImg(request,imgPath, null);
		 
		 
		 return  result;
	}
	
	
	/**
	 * 查询待处理事项
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="queryHanding")
	public void queryHanding(HttpServletRequest request,HttpServletResponse response) throws IOException{

		boolean isLogin = false;
		HttpSession session = RmJspHelper.getSession(request, false);
		
        if(session != null && session.getAttribute(IGlobalConstants.RM_USER_VO) != null) {
            isLogin = true; 
        }
        
		if(isLogin){//已登录查看对应用户 id  获取到对应角色id
			
//			String party_id=RmProjectHelper.getRmUserVo(request).getParty_id_org();//团体id
			String userId=RmProjectHelper.getRmUserId(request);
			
			List<RmCommonVo> list=pubPartyService.getAllNodeByRole(userId);//根据节点直接查询对应的待办事项
			 
			 response.getWriter().write(JSONObject.toJSONString(list));
		
		}else{
			
			List list=new ArrayList();
			response.getWriter().write(JSONObject.toJSONString(list));
			
		}
		
		
		
	}
	/**
	 * 查副产品今日价格
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getPriceMsg")
	public Map<String,Object> getPriceMsg(HttpServletRequest request,Model model){
		
		Map<String,Object> result=new HashMap<String,Object>();
		
		StringBuilder sb=new StringBuilder();
		sb.append("'");
		sb.append(ISystemConstant.DICTIONARY_PRICE_STATUS_3);
		sb.append("','");
		sb.append(ISystemConstant.DICTIONARY_PRICE_STATUS_2);
		sb.append("'");
		result.put("status",sb.toString());//已审核/待审核
		result.put("mainFlag",ISystemConstant.DICTIONARY_MAIN_SECONDARY_PRODUCT_FLAG_02);//副产品
		List<PriceVo> listPrice=priceService.listSubProPrice(result, "", -1, -1);//查副产品今日价格
		
		String sql="select BASE_PRICE.PRODUCT_NAME as name from BASE_PRICE group by BASE_PRICE.PRODUCT_NAME ";
		List<RmCommonVo> list=RmProjectHelper.getCommonServiceInstance().doQuery(sql);
//		result.remove("status");
		TodayCarTabVo todayCarTabVo=priceService.getTodayCarMsg();
		
		List<PriceVo> listPriceTrend=priceService.listPriceTrend(result, "to_char(BASE_PRICE.START_TIME,'yyyy-mm-dd') asc", 1, -1);
		
		model.addAttribute("listPro", list);//存放产品名
		model.addAttribute("listPrice", listPrice);//存放副产品今日价格
		model.addAttribute("todayCarTabVo", todayCarTabVo);//存放今日车辆数tab数据
		model.addAttribute("listPriceTrend", listPriceTrend);//产品价格趋势
		
		return result;
		
	}
	
	
	/**
	 *  客户首页   
	 * @param request
	 * @return
	 */
	@RequestMapping(value="doCustomerHomepage")
	public Map<String,Object> doCustomerHomepage(HttpServletRequest request,Model model,String loginId){
		//通知公告
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		result.put("recipientId",loginId);
		result.put("doMessages","doMessages");
		List<RecipientsVo>  list=recipientsService.list(result," BUS_RECIPIENTS.MESSAGE_STATUS  desc",1,6);
		model.addAttribute(ISystemConstant.AJAX_LIST, list);
		
		//查副产品今日价格
		result.clear();
		result.put("status","'"+ISystemConstant.DICTIONARY_PRICE_STATUS_3+"','"+ISystemConstant.DICTIONARY_PRICE_STATUS_2+"'");//已审核/待审核
		result.put("mainFlag",ISystemConstant.DICTIONARY_MAIN_SECONDARY_PRODUCT_FLAG_02);//副产品
		List<PriceVo> listPrice=priceService.listSubProPrice(result, "",-1,-1);
		model.addAttribute("listPrice", listPrice);
		
		
		String sql="select BASE_PRICE.PRODUCT_NAME as name from BASE_PRICE group by BASE_PRICE.PRODUCT_NAME ";
		List<RmCommonVo> listPro=RmProjectHelper.getCommonServiceInstance().doQuery(sql);
		model.addAttribute("listPro", listPro);//存放产品名
		List<PriceVo> listPriceTrend=priceService.listPriceTrend(result, "to_char(BASE_PRICE.START_TIME,'yyyy-mm-dd') asc", 1, -1);
		model.addAttribute("listPriceTrend", listPriceTrend);

		
		return result;
		
	}
	
	/**
	 * 物流公司首页   
	 * @param request
	 * @return
	 */
	@RequestMapping(value="doLogisticsCompanyHomepage")
	public Map<String,Object> doLogisticsCompanyHomepage(HttpServletRequest request,Model model,UserVo  useVo){
		//通知公告
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("delete_flag",ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		result.put("recipientId",useVo.getOrganizationId());
		result.put("doMessages","doMessages");
		List<RecipientsVo>  list=recipientsService.list(result," BUS_RECIPIENTS.MESSAGE_STATUS  desc",1,6);
		model.addAttribute(ISystemConstant.AJAX_LIST, list);
	
		
		//待办
		List<RmCommonVo> listRmCommonVo=pubPartyService.getAllNodeByRole(useVo.getId());//根据节点直接查询对应的待办事项
		model.addAttribute("listRmCommonVo", listRmCommonVo);

		
		
		return result;
		
	}
	
	
}
