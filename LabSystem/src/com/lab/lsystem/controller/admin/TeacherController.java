package com.lab.lsystem.controller.admin;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

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
import org.springframework.web.multipart.MultipartFile;

import com.lab.lsystem.domain.CodeBookDomain;
import com.lab.lsystem.util.CodeBookConstsType;
import com.lab.lsystem.util.CodeBookHelper;
import com.lab.lsystem.util.Consts;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.service.ITeacherService;
import com.lab.system.util.CompressPicUtil;
import com.lab.system.util.PageInfo;

/*
 * 教师管理
 */
@Controller
@RequestMapping("/admin/teacher")
public class TeacherController {
	/**
	 * 账户列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@Resource private ITeacherService teacherService;
	
	
	@Value("#{envProperties['labsystemupload']}") private String shareupload;
	@Value("#{envProperties['uploadpath']}") private String uploadpath;
	@Value("#{envProperties['headImageDir']}") private String headImageDir;
	@Value("#{envProperties['midWidth']}") private String midWidth;
	@Value("#{envProperties['midHeight']}") private String midHeight;
	
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
	@RequestMapping("/teacherList")
	public String getUserList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model) throws Exception{
		//采用分页方式获取
		List<TeacherDomain> teacherList=teacherService.doGetPageList(pageInfo);
		List<CodeBookDomain> jobTitleList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.JOBTITLE_TYPE);	
		List<CodeBookDomain> mentorStatusList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.MENTOR_STATUS);
		List<CodeBookDomain> sexList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.SEX_TYPE);
		model.addAttribute("jobTitleList", jobTitleList);
		model.addAttribute("mentorStatusList", mentorStatusList);
		model.addAttribute("sexList", sexList);
		model.addAttribute("teacherList", teacherList);
		
		return "/adminView/teacher/teacherList";
	}
	/**
	 * 学生详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/teacherView/{id}")
	public String dostudentView(Model model,@PathVariable String id) throws Exception{
		
		//获取student信息
		TeacherDomain teacherDomain=teacherService.doGetById(id);
		model.addAttribute("teacherDomain", teacherDomain);
		
		//头像路径
		String headImgPath=shareupload+headImageDir;
		model.addAttribute("headImgPath", headImgPath);
		
		return "/adminView/teacher/teacherView";
	}
	/**
	 * 新增教师页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/teacherAdd")
	public String doteacherAdd(Model model)throws Exception{
		
		List<CodeBookDomain> jobTitleList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.JOBTITLE_TYPE);	
		List<CodeBookDomain> mentorStatusList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.MENTOR_STATUS);
		model.addAttribute("jobTitleList", jobTitleList);
		model.addAttribute("mentorStatusList", mentorStatusList);
		
		return "/adminView/teacher/teacherAdd";
	}
	
	/**
	 * 修改学生
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/teacherEdit/{id}")
	public String dostudentEdit(Model model,@PathVariable String id)throws Exception{
		
		//获取student信息
		TeacherDomain teacherDomain=teacherService.doGetById(id);
		
		List<CodeBookDomain> jobTitle=CodeBookHelper.getCodeBookByType(CodeBookConstsType.JOBTITLE_TYPE);
		List<CodeBookDomain> mentorStatus=CodeBookHelper.getCodeBookByType(CodeBookConstsType.MENTOR_STATUS);
		model.addAttribute("jobTitle", jobTitle);
		model.addAttribute("mentorStatus", mentorStatus);
		model.addAttribute("teacherDomain", teacherDomain);
		
		//头像路径
		String headImgPath=shareupload+headImageDir;
		model.addAttribute("headImgPath", headImgPath);
		
		return "/adminView/teacher/teacherEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") TeacherDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			System.out.println(result);
			return Consts.ERROR;
		} else {
			if(teacherService.doSave(domain)){
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
		
		if(teacherService.doDeleteById(id)){
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
	@RequestMapping("/deleteTeachers")
	@ResponseBody
	public String doDeleteStudents(@RequestParam(value = "teacherIds[]") String[] teacherIds)throws Exception{
		
		if(teacherService.doDeleteByIds(teacherIds)){
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
	@RequestMapping("/teacherSearchList")
	public String dostudentSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String searchText,Integer sex,Integer jobTitle,Integer mentorStatus)throws Exception{	
		List<TeacherDomain> teacherList=teacherService.doSearchteacherPageList(pageInfo,searchText,sex,jobTitle,mentorStatus);
		model.addAttribute("teacherList", teacherList);
		List<CodeBookDomain> jobTitleList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.JOBTITLE_TYPE);	
		List<CodeBookDomain> mentorStatusList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.MENTOR_STATUS);
		List<CodeBookDomain> sexList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.SEX_TYPE);
		model.addAttribute("jobTitleList", jobTitleList);
		model.addAttribute("mentorStatusList", mentorStatusList);
		model.addAttribute("sexList", sexList);
		
//		model.addAttribute("sexDomain", )
//		model.addAttribute("teacherList", teacherList);
		
		return "/adminView/teacher/teacherList";
	}
	/**
	 * 上传图片
	 * <p>The uploaderImg</p>
	 * @param file
	 * @param adverType
	 * @return
	 * @throws Exception
	 * @author:Administrator 2016-2-17
	 * @update: [updatedate] [changer][change description]
	 */
	@RequestMapping("/uploaderImg")
	@ResponseBody
	public String uploaderImg(@RequestParam(value = "file", required = false) MultipartFile file, String workCode)
		throws Exception
	{
		String imgType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
			file.getOriginalFilename().length());
		String fileName = workCode+"_"+System.currentTimeMillis() + imgType;
		String path = uploadpath + headImageDir+ workCode +File.separator;
		
		File targetFile = new File(path, fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		// 保存
		try{
			//保存文件
			file.transferTo(targetFile);
			//切割尺寸
			CompressPicUtil.compressPic(targetFile, path, fileName, Integer.parseInt(midWidth),
					Integer.parseInt(midHeight), "");

		}catch (Exception e){
			//e.printStackTrace();
		}
		return fileName;
	}
}
