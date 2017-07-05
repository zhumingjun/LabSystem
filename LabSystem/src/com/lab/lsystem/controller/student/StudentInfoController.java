package com.lab.lsystem.controller.student;

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
import com.lab.lsystem.domain.UserDomain;
import com.lab.lsystem.util.Consts;
import com.lab.lsystem.domain.StudentDomain;
import com.lab.lsystem.service.IStudentService;



/**
 * 学生信息控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("student/studentinfo")
public class StudentInfoController {

	@Resource private IStudentService studentService;
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
	 * 学生主页
	 * @param model
	 * @return
	 */
	@RequestMapping("/studentIndex")
	public String dostudentIndex(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,HttpSession session) throws Exception{
		//获取当前登录用户名
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		//获取student信息
		StudentDomain studentDomain=studentService.doGetUserByUsername(username);
		model.addAttribute("studentDomain", studentDomain);
		//头像路径
		String headImgPath=shareupload+headImageDir;
		model.addAttribute("headImgPath", headImgPath);
		return "/studentView/studentIndex";
	}
	
}
