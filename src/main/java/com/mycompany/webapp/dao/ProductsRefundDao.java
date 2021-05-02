package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.ProductsRefund;

@Mapper
public interface ProductsRefundDao {
	public int count(@Param("stateval") int stateval, @Param("reason") String reason);
	public List<ProductsRefund> selectByPage(@Param("pager") Pager pager, @Param("stateval") int stateval, @Param("reason") String reason);
	public ProductsRefund selectByOno(int orderNo);
	public void update(ProductsRefund refund);
}