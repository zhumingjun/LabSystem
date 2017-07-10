package com.lab.lsystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.lab.lsystem.dao.IPaperDao;
import com.lab.lsystem.domain.PaperDomain;
import com.lab.system.dao.bean.BaseDao;

@Repository
public class PaperDao extends BaseDao<PaperDomain> implements IPaperDao{

}
