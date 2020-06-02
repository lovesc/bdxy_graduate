package cn.sc.dao;

import java.util.List;
import java.util.Map;

import cn.sc.pojo.Admin;

public interface AdminDao {
	public Admin findByUserName(String username);
	public int add(Admin admin);
	public List<Admin> findList(Map<String,Object> queryMap);
	public int  getTotal(Map<String,Object> queryMap);
	public int edit(Admin admin);
	public int delete(String ids);
}
