package com.lab.lsystem.service;

import java.util.List;

import com.lab.system.util.PageInfo;
import com.lab.lsystem.domain.UserDomain;

/**
 * 
 * @author zhu
 *
 */
public interface IUserService{

	/**
	 * ͨ获取id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserDomain doGetById(String id)throws Exception;
	
	/**
	 * 获取用户List
	 * @return
	 * @throws Exception
	 */
	public List<UserDomain> doGetFilterList() throws Exception;
	
	
	/**
	 *保存
	 * @param UserDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(UserDomain user) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteById(String id) throws Exception;
	
	/**
	 * 删除多个
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteByIds(String[] ids)throws Exception;
	
	/**
	 * 根据用户名获取用户实体
	 */
	public UserDomain doGetUserByUsername(String username)throws Exception;
	/**
	 * 验证用户名密码，如果通过，返回User，不通过，返回null
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean doCheckUserPassword(String username,char[] password)throws Exception;
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public List<UserDomain> doGetPageList(PageInfo pageInfo)throws Exception;

	/**
	 * 根据查询条件返回用户列表
	 * @param pageInfo 分页
	 * @param authority 权限
	 * @param searchText 关键字
	 * @return
	 * @throws Exception
	 */
	public List<UserDomain> doSearchUserPageList(PageInfo pageInfo,String roleId,String searchText)throws Exception;

}
