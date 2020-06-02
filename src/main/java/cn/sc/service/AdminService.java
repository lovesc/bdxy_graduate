package cn.sc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.sc.pojo.Admin;
@Service
public interface AdminService {

	public Admin findByUserName(String username);

	public int add(Admin admin);

	public List<Admin> findList(Map<String, Object> queryMap);

	public int getTotal(Map<String, Object> queryMap);

	public int edit(Admin admin);

	public int delete(String ids);
}
