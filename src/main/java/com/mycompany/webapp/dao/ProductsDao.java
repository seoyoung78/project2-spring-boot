package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Products;

@Mapper
public interface ProductsDao{

	public int totalCount();
	public int count(int cno);
	public List<Products> selectByPage(@Param("pager") Pager pager, @Param("cno") int cno);
	public List<Products> selectByPageAll(Pager pager);
	public int insert(Products product);
	public List<Products> selectByPno(int productNo);
	public List<Products> selectBestProduct();
	public List<Products> selectNewProduct();
	public int countKeyword(String keyword);
	public List<Products> selectBySearchPage(@Param("pager")Pager pager,@Param("keyword")String keyword);
	public List<Products> selectByCategorySearchPage(@Param("pager")Pager pager,@Param("categoryNo")int categoryNo, @Param("keyword")String keyword);
	public int countCategoryKeyword(@Param("categoryNo")int categoryNo, @Param("keyword")String keyword);	
	public int update(Products product);
}
