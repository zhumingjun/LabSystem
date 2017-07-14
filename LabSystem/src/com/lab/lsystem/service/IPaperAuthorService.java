package com.lab.lsystem.service;

import java.util.List;

import com.lab.lsystem.domain.PaperAuthorDomain;
import com.lab.system.util.PageInfo;

public interface IPaperAuthorService{

		/**
		 * ͨ获取id
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public PaperAuthorDomain doGetById(String id)throws Exception;
		
		/**
		 * 获取用户List
		 * @return
		 * @throws Exception
		 */
		public List<PaperAuthorDomain> doGetFilterList() throws Exception;
		
		
		/**
		 *保存
		 * @param UserDomain
		 * @return
		 * @throws Exception
		 */
		public boolean doSave(PaperAuthorDomain PaperAuthor) throws Exception;
		
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
		public List<PaperAuthorDomain> doGetPageList(PageInfo pageInfo)throws Exception;
		/**
		 * 搜索功能
		 * @param pageInfo
		 * @param searchText
		 * @return
		 */
		public List<PaperAuthorDomain> doSearchPaperAuthorPageList(PageInfo pageInfo,
				String searchText)throws Exception;
		/*
		 * 根据姓名查找学生信息
		 */
		public PaperAuthorDomain doGetUserByUsername(String name)throws Exception;
		/**
		 * 根据用户id获取用户姓名
		 * @param userId
		 * @return
		 * @throws Exception
		 */
		public String doGetNameById(String userId)throws Exception;
		/**
		 * 根据论文id获取论文作者信息
		 * @param pageInfo
		 * @param id
		 * @return
		 */
		public List<PaperAuthorDomain> doGetPageListByPaperId(
				PageInfo pageInfo, String id)throws Exception;

}
