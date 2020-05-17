package com.java1234.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.java1234.entity.SaleList;
import com.java1234.entity.SaleListGoods;

/**
 * <br>销售单Service接口
 * @author admin
 *
 */
public interface SaleListService {

	/**
	 * <br>根据id查询实体
	 * @param id
	 * @return
	 */
	public SaleList findById(Integer id);
	
	/**
	 * <br>获取当天最大销售单号
	 * @return
	 */
	public String getTodayMaxSaleNumber();
	
	/**
	 * <br>添加销售单 以及所有销售单商品 以及 修改商品的成本均价
	 * @param saleList 销售单
	 * @param SaleListGoodsList 销售单商品
	 */
	public void save(SaleList saleList,List<SaleListGoods> saleListGoodsList);
	
	/**
	 * <br>根据条件查询销售单信息
	 * @param saleList
	 * @param page
	 * @param pageSize
	 * @param direction
	 * @param properties
	 * @return
	 */
	public List<SaleList> list(SaleList saleList,Direction direction,String... properties);
	
	/**
	 * <br>根据id删除销售单信息 包括销售单里的商品
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * <br>更新销售单
	 * @param saleList
	 */
	public void update(SaleList saleList);
	
	/**
	 * <br>按天统计某个日期范围内的销售信息
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Object> countSaleByDay(String begin,String end);
	
	/**
	 * <br>按月统计某个日期范围内的销售信息
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<Object> countSaleByMonth(String begin,String end);

}
