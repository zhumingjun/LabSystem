package com.lab.lsystem.controller.student;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab.lsystem.domain.PaperDomain;
import com.lab.lsystem.domain.StudentDomain;
import com.lab.lsystem.domain.StudentPaperDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.domain.TeacherPaperDomain;
import com.lab.lsystem.service.IPaperService;
import com.lab.lsystem.service.IStudentPaperService;
import com.lab.lsystem.service.IStudentService;
import com.lab.lsystem.service.ITeacherPaperService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.lsystem.util.Consts;
import com.lab.system.util.PageInfo;

/**
 * 学生论文信息控制层
 * 
 * @author chen
 * 
 */
@Controller
@RequestMapping("student/paper")
public class StudentPaperManage {
	@Resource
	private IPaperService paperService;
	@Resource
	private IStudentService studentService;
	@Resource
	private IStudentPaperService studentpaperService;

	/**
	 * 过滤起前台pageInfo 使@ModelAttribute("pageInfo") PageInfo
	 * pageInfo在前台使用name="pageInfo.currentPageNo"来进行传参数
	 * 
	 * @param binder
	 */
	@InitBinder("pageInfo")
	public void initPageInfoBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("pageInfo.");
	}

	/**
	 * 账户列表页面
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/paperList")
	public String getPaperList(@ModelAttribute("pageInfo") PageInfo pageInfo,
			BindingResult bindingResult, Model model, HttpSession session)
			throws Exception {
		// 获取当前登录用户名
		String stuCode = (String) session.getAttribute(Consts.CURRENT_USER);
		// 根据学生学号获取教师论文
		StudentDomain studentDomain=studentService.doGetStudentByStuCode(stuCode);
		List<StudentPaperDomain> studentPaperList = studentpaperService
				.doGetPageListByStuId(pageInfo, studentDomain.getId());
		List<PaperDomain> paperList = new ArrayList<PaperDomain>();
		if (studentPaperList.size() > 0) {
			for (StudentPaperDomain sp : studentPaperList) {
				PaperDomain paper = paperService.doGetById(sp.getPaperId());
				paperList.add(paper);
			}
		}
		model.addAttribute("paperList", paperList);
		return "/teacherView/paper/paperList";
	}
}
