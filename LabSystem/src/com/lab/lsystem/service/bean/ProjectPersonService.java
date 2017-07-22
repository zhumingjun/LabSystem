package com.lab.lsystem.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lab.lsystem.dao.IProjectPersonDao;
import com.lab.lsystem.dao.IStudentDao;
import com.lab.lsystem.dao.ITeacherDao;
import com.lab.lsystem.domain.ProjectPersonDomain;
import com.lab.lsystem.domain.StudentDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.service.IProjectPersonService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.system.util.PageInfo;
import com.lab.system.util.ValidateUtil;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class ProjectPersonService implements IProjectPersonService{

	@Resource private IProjectPersonDao projectPersonDao;
	@Resource private IStudentDao studentDao;
	@Resource private ITeacherDao teacherDao;
	/**
	 * @see ITeacherService#doGetFilterList()
	 */
	@Override
	public List<ProjectPersonDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ProjectPersonDomain.class);
		List<ProjectPersonDomain> ProjectPersonList=projectPersonDao.getFilterList(detachedCriteria);
		
		return ProjectPersonList;
	}

	/**
	 * @see ITeacherService#doSave(TeacherDomain)
	 */
	@Override
	public boolean doSave(ProjectPersonDomain ProjectPerson) throws Exception {
		// TODO Auto-generated method stub

		if(ProjectPerson.getId()==null){
			return projectPersonDao.save(ProjectPerson);
		}else{
			return projectPersonDao.update(ProjectPerson);
		}
	}

	/**
	 * @see ITeacherService#doGetById(String)
	 */
	@Override
	public ProjectPersonDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return projectPersonDao.getById(id);
	}

	/**
	 * @see ITeacherService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return projectPersonDao.deleteById(id);
	}

	
	

	/**
	 * @see ITeacherService#doDeleteByIds(String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		
		boolean b=false;
		for(String id:ids){
			b=projectPersonDao.deleteById(id);
			if(!b){
				return false;
			}
		}
		
		return b;
	}
	/**
	 * @see ITeacherService#doGetPageList(DetachedCriteria, PageInfo)
	 */
	@Override
	public List<ProjectPersonDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ProjectPersonDomain.class);
		List<ProjectPersonDomain> ProjectPersonList=projectPersonDao.getPageList(detachedCriteria, pageInfo);
		
		return ProjectPersonList;
	}
	/**
	 * @see ITeacherService#doSearchteacherPageList(PageInfo pageInfo,String searchText);
	 */
	@Override
	public List<ProjectPersonDomain> doSearchProjectPersonPageList(PageInfo pageInfo,
			String searchText) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ProjectPersonDomain.class);
		if(ValidateUtil.notEmpty(searchText)){
			//多条件过滤，此处名字，宿舍，籍贯
			Disjunction disjunction = Restrictions.disjunction();  
			disjunction.add(Restrictions.like("title", searchText,MatchMode.ANYWHERE).ignoreCase());  	              
			detachedCriteria.add(disjunction);  
		}
		
		return projectPersonDao.getPageList(detachedCriteria, pageInfo);
	}

	@Override
	public ProjectPersonDomain doGetUserByUsername(String name) throws Exception {
		// TODO Auto-generated method stub
				DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ProjectPersonDomain.class);
				detachedCriteria.add(Restrictions.eq("name", name.trim()));
				
				List<ProjectPersonDomain> ProjectPersonList=projectPersonDao.getFilterList(detachedCriteria);
				
				//如果有结果，username是唯一的
				if(ProjectPersonList.size()==1){
					ProjectPersonDomain ProjectPerson=ProjectPersonList.get(0);
					return ProjectPerson;
				}
				
				return null;
	}

	@Override
	public String doGetNameById(String userId) throws Exception {
		// TODO Auto-generated method stub
		StudentDomain student=studentDao.getById(userId);
		TeacherDomain teacher=teacherDao.getById(userId);
		if(student!=null){
			return student.getName();
		}else if(teacher!=null){
			return teacher.getName();
		}else{
			return null;
		}
	}
}
