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
import com.mycompany.webapp.dto.Products;
import com.mycompany.webapp.dto.Users;
import com.mycompany.webapp.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UsersService usersService;

	@GetMapping("")
	public Map<String, Object> list(@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="") String userStateVal,
			@RequestParam(defaultValue="") String idKeywordVal) {

		int totalRows;
		int userState;
		Map<String, Object> map = new HashMap<>();

		if(idKeywordVal.equals("")) {	//키워드가 없으면
			if(userStateVal.equals("전체")) {	//카테고리가 전체이면 AllList
				totalRows = usersService.getTotalCount();
				Pager pager = new Pager(10, 5, totalRows, pageNo);
				List<Users> list = usersService.getUsersList(pager);
				map.put("users", list);
				map.put("pager", pager);
			}else {	//그렇지 않으면 카테고리에 맞는 List
				if(userStateVal.equals("회원")) {
					userState = 0;
				}else {
					userState = 1;
				}
				totalRows = usersService.getTotalStateCount(userState);
				Pager pager = new Pager(10, 5, totalRows, pageNo);
				List<Users> list = usersService.getUsersStateList(pager, userState);
				map.put("users", list);
				map.put("pager", pager);
			}
		}else {
			if(userStateVal.equals("전체")) {	//카테고리가 전체이면 AllList
				totalRows = usersService.getKeywordCount(idKeywordVal);
				Pager pager = new Pager(10, 5, totalRows, pageNo);
				List<Users> list = usersService.getUser(pager, idKeywordVal);
				map.put("users", list);
				map.put("pager", pager);
			}else {	//그렇지 않으면 카테고리에 맞는 List
				if(userStateVal.equals("회원")) {
					userState = 0;
				}else {
					userState = 1;
				}
				totalRows = usersService.getTotalStateKeywordCount(userState, idKeywordVal);
				Pager pager = new Pager(10, 5, totalRows, pageNo);
				List<Users> list = usersService.getUsersStateKeywordList(pager, userState, idKeywordVal);
				map.put("users", list);
				map.put("pager", pager);
			}
			
		}

		return map;
	}   


	@GetMapping("/{uid}")
	public Users read(@PathVariable String uid) {
		Users user = usersService.getUserDetail(uid);
		return user;
	}   
	
	@GetMapping("/readCount")
	public String readCount(int countNo) {
		String result;
		//0, 1, 2를 받아 전체, 회원, 탈퇴 구분
		if(countNo == 0) {
			result = String.valueOf(usersService.getTotalCount());
		}else if(countNo == 1){
			result = String.valueOf(usersService.getTotalStateCount(0));
		}else {
			result = String.valueOf(usersService.getTotalStateCount(1));
		}
		return result;
	}

	@PutMapping("")
	public Users update(@RequestBody Users user) {
		int userState = user.getDeleteState();
		usersService.updateUser(user, userState);
		return user;
	}

}