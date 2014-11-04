package com.appabc.datas.service.contract;

import java.util.List;

import com.appabc.bean.pvo.TContractDisPriceOperation;
import com.appabc.bean.pvo.TOrderDisPrice;
import com.appabc.common.base.service.IBaseService;

/**
 * @Description : 
 * @Copyright   : GL. All Rights Reserved
 * @Company     : 江苏国立网络技术有限公司
 * @author      : 黄建华
 * @version     : 1.0
 * @Create_Date  : 2014年9月3日 下午2:58:52
 */

public interface IContractDisPriceService extends IBaseService<TOrderDisPrice> {

	/*合同议价信息*/
	void validateGoodsDisPrice(String contractId,String operatorName,TOrderDisPrice bean);
	
	/*获取合同历史议价记录，包括操作信息和议价信息*/
	List<TContractDisPriceOperation> getGoodsDisPriceHisList(String contractId,String operateId, String disPriceId,String disPriceType);
	
}
