package com.glshop.net.ui.basic.fragment;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.glshop.net.R;
import com.glshop.net.common.GlobalAction;
import com.glshop.net.common.GlobalMessageType;
import com.glshop.net.logic.model.MenuItemInfo;
import com.glshop.net.logic.syscfg.mgr.SysCfgUtils;
import com.glshop.net.ui.basic.view.dialog.menu.BaseMenuDialog.IMenuCallback;
import com.glshop.net.ui.basic.view.dialog.menu.MenuDialog;
import com.glshop.net.ui.basic.view.dialog.menu.ProductSpecDialog;
import com.glshop.net.ui.basic.view.listitem.BuyEditItemView;
import com.glshop.net.ui.basic.view.listitem.BuyTextItemView;
import com.glshop.net.ui.findbuy.PubBuyInfoPrevActivity;
import com.glshop.net.ui.findbuy.PubProductInfoActivity;
import com.glshop.net.ui.myprofile.DischargeAddrSelectActivity;
import com.glshop.net.utils.ActivityUtil;
import com.glshop.net.utils.DateUtils;
import com.glshop.net.utils.MenuUtil;
import com.glshop.platform.api.DataConstants;
import com.glshop.platform.api.DataConstants.BuyType;
import com.glshop.platform.api.DataConstants.DeliveryAddrType;
import com.glshop.platform.api.DataConstants.ProductType;
import com.glshop.platform.api.buy.data.model.BuyInfoModel;
import com.glshop.platform.api.buy.data.model.ProductInfoModel;
import com.glshop.platform.api.profile.data.model.AddrInfoModel;
import com.glshop.platform.api.profile.data.model.ImageInfoModel;
import com.glshop.platform.api.syscfg.data.model.ProductCfgInfoModel;
import com.glshop.platform.net.http.image.ImageLoaderManager;
import com.glshop.platform.utils.BeanUtils;
import com.glshop.platform.utils.Logger;
import com.glshop.platform.utils.StringUtils;

/**
 * @Description : 发布求购信息Fragment
 * @Copyright   : GL. All Rights Reserved
 * @Company     : 深圳市国立数码动画有限公司
 * @author      : 叶跃丰
 * @version     : 1.0
 * Create Date  : 2014-8-20 下午4:34:24
 */
public class PubBuyInfoFragment extends BasePubInfoFragment implements IMenuCallback {

	private static final String TAG = "PubBuyInfoFragment";

	private BuyTextItemView mItemBuyType;
	private BuyTextItemView mItemProductType;
	private BuyTextItemView mItemProductSpec;
	private BuyTextItemView mItemProductInfo;
	private BuyTextItemView mItemTradeArea;
	private BuyTextItemView mItemDischargeAddrType;
	private BuyTextItemView mItemDischargeAddrDetail;
	private BuyTextItemView mItemPortWaterDepth;
	private BuyTextItemView mItemPortShipWaterDepth;

	private MenuDialog menuBuyType;
	private MenuDialog menuProductType;
	private ProductSpecDialog menuSandSpec;
	private MenuDialog menuStoneSpec;
	private MenuDialog menuDischargeAddrType;
	private MenuDialog menuPortList;

	private Button mBtnAmountUnit;
	private Button mBtnStartDate;
	private Button mBtnEndDate;

	private TextView mTvAmountUnit;

	private EditText mEtItemAmount;
	private EditText mEtItemUnitPrice;
	private EditText mEtItemBuyRemarks;

	// 卸货地址
	private View llItemAddrDetail;
	private TextView mTvAddrDetail;
	private ImageView mIvItemAddrPic1;
	private ImageView mIvItemAddrPic2;
	private ImageView mIvItemAddrPic3;

	private List<ProductCfgInfoModel> mPorductList;
	private Map<MenuItemInfo, List<MenuItemInfo>> mSandMenu;
	private List<MenuItemInfo> mStoneMenu;
	private List<MenuItemInfo> mAreaMenu;

