package cn.sc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.sc.pojo.Enterprise;
@Service
public interface EnterpriseService {
	public Enterprise findByUserName(String name);
	public List<String> findname();
	public int add(Enterprise enterprise);
	public List<Enterprise>  findlist(Map<String, Object> queryMap);
	public int  getTotal(Map<String,Object> queryMap);
	public int edit(Enterprise enterprise);
	public int delete(String ids);
}
