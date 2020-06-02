package cn.sc.dao;

import java.util.List;
import java.util.Map;

import cn.sc.pojo.Enterprise;

public interface EnterpriseDao {
	public Enterprise findByUserName(String name);
	public List<String> findname();
	public int add(Enterprise enterprise);
	public List<Enterprise>  findlist(Map<String, Object> queryMap);
	public int  getTotal(Map<String,Object> queryMap);
	public int edit(Enterprise admin);
	public int delete(String ids);
}
