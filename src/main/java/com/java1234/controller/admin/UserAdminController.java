package com.java1234.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java1234.entity.Log;
import com.java1234.entity.Role;
import com.java1234.entity.User;
import com.java1234.entity.UserRole;
import com.java1234.service.LogService;
import com.java1234.service.RoleService;
import com.java1234.service.UserRoleService;
import com.java1234.service.UserService;
import com.java1234.util.StringUtil;

/**
 * <br>后台管理用户Controller
 * @author admin
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserAdminController {

	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	    
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private LogService logService;
	
	/**
	 * <br>修改密码
	 * @param id
	 * @param newPassword
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/modifyPassword")
	@RequiresPermissions(value = { "修改密码" })
	public Map<String,Object> modifyPassword(Integer id,String newPassword,HttpSession session)throws Exception{
		User currentUser=(User) session.getAttribute("currentUser");
		User user=userService.findById(currentUser.getId());
		user.setPassword(newPassword);
		userService.save(user);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("success", true);
		logService.save(new Log(Log.UPDATE_ACTION,"Update passWord")); // 写入日志
		return map;
	}
	
	/**
	 * <br>安全退出
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/logout")
	@RequiresPermissions(value = { "安全退出" })
	public String logout()throws Exception{
		logService.save(new Log(Log.LOGOUT_ACTION,"User logout"));
		SecurityUtils.getSubject().logout();
		return "redirect:/login.html";
	}
	
	/**
	 * <br>分页查询用户信息
	 * @param user
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions(value = { "用户管理" })
	public Map<String,Object> list(User user,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows)throws Exception{
		List<User> userList=userService.list(user, page, rows, Direction.ASC, "id");
		for(User u:userList){
			List<Role> roleList=roleService.findByUserId(u.getId());
			StringBuffer sb=new StringBuffer();
			for(Role r:roleList){
				sb.append(","+r.getName());
			}
			u.setRoles(sb.toString().replaceFirst(",", ""));
		}
		Long total=userService.getCount(user);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("rows", userList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION,"Query user information")); // 写入日志
		return resultMap;
	}
	
	/**
	 * <br>保存用户角色设置
	 * @param roleIds
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/saveRoleSet")
	@RequiresPermissions(value = { "用户管理" })
	public Map<String,Object> saveRoleSet(String roleIds,Integer userId)throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		userRoleService.deleteByUserId(userId);  // 根据用户id删除所有用户角色关联实体
		if(StringUtil.isNotEmpty(roleIds)){
			String idsStr[]=roleIds.split(",");
			for(int i=0;i<idsStr.length;i++){ // 然后添加所有用户角色关联实体
				UserRole userRole=new UserRole();
				userRole.setUser(userService.findById(userId));
				userRole.setRole(roleService.findById(Integer.parseInt(idsStr[i])));
				userRoleService.save(userRole);
			}
		}
		resultMap.put("success", true);
		logService.save(new Log(Log.UPDATE_ACTION,"Save user information")); 
		return resultMap;
	}
	
	
	/**
	 * <br>添加或者修改用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions(value = { "用户管理" })
	public Map<String,Object> save(User user)throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		if(user.getId()==null){
			if(userService.findByUserName(user.getUserName())!=null){
				resultMap.put("success", false);
				resultMap.put("errorInfo", "用户名已经存在!");
				return resultMap;
			}
		}
		if(user.getId()!=null){ // 写入日志
			logService.save(new Log(Log.UPDATE_ACTION,"Update user information"+user)); 
		}else{
			logService.save(new Log(Log.ADD_ACTION,"Add user information"+user)); 
		}
		userService.save(user);			
		resultMap.put("success", true);
		return resultMap;
	}
	
	
	/**
	 * <br>删除用户信息
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions(value = { "用户管理" })
	public Map<String,Object> delete(Integer id)throws Exception{
		logService.save(new Log(Log.DELETE_ACTION,"Delete user information"+userService.findById(id)));  // 写入日志
		Map<String, Object> resultMap = new HashMap<>();
		userRoleService.deleteByUserId(id); // 删除用户角色关联信息
		userService.delete(id);				
		resultMap.put("success", true);
		return resultMap;
	}
}
