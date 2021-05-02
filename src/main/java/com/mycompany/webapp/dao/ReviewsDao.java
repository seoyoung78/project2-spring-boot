package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Reviews;

@Mapper
public interface ReviewsDao {
	
	public int count(String keyword);	
	public List<Reviews> selectByPage(@Param("pager") Pager pager, @Param("keyword") String keyword);
	public Reviews selectByRno(int ReviewtNo);
	public void update(Reviews review);
	public void deleteReview(int reviewNo);
	public int countKeyword(String keyword);
}
