package com.lab.lsystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.lab.lsystem.dao.IProjectPersonDao;
import com.lab.lsystem.domain.ProjectPersonDomain;
import com.lab.system.dao.bean.BaseDao;

@Repository
public class ProjectPersonDao extends BaseDao<ProjectPersonDomain> implements IProjectPersonDao{

}
