package com.lab.lsystem.service;

import java.util.List;

import com.lab.lsystem.domain.TeacherDomain;
import com.lab.lsystem.domain.UserDomain;
import com.lab.system.util.PageInfo;

public interface ITeacherService{

		/**
		 * ͨ获取id
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public TeacherDomain doGetById(String id)throws Exception;
		
		/**
		 * 获取用户List
		 * @return
		 * @throws Exception
		 */
		public List<TeacherDomain> doGetFilterList() throws Exception;
		
		
		/**
		 *保存
		 * @param UserDomain
		 * @return
		 * @throws Exception
		 */
		public boolean doSave(TeacherDomain user) throws Exception;
		
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
		public List<TeacherDomain> doGetPageList(PageInfo pageInfo)throws Exception;
		/**
		 * 搜索功能
		 * @param pageInfo
		 * @param searchText
		 * @return
		 */
		public List<TeacherDomain> doSearchteacherPageList(PageInfo pageInfo,
				String searchText)throws Exception;

}
