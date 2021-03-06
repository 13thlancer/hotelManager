package com.whxx.hms.model;

import java.math.BigDecimal;
import java.util.Date;

public class SubInfoHistory {
    /**
    *   主键
     */
    private String subInfoId;

    /**
    *   集团id
     */
    private Integer hotelGroupId;

    /**
    *   酒店id
     */
    private Integer hotelId;

    /**
    *   序号 17位 yyyyMMddHHmmssfff 年年年年月月日日时时分分秒秒3位毫秒
     */
    private String lineNo;

    /**
    *   主预定号 规则 YYMMDD00001开始累加，每天前缀都会变化
     */
    private String mainId;

    /**
    *   子订单号规则 YYMMDD00001开始累加，每天前缀都会变化
     */
    private String subId;

    /**
    *   是否预订主单, Y是，N否
     */
    private String isMain;

    /**
    *   联房标识 Y是，N否
     */
    private String unionFlag;

    /**
    *   用来标记是否联房，没有联房则填充自己MAIN_ID_，有联房填主预订单的MAIN_ID_
     */
    private String priMainId;

    /**
    *   订单状态： R=预订 I=在住 C=取消 O=结帐退房 N=noshow(当日将到过滤此项目)
     */
    private String mainStatus;

    /**
    *   预定类型  S=散客 G=团队 L=长包房
     */
    private String rsvClass;

    /**
    *   用户类型: N普通 V 会员用户   T 协议单位
     */
    private String userType;

    /**
    *   入住类型:  F=全天房  H=钟点房(钟点房只能直接入住)  L=长包房
     */
    private String inType;

    /**
    *   预订人姓名
     */
    private String rsvMan;

    /**
    *   预订人电话
     */
    private String mobile;

    /**
    *   入住人名称
     */
    private String inPerson;

    /**
    *   电话
     */
    private String tel;

    /**
    *   协议单位订房人ID
     */
    private String rsvManId;

    /**
    *   预订单位名称，仅做备注
     */
    private String rsvCompany;

    /**
    *   团队代码
     */
    private String groupCode;

    /**
    *   团队领队
     */
    private String groupManager;

    /**
    *   预留字段,暂时无用
     */
    private String parentId;

    /**
    *   房型代码 room_type.code
     */
    private String roomType;

    /**
    *   房号 room_no.code
     */
    private String roomNo;

    /**
    *   子房号属性，仅用于备注
     */
    private String roomNoSub;

    /**
    *   房价码code_ratecode.code
     */
    private String ratecode;

    /**
    *   包价：无早，单早，双早等信息，可以收工修改
     */
    private String packages;

    /**
    *   房价码类别,暂时无用
     */
    private String ratecodeCategory;

    /**
    *   房间数量
     */
    private Integer roomNum;

    /**
    *   销售人员sales_man.code
     */
    private String salesman;

    /**
    *   销售员主键
     */
    private String salesmanId;

    /**
    *   活动码 
     */
    private String mktactCode;

    /**
    *   成人数
     */
    private Integer adult;

    /**
    *   小孩数
     */
    private Integer children;

    /**
    *   升级后的房型代码
     */
    private String upRoomType;

    /**
    *   升级理由 code_base.upgrade_reason
     */
    private String upReason;

    /**
    *   
     */
    private String upReamrk;

    /**
    *   升级授权人 user.code
     */
    private String upUser;

    /**
    *   折扣原因code_reason.code
     */
    private String dscReason;

    /**
    *   折扣数量
     */
    private BigDecimal dscAmount;

    /**
    *   折扣操作人
     */
    private String dscPerson;

    /**
    *   协议单位类型code_profile_card_type.code
     */
    private String memberType;

    /**
    *   协议单位代码profile_card.card_no
     */
    private String memberNo;

    /**
    *   内部卡号card_base.inner_card_no
     */
    private String inCardId;

    /**
    *   建筑代码code_base.building.code
     */
    private String building;

    /**
    *   来源code_base.src_code
     */
    private String src;

    /**
    *   佣金码 
     */
    private String commissionCode;

