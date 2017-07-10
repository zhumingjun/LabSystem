package com.lab.lsystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.lab.lsystem.dao.ITeacherPaperDao;
import com.lab.lsystem.domain.TeacherPaperDomain;
import com.lab.system.dao.bean.BaseDao;

@Repository
public class TeacherPaperDao extends BaseDao<TeacherPaperDomain> implements ITeacherPaperDao{

}
