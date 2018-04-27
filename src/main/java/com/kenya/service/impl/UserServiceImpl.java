package com.kenya.service.impl;



import java.awt.image.renderable.RenderableImage;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kenya.bean.User;

import com.kenya.dao.UserMapper;
import com.kenya.service.UserService;

import com.kenya.until.MD5Util;

/*import com.alibaba.druid.sql.ast.statement.SQLIfStatement.Else;
import com.alibaba.fastjson.JSON;
import com.kenYa.ajaxResult.JsonResult;
import com.kenYa.ajaxResult.ParameterException;
import com.kenYa.ajaxResult.JsonCodeEnum;
//import com.kenYa.controller.ExceptionController;
import com.kenYa.exception.MyException;
import com.kenYa.exception.MyExceptionHandler;
import com.kenYa.mapper.KenyaUserMapper;
import com.kenYa.output.UserDetails;
import com.kenYa.pojo.KenyaUser;
import com.kenYa.pojo.KenyaUserExample;
import com.kenYa.pojo.KenyaUserExample.Criteria;
import com.kenYa.service.kenYaUserService;
import com.kenYa.until.MD5Util;

import test.HandleException;
*/
/*import static com.google.common.base.Preconditions.checkNotNull;*/

@Service

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper kenyaUserMapper;
	@Autowired
	private UserMapper userdao;
	
	
	
	public List<User> selectAll() {
		
		return userdao.selectByExample(null);
	}

	public void insertUser(User KenyaUser) {
		// TODO Auto-generated method stub

	}
	// ExceptionController1 exceptionController = new ExceptionController1();

	// 获取用户详细信息（通过id）
	@Override
	public User getUserDetails(Integer userId) {
		
		User e = kenyaUserMapper.getUserDetailsById(userId);
		if (e == null) {
			// throw new MyException("该用户信息不存在");
			// myExceptionHandler.resolveException(request, response, handler, ex)
			return null;
		}
		/*
		 * List<Role> roles = roleMapper.getRoleListByUserId(userId);
		 * userDetails.setRoles(roles); return userDetails;
		 */
		return e;
	}
	
	@Override
	public User login(String userPhoneNumber, String password) {

		User kenyaUser = kenyaUserMapper.ByUserPhoneNumber(userPhoneNumber);

		if (kenyaUser != null) {
			//System.out.println(kenyaUser.getUserName());
			return kenyaUser;

		} else {
			return null;
		}
	}


	@Override

	public void createUser(String userName, String psw, Integer userSex, int userAge, String userPhonenumber) {
		

		User kenyaUser = new User();
		kenyaUser.setUserName(userName);
		kenyaUser.setUserPsw(MD5Util.getMD5(psw.getBytes()));
		kenyaUser.setUserSex(userSex);
		kenyaUser.setUserAge(userAge);
		kenyaUser.setUserPhonenumber(userPhonenumber);
		kenyaUserMapper.insert(kenyaUser);	

	}

	@Override
	// 根据手机查询数量
	public Integer getPhoneNumberCount(String PhoneNumber) {
		Integer count = kenyaUserMapper.getPhoneNumberCount(PhoneNumber);
		return count;
	}

	@Override//修改密码
	public Boolean updatePassWord(String userPhoneNumber, String userPsw) {
		if(kenyaUserMapper.getPhoneNumberCount(userPhoneNumber)>0) {
			//更新密码,不能和上次密码相同
			User kenyaUser = kenyaUserMapper.ByUserPhoneNumber(userPhoneNumber);
			if (kenyaUser.getUserPsw().equals(MD5Util.getMD5(userPsw.getBytes()))) {
				return false;
				
			} else {
				String updatePW = MD5Util.getMD5(userPsw.getBytes());
				//kenyaUserMapper.updatePassWord(null, null, userPsw, null, null,userPhoneNumber);
				kenyaUserMapper.updatePassWord(null, null, updatePW, null, null, userPhoneNumber);
				return true;
			}
			
		}else {
			return null;
		}
	}



}
