package com.lab.lsystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.lab.lsystem.dao.IDiscussionDao;
import com.lab.lsystem.domain.DiscussionDomain;
import com.lab.system.dao.bean.BaseDao;

@Repository
public class DiscussionDao extends BaseDao<DiscussionDomain> implements IDiscussionDao{

}
