package com.mycompany.webapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.UsersDao;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Products;
import com.mycompany.webapp.dto.Users;

@Service
public class UsersService {
	private static final Logger logger =
			LoggerFactory.getLogger(UsersService.class);

	@Autowired
	private UsersDao usersDao;

	//전체 카운트, 유저 목록
	public int getTotalCount() {
		int result = usersDao.count();
		return result;
	}
	public List<Users> getUsersList(Pager pager) {
		List<Users> list = usersDao.selectByPage(pager);
		return list;
	}
	
	//상태 별 카운트, 유저 목록
	public int getTotalStateCount(int userState) {
		int result = usersDao.stateCount(userState);
		return result;
	}
	public List<Users> getUsersStateList(Pager pager, int userState) {
		List<Users> list = usersDao.selectByStatePage(pager, userState);
		return list;
	}
	
	//키워드 별 카운트, 유저 목록
	public int getKeywordCount(String idKeywordVal) {
		int result = usersDao.keywordCount(idKeywordVal);
		return result;
	}
	public List<Users> getUser(Pager pager, String idKeywordVal) {
		List<Users> list = usersDao.selectByUserPage(pager, idKeywordVal);
		return list;
	}

	//키워드 + 상태 별 카운트, 유저 목록
	public int getTotalStateKeywordCount(int userState, String idKeywordVal) {
		int result = usersDao.stateKeywordCount(userState, idKeywordVal);
		return result;
	}
	public List<Users> getUsersStateKeywordList(Pager pager, int userState, String idKeywordVal) {
		List<Users> list = usersDao.selectByStateKeywordPage(pager, userState, idKeywordVal);
		return list;
	}
	
	//유저 상세정보
	public Users getUserDetail(String userId) {
		Users user = usersDao.selectByUserid(userId);
		return user;
	}
	
	//유저 상태 수정
	public int updateUser(Users user, int userState) {
		int result = usersDao.updateUser(user, userState);
		return result;
	}


}
