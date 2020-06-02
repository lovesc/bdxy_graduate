package cn.sc.service;

import org.springframework.stereotype.Service;

import cn.sc.pojo.User;
@Service
public interface UserService {
	public User findByUserName(String username);
}
