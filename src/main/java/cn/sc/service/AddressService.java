package cn.sc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.sc.pojo.Address;
@Service
public interface AddressService {

	public List<String> findprovince();

	public String findcity(String province);
	public Address findall(String province);
}
