package com.mycompany.webapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.QnaDao;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Qna;

@Service
public class QnaService{
	
	private static final Logger logger = LoggerFactory.getLogger(QnaService.class);
	
	@Autowired
	private QnaDao qnaDao;
	 
	 public List<Qna> getStatevalList(Pager pager,int stateval) {
		      return qnaDao.selectStatevalByPage(pager, stateval);
		   }
	 
	 public int getStatevalCount(int stateval) {
		   return qnaDao.statevalCount(stateval);
	 }
	 
	 public Qna getQna(int qnaNo) {
	    return qnaDao.selectByQnaNo(qnaNo);
	 }
	 
	 public int insert(Qna qna) {
	    return qnaDao.insert(qna);
	 }
	 
	 public int delete(int qnaNo) {
	    return qnaDao.deleteByQnaNo(qnaNo);
	 }
	 
	 public int update(Qna qna) {
	    return qnaDao.update(qna);
	 }

		
		//	   public List<Qna> getList(Pager pager, String keyword) {
		//    return qnaDao.selectByPage(pager,keyword);
		// }
		// public int getCount(String keyword) {
		// return qnaDao.count(keyword);
		//	}
	 
	 public int getTotalCount() {
		int result = qnaDao.totalcount();
		return result;
	 }

	 public int getTotalStateCount(int answerState) {
		int result = qnaDao.stateCount(answerState);
		return result;
	 }
}