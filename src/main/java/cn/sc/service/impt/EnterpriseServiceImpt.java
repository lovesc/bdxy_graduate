package cn.sc.service.impt;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sc.dao.EnterpriseDao;
import cn.sc.pojo.Enterprise;
import cn.sc.service.EnterpriseService;

@Service
public class EnterpriseServiceImpt implements EnterpriseService {
@Autowired
private EnterpriseDao enterpriseDao;
	@Override
	public Enterprise findByUserName(String name) {
		// TODO Auto-generated method stub
		return enterpriseDao.findByUserName(name);
	}

	@Override
	public int add(Enterprise enterprise) {
		// TODO Auto-generated method stub
		return enterpriseDao.add(enterprise);
	}

	@Override
	public List<Enterprise> findlist(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return enterpriseDao.findlist(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return enterpriseDao.getTotal(queryMap);
	}

	@Override
	public int edit(Enterprise enterprise) {
		// TODO Auto-generated method stub
		return enterpriseDao.edit(enterprise);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return enterpriseDao.delete(ids);
	}

	@Override
	public List<String> findname() {
		// TODO Auto-generated method stub
		return enterpriseDao.findname();
	}

}
