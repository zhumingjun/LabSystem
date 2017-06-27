package com.lab.lsystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.lab.lsystem.dao.IUserDao;
import com.lab.lsystem.domain.UserDomain;
import com.lab.system.dao.bean.BaseDao;

@Repository
public class UserDao extends BaseDao<UserDomain> implements IUserDao{

}
