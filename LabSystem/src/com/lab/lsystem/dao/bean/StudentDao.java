package com.lab.lsystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.lab.lsystem.dao.IStudentDao;
import com.lab.lsystem.domain.StudentDomain;
import com.lab.system.dao.bean.BaseDao;

@Repository
public class StudentDao extends BaseDao<StudentDomain> implements IStudentDao{

}
