/**
 * 软件著作权：东方汇创
 *
 * 系统名称：  中煤陕西榆林能源化工产品销售智能管理系统
 *
 * 文件名称：  DistributionPlanService.java
 *
 * 功能描述：  配送计划服务
 * 
 * 版本历史：
 * 
 * 2017-01-04   1.0.0版 （龙色波）（创建文件）
 */
package com.dfhc.bus.distributionplan.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dfhc.ISystemConstant;
import com.dfhc.PjException;
import com.dfhc.base.logisticscompany.service.LogisticsCompanyService;
import com.dfhc.base.logisticscompany.vo.LogisticsCompanyVo;
import com.dfhc.base.shipmode.service.ShipModeService;
import com.dfhc.base.shipmode.vo.ShipModeVo;
import com.dfhc.bus.cardistributionplan.dao.CarDistributionPlanDao;
import com.dfhc.bus.cardistributionplan.service.ExcelHandleExpandLongImpl;
import com.dfhc.bus.cardistributionplan.service.ExcelHandleExpandSqlDateImpl;
import com.dfhc.bus.cardistributionplan.service.ExcelHandleExpandSqlDateMakeDateImpl;
import com.dfhc.bus.cardistributionplan.vo.CarDistributionPlanVo;
import com.dfhc.bus.distributionplan.IDistributionPlanConstants;
import com.dfhc.bus.distributionplan.dao.DistributionPlanDao;
import com.dfhc.bus.distributionplan.vo.DistributionPlanVo;
import com.dfhc.pub.service.ExcelService;
import com.dfhc.pub.service.FileUploadService;
import com.dfhc.pub.service.PubParamService;
import com.dfhc.pub.vo.ExcelUtilVo;
import com.dfhc.util.DateUtil;
import com.dfhc.util.StringHelper;
/**
 * 配送计划服务
 * 
 * @author 龙色波
 * @see 参见的类
 * @remark 默认将类中的所有public函数纳入事务管理
 */
@Service
@Transactional(readOnly = true)
public class DistributionPlanService implements IDistributionPlanConstants {
	@Autowired
	private ShipModeService shipModeService;
    @Autowired
    /**
     * 配送计划数据访问对象
     */
    private DistributionPlanDao distributionPlanDao;

    @Autowired
    private  LogisticsCompanyService logisticsCompanyService;
    @Autowired
    private  FileUploadService fileUploadService;
    @Autowired
    private PubParamService  pubParamService;
    
    @Autowired
    private ExcelService excelService;
    
