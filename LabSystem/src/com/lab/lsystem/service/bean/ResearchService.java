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
import com.lab.lsystem.dao.IResearchDao;
import com.lab.lsystem.domain.ResearchDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.service.IResearchService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.system.util.PageInfo;
import com.lab.system.util.ValidateUtil;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class ResearchService implements IResearchService{

	@Resource private IResearchDao researchDao;
	
	/**
	 * @see ITeacherService#doGetFilterList()
	 */
	@Override
	public List<ResearchDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ResearchDomain.class);
		List<ResearchDomain> ResearchList=researchDao.getFilterList(detachedCriteria);
		
		return ResearchList;
	}

	/**
	 * @see ITeacherService#doSave(TeacherDomain)
	 */
	@Override
	public boolean doSave(ResearchDomain Research) throws Exception {
		// TODO Auto-generated method stub

		if(Research.getId()==null){
			return researchDao.save(Research);
		}else{
			return researchDao.update(Research);
		}
	}

	/**
	 * @see ITeacherService#doGetById(String)
	 */
	@Override
	public ResearchDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return researchDao.getById(id);
	}

	/**
	 * @see ITeacherService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return researchDao.deleteById(id);
	}

	
	

	/**
	 * @see ITeacherService#doDeleteByIds(String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		
		boolean b=false;
		for(String id:ids){
			b=researchDao.deleteById(id);
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
	public List<ResearchDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ResearchDomain.class);
		List<ResearchDomain> ResearchList=researchDao.getPageList(detachedCriteria, pageInfo);
		
		return ResearchList;
	}
	/**
	 * @see ITeacherService#doSearchteacherPageList(PageInfo pageInfo,String searchText);
	 */
	@Override
	public List<ResearchDomain> doSearchResearchPageList(PageInfo pageInfo,
			String searchText) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ResearchDomain.class);
		if(ValidateUtil.notEmpty(searchText)){
			//多条件过滤，此处名字，宿舍，籍贯
			Disjunction disjunction = Restrictions.disjunction();  
			disjunction.add(Restrictions.like("name", searchText,MatchMode.ANYWHERE).ignoreCase());  	              
			detachedCriteria.add(disjunction);  
		}
		
		return researchDao.getPageList(detachedCriteria, pageInfo);
	}

	@Override
	public ResearchDomain doGetUserByUsername(String name) throws Exception {
		// TODO Auto-generated method stub
				DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ResearchDomain.class);
				detachedCriteria.add(Restrictions.eq("name", name.trim()));
				
				List<ResearchDomain> ResearchList=researchDao.getFilterList(detachedCriteria);
				
				//如果有结果，username是唯一的
				if(ResearchList.size()==1){
					ResearchDomain Research=ResearchList.get(0);
					return Research;
				}
				
				return null;
	}
}
