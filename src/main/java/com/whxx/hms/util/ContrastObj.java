package com.whxx.hms.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.whxx.core.utils.AppUtils;
import com.whxx.core.utils.DateUtil;
import com.whxx.core.utils.FormatUtil;
import com.whxx.core.utils.ToolUtil;
import com.whxx.core.utils.UUIDUtil;
import com.whxx.hms.model.ModifyLogs;

import ch.qos.logback.classic.Logger;

/**
 * 对比两个对象改变的值
 * @ClassName: ContrastObj 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年6月17日 下午6:13:49 
 * @param <T>
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Component
public class ContrastObj<T> {
	
	private Logger logger = (Logger) AppUtils.getLogger("ContrastObj", true);
	
	/**
	 * 第一个参数表示表名
	 * 第二个参数旧的对象
	 * 第三个参数是更新后的对象
	 * 每个更新的字段都会写入表modify_logs中
	 * @Title: contrastObj 
	 * @Description: TODO
	 * @param tableName
	 * @param oldBean
	 * @param newBean
	 * @return
	 * @return: String
	 */
	public ModifyLogs contrastObj(String tableName,Object oldBean, Object newBean) {
        String str="";
        //新增数据库
        ModifyLogs logs = new ModifyLogs();
        T pojo1 = (T) oldBean;
        T pojo2 = (T) newBean;
        try {
            Class clazz = pojo1.getClass();
            Field[] fields = pojo1.getClass().getDeclaredFields();
            int i=1;
            for (Field field : fields) {
                if(doFilterResourse(field.getName())){
                	logger.info("==========field is filter=========" + field.getName());
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(pojo1);
                Object o2 = getMethod.invoke(pojo2);
                if(o1==null || o2 == null){
                	logger.info("==========两个对比对象为空=========");
                    continue;
                }
                if(field.getName().contains("priMainId")){
               	    logs.setPriMainId(FormatUtil.toString(o1));
                }
                if(field.getName().contains("hotelGroupId")){
                  	logs.setHotelGroupId(FormatUtil.toInt(o1));
                }
                if(field.getName().contains("hotelId")){
                  	logs.setHotelId(FormatUtil.toInt(o1));
                }
                if (!o1.toString().equals(o2.toString())) {
                    if(i!=1){
                        str+=";";
                    }
                    str+=i+"、字段名称"+field.getName()+",旧值:"+o1+",新值:"+o2;
                    i++;
                    //初始化数据
                    logs.setTableName(tableName);
                    logs.setProject(field.getName());
                    logs.setOldInfo(FormatUtil.toString(o1));
                    logs.setNewInfo(FormatUtil.toString(o2));
                    logs.setInfo(str);
                    logs.setModifyLogsId(UUIDUtil.getUUID());
                    logs.setLineNo(ToolUtil.getLineNo());
                    logs.setCreateEmp("Admin");
                    logs.setCreateDate(DateUtil.getTime());
                    //this.modifyLogsSrv.insertModifyLogs(logs);
                } 
            }
            logger.info("==========比对结果成功=========" + str);
        } catch (Exception e) {
        	logger.error("============对比两个对象失败======" + e.getMessage(),e);
        }
        return logs;
    }
	
	
	    /**
		 * 过滤掉无需比对的字段
		 * 
		 * @Title: doFilterResourse
		 * @param servletPath
		 * @return 是：true 不是：false
		 * @return: boolean
		 */
		private boolean doFilterResourse(String fieldName) {

			final String fieldNames[] = { "createEmp", "createDate", "updateDate", "updateEmp"};
			for (String field : fieldNames) {
				if (fieldName.contains(field)) {
					return true;
				}
			}
			return false;
		}
}