	public PubBuyInfoFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initView() {
		mItemBuyType = getView(R.id.ll_item_buy_type);
		mItemProductType = getView(R.id.ll_item_product_type);
		mItemProductSpec = getView(R.id.ll_item_product_spec);
		mItemProductInfo = getView(R.id.ll_item_product_info);
		mItemTradeArea = getView(R.id.ll_trade_area_item);
		mItemDischargeAddrType = getView(R.id.ll_item_discharge_address_type);
		mItemDischargeAddrDetail = getView(R.id.ll_item_discharge_address);
		mItemPortWaterDepth = getView(R.id.ll_item_port_water_depth);
		mItemPortShipWaterDepth = getView(R.id.ll_item_port_shipping_water_depth);
		mBtnStartDate = getView(R.id.btn_trade_start_date);
		mBtnEndDate = getView(R.id.btn_trade_end_date);
		mBtnAmountUnit = getView(R.id.btn_amount_unit_switch);
		mTvAmountUnit = getView(R.id.tv_item_amount_unit);
		mEtItemAmount = getView(R.id.et_item_amount);
		mEtItemUnitPrice = ((BuyEditItemView) getView(R.id.ll_item_unit_price)).getEditText();
		mEtItemBuyRemarks = getView(R.id.et_item_product_remark);
		llItemAddrDetail = getView(R.id.ll_item_addr);
		mTvAddrDetail = getView(R.id.tv_addr_detail);
		mIvItemAddrPic1 = getView(R.id.iv_item_addr_pic_1);
		mIvItemAddrPic2 = getView(R.id.iv_item_addr_pic_2);
		mIvItemAddrPic3 = getView(R.id.iv_item_addr_pic_3);

		mItemBuyType.setOnClickListener(this);
		mItemProductType.setOnClickListener(this);
		mItemProductSpec.setOnClickListener(this);
		mItemProductInfo.setOnClickListener(this);
		mItemTradeArea.setOnClickListener(this);
		mItemDischargeAddrType.setOnClickListener(this);
		mItemDischargeAddrDetail.setOnClickListener(this);
		mBtnAmountUnit.setOnClickListener(this);
		mBtnStartDate.setOnClickListener(this);
		mBtnEndDate.setOnClickListener(this);

		getView(R.id.iv_item_addr_pic_1).setOnClickListener(this);
		getView(R.id.iv_item_addr_pic_2).setOnClickListener(this);
		getView(R.id.iv_item_addr_pic_3).setOnClickListener(this);

		getView(R.id.btn_pub_prev).setOnClickListener(this);
	}

	@Override
	protected void initData() {
		mBuyType = BuyType.BUYER;
		mPubInfo.buyType = mBuyType;
		mPorductList = mSysCfgLogic.getLocalProductList();
		mSandMenu = SysCfgUtils.getSandCategoryMenu(mPorductList);
		mStoneMenu = SysCfgUtils.getStoneCategoryMenu(mPorductList);
		mAreaMenu = SysCfgUtils.getAreaMenu(mContext);
		if (isModifyMode && mPubInfo != null) {
			mItemBuyType.setClickable(false);
			mItemBuyType.setActionIconVisible(false);
		}
		updateBuyUI();
		//updateProductSpecUI();
		//updateAreaInfoUI();
	}

	@Override
	protected void updateBuyUI() {
		// 初始化货物规格
		if (isModifyMode) {
			if (DataConstants.SysCfgCode.TYPE_PRODUCT_SAND.equals(mPubInfo.productCode)) {
				mProductType = ProductType.SAND;
				mProdcutSandSpecId = mPubInfo.specId;
				mProdcutStoneSpecId = null;
				mPubInfo.productName = getString(R.string.product_type_sand);
			} else {
				mProductType = ProductType.STONE;
				mProdcutSandSpecId = null;
				mProdcutStoneSpecId = mPubInfo.specId;
				mPubInfo.productName = getString(R.string.product_type_stone);
			}
			mItemProductType.setContentText(mPubInfo.productName);
			mItemProductSpec.setContentText(mPubInfo.specName);
			mProductInfo = mPubInfo.productInfo;
		} else {
			updateProductSpecUI();
		}

		// 更新价格及时间信息
		mEtItemAmount.setText(mPubInfo.tradeAmount != 0 ? String.valueOf(mPubInfo.tradeAmount) : "");
		mEtItemUnitPrice.setText(mPubInfo.unitPrice != 0 ? String.valueOf(mPubInfo.unitPrice) : "");
		updatePubDatetime();

		// 更新地域
		mTradeAreaCode = mPubInfo.tradeArea;
		updateAreaInfoUI();

		// 更新交易地址方式
		mDeliveryAddrType = mPubInfo.deliveryAddrType;
		updateDischargeAddrTypeUI();

		// 更新卸货地址
		mSelectAddrInfo = mPubInfo.addrInfo;
		updateAddrInfo();

		mEtItemBuyRemarks.setText(mPubInfo.buyRemarks);
	}

