package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.OrderProducts;
import com.mycompany.webapp.dto.Reviews;

@Mapper
public interface OrderProductsDao {

	public void insert(OrderProducts orderProducts);
	public List<OrderProducts> selectDetail(@Param("orderNo") int orderNo, @Param("userId") String userId);
	
	//리뷰 작성 상태 수정
	public void update(Reviews review);
	//리뷰 개수 리턴
	public int count(int state);
}
