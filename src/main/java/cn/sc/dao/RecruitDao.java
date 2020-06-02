package cn.sc.dao;

import java.util.List;
import java.util.Map;
import cn.sc.pojo.Recruit;

public interface RecruitDao {
	public Recruit findByUserName(String username);
	public int add(Recruit recruit);
	public List<Recruit>  findlist(Map<String, Object> queryMap);
	public int  getTotal(Map<String,Object> queryMap);
	public int edit(Recruit admin);
	public int delete(String ids);
	public void deletetime();
}




































