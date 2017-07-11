package com.lab.lsystem.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Value;
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
import com.lab.lsystem.domain.PaperDomain;
import com.lab.lsystem.domain.StudentDomain;
import com.lab.lsystem.domain.StudentPaperDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.domain.TeacherPaperDomain;
import com.lab.lsystem.util.CodeBookConsts;
import com.lab.lsystem.util.CodeBookConstsType;
import com.lab.lsystem.util.CodeBookHelper;
import com.lab.lsystem.util.Consts;
import com.lab.lsystem.service.IPaperService;
import com.lab.lsystem.service.IStudentPaperService;
import com.lab.lsystem.service.IStudentService;
import com.lab.lsystem.service.ITeacherPaperService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.system.util.PageInfo;
import com.lab.system.util.SelectItem;

/*
 * 教师管理
 */
@Controller
@RequestMapping("/admin/paper")
public class PaperController {
	/**
	 * 账户列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Resource private IPaperService paperService;
	@Resource private ITeacherService teacherService;
	@Resource private IStudentService studentService;
	@Resource private IStudentPaperService studentpaperService;
	@Resource private ITeacherPaperService teacherpaperService;
	
	
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
	@RequestMapping("/paperList")
	public String getUserList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model) throws Exception{
		//采用分页方式获取
		List<PaperDomain> paperList=paperService.doGetPageList(pageInfo);
		
		model.addAttribute("paperList", paperList);
		
		return "/adminView/paper/paperList";
	}
	/**
	 * 学生详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/paperView/{id}")
	public String dopaperView(Model model,@PathVariable String id) throws Exception{
		
		//获取paper信息
		PaperDomain paperDomain=paperService.doGetById(id);
		model.addAttribute("paperDomain", paperDomain);
		
		
		return "/adminView/paper/paperList";
	}
	/**
	 * 新增教师页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/paperAdd")
	public String doteacherAdd(Model model)throws Exception{
		
		List<CodeBookDomain> disciplineItem=CodeBookHelper.getCodeBookByType(CodeBookConstsType.DISCIPLINE_TYPE);	
		List<CodeBookDomain> levelItem=CodeBookHelper.getCodeBookByType(CodeBookConstsType.JOURNAL_LEVEL);
		List<CodeBookDomain> typeItem=CodeBookHelper.getCodeBookByType(CodeBookConstsType.PAPER_TYPE);
		List<CodeBookDomain> authorItem=CodeBookHelper.getCodeBookByType(CodeBookConstsType.AUTHOR_TYPE);
		model.addAttribute("disciplineItem", disciplineItem);
		model.addAttribute("levelItem", levelItem);
		model.addAttribute("typeItem", typeItem);
		model.addAttribute("authorItem", authorItem);
		List<TeacherDomain> teachers= teacherService.doGetFilterList();
		List<StudentDomain> students= studentService.doGetFilterList();
		model.addAttribute("teachers", teachers);
		model.addAttribute("students", students);
		return "/adminView/paper/paperAdd";
	}
	
	/**
	 * 修改学生
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/paperEdit/{id}")
	public String dopaperEdit(Model model,@PathVariable String id)throws Exception{
		
		//获取paper信息
		PaperDomain paperDomain=paperService.doGetById(id);
		
		List<CodeBookDomain> grade=CodeBookHelper.getCodeBookByType(CodeBookConstsType.GRADE_TYPE);
		
		model.addAttribute("grade", grade);
		model.addAttribute("paperDomain", paperDomain);
		
	
		
		List<TeacherDomain> teachers= teacherService.doGetFilterList();
		model.addAttribute("teachers", teachers);
		
		return "/adminView/paper/paperEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") PaperDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			System.out.println(result);
			return Consts.ERROR;
		} else {
			if(paperService.doSave(domain)){
				String paperId=domain.getId();
				if(domain.getFirstIdentity().toString().equals(CodeBookConsts.AUTHOR_TYPE_A)){
					TeacherPaperDomain teacherPaperDomain=new TeacherPaperDomain();
					teacherPaperDomain.setPaperId(paperId);
					teacherPaperDomain.setTeacherId(domain.getFirstAuthor());
					teacherpaperService.doSave(teacherPaperDomain);
				}else{
					StudentPaperDomain studentPaperDomain=new StudentPaperDomain();
					studentPaperDomain.setPaperId(paperId);
					studentPaperDomain.setStuId(domain.getFirstAuthor());
					studentpaperService.doSave(studentPaperDomain);
				}
				if(domain.getSecondIdentity().toString().equals(CodeBookConsts.AUTHOR_TYPE_A)){
					TeacherPaperDomain teacherPaperDomain=new TeacherPaperDomain();
					teacherPaperDomain.setPaperId(paperId);
					teacherPaperDomain.setTeacherId(domain.getSecondAuthor());
					teacherpaperService.doSave(teacherPaperDomain);
				}else{
					StudentPaperDomain studentPaperDomain=new StudentPaperDomain();
					studentPaperDomain.setPaperId(paperId);
					studentPaperDomain.setStuId(domain.getSecondAuthor());
					studentpaperService.doSave(studentPaperDomain);
				}
		
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
		
		if(paperService.doDeleteById(id)){
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
	@RequestMapping("/deletepapers")
	@ResponseBody
	public String doDeletepapers(@RequestParam(value = "paperIds[]") String[] paperIds)throws Exception{
		
		if(paperService.doDeleteByIds(paperIds)){
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
	@RequestMapping("/paperSearchList")
	public String dopaperSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String searchText)throws Exception{
		
		List<PaperDomain> paperList=paperService.doSearchPaperPageList(pageInfo,searchText);
		model.addAttribute("paperList", paperList);
		return "/adminView/paper/paperList";
	}
	/**
	 * 根据第一作者身份获取人员
	 * @param model
	 * @param identify_value
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getFirstIdentity")
	@ResponseBody
	public String dogetFirstIdentify(Model model,String identity_value)throws Exception{
		
		List<SelectItem> identifyItems=new ArrayList<SelectItem>();
		List<TeacherDomain> teachers= teacherService.doGetFilterList();
		List<StudentDomain> students= studentService.doGetFilterList();
		if(identity_value.equals("0")){
			for(TeacherDomain teacherDomain:teachers){
				identifyItems.add(new SelectItem(teacherDomain.getId(),teacherDomain.getName()));
			}
		}else{
			for(StudentDomain studentDomain:students){
				identifyItems.add(new SelectItem(studentDomain.getId(),studentDomain.getName()));
			}
		}
		JSONArray jsonArray=JSONArray.fromObject(identifyItems);
		return jsonArray.toString();
		
	}
}