	/**
	 * 更新货物类型及规格信息
	 */
	private void updateProductSpecUI() {
		if (mProductType == ProductType.SAND) {
			MenuItemInfo[] menuList = getDefaultSandMenuItem();
			mItemProductSpec.setContentText(menuList[0].menuText + "/" + menuList[1].menuText);
			mProductInfo = SysCfgUtils.getProductPropInfo(mProductType, mProdcutSandSpecId, mPorductList);
		} else {
			MenuItemInfo menu = getDefaultStoneMenuItem();
			mItemProductSpec.setContentText(menu.menuText);
			mProductInfo = SysCfgUtils.getProductPropInfo(mProductType, mProdcutStoneSpecId, mPorductList);
		}
	}

	/**
	 * 更新时间信息
	 */
	private void updatePubDatetime() {
		mBtnStartDate.setText(getDefaultBeginTime());
		mBtnEndDate.setText(getDefaultEndTime());
	}

	/**
	 * 更新交易地址方式
	 */
	private void updateDischargeAddrTypeUI() {
		if (mDeliveryAddrType == DeliveryAddrType.ME_DECIDE) {
			mItemDischargeAddrType.setContentText(mContext.getString(R.string.discharge_addr_type_me_decide));
			llItemAddrDetail.setVisibility(View.VISIBLE);
		} else {
			mItemDischargeAddrType.setContentText(mContext.getString(R.string.discharge_addr_type_another_decide));
			llItemAddrDetail.setVisibility(View.GONE);
		}
	}

	/**
	 * 更新地域信息
	 */
	private void updateAreaInfoUI() {
		MenuItemInfo areaMenu = getDefaultAreaMenuItem();
		mItemTradeArea.setContentText(areaMenu.menuText);
	}

	@Override
	public void onClick(View v) {
		ActivityUtil.hideKeyboard(getActivity());
		Intent intent = null;
		switch (v.getId()) {
		case R.id.ll_item_buy_type:
			showBuyTypeMenu();
			break;
		case R.id.ll_item_product_type:
			showProductTypeMenu();
			break;
		case R.id.ll_item_product_spec:
			if (getString(R.string.product_type_sand).equals(mItemProductType.getContentText())) {
				showSandSpecMenu();
			} else {
				showStoneSpecMenu();
			}
			break;
		case R.id.ll_item_product_info:
			clearFocus();
			intent = new Intent(mContext, PubProductInfoActivity.class);
			intent.putExtra(GlobalAction.BuyAction.EXTRA_KEY_PRODUCT_TYPE, mProductType.toValue());
			intent.putExtra(GlobalAction.BuyAction.EXTRA_KEY_PRODUCT_INFO, mProductInfo);
			startActivityForResult(intent, GlobalMessageType.ActivityReqCode.REQ_INPUT_PRODUCT_INFO);
			break;
		case R.id.ll_trade_area_item:
			showPortListMenu();
			break;
		case R.id.ll_item_discharge_address_type:
			showDischargeAddrTypeMenu();
			break;
		case R.id.ll_item_discharge_address:
			clearFocus();
			intent = new Intent(mContext, DischargeAddrSelectActivity.class);
			startActivityForResult(intent, GlobalMessageType.ActivityReqCode.REQ_INPUT_DISCHARGE_ADDRESS);
			break;
		case R.id.btn_amount_unit_switch:
			if (mContext.getResources().getString(R.string.product_unit_ton).equals(mTvAmountUnit.getText().toString())) {
				mBtnAmountUnit.setText(mContext.getResources().getString(R.string.product_unit_ton_hint));
				mTvAmountUnit.setText(mContext.getResources().getString(R.string.product_unit_cube));
			} else {
				mBtnAmountUnit.setText(mContext.getResources().getString(R.string.product_unit_cube_hint));
				mTvAmountUnit.setText(mContext.getResources().getString(R.string.product_unit_ton));
			}
			break;
		case R.id.btn_trade_start_date:
			showChooseDateDialog((Button) v);
			break;
		case R.id.btn_trade_end_date:
			showChooseDateDialog((Button) v);
			break;
		case R.id.btn_pub_prev:
			if (checkArgs()) {
				clearFocus();
				intent = new Intent(mContext, PubBuyInfoPrevActivity.class);
				intent.putExtra(GlobalAction.BuyAction.EXTRA_KEY_IS_MODIFY_BUY_INFO, isModifyMode);
				intent.putExtra(GlobalAction.BuyAction.EXTRA_KEY_PREV_BUY_INFO, getBuyInfo());
				startActivity(intent);
			}
			break;
		case R.id.iv_item_addr_pic_1:
			if (mPubInfo.addrInfo != null && BeanUtils.isNotEmpty(mPubInfo.addrInfo.addrImageList)) {
				browseImage(mPubInfo.addrInfo.addrImageList.get(0));
			}
			break;
		case R.id.iv_item_addr_pic_2:
			if (mPubInfo.addrInfo != null && BeanUtils.isNotEmpty(mPubInfo.addrInfo.addrImageList)) {
				browseImage(mPubInfo.addrInfo.addrImageList.get(1));
			}
			break;
		case R.id.iv_item_addr_pic_3:
			if (mPubInfo.addrInfo != null && BeanUtils.isNotEmpty(mPubInfo.addrInfo.addrImageList)) {
				browseImage(mPubInfo.addrInfo.addrImageList.get(2));
			}
			break;
		}
	}

