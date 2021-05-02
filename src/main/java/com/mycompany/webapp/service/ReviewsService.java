package com.mycompany.webapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.ReviewsDao;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Reviews;

@Service
public class ReviewsService {
	private static final Logger logger = LoggerFactory.getLogger(ReviewsService.class);
	
	@Autowired
	private ReviewsDao reviewsDao;

	public int getCount(String keyword) {
		int rows = reviewsDao.count(keyword);
		return rows;
	}
	
	public List<Reviews> getList(Pager pager, String keyword) {
		List<Reviews> list = reviewsDao.selectByPage(pager, keyword);
		return list;
	}

	public Reviews getReview(int reviewNo) {
		return reviewsDao.selectByRno(reviewNo);
	}

	public void update(Reviews review) {
		reviewsDao.update(review);
	}

	public void delete(int reviewNo) {
		reviewsDao.deleteReview(reviewNo);
	}

}
