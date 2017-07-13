package com.lab.lsystem.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lab.lsystem.dao.ITeacherPaperDao;
import com.lab.lsystem.domain.TeacherPaperDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.service.ITeacherPaperService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.system.util.PageInfo;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class TeacherPaperService implements ITeacherPaperService{

	@Resource private ITeacherPaperDao teacherPaperDao;
	
	/**
	 * @see ITeacherService#doGetFilterList()
	 */
	@Override
	public List<TeacherPaperDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TeacherPaperDomain.class);
		List<TeacherPaperDomain> TeacherPaperList=teacherPaperDao.getFilterList(detachedCriteria);
		
		return TeacherPaperList;
	}

	/**
	 * @see ITeacherService#doSave(TeacherDomain)
	 */
	@Override
	public boolean doSave(TeacherPaperDomain TeacherPaper) throws Exception {
		// TODO Auto-generated method stub

		if(TeacherPaper.getId()==null){
			return teacherPaperDao.save(TeacherPaper);
		}else{
			return teacherPaperDao.update(TeacherPaper);
		}
	}

	/**
	 * @see ITeacherService#doGetById(String)
	 */
	@Override
	public TeacherPaperDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return teacherPaperDao.getById(id);
	}

	/**
	 * @see ITeacherService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return teacherPaperDao.deleteById(id);
	}

	
	

	/**
	 * @see ITeacherService#doDeleteByIds(String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		
		boolean b=false;
		for(String id:ids){
			b=teacherPaperDao.deleteById(id);
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
	public List<TeacherPaperDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TeacherPaperDomain.class);
		List<TeacherPaperDomain> TeacherPaperList=teacherPaperDao.getPageList(detachedCriteria, pageInfo);
		
		return TeacherPaperList;
	}

	@Override
	public List<TeacherPaperDomain> doGetByPaperId(String paperId) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TeacherPaperDomain.class);
		detachedCriteria.add(Restrictions.eq("paperId", paperId.trim()));
		List<TeacherPaperDomain> teacherPaperList=teacherPaperDao.getFilterList(detachedCriteria);				
		return teacherPaperList;
	}
}
