package cn.sc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.sc.pojo.Recruit;
@Service
public interface RecruitService {
	public Recruit findByUserName(String username);
	public int add(Recruit recruit);
	public List<Recruit> findlist(Map<String,Object> queryMap);
	public int  getTotal(Map<String,Object> queryMap);
	public int edit(Recruit recruit);
	public int delete(String ids);
	public void deletetime();
}
