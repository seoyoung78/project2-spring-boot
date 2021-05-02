package com.mycompany.webapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.OrdersDao;
import com.mycompany.webapp.dto.OrderProducts;
import com.mycompany.webapp.dto.Orders;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.ProductsRefund;


@Service
public class OrdersService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersService.class);
		
	@Autowired
	private OrdersDao ordersDao;

	/*주문리스트 페이징처리*/
	public List<Orders> getOrdersPage(Pager pager, String keyword) {
		List<Orders> list = ordersDao.selectByPage(pager,keyword);
		return list;
	}
	
	public int getTotalRows(String keyword) {
		int rows = ordersDao.count(keyword);
		return rows;
	}
	
	public List<OrderProducts> getOrders(int orderNo) {
		List<OrderProducts> list = ordersDao.selectByOrderNo(orderNo);
		return list;
	}

	public void updateRefund(ProductsRefund refund) {
		ordersDao.update(refund);
	}

	public int getTotalCount() {
		int result = ordersDao.totalcount();
		return result;
	}

	public int getTotalStateCount(int orderState) {
		int result = ordersDao.stateCount(orderState);
		return result;
	}

	
	
}
