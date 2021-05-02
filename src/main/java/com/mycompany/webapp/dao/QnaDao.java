package com.mycompany.webapp.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Qna;



@Mapper
public interface QnaDao{
	
	
//	public List<Qna> selectByPage(@Param("pager")Pager pager,@Param("keyword")String keyword);
//	public int count(String keyword);

	public List<Qna> selectStatevalByPage(@Param("pager")Pager pager,@Param("stateval")int stateval);

	public int statevalCount(int stateval);

	public Qna selectByQnaNo(int qnaNo);

	public int insert(Qna qna);

	public int deleteByQnaNo(int qnaNo);

	public int update(Qna qna);
	
	public int totalcount();
	
	public int stateCount(int answerState);
	
}