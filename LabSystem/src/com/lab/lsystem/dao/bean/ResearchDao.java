package com.lab.lsystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.lab.lsystem.dao.IResearchDao;
import com.lab.lsystem.domain.ResearchDomain;
import com.lab.system.dao.bean.BaseDao;

@Repository
public class ResearchDao extends BaseDao<ResearchDomain> implements IResearchDao{

}
