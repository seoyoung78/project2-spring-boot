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

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Reviews;
import com.mycompany.webapp.service.OrderProductsService;
import com.mycompany.webapp.service.ReviewsService;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
private final Logger logger = LoggerFactory.getLogger(ReviewsController.class);
	
	@Autowired
	private ReviewsService reviewsService;
	@Autowired
	private OrderProductsService orderProductsService;
	
	//리뷰 목록
	@GetMapping("")
	private Map<String, Object> list(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue ="") String keyword) {		
		int totalRows = reviewsService.getCount(keyword);
		Pager pager = new Pager(10, 5, totalRows, pageNo);
		
		List<Reviews> list = reviewsService.getList(pager, keyword);
		
		Map<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("reviews", list);
		
		return map;
	}

	//리뷰 개수 리턴
	@GetMapping("/readcount")
	public int readCount(int countNo) {
		int result;
		if(countNo == 0) {
			result = reviewsService.getCount("");
		} else if(countNo == 1) {
			result = orderProductsService.getCount(0);
		}  else {
			result = orderProductsService.getCount(1);
		}
		return result;
	}
	
	//리뷰 조회
	@GetMapping("/{reviewNo}")
    public Reviews read(@PathVariable int reviewNo) {	
		Reviews review= reviewsService.getReview(reviewNo);
    	return review;
    }
	
    //리뷰 수정
    @PutMapping("")
    public Reviews update(@RequestBody Reviews review) {
    	reviewsService.update(review);
    	return review;
    }
    
    //리뷰 삭제
    @DeleteMapping("/{reviewNo}")
    public void delete(@PathVariable int reviewNo) {
    	reviewsService.delete(reviewNo);
    }
}
