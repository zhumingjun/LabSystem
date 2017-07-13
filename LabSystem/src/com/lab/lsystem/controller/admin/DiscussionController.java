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
import com.lab.lsystem.domain.DiscussionDomain;
import com.lab.lsystem.util.Consts;
import com.lab.lsystem.service.IDiscussionService;
import com.lab.system.util.PageInfo;

/*
 * 教师管理
 */
@Controller
@RequestMapping("/admin/discussion")
public class DiscussionController {
	/**
	 * 账户列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Resource private IDiscussionService discussionService;
	
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
	@RequestMapping("/discussionList")
	public String getUserList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model) throws Exception{
		//采用分页方式获取
		List<DiscussionDomain> discussionList=discussionService.doGetPageList(pageInfo);
		
		model.addAttribute("discussionList", discussionList);
		
		return "/adminView/discussion/discussionList";
	}
	/**
	 * 学生详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/discussionView/{id}")
	public String dodiscussionView(Model model,@PathVariable String id) throws Exception{
		
		//获取discussion信息
		DiscussionDomain discussionDomain=discussionService.doGetById(id);
		model.addAttribute("discussionDomain", discussionDomain);
		
		
		return "/adminView/discussion/discussionView";
	}
	/**
	 * 新增教师页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/discussionAdd")
	public String doteacherAdd(Model model)throws Exception{
		
		return "/adminView/discussion/discussionAdd";
	}
	
	/**
	 * 修改学生
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/discussionEdit/{id}")
	public String dodiscussionEdit(Model model,@PathVariable String id)throws Exception{
		
		//获取discussion信息
		DiscussionDomain discussionDomain=discussionService.doGetById(id);
		
		
		return "/adminView/discussion/discussionEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") DiscussionDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			System.out.println(result);
			return Consts.ERROR;
		} else {
			if(discussionService.doSave(domain)){
				return Consts.SUCCESS;
			}
		}
		return Consts.ERROR;
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
		
		if(discussionService.doDeleteById(id)){
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
	@RequestMapping("/deletediscussions")
	@ResponseBody
	public String doDeletediscussions(@RequestParam(value = "discussionIds[]") String[] discussionIds)throws Exception{
		
		if(discussionService.doDeleteByIds(discussionIds)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	/**
	 * 搜索
	 * @param pageInfo
	 * @param bindingResult
	 * @param model
	 * @param searchText
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/discussionSearchList")
	public String dodiscussionSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String searchText)throws Exception{
		
		List<DiscussionDomain> discussionList=discussionService.doSearchDiscussionPageList(pageInfo,searchText);
		model.addAttribute("discussionList", discussionList);
		return "/adminView/discussion/discussionList";
	}
}
