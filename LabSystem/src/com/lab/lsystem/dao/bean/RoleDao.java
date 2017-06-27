package com.lab.lsystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.lab.lsystem.dao.IRoleDao;
import com.lab.lsystem.domain.RoleDomain;
import com.lab.system.dao.bean.BaseDao;

@Repository
public class RoleDao extends BaseDao<RoleDomain> implements IRoleDao{

}
