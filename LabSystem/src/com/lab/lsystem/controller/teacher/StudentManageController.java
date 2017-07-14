package com.lab.lsystem.controller.teacher;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
import com.lab.lsystem.domain.StudentDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.service.IStudentService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.lsystem.service.IUserService;
import com.lab.lsystem.util.CodeBookConstsType;
import com.lab.lsystem.util.CodeBookHelper;
import com.lab.lsystem.util.Consts;
import com.lab.system.util.CompressPicUtil;
import com.lab.system.util.PageInfo;

/**
 * 教师对学生控制层
 * @author zhu
 *
 */
@Controller
@RequestMapping("teacher/student")
public class StudentManageController {
	@Resource private ITeacherService teacherService;
	@Resource private IUserService userService;
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
	 * 学生管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/studentList")
	public String doStudentManage(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,HttpSession session) throws Exception{
		//获取当前登录用户名
		String workCode=(String)session.getAttribute(Consts.CURRENT_USER);
		//获取Teacher信息
		TeacherDomain teacherDomain=teacherService.doGetTeacherByWorkcode(workCode);
		String teacherId = teacherDomain.getId();
		List<StudentDomain> studentList = teacherService.doGetStudentPagedListByTutorId(pageInfo,teacherId);
		model.addAttribute("studentList", studentList);
		String headImgPath=shareupload+headImageDir;
		model.addAttribute("headImgPath", headImgPath);
		return "/teacherView/student/studentList";
	}
	/**
	 * 学生详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentView/{id}")
	public String dostudentView(Model model,@PathVariable String id) throws Exception{
		
		//获取student信息
		StudentDomain studentDomain=studentService.doGetById(id);
		model.addAttribute("studentDomain", studentDomain);
		
		//头像路径
		String headImgPath=shareupload+headImageDir;
		model.addAttribute("headImgPath", headImgPath);
		
		return "/teacherView/student/studentView";
	}
	/**
	 * 新增教师页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentAdd")
	public String doteacherAdd(Model model)throws Exception{
		
		List<CodeBookDomain> gradeItem=CodeBookHelper.getCodeBookByType(CodeBookConstsType.GRADE_TYPE);	
		List<TeacherDomain> teachers= teacherService.doGetFilterList();
		model.addAttribute("teachers", teachers);
		model.addAttribute("gradeItem", gradeItem);
		
		return "/teacherView/student/studentAdd";
	}
	
	/**
	 * 修改学生
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentEdit/{id}")
	public String dostudentEdit(Model model,@PathVariable String id)throws Exception{
		
		//获取student信息
		StudentDomain studentDomain=studentService.doGetById(id);
		
		List<CodeBookDomain> grade=CodeBookHelper.getCodeBookByType(CodeBookConstsType.GRADE_TYPE);
		
		model.addAttribute("grade", grade);
		model.addAttribute("studentDomain", studentDomain);
		
		//头像路径
		String headImgPath=shareupload+headImageDir;
		model.addAttribute("headImgPath", headImgPath);
		
		List<TeacherDomain> teachers= teacherService.doGetFilterList();
		model.addAttribute("teachers", teachers);
		
		return "/teacherView/student/studentEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") StudentDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			System.out.println(result);
			return Consts.ERROR;
		} else {
			if(studentService.doSave(domain)){
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
		
		if(studentService.doDeleteById(id)){
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
	@RequestMapping("/deleteStudents")
	@ResponseBody
	public String doDeleteStudents(@RequestParam(value = "studentIds[]") String[] studentIds)throws Exception{
		
		if(studentService.doDeleteByIds(studentIds)){
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
	@RequestMapping("/studentSearchList")
	public String dostudentSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String searchText)throws Exception{
		
		List<StudentDomain> studentList=studentService.doSearchstudentPageList(pageInfo,searchText);
		model.addAttribute("studentList", studentList);
		return "/teacherView/student/studentList";
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
