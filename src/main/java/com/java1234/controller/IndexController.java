package com.java1234.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br>首页或者权限url跳转控制器
 *
 */
@Controller
public class IndexController {

	
    /**
     * <br> 网站根目录请求，跳转到登录页面
     * @return
     */
    @RequestMapping("/")
    public String root() {
    	return "redirect:/login.html";
    }
    
 
}
