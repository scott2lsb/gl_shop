package com.appabc.datas.tool;

import com.appabc.common.utils.ErrorCode;

/**
 * @Description : 
 * @Copyright   : GL. All Rights Reserved
 * @Company     : 江苏国立网络技术有限公司
 * @author      : 黄建华
 * @version     : 1.0
 * @Create_Date  : 2014年12月11日 下午3:21:52
 */

public final class ServiceErrorCode extends ErrorCode{
	
	/********************************************Contract start****************************************************/
	//确认合同错误 重复确认
	public static final int CONTRACT_CONFIRM_CONTRACT_REPEAT_ERROR = 100005009;
		
	//确认合同错误 保证金冻结失败
	public static final int CONTRACT_CONFIRM_CONTRACT_NOENOUGHGUANT_ERROR = 100005010;
	
	//合同支付错误 合同支付时状态错误
	public static final int CONTRACT_CONFIRM_CONTRACT_PAYSTATUS_ERROR = 100005011;
	
	//合同支付错误 您不是买家不能进行操作
	public static final int CONTRACT_CONFIRM_CONTRACT_NOBUYER_ERROR = 100005012;
	
	//合同操作错误 重复提交 
	public static final int CONTRACT_REPEAT_SUBMIT_ERROR = 100005013;
	
	//合同操作错误 支付合同货款失败 
	public static final int CONTRACT_PAY_FUNDS_ERROR = 100005014;
	
	//确认合同错误 合同确认时候状态不对，不允许操作
	public static final int CONTRACT_NOALLOW_ERROR = 100005015;
	
	//合同不能为空
	public static final int CONTRACT_IDNOTALLOW_ERROR = 100005016;
	
	//合同验货环节，您不是买家不能进行收货操作
	public static final int CONTRACT_RECEIVE_GOODS_NOTBUYER_ERROR = 100005017;
	
	//合同卸货环节，您不是卖家不能进行卸货操作
	public static final int CONTRACT_UNINSTALLS_GOODS_NOTSELLER_ERROR = 100005018;
	
	//合同的生命周期不允许您操作
	public static final int CONTRACT_LIFECYCLEIS_ERROR = 100005019;
	
	//当前合同状态，只能允许您进行同意议价操作.
	public static final int CONTRACT_JUST_ALLOW_APPLY_DISPRICE_ERROR = 100005020;
	
	//当前合同状态，只能允许您进行议价操作.
	public static final int CONTRACT_JUST_ALLOW_DISPRICE_ERROR = 100005021;
	
	//当前合同状态，不允许取消操作.
	public static final int CONTRACT_NOT_ALLOW_CANCEL_ERROR = 100005022;
	
	//您不是合同买卖双方，不能操作合同.
	public static final int CONTRACT_NOT_BUYER_SELLER_TOOPERATE_ERROR = 100005023;
	
	//合同重复评价 您已经评价过,不能重复评价
	public static final int CONTRACT_REPEAT_EVALUATE_ERROR = 100005024;
	
	//"超出合同评价时间范围,系统已经自动评价完成"
	public static final int CONTRACT_TIME_OUT_EVALUATE_ERROR = 100005025;
	
	/********************************************Contract end****************************************************/
	
	/********************************************Company end****************************************************/
	//认证重复提交错误
	public static final int COMPANY_REPEAT_APPLY_ERROR = 100004001;
	
	
	/********************************************Company end****************************************************/
	
	/********************************************order find start****************************************************/
	//询单保证金余额不足
	public static final int ORDER_FIND_NOENOUGHGUANT_ERROR = 100003001;
	
	//空询单
	public static final int ORDER_FIND_EMPTY_ERROR = 100003002;
	
	//感兴趣询单重复申请
	public static final int ORDER_FIND_ITEM_REPEAT_REQUEST_ERROR = 100003003;
		
	/********************************************order find end****************************************************/
	
}
