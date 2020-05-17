package com.java1234.service;

import java.util.List;

import com.java1234.entity.PurchaseListGoods;

/**
 * <br>进货单商品Service接口
 * @author admin
 *
 */
public interface PurchaseListGoodsService {

	/**
	 * <br>根据进货单id查询所有进货单商品
	 * @param purchaseListId
	 * @return
	 */
	public List<PurchaseListGoods> listByPurchaseListId(Integer purchaseListId);
	
	/**
	 * <br>根据条件查询进货单商品
	 * @param PurchaseListGoods
	 * @return
	 */
	public List<PurchaseListGoods> list(PurchaseListGoods purchaseListGoods);


}
