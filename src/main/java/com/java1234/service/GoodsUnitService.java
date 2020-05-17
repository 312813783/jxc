package com.java1234.service;

import java.util.List;

import com.java1234.entity.GoodsUnit;

/**
 * <br> 商品单位Service接口
 * @author admin
 *
 */
public interface GoodsUnitService {

	/**
	 * <br> 根据id查询实体
	 * @param id
	 * @return
	 */
	public GoodsUnit findById(Integer id);
	
	/**
	 * <br>查询所有商品单位信息
	 * @return
	 */
	public List<GoodsUnit> listAll();
	
	/**
	 * <br>修改或者修改商品单位信息
	 * @param goods
	 */
	public void save(GoodsUnit goodsUnit);
	
	/**
	 * <br>根据id删除商品单位
	 * @param id
	 */
	public void delete(Integer id);
}
