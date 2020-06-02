package cn.sc.service.impt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sc.dao.AddressDao;
import cn.sc.pojo.Address;
import cn.sc.service.AddressService;
@Service
public class AddressServiceImpt implements AddressService {
  @Autowired
  private AddressDao addressDao;
	@Override
	public List<String> findprovince() {
		// TODO Auto-generated method stub
		return addressDao.findprovince();
	}

	@Override
	public String findcity(String province) {
		// TODO Auto-generated method stub
		return addressDao.findcity(province);
	}

	@Override
	public Address findall(String province) {
		// TODO Auto-generated method stub
		return addressDao.findall(province);
	}

}
