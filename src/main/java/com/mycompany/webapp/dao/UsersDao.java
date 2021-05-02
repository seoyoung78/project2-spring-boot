package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Users;

@Mapper
public interface UsersDao {
	
	public int count();
	public List<Users> selectByPage(Pager pager);
	public int stateCount(@Param("deleteState")int userState);
	public List<Users> selectByStatePage(@Param("pager")Pager pager, @Param("deleteState")int userState);
	public List<Users> selectByUserPage(@Param("pager")Pager pager, @Param("keyword")String idKeywordVal);
	public int stateKeywordCount(@Param("deleteState")int userState, @Param("keyword")String idKeywordVal);
	public List<Users> selectByStateKeywordPage(@Param("pager")Pager pager,@Param("deleteState") int userState,@Param("keyword") String idKeywordVal);
	public int keywordCount(@Param("keyword")String idKeywordVal);
	public Users selectByUserid(String userId); 
	public int updateUser(@Param("user")Users user, @Param("userState")int userState);
}
