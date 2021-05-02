package com.mycompany.webapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.CategoryDao;
import com.mycompany.webapp.dao.ProductsDao;
import com.mycompany.webapp.dao.ProductsImgDao;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Products;
import com.mycompany.webapp.dto.ProductsImg;

@Service
public class ProductsService {
	private static final Logger logger =
			LoggerFactory.getLogger(ProductsService.class);

	@Autowired
	private ProductsDao productsDao;

	@Autowired
	private ProductsImgDao productsImgDao;

	//insert
	public int insert(Products product) {
		int result = productsDao.insert(product);
		return result;
	}	
	public int insertImg(Products product) {
		int result = productsImgDao.insert(product);
		return result;
	}

	//update
	public int update(Products product) {
		int result = productsDao.update(product);
		return result;
	}
	public int updateImg(Products product) {
		int result = productsImgDao.update(product);
		return result;
	}

	//전체 카운트, 상품 목록
	public int getCount() {
		int result = productsDao.totalCount();
		return result;
	}
	public List<Products> getProductsAllList(Pager pager) {
		List<Products> list = productsDao.selectByPageAll(pager);
		return list;
	}

	//카테고리 별 카운트, 상품 목록 
	public int getTotalCount(int cno) {
		int total = productsDao.count(cno);
		return total;
	}
	public List<Products> getProductsList(Pager pager, int cno) {
		List<Products> list = productsDao.selectByPage(pager, cno);
		return list;
	}

	//키워드 별 카운트, 상품 목록
	public int getTotalRows(String keyword) {
		int rows = productsDao.countKeyword(keyword);
		return rows;
	}
	public List<Products> getProductsSearchListPager(Pager pager,String keyword){
		List<Products> list = productsDao.selectBySearchPage(pager,keyword);
		return list;
	}

	//키워드+카테고리 별 카운트, 상품 목록
	public int getTotalCategoryKeywordCount(int categoryNo, String keyword) {
		int result = productsDao.countCategoryKeyword(categoryNo, keyword);
		return result;
	}
	public List<Products> getProductCategoryKeyword(Pager pager, int categoryNo, String keyword) {
		List<Products> list = productsDao.selectByCategorySearchPage(pager, categoryNo, keyword);
		return list;
	}

	//상품 상세
	public List<Products> getProductDetail(int productNo) {
		List<Products> list = productsDao.selectByPno(productNo);
		return list;
	}
	
	//사진 가져오기
	public List<ProductsImg> getImgList(int pid) {
		List<ProductsImg> list =  productsImgDao.selectByPnoImg(pid);
		return list;
	}


	
	
	public List<Products> getBestProductList() {
		List<Products> list = productsDao.selectBestProduct();		
		return list;
	}

	public List<Products> getNewProductList() {
		List<Products> list = productsDao.selectNewProduct();
		return list;
	}











}
