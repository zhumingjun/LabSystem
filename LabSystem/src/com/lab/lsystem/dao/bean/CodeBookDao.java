package com.lab.lsystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.lab.lsystem.dao.ICodeBookDao;
import com.lab.lsystem.domain.CodeBookDomain;
import com.lab.system.dao.bean.BaseDao;

/**
 * CodeBook
 * @author chen
 *
 */
@Repository
public class CodeBookDao extends BaseDao<CodeBookDomain> implements ICodeBookDao{

}
