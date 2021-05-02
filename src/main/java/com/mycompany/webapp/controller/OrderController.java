package com.mycompany.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.OrderProducts;
import com.mycompany.webapp.dto.Orders;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrdersService ordersService;
	
	/*
	 * @GetMapping("") public Map<String, Object> list(@RequestParam(defaultValue =
	 * "1") int pageNo){ int totalRows = ordersService.getTotalRows(); Pager pager =
	 * new Pager(5,5,totalRows, pageNo); List<Orders> list =
	 * ordersService.getOrdersPage(pager); Map<String, Object> map = new
	 * HashMap<>(); map.put("pager", pager); map.put("orders", list); //order in
	 * orders return map;
	 * 
	 * }
	 */
	
	@GetMapping("/keyword")
	public Map<String, Object> list(@RequestParam(defaultValue="") String keyword,@RequestParam(defaultValue = "1") int pageNo){
		int totalRows = ordersService.getTotalRows(keyword);
		Pager pager = new Pager(10,5,totalRows, pageNo);
		List<Orders> list = ordersService.getOrdersPage(pager,keyword);
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("orders", list); //order in orders
		return map;
		
	}

	@GetMapping("/{orderNo}")
	public List<OrderProducts> read(@PathVariable int orderNo) {
		List<OrderProducts> list = ordersService.getOrders(orderNo);
		return list;
	}
	
	@GetMapping("/readCount")
	public String readCount(int countNo) {
		String result;
		if(countNo == 0) {
			result = String.valueOf(ordersService.getTotalCount());
		}else if(countNo == 1){
			result = String.valueOf(ordersService.getTotalStateCount(0));
		}else {
			result = String.valueOf(ordersService.getTotalStateCount(1));
		}
		return result;
	}
	
}
