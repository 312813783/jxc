package com.java1234.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.java1234.entity.Log;
import com.java1234.entity.Menu;
import com.java1234.entity.Role;
import com.java1234.entity.User;
import com.java1234.service.LogService;
import com.java1234.service.MenuService;
import com.java1234.service.RoleService;
import com.java1234.service.UserService;
import com.java1234.util.StringUtil;

/**
 * <br>用户 登录控制器
 * @author 
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private RoleService roleService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private MenuService menuService;
	
	@Resource
	private LogService logService;
	
	/**
     * <br>登录
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public Map<String,Object> login(String imageCode,@Valid User user,BindingResult bindingResult,HttpSession session){
    	Map<String,Object> map=new HashMap<String,Object>();
//    	验证是否输入验证码，如果验证码为空提示影虎输入验证码
    	if(StringUtil.isEmpty(imageCode)){
    		map.put("success", false);
    		map.put("errorInfo", "请输入验证码");
    		return map;
    	}
//      验证用户输入验证码是否正确
//    	if(!imageCode.equalsIgnoreCase((String) session.getAttribute("checkcode"))){
//    		map.put("success", false);
//    		map.put("errorInfo", "验证码输入错误");
//    		return map;
//    	}
    	if(bindingResult.hasErrors()){
    		map.put("success", false);
    		map.put("errorInfo", bindingResult.getFieldError().getDefaultMessage());
    		return map;
    	}
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try{
//			登录认证
			subject.login(token);
			String userName=(String) SecurityUtils.getSubject().getPrincipal();
			User currentUser=userService.findByUserName(userName);
			session.setAttribute("currentUser", currentUser);
			List<Role> roleList=roleService.findByUserId(currentUser.getId());
			map.put("roleList", roleList);
			map.put("roleSize", roleList.size());
			map.put("success", true);
//			写入日志
			logService.save(new Log(Log.LOGIN_ACTION,"User Logining"));
			return map;
		}catch(Exception e){
			map.put("success", false);
			map.put("errorInfo", "用户名或者密码错误");
			return map;
		}
    }
    
    /**
     * <br>保存角色信息
     * @param roleId
     * @param session
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/saveRole")
    public Map<String,Object> saveRole(Integer roleId,HttpSession session)throws Exception{
    	Map<String,Object> map=new HashMap<String,Object>();
    	Role currentRole=roleService.findById(roleId);
    	session.setAttribute("currentRole", currentRole); // 保存当前角色信息
    	map.put("success", true);
    	return map;
    }
    
    /**
     * <br>加载当前用户信息
     * @param session
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/loadUserInfo")
    public String loadUserInfo(HttpSession session)throws Exception{
    	User currentUser=(User) session.getAttribute("currentUser");
    	Role currentRole=(Role) session.getAttribute("currentRole");
    	return "欢迎您："+currentUser.getTrueName()+"&nbsp;[&nbsp;"+currentRole.getName()+"&nbsp;]";
    }
    
    /**
     * <br>加载权限菜单
     * @param session
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/loadMenuInfo")
    public String loadMenuInfo(HttpSession session,Integer parentId)throws Exception{
    	Role currentRole=(Role) session.getAttribute("currentRole");
    	return getAllMenuByParentId(parentId,currentRole.getId()).toString();
    }
    
    /**
     * <br>获取所有菜单信息
     * @param parentId
     * @param roleId
     * @return
     */
    private JsonArray getAllMenuByParentId(Integer parentId,Integer roleId){
    	JsonArray jsonArray=this.getMenuByParentId(parentId, roleId);
    	for(int i=0;i<jsonArray.size();i++){
    		JsonObject jsonObject=(JsonObject) jsonArray.get(i);
    		if("open".equals(jsonObject.get("state").getAsString())){
    			continue;
    		}else{
    			jsonObject.add("children", getAllMenuByParentId(jsonObject.get("id").getAsInt(),roleId));
    		}
    	}
    	return jsonArray;
    }
    
    /**
     * <br>根据父节点和用户角色id查询菜单
     * @param parentId
     * @param roleId
     * @return
     */
    private JsonArray getMenuByParentId(Integer parentId,Integer roleId){
    	List<Menu> menuList=menuService.findByParentIdAndRoleId(parentId, roleId);
    	JsonArray jsonArray=new JsonArray();
    	for(Menu menu:menuList){
    		JsonObject jsonObject=new JsonObject();
    		jsonObject.addProperty("id", menu.getId()); // 节点id
    		jsonObject.addProperty("text", menu.getName()); // 节点名称
    		if(menu.getState()==1){
    			jsonObject.addProperty("state", "closed"); // 根节点
    		}else{
    			jsonObject.addProperty("state", "open"); // 叶子节点
    		}
    		jsonObject.addProperty("iconCls", menu.getIcon());
    		JsonObject attributeObject=new JsonObject(); // 扩展属性
    		attributeObject.addProperty("url", menu.getUrl()); // 菜单请求地址
			jsonObject.add("attributes", attributeObject);
			jsonArray.add(jsonObject);
    	}
    	return jsonArray;
    }
}