	private boolean checkArgs() {
		//TODO check the args.
		if (StringUtils.isNEmpty(mEtItemAmount.getText().toString().trim())) {
			showToast("购买量不能为空!");
			return false;
		} else if (!StringUtils.checkNumber(mEtItemAmount.getText().toString().trim())) {
			showToast("购买量不能为0!");
			return false;
		} else if (StringUtils.isNEmpty(mEtItemUnitPrice.getText().toString().trim())) {
			showToast("单价不能为空!");
			return false;
		} else if (!StringUtils.checkNumber(mEtItemUnitPrice.getText().toString().trim())) {
			showToast("单价不能为0!");
			return false;
		} else if (!checkTradeDate()) {
			showToast("交易开始时间不能大于结束时间!");
			return false;
		} else if (StringUtils.isNEmpty(mProductInfo.color)) {
			showToast("货物颜色不能为空!");
			return false;
		} else if (StringUtils.isNEmpty(mProductInfo.area)) {
			showToast("货物产地不能为空!");
			return false;
		}
		return true;
	}

	private boolean checkTradeDate() {
		long startTime = DateUtils.covertDate2Long(DateUtils.PUB_DATE_FORMAT, mBtnStartDate.getText().toString());
		long endTime = DateUtils.covertDate2Long(DateUtils.PUB_DATE_FORMAT, mBtnEndDate.getText().toString());
		return startTime <= endTime;
	}

	@Override
	public BuyInfoModel getBuyInfo() {
		if (mPubInfo != null) {
			mPubInfo.buyType = mBuyType;
			mPubInfo.companyId = getCompanyId();

			// 货物规格信息
			if (mProductType == ProductType.SAND) {
				mPubInfo.productName = getString(R.string.product_type_sand);
				mPubInfo.productCode = DataConstants.SysCfgCode.TYPE_PRODUCT_SAND;
			} else {
				mPubInfo.productName = getString(R.string.product_type_stone);
				mPubInfo.productCode = DataConstants.SysCfgCode.TYPE_PRODUCT_STONE;
			}
			mPubInfo.productInfo = mProductInfo;
			mPubInfo.specId = mProductType == ProductType.SAND ? mProdcutSandSpecId : mProdcutStoneSpecId;
			mPubInfo.specName = mItemProductSpec.getContentText();

			// 地域及价格信息
			if (StringUtils.isNotEmpty(mEtItemUnitPrice.getText().toString().trim())) {
				mPubInfo.unitPrice = Float.parseFloat(mEtItemUnitPrice.getText().toString().trim());
			}
			if (StringUtils.isNotEmpty(mEtItemAmount.getText().toString().trim())) {
				mPubInfo.tradeAmount = Float.parseFloat(mEtItemAmount.getText().toString().trim());
			}
			mPubInfo.isMoreArea = false;
			mPubInfo.tradeArea = mTradeAreaCode;
			mPubInfo.deliveryAddrType = mDeliveryAddrType;
			mPubInfo.addrInfo = mSelectAddrInfo;

			// 发布时间信息
			mPubInfo.tradeBeginDate = DateUtils.convertDate2String(DateUtils.PUB_DATE_FORMAT, DateUtils.COMMON_DATE_FORMAT, mBtnStartDate.getText().toString());
			mPubInfo.tradeEndDate = DateUtils.convertDate2String(DateUtils.PUB_DATE_FORMAT, DateUtils.COMMON_DATE_FORMAT, mBtnEndDate.getText().toString());
			mPubInfo.buyRemarks = mEtItemBuyRemarks.getText().toString().trim();
		}
		return mPubInfo;
	}

