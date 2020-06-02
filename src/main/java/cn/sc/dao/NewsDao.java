package cn.sc.dao;

import java.util.List;
import java.util.Map;

import cn.sc.pojo.News;
import cn.sc.pojo.Recruit;

public interface NewsDao {
	public News findByUserName(Integer id);
	public int add(News news);
	public List<News>  findlist(Map<String, Object> queryMap);
	public int  getTotal(Map<String,Object> queryMap);
	public int edit(News news);
	public int delete(String ids);
}
