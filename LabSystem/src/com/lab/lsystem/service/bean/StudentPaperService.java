package com.lab.lsystem.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lab.lsystem.dao.IStudentPaperDao;
import com.lab.lsystem.domain.StudentPaperDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.service.IStudentPaperService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.system.util.PageInfo;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class StudentPaperService implements IStudentPaperService{

	@Resource private IStudentPaperDao StudentPaperDao;
	
	/**
	 * @see ITeacherService#doGetFilterList()
	 */
	@Override
	public List<StudentPaperDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(StudentPaperDomain.class);
		List<StudentPaperDomain> StudentPaperList=StudentPaperDao.getFilterList(detachedCriteria);
		
		return StudentPaperList;
	}

	/**
	 * @see ITeacherService#doSave(TeacherDomain)
	 */
	@Override
	public boolean doSave(StudentPaperDomain StudentPaper) throws Exception {
		// TODO Auto-generated method stub

		if(StudentPaper.getId()==null){
			return StudentPaperDao.save(StudentPaper);
		}else{
			return StudentPaperDao.update(StudentPaper);
		}
	}

	/**
	 * @see ITeacherService#doGetById(String)
	 */
	@Override
	public StudentPaperDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return StudentPaperDao.getById(id);
	}

	/**
	 * @see ITeacherService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return StudentPaperDao.deleteById(id);
	}

	
	

	/**
	 * @see ITeacherService#doDeleteByIds(String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		
		boolean b=false;
		for(String id:ids){
			b=StudentPaperDao.deleteById(id);
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
	public List<StudentPaperDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(StudentPaperDomain.class);
		List<StudentPaperDomain> StudentPaperList=StudentPaperDao.getPageList(detachedCriteria, pageInfo);
		
		return StudentPaperList;
	}
}
