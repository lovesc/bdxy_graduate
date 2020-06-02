package cn.sc.service.impt;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sc.dao.RecruitDao;
import cn.sc.pojo.Recruit;
import cn.sc.service.RecruitService;
@Service
public class RecruitServiceImpt implements RecruitService {
@Autowired
private RecruitDao recruitDao;
	public Recruit findByUserName(String username) {
		// TODO Auto-generated method stub
		return recruitDao.findByUserName(username);
	}

	public int add(Recruit recruit) {
		// TODO Auto-generated method stub
		return recruitDao.add(recruit);
	}

	public List<Recruit> findlist(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return recruitDao.findlist(queryMap);
	}

	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return recruitDao.getTotal(queryMap);
	}

	public int edit(Recruit recruit) {
		// TODO Auto-generated method stub
		return recruitDao.edit(recruit);
	}

	public int delete(String ids) {
		// TODO Auto-generated method stub
		return recruitDao.delete(ids);
	}

	public void deletetime() {
		// TODO Auto-generated method stub
		recruitDao.deletetime();
		System.out.println("删除成功");	
	}

}
