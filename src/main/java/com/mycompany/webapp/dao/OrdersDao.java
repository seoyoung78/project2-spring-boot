package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.OrderProducts;
import com.mycompany.webapp.dto.Orders;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.ProductsRefund;

@Mapper
public interface OrdersDao {

	public List<Orders> selectByPage(@Param("pager")Pager pager,@Param("keyword")String keyword);
	public int count(String keyword);
	public List<OrderProducts> selectByOrderNo(int orderNo);
//	public Orders selectByOrderNo(int orderNo);
	
	//환불 상테 수정
	public void update(ProductsRefund refund);
	
	
	public int totalcount();
	public int stateCount(int orderState);
	
}
