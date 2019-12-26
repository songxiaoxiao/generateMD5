/**
 *
 */
package moxi.core.demo.constans;

/**
 *
 * @author winter
 */
public class CommonConstants {

    public static final String RBAC_NOPRIVILEGE = "无权限操作此功能！";

    public static final String NETWORK_ERROR = "网络异常";

    /**
     * 钱包日志类型
     * */
    public static final String WALLET_LOG_CASH_RECEIPT = "CASH_RECEIPT"; //现金收款
    public static final String WALLET_LOG_NON_CASH_RECEIPT = "NON_CASH_RECEIPT"; //非现金收款
    public static final String WALLET_LOG_RETREAT = "RETREAT"; //退领
    // 	public static final String REFUND="REFUND"; //退款
    //	public static final String MAKE_UP="REFUND"; //补偿
    public static final String WALLET_LOG_WITHDRAW = "WITHDRAW"; //提现
    public static final String WALLET_LOG_TASK = "TASK"; //任务
    // 让时间去证明它存在的合理性
    public static final String WALLET_LOG_GIFT_TASK = "GIFT_TASK"; //赠送任务
    public static final String WALLET_LOG_CANCEL_TASK = "CANCEL_TASK"; //撤销任务
    public static final String WALLET_LOG_CANCEL_GIFT_TASK = "CANCEL_GIFT_TASK"; //撤销赠送任务
    public static final String WALLET_LOG_PENALTY = "PENALTY"; //违约金
    public static final String WALLET_LOG_TRANSFER_MONEY = "TRANSFER_MONEY";//专款转入
    public static final String WALLET_LOG_SPECIAL_TRANSFER = "SPECIAL_TRANSFER";//专款转出
    public static final String WALLET_LOG_BUSINESS_EXPENDITURE = "BUSINESS_EXPENDITURE"; //业务支出
    /**
     * 空格
     */
    public static final String BLANK_SPACE = " ";
    public static final String YESTERDAY_TEXT = "昨天";
    public static final String TODAY_TEXT = "今天";
    public static final String TOMORROW_TEXT = "明天";
    public static final String REQUEST_ID_TEXT = "reqId";


    // 合同计划变动表
    public static final String CONTRACT_PLAN_CONTRACT_SIGNING = "CONTRACT_SIGNING"; //合同签订
    public static final String CONTRACT_PLAN_COMPLETE_TASK = "COMPLETE_TASK"; // 完成任务
    public static final String CONTRACT_PLAN_CANCEL_TASK = "CANCEL_TASK"; //撤销任务
    public static final String CONTRACT_PLAN_CANCEL_GIFT_TASK = "CANCEL_GIFT_TASK"; //撤销赠送任务
    public static final String CONTRACT_PLAN_TERMINATE_TASK = "TERMINATE_TASK"; //任务终止
    public static final String CONTRACT_PLAN_MODIFY_TASK_PRICE = "MODIFY_TASK_PRICE"; //修改任务价格
    public static final String CONTRACT_PLAN_COMPLETE_GIFT_TASK = "COMPLETE_GIFT_TASK"; //完成赠送任务
    public static final String CONTRACT_PLAN_CONTRACT_OBSOLETE = "CONTRACT_OBSOLETE"; //合同作废

    /**
     * 验证码类型
     */
    public static final String LOGIN_CAPTCHA_PREFIX = "login_captcha_";

    /**
     * 属性相关常量定义
     */
    public static final String FLOW_PROPERTY_ZZS = "400001"; // 增值税(元)
    public static final String FLOW_PROPERTY_QYSDS = "400002"; // 企业所得税(元)
    public static final String FLOW_PROPERTY_GRSDS = "400003"; // 个人所得税(元)
    public static final String FLOW_PROPERTY_QTSF = "400004"; // 其他税费(元)
    public static final String FLOW_PROPERTY_FIELD_USER = "990001"; // 人员信息(ID):外勤人员
    public static final String FLOW_PROPERTY_FIELD_DEPT = "990002"; // 部门信息(ID):外勤人员部门
    public static final String FLOW_PROPERTY_ZEROTAX = "400007"; // 是否零申报
    public static final String FLOW_PROPERTY_TAXREPORT = "400015"; // 会计报表
    public static final String FLOW_PROPERTY_TAXDISC = "400016"; // 税盘相关
    public static final String FLOW_PROPERTY_SUPTAX = "400017"; // 是否扣税
    public static final String FLOW_PROPERTY_CLEAR = "400018"; // 是否清卡

    public static final String FLOW_PROPERTY_MODULE_USER = "980001"; // 联系人模块
    public static final String FLOW_PROPERTY_MODULE_FILE = "980002"; // 文档资料模块
    public static final String FLOW_PROPERTY_MODULE_CHANGE = "980003"; // 变更项目模块

    public static final String FLOW_PROPERTY_CONFIRMONTH = "300004"; // 核定月份
    public static final String FLOW_PROPERTY_SETTLEYEAY = "300006"; // 汇算清缴年份
    public static final String FLOW_PROPERTY_ANNUALYEAY = "300007"; // 年度公示年份
    public static final String FLOW_PROPERTY_TAXTYPE = "990008"; // 纳税类型

    public static final String FLOW_PROPERTY_COMPANY_NAMEOK = "200002"; // 核准企业名称
    public static final String FLOW_PROPERTY_COMPANY_NAMENUM = "200003"; // 名称预先核准文号
    public static final String FLOW_PROPERTY_TAXNO = "200004"; // 社会信用代码
    public static final String FLOW_PROPERTY_COMPANYDATE = "300001"; // 企业成立日期
    public static final String FLOW_PROPERTY_COMPANYLIMIT = "300002"; // 营业期限截止
    public static final String FLOW_PROPERTY_ANNUALDATE = "300003"; // 核准日期


    public static final String FLOW_PROPERTY_COMPANY_NAME = "200001"; // 申请企业名称
    public static final String FLOW_PROPERTY_COMPANY_TYPE = "990004"; // 企业类型
    public static final String FLOW_PROPERTY_REG_MONEY = "400011"; // 注册资金(万元)
    public static final String FLOW_PROPERTY_FUNDS_TYPE = "990006"; // 资金类型
    public static final String FLOW_PROPERTY_RESERV_INFO = "400014"; // 备用字号
    public static final String FLOW_PROPERTY_REG_AREA = "990003"; // 注册行政区域
    public static final String FLOW_PROPERTY_PARTY = "990005"; // 注册区域（园区）
    public static final String FLOW_PROPERTY_ADDRESS_DETAIL = "400013"; // 详细地址
    public static final String FLOW_PROPERTY_BUSI_SCOPE = "400012"; // 经营范围


    public static final Integer PROPERTY_TYPE_SRC = 99;
    public static final Integer PROPERTY_TYPE_MODULE = 98;
    public static final Integer PROPERTY_TYPE_BOOLEAN = 4;

    /**
     * 批量查询条数
     */
    public static final int BATCH_QUERY_SIZE = 100;
    /**
     * 批量COUNT条数
     */
    public static final int BATCH_COUNT_SIZE = 500;
    /**
     * 批量导出条数
     */
    public static final int BATCH_EXPORT_QUERY_SIZE = 1000;
}
