package com.lab.lsystem.controller.teacher;

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
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.domain.TeacherPaperDomain;
import com.lab.lsystem.service.IPaperService;
import com.lab.lsystem.service.ITeacherPaperService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.lsystem.util.Consts;
import com.lab.system.util.PageInfo;

/**
 * 教师对论文控制层
 * 
 * @author zhu
 * 
 */
@Controller
@RequestMapping("teacher/paper")
public class PaperManageController {
	@Resource
	private IPaperService paperService;
	@Resource
	private ITeacherService teacherService;
	@Resource
	private ITeacherPaperService teacherpaperService;

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
		String workCode = (String) session.getAttribute(Consts.CURRENT_USER);
		// 获取Teacher信息
		TeacherDomain teacherDomain = teacherService
				.doGetTeacherByWorkcode(workCode);
		String teacherId = teacherDomain.getId();
		// 根据教师Id获取教师论文
		List<TeacherPaperDomain> teacherPaperList = teacherpaperService
				.doGetPageListByTeacherId(pageInfo, teacherId);
		List<PaperDomain> paperList = new ArrayList<PaperDomain>();
		if (teacherPaperList.size() > 0) {
			for (TeacherPaperDomain tp : teacherPaperList) {
				PaperDomain paper = paperService.doGetById(tp.getPaperId());
				paperList.add(paper);
			}
		}
		model.addAttribute("paperList", paperList);
		return "/teacherView/paper/paperList";
	}
}
