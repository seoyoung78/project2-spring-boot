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

import com.mycompany.webapp.dto.Orders;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Qna;
import com.mycompany.webapp.service.QnaService;

@RestController
@RequestMapping("/qna")
public class QnAController {
	private final Logger logger = LoggerFactory.getLogger(QnAController.class);
	
	@Autowired
	private QnaService qnaService;
	
//	 @GetMapping("")
//	   public Map<String, Object> list(@RequestParam(defaultValue="1") int pageNo) {
//	      int totalRows = qnaService.getCount();
//	      Pager pager = new Pager(5, 5, totalRows, pageNo);
//	      List<Qna> list = qnaService.getList(pager);
//	      Map<String,Object> map = new HashMap<>();
//	      map.put("pager", pager);
//	      map.put("qna", list); //qna in qna
//	      return map;
//	   }
	   
//	   
//	 	@GetMapping("/keyword")
//	   public Map<String, Object> searchList(@RequestParam(defaultValue="") String keyword, @RequestParam(defaultValue="1") int pageNo) {
//		   int totalRows = qnaService.getCount(keyword);
//		   Pager pager = new Pager(5, 5, totalRows, pageNo);
//	      List<Qna> list = qnaService.getList(pager, keyword);
//	      Map<String,Object> map = new HashMap<>();
//	      map.put("pager", pager);
//	      map.put("qna", list); //qna in qna
//		  
//	      return map;
//	   }
//	 
	 	
 	 	@GetMapping("/stateval")
 	   public Map<String, Object> statevalList(@RequestParam(defaultValue="") String state, @RequestParam(defaultValue="1") int pageNo) {
 	 		int stateval = 2;
 	 		if(state.equals("미답변")) {
 	 			stateval = 0;
 	 		}else if(state.equals("답변 완료")){
 	 			stateval = 1;
 	 		}
 	 		
 		   int totalRows = qnaService.getStatevalCount(stateval);
 		   Pager pager = new Pager(10, 5, totalRows, pageNo);
 	      List<Qna> list = qnaService.getStatevalList(pager, stateval);
 	      Map<String,Object> map = new HashMap<>();
 	      map.put("pager", pager);
 	      map.put("qna", list); //qna in qna
 		  
 	      return map;
 	   }
	 	 
	 	
		/*
		 * @GetMapping("/{keyword}") public Map<String, Object>
		 * searchList(@RequestParam(defaultValue="1") int pageNo, @PathVariable String
		 * keyword) { int totalRows = qnaService.getCount(); Pager pager; if(keyword !=
		 * null) { totalRows = qnaService.getKeywordCount(); pager = new Pager(5, 5,
		 * totalRows, pageNo); pager.setKeyWord(keyword); }else{ pager = new Pager(5, 5,
		 * totalRows, pageNo); } List<Qna> list = qnaService.getList(pager);
		 * Map<String,Object> map = new HashMap<>(); map.put("pager", pager);
		 * map.put("qna", list); //qna in qna return map; }
		 */
	
	   @GetMapping("/{qnaNo}")
	   public Qna read(@PathVariable int qnaNo) {
		   Qna qna = qnaService.getQna(qnaNo);
		   return qna;
	   }
	   
	   @PutMapping("")
	   public Qna update(@RequestBody Qna qna) {
		   
		   qnaService.update(qna);
		 return qna;
	   }
	   
	   @DeleteMapping("/{qnaNo}")
	   public void delete(@PathVariable int qnaNo) {
		   qnaService.delete(qnaNo);
	   }
	   
	   @GetMapping("/readCount")
		public String readCount(int countNo) {
			String result;
			if(countNo == 0) {
				result = String.valueOf(qnaService.getTotalCount());
			}else if(countNo == 1){
				result = String.valueOf(qnaService.getTotalStateCount(0));
			}else {
				result = String.valueOf(qnaService.getTotalStateCount(1));
			}
			return result;
		}
	
}
