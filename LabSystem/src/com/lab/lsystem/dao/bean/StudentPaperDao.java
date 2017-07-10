package com.lab.lsystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.lab.lsystem.dao.IStudentPaperDao;
import com.lab.lsystem.domain.StudentPaperDomain;
import com.lab.system.dao.bean.BaseDao;

@Repository
public class StudentPaperDao extends BaseDao<StudentPaperDomain> implements IStudentPaperDao{

}
