package cn.sc.service.impt;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sc.dao.NewsDao;
import cn.sc.pojo.News;
import cn.sc.pojo.Recruit;
import cn.sc.service.NewsService;
@Service
public class NewsServiceImpt implements NewsService {
	@Autowired
	private NewsDao newsDao;

	@Override
	public News findById(Integer id) {
		// TODO Auto-generated method stub
		return newsDao.findByUserName(id);
	}

	@Override
	public int add(News news) {
		// TODO Auto-generated method stub
		return newsDao.add(news);
	}

	@Override
	public List<News> findlist(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return newsDao.findlist(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return newsDao.getTotal(queryMap);
	}

	@Override
	public int edit(News news) {
		// TODO Auto-generated method stub
		return newsDao.edit(news);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return newsDao.delete(ids);
	}
}
