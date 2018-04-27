package com.kenya.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kenya.bean.User;
import com.kenya.service.UserService;
  
  
@Controller  
@RequestMapping("/user") 
public class UserController {  
	private static Logger log=LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/selectAll")
	public @ResponseBody List<User> SelectAll(Map<String, Object> model) {

        List<User> users = userService.selectAll(); 
        log.info(users.toString());
        return users; 
	}
}  
 