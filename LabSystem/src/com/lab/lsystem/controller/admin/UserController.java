package com.lab.lsystem.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lab.lsystem.util.Consts;
import com.lab.system.util.EndecryptUtils;
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
	/**
	 * 用户详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userView/{id}")
	public String doUserView(Model model,@PathVariable String id) throws Exception{
		
		//获取User信息
		UserDomain userDomain=userService.doGetById(id);
		model.addAttribute("userDomain", userDomain);
		
		return "/adminView/user/userView";
	}
	
	/**
	 * 新增账户页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userAdd")
	public String doUserAdd(Model model)throws Exception{
		
		List<RoleDomain> roleList=roleService.doGetFilterList();
		model.addAttribute("roleList", roleList);
		return "/adminView/user/userAdd";
	}
	
	/**
	 * 修改用户
	 * @param model
	 * @param id 用户id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userEdit/{id}")
	public String doUserEdit(Model model,@PathVariable String id)throws Exception{

		//获取单条Domain信息
		UserDomain userDomain=userService.doGetById(id);
		List<RoleDomain> roleList=roleService.doGetFilterList();
		
		model.addAttribute("roleList", roleList);
		model.addAttribute("userDomain", userDomain);
		return "/adminView/user/userEdit";
	}
	
	/**
	 * 保存
	 * @param domain
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String doSave(@Valid @ModelAttribute("domain") UserDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			return Consts.ERROR;
		} else {			
			//设置md5加密
			domain.setPassword(EndecryptUtils.md5(domain.getPassword()));
			if(userService.doSave(domain)){
				return Consts.SUCCESS;
			}
		}
		return "error";
	}
	
	/**
	 * 保存
	 * @param domain
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editSave")
	@ResponseBody
	public String doEditSave(@Valid @ModelAttribute("domain") UserDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			return Consts.ERROR;
		} else {
			
			if(userService.doSave(domain)){
				return Consts.SUCCESS;
			}
		}
		return "error";
	}
	
	/**
	 * 删除单条数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public String doDelete(@PathVariable String id)throws Exception{
		
		if(userService.doDeleteById(id)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	
	/**
	 * 批量删除
	 * @param userIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteUsers")
	@ResponseBody
	public String doDeleteUsers(@RequestParam(value = "userIds[]") String[] userIds)throws Exception{
		
		if(userService.doDeleteByIds(userIds)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}

}
