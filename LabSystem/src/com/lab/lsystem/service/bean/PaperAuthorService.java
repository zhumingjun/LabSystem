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

import com.lab.lsystem.dao.IPaperAuthorDao;
import com.lab.lsystem.dao.IStudentDao;
import com.lab.lsystem.dao.ITeacherDao;
import com.lab.lsystem.domain.PaperAuthorDomain;
import com.lab.lsystem.domain.StudentDomain;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.service.IPaperAuthorService;
import com.lab.lsystem.service.ITeacherService;
import com.lab.system.util.PageInfo;
import com.lab.system.util.ValidateUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PaperAuthorService implements IPaperAuthorService {

	@Resource
	private IPaperAuthorDao paperAuthorDao;
	@Resource
	private IStudentDao studentDao;
	@Resource
	private ITeacherDao teacherDao;

	/**
	 * @see ITeacherService#doGetFilterList()
	 */
	@Override
	public List<PaperAuthorDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(PaperAuthorDomain.class);
		List<PaperAuthorDomain> PaperAuthorList = paperAuthorDao
				.getFilterList(detachedCriteria);

		return PaperAuthorList;
	}

	/**
	 * @see ITeacherService#doSave(TeacherDomain)
	 */
	@Override
	public boolean doSave(PaperAuthorDomain PaperAuthor) throws Exception {
		// TODO Auto-generated method stub

		if (PaperAuthor.getId() == null) {
			return paperAuthorDao.save(PaperAuthor);
		} else {
			return paperAuthorDao.update(PaperAuthor);
		}
	}

	/**
	 * @see ITeacherService#doGetById(String)
	 */
	@Override
	public PaperAuthorDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub

		return paperAuthorDao.getById(id);
	}

	/**
	 * @see ITeacherService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub

		return paperAuthorDao.deleteById(id);
	}

	/**
	 * @see ITeacherService#doDeleteByIds(String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] ids) throws Exception {
		// TODO Auto-generated method stub

		boolean b = false;
		for (String id : ids) {
			b = paperAuthorDao.deleteById(id);
			if (!b) {
				return false;
			}
		}

		return b;
	}

	/**
	 * @see ITeacherService#doGetPageList(DetachedCriteria, PageInfo)
	 */
	@Override
	public List<PaperAuthorDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(PaperAuthorDomain.class);
		List<PaperAuthorDomain> PaperAuthorList = paperAuthorDao.getPageList(
				detachedCriteria, pageInfo);

		return PaperAuthorList;
	}

	/**
	 * @see ITeacherService#doSearchteacherPageList(PageInfo pageInfo,String
	 *      searchText);
	 */
	@Override
	public List<PaperAuthorDomain> doSearchPaperAuthorPageList(
			PageInfo pageInfo, String searchText) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(PaperAuthorDomain.class);
		if (ValidateUtil.notEmpty(searchText)) {
			// 多条件过滤，此处名字，宿舍，籍贯
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("title", searchText,
					MatchMode.ANYWHERE).ignoreCase());
			detachedCriteria.add(disjunction);
		}

		return paperAuthorDao.getPageList(detachedCriteria, pageInfo);
	}

	@Override
	public PaperAuthorDomain doGetUserByUsername(String name) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(PaperAuthorDomain.class);
		detachedCriteria.add(Restrictions.eq("name", name.trim()));

		List<PaperAuthorDomain> PaperAuthorList = paperAuthorDao
				.getFilterList(detachedCriteria);

		// 如果有结果，username是唯一的
		if (PaperAuthorList.size() == 1) {
			PaperAuthorDomain PaperAuthor = PaperAuthorList.get(0);
			return PaperAuthor;
		}

		return null;
	}

	@Override
	public String doGetNameById(String userId) throws Exception {
		// TODO Auto-generated method stub
		StudentDomain student = studentDao.getById(userId);
		TeacherDomain teacher = teacherDao.getById(userId);
		if (student != null) {
			return student.getName();
		} else if (teacher != null) {
			return teacher.getName();
		} else {
			return null;
		}
	}

	@Override
	public List<PaperAuthorDomain> doGetPageListByPaperId(PageInfo pageInfo,
			String id) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(PaperAuthorDomain.class);
		detachedCriteria.add(Restrictions.eq("paperId", id.trim()));

		List<PaperAuthorDomain> paperAuthorList = paperAuthorDao
				.getFilterList(detachedCriteria);
		return paperAuthorList;

	}
}
