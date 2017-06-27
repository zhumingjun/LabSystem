package com.lab.lsystem.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab.lsystem.domain.RoleDomain;
import com.lab.system.util.PageInfo;
import com.lab.lsystem.domain.UserDomain;
import com.lab.lsystem.service.IRoleService;
import com.lab.lsystem.service.IUserService;
/**
 * 用户登录控制
 * @author zhu
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {
	/**
	 * 账户列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	
	@Resource private IUserService userService;
	@Resource private IRoleService roleService;
	
	/**
	 * 过滤起前台pageInfo
	 * 使@ModelAttribute("pageInfo") PageInfo pageInfo在前台使用name="pageInfo.currentPageNo"来进行传参数
	 * @param binder
	 */
	@InitBinder("pageInfo")  
	public void initPageInfoBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("pageInfo.");
	}
	
	/**
	 * 账户列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userList")
	public String getUserList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model) throws Exception{
		//采用分页方式获取
		List<UserDomain> userList=userService.doGetPageList(pageInfo);
		List<RoleDomain> roleList=roleService.doGetFilterList();
		
		model.addAttribute("userList", userList);
		model.addAttribute("roleList", roleList);
		
		return "/adminView/user/userList";
	}
	/**
	 * 根据查询条件返回用户列表
	 * @param pageInfo
	 * @param bindingResult
	 * @param model
	 * @param authority
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userSearchList")
	public String doUserSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String roleId,String searchText)throws Exception{
		
		//采用分页方式获取
		List<UserDomain> userList=userService.doSearchUserPageList(pageInfo, roleId, searchText);
		List<RoleDomain> roleList=roleService.doGetFilterList();
		
		model.addAttribute("userList", userList);
		model.addAttribute("roleList", roleList);
		model.addAttribute("roleId", roleId);
		model.addAttribute("searchText", searchText);
		
		return "/adminView/user/userList";
	}

}
