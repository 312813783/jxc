package com.java1234.service;

import java.util.List;

import com.java1234.entity.DamageListGoods;

/**
 * <br>报损单商品Service接口
 * @author admin
 *
 */
public interface DamageListGoodsService {

	/**
	 * <br> 根据报损单id查询所有报损单商品
	 * @param damageListId
	 * @return
	 */
	public List<DamageListGoods> listByDamageListId(Integer damageListId);


}
