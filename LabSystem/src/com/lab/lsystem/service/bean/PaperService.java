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

import com.lab.lsystem.dao.IPaperDao;
import com.lab.lsystem.dao.IStudentDao;
import com.lab.lsystem.dao.ITeacherDao;
import com.lab.lsystem.domain.PaperDomain;
import com.lab.lsystem.domain.StudentDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.service.IPaperService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.system.util.PageInfo;
import com.lab.system.util.ValidateUtil;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class PaperService implements IPaperService{

	@Resource private IPaperDao paperDao;
	@Resource private IStudentDao studentDao;
	@Resource private ITeacherDao teacherDao;
	/**
	 * @see ITeacherService#doGetFilterList()
	 */
	@Override
	public List<PaperDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(PaperDomain.class);
		List<PaperDomain> PaperList=paperDao.getFilterList(detachedCriteria);
		
		return PaperList;
	}

	/**
	 * @see ITeacherService#doSave(TeacherDomain)
	 */
	@Override
	public boolean doSave(PaperDomain Paper) throws Exception {
		// TODO Auto-generated method stub

		if(Paper.getId()==null){
			return paperDao.save(Paper);
		}else{
			return paperDao.update(Paper);
		}
	}

	/**
	 * @see ITeacherService#doGetById(String)
	 */
	@Override
	public PaperDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return paperDao.getById(id);
	}

	/**
	 * @see ITeacherService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return paperDao.deleteById(id);
	}

	
	

	/**
	 * @see ITeacherService#doDeleteByIds(String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		
		boolean b=false;
		for(String id:ids){
			b=paperDao.deleteById(id);
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
	public List<PaperDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(PaperDomain.class);
		List<PaperDomain> PaperList=paperDao.getPageList(detachedCriteria, pageInfo);
		
		return PaperList;
	}
	/**
	 * @see ITeacherService#doSearchteacherPageList(PageInfo pageInfo,String searchText);
	 */
	@Override
	public List<PaperDomain> doSearchPaperPageList(PageInfo pageInfo,
			String searchText) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(PaperDomain.class);
		if(ValidateUtil.notEmpty(searchText)){
			//多条件过滤，此处名字，宿舍，籍贯
			Disjunction disjunction = Restrictions.disjunction();  
			disjunction.add(Restrictions.like("name", searchText,MatchMode.ANYWHERE).ignoreCase());  	              
			detachedCriteria.add(disjunction);  
		}
		
		return paperDao.getPageList(detachedCriteria, pageInfo);
	}

	@Override
	public PaperDomain doGetUserByUsername(String name) throws Exception {
		// TODO Auto-generated method stub
				DetachedCriteria detachedCriteria=DetachedCriteria.forClass(PaperDomain.class);
				detachedCriteria.add(Restrictions.eq("name", name.trim()));
				
				List<PaperDomain> PaperList=paperDao.getFilterList(detachedCriteria);
				
				//如果有结果，username是唯一的
				if(PaperList.size()==1){
					PaperDomain Paper=PaperList.get(0);
					return Paper;
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
