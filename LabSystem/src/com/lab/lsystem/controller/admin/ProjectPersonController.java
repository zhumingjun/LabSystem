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

import com.lab.lsystem.domain.CodeBookDomain;
import com.lab.lsystem.domain.PaperAuthorDomain;
import com.lab.lsystem.domain.ProjectPersonDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.util.CodeBookConstsType;
import com.lab.lsystem.util.CodeBookHelper;
import com.lab.lsystem.util.Consts;
import com.lab.lsystem.service.IProjectPersonService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.system.util.PageInfo;

/*
 * 教师管理
 */
@Controller
@RequestMapping("/admin/projectPerson")
public class ProjectPersonController {
	/**
	 * 账户列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Resource private IProjectPersonService projectPersonService;
	@Resource private ITeacherService teacherService;
	
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
	@RequestMapping("/projectPersonList/{id}")
	public String getUserList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,@PathVariable String id) throws Exception{
		//根据科研项目id获取参与人员
		List<ProjectPersonDomain> projectPersonList=projectPersonService.doGetPageListByResearchId(pageInfo,id);
		model.addAttribute("projectPersonList", projectPersonList);
		model.addAttribute("projectId", id);
		return "/adminView/projectPerson/projectPersonList";
	}
	/**
	 * 学生详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/projectPersonView/{id}")
	public String doprojectPersonView(Model model,@PathVariable String id) throws Exception{
		
		//获取projectPerson信息
		ProjectPersonDomain projectPersonDomain=projectPersonService.doGetById(id);
		model.addAttribute("projectPersonDomain", projectPersonDomain);
		
		
		return "/adminView/projectPerson/projectPersonView";
	}
	/**
	 * 新增教师页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/projectPersonAdd/{projectId}")
	public String doteacherAdd(Model model,@PathVariable String projectId)throws Exception{
		model.addAttribute("projectId", projectId);
		return "/adminView/projectPerson/projectPersonAdd";
	}
	
	/**
	 * 修改学生
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/projectPersonEdit/{id}")
	public String doprojectPersonEdit(Model model,@PathVariable String id)throws Exception{
		
		//获取projectPerson信息
		ProjectPersonDomain projectPersonDomain=projectPersonService.doGetById(id);
		model.addAttribute("projectPersonDomain", projectPersonDomain);
		List<TeacherDomain> teachers=teacherService.doGetFilterList();
		List<CodeBookDomain> stateList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.PROJECT_STATE);	
		List<CodeBookDomain> typeList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.PROJECT_TYPE);
		List<CodeBookDomain> levelList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.PROJECT_LEVEL);
		model.addAttribute("teachers", teachers);
		model.addAttribute("stateList", stateList);
		model.addAttribute("typeList", typeList);
		model.addAttribute("levelList", levelList);
		return "/adminView/projectPerson/projectPersonEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") ProjectPersonDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			System.out.println(result);
			return Consts.ERROR;
		} else {
			if(projectPersonService.doSave(domain)){
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
		
		if(projectPersonService.doDeleteById(id)){
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
	@RequestMapping("/deleteprojectPersons")
	@ResponseBody
	public String doDeleteprojectPersons(@RequestParam(value = "projectPersonIds[]") String[] projectPersonIds)throws Exception{
		
		if(projectPersonService.doDeleteByIds(projectPersonIds)){
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
	@RequestMapping("/projectPersonSearchList")
	public String doprojectPersonSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String searchText)throws Exception{
		
		List<ProjectPersonDomain> projectPersonList=projectPersonService.doSearchProjectPersonPageList(pageInfo,searchText);
		model.addAttribute("projectPersonList", projectPersonList);
		return "/adminView/projectPerson/projectPersonList";
	}
}
