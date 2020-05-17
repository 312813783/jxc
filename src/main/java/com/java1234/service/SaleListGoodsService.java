package com.java1234.service;

import java.util.List;

import com.java1234.entity.SaleListGoods;

/**
 * <br>销售单商品Service接口
 * @author admin
 *
 */
public interface SaleListGoodsService {

	/**
	 * <br>根据销售单id查询所有销售单商品
	 * @param saleListId
	 * @return
	 */
	public List<SaleListGoods> listBySaleListId(Integer saleListId);
	
	/**
	 * <br>统计某个商品的销售总量
	 * @param goodsId
	 * @return
	 */
	public Integer getTotalByGoodsId(Integer goodsId);
	
	/**
	 * <br>根据条件查询销售单所有商品
	 * @param saleListGoods
	 * @return
	 */
	public List<SaleListGoods> list(SaleListGoods saleListGoods);


}
