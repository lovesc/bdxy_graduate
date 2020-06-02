package cn.sc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.sc.pojo.Company;
@Service
public interface CompanyService {
	public Company findByUserName(String username);
	public int add(Company company);
	public List<Company>  findlist(Map<String, Object> queryMap);
	public int  getTotal(Map<String,Object> queryMap);
	public int edit(Company company);
	public int delete(String ids);
	public void deletetime();
}
