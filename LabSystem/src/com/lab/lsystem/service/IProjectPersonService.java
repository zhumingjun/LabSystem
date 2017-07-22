package com.lab.lsystem.service;

import java.util.List;

import com.lab.lsystem.domain.ProjectPersonDomain;
import com.lab.system.util.PageInfo;

public interface IProjectPersonService{

		/**
		 * ͨ获取id
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public ProjectPersonDomain doGetById(String id)throws Exception;
		
		/**
		 * 获取用户List
		 * @return
		 * @throws Exception
		 */
		public List<ProjectPersonDomain> doGetFilterList() throws Exception;
		
		
		/**
		 *保存
		 * @param UserDomain
		 * @return
		 * @throws Exception
		 */
		public boolean doSave(ProjectPersonDomain ProjectPerson) throws Exception;
		
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
		 * 分页查询
		 * @return
		 * @throws Exception
		 */
		public List<ProjectPersonDomain> doGetPageList(PageInfo pageInfo)throws Exception;
		/**
		 * 搜索功能
		 * @param pageInfo
		 * @param searchText
		 * @return
		 */
		public List<ProjectPersonDomain> doSearchProjectPersonPageList(PageInfo pageInfo,
				String searchText)throws Exception;
		/*
		 * 根据姓名查找学生信息
		 */
		public ProjectPersonDomain doGetUserByUsername(String name)throws Exception;
		/**
		 * 根据用户id获取用户姓名
		 * @param userId
		 * @return
		 * @throws Exception
		 */
		public String doGetNameById(String userId)throws Exception;

}
