package cn.sc.service.impt;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sc.dao.AdminDao;
import cn.sc.pojo.Admin;
import cn.sc.service.AdminService;
@Service
public class AdminServiceImpt implements AdminService{
@Autowired
private AdminDao adminDao;
	public Admin findByUserName(String username) {
		// TODO Auto-generated method stub
		return adminDao.findByUserName(username);
	}

	public int add(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.add(admin);
	}

	public List<Admin> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return adminDao.findList(queryMap);
	}

	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return adminDao.getTotal(queryMap);
	}

	public int edit(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.edit(admin);
	}

	public int delete(String ids) {
		// TODO Auto-generated method stub
		return adminDao.delete(ids);
	}

}