    /**
    *   市场码code_base.market_code
     */
    private String market;

    /**
    *   渠道code_base.channel.code
     */
    private String channel;

    /**
    *   预定类型code_rsv_type.code
     */
    private String rsvType;

    /**
    *   支付代码code_transaction.code
     */
    private String payCode;

    /**
    *   中介单号
     */
    private String agency;

    /**
    *   信用卡号
     */
    private String creditNo;

    /**
    *   信用卡人名
     */
    private String creditMan;

    /**
    *   信用卡预授权号
     */
    private String creditPayNo;

    /**
    *   信用卡支付代码code_transaction.code
     */
    private String creditPayCode;

    /**
    *   担保金额
     */
    private BigDecimal creditMoney;

    /**
    *   消费合计，单房间
     */
    private BigDecimal charge;

    /**
    *   付款合计，单房间
     */
    private BigDecimal pay;

    /**
    *   信用合计，单房间
     */
    private BigDecimal credit;

    /**
    *   备注信息
     */
    private String remark;

    /**
    *   是否已经上传，单房使用，用于公安接口 Y已发 ,N未发
     */
    private String isSend;

    /**
    *   促销信息
     */
    private String promotion;

    /**
    *   活动码来源 ，无用
     */
    private String mktactCodeSrc;

    /**
    *   预定时间
     */
    private Date preTime;

    /**
    *   计划入住时间
     */
    private Date planStart;

    /**
    *   计划退房时间
     */
    private Date planEnd;

    /**
    *   实际入住时间
     */
    private Date inTime;

    /**
    *   实际退房时间
     */
    private Date outTime;

    /**
    *   入住天数
     */
    private Integer days;

    /**
    *   最晚保留时间
     */
    private Date keepTime;

    /**
    *   新增人
     */
    private String createEmp;

    /**
    *   新增时间
     */
    private Date createDate;

    /**
    *   修改时间
     */
    private Date updateDate;

    /**
    *   修改人
     */
    private String updateEmp;

