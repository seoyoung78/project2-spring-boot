package com.mycompany.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.ProductsRefundDao;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.ProductsRefund;

@Service
public class ProductsRefundService {
	@Autowired
	private ProductsRefundDao refundDao;
	
	public int getCount(int stateval, String reason) {
		return refundDao.count(stateval, reason);
	}

	public List<ProductsRefund> getList(Pager pager, int stateval, String reason) {
		return refundDao.selectByPage(pager, stateval, reason);
	}

	public ProductsRefund getRefund(int orderNo) {
		return refundDao.selectByOno(orderNo);
	}

	public void update(ProductsRefund refund) {
		refundDao.update(refund);
	}
}
