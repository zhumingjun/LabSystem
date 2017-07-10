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
import com.lab.lsystem.domain.PaperDomain;
import com.lab.lsystem.domain.StudentDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.util.CodeBookConstsType;
import com.lab.lsystem.util.CodeBookHelper;
import com.lab.lsystem.util.Consts;
import com.lab.lsystem.service.IPaperService;
import com.lab.lsystem.service.IStudentService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.system.util.CompressPicUtil;
import com.lab.system.util.PageInfo;

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
		
		//头像路径
		String headImgPath=shareupload+headImageDir;
		model.addAttribute("headImgPath", headImgPath);
		
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
		model.addAttribute("disciplineItem", disciplineItem);
		model.addAttribute("levelItem", levelItem);
		model.addAttribute("typeItem", typeItem);
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
		
		//头像路径
		String headImgPath=shareupload+headImageDir;
		model.addAttribute("headImgPath", headImgPath);
		
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
	public String uploaderImg(@RequestParam(value = "file", required = false) MultipartFile file, String stuCode)
		throws Exception
	{
		String imgType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
			file.getOriginalFilename().length());
		String fileName = stuCode+"_"+System.currentTimeMillis() + imgType;
		String path = uploadpath + headImageDir+ stuCode +File.separator;
		
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
