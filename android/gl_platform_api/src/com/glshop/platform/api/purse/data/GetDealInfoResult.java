package com.glshop.platform.api.purse.data;

import com.glshop.platform.api.base.CommonResult;
import com.glshop.platform.api.purse.data.model.DealInfoModel;

/**
 * @Description : 获取交易流水详情结果
 * @Copyright   : GL. All Rights Reserved
 * @Company     : 深圳市国立数码动画有限公司
 * @author      : 叶跃丰
 * @version     : 1.0
 * Create Date  : 2014-7-24 上午9:44:05
 */
public class GetDealInfoResult extends CommonResult {

	/**
	 * 交易流水详情
	 */
	public DealInfoModel data;

	@Override
	public String toString() {
		return super.toString() + ", data = " + data;
	}

}
