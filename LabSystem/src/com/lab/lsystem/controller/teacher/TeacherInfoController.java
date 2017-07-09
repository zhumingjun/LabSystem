package com.lab.lsystem.controller.teacher;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab.system.util.PageInfo;
import com.lab.lsystem.service.IUserService;
import com.lab.lsystem.domain.StudentDomain;
import com.lab.lsystem.domain.UserDomain;
import com.lab.lsystem.util.Consts;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.service.ITeacherService;



/**
 * 教师信息控制层
 * @author zhu
 *
 */
@Controller
@RequestMapping("teacher/teacherinfo")
public class TeacherInfoController {

	@Resource private ITeacherService teacherService;
	@Resource private IUserService userService;
	
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
	 * 教师主页
	 * @param model
	 * @return
	 */
	@RequestMapping("/teacherIndex")
	public String doTeacherIndex(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,HttpSession session) throws Exception{
		//获取当前登录用户名
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		//获取Teacher信息
		TeacherDomain teacherDomain=teacherService.doGetUserByUsername(username);
		model.addAttribute("teacherDomain", teacherDomain);
		//头像路径
		String headImgPath=shareupload+headImageDir;
		model.addAttribute("headImgPath", headImgPath);
		return "/teacherView/teacherIndex";
	}
	/**
	 * 学生管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/studentManage")
	public String doStudentManage(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,HttpSession session) throws Exception{
		//获取当前登录用户名
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		//获取Teacher信息
		TeacherDomain teacherDomain=teacherService.doGetUserByUsername(username);
		String teacherId = teacherDomain.getId();
		List<StudentDomain> studentList = teacherService.doGetStudentPagedListByTutorId(pageInfo,teacherId);
		model.addAttribute("studentList", studentList);
		String headImgPath=shareupload+headImageDir;
		model.addAttribute("headImgPath", headImgPath);
		return "/adminView/student/studentList";
	}
	
}