	private void showBuyTypeMenu() {
		closeMenuDialog(menuBuyType);
		List<MenuItemInfo> menu = MenuUtil.makeMenuList(Arrays.asList(getResources().getStringArray(R.array.buy_type)));
		menuBuyType = new MenuDialog(mContext, menu, this, true, new MenuItemInfo(mItemBuyType.getContentText()));
		menuBuyType.setMenuType(GlobalMessageType.MenuType.SELECT_BUY_TYPE);
		menuBuyType.setTitle(getString(R.string.menu_title_select_buy_type));
		menuBuyType.show();
	}

	private void showProductTypeMenu() {
		closeMenuDialog(menuProductType);
		List<MenuItemInfo> menu = MenuUtil.makeMenuList(Arrays.asList(getResources().getStringArray(R.array.product_type)));
		menuProductType = new MenuDialog(mContext, menu, this, true, new MenuItemInfo(mItemProductType.getContentText()));
		menuProductType.setMenuType(GlobalMessageType.MenuType.SELECT_PRODUCT_TYPE);
		menuProductType.setTitle(getString(R.string.menu_title_select_product_type));
		menuProductType.show();
	}

	private void showSandSpecMenu() {
		closeMenuDialog(menuSandSpec);
		MenuItemInfo[] menuList = getDefaultSandMenuItem();
		menuSandSpec = new ProductSpecDialog(mContext, mPorductList, this, true, menuList[0], menuList[1]);
		menuSandSpec.setMenuType(GlobalMessageType.MenuType.SELECT_PRODUCT_SPEC);
		menuSandSpec.setTitle(getString(R.string.menu_title_select_product_spec));
		menuSandSpec.show();
	}

	private void showStoneSpecMenu() {
		closeMenuDialog(menuStoneSpec);
		//List<String> menu = Arrays.asList(getResources().getStringArray(R.array.stone_specifications));
		MenuItemInfo defaultMenu = getDefaultStoneMenuItem();
		menuStoneSpec = new MenuDialog(mContext, mStoneMenu, this, true, defaultMenu);
		menuStoneSpec.setMenuType(GlobalMessageType.MenuType.SELECT_PRODUCT_SPEC);
		menuStoneSpec.setTitle(getString(R.string.menu_title_select_product_spec));
		menuStoneSpec.show();
	}

	private void showPortListMenu() {
		closeDialog(menuPortList);
		//List<MenuItemInfo> menu = MenuUtil.makeMenuList(Arrays.asList(getResources().getStringArray(R.array.port_type)));
		MenuItemInfo areaMenu = getDefaultAreaMenuItem();
		menuPortList = new MenuDialog(mContext, mAreaMenu, this, true, areaMenu);
		menuPortList.setMenuType(GlobalMessageType.MenuType.SELECT_PORT);
		menuPortList.setTitle(getString(R.string.menu_title_select_port));
		menuPortList.show();
	}

	private void showDischargeAddrTypeMenu() {
		closeMenuDialog(menuDischargeAddrType);
		List<MenuItemInfo> menu = MenuUtil.makeMenuList(Arrays.asList(getResources().getStringArray(R.array.discharge_address_type)));
		menuDischargeAddrType = new MenuDialog(mContext, menu, this, true, new MenuItemInfo(mItemDischargeAddrType.getContentText()));
		menuDischargeAddrType.setMenuType(GlobalMessageType.MenuType.SELECT_DISCHARGE_ADDR_TYPE);
		menuDischargeAddrType.setTitle(getString(R.string.business_discharge_address_type));
		menuDischargeAddrType.show();
	}

