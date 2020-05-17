package com.java1234.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java1234.entity.Log;
import com.java1234.entity.Supplier;
import com.java1234.service.LogService;
import com.java1234.service.SupplierService;

/**
 * <br>后台管理供应商Controller
 *
 */
@RestController
@RequestMapping("/admin/supplier")
public class SupplierAdminController {
	
	@Resource
	private SupplierService supplierService;
	
	@Resource
	private LogService logService;
	
	/**
	 * <br>分页查询供应商信息
	 * @param supplier
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@RequiresPermissions(value = { "供应商管理" })
	public Map<String,Object> list(Supplier supplier,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows)throws Exception{
		List<Supplier> supplierList=supplierService.list(supplier, page, rows, Direction.ASC, "id");
		Long total=supplierService.getCount(supplier);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("rows", supplierList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION,"Query supplier information")); // 写入日志
		return resultMap;
	}
	
	/**
	 * <br>下拉框模糊查询
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/comboList")
	@RequiresPermissions(value = {"进货入库","退货出库","进货单据查询","退货单据查询"},logical=Logical.OR)
	public List<Supplier> comboList(String q)throws Exception{
		if(q==null){
			q="";
		}
		return supplierService.findByName("%"+q+"%");
	}
	
	
	
	/**
	 * <br>添加或者修改供应商信息
	 * @param supplier
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@RequiresPermissions(value = { "供应商管理" })
	public Map<String,Object> save(Supplier supplier)throws Exception{
		if(supplier.getId()!=null){ // 写入日志
			logService.save(new Log(Log.UPDATE_ACTION,"Update supplier information"+supplier)); 
		}else{
			logService.save(new Log(Log.ADD_ACTION,"Add supplier information"+supplier)); 
		}
		Map<String, Object> resultMap = new HashMap<>();
		supplierService.save(supplier);			
		resultMap.put("success", true);
		return resultMap;
	}
	
	
	/**
	 * <br>删除供应商信息
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@RequiresPermissions(value = { "供应商管理" })
	public Map<String,Object> delete(String ids)throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			int id=Integer.parseInt(idsStr[i]);
			logService.save(new Log(Log.DELETE_ACTION,"Delete supplier information"+supplierService.findById(id)));  // 写入日志
			supplierService.delete(id);							
		}
		resultMap.put("success", true);
		return resultMap;
	}

}
