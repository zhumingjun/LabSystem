package com.lab.lsystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.lab.lsystem.dao.IPaperAuthorDao;
import com.lab.lsystem.domain.PaperAuthorDomain;
import com.lab.system.dao.bean.BaseDao;

@Repository
public class PaperAuthorDao extends BaseDao<PaperAuthorDomain> implements IPaperAuthorDao{

}
