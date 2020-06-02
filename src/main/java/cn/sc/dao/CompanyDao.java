package cn.sc.dao;

import java.util.List;
import java.util.Map;

import cn.sc.pojo.Company;


public interface CompanyDao {
	public Company findByUserName(String username);
	public int add(Company company);
	public List<Company>  findlist(Map<String, Object> queryMap);
	public int  getTotal(Map<String,Object> queryMap);
	public int edit(Company company);
	public int delete(String ids);
	public void deletetime();
}
