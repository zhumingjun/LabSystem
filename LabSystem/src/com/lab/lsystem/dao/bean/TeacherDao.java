package com.lab.lsystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.lab.lsystem.dao.ITeacherDao;
import com.lab.lsystem.domain.TeacherDomain;
import com.lab.system.dao.bean.BaseDao;

@Repository
public class TeacherDao extends BaseDao<TeacherDomain> implements ITeacherDao{

}
