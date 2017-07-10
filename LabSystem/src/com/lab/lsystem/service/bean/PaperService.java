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
import com.lab.lsystem.dao.ITeacherDao;
import com.lab.lsystem.domain.PaperDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.domain.UserDomain;
import com.lab.lsystem.service.IPaperService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.system.util.PageInfo;
import com.lab.system.util.ValidateUtil;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class PaperService implements IPaperService{

	@Resource private IPaperDao PaperDao;
	
	/**
	 * @see ITeacherService#doGetFilterList()
	 */
	@Override
	public List<PaperDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(PaperDomain.class);
		List<PaperDomain> PaperList=PaperDao.getFilterList(detachedCriteria);
		
		return PaperList;
	}

	/**
	 * @see ITeacherService#doSave(TeacherDomain)
	 */
	@Override
	public boolean doSave(PaperDomain Paper) throws Exception {
		// TODO Auto-generated method stub

		if(Paper.getId()==null){
			return PaperDao.save(Paper);
		}else{
			return PaperDao.update(Paper);
		}
	}

	/**
	 * @see ITeacherService#doGetById(String)
	 */
	@Override
	public PaperDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return PaperDao.getById(id);
	}

	/**
	 * @see ITeacherService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return PaperDao.deleteById(id);
	}

	
	

	/**
	 * @see ITeacherService#doDeleteByIds(String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		
		boolean b=false;
		for(String id:ids){
			b=PaperDao.deleteById(id);
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
		List<PaperDomain> PaperList=PaperDao.getPageList(detachedCriteria, pageInfo);
		
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
		
		return PaperDao.getPageList(detachedCriteria, pageInfo);
	}

	@Override
	public PaperDomain doGetUserByUsername(String name) throws Exception {
		// TODO Auto-generated method stub
				DetachedCriteria detachedCriteria=DetachedCriteria.forClass(PaperDomain.class);
				detachedCriteria.add(Restrictions.eq("name", name.trim()));
				
				List<PaperDomain> PaperList=PaperDao.getFilterList(detachedCriteria);
				
				//如果有结果，username是唯一的
				if(PaperList.size()==1){
					PaperDomain Paper=PaperList.get(0);
					return Paper;
				}
				
				return null;
	}
}
