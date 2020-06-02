package cn.sc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.sc.pojo.News;
import cn.sc.pojo.Recruit;
@Service
public interface NewsService {
	public News findById(Integer id);
	public int add(News news);
	public List<News>  findlist(Map<String, Object> queryMap);
	public int  getTotal(Map<String,Object> queryMap);
	public int edit(News news);
	public int delete(String ids);
}
