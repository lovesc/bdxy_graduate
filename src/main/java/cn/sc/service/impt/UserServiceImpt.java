package cn.sc.service.impt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sc.dao.UserDao;
import cn.sc.pojo.User;
import cn.sc.service.UserService;
@Service
public class UserServiceImpt implements UserService{
  @Autowired
  private UserDao userDao;
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(username);
	}

}