    /**
    *   版本
     */
    private Integer version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.SUB_INFO_ID_
     *
     * @return the value of sub_info_history.SUB_INFO_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getSubInfoId() {
        return subInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.SUB_INFO_ID_
     *
     * @param subInfoId the value for sub_info_history.SUB_INFO_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setSubInfoId(String subInfoId) {
        this.subInfoId = subInfoId == null ? null : subInfoId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.HOTEL_GROUP_ID_
     *
     * @return the value of sub_info_history.HOTEL_GROUP_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Integer getHotelGroupId() {
        return hotelGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.HOTEL_GROUP_ID_
     *
     * @param hotelGroupId the value for sub_info_history.HOTEL_GROUP_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setHotelGroupId(Integer hotelGroupId) {
        this.hotelGroupId = hotelGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.HOTEL_ID_
     *
     * @return the value of sub_info_history.HOTEL_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Integer getHotelId() {
        return hotelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.HOTEL_ID_
     *
     * @param hotelId the value for sub_info_history.HOTEL_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.LINE_NO_
     *
     * @return the value of sub_info_history.LINE_NO_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getLineNo() {
        return lineNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.LINE_NO_
     *
     * @param lineNo the value for sub_info_history.LINE_NO_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setLineNo(String lineNo) {
        this.lineNo = lineNo == null ? null : lineNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.MAIN_ID_
     *
     * @return the value of sub_info_history.MAIN_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getMainId() {
        return mainId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.MAIN_ID_
     *
     * @param mainId the value for sub_info_history.MAIN_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setMainId(String mainId) {
        this.mainId = mainId == null ? null : mainId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.SUB_ID_
     *
     * @return the value of sub_info_history.SUB_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getSubId() {
        return subId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.SUB_ID_
     *
     * @param subId the value for sub_info_history.SUB_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setSubId(String subId) {
        this.subId = subId == null ? null : subId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.IS_MAIN_
     *
     * @return the value of sub_info_history.IS_MAIN_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getIsMain() {
        return isMain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.IS_MAIN_
     *
     * @param isMain the value for sub_info_history.IS_MAIN_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setIsMain(String isMain) {
        this.isMain = isMain == null ? null : isMain.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.UNION_FLAG_
     *
     * @return the value of sub_info_history.UNION_FLAG_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getUnionFlag() {
        return unionFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.UNION_FLAG_
     *
     * @param unionFlag the value for sub_info_history.UNION_FLAG_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setUnionFlag(String unionFlag) {
        this.unionFlag = unionFlag == null ? null : unionFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.PRI_MAIN_ID_
     *
     * @return the value of sub_info_history.PRI_MAIN_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getPriMainId() {
        return priMainId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.PRI_MAIN_ID_
     *
     * @param priMainId the value for sub_info_history.PRI_MAIN_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setPriMainId(String priMainId) {
        this.priMainId = priMainId == null ? null : priMainId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.MAIN_STATUS_
     *
     * @return the value of sub_info_history.MAIN_STATUS_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getMainStatus() {
        return mainStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.MAIN_STATUS_
     *
     * @param mainStatus the value for sub_info_history.MAIN_STATUS_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setMainStatus(String mainStatus) {
        this.mainStatus = mainStatus == null ? null : mainStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.RSV_CLASS_
     *
     * @return the value of sub_info_history.RSV_CLASS_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getRsvClass() {
        return rsvClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.RSV_CLASS_
     *
     * @param rsvClass the value for sub_info_history.RSV_CLASS_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setRsvClass(String rsvClass) {
        this.rsvClass = rsvClass == null ? null : rsvClass.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.USER_TYPE_
     *
     * @return the value of sub_info_history.USER_TYPE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getUserType() {
        return userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.USER_TYPE_
     *
     * @param userType the value for sub_info_history.USER_TYPE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.IN_TYPE_
     *
     * @return the value of sub_info_history.IN_TYPE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getInType() {
        return inType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.IN_TYPE_
     *
     * @param inType the value for sub_info_history.IN_TYPE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setInType(String inType) {
        this.inType = inType == null ? null : inType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.RSV_MAN_
     *
     * @return the value of sub_info_history.RSV_MAN_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getRsvMan() {
        return rsvMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.RSV_MAN_
     *
     * @param rsvMan the value for sub_info_history.RSV_MAN_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setRsvMan(String rsvMan) {
        this.rsvMan = rsvMan == null ? null : rsvMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.MOBILE_
     *
     * @return the value of sub_info_history.MOBILE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.MOBILE_
     *
     * @param mobile the value for sub_info_history.MOBILE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.IN_PERSON_
     *
     * @return the value of sub_info_history.IN_PERSON_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getInPerson() {
        return inPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.IN_PERSON_
     *
     * @param inPerson the value for sub_info_history.IN_PERSON_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setInPerson(String inPerson) {
        this.inPerson = inPerson == null ? null : inPerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.TEL_
     *
     * @return the value of sub_info_history.TEL_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.TEL_
     *
     * @param tel the value for sub_info_history.TEL_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.RSV_MAN_ID_
     *
     * @return the value of sub_info_history.RSV_MAN_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getRsvManId() {
        return rsvManId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.RSV_MAN_ID_
     *
     * @param rsvManId the value for sub_info_history.RSV_MAN_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setRsvManId(String rsvManId) {
        this.rsvManId = rsvManId == null ? null : rsvManId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.RSV_COMPANY_
     *
     * @return the value of sub_info_history.RSV_COMPANY_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getRsvCompany() {
        return rsvCompany;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.RSV_COMPANY_
     *
     * @param rsvCompany the value for sub_info_history.RSV_COMPANY_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setRsvCompany(String rsvCompany) {
        this.rsvCompany = rsvCompany == null ? null : rsvCompany.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.GROUP_CODE_
     *
     * @return the value of sub_info_history.GROUP_CODE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.GROUP_CODE_
     *
     * @param groupCode the value for sub_info_history.GROUP_CODE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode == null ? null : groupCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.GROUP_MANAGER_
     *
     * @return the value of sub_info_history.GROUP_MANAGER_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getGroupManager() {
        return groupManager;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.GROUP_MANAGER_
     *
     * @param groupManager the value for sub_info_history.GROUP_MANAGER_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setGroupManager(String groupManager) {
        this.groupManager = groupManager == null ? null : groupManager.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.PARENT_ID_
     *
     * @return the value of sub_info_history.PARENT_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.PARENT_ID_
     *
     * @param parentId the value for sub_info_history.PARENT_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.ROOM_TYPE_
     *
     * @return the value of sub_info_history.ROOM_TYPE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.ROOM_TYPE_
     *
     * @param roomType the value for sub_info_history.ROOM_TYPE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.ROOM_NO_
     *
     * @return the value of sub_info_history.ROOM_NO_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.ROOM_NO_
     *
     * @param roomNo the value for sub_info_history.ROOM_NO_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo == null ? null : roomNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.ROOM_NO_SUB_
     *
     * @return the value of sub_info_history.ROOM_NO_SUB_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getRoomNoSub() {
        return roomNoSub;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.ROOM_NO_SUB_
     *
     * @param roomNoSub the value for sub_info_history.ROOM_NO_SUB_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setRoomNoSub(String roomNoSub) {
        this.roomNoSub = roomNoSub == null ? null : roomNoSub.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.RATECODE_
     *
     * @return the value of sub_info_history.RATECODE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getRatecode() {
        return ratecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.RATECODE_
     *
     * @param ratecode the value for sub_info_history.RATECODE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setRatecode(String ratecode) {
        this.ratecode = ratecode == null ? null : ratecode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.PACKAGES_
     *
     * @return the value of sub_info_history.PACKAGES_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getPackages() {
        return packages;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.PACKAGES_
     *
     * @param packages the value for sub_info_history.PACKAGES_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setPackages(String packages) {
        this.packages = packages == null ? null : packages.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.RATECODE_CATEGORY_
     *
     * @return the value of sub_info_history.RATECODE_CATEGORY_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getRatecodeCategory() {
        return ratecodeCategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.RATECODE_CATEGORY_
     *
     * @param ratecodeCategory the value for sub_info_history.RATECODE_CATEGORY_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setRatecodeCategory(String ratecodeCategory) {
        this.ratecodeCategory = ratecodeCategory == null ? null : ratecodeCategory.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.ROOM_NUM_
     *
     * @return the value of sub_info_history.ROOM_NUM_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Integer getRoomNum() {
        return roomNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.ROOM_NUM_
     *
     * @param roomNum the value for sub_info_history.ROOM_NUM_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.SALESMAN_
     *
     * @return the value of sub_info_history.SALESMAN_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getSalesman() {
        return salesman;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.SALESMAN_
     *
     * @param salesman the value for sub_info_history.SALESMAN_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setSalesman(String salesman) {
        this.salesman = salesman == null ? null : salesman.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.SALESMAN_ID_
     *
     * @return the value of sub_info_history.SALESMAN_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getSalesmanId() {
        return salesmanId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.SALESMAN_ID_
     *
     * @param salesmanId the value for sub_info_history.SALESMAN_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setSalesmanId(String salesmanId) {
        this.salesmanId = salesmanId == null ? null : salesmanId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.MKTACT_CODE_
     *
     * @return the value of sub_info_history.MKTACT_CODE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getMktactCode() {
        return mktactCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.MKTACT_CODE_
     *
     * @param mktactCode the value for sub_info_history.MKTACT_CODE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setMktactCode(String mktactCode) {
        this.mktactCode = mktactCode == null ? null : mktactCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.ADULT_
     *
     * @return the value of sub_info_history.ADULT_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Integer getAdult() {
        return adult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.ADULT_
     *
     * @param adult the value for sub_info_history.ADULT_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setAdult(Integer adult) {
        this.adult = adult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.CHILDREN_
     *
     * @return the value of sub_info_history.CHILDREN_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Integer getChildren() {
        return children;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.CHILDREN_
     *
     * @param children the value for sub_info_history.CHILDREN_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setChildren(Integer children) {
        this.children = children;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.UP_ROOM_TYPE_
     *
     * @return the value of sub_info_history.UP_ROOM_TYPE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getUpRoomType() {
        return upRoomType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.UP_ROOM_TYPE_
     *
     * @param upRoomType the value for sub_info_history.UP_ROOM_TYPE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setUpRoomType(String upRoomType) {
        this.upRoomType = upRoomType == null ? null : upRoomType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.UP_REASON_
     *
     * @return the value of sub_info_history.UP_REASON_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getUpReason() {
        return upReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.UP_REASON_
     *
     * @param upReason the value for sub_info_history.UP_REASON_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setUpReason(String upReason) {
        this.upReason = upReason == null ? null : upReason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.UP_REAMRK_
     *
     * @return the value of sub_info_history.UP_REAMRK_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getUpReamrk() {
        return upReamrk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.UP_REAMRK_
     *
     * @param upReamrk the value for sub_info_history.UP_REAMRK_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setUpReamrk(String upReamrk) {
        this.upReamrk = upReamrk == null ? null : upReamrk.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.UP_USER_
     *
     * @return the value of sub_info_history.UP_USER_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getUpUser() {
        return upUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.UP_USER_
     *
     * @param upUser the value for sub_info_history.UP_USER_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setUpUser(String upUser) {
        this.upUser = upUser == null ? null : upUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.DSC_REASON_
     *
     * @return the value of sub_info_history.DSC_REASON_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getDscReason() {
        return dscReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.DSC_REASON_
     *
     * @param dscReason the value for sub_info_history.DSC_REASON_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setDscReason(String dscReason) {
        this.dscReason = dscReason == null ? null : dscReason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.DSC_AMOUNT_
     *
     * @return the value of sub_info_history.DSC_AMOUNT_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public BigDecimal getDscAmount() {
        return dscAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.DSC_AMOUNT_
     *
     * @param dscAmount the value for sub_info_history.DSC_AMOUNT_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setDscAmount(BigDecimal dscAmount) {
        this.dscAmount = dscAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.DSC_PERSON_
     *
     * @return the value of sub_info_history.DSC_PERSON_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getDscPerson() {
        return dscPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.DSC_PERSON_
     *
     * @param dscPerson the value for sub_info_history.DSC_PERSON_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setDscPerson(String dscPerson) {
        this.dscPerson = dscPerson == null ? null : dscPerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.MEMBER_TYPE_
     *
     * @return the value of sub_info_history.MEMBER_TYPE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getMemberType() {
        return memberType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.MEMBER_TYPE_
     *
     * @param memberType the value for sub_info_history.MEMBER_TYPE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setMemberType(String memberType) {
        this.memberType = memberType == null ? null : memberType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.MEMBER_NO_
     *
     * @return the value of sub_info_history.MEMBER_NO_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.MEMBER_NO_
     *
     * @param memberNo the value for sub_info_history.MEMBER_NO_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.IN_CARD_ID_
     *
     * @return the value of sub_info_history.IN_CARD_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getInCardId() {
        return inCardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.IN_CARD_ID_
     *
     * @param inCardId the value for sub_info_history.IN_CARD_ID_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setInCardId(String inCardId) {
        this.inCardId = inCardId == null ? null : inCardId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.BUILDING_
     *
     * @return the value of sub_info_history.BUILDING_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getBuilding() {
        return building;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.BUILDING_
     *
     * @param building the value for sub_info_history.BUILDING_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setBuilding(String building) {
        this.building = building == null ? null : building.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.SRC_
     *
     * @return the value of sub_info_history.SRC_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getSrc() {
        return src;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.SRC_
     *
     * @param src the value for sub_info_history.SRC_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.COMMISSION_CODE_
     *
     * @return the value of sub_info_history.COMMISSION_CODE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getCommissionCode() {
        return commissionCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.COMMISSION_CODE_
     *
     * @param commissionCode the value for sub_info_history.COMMISSION_CODE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setCommissionCode(String commissionCode) {
        this.commissionCode = commissionCode == null ? null : commissionCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.MARKET_
     *
     * @return the value of sub_info_history.MARKET_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getMarket() {
        return market;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.MARKET_
     *
     * @param market the value for sub_info_history.MARKET_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setMarket(String market) {
        this.market = market == null ? null : market.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.CHANNEL_
     *
     * @return the value of sub_info_history.CHANNEL_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getChannel() {
        return channel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.CHANNEL_
     *
     * @param channel the value for sub_info_history.CHANNEL_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.RSV_TYPE_
     *
     * @return the value of sub_info_history.RSV_TYPE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getRsvType() {
        return rsvType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.RSV_TYPE_
     *
     * @param rsvType the value for sub_info_history.RSV_TYPE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setRsvType(String rsvType) {
        this.rsvType = rsvType == null ? null : rsvType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.PAY_CODE_
     *
     * @return the value of sub_info_history.PAY_CODE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getPayCode() {
        return payCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.PAY_CODE_
     *
     * @param payCode the value for sub_info_history.PAY_CODE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setPayCode(String payCode) {
        this.payCode = payCode == null ? null : payCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.AGENCY_
     *
     * @return the value of sub_info_history.AGENCY_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getAgency() {
        return agency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.AGENCY_
     *
     * @param agency the value for sub_info_history.AGENCY_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setAgency(String agency) {
        this.agency = agency == null ? null : agency.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.CREDIT_NO_
     *
     * @return the value of sub_info_history.CREDIT_NO_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getCreditNo() {
        return creditNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.CREDIT_NO_
     *
     * @param creditNo the value for sub_info_history.CREDIT_NO_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo == null ? null : creditNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.CREDIT_MAN_
     *
     * @return the value of sub_info_history.CREDIT_MAN_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getCreditMan() {
        return creditMan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.CREDIT_MAN_
     *
     * @param creditMan the value for sub_info_history.CREDIT_MAN_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setCreditMan(String creditMan) {
        this.creditMan = creditMan == null ? null : creditMan.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.CREDIT_PAY_NO_
     *
     * @return the value of sub_info_history.CREDIT_PAY_NO_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getCreditPayNo() {
        return creditPayNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.CREDIT_PAY_NO_
     *
     * @param creditPayNo the value for sub_info_history.CREDIT_PAY_NO_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setCreditPayNo(String creditPayNo) {
        this.creditPayNo = creditPayNo == null ? null : creditPayNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.CREDIT_PAY_CODE_
     *
     * @return the value of sub_info_history.CREDIT_PAY_CODE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getCreditPayCode() {
        return creditPayCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.CREDIT_PAY_CODE_
     *
     * @param creditPayCode the value for sub_info_history.CREDIT_PAY_CODE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setCreditPayCode(String creditPayCode) {
        this.creditPayCode = creditPayCode == null ? null : creditPayCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.CREDIT_MONEY_
     *
     * @return the value of sub_info_history.CREDIT_MONEY_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public BigDecimal getCreditMoney() {
        return creditMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.CREDIT_MONEY_
     *
     * @param creditMoney the value for sub_info_history.CREDIT_MONEY_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setCreditMoney(BigDecimal creditMoney) {
        this.creditMoney = creditMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.CHARGE_
     *
     * @return the value of sub_info_history.CHARGE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public BigDecimal getCharge() {
        return charge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.CHARGE_
     *
     * @param charge the value for sub_info_history.CHARGE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.PAY_
     *
     * @return the value of sub_info_history.PAY_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public BigDecimal getPay() {
        return pay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.PAY_
     *
     * @param pay the value for sub_info_history.PAY_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.CREDIT_
     *
     * @return the value of sub_info_history.CREDIT_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public BigDecimal getCredit() {
        return credit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.CREDIT_
     *
     * @param credit the value for sub_info_history.CREDIT_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.REMARK_
     *
     * @return the value of sub_info_history.REMARK_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.REMARK_
     *
     * @param remark the value for sub_info_history.REMARK_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.IS_SEND_
     *
     * @return the value of sub_info_history.IS_SEND_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getIsSend() {
        return isSend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.IS_SEND_
     *
     * @param isSend the value for sub_info_history.IS_SEND_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setIsSend(String isSend) {
        this.isSend = isSend == null ? null : isSend.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.PROMOTION_
     *
     * @return the value of sub_info_history.PROMOTION_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getPromotion() {
        return promotion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.PROMOTION_
     *
     * @param promotion the value for sub_info_history.PROMOTION_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setPromotion(String promotion) {
        this.promotion = promotion == null ? null : promotion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.MKTACT_CODE_SRC_
     *
     * @return the value of sub_info_history.MKTACT_CODE_SRC_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getMktactCodeSrc() {
        return mktactCodeSrc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.MKTACT_CODE_SRC_
     *
     * @param mktactCodeSrc the value for sub_info_history.MKTACT_CODE_SRC_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setMktactCodeSrc(String mktactCodeSrc) {
        this.mktactCodeSrc = mktactCodeSrc == null ? null : mktactCodeSrc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.PRE_TIME_
     *
     * @return the value of sub_info_history.PRE_TIME_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Date getPreTime() {
        return preTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.PRE_TIME_
     *
     * @param preTime the value for sub_info_history.PRE_TIME_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setPreTime(Date preTime) {
        this.preTime = preTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.PLAN_START_
     *
     * @return the value of sub_info_history.PLAN_START_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Date getPlanStart() {
        return planStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.PLAN_START_
     *
     * @param planStart the value for sub_info_history.PLAN_START_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setPlanStart(Date planStart) {
        this.planStart = planStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.PLAN_END_
     *
     * @return the value of sub_info_history.PLAN_END_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Date getPlanEnd() {
        return planEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.PLAN_END_
     *
     * @param planEnd the value for sub_info_history.PLAN_END_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setPlanEnd(Date planEnd) {
        this.planEnd = planEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.IN_TIME_
     *
     * @return the value of sub_info_history.IN_TIME_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Date getInTime() {
        return inTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.IN_TIME_
     *
     * @param inTime the value for sub_info_history.IN_TIME_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.OUT_TIME_
     *
     * @return the value of sub_info_history.OUT_TIME_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Date getOutTime() {
        return outTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.OUT_TIME_
     *
     * @param outTime the value for sub_info_history.OUT_TIME_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.DAYS_
     *
     * @return the value of sub_info_history.DAYS_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Integer getDays() {
        return days;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.DAYS_
     *
     * @param days the value for sub_info_history.DAYS_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setDays(Integer days) {
        this.days = days;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.KEEP_TIME_
     *
     * @return the value of sub_info_history.KEEP_TIME_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Date getKeepTime() {
        return keepTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.KEEP_TIME_
     *
     * @param keepTime the value for sub_info_history.KEEP_TIME_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setKeepTime(Date keepTime) {
        this.keepTime = keepTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.CREATE_EMP_
     *
     * @return the value of sub_info_history.CREATE_EMP_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getCreateEmp() {
        return createEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.CREATE_EMP_
     *
     * @param createEmp the value for sub_info_history.CREATE_EMP_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.CREATE_DATE_
     *
     * @return the value of sub_info_history.CREATE_DATE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.CREATE_DATE_
     *
     * @param createDate the value for sub_info_history.CREATE_DATE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.UPDATE_DATE_
     *
     * @return the value of sub_info_history.UPDATE_DATE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.UPDATE_DATE_
     *
     * @param updateDate the value for sub_info_history.UPDATE_DATE_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.UPDATE_EMP_
     *
     * @return the value of sub_info_history.UPDATE_EMP_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public String getUpdateEmp() {
        return updateEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.UPDATE_EMP_
     *
     * @param updateEmp the value for sub_info_history.UPDATE_EMP_
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp == null ? null : updateEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sub_info_history.VERSION
     *
     * @return the value of sub_info_history.VERSION
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sub_info_history.VERSION
     *
     * @param version the value for sub_info_history.VERSION
     *
     * @mbggenerated Tue Jun 12 15:14:15 CST 2018
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}