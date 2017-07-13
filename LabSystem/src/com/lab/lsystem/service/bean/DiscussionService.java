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
import com.lab.lsystem.dao.IDiscussionDao;
import com.lab.lsystem.domain.DiscussionDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.service.IDiscussionService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.system.util.PageInfo;
import com.lab.system.util.ValidateUtil;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class DiscussionService implements IDiscussionService{

	@Resource private IDiscussionDao discussionDao;
	
	/**
	 * @see ITeacherService#doGetFilterList()
	 */
	@Override
	public List<DiscussionDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(DiscussionDomain.class);
		List<DiscussionDomain> DiscussionList=discussionDao.getFilterList(detachedCriteria);
		
		return DiscussionList;
	}

	/**
	 * @see ITeacherService#doSave(TeacherDomain)
	 */
	@Override
	public boolean doSave(DiscussionDomain Discussion) throws Exception {
		// TODO Auto-generated method stub

		if(Discussion.getId()==null){
			return discussionDao.save(Discussion);
		}else{
			return discussionDao.update(Discussion);
		}
	}

	/**
	 * @see ITeacherService#doGetById(String)
	 */
	@Override
	public DiscussionDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return discussionDao.getById(id);
	}

	/**
	 * @see ITeacherService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return discussionDao.deleteById(id);
	}

	
	

	/**
	 * @see ITeacherService#doDeleteByIds(String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		
		boolean b=false;
		for(String id:ids){
			b=discussionDao.deleteById(id);
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
	public List<DiscussionDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(DiscussionDomain.class);
		List<DiscussionDomain> DiscussionList=discussionDao.getPageList(detachedCriteria, pageInfo);
		
		return DiscussionList;
	}
	/**
	 * @see ITeacherService#doSearchteacherPageList(PageInfo pageInfo,String searchText);
	 */
	@Override
	public List<DiscussionDomain> doSearchDiscussionPageList(PageInfo pageInfo,
			String searchText) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(DiscussionDomain.class);
		if(ValidateUtil.notEmpty(searchText)){
			//多条件过滤，此处名字，宿舍，籍贯
			Disjunction disjunction = Restrictions.disjunction();  
			disjunction.add(Restrictions.like("name", searchText,MatchMode.ANYWHERE).ignoreCase());  	              
			detachedCriteria.add(disjunction);  
		}
		
		return discussionDao.getPageList(detachedCriteria, pageInfo);
	}

	@Override
	public DiscussionDomain doGetUserByUsername(String name) throws Exception {
		// TODO Auto-generated method stub
				DetachedCriteria detachedCriteria=DetachedCriteria.forClass(DiscussionDomain.class);
				detachedCriteria.add(Restrictions.eq("name", name.trim()));
				
				List<DiscussionDomain> DiscussionList=discussionDao.getFilterList(detachedCriteria);
				
				//如果有结果，username是唯一的
				if(DiscussionList.size()==1){
					DiscussionDomain Discussion=DiscussionList.get(0);
					return Discussion;
				}
				
				return null;
	}
}
