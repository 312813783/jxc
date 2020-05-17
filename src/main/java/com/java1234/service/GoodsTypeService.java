package com.java1234.service;

import java.util.List;

import com.java1234.entity.GoodsType;

/**
 * <br>商品类别Service接口
 * @author admin
 *
 */
public interface GoodsTypeService {

	/**
	 * <br>根据id查询商品类别实体
	 * @param id
	 * @return
	 */
	public GoodsType findById(Integer id);
	
	/**
	 * <br>根据父节点查找商品类别
	 * @param parentId
	 * @return
	 */
	public List<GoodsType> findByParentId(int parentId);
	
	/**
	 * <br>添加或者修改商品类别信息
	 * @param goodsType
	 */
	public void save(GoodsType goodsType);
	
	/**
	 * <br>根据id删除商品类别信息
	 * @param id
	 */
	public void delete(Integer id);
}
