package cn.sc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.sc.pojo.User;



@Repository
public interface UserDao {
	public User findByUserName(String username);

}
