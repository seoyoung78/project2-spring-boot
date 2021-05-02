package com.mycompany.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.ProductsRefund;
import com.mycompany.webapp.service.OrdersService;
import com.mycompany.webapp.service.ProductsRefundService;

@RestController
@RequestMapping("/products_refund")
public class ProductsRefundController {
	private final Logger logger = LoggerFactory.getLogger(ProductsRefundController.class);
	
	@Autowired
	private ProductsRefundService refundService;
	@Autowired
	private OrdersService ordersService;
	
	//환불 목록
	@GetMapping("")
	private Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue ="") String state, @RequestParam(defaultValue="") String reason) {
		int stateval =2;
		
		if (state.equals("환불 중")) {
			stateval = 0;
		} else if (state.equals("환불 완료")) {
			stateval = 1;
		}
		
		int totalRows = refundService.getCount(stateval, reason);
		Pager pager = new Pager(10, 5, totalRows, pageNo);
		List<ProductsRefund> list = refundService.getList(pager, stateval, reason);
		
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("refunds", list);
		return map;
	}
	
	//환불 개수 리턴
	@GetMapping("/readcount")
	public int readCount(int countNo) {
		int result;
		if(countNo == 0) {
			result = refundService.getCount(2, "전체");
		} else if(countNo == 1) {
			result = refundService.getCount(0, "전체");
		}  else {
			result = refundService.getCount(1, "전체");
		}		
		return result;
	}

	//환불 조회
	@GetMapping("/{orderNo}")
    public ProductsRefund read(@PathVariable int orderNo) {	
		ProductsRefund refund= refundService.getRefund(orderNo);
    	return refund;
    }
	    
	//환불 수정
    @PutMapping("")
    public ProductsRefund update(@RequestBody ProductsRefund refund) {
    	refundService.update(refund);
    	return refund;
    }
}
