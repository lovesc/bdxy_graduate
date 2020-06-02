package cn.sc.service.impt;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sc.dao.CompanyDao;
import cn.sc.pojo.Company;
import cn.sc.service.CompanyService;
@Service
public class CompanyServiceImpt  implements CompanyService{
@Autowired
private CompanyDao companyDao;
	public Company findByUserName(String username) {
		// TODO Auto-generated method stub
		return companyDao.findByUserName(username);
	}

	public int add(Company company) {
		// TODO Auto-generated method stub
		return companyDao.add(company);
	}

	public List<Company> findlist(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return companyDao.findlist(queryMap);
	}

	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return companyDao.getTotal(queryMap);
	}

	public int edit(Company company) {
		// TODO Auto-generated method stub
		return companyDao.edit(company);
	}

	public int delete(String ids) {
		// TODO Auto-generated method stub
		return companyDao.delete(ids);
	}

	public void deletetime() {
		// TODO Auto-generated method stub
		companyDao.deletetime();
		
	}

}