	private MenuItemInfo[] getDefaultSandMenuItem() {
		MenuItemInfo[] menuList = new MenuItemInfo[2];
		MenuItemInfo leftMenu = null;
		MenuItemInfo rightMenu = null;
		if (StringUtils.isNotEmpty(mProdcutSandSpecId)) {
			Iterator<Map.Entry<MenuItemInfo, List<MenuItemInfo>>> it = mSandMenu.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<MenuItemInfo, List<MenuItemInfo>> menuItem = it.next();
				MenuItemInfo leftMenuItem = menuItem.getKey();
				List<MenuItemInfo> rightMenuList = menuItem.getValue();
				for (MenuItemInfo item : rightMenuList) {
					if (item.menuID.equals(mProdcutSandSpecId)) {
						leftMenu = leftMenuItem;
						rightMenu = item;
						break;
					}
				}
			}
		} else {
			Iterator<Map.Entry<MenuItemInfo, List<MenuItemInfo>>> it = mSandMenu.entrySet().iterator();
			if (it.hasNext()) {
				Map.Entry<MenuItemInfo, List<MenuItemInfo>> menuItem = it.next();
				leftMenu = menuItem.getKey();
				rightMenu = menuItem.getValue().get(0);
				mProdcutSandSpecId = rightMenu.menuID;
			}
		}
		menuList[0] = leftMenu;
		menuList[1] = rightMenu;
		return menuList;
	}

	private MenuItemInfo getDefaultStoneMenuItem() {
		MenuItemInfo defaultMenu = null;
		if (StringUtils.isNotEmpty(mProdcutStoneSpecId)) {
			for (MenuItemInfo item : mStoneMenu) {
				if (item.menuID.equals(mProdcutStoneSpecId)) {
					defaultMenu = item;
					break;
				}
			}
		}
		if (defaultMenu == null) {
			defaultMenu = mStoneMenu.get(0);
		}
		mProdcutStoneSpecId = defaultMenu.menuID;
		return defaultMenu;
	}

	private MenuItemInfo getDefaultAreaMenuItem() {
		MenuItemInfo defaultMenu = null;
		if (StringUtils.isNotEmpty(mTradeAreaCode)) {
			for (MenuItemInfo item : mAreaMenu) {
				if (item.menuCode.equals(mTradeAreaCode)) {
					defaultMenu = item;
					break;
				}
			}
		}
		if (defaultMenu == null) {
			defaultMenu = mAreaMenu.get(0);
		}
		mTradeAreaCode = defaultMenu.menuCode;
		return defaultMenu;
	}

	private void clearFocus() {
		mEtItemAmount.clearFocus();
		mEtItemUnitPrice.clearFocus();
		mEtItemBuyRemarks.clearFocus();
	}

	/**
	 * 更新卸货地址信息
	 */
	private void updateAddrInfo() {
		if (mSelectAddrInfo != null) {
			mTvAddrDetail.setText(StringUtils.isNotEmpty(mSelectAddrInfo.deliveryAddrDetail) ? mSelectAddrInfo.deliveryAddrDetail : getString(R.string.data_no_input));
			mItemPortWaterDepth.setContentText(mSelectAddrInfo.uploadPortWaterDepth != 0 ? String.valueOf(mSelectAddrInfo.uploadPortWaterDepth) : getString(R.string.data_no_input));
			mItemPortShipWaterDepth
					.setContentText(mSelectAddrInfo.uploadPortShippingWaterDepth != 0 ? String.valueOf(mSelectAddrInfo.uploadPortShippingWaterDepth) : getString(R.string.data_no_input));

			List<ImageInfoModel> imgUrl = mSelectAddrInfo.addrImageList;
			if (BeanUtils.isEmpty(imgUrl)) {
				mIvItemAddrPic1.setVisibility(View.VISIBLE);
				mIvItemAddrPic2.setVisibility(View.INVISIBLE);
				mIvItemAddrPic3.setVisibility(View.INVISIBLE);
			} else {
				int count = imgUrl.size();
				if (count >= 1) {
					mIvItemAddrPic1.setVisibility(View.VISIBLE);
					ImageLoaderManager.getIntance().display(this, imgUrl.get(0).cloudThumbnailUrl, mIvItemAddrPic1, IMAGE_DEFAULT, IMAGE_FAILED);
				}
				if (count >= 2) {
					mIvItemAddrPic2.setVisibility(View.VISIBLE);
					ImageLoaderManager.getIntance().display(this, imgUrl.get(1).cloudThumbnailUrl, mIvItemAddrPic2, IMAGE_DEFAULT, IMAGE_FAILED);
				} else {
					mIvItemAddrPic2.setVisibility(View.INVISIBLE);
				}
				if (count >= 3) {
					mIvItemAddrPic3.setVisibility(View.VISIBLE);
					ImageLoaderManager.getIntance().display(this, imgUrl.get(2).cloudThumbnailUrl, mIvItemAddrPic3, IMAGE_DEFAULT, IMAGE_FAILED);
				} else {
					mIvItemAddrPic3.setVisibility(View.INVISIBLE);
				}
			}
		} else {
			mTvAddrDetail.setText(getString(R.string.data_no_input));
			mItemPortWaterDepth.setContentText(getString(R.string.data_no_input));
			mItemPortShipWaterDepth.setContentText(getString(R.string.data_no_input));
			mIvItemAddrPic1.setVisibility(View.VISIBLE);
			mIvItemAddrPic2.setVisibility(View.INVISIBLE);
			mIvItemAddrPic3.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Logger.e(TAG, "onActivityResult: reqCode = " + requestCode + ", respCode = " + resultCode);
		switch (requestCode) {
		case GlobalMessageType.ActivityReqCode.REQ_INPUT_PRODUCT_INFO:
			if (resultCode == Activity.RESULT_OK) {
				if (data != null) {
					mProductInfo = (ProductInfoModel) data.getSerializableExtra(GlobalAction.BuyAction.EXTRA_KEY_PRODUCT_INFO);
				}
			}
			break;
		case GlobalMessageType.ActivityReqCode.REQ_INPUT_DISCHARGE_ADDRESS:
			if (resultCode == Activity.RESULT_OK) {
				if (data != null) {
					mSelectAddrInfo = (AddrInfoModel) data.getSerializableExtra(GlobalAction.ProfileAction.EXTRA_KEY_ADDR_INFO);
					updateAddrInfo();
				}
			}
			break;
		}
	}

	@Override
	public void onMenuClick(int type, int position, Object obj) {
		switch (type) {
		case GlobalMessageType.MenuType.SELECT_BUY_TYPE:
			if (position == 1) {
				if (mCallback != null) {
					mCallback.switchPubType(BuyType.SELLER, false);
				}
			}
			break;
		case GlobalMessageType.MenuType.SELECT_PRODUCT_TYPE:
			mItemProductType.setContentText(menuProductType.getAdapter().getItem(position).menuText);
			if (position == 0 && mProductType != ProductType.SAND) {
				mProductType = ProductType.SAND;
				mProdcutSandSpecId = null;
			} else if (position == 1 && mProductType != ProductType.STONE) {
				mProductType = ProductType.STONE;
				mProdcutSandSpecId = null;
			}
			updateProductSpecUI();
			break;
		case GlobalMessageType.MenuType.SELECT_PRODUCT_SPEC:
			if (getString(R.string.product_type_sand).equals(mItemProductType.getContentText())) {
				mProdcutSandSpecId = (String) obj;
				//Logger.i(TAG, "SandSpecId = " + mProdcutSandSpecId);
				updateProductSpecUI();
			} else {
				mProdcutStoneSpecId = menuStoneSpec.getAdapter().getItem(position).menuID;
				//Logger.i(TAG, "StoneSpecId = " + mProdcutStoneSpecId);
				updateProductSpecUI();
			}
			break;
		case GlobalMessageType.MenuType.SELECT_DISCHARGE_ADDR_TYPE:
			mDeliveryAddrType = DeliveryAddrType.convert(position + 1);
			updateDischargeAddrTypeUI();
			break;
		case GlobalMessageType.MenuType.SELECT_PORT:
			mTradeAreaCode = menuPortList.getAdapter().getItem(position).menuCode;
			mItemTradeArea.setContentText(menuPortList.getAdapter().getItem(position).menuText);
			break;
		}
	}

	@Override
	public void onConfirm(Object obj) {

	}

	@Override
	public void onCancel() {

	}

}