    @Autowired
    private CarDistributionPlanDao carDistributionPlanDao;
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(DistributionPlanVo vo) {
        //检查插入vo合法性
        verifyInsertVo(vo);
        String id = distributionPlanDao.insert(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "配送计划插入了1条记录,id={}", id);
        return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(DistributionPlanVo[] vos) {
        String[] ids = distributionPlanDao.insert(vos);
        RmProjectHelper.log(LOG_TYPE_NAME, "配送计划插入了{}条记录,id={}", vos.length, Arrays.toString(ids));
        return ids;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
        int sum = distributionPlanDao.delete(id);
        RmProjectHelper.log(LOG_TYPE_NAME, "配送计划删除了{}条记录,id={}", sum, id);
        return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
        int sum = distributionPlanDao.delete(ids);
        RmProjectHelper.log(LOG_TYPE_NAME, "配送计划删除了{}条记录,ids={}", sum, Arrays.toString(ids));
        return sum;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(DistributionPlanVo vo) {
        //检查更新vo合法性
        verifyUpdateVo(vo);
        int sum = distributionPlanDao.update(vo);
        RmProjectHelper.log(LOG_TYPE_NAME, "配送计划更新了{}条记录,id={}", sum, vo.getId());
        return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
    public int update(DistributionPlanVo[] vos) {        
        int finalSum = 0;
        for (int i = 0; i < vos.length; i++) {
            finalSum += update(vos[i]);
        }
        RmProjectHelper.log(LOG_TYPE_NAME, "配送计划批量更新了{}条记录", finalSum);
        return finalSum;
    }

    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param vos 更新的VO对象数组
     * @return new int[2]{insert的记录数, update的记录数}   
     */
    public int[] insertUpdateBatch(DistributionPlanVo[] vos) {
        int[] sum_insert_update = new int[2];
        List<DistributionPlanVo> lInsert = new ArrayList<DistributionPlanVo>();
        List<DistributionPlanVo> lUpdate = new ArrayList<DistributionPlanVo>();
        for (int i = 0; i < vos.length; i++) {
            if(vos[i].getId() != null) {
                lUpdate.add(vos[i]);
            } else {
                lInsert.add(vos[i]);
            }
        }
        if(lInsert.size() > 0) {
            sum_insert_update[0] = insert(lInsert.toArray(new DistributionPlanVo[0])).length;
        }
        if(lUpdate.size() > 0) {
            sum_insert_update[1] = update(lUpdate.toArray(new DistributionPlanVo[0]));
        }
        return sum_insert_update;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public DistributionPlanVo get(String id) {
        DistributionPlanVo vo = distributionPlanDao.get(id);
        return vo;
    }
    
    /**
     * 查询总记录数，带查询条件
     * 
     * @param searchPara 查询条件Map
     * @return 总记录数
     */
    public int getCount(Map<String, Object> searchPara) {
        int sum = distributionPlanDao.getCount(searchPara);
        return sum;
    }

    /**
     * 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<DistributionPlanVo> list(Map<String, Object> searchPara, String orderStr) {
        return list(searchPara, orderStr, 1, Integer.MAX_VALUE);
    }

    /**
     * 通过查询条件获得所有的VO对象列表，带翻页，带排序字符，只查询必要的字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录
     * @return 查询到的VO列表
     */
    public List<DistributionPlanVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        return list(searchPara, orderStr, startIndex, size, false);
    }
    
    /**
     * 通过查询条件获得所有的VO对象列表，带翻页，带排序字符，根据selectAllClumn判断是否查询所有字段
     *
     * @param searchPara 查询条件Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录
     * @param allColumn 是否查询所有列，即 SELECT * FROM ...(适用于导出)
     * @return 查询到的VO列表
     */
    public List<DistributionPlanVo> list(Map<String, Object> searchPara, String orderStr, int startIndex, int size, boolean allColumn) {
        List<DistributionPlanVo> lResult = distributionPlanDao.list(searchPara, orderStr, startIndex, size, allColumn);
        return lResult;
    }
    
    /**
     * 按条件搜索，走MyBatis的XML文件的search
     * 
     * @param searchPara 搜索参数的Map
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录
     * @return 查询到的VO列表
     */
    public List<DistributionPlanVo> search(Map<String, Object> searchPara, String orderStr, int startIndex, int size) {
        List<DistributionPlanVo> lResult = distributionPlanDao.search(searchPara, orderStr, startIndex, size);
        return lResult;
    }
    /**
     * 校验更新vo
     * @param vo
     */
    private void verifyUpdateVo(DistributionPlanVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
        if(StringHelper.isEmpty(vo.getId())){
           throw new PjException("id为空!");
        }
    }
    /**
     * 校验插入vo
     * @param vo
     */
    private void verifyInsertVo(DistributionPlanVo vo) {
        if(vo==null){
           throw new PjException("vo 对象为空!");
        }
    }
    /**
     * 批量更新
     * @param vos vo数组
     */
    public void updateBatch(List<DistributionPlanVo> vos) {
        for(DistributionPlanVo vo:vos){
           verifyUpdateVo(vo);
        }
        distributionPlanDao.updateBatch(vos);
    }
    /**
     * 批量插入
     * @param vos vo数组
     */
    public void insertBatch(List<DistributionPlanVo> vos) {
        for(DistributionPlanVo vo:vos){
           verifyInsertVo(vo);
        }
        distributionPlanDao.insertBatch(vos);
    }
    /**
     * 逻辑删除单条记录
     * @param request 页面请求
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String id) {
        DistributionPlanVo vo = distributionPlanDao.get(id);
        if(vo==null){
           throw new PjException("id:"+id+"没找到!");
        }
        if(ISystemConstant.DICTIONARY_RM_YES_NOT_1.equals(vo.getDelete_flag())){
           throw new PjException("id:"+id+"已经逻辑删除!");
        }
        RmVoHelper.markLogicDeleteStamp(request,vo);
        if(distributionPlanDao.update(vo)!=1){
           throw new PjException("更新删除标志失败!");
        }
        return 1;
    }

    /**
     * 逻辑删除多条记录
     * @param request 页面请求
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int deleteLogic(HttpServletRequest request,String ids[]) {
        if(ids!=null && ids.length>0 ){
           for(String id:ids){
               deleteLogic(request,id);
           }
           return ids.length;
        }else{
           throw new PjException("ids 为空!");
        }
    }

    
    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    @SuppressWarnings("static-access")
	public String insert(HttpServletRequest request, DistributionPlanVo vo, MultipartFile sendCarPlanImages) {
    	//单号
    	vo.setPlanCode(pubParamService.getSequenceNumber(IDistributionPlanConstants.CAR_DISTRIBUTION_PLAN_CODE));
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        String id = distributionPlanDao.insert(vo);
        
        boolean flag=false;
        //照片
        if(sendCarPlanImages!=null && !sendCarPlanImages.isEmpty()){
        	String path=fileUploadService.uploadFile(sendCarPlanImages,id,ISystemConstant.DICTIONARY_ROOT_PATH_01);
        	vo.setSendCarPlanImage(path);
        	List<CarDistributionPlanVo> list = null;
        	LinkedHashMap<String, Object> fieldMap = new LinkedHashMap<String, Object>();
        	
        	assigment(fieldMap);
        	
        	try {
        		String  rootPath=pubParamService.getPurRootPath(ISystemConstant.DICTIONARY_ROOT_PATH_01) + path;
				list = excelService.excelToList(new FileInputStream(new File(rootPath)), 0, CarDistributionPlanVo.class, fieldMap, new String[0]);
				
				if(!CollectionUtils.isEmpty(list)){
					for (CarDistributionPlanVo carDistributionPlanVo : list) {
						//主表发车日期
						vo.setPlanDistributionDate(carDistributionPlanVo.getSendCarDate());

						carDistributionPlanVo.setPlanCode(vo.getPlanCode());
						carDistributionPlanVo.setDistributionPlanId(vo.getId());
						
						//补全信息
						doGetVo(request, carDistributionPlanVo);
					}
				}
				
				RmVoHelper.markCreateStamp(request, list);
				carDistributionPlanDao.insertBatch1(list);
				
        	
        	} catch (PjException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
//        	excelToList
        	//读取excel表数据
//        	insertReadExcel(sendCarPlanImages, request, id, list);
        	
            flag = true;
        }
        if(flag){
        	this.distributionPlanDao.update(vo);
        }
       
        return id;
    }
 
    private void assigment(LinkedHashMap<String, Object> fieldMap) {
    	fieldMap.put("序号", "sequenceNumber");
    	
    	ExcelUtilVo vo = new ExcelUtilVo();
    	vo.setHandleClass(new ExcelHandleExpandSqlDateImpl());
    	vo.setFiled("acceptOrderTime");
    	
    	fieldMap.put("接收订单日期", vo);
    	
    	vo = new ExcelUtilVo();
    	vo.setHandleClass(new ExcelHandleExpandSqlDateMakeDateImpl());
    	vo.setFiled("sendCarDate");
    	fieldMap.put("安排订单日期", vo);
    	fieldMap.put("价格", "price");
    	fieldMap.put("发货仓库", "shipperCode");
    	fieldMap.put("物流公司", "logisticsCompany");
    	fieldMap.put("区域", "region");
    	fieldMap.put("客户", "customerName");
    	fieldMap.put("货物名称", "goodsName");
    	fieldMap.put("等级", "productLevel");
    	
    	fieldMap.put("接货人", "receivingPerson");
    	fieldMap.put("接货人电话", "receivingPersonPhone");
    	fieldMap.put("省份", "province");
    	//待添加字段
    	fieldMap.put("地区", "area");
    	
    	//待定
    	fieldMap.put("发货详细地址", "shippingAddress");
    	ExcelHandleExpandLongImpl excelHandleExpandLongImpl = new ExcelHandleExpandLongImpl();
    	vo = new ExcelUtilVo();
    	vo.setFiled("num");
    	vo.setHandleClass(excelHandleExpandLongImpl);
    	
    	fieldMap.put("数量(吨)", vo);
    	
    	vo = new ExcelUtilVo();
    	vo.setFiled("plannedMileage");
    	vo.setHandleClass(excelHandleExpandLongImpl);
    	fieldMap.put("里程", vo);
    	
    	vo = new ExcelUtilVo();
    	vo.setFiled("tonKm");
    	vo.setHandleClass(excelHandleExpandLongImpl);
    	fieldMap.put("元/吨.公里（合同）", vo);
    	
    	vo = new ExcelUtilVo();
    	vo.setFiled("unitPrice");
    	vo.setHandleClass(excelHandleExpandLongImpl);
    	fieldMap.put("单价（元/吨）", vo);
    	
    	vo = new ExcelUtilVo();
    	vo.setFiled("roundingPrice");
    	vo.setHandleClass(excelHandleExpandLongImpl);
    	fieldMap.put("取整单价", vo);
    	fieldMap.put("备注", "remark");
		
	}

	/**
     * 导入excel数据
     * @param excel
     * @param request
     * @param id 配送计划id
     */
//    private void insertReadExcel(MultipartFile excel,
//			HttpServletRequest request, String id, List<CarDistributionPlanVo> list) {
//    	POIFSFileSystem fs = null;
//    	HSSFWorkbook wb = null;
//    	HSSFSheet sheet = null;
//    	HSSFRow row = null;
//    	try {
//            fs = new POIFSFileSystem(excel.getInputStream());
//            wb = new HSSFWorkbook(fs);
//            sheet = wb.getSheetAt(0);
//            // 得到总行数
//            int rowNum = sheet.getLastRowNum();
//            row = sheet.getRow(0);
//            //总列数
//            int colNum = row.getPhysicalNumberOfCells();
//            // 正文内容应该从第二行开始,第一行为表头的标题
//            for (int i = 1; i <= rowNum; i++) {
//                
//            	CarDistributionPlanVo vo = new CarDistributionPlanVo();
//            	row = sheet.getRow(i);
//            	
//            	assignment(vo, row, colNum);
//            	
//                
//              
//            }
//            
//            
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        
//    	
//    	
//	}

//	private void assignment(CarDistributionPlanVo vo, HSSFRow row, int colNum) {
//		int c = 0;
//		String value = null;
//        while (c < colNum) {
//        	switch (c) {
//			case 1:
//				value = readValue(row, c);
//				if(!StringHelper.isEmpty(value)){
//					vo.setSequenceNumber(Long.valueOf(value));
//				}
//				break;
//			case 2:
//				value = readValue(row, c);
//				if(!StringHelper.isEmpty(value)){
//					vo.setAcceptOrderTime(DateUtil.toSqlDate(DateUtil.parser(value)));
//				}
//				break;
//			case 3:
//				value = readValue(row, c);
//				if(!StringHelper.isEmpty(value)){
//					vo.setMakeDate(DateUtil.toSqlDate(DateUtil.parser(value)));
//				}
//				break;
//			case 4://实际发车日期
//				//不做处理
//				break;
//			case 5:
//				value = readValue(row, c);
//				if(StringHelper.isEmpty(value)){
//					vo.setPrice(new BigDecimal(value));
//				}
//				//PRICE
//				break;
//			case 6://发货仓库
//				break;
//			case 7:
//				value = readValue(row, c);
//				if(!StringHelper.isEmpty(value)){
//					LogisticsCompanyVo logisticsCompanyVo = logisticsCompanyService.getCompanyVoByName(value);
//					
//					if(logisticsCompanyVo == null){
//						throw new PjException("物流公司名称错误，导入失败！");
//					}
//					
//					vo.setLogisticsCompanyId(logisticsCompanyVo.getId());
//					
//					vo.setLogisticsCompany(logisticsCompanyVo.getName());
//				
//				}
//				
//				break;
//			case 8:
//				value = readValue(row, c);
//				if(!StringHelper.isEmpty(value)){
//					
//					vo.setLogisticsCompany(value);
//				
//				}
//				
//				break;
//			case 9:
//				break;
//			case 10:
//				break;
//			case 11:
//				break;
//			case 12:
//				break;
//			case 13:
//				break;
//			case 14:
//				break;
//			case 15:
//				break;
//			case 16:
//				break;
//			case 17:
//				break;
//			case 18:
//				break;
//			case 19:
//				break;
//			case 20:
//				break;
//			case 21:
//				break;
//			case 22:
//				break;
//			case 23:
//				break;
//			case 24:
//				break;
//			case 25:
//				break;
//			default:
//				break;
//			}
//        	c++;
//		}
//		
//	}

//	private String readValue(HSSFRow row, int c) {
//		HSSFCell cell = row.getCell(c);
//		String value = cell.getStringCellValue();
//		
//		if(!StringHelper.isEmpty(value)){
//			value = value.trim();
//		}
//		return value;
//	}

	/**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public void update(HttpServletRequest request,DistributionPlanVo vo, MultipartFile sendCarPlanImages) {

    	RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
    	distributionPlanDao.update(vo);

        boolean flag=false;
        //照片
        if(sendCarPlanImages!=null && !sendCarPlanImages.isEmpty()){
        	String path=fileUploadService.uploadFile(sendCarPlanImages,vo.getId(),ISystemConstant.DICTIONARY_ROOT_PATH_01);
        	vo.setSendCarPlanImage(path);
            flag = true;
        }
        if(flag){
        	this.distributionPlanDao.update(vo);
        }
       
        return ;
    }

    
    
	/**
     * 导入时补全信息
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public void doGetVo(HttpServletRequest request,CarDistributionPlanVo carDistributionPlanVo) {
		String  shipperCode=null;

    	//制表日期
		carDistributionPlanVo.setMakeDate(new java.sql.Date(DateUtil.getNowDate().getTime()));
		//计划车数
		carDistributionPlanVo.setPlanCarNum(Long.valueOf(ISystemConstant.DICTIONARY_RM_YES_NOT_1));
		//状态已导入
		carDistributionPlanVo.setStatus(ISystemConstant.DICTIONARY_PLAN_STATUS_00);
		//剩余车数==计划车数
		carDistributionPlanVo.setRemainderCarNum(carDistributionPlanVo.getPlanCarNum());
		//业务类型  ：  主产品配送计划
		carDistributionPlanVo.setBusinessType(ISystemConstant.DICTIONARY_PLAN_TYPE_00);
		//发货编码
		shipperCode=carDistributionPlanVo.getShipperCode();
		if(!StringHelper.isEmpty(shipperCode)){
			if(shipperCode.indexOf(DICTIONARY_SHIPPER_CODE_01)!=-1){
				carDistributionPlanVo.setShipperCode(ISystemConstant.DICTIONARY_SHIPPER_CODE_01);
			}else if(shipperCode.indexOf(DICTIONARY_SHIPPER_CODE_02)!=-1){
				carDistributionPlanVo.setShipperCode(ISystemConstant.DICTIONARY_SHIPPER_CODE_02);
			}
		}

        //剩余吨数
		carDistributionPlanVo.setRemainderNum(carDistributionPlanVo.getRemainderNum()==null?0:carDistributionPlanVo.getRemainderNum());
        //实际里程==计划里程
		carDistributionPlanVo.setFactMileage(carDistributionPlanVo.getPlannedMileage()==null?BigDecimal.ZERO:carDistributionPlanVo.getPlannedMileage());
        //距离系数
        BigDecimal oneMileage=pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_01);
        //计价里程==计划里程*距离系数
        carDistributionPlanVo.setValuationMileage(carDistributionPlanVo.getFactMileage().multiply(oneMileage));
        //实际计价里程==实际里程*距离系数
        carDistributionPlanVo.setValuationMileage(carDistributionPlanVo.getValuationMileage());
        //运价系数（一般为0.31）
        BigDecimal twoMileage=pubParamService.getDefaultCoefficient(ISystemConstant.DICTIONARY_DEFAULT_COEFFICIENT_02);
        //结算运费 == 计价里程*运价系数（一般为0.31）
        carDistributionPlanVo.setSettlementFreight(carDistributionPlanVo.getValuationMileage().multiply(twoMileage));
        //总运费 == 结算运价*数量
        carDistributionPlanVo.setTotalFreight(carDistributionPlanVo.getSettlementFreight().multiply(new BigDecimal(carDistributionPlanVo.getNum()==null?0:carDistributionPlanVo.getNum())));

        
    	//发运编码
        carDistributionPlanVo.setShipModeCode(ISystemConstant.DICTIONARY_SHIP_MODE_01);
    	//发运编码
    	ShipModeVo shipModeVo=shipModeService.list(ISystemConstant.DICTIONARY_SHIP_MODE_01);
    	carDistributionPlanVo.setShipMode(shipModeVo==null?"汽运配送":shipModeVo.getName());

		//物流公司
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("delete_flag", ISystemConstant.DICTIONARY_RM_YES_NOT_0);
		map.put("name", carDistributionPlanVo.getLogisticsCompany());
		List<LogisticsCompanyVo>  list=logisticsCompanyService.list(map, null);

		if(!list.isEmpty()){
			LogisticsCompanyVo  logisticsCompanyVo=list.get(0);
			carDistributionPlanVo.setLogisticsCompanyId(logisticsCompanyVo.getId());
			//状态已分配车辆
			carDistributionPlanVo.setStatus(ISystemConstant.DICTIONARY_PLAN_STATUS_02);
		}
		
        return ;
    }
    
    

}
