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

import com.lab.lsystem.domain.ResearchDomain;
import com.lab.lsystem.util.Consts;
import com.lab.lsystem.service.IResearchService;
import com.lab.system.util.PageInfo;

/*
 * 教师管理
 */
@Controller
@RequestMapping("/admin/research")
public class ResearchController {
	/**
	 * 账户列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Resource private IResearchService researchService;
	
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
	@RequestMapping("/researchList")
	public String getUserList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model) throws Exception{
		//采用分页方式获取
		List<ResearchDomain> researchList=researchService.doGetPageList(pageInfo);
		
		model.addAttribute("researchList", researchList);
		
		return "/adminView/research/researchList";
	}
	/**
	 * 学生详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/researchView/{id}")
	public String doresearchView(Model model,@PathVariable String id) throws Exception{
		
		//获取research信息
		ResearchDomain researchDomain=researchService.doGetById(id);
		model.addAttribute("researchDomain", researchDomain);
		
		
		return "/adminView/research/researchView";
	}
	/**
	 * 新增教师页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/researchAdd")
	public String doteacherAdd(Model model)throws Exception{
		
		return "/adminView/research/researchAdd";
	}
	
	/**
	 * 修改学生
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/researchEdit/{id}")
	public String doresearchEdit(Model model,@PathVariable String id)throws Exception{
		
		//获取research信息
		ResearchDomain researchDomain=researchService.doGetById(id);
		model.addAttribute("researchDomain", researchDomain);
		
		return "/adminView/research/researchEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") ResearchDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			System.out.println(result);
			return Consts.ERROR;
		} else {
			if(researchService.doSave(domain)){
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
		
		if(researchService.doDeleteById(id)){
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
	@RequestMapping("/deleteresearchs")
	@ResponseBody
	public String doDeleteresearchs(@RequestParam(value = "researchIds[]") String[] researchIds)throws Exception{
		
		if(researchService.doDeleteByIds(researchIds)){
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
	@RequestMapping("/researchSearchList")
	public String doresearchSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String searchText)throws Exception{
		
		List<ResearchDomain> researchList=researchService.doSearchResearchPageList(pageInfo,searchText);
		model.addAttribute("researchList", researchList);
		return "/adminView/research/researchList";
	}
}
